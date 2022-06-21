package hello.springtx.exception;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * 체크 예외 발생시와 언체크 예외 발생이의 각각의 커밋, 롤백 상태를 테스트하는 테스트
 * 체크 예외 발생시에는 커밋을 하게 되고 언체크 예외가 발생할 경우에는 롤백하게 된다.
 */
@SpringBootTest
public class RollbackTest {
    @Autowired RollbackService service;

    @Test
    public void runtimeException(){
        assertThatThrownBy(() -> service.runTimeException()).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void checkedException(){
        assertThatThrownBy(() -> service.checkedException()).isInstanceOf(MyException.class);
    }

    @Test
    public void rollbackFor(){
        assertThatThrownBy(() -> service.rollbackFor()).isInstanceOf(MyException.class);
    }
    @TestConfiguration
    public static class RollbackTestConfig{
        @Bean
        RollbackService rollbackService(){
            return new RollbackService();
        }
    }
    @Slf4j
    static class RollbackService{
        //RunException 발생 : -> 롤백 발생
        @Transactional
        public void runTimeException(){
            log.info("call runtimeException");
            throw new RuntimeException();
        }

        //체크 예외 발생 : -> 커밋 발생
        @Transactional
        public void checkedException() throws MyException{
            log.info("call checkedException");
            throw new MyException();
        }

        //체크 예외 발생 -> 그러나 체크 예외 발생시 롤백하도록 기본값을 설정하여 롤백 발생
        @Transactional(rollbackFor = MyException.class)
        public void rollbackFor() throws MyException{
            log.info("call rollbackFor");
            throw new MyException();
        }
    }

    static class MyException extends Exception{

    }
}

