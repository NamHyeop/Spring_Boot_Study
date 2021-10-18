package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    //JSON의 JACKSON 라이브러리를 사용할 것 Dependencies에서 심실할 때 찾아보자
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println("messageBody = " + messageBody);

        //읽어온 JSON 객체를 HelloData 객체에 맞게 변환하는 과정. ObjectMapper라는 JSON 라이브러리에서 readValue라는 메소드를 활용하여 데이터를 읽어 객체로 만든다.
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        //hello 객체에 getUsername이 없는데 롬복을 사용하여서 선언해준것이다.
        System.out.println("helloData.username = " + helloData.getUsername());
        System.out.println("helloData.age = " + helloData.getAge());

        response.getWriter().write("send suceess JSON FILE");
    }
}
