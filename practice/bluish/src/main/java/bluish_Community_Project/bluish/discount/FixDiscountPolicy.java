package bluish_Community_Project.bluish.discount;

import bluish_Community_Project.bluish.annotation.MainDiscountPolicy;
import bluish_Community_Project.bluish.member.Grade;
import bluish_Community_Project.bluish.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * 기본 할인 정책 전략
 */
@MainDiscountPolicy
@Primary
@Component
public class FixDiscountPolicy implements DiscountPolicy {
    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.BASIC) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
