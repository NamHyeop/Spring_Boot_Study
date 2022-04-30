package bluish_Community_Project.bluish.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.Lifecycle;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class BeanLifeCycleTest {
    @Test
    public void lifeCycleTest() throws Exception {
        //given
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        //when
        NetWorkClient bean = ac.getBean(NetWorkClient.class);
        //then
        ac.close(); // 스프링 컨테이너를 종료한다.
    }

    static class LifeCycleConfig{
//        @Bean(initMethod = "init", destroyMethod = "close")
        public NetWorkClient netWorkClient(){
            NetWorkClient netWorkClient = new NetWorkClient();
            netWorkClient.setUrl("http://hello-spring.com");
            return netWorkClient;
        }
    }
}
