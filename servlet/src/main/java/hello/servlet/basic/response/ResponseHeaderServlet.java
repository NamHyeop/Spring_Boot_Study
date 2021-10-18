package hello.servlet.basic.response;

import org.apache.catalina.filters.ExpiresFilter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //setStatus가 이전 http 수업에서 상태코드 반환할때 상태코드를 반환하는 메소드이다., HTTPServletResponse에 들어가보면 HTTP 상태코드들이 정의되어있다.
        //전체적으로 HTTP 헤더의 정보를 만들어주는 과정이다.
        //status-line에 200을 넣어준것
        response.setStatus(HttpServletResponse.SC_OK);
        //response-header <- headre에 정보를 넣어주는 과정 value에 아래와 같이 옵션을 넣어주면 cache 안쓰겠다는 조건
        response.setHeader("Content-Type",  "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello");

        // content(response);
        // cookie(response);
        redirect(response);

        //Body에 들어가는 내요을 정의
        PrintWriter writer = response.getWriter();
        writer.println("ok");
    }

    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        //이렇게 두 문장만 이용하면 위에 22번줄 처럼 할 수 있음 메소드의 이름을 보면 바로 이해감
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성)
    }

    private void cookie(HttpServletResponse response) {
        //이렇게 문법 맞추면서 넣는게 귀찮아서 밑에처럼 객체를 사용한다.
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //status code를 302를 반환할것이고 위치를 hallo-form.html로 보내버릴것이다.
        //Status Code 302
        //Location: /basic/hello-form.html

        //밑에 두줄이 밑에 3번째 줄과 다르다. 훨씬 더 직관적이고 효율적
        //response.setStatus(HttpServletResponse.SC_FOUND); //302
        //response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }
}
