package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>(); //static 사용
    private static long sequence = 0L; //static 사용

    public Member save(Member member){
        member.setId(++sequence);
        log.info("save: member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId){
        /*1. if절 사용한 구현
        List<Member> all = findAll();
        for(Member m : all){
            if(m.getLoginId().equals(loginId)){
                return Optional.of(m);
            }
        }
        */

        //람다를 사용한 구현
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }
    public List<Member> findAll(){
        //map의 values를 사용하면 모든 Map의 value값이 모두 List로 반환된다.
        //c++에서 vector에 vector<int> arr(v.begin(), v,end())와 유사
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
