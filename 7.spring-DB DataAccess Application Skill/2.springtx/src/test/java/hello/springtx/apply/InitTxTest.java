package hello.springtx.apply;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.PostConstruct;

/**
 * Transaction의 초기화 시점 테스트
 * @PostConstruct로는 불가능하다. 초기화 코드가 먼저 호출된뒤 AOP가 적용되기 때문이다.
 * 확실한 대안은 @EventListenr이다.
 */
@SpringBootTest
public class InitTxTest {
    @Autowired Hello hello;

    @Test
    public void go(){
    }

    @TestConfiguration
    static class InitTxTestConfig{
        @Bean
        Hello hello(){
            return new Hello();
        }
    }

    @Slf4j
    static class Hello{
        @PostConstruct
        @Transactional
        public void initV1(){
            boolean isActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("Hello init @PostConstruct tx = active={}", isActive);
        }

        @EventListener(value = ApplicationReadyEvent.class)
        @Transactional
        public void init2(){
            boolean isActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("Heello init ApplicationReadyEvent tx active={}", isActive);
        }
    }
}
