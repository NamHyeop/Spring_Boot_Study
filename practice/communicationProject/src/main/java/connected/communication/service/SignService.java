package connected.communication.service;

import connected.communication.config.token.TokenHelper;
import connected.communication.dto.MemberEmailAlreadyExistsException;
import connected.communication.dto.MemberNicknameAlreadyExistsException;
import connected.communication.dto.sign.RefreshTokenResponse;
import connected.communication.dto.sign.SignInRequest;
import connected.communication.dto.sign.SignInResponse;
import connected.communication.dto.sign.SignUpRequest;
import connected.communication.entity.member.Member;
import connected.communication.entity.member.RoleType;
import connected.communication.exception.AuthenticationEntryPointException;
import connected.communication.exception.LoginFailureException;
import connected.communication.exception.RoleNotFoundException;
import connected.communication.repository.member.MemberRepository;
import connected.communication.repository.role.RoleRepository;
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
    private final TokenHelper accessTokenHelper;
    private final TokenHelper refreshTokenHelper;

    /**
     * 회원 등록 로직
     */
    @Transactional
    public void signUp(SignUpRequest req){
        validateSignUpInfo(req);
        memberRepository.save(SignUpRequest.toEntity(req
                ,roleRepository.findByRoleType(RoleType.ROLE_NORMAL).orElseThrow(RoleNotFoundException::new)
                ,passwordEncoder));
    }

    /**
     * 회원 로그인 로직
     */
    @Transactional(readOnly = true)
    public SignInResponse signIn(SignInRequest req){
        Member member = memberRepository.findByEmail(req.getEmail()).orElseThrow(LoginFailureException::new);
        validatePassword(req, member);
        String subject = createSubject(member);
        String accessToken = accessTokenHelper.createToken(subject);
        String refreshToken = refreshTokenHelper.createToken(subject);
        return new SignInResponse(accessToken, refreshToken);
    }

    public RefreshTokenResponse refreshToken(String rToken){
        validateRefreshToken(rToken);
        String subject = refreshTokenHelper.extractSubject(rToken);
        String accessToken = accessTokenHelper.createToken(subject);
        return new RefreshTokenResponse(accessToken);
    }

    private void validateRefreshToken(String rToken) {
        if(!refreshTokenHelper.validate(rToken)){
            throw new AuthenticationEntryPointException();
        }
    }

    private void validateSignUpInfo(SignUpRequest req) {
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

    private String createSubject(Member member) {
        return String.valueOf(member.getId());
    }

    private void validatePassword(SignInRequest req, Member member) {
        if(!passwordEncoder.matches(req.getPassword(), member.getPassword()))
            throw new LoginFailureException();
    }
}
