package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//Lombok의 기능중 하나 생성자를 자동으로 만들어줌
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    //잘못된 OCP, DI 규칙을 어긋난 코드
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private DiscountPolicy discountPolicy;

    //생성자를 이용한 OCP, DI 규칙을 지킨 코드

    //1.필드 주입을 사용한 의존관계 주입방법은 앞에 @Autowired를 붙치면 된다. 좋지 않은 방법 - 자바 코드를 사용한 로컬 테스트 불가능
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

 /*//2.생성자 주입을 사용한 의존관계 주입방법.
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository){
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }
    @Autowired
    public void setDisCountPolicy(DiscountPolicy discountPolicy){
        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }*/

    /**
     * 3.메소드를 사용한 주입방법.
     * 잘 사용 안함. 생성자랑 비슷하다.
    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

     //@RequiredArgsContrller 어노테이션을 사용해서 주석처리 해줘야함
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
