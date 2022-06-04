package hello.login.web.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 세션 관리
 */

@Slf4j
@Component
public class SessionManager {

    public static final String SESSION_COOKIE_NAME = "mySessionId";
    //동시성 문제 때문에 ConcurrentHashMap<>을 사용해야한다.
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    /**
     * 세션 생성
     */
    public void createSession(Object value, HttpServletResponse response){
        //세션 id를 생성하고, 값을 세션에 저장한다.
        //UUID.randonUUID는 랜덤값을 생성해주고 이것을 toString으로 문자열로 바꿔준다.(Universial의 U를 따온거라고 하는데... 오글거린다.)
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);

        //쿠키 생성
        Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        response.addCookie(mySessionCookie);
    }

    /**
     * 세션 조회
     */

    public Object getSession(HttpServletRequest request){
        /** if 문을 사용한 구현
         Cookie[] cookies = request.getCookies();
         if(cookies == null){
            return null;
        }
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(SESSION_COOKIE_NAME)){
                log.info("cookie value = {}", cookie.getValue());
                return sessionStore.get(cookie.getValue());
            }
        }
        return null;
         */
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if(sessionCookie == null){
            return null;

        }
        return sessionStore.get(sessionCookie.getValue());
    }

    public Cookie findCookie(HttpServletRequest request, String cookieName){
        //request를 통해 cookie들의 값을 받아온다.
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return null;
        }
        //Arrays.stream을 사용하면 배열을 스트림으로 바꿔준다.
        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findAny()
                .orElse(null);
    }

    public void expire(HttpServletRequest request){
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if(sessionCookie != null){
            sessionStore.remove(sessionCookie.getValue());
        }
    }

}
