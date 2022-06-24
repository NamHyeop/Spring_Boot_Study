package connected.communication.config.ConfigSecurity;

import connected.communication.entity.member.Member;
import connected.communication.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        /**
         * 유저를 아이디를 기준으로 유저를 찾는다. 없을 경우 커스텀 유저(방문 유저)용 member를 생산한다.
         */
        Member member = memberRepository.findById(Long.valueOf(userId))
                .orElseGet(() -> new Member(null, null, null, null, List.of()));
        /**
         * 가입된 유저 정보 기반으로 권한을 부여한다. CustomUserDetails 권한등급을 GrandedAuthrity 인터페이스 타입으로 받게 된다.
         * ㅇGrandtedAuthrity의 구현체인 SimpleCrantedAuthority를 사용하였다.
         */
        return new CustomUserDetails(
                String.valueOf(member.getId()),
                member.getRoles().stream().map(memberRole -> memberRole.getRole())
                        .map(role -> role.getRoleType())
                        .map(roleType -> roleType.toString())
                        .map(SimpleGrantedAuthority::new).collect(Collectors.toSet())
        );
    }
}
