package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResonseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mav = new ModelAndView("response/hello").addObject("data", "hello!");
        return mav;
    }

    //여기에 ResponseBody를 붙치면 response/hello에 있는 html이 넘어가는게 아니라 순수한 문자열이 넘어간다. 이 차이를 알고 있어야
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){
        model.addAttribute("data", "hello");
        return "response/hello";
    }

    /**
     * void를 반환하는 경우, @Conroller를 사용하고 HttpservletResponse, OutpuStream 같은 HTTP 메시지 바디를 처리하는 파리미터가 없으면 요청 URL을 참고해서 논리 뷰이름으로 용요한다.
     * 이 방식은 명시성이 너무 떨어지고 이렇게 딱딱 떨이지는 경우가 많지 않으므로 권장하지 않는다.
     */
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model){
        model.addAttribute("data", "hello!!");
    }
}
