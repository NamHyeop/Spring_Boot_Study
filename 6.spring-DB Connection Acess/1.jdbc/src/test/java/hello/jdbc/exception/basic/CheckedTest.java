package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class CheckedTest {
    @Test
    public void checked_catch() {
        Service service = new Service();
        service.callCatch();
    }

    @Test
    public void checkded_throw() {
        //given
        Service service = new Service();
        //when
        //then
        assertThatThrownBy(() -> service.callThrow()).isInstanceOf(MyCheckedException.class);
    }

    /**
     * Exception을 상속받는 사용자 정의 체크 예외
     */
    static class MyCheckedException extends Exception {
        public MyCheckedException(String message) {
            super(message);
        }
    }

    /**
     * checked 예외는 catch를 사용해서 처리하거나 throws로 던지거나 둘 중 선택해야한다.
     */
    static class Service{
        Repository repository = new Repository();

        /**
         * 예외를 잡아서 처리하는 코드
         */
        public void callCatch() {
            try {
                repository.call();
            } catch (MyCheckedException e) {
                log.info("예외 처리, message={}", e.getMessage(), e);
            }
        }

        /**
         * 체크 예외를 밖으로 던지는 코드
         * 체크 예외는 예외를 잡지 않고 밖으로 던지려면 throws 예외를 메서드에 필수로 선언해야한다.
         */
        public void callThrow() throws MyCheckedException {
            repository.call();
        }
    }

    /**
     * Checkded 예외는
     * Catch를 사용하여 예외를 처리하거나
     * Throw를 명시하여 던지거나 둘 중 반드시 한 가지 처리를 해야한다.
     */
    static class Repository {
        public void call() throws MyCheckedException {
            throw new MyCheckedException("ex");
        }
    }
}
