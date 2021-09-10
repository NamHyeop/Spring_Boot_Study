package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; //jpa는 EntityManager에서 모든것이 동작이 된다. application에서 jpa를 implementation 했다면 Spring이 시작될대 Entitymanger를 자동으로 만들어준다.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //persist의 의미는 영속하다, 영구저장하다임
        return member;
    }
    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);//find의 첫 번째 매개변수는 조회할 타입, 뒤에는 식별자(어떤 것을 탐색할지), 이러변 Member 타입대로 query문이 날아감
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member as m", Member.class).getResultList(); //query문은 m 전체를 조회하는것 (as m으로 별칭 설정)
    }
}
