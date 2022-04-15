package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/") //domain이 들어오자마자 실행되게 해주기 위한 의도를 가지고 있음
    public String home(){
        return "home";
    }
}
