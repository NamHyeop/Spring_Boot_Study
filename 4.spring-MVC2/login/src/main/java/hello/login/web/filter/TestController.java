package hello.login.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {
    @GetMapping("/testFilteSignal/{code}")
    public String testSignal(@PathVariable("code") String code){
        log.info("controller Section");

        if(code.equals("response")){
            throw new RuntimeException("예외 호출");
        }
        return "OK";
    }
}
