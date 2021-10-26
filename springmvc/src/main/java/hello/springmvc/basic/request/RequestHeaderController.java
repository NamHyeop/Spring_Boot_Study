package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {
    @RequestMapping("/headers")
    //header들을 정보를 가져오는 매개변수
    public String headers(HttpServletRequest request,
                          HttpServletRequest response,
                          HttpMethod httpMethod, //http 메소드
                          Locale locale, //언어 우선순위
                          //http query 파라피터처럼 하나의 키에 여러 값을 받을 때 사용한다
                          @RequestHeader MultiValueMap<String, String> headerMap,
                          @RequestHeader ("host") String host,
                          @CookieValue(value = "myCookie", required = false) String cookie){
        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host = {}", host);
        log.info("CookValue={}", cookie);
        return "OK";
    }
}
