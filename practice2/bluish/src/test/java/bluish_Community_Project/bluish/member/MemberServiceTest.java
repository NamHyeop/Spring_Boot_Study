package bluish_Community_Project.bluish.member;

import bluish_Community_Project.bluish.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appconfig = new AppConfig();
        memberService = appconfig.memberService();
    }

    /**
     * 회원 가입 테스트 - 거주자 등급의 회원이 가입이 가능한가
     */
    @Test
    void join() {
        //given
        Member memberA = new Member(1L, "memberA", Grade.RESIDENT);

        //when
        memberService.join(memberA);
        Member member = memberService.findMember(1L);

        //then
        Assertions.assertThat(memberA).isEqualTo(member);
    }
}
