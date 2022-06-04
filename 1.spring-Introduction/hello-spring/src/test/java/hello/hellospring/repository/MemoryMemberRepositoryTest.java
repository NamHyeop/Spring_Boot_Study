package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach //실행전에 사용되어야할 메소드를 지정해주는 어노테이션
    public void beforeEcah() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach //AfterEach 어노테이션은 메소드가 실행된 후 이 메소드가실행이된다.
    public void afterEach(){
        memberRepository.clearStore();
    }
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        memberRepository.save(member); //repository에 member를 저장한다.

        Member result = memberRepository.findById(member.getId()).get();
        //1.Assertions.assertEquals(member, result); //왼쪽이 기대값, 오른쪽에 있는 값이 왼쪽이랑 같은가
        assertThat(member).isEqualTo(result); //1번 코드랑 같으나 이 방식을 좀 더 많이 사용, meber가 result랑 같은가
    }

    @Test
    public void FindByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1); //member1인 spring1을 repository에 저장

        Member member2 = new Member();
        member2.setName("spring2");//member2인 spring2를 repository에 저장
        memberRepository.save(member2);

        Member result = memberRepository.findByName("spring1").get(); //객체를 비교하기 때문에 값이 4E27667 이런식으로 나온다.
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void FindAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberRepository.save(member2);

        List<Member> result = memberRepository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
