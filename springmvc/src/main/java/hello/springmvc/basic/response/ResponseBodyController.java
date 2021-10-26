package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * http 메시지 바디에 직접 데이터를 입력하여 넣는 방법들
 * 처음 3개는 문자열을 넘기는 방법 이후 3개는 JSON을 활용하여 넘기는 방법
 */

@Slf4j
//@Controller
//ResponseBody를 사용하면 모든 클래스의 메소드에 ResponseBody가 적용된다
//@RestController = @ResponseBody + @Controller, 뷰 템플릿을 사용할 때는 사용하면 안된다
@RestController
public class ResponseBodyController {

    //getWriter를 사용하는 방법
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException{
        response.getWriter().write("response-body-string-v1이 정상적으로 실행되었습니다.");
    }

    //ResponseEntity를 사용하는 방법
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() throws IOException{
        return new ResponseEntity<>("response-body-string-v2가 정상적으로 실행되었습니다.", HttpStatus.OK);
    }

    //@ResponseBody를 사용해서 직접적으로 넘기는 방법
    //@ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3(){
        return "response-body-string-v3가 정상적으로 실행되었습니다.";
    }

    //여기서부터는 JSON을 처리하는 방법들

    //ResponseEntity를 사용해서 JSON과 http상태를 동시에 넘기는 방법이다.
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1(){
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    //ResponseBody를 사용해서 JSON을 return값으로 넘기고 ResponseStatust를 사용해서 http의 상태를 넘긴다.
    @ResponseStatus(HttpStatus.OK)
    //@ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2(){
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return helloData;
    }


}
