package connected.communication.controller.sign;

import com.fasterxml.jackson.databind.ObjectMapper;
import connected.communication.dto.sign.SignInRequest;
import connected.communication.dto.sign.SignInResponse;
import connected.communication.dto.sign.SignUpRequest;
import connected.communication.service.SignService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class SignControllerTest {
    @InjectMocks SignController signController;
    @Mock SignService signService;
    MockMvc mockMvc;
    /**
     *     객제를 Json 문자열로 바꿔주기 위해 선언
     */
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void beforeEach(){
        mockMvc = MockMvcBuilders.standaloneSetup(signController).build();
    }

    @Test
    public void signUpTest() throws Exception{
        //given
        SignUpRequest req = new SignUpRequest("emaill@goolge.com", "!ASDasd123456", "namhyeop", "iamreadyToHwan");
        //when
        //then
        mockMvc.perform(
                post("/api/sign-up")
                    .contentType(MediaType.APPLICATION_JSON)
                        /**
                         * ObjectMapper.writeaValueAsString을 활요하여 객체를 Json 문자열로 변환함.
                         * content에 이를 넣어주면 요청 바디에 담기게 된다.
                         * contentType으로 APLICATION_JSON 설정까지 해줘야 한다.(www.타입 X)
                         */
                    .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated());
        verify(signService).signUp(req);
    }

    @Test
    public void signInTest() throws Exception{
        //given
        SignInRequest req = new SignInRequest("emaill@google.com", "!ASDasd123456");
        given(signService.signIn(req)).willReturn(new SignInResponse("access", "refresh"));
        //when //then
        mockMvc.perform(
                        post("/api/sign-in")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                /**
                 * SignService.signIn에서 반환 값이 JSON 반환 데이터에 포함되어있는지 확인
                 */
                .andExpect(jsonPath("$.result.data.accessToken").value("access"))
                .andExpect(jsonPath("$.result.data.refreshToken").value("refresh"));
        verify(signService).signIn(req);
    }

    @Test
    void ignoreNullValueInJsonResponseTest() throws Exception{
        //given
        SignUpRequest req = new SignUpRequest("email@google.com", "asd123456", "username", "nickName");
        //when, then

        mockMvc.perform(
                post("/api/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.result").doesNotExist());
    }
}