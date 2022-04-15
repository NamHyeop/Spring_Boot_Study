package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**
 * HTTP의 Body의 정보를 읽어와야 하는 경우
 */

@Slf4j
@Controller
public class RequestBodyStringController {
    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ServletInputStream inputStream = request.getInputStream();
        //inputStream을 string형식으로 복사. 매개변수의 두 번째는 정보를 어떤 문자로 해석해줄것인지를 명시하는 매개변수
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        response.getWriter().write("OK");
    }

    //Spring은 Servlet API에서 request는 InputStream, response는 OutptStream을 받을 수 있다. 그러므로 밑에 처럼 생략이 가능하다.
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        responseWriter.write("OK");
    }

    //HttpEnity를 활용한 바디메시지 해석
//    @PostMapping("/request-body-string-v3")
//    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException{
//        String messageBody = httpEntity.getBody();
//        return new HttpEntity<>("This is point send body message");
//    }

    //httpEntity를 상속해서 만든 ResponseEntity를 활용하면 상태코드를 반환할 수 도 있다.
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(RequestEntity<String> httpEntity) throws IOException{
        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);
        return new ResponseEntity<>("This is point send body message", HttpStatus.CREATED);
    }

    //가장 간편한 방법으로 @RequestBody를 사용해서 http body 정보를 읽어오고 @Response Body로 상태코드를 반환해준다.
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requeestBodyStringV4(@RequestBody String messageBody){
        log.info("messageBody={}", messageBody);
        return "OK";
    }
}
