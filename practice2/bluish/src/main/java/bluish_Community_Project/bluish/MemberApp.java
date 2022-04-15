package bluish_Community_Project.bluish;

import bluish_Community_Project.bluish.member.Grade;
import bluish_Community_Project.bluish.member.Member;
import bluish_Community_Project.bluish.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        Member memberA = new Member(1L, "memberA", Grade.RESIDENT);
        memberService.join(memberA);

        Member member = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + member.getName());
    }
}
