package bluish_Community_Project.bluish.scope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

public class SingletonTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();

    static class SingletonBean{
        @PostConstruct
        public void init(){
            System.out.println("singletonBean.init");
        }
    }
}

