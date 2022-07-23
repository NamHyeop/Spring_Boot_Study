package connected.communication.service;

import com.fasterxml.jackson.databind.annotation.JsonTypeResolver;
import connected.communication.dto.MemberEmailAlreadyExistsException;
import connected.communication.dto.MemberNicknameAlreadyExistsException;
import connected.communication.dto.sign.RefreshTokenResponse;
import connected.communication.dto.sign.SignInRequest;
import connected.communication.dto.sign.SignInResponse;
import connected.communication.dto.sign.SignUpRequest;
import connected.communication.entity.member.Member;
import connected.communication.entity.member.Role;
import connected.communication.entity.member.RoleType;
import connected.communication.exception.AuthenticationEntryPointException;
import connected.communication.exception.LoginFailureException;
import connected.communication.exception.RoleNotFoundException;
import connected.communication.repository.member.MemberRepository;
import connected.communication.repository.role.RoleRepository;
import connected.communication.service.sign.TokenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static java.util.Collections.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SignServiceTest {
    @InjectMocks SignService signService;
    @Mock MemberRepository memberRepository;
    @Mock RoleRepository roleRepository;
    @Mock PasswordEncoder passwordEncoder;
    @Mock TokenService tokenService;

    @Test
    public void signUpTest() throws Exception{
        //given
        SignUpRequest req = createSignUprequest();
        given(roleRepository.findByRoleType(RoleType.ROLE_NORMAL)).willReturn(Optional.of(new Role(RoleType.ROLE_NORMAL)));
        //when
        signService.signUp(req);
        //then
        verify(passwordEncoder).encode(req.getPassword());
        verify(memberRepository).save(any());
    }

    @Test
    void validateSignUpByDuplicateEmailTest() throws Exception{
        //given
        given(memberRepository.existsByEmail(anyString())).willReturn(true);
        //when,then
        assertThatThrownBy(() -> signService.signUp(createSignUprequest()))
                .isInstanceOf(MemberEmailAlreadyExistsException.class);
    }

    @Test
    public void validateSignUpByDuplicateNickNameTest() throws Exception{
        //given
        given(memberRepository.existsByNickname(anyString())).willReturn(true);
        //when
        //then
        assertThatThrownBy(() -> signService.signUp(createSignUprequest())).isInstanceOf(MemberNicknameAlreadyExistsException.class);
    }
    
    @Test
    public void signUpRoleNotFoundTest() throws Exception{
        //given
        given(roleRepository.findByRoleType(RoleType.ROLE_NORMAL)).willReturn(Optional.empty());
        //when
        //then
        assertThatThrownBy(() -> signService.signUp(createSignUprequest())).isInstanceOf(RoleNotFoundException.class);
    }
    
    @Test
    public void signInTest() throws Exception{
        //given
        given(memberRepository.findByEmail(any())).willReturn(Optional.of(createMember()));
        given(passwordEncoder.matches(anyString(), anyString())).willReturn(true);
        given(tokenService.createAccessToken(anyString())).willReturn("access");
        given(tokenService.createRefreshToken(anyString())).willReturn("refresh");
        //when
        SignInResponse signInResponse = signService.signIn(new SignInRequest("email", "password"));
        //then
        assertThat(signInResponse.getAccessToken()).isEqualTo("access");
        assertThat(signInResponse.getRefreshToken()).isEqualTo("refresh");
    }   
    
    @Test
    public void signInExceptionByNoneMemberTest() throws Exception{
        //given
        given(memberRepository.findByEmail(any())).willReturn(Optional.empty());

        //when
        //then
        assertThatThrownBy(() -> signService.signIn(new SignInRequest("email", "password"))).isInstanceOf(LoginFailureException.class);
    }

    @Test
    void signInExceptionByNoneMe1mberTest() {
        // given
        given(memberRepository.findByEmail(any())).willReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> signService.signIn(new SignInRequest("email", "password"))).isInstanceOf(LoginFailureException.class);
    }

    @Test
    public void signInExceptionByInvalidPasswordTest() throws Exception{
        //given
        given(memberRepository.findByEmail(any())).willReturn(Optional.of(createMember()));
        given(passwordEncoder.matches(anyString(),anyString())).willReturn(false);
        //when
        //then
        assertThatThrownBy(() -> signService.signIn(new SignInRequest("email","password"))).isInstanceOf(LoginFailureException.class);
    }

    @Test
    public void refreshTokenTest() throws Exception{
        //given
        String refreshToken="refreshToken";
        String subject = "subject";
        String accessToken="accessToken";
        given(tokenService.validateRefreshToken(refreshToken)).willReturn(true);
        given(tokenService.extractRefreshTokenSubject(refreshToken)).willReturn(subject);
        given(tokenService.createAccessToken(subject)).willReturn(accessToken);
        //when
        RefreshTokenResponse res = signService.refreshToken(refreshToken);
        //then
        assertThat(res.getAccessToken()).isEqualTo(accessToken);
    }

    @Test
    void refreshTokenExceptionByInvalidTokenTest(){
        //given
        String refreshToken="refreshToken";
        given(tokenService.validateRefreshToken(refreshToken)).willReturn(false);
        //when//then
        assertThatThrownBy(()->signService.refreshToken(refreshToken)).isInstanceOf(AuthenticationEntryPointException.class);
    }

    private SignUpRequest createSignUprequest() {
        return new SignUpRequest("email", "password", "username", "nickname");
    }
    
    private Member createMember(){
        return new Member("email", "password", "username", "nickname", emptyList());
    }
}
