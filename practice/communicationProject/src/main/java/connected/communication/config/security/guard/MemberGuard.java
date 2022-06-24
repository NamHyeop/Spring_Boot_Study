package connected.communication.config.security.guard;

import connected.communication.entity.member.RoleType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class MemberGuard {
    private final AuthHelper authHelper;

    /**
     * 사용자가 인증되었는지, 엑세스 토큰을 통한 요청인지, 자원 접근 권한(관리자권한, 자신의 로그인 아이디)을 가지고 있는지 검사
     */
    public boolean check(Long id) {
        return authHelper.isAuthenticated() && authHelper.isAccessTokenType() && hasAuthority(id);
    }

    private boolean hasAuthority(Long id) {
        Long memberId = authHelper.extractMemberId();
        Set<RoleType> memberRoles = authHelper.extractMemberRoles();
        return id.equals(memberId) || memberRoles.contains(RoleType.ROLE_ADMIN);
    }
}
