package com.example.userservice.security;

import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * packageName    : com.example.userservice.security
 * fileName       : WebSecurity
 * author         : namhyeop
 * date           : 2022/09/12
 * description    :
 * 인증은 되었다는 가정하에 예제를 진행한다
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/12        namhyeop       최초 생성
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Environment env;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
//        http.authorizeRequests().antMatchers("/users/**").permitAll();
        //Actuator test - 관련 요청 전부 수락
        http.authorizeRequests().antMatchers("/actuator/**").permitAll();
        http.authorizeRequests().antMatchers("/**")
                        .hasIpAddress("172.20.10.7")
                        .and()
                        .addFilter(getAuthenticationFilter());
        http.headers().frameOptions().disable();
    }

    //getAuthenticationFilter에서 AuthenticatFilter를 생성한 뒤 AuthenticationManger를 등록한다. 이후 등록된 AuthenticationFilter가 동작하면서 검증 로직을 수행한다.
    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager(), userService, env);
        authenticationFilter.setAuthenticationManager(authenticationManager());

        return authenticationFilter;
    }

    //configure는 인증 과정을 수행하기 위해 존재한다.
    //아래와 같은 쿼리문이 진행된다고 생각하면 된다.
    //select pwd from users where email=?
    //근데 문제가 있는데 아래와 같이 DB에는 이미 암호화된 PWD가 들어가 있기 때문에 로그인을 시도하는 PWD도 암호화해줘야 한다, 아래의 BCrypt는 암화화를 수행하는 구역이다.
    //db_pwd(encrypted) == input_pwd(encrypted)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }
}
