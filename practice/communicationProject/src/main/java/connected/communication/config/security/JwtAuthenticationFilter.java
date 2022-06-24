package connected.communication.config.security;

import connected.communication.config.ConfigSecurity.CustomAuthenticationEntryPoint;
import connected.communication.config.ConfigSecurity.CustomUserDetails;
import connected.communication.config.ConfigSecurity.CustomUserDetailsService;
import connected.communication.service.sign.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final TokenService tokenService;
    private final CustomUserDetailsService userDetailsService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /**
         * 요청으로 전달받은 Authorixation 헤더에서 토큰 값을 꺼내온다. 이후 토근이 유효하다면
         * SpringSecurity가 관리해주는 컨텍스트에 사용자 정보(CustomAuthenticationToken)을 등록한다
         */
        String token = extractToken(request);
        if(validateAccessToken(token)){
            setAccessAuthentication("access", token);
        }else if(tokenService.validateRefreshToken(token));
        chain.doFilter(request,response);
    }

    private String extractToken(ServletRequest request){
        return ((HttpServletRequest)request).getHeader("Authorization");
    }

    private boolean validateAccessToken(String token){
        return token != null && tokenService.validateAccessToken(token);
    }

    private boolean validateRefreshToken(String token){
        return token != null && tokenService.validateRefreshToken(token);
    }

    private void setAccessAuthentication(String type, String token){
        String userId = tokenService.extractAccessTokenSubject(token);
        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(userId);
        SecurityContextHolder.getContext().setAuthentication(new CustomAuthenticationToken(userDetails.getAuthorities(), type, userDetails));
    }

    private void setRefreshAuthentication(String type, String token){
        String userId = tokenService.extractRefreshTokenSubject(token);
        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(userId);
        SecurityContextHolder.getContext().setAuthentication(new CustomAuthenticationToken(userDetails.getAuthorities(), type, userDetails));
    }
}
