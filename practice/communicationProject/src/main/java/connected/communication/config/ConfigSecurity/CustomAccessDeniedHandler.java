package connected.communication.config.ConfigSecurity;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AccessDeniedHandler는 인증은 되더라도 사용자가 요청에 대한 접근 권한이 없을 때에 작동되는 핸들러
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        /*
         * exception/예외로 Redirect처리하여 RestController에서 처리함
         */
        response.sendRedirect("/exception/access-denied");
    }
}
