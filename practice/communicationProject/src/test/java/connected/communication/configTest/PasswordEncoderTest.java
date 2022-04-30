package connected.communication.configTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PasswordEncoderTest {
    @Autowired PasswordEncoder passwordEncoder;

    //encoder 형식에 맞게 암호화 되었는지 확인하는 테스트
    @Test
    public void passwordEncryptionTest() throws Exception{
        //given
        String password ="namhyeop짱짱1234";
        //when
        String encryptionPw = passwordEncoder.encode(password);
        //then
        assertThat(encryptionPw).startsWith("{");
        assertThat(encryptionPw).contains("{bcrypt}");
        assertThat(encryptionPw).isNotEqualTo(password);
    }

    //암호화 할 때마다 다른 결과가 나오는지 확인하는 테스트
    @Test
    public void passwordRandomEncryptionTest() throws Exception{
        //given
        String password ="namhyeop짱짱1234";
        //when
        String encryptionPw1 = passwordEncoder.encode(password);
        String encryptionPw2 = passwordEncoder.encode(password);
        //then
        assertThat(encryptionPw1).isNotEqualTo(encryptionPw2);
    }

    //pw를 암호화된 PW와 매치가 되는지 확인하는 테스트(매치가 되는 원리는 모르겠음 추후 공부 필요)
    @Test
    public void pwEqualtoEncryptionPw() throws Exception{
        //given
        String password ="namhyeop짱짱1234";
        //when
        String encodePassword = passwordEncoder.encode(password);
        //then
        assertThat(passwordEncoder.matches(password, encodePassword)).isTrue();
    }
}
