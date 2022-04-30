package bluish_Community_Project.bluish.singleontest;

import bluish_Community_Project.bluish.AppConfig;
//import bluish_Community_Project.bluish.discount.DiscountPolicyConfig;
import bluish_Community_Project.bluish.member.MemberRepository;
import bluish_Community_Project.bluish.member.MemberService;
import bluish_Community_Project.bluish.member.MemberServiceImpl;
import bluish_Community_Project.bluish.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    public void configurationTest() throws Exception {
        //given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //when
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
//        MemberServiceImpl memberService2 = ac.getBean("memberService2", MemberServiceImpl.class);
        MemberRepository memberRepository3 = ac.getBean("memberRepository", MemberRepository.class);

        //then
        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();
//        MemberRepository memberRepository4 = memberService2.getMemberRepository();

        System.out.println("memberservice -> memberRepository" + memberRepository1);
        System.out.println("orderservice -> memberRepository" + memberRepository2);
        System.out.println("memberRepository->" + memberRepository3);
//        System.out.println("memberservice2->" + memberRepository4);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository3);
//        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository3);
    }

//    @Test
//    public void deppInfo() throws Exception{
//        ApplicationContext ac = new AnnotationConfigApplicationContext(DiscountPolicyConfig.class);
//        DiscountPolicyConfig bean = ac.getBean(DiscountPolicyConfig.class);
//        System.out.println("bean = " + bean.getClass());
//    }
}
