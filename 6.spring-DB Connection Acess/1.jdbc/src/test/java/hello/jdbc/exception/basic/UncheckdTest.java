package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class UncheckdTest {
    @Test
    public void unchecked_catch(){
        //given
        Service service = new Service();
        //when
        //then
        service.callCatch();
    }

    @Test
    public void unchecked_throw(){
        Service service = new Service();
        Assertions.assertThatThrownBy(() -> service.callThrow()).isInstanceOf(MyUncheckedException.class);
    }

    /**
     * RuntimeException을 상속받은 예외는 언체크 예외가 된다.
     */
    static class MyUncheckedException extends RuntimeException{
        public MyUncheckedException(String message){
            super(message);
        }
    }

    /**
     * UnChecked 예외는
     * 예외를 잡거나, 던지지 않아도 된다.
     * 예외를 잡지 않으면 자동으로 밖으로 던진다. */

    static class Service{
        Repository repository = new Repository();

        /**
         * catch를 통해 예외를 잡아도 되고 안해도 괜찮다.
         */
        public void callCatch(){
            try{
                repository.call();
            }catch (MyUncheckedException e){
                log.info("예외처리, message={}", e.getMessage(), e);
            }
        }

        /**
         * RunTimeException이 언체크 예외 이기 때문에 예외를 잡지 않아도 된다.
         * 상위로 넘어간다.
         * 그렇게 때문에 throws 예외 선언을하지 않아도 된다.
         */
        public void callThrow(){
            repository.call();
        }
    }

    static class Repository{
        public void call(){
            throw new MyUncheckedException("ex");
        }
    }
}
