package hello.jdbc.exception.translator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * 오류코드를 직접 찾아서 사용하는 방식과 Spring이 제공하는 SQLExceptionTransltor를 사용한 방식의 차이
 * SQLExceptionTranslotor를 사용하는것이 좀 더 효율적이다.
 */
@Slf4j
public class SpringExceptionTranslatorTest {
    DataSource dataSource;

    @BeforeEach
    void init(){
        dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
    }

    /**
     * 오류코드를 직접 정의해서 사용하는 방식, 비효율적이다.
     */
    @Test
    public void sqlExceptionErrorCode(){
        String sql = "select bad grammer";
        try{
            Connection con = dataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.executeQuery();
        }catch (SQLException e){
            assertThat(e.getErrorCode()).isEqualTo(42122);
            int errorCode = e.getErrorCode();
            log.info("errorCode={}", errorCode);
            //org.h2.jdbc.jdbcQLSyntaxErrorException 오류 발생ㅇ
            log.info("error", e);
        }
    }

    @Test
    public void exceptionTarnslator(){
      String sql = "select bad grammer";
      try{
          Connection con = dataSource.getConnection();
          PreparedStatement preparedStatement = con.prepareStatement(sql);
      } catch (SQLException e) {
          assertThat(e.getErrorCode()).isEqualTo(42122);
          /**
           * org.springframework.jdbc.suppor.sql-error-codes.xml에 등록된 오류코드를 조회한다.
           * DB별로 오류코드들이 전부 등록되어있다.
           */
          SQLErrorCodeSQLExceptionTranslator exTranslator = new SQLErrorCodeSQLExceptionTranslator(dataSource);
          //org.springframework.jdbc.BadSqlGrammerException 오류 발생
          /**
           * 매개변수는 오류 이름 정보, 쿼리, exception 변수 이다.
           */
          DataAccessException resultEx = exTranslator.translate("exception info position", sql, e);
          System.out.println("resultEx = " + resultEx);
          assertThat(resultEx.getClass()).isEqualTo(BadSqlGrammarException.class);
      }
    }
}
