package bluish_Community_Project.bluish.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingltoWithPrototypeTest1 {


    @Test
    public void prototypeFind() throws Exception{
        //given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class); //프로토타입 1번 생성
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);
        //when
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class); // 프로토타입 2번 생성, 대신 싱글톤 객체는 똑같이 유지
        prototypeBean2.addCount();
        //then
    }
    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
    public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }

}
