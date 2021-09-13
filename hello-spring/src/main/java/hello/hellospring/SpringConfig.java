package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

//1.Spring이 Configuration을 실행하고
@Configuration
//2.@Bean을 확인하고 Spring에 추가하라는 의미로 인식하고 추가한다.
public class SpringConfig {

    //스프링 데이터 JPA를 활용한 연결
    private final MemberRepository memberRepository;

    @Autowired //생성자가 한 개일때 생략이 가능하긴하다
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*JPA 활용한 연결
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }*/

    @Bean //Spring Bean에 저장하겠다의 선언
    public MemberService memberService() { //spring이 MemberSErvice와 memberRepository를 동시에 인식하고 그 안의 메소드가 필요할 때 실행
        return new MemberService(memberRepository);
    }

/* Jdbc, Jdbc Template를 활용한 연결
   private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

//    @Bean
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository(); // 이전에 구현할 대 MemberRepository는 인터페이스라 리턴 불가능!(인터페이스는 리턴 불가능) 추상화만 되있을 뿐, 로컬에서 만든 메모리로 디비 연결하는 방법
////        return new JdbcMemberRepository(dataSource); // Jdbc 기법을 사용한 데이터베이스 연결
////        return new JdbcTemplateMemberRepository(dataSource); //JdbcTemplate를 활용 데이터베이스 연결
////        return new JpaMemberRepository(em); //JPA를 활용한 연결
//    }

    //TimeTraceAop를 AOP에 따로 추가해주는 과정
//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }
}
