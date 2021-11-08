package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTeest {
    /**
     * 현재 추상화에도 의존하고 구체화에도 의존하는 상황이라 안좋은 구현 방법
     * MemberService memberService = new MemberServiceImpl();
     */

    MemberService memberService;

    @BeforeEach // 테스트 실행전 반드시 실행되어야 하는 것
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        //gitven
        Member member = new Member(1L, "memberA", Grade.VIP);
        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember); //member와 findMember로 찾은 member가 같은지
    }

}
