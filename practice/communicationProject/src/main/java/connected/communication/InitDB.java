package connected.communication;

import connected.communication.entity.member.Member;
import connected.communication.entity.member.Role;
import connected.communication.entity.member.RoleType;
import connected.communication.exception.RoleNotFoundException;
import connected.communication.repository.member.MemberRepository;
import connected.communication.repository.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
/**
 * InitDB클래스는 local 환경에서만 동작하도록 설정
 */
@Profile("local")
public class InitDB {
    private final RoleRepository roleRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * PostConstruct는 트랜잭션을 적용할 수 없다.
     * 그렇기 때문에 생성이후 작동할 수 있게 EventListner를 사용했다.
     * ApplicationReadyEvent.class를 사용하면 모든 작업이 완료된 후 실행된다.
     */
    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initDB(){
        log.info("initialize database");
        initRole();

    }

    private void initRole(){
        roleRepository.saveAll(List.of(RoleType.values())
                .stream().map(roleType -> new Role(roleType)).collect(Collectors.toList()));
    }

    /**
     * 생성 이후 Admin 정보 추가
     */
    private void initTestAdmin(){
        memberRepository.save(
                new Member("admin@admin.com"
                        , passwordEncoder.encode("123456a!")
                        , "admin"
                        , "admin"
                        , List.of(
                                roleRepository.findByRoleType(RoleType.ROLE_NORMAL).orElseThrow(RoleNotFoundException::new),
                                roleRepository.findByRoleType(RoleType.ROLE_ADMIN).orElseThrow(RoleNotFoundException::new)))
                        );
    }

    /**
     * 생성 이후 user 테스트 정보 추가
     */
    private void initTestMember(){
        memberRepository.saveAll(
                List.of(new Member("member1@member.com", passwordEncoder.encode("123456a!"), "member1", "member1",
                                List.of(roleRepository.findByRoleType(RoleType.ROLE_NORMAL).orElseThrow(RoleNotFoundException::new))),
                        new Member("member2@member.com", passwordEncoder.encode("123456a!"), "member2", "member2",
                                List.of(roleRepository.findByRoleType(RoleType.ROLE_NORMAL).orElseThrow(RoleNotFoundException::new))))
        );
    }
}