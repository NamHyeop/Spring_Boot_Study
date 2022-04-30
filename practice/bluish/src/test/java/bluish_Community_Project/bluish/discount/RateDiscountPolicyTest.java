package bluish_Community_Project.bluish.discount;

import bluish_Community_Project.bluish.AppConfig;
import bluish_Community_Project.bluish.member.Grade;
import bluish_Community_Project.bluish.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class RateDiscountPolicyTest {
    DiscountPolicy discountPolicy;

    @BeforeEach
    public void beforeEach(){
        AppConfig appconfig = new AppConfig();
        discountPolicy = appconfig.discountPolicy();
    }

    @Test
    @DisplayName("청년주택 실 거주자용 쿠폰 할인 테스트")
    void resident(){
        Member member = new Member(1L, "memberA", Grade.RESIDENT);
        int discount = discountPolicy.discount(member, 120000);
        Assertions.assertThat(discount).isEqualTo(12000);
    }

    @Test
    @DisplayName("청년주택 비 거주자용 쿠폰 할인 테스트")
    void basic(){
        Member member = new Member(1L, "memberA", Grade.BASIC);
        int discount = discountPolicy.discount(member, 120000);
        Assertions.assertThat(discount).isEqualTo(0);
    }
}