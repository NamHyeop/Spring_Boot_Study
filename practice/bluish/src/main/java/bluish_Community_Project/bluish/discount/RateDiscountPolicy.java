package bluish_Community_Project.bluish.discount;

import bluish_Community_Project.bluish.member.Grade;
import bluish_Community_Project.bluish.member.Member;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercnet = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.RESIDENT) {
            return price * discountPercnet / 100;
        } else {
            return 0;
        }
    }
}
