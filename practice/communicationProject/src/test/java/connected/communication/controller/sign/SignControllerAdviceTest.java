package connected.communication.controller.sign;

import com.fasterxml.jackson.databind.ObjectMapper;
import connected.communication.advice.ExceptionAdvice;
import connected.communication.dto.MemberEmailAlreadyExistsException;
import connected.communication.dto.sign.SignInRequest;
import connected.communication.dto.sign.SignUpRequest;
import connected.communication.exception.AuthenticationEntryPointException;
import connected.communication.exception.LoginFailureException;
import connected.communication.exception.RoleNotFoundException;
import connected.communication.service.SignService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class SignControllerAdviceTest {
    @InjectMocks SignController signController;
    @Mock
    SignService signService;
    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * API마나 호출하면 데던 SignControllTest와는 다르게 ControolerAdvice를 테스트하깅위해
     * MockMvc를 빌드하는 과정에서 setControllerAdvice로 등록해줘야한다.
     */
    @BeforeEach
    void beforeEach(){
        mockMvc = MockMvcBuilders.standaloneSetup(signController).setControllerAdvice(new ExceptionAdvice()).build();
    }

    /**
     * 가짜 객체로 만들어둔 SignService.siginIn을 호출 할 때 LoginFailureException.class를 Throw 하도록 설정한다.
     * 반환형이 있을 경우 given이 사용 가능하지만 반환형이 없는 경우에는 doThrow를 사용해야한다.
     * signInMethodArgumentNotValidExetpionTest에서 확인해보자
     */
    @Test
    public void signInLoginFailureExceptionTest() throws Exception{
        //given
        SignInRequest req = new SignInRequest("email@google.com", "12345a!");
        given(signService.signIn(any())).willThrow(LoginFailureException.class);
        //when
        //then
        mockMvc.perform(
                post("/api/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                        .andExpect(status().isUnauthorized());
    }

    /**
     * doThrow를 활용하여 오류를 던져주게 섲렁하여 테스트를 진행한다.
     */
    @Test
    public void signUpMemberEmailAlreadyExistsExceptionTest() throws Exception{
        //given
        SignUpRequest req = new SignUpRequest("email@google.com", "123456a!", "username", "nickname");
        doThrow(MemberEmailAlreadyExistsException.class).when(signService).signUp(any());
        //when
        //then
        mockMvc.perform(
                        post("/api/sign-up")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isConflict());
    }


    @Test
    public void signInMethodArgumentNotValidExceptionTest() throws Exception{
        //given
        SignInRequest req = new SignInRequest("email", "1234567");
        //when
        //then
        mockMvc.perform(
                        post("/api/sign-in")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpRoleNotFoundExceptionTest() throws Exception {
        // given
        SignUpRequest req = new SignUpRequest("email@email.com", "123456a!", "username", "nickname");
        doThrow(RoleNotFoundException.class).when(signService).signUp(any());

        // when, then
        mockMvc.perform(
                        post("/api/sign-up")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void signUpMethodArgumentNotValidExceptionTest() throws Exception{
        //given
        SignUpRequest req = new SignUpRequest("", "", "", "");
        //when
        //then
        mockMvc.perform(
                post("/api/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void refreshTokenAuthenticationEntryPointException() throws Exception { // 1
        // given
        given(signService.refreshToken(anyString())).willThrow(AuthenticationEntryPointException.class);

        // when, then
        mockMvc.perform(
                        post("/api/refresh-token")
                                .header("Authorization", "refreshToken"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.code").value(-1001));
    }

    @Test
    void refreshTokenMissingRequestHeaderException() throws Exception { // 2
        // given, when, then
        mockMvc.perform(
                        post("/api/refresh-token"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(-1009));
    }
}
