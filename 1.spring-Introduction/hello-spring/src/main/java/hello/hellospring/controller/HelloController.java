package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") //url에서 hello가 들어오면 반응하게 해주는 설정
    public String hello(Model model){ //model은 Spring이 만들어서 넣어준다.
        model.addAttribute("data", "spring"); //hello가 들어오면 model을 넘겨주겠다.
        return "hello"; //반환은 templates 폴더에 어떤 인덱스페이지를 줄 것인지를 의미한다. 이 코드에서는 templates.hello.html한테 model을 반환한다.
    }

    @GetMapping("hello-mvc") //hello-mvc를 탐색해야할 경우 (like from URL or templates)
    public  String helloMvc(@RequestParam("name") String name, Model model){ //RequstParam의 의미는 탐색되는 위치의 변수를 자리를 의미하고 뒤에 name은 내가 입력해서 바꿀 정보를 의미함
        model.addAttribute("name", name);
        return "hello-template"; //templates
    }
    /*위의 예시 응용
    이렇게 RequestParam의 변수를 바꿔주면 templates에서도 변수를 바꿔줘야 한다.
    public  String helloMvc(@RequestParam("test123") String name, Model model){ //RequstParam의 의미는 탐색되는 위치의 변수를 자리를 의미하고 뒤에 name은 내가 입력해서 바꿀 정보를 의미함
        model.addAttribute("test123", name);
        return "hello-template"; //templates
    }
    */

    @GetMapping("hello-string")
    //@ResponseBody는 HTML의 Body 부분에 내가 직접 데이터를 내려주겠다는 의미, 이렇게 하면 view를 사용하지 않고 그대로 HTML로 내려간다. 위의 @ResponseBody 부분을 사용하지 않은 방식은 view가 사용되어있다.
    //결과를 보면 HTML 소스가 하나도 없고 전달한 문자만 있는 것을 확인할 수 있다.
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
        //return "<html>hello " + name + "/<html>"; <- 이렇게 하면 html 코드도 보이나 굳이;;
    }

    //API를 사용하는 방식(API중 JSON을 사용하는 방식)
    //데이터를 요구할 때 많이 사용함
    @GetMapping("hello-api")
    @ResponseBody
    //객체 Hello를 반환하는 helloApi 메소드
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
