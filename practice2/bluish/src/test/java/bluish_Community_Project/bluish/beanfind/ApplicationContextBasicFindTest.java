package bluish_Community_Project.bluish.beanfind;

import bluish_Community_Project.bluish.AppConfig;
import bluish_Community_Project.bluish.member.MemberService;
import bluish_Community_Project.bluish.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회하기")
    public void findBeanByName() throws Exception{
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회하기")
    public void findBeanByType() throws Exception{
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회하기")
    public void findCertainType() throws Exception{
        //given
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    //NoSuchBeanDefinitionException이 발생하여야 테스트 성공
    @Test
    @DisplayName("없는 빈 이름으로 조회하여 오류 유도하기")
    public void findNoBeanName() throws Exception{
        //given
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("Nothing name", MemberService.class));
    }
}
