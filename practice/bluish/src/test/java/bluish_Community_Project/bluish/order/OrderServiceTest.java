package bluish_Community_Project.bluish.order;

import bluish_Community_Project.bluish.AppConfig;
import bluish_Community_Project.bluish.member.Grade;
import bluish_Community_Project.bluish.member.Member;
import bluish_Community_Project.bluish.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        memberService = applicationContext.getBean("memberService", MemberService.class);
        orderService = applicationContext.getBean("orderService", OrderService.class);
    }

    @Test
    public void createOrder() throws Exception{
        //given
        Long memberId = 1L;
        Member memberA = new Member(memberId, "memberA", Grade.RESIDENT);
        memberService.join(memberA);
        //when
        Order order = orderService.createOrder(memberId, "침대", 120000);
        //then
        Assertions.assertThat(memberA.getId()).isEqualTo(order.getMemberId());
        System.out.println("order = " + order);
    }
}
