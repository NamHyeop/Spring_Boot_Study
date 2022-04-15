package hello.thymeleaf.basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/template")
public class TemplateController {

    /**
     * 템플릿을 활용한 줄 단위의 변환
     */
    @GetMapping("/fragment")
    public String template() {
        return "template/fragment/fragmentMain";
    }

    /**
     * 템플릿 fragment를 사용해서 부분적인 변환
     */
    @GetMapping("/layout")
    public String layout(){
        return "template/layout/layoutMain";
    }

    /**
     * 템플릿 fragement를 사용해서 html 자체를 변환 하는 예제
     */
    @GetMapping("/layoutExtend")
    public String layoutExtends(){
        return "template/layoutExtend/layoutExtendMain";
    }
}
