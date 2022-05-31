package connected.communication.handler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtHandlerTest {
    @Autowired
    JwtHandler jwtHandler;

    @Test
    public void createTokenTest() throws Exception{
        //given
        //when
        String encodedKey = Base64.getEncoder().encodeToString("myKey".getBytes());
        String token = createToken(encodedKey, "subject", 60L);
        //then
        assertThat(token).contains("bluish");
    }

    @Test
    public void extractSubjectTest() throws Exception{
        //given
        String encodedKey = Base64.getEncoder().encodeToString("myKey".getBytes());
        String subject = "subject";
        String token = createToken(encodedKey, subject, 60L);
        //when
        String extractedSubject = jwtHandler.extractSubject(encodedKey, token);
        //then
        assertThat(extractedSubject).isEqualTo(subject);
    }
    
    @Test
    public void invalidateByInvalidKeyTest() throws Exception{
        //given
        String encodedKey = Base64.getEncoder().encodeToString("myKey".getBytes());
        String token = createToken(encodedKey, "subject", 60L);
        //when
        boolean isValid = jwtHandler.validate("invalid", token);
        //then
        assertThat(isValid).isFalse();
    }

    /**
     * 토큰의 유효 기간을 0초로 설정하여 시작하자마자 만료되게 설정하는 유효성 검사 테스트
     */
    @Test
    public void invalidDateByExpiredTokenTest() throws Exception{
        //given
        String encodedKey = Base64.getEncoder().encodeToString("myKey".getBytes());
        String token = createToken(encodedKey, "subject", 0L);
        //when
        boolean isValid = jwtHandler.validate(encodedKey, token);
        //then
        assertThat(isValid).isFalse();
    }

    private String createToken(String encodedKey, String subject, long maxAgeSeconds){
        return jwtHandler.createToken(encodedKey, subject, maxAgeSeconds);
    }
}