package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        //spring container를 생성하고 컨테이너 대표 클래스인 AppConfig 객체를 설정해주는 과정
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //spring container안에서 memberService 이름이고 타입이 MemberService.class인 선언문을 가져와서 memberService 변수에 담는 과정 (getBean 매개변수 : (이름, type))
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
