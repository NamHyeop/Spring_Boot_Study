package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    /**
     * @return 값이 null이면 로그인 실패
     */

    public Member login(String loginId, String password) {
        /**
         *자바 if로 구현
         *Optional 객체에 get() 메소드를 사용하면 Optional로 감싸진 부분을 없애고 순순 객체만 받을 수 있음. 아래줄이 예시
         */
        //        Optional<Member> findMemberOptional = memberRepository.findByLoginId(loginId);
        //        Member member = findMemberOptional.get();
        //        if(member.getPassword().equals(password)){
        //            return member;
        //        }
        //        else
        //            return null;

        //람다로 구현. 찾는게 있으면 반환하고 아니면 null을 반환
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
