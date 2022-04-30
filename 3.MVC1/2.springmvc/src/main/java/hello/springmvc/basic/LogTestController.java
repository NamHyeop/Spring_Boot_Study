package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
    //Slf4j 어노테이션이 롬복처럼 -> 추가해줌 private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";
        String name2 = "boot";

        //log.trace("Trace log " + name); 이런 사용방법은 잘못된것이다. 왜냐하면 debug단계로 로그페이지로 설정한경우 내부에서 +연산은 일어나는데 실행은 안되므로 메모리를 점유만 하는 현상이 발생
        log.trace("trace log = {}", name);
        log.debug("debug long = {}", name);
        log.info(" info log = {} {}", name, name2);
        log.warn(" warn log = {}", name);
        log.error("error log = {}", name);

        //System.out.println("name = " + name);
        return "OK";
    }
}
