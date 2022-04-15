package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//name은 servlet의 이름을 지어주는 것, urlPatterns는 설정 이름이 들어올 경우 반응한다는 의미
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    //servlet 메소스가 호출시 서비스가 실시된다.
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        //getParameter는 http 쿼리의 변수를 가져온다.
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        String callname = request.getParameter("callname");
        System.out.println("callname = " + callname);

        //response를 사용해서 http에 정보를 전달해보자
        //setContentType으로 htto의 contentType을 결정
        //setCharecterEncoding으로 문자의 해독 방식 결정
        //wride로 전달할 정보 입력
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello " + username + " " + callname);
    }
}
