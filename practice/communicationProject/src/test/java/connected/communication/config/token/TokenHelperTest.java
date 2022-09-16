package connected.communication.config.token;

import connected.communication.handler.JwtHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * packageName    : connected.communication.config.token
 * fileName       : TokenHelperTest
 * author         : namhyeop
 * date           : 2022/08/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/30        namhyeop       최초 생성
 */
@ExtendWith(MockitoExtension.class)
class TokenHelperTest {
    TokenHelper tokenHelper;
    @Mock JwtHandler jwtHandler;

    @BeforeEach
    void beforeEach(){
        tokenHelper = new TokenHelper(jwtHandler,"key", 1000L);
    }

    @Test
    public void createTokenTest() throws Exception{
        //given
        given(jwtHandler.createToken(anyString(), anyString(), anyLong())).willReturn("token");
        //when
        String createdToken = tokenHelper.createToken("subject");
        //then
        assertThat(createdToken).isEqualTo("token");
        verify(jwtHandler).createToken(anyString(), anyString(), anyLong());
    }

    @Test
    public void invalidateTest() throws Exception{
        //given
        given(jwtHandler.validate(anyString(),anyString())).willReturn(false);
        //when
        boolean result = tokenHelper.validate("token");
        //then
        assertThat(result).isFalse();
    }

    @Test
    public void extractSubjectTest() throws Exception{
        //given
        given(jwtHandler.extractSubject(anyString(),anyString())).willReturn("subject");
        //when
        String subject = tokenHelper.extractSubject("token");
        //then
        assertThat(subject).isEqualTo(subject);
    }
}