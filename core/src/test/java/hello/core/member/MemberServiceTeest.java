package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTeest {
    MemberService memberService = new MemberServiceImpl(); // 현재 추상화에도 의존하고 구체화에도 의존하는 상황이라 안좋은 구현 방법
    @Test
    void join(){
        //gitven
        Member member = new Member(1L, "memberA", Grade.VIP);
        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember); //member와 findMember로 찾은 member가 같은지
    }

}
