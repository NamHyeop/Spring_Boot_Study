package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//SpringData JPA가 SpringDataJpaMemberRepository가 JpaRepository를 상속한것을 보고 Spring Bin등록을 해준다.
//인터페이스와 인터페이스를 상속받을때는 extends를 사용, 인터페이스 클래스는 implements
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository{

    @Override
    Optional<Member> findByName(String name);
}
