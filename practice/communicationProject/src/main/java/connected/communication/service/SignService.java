package connected.communication.service;

import connected.communication.dto.MemberEmailAlreadyExistsException;
import connected.communication.dto.MemberNicknameAlreadyExistsException;
import connected.communication.dto.sign.SignInResponse;
import connected.communication.dto.sign.SignUpRequest;
import connected.communication.dto.sign.SignInRequest;
import connected.communication.entity.member.Member;
import connected.communication.entity.member.RoleType;
import connected.communication.exception.LoginFailureException;
import connected.communication.exception.RoleNotFoundException;
import connected.communication.repository.member.MemberRepository;
import connected.communication.repository.role.RoleRepository;
import connected.communication.service.sign.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SignService {
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    /**
     * 회우 등록 로직
     */
    @Transactional
    public void signUp(SignUpRequest req){
        validateSignupInfo(req);
        memberRepository.save(SignUpRequest.toEntity(req
                ,roleRepository.findByRoleType(RoleType.ROLE_NORMAL).orElseThrow(RoleNotFoundException::new)
                ,passwordEncoder));
    }

    /**
     * 회원 로그인 로직
     */
    public SignInResponse signIn(SignInRequest req){
        Member member = memberRepository.findByEmail(req.getEmail()).orElseThrow(LoginFailureException::new);
        validatePassword(req, member);
        String subject = createSubject(member);
        String accessToken = tokenService.createAccessToken(subject);
        String refreshToken = tokenService.createRefreshToken(subject);
        return new SignInResponse(accessToken, refreshToken);
    }

    private String createSubject(Member member) {
        return String.valueOf(member.getId());
    }

    private void validatePassword(SignInRequest req, Member member) {
        if(!passwordEncoder.matches(req.getPassword(), member.getPassword()))
            throw new LoginFailureException();
    }

    private void validateSignupInfo(SignUpRequest req) {
        /*
         * 메일이 이미 존재한다면 예외처리
         */
        if(memberRepository.existsByEmail(req.getEmail()))
            throw new MemberEmailAlreadyExistsException(req.getEmail());
        /*
         * 닉네임이 이미 존재한다면 예외처리
         */
        if (memberRepository.existsByNickname(req.getNickname()))
            throw new MemberNicknameAlreadyExistsException(req.getNickname());
    }
}
