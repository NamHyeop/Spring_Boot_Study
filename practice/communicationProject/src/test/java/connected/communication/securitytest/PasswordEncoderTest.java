package connected.communication.securitytest;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordEncoderTest {
    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    @Test
    void encodeWithBcryptTest() {
        // given
        String password = "password";
        // when
        String encodedPassword = passwordEncoder.encode(password);
        // then
        assertThat(encodedPassword).contains("{bcrypt}");
    }

    @Test
    void matchTest() {
        // given
        String password = "password";
        String encodedPassword = passwordEncoder.encode(password);
        System.out.println("encodedPassword = " + encodedPassword +" " + System.identityHashCode(password));
        System.out.println("password = " + password + " " + System.identityHashCode(encodedPassword) +" " +encodedPassword.hashCode());
        // when
        /**
         *         matches()는 내부에서 평문 패스워드와 암호화된 패스워드가 서로 대칭되는지에 대한 알고리즘을 구현
         */
        boolean isMatch = passwordEncoder.matches(password, encodedPassword);
        // then
        assertThat(isMatch).isTrue();
    }
}
