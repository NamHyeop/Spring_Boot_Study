package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
//생성자에 @Autowired를 자동으로 넣어주고 DI를 자동으로 해주는 어노테이션
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    //요청 중 log-demo가 올 경우 응답하는 컨트롤러
    @RequestMapping("log-demo")
    //view 화면 없이 문자를 바로 반환하고 싶은경우 @ResponseBody 사용
    @ResponseBody
    //HttpServeletRequset의 자료형으로 getRequestURL을 사용하면 고객이 어떤 URL을 사용했는지 알 수 있다.
    public String logDemo(HttpServletRequest request){
        String requestURL = request.getRequestURL().toString();
        //MyLogger myLogger = myLoggerProvider.getObject();
        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequsetURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }

}
