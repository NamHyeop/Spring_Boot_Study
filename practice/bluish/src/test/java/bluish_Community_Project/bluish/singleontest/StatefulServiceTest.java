package bluish_Community_Project.bluish.singleontest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    @DisplayName("멀티스레드 환경에서 상태와 무상태 영역의 발생할 수 있는 오류를 임의로 만들어보기")
    public void statefulServiceSingleton() throws Exception{
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(Testconfig.class);
        //when
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);
        //then
        statefulService1.order("버거킹", 12222);
        statefulService2.order("맥도날", 20000);
        //이딴 식으로 하면 동시성 문제 발생하는거임, 조심하고 또 조심하자
        System.out.println("statefulService1.getPrice() = " + statefulService1.getPrice());
        System.out.println("statefulService2.getPrice() = " + statefulService2.getPrice());
    }

    static class Testconfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}