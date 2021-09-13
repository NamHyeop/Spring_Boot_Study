package hello.hellospring.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller //이렇게 하면 기능은 아무것도 안 변하지만 스프링을 실행할 때 스프링 빈을 생성한 뒤 Member Anotation이 MemberController 객체를 생성해서 집어넣어준다
public class MemberController {
    private final MemberService memberService; //매우 중요. 우리는 하나의 객체만 사용해야 하는데 이걸 밑에 Autowired를 사용해서 기존에 스프링 빈에 들어있던 MemberService의 객체로 연결시킨다.

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
        //Aop를 활용한 동작과정을 테스트할 때 사용하는 과정, proxy에 담고 spring bean에 옮겨야 하므로 과정들이 출력된다.
        System.out.println("memberService = "+ memberService.getClass());
    }

    @GetMapping("/members/new") ///1.members/new가 들어오면
    public String createForm(){
        return "members/createMemberForm";//2.templates의 createMemberForm을 찾음, 없으면 static 폴더로 이동
    }

    @PostMapping("/members/new") // member/new에서 입력받은 데이터를 spring으로 넘겨줌, 즉 나는 데이터를 받는것이다.
    public String create(MemberForm form){ //이전에 만들어 두었던 MemberForm class의 name에 정보가 담긴 상태로 넘어온다., HTML의 name을 보고 넘어옴
        Member member = new Member();
        member.setName(form.getName());
        System.out.println("member = " + member.getName());
        memberService.join(member);
        return "redirect:/";
    }

//    @GetMapping(value="/members")
//    public String list(Model model){
//        List<Member> members = memberService.findMembers();
//        model.addAttribute("members", members);
//        return "members/memberList";
//    }
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
