package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * HTTP API를 활용하여 JSON 데이터를 받아오는 방법
 */

@Slf4j
@Controller
public class RequestBodyJsonController {
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        //objectMapper가 읽어온 데이어틀 helloData의 양식에 맞게 데이터를 넣어줌
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData);
        response.getWriter().write("OK");
    }

    //ResponseBody 와 RequestBody로 위의 코드를 단축시킨 버전
    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException{
        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "OK";
    }

    //HelloData객체 자체를 매개변수로 받아서 objectMapper를 사용을 안하고 단축시키는 버전
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData){
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "OK";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity){
        HelloData helloData = httpEntity.getBody();
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "OK";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    //Json데이터가 httpConvert로 전환되어 helloData가 되었다가 helloData를 httpConvert를 사용하여 다시 json으로 전환한뒤 반환하는것을 보여준다.
    public HelloData requestBodyJsonV5(@RequestBody HelloData data){
        log.info("username={}, age={}", data.getUsername(), data.getAge());
        return data;
    }
}
