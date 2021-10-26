package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    //1.RequestMethod로 http메소드중 get일 경우에만 작동한다. 이런 설계가 좀 더 좋은 설계
    //@RequestMapping(value = "/new-form", method = RequestMethod.GET)
    //2.근데 너무 길어서 어노테이션으로 만들어버림 밑에 코드가 GetMapping이 그 예시
    @GetMapping("/new-form")
    public String newForm() {
        //이렇게 하면 viewResolver가 "new-form"을 반환한다.
        return "new-form";
    }

    //RequestMethod로 http메소드중 POST일 경우에만 작동한다. 이런 설계가 좀 더 좋은 설계
    //@RequestMapping(value = "/save", method = RequestMethod.POST)
    @PostMapping("/save")
    //파라미터로 받은 username과 age를 String username과 int age에 담아주는 과정, model은 스프링에서 제공하는 model이다.
    public String save(@RequestParam("username") String username, @RequestParam("age") int age, Model model) {

        // 위에서 RequestParam을 사용해서 전부 변환시켜주었다.
        //        String username = request.getParameter("username");
        //        int age = Int eger.parseInt(request.getParameter("age"));
        Member member = new Member(username, age);
        memberRepository.save(member);

        //ModelAndView로 넘겼던 "save-result를 return으로 넘겨서 없애버리고 mv.addObject로 넘겼던 model을 파라미터로 직접적으로 넘기는 방식으로 수정
        //          ModelAndView mv = new ModelAndView("save-result");
        //        mv.addObject("member", member);
        model.addAttribute("member", member);
        return "save-result";
    }

    @GetMapping
    //@RequestMapping(method = RequestMethod.GET)
    public String members(Model model){
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
//        ModelAndView mv = new ModelAndView("members");
//        mv.addObject("members", members);
        return "members";
    }
}
