package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Check Exception에 대한 문제점을 보여주는 TEST이다.
 * 1.SQLException으로 인해 JDBC 기술에 종속적이게 되어 Spring의 OCP, DI의 장점이 사라진다.
 * 2.무분별한 throws로 인한 코드 가독성이 하락된다.
 * 3.SQLException과 ConnectException이 Controller와 Service에서 해결될 문제가 아니다.
 */
@Slf4j
public class CheckedAppTest {
    @Test
    public void checked(){
        Controller controller = new Controller();
        assertThatThrownBy(() -> controller.request()).isInstanceOf(Exception.class);
    }

    static class Controller{
        Service service = new Service();
        public void request() throws SQLException, ConnectException {
            service.logic();
        }
    }

    static class Service{
        Repository repository = new Repository();
        NetworkClient networkClient = new NetworkClient();

        public void logic() throws SQLException, ConnectException{
            repository.call();
            networkClient.call();
        }
    }

    static class NetworkClient{
        public void call() throws ConnectException{
            throw new ConnectException("연결 실패");
        }
    }

    static class Repository{
        public void call() throws SQLException {
            throw new SQLException("ex");
        }
    }
}
