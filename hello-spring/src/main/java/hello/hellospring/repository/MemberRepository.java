package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원이 저장소에 저장되는 기능
    Optional<Member> findById(Long id);//id를 찾아오는 기능
    Optional<Member> findByName(String name);//name을 찾아오는 기능
    List<Member> findAll();//모든것을 list로 반환
}
