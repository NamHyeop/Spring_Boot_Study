package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * HTTP의 URL의 정보를 받아오는 방식.
 * GET, POST와 같은
 */
@Slf4j
@Controller
public class RequestParamController {
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}. age={}", username, age);

        response.getWriter().write("ok");
    }

    //ResponseBody를 사용할 경우 return 값을 http body에 넣어서 반환 RestController와 같은 효과
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParanV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge){
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    //변수의 이름이 속성과 같다면 RequestParam의 매개변수를 지울수가 있다.
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    //아에 RequestParam을 지우는것도 가능
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username,int age){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    //required를 사용하여 파라미터가 존재하지 않는 경우 에러를 발생 시킬 수 있다.
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            //required를 트루로 설정하면미 username이 반드시 있어야 한다는 의미이나.
            @RequestParam(required = true) String username,
            //자바의 기본형에(string int char 같은)는 null을 넣을수가 없으므로 Integer를 사용해서 null을 넣어줘야 한다. int가 아닌 Integer를 사용한 이유는 false 같이 들어와서 사용을 안한 경우 null값을 담을 그릇이 필요하기 때문이다.
            @RequestParam(required = false) Integer age ){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            //파라미터에 아무것도 안넣을 경우 defaultValue로 값을 지정해줄수 있다.
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    //한 번에 여러 파라미터를 받아올때 Map을 사용, 파라미터의 값이 여러개라면 MultiValueMap을 사용하면 된다. 그러나 MultiValueMap을 사용하는 경우는 드물다
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap){
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    //model을 직접 만들어서 해주는 불편한 방법 여태까지 이렇게 해왔음. 밑에는 ModelAttribute를 활용한 스프링 형식의 편한 방법
//    @ResponseBody
//    @RequestMapping("/model-attribute-v1")
//    public String modelAttributeV1(@RequestParam String username, @RequestParam int age){
//        HelloData helloData = new HelloData();
//        helloData.setUsername(username);
//        helloData.setAge(age);
//        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
//        log.info("helloData={}", helloData);
//        return "OK";
//    }

    //ModelAttribute를 적용하여 model에 값을 적용하는 편리한 방법, ModelAttribute는 RequestParam처럼 생략가능하다.
    //둘 다 생략할 경우 @RequestParam은 String, int, Integer와 같은 단순타입으로 해석되고 @ModelAttribute는 argument resolver(HttpServeletRequest, 자신이만든클래스)로 해결해둔다.
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
