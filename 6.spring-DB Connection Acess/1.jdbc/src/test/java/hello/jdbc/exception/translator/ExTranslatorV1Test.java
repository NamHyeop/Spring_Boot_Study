package hello.jdbc.exception.translator;


import hello.jdbc.domain.Member;
import hello.jdbc.repository.ex.MyDbException;
import hello.jdbc.repository.ex.MyDuplicateKeyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import static hello.jdbc.connection.ConnectionConst.*;
import static org.springframework.jdbc.support.JdbcUtils.closeConnection;
import static org.springframework.jdbc.support.JdbcUtils.closeStatement;

/**
 * 별도의 중복 아이디 발생 예외처리를 제작한 뒤
 * 예외처리가 정상적으로 잘 진행되는지 확인하는 테스트
 */
public class ExTranslatorV1Test {
    Repository repository;
    Service service;

    @BeforeEach
    void init() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL,
                USERNAME, PASSWORD);
        repository = new Repository(dataSource);
        service = new Service(repository);
    }

    @Test
    void duplicateKeySave(){
        service.create("myId");;
        service.create("myId");;
    }


    @Slf4j
    @RequiredArgsConstructor
    static class Service{
        private final Repository repository;

        public void create(String memberId){
            try {
                repository.save(new Member(memberId, 0));
                log.info("saveId={}", memberId);
            }catch (MyDuplicateKeyException e){
                log.info("키 중복 발생, 복구 시도");
                String retryId = generateNewId(memberId);
                System.out.println("retryId = " + retryId);
                repository.save(new Member(retryId, 0));
            }catch (MyDbException e){
                log.info("데이터 접근 계층 예외", e);
                throw e;
            }
        }

        private String generateNewId(String memberId) {
            return memberId + new Random().nextInt(10000);
        }
    }


    @RequiredArgsConstructor
    static class Repository{
        private final DataSource dataSource;

        public Member save(Member member){
            String sql = "insert into member(member_id, money) values(?,?)";

            Connection con = null;
            PreparedStatement pstmt = null;
            try{
                con = dataSource.getConnection();
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, member.getMemberId());
                pstmt.setInt(2, member.getMoney());
                pstmt.executeUpdate();
                return member;
            }catch (SQLException e){
                if(e.getErrorCode() == 23505){
                    throw new MyDuplicateKeyException(e);
                }
                throw new MyDbException(e);
            }finally {
                closeStatement(pstmt);
                closeConnection(con);
            }
        }
    }
}