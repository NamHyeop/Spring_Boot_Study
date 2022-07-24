package hello.itemservice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class testcontrolelr {
    @PostMapping("/testPage")
    public void hello(String test){
        System.out.println("test = " + test);
    }

    @GetMapping ("/testPage2")
    public void hello2(String test){
        System.out.println("test = " + test);
    }

    static class testDto{
        String test;
    }
}
