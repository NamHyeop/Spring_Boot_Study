package connected.communication.service.sign;

import connected.communication.handler.JwtHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TokenServiceTest {
    @InjectMocks TokenService tokenService;
    @Mock
    JwtHandler jwtHandler;

    @BeforeEach
    void beforeEach(){
        ReflectionTestUtils.setField(tokenService, "accessTokenMaxAgeSeconds", 10L);
        ReflectionTestUtils.setField(tokenService, "refreshTokenMaxAgeSeconds", 10L);
        ReflectionTestUtils.setField(tokenService, "accessKey", "accessKey");
        ReflectionTestUtils.setField(tokenService, "refreshKey", "refreshKey");
    }

    @Test
    public void creatAccessTokenTest() throws Exception{
        //given
        /**
         * jwthandler의 creattoken 메소드의 매개변수를 미리 설정하고 return값까지 미리 설정
         */
        given(jwtHandler.createToken(anyString(), anyString(), anyLong())).willReturn("access");
        //when
        String token = tokenService.createAccessToken("subject");
        //then
        /**
         * verify를 수행하여 가짜 객체 검증
         */
        assertThat(token).isEqualTo("access");
        verify(jwtHandler).createToken(anyString(), anyString(), anyLong());
    }

    @Test
    public void creatRefreshTokenTest() throws Exception{
        //given
        /**
         * jwthandler의 createtoken 메소드의 매개변수를 미리 설정하고 return값까지 미리 설정
         */
        given(jwtHandler.createToken(anyString(), anyString(), anyLong())).willReturn("refresh");
        //when
        String token = tokenService.createRefreshToken("subject");
        //then
        /**
         * verify를 수행하여 가짜 객체 검증
         */
        assertThat(token).isEqualTo("refresh");
        verify(jwtHandler).createToken(anyString(), anyString(), anyLong());
    }
}