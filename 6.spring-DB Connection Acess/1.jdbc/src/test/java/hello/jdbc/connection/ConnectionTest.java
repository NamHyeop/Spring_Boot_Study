package hello.jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
public class ConnectionTest {
    @Test
    public void driverManager() throws Exception{
        //given
        Connection con1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection con2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        //when
        //then
        log.info("connection={}, class={}", con1, con1.getClass());
        log.info("connection={}, class={}", con2, con2.getClass());
    }

    @Test
    public void dataSourceDriverManager() throws Exception{
        //given
        /**
         * DriverManagerDatasource - DirverManager와 동일하게 DriverManagerDatasource도 항상 새로운 커넥션을 획득한다.
         * 그러나 설정이 한 번뿐이라는 점에서 차이가 존재한다.
         * 이를 통해 사용자는 한 번의 설정으로 여러번의 Connection을 자유롭게 사용이 가능하다.
         * 즉 설정과 사용이 분리된다는 점에서 장점이 존재한다.
         */
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        //when
        //then
        useDataSource(dataSource);
    }

    @Test
    public void dataSourceConnectionPool() throws Exception{
        /**
         * HikariCP 커넥션 풀 사용 예제
         */
        //given
        HikariDataSource dataSource = new HikariDataSource();
        //when
        //then
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        /**
         * 커넥션 풀 최대 사이즈 지정, 기본값 자체가 10으로 설정되어있다.
         */
        dataSource.setMaximumPoolSize(10);
        dataSource.setPoolName("MyPool");
        useDataSource(dataSource);
        /**
         * 커넥션 풀은 별도의 스레드로 생성이 진행되므로 생성 속도가 로직 처리시간보다 느리다.
         * 전체 과정 확인을 위해 sleep를 추가해주었다.
         */
        Thread.sleep(3000);
    }

    private void useDataSource(DataSource dataSource) throws SQLException {
        Connection con1 = dataSource.getConnection();
        Connection con2 = dataSource.getConnection();
        log.info("connection={}, class={}", con1, con1.getClass());
        log.info("connection={}, class={}", con2, con2.getClass());
    }
}
