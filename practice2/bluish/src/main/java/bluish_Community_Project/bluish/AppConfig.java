package bluish_Community_Project.bluish;

import bluish_Community_Project.bluish.discount.DiscountPolicy;
import bluish_Community_Project.bluish.discount.RateDiscountPolicy;
import bluish_Community_Project.bluish.member.*;
import bluish_Community_Project.bluish.order.OrderService;
import bluish_Community_Project.bluish.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

   /* @Bean
    public MemberService memberService2(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }*/

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        System.out.println("call AppConfig.discountPolicy");
        return new RateDiscountPolicy();
    }
}
