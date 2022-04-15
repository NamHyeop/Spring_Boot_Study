package hello.login.web.member;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("/add")
    //현재 예제 기준 ("member") 생략가능 자료형 Member의 앞글자가 소문자로 변경되는게 기본값이기 때문이다.
    public String addForm(@ModelAttribute("member") Member member){
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    //@Validate도 사용가능 현재는 자바에서 지원하는 Valid를 사용, @Validated와 @Valid의 차이는 groups가 사용가능하냐 불가능하냐의 차이
    public String save(@Valid @ModelAttribute Member member, BindingResult result){

        //입력양식에 안맞게 입력하여 오류 발생시 다시 입력폼으로
        if(result.hasErrors()){
            return "members/addMemberForm";
        }
        memberRepository.save(member);
        return "redirect:/";
    }
}
