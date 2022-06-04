package hello.thymeleaf.basic;


import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {

    /**
     * 타임리프를 활용한 문자 출력
     */

    @GetMapping("/text-basic")
    public String textBasic(Model model){
        model.addAttribute("data", "Hello Spring");
        return "basic/text-basic";
    }

    /**
     * 타임리프중 unescape를 활용한 문자 출력
     */
    @GetMapping("/text-unescaped")
    public String textUnescaped(Model model){
        model.addAttribute("data", "Hello <b>Spring!</b>");
        return "basic/text-unescaped";
    }

    @GetMapping("/variable")
    public String variable(Model model){
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);

        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);

        return "basic/variable";
    }

    /**
     *     session은 로그인처럼 웹페이지가 들어오고 종료할 때까지 정보를 유지 시키는것이다. 일단은 여기까지만 알아두자
     */
    @GetMapping("/basic-objects")
    public String basicObjects(HttpSession session){
        session.setAttribute("sessionData", "Hello Session");
        return "basic/basic-objects";
    }

    //Component로 Spring bean에 추가해준것
    //helloBean 검색시 응답.
    @Component("helloBean")
    static class HelloBean{
        public String hello(String data){
            return "Hello " + data;
        }
    }

    /**
     * 시간 정보를 조회하는 용도. date.html랑 같이 보면서 필요할 때 사용하면 된다. 이외의 필요한 유틸리티는 타임리프 홈페이지에 자세히 있다. 필요할 때 찾아 쓰자 @return
     */
    @GetMapping("/date")
    public String date(Model model){
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }

    /**
     * URL 조절에 필요한 예제
     */
    @GetMapping("link")
    public String link(Model model){
        model.addAttribute("param1", "date1");
        model.addAttribute("param2", "date2");
        return "basic/link";
    }

    /**
     * 리터럴 예제
     * ' '를 안 붙쳐서 생기는 오류에 주의하자
     */
    @GetMapping("/literal")
    public String literal(Model model){
        model.addAttribute("data", "Spring");
        return "basic/literal";
    }

    /**
     * 연산 예제
     */

    @GetMapping("/operation")
    public String operation(Model model){
        model.addAttribute("nullData", null);
        model.addAttribute("data", "Spring!");
        return "basic/operation";
    }

    /**
     * 속성들을 바꾸는 예제
     */
    @GetMapping("/attribute")
    public String attribute(){
        return "basic/attribute";
    }

    /**
     * 반복문 예제
     */
    @GetMapping("/each")
    public String each(Model model){
        addUsers(model);
        return "basic/each";
    }

    /**
     * 조건부 예제
     */

    @GetMapping("/condition")
    public String condition(Model model){
        addUsers(model);
        return "basic/condition";
    }

    /**
     * 주석 예제
     */

    @GetMapping("/comments")
    public String comments(Model model){
        model.addAttribute("data", "Spring");
        return "basic/comments";
    }
    private void addUsers(Model model){
        List<User> list = new ArrayList<>();
        list.add(new User("userA", 10));
        list.add(new User("userB", 20));
        list.add(new User("userC", 30));
        model.addAttribute("users", list);
    }

    /**
     * block 사용 예제
     */

    @GetMapping("/block")
    public String block(Model model){
        addUsers(model);
        return "basic/block";
    }

    /**
     * 자바스크립트 인라인 사용 예제
     */

    @GetMapping("/javascript")
    public String javascript(Model model){
        model.addAttribute("user", new User("userA", 10));
        addUsers(model);
        return "basic/javascript";
    }

    @Data
    static class User{
        private String username;
        private int age;

        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }
}

