package connected.communication.config;

import connected.communication.config.ConfigSecurity.CustomAccessDeniedHandler;
import connected.communication.config.ConfigSecurity.CustomAuthenticationEntryPoint;
import connected.communication.config.ConfigSecurity.CustomUserDetailsService;
import connected.communication.config.security.JwtAuthenticationFilter;
import connected.communication.service.sign.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 */
@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 토큰을 통해 사용자를 인증하기 때문에 JwtAuthenticationFilter에 의존해야한다.
     */
    private final TokenService tokenService;
    /**
     * 토큰을 통해 사용자를 인증하기 때문에 JwtAuthenticationFilter에 의존해야한다.
     * 토큰에 저장된 subject(현재 프로젝트 기준 id)로 사용자의 정보를 조회하는데 사용한다.
     */
    private final CustomUserDetailsService userDetailsService;

    /**
     * SpringSecurity를 무시할 URL을 지정해준다.
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/exception/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /**
                 * 각 메소드별 URL에 따른 접근 정책을 설정해준다.
                 * 1.로그인과 회원가입과 연관된 POST, GET 요청 API는 누구나 접근할 수 있도록 설정
                 * 2.사용자 삭제 API는 access()로 정책을 설정
                 */
                .httpBasic().disable()
                .formLogin().disable()//formlogin 인증방법 비활성화
                .httpBasic().disable()//httpBasic 인증방법 비활성화(특정 리소스에 접근시에만 username과 password를 요구한다)
                .csrf().disable() //csrf 관련 설정을 비활성화한다.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //현재는 STATELESS로 상태유지를 안하게 설정
                .and()
                    .authorizeRequests()
                        .antMatchers(HttpMethod.POST, "/api/sign-in","/api/sign-up").permitAll()
                        .antMatchers(HttpMethod.GET, "/api/**").permitAll()
                /**
                 * ===access()정책 문법===
                 * "@<빈이름>.<메소드명>(<인자>, #id로 하면 URL에 지정한 {id}가 매핑되어 인자로 들어간다)
                 * 스프링 빈으로 등록한 MemberGuard.check()를 호출하고 반환값이 True라면 지정된 요청의 접근을 수락한다.
                 * 프로젝트 기준 접근자 이외의 요청은 관리자의 권한이 필요하도록 설정한다.
                 */
                        .antMatchers(HttpMethod.DELETE, "/api/members/{id}/**").access("@MemberGuard.check(#id)")
                        .anyRequest().hasAnyRole("ADMIN")
                .and()
                /**
                 * 인증된 사용자가 권한 부족등 사유로 접근 거부시 작동할 핸들러 지정
                 */
                    .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                /**
                 * 인증되지 않은 사용자가 권한 부족의 사유로 접근 거부시 작동할 핸들러 지정
                 */
                    .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                /**
                 * 토큰으로 사용자를 인증하기 위해 직접 정의한 JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter의 이전 위치에 등록
                 * JwtAuthenticationFilter는 TokenService와 CustomUserDetailService를 주입받는다.
                 */
                    .addFilterBefore(new JwtAuthenticationFilter(tokenService, userDetailsService), UsernamePasswordAuthenticationFilter.class);
    }
}
