package bluish_Community_Project.bluish.scan;

import bluish_Community_Project.bluish.AutoAppConfig;
import bluish_Community_Project.bluish.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {
    @Test
    public void basicScan() throws Exception{
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        //when
        MemberService mem = ac.getBean(MemberService.class);
        //then
        Assertions.assertThat(mem).isInstanceOf(MemberService.class);
    }
}
