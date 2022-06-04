package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {
    @Test
    public void singletonBeenFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
        System.out.println(" what is");
        SingletonBean bean1 = ac.getBean(SingletonBean.class);
        System.out.println("make bean1");
        SingletonBean bean2 = ac.getBean(SingletonBean.class);
        System.out.println("make baan2");
        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);
        assertThat(bean1).isSameAs(bean2);
        ac.close();
    }

    @Scope("singleton")
    static class SingletonBean{
        @PostConstruct
        public void init(){
            System.out.println("SingletonBean.init");
        }
        @PreDestroy
        public void destroy(){
            System.out.println("SingletonBean.destroy");
        }
    }

}
