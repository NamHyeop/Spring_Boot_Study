package connected.communication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().disable()//formlogin 인증방법 비활성화
                .httpBasic().disable()//httpBasic 인증방법 비활성화(특정 리소스에 접근시에만 username과 password를 요구한다)
                .csrf().disable() //csrf 관련 설정을 비활성화한다.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //현재는 STATELESS로 상태유지를 안하게 설정
                .and()
                .authorizeRequests()
//                .antMatchers("/login","/signUp","/").permitAll() //로그인, 회원가입, 메인페이지는 인증없이 접근 허용
                .antMatchers("/**").permitAll(); //로그인, 회원가입, 메인페이지는 인증없이 접근 허용
//               .anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
