package bluish_Community_Project.bluish.singleontest;

import bluish_Community_Project.bluish.AppConfig;
import bluish_Community_Project.bluish.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너 만드는 테스트, 싱글톤과 다르게 객체가 하나가 아님")
    public void preContatiner() throws Exception{
        //given
        AppConfig appConfig = new AppConfig();
        //when
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();
        //then
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isNotEqualTo(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체사용")
    void singletonServiceTest(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();
        //isEqualto는 가지고 있는 값 자체가 같은지 비교
        Assertions.assertThat(singletonService1).isEqualTo(singletonService2);
        //isSameas는 주소값이 같은지 비교
        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤 예시")
    public void springContainer() throws Exception{
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //when
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);
        //then
        System.out.println("memberService2 = " + memberService2);
        System.out.println("memberService1 = " + memberService1);
        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}
