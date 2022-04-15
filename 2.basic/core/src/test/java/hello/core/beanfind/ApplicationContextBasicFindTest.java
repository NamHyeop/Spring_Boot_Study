package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    /**
     * Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class)는 memberservice가 MemberServiceImpl의 객체가 맞는지 판단하는 과정
     */

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
//        System.out.println("memberService = " + memberService);
//        System.out.println("memberService.getClass() = " + memberService.getClass());
    }

    /**
     * 타입으로만 조회할 경우 같은 이름이 있는 경우 기대값과 다른 경우가 존재할 수 있다.
     */
    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    /**
     *  타입을 구체(구현화)타입으로 조회 했는데 이는 좋지 않은 방법, 앞에서 말한 객체 지향적 5요소에서 의해 인터페이스 의존적이어야 하지만 이것은 구체화 의존적
     *  하지만 사는것이 규율만 지키면서 사는것은 불가능.. 상황에 맞게 사용하자
     */
    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    /**
     * assertThrow는 괄호 안의 예외가 발생이 우측 매개변수가 실행되는 함수
     */

    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanBynameX(){
        //ac.getBean("xxxxx", MemberService.class);
        //MemberService xxxxx = ac.getBean("xxxxx", MemberService.class);
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxxx", MemberService.class) );
    }
}
