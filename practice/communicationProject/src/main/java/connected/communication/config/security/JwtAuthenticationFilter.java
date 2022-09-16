package connected.communication.config.security;

import connected.communication.config.ConfigSecurity.CustomUserDetails;
import connected.communication.config.ConfigSecurity.CustomUserDetailsService;
import connected.communication.config.token.TokenHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final TokenHelper accessTokenHelper;
    private final CustomUserDetailsService userDetailsService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /**
         * 요청으로 전달받은 Authorixation 헤더에서 토큰 값을 꺼내온다. 이후 토근이 유효하다면
         * SpringSecurity가 관리해주는 컨텍스트에 사용자 정보(CustomAuthenticationToken)을 등록한다
         */
        String token = extractToken(request);
        if(validateToken(token)){
            setAuthentication(token);
        }
//        if(validateAccessToken(token)){
//            setAccessAuthentication("access", token);
//        }else if(validateRefreshToken(token)){
//            setRefreshAuthentication("refresh",token);
//        }
        chain.doFilter(request,response);
    }

    private String extractToken(ServletRequest request){
        return ((HttpServletRequest)request).getHeader("Authorization");
    }

    private boolean validateToken(String token){
        return token != null && accessTokenHelper.validate(token);
    }

    private void setAuthentication(String token){
        String userId = accessTokenHelper.extractSubject(token);
        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(userId);
        SecurityContextHolder.getContext().setAuthentication(new CustomAuthenticationToken(userDetails, userDetails.getAuthorities()));
    }
    /**
     * 엑세스 토큰이 유효할 때만 컨텍스트에 사용자 정보를 저장해주기 위해
     * 아래 코드 로직 리팩토링
     * 이를 위해 사용자 정보르 조회하는 작업을 제거함
     */
//    private boolean validateAccessToken(String token){
//        return token != null && tokenService.validateAccessToken(token);
//    }
//
//    private boolean validateRefreshToken(String token){
//        return token != null && tokenService.validateRefreshToken(token);
//    }
//    private void setAccessAuthentication(String type, String token){
//        String userId = tokenService.extractAccessTokenSubject(token);
//        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(userId);
//        SecurityContextHolder.getContext().setAuthentication(new CustomAuthenticationToken(type, userDetails, userDetails.getAuthorities()));
//    }

}
