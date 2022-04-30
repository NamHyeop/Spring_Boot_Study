package bluish_Community_Project.bluish.autowired;

import bluish_Community_Project.bluish.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {
    @Test
    public void AutowiredOption() throws Exception{
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(TestConfig.class);
    }

    static class TestConfig{
        @Autowired(required = false)
        public void setNoBean1(Member member){
            System.out.println("member = " + member);
        }

        @Autowired
        public void setNoBean2(@Nullable Member member){
            System.out.println("member = " + member);
        }

        @Autowired
        public void setNoBean3(@Nullable Optional<Member> member){
            System.out.println("member = " + member);
        }
    }
}
