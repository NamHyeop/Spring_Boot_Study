package hello.login.web.filter;

import hello.login.web.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Pattern;

@Slf4j
public class LoginCheckFilter implements Filter {
    private static final String[] whitelist = {"/", "/members/add", "/login", "/logout", "/css/*"};
    //init,destroy에는 defalut가 선언되어있기 때문에 구현이 필수가 아니다.
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try{
            log.info("인증 체크 필터 시작 {}", requestURI);

            if(isLoginCheckPath(requestURI)){
                log.info("인증 체크 로직 실행 {}", requestURI);
                HttpSession session = httpRequest.getSession(false);
                if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null){
                    log.info("미인증 사용자 요청 {}", requestURI);

                    httpResponse.sendRedirect("login?redirectURL=" + requestURI);
                    return; //여기서 중요, 미인증 사용자는 다음으로 진행하지 않고 진행 종료
                }
            }

            //매우 중요, 더 진행할 필터가 없는지 검토 후 Servlet을 호출, 실질적으로 이거 빼면 DispatchServlet이 호출이 안되서 안돌아간다.
            chain.doFilter(request, response);
        }catch(Exception e){
            throw e; //예외 로깅이 가능하지만, 톰캣까지 예외를 보내줘야 한다.
        }finally{
            log.info("인증 체크 필터 종료 {}", requestURI);
        }
    }

    /**
     * 화이트 리스트의 경우 인증 체크
     */
    private boolean isLoginCheckPath(String requestURI){
        //PatternMatchUtils를 활용하여 내부의 값이 있는지 없는지 판단 해주는 메소드. 현재 예제 기준 whitelist안에 requestURI가 있다면 참, 없으면 false
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}
