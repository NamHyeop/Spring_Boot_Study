//package bluish_Community_Project.bluish.discount;
//
//import bluish_Community_Project.bluish.member.Grade;
//import bluish_Community_Project.bluish.member.Member;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//public class PassiveSucessTest {
//    DiscountPolicy discountPolicy;
//
//    @BeforeEach
//    public void beforeEach(){
//        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(DiscountPolicyConfig.class);
//        discountPolicy = ac.getBean("fixDiscountPolicy", FixDiscountPolicy.class);
//    }
//    @Test
//    public void passiveChangedConfigurationTest() throws Exception {
//        Member memberA = new Member(1L, "MemberA", Grade.BASIC);
//        int discountPrice = discountPolicy.discount(memberA, 10000);
//        Assertions.assertThat(discountPrice).isEqualTo(1000);
//    }
//
//    @Test
//    public void findBeanRate() throws Exception{
//        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(DiscountPolicyConfig.class);
//        DiscountPolicy discountPolicy1 = ac.getBean("fixDiscountPolicy", FixDiscountPolicy.class);
//        DiscountPolicy discountPolicy2 = ac.getBean("fixDiscountPolicy", FixDiscountPolicy.class);
//        System.out.println("discountPolicy2 = " + discountPolicy2);
//        System.out.println("discountPolicy2 = " + discountPolicy1);
//        Assertions.assertThat(discountPolicy1).isInstanceOf(FixDiscountPolicy.class);
//        Assertions.assertThat(discountPolicy1).isSameAs(discountPolicy2);
//    }
//}
