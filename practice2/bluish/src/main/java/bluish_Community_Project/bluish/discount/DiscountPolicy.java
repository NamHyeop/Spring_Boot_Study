package bluish_Community_Project.bluish.discount;

import bluish_Community_Project.bluish.member.Member;

public interface DiscountPolicy {
    /**
     *
     * @param member 할인 맴버
     * @param price 상품 가격
     * @return
     */
    int discount(Member member, int price);
}
