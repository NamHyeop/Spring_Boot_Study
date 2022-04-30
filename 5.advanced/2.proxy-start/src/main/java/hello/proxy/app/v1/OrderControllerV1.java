package hello.proxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller 인터페이스
 */
@RequestMapping //스프링은 @Controller 또는 @RequestMapping 이 있어야 스프링 컨트롤러로 인식한다.
@ResponseBody
public interface OrderControllerV1 {
    //인터페이스에서 파라미터를 받을때 RequestParam을 안써주면 자바 버전에 따라 인식 불가능한 경우가 존재함, 그러므로 넣어줘야한다
    @GetMapping("/v1/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v1/no-log")
    String noLog();
}
