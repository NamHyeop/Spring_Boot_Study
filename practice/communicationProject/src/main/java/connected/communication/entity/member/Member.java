package connected.communication.entity.member;

import connected.communication.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Entity
@Getter
/**
 * Member는 위와 같이 필드가 지정된 생성자를 사용하여 생성할 수 있습니다.
 * 인스턴스가 불완전한 상태에 있음을 방지하기 위해, 기본 생성자는 외부로 노출할 필요가 없다.
 * 하지만 JPA 명세에서는 엔티티에 기본 생성자를 요구하기 때문에, 기본 생성자는 접근 제어 레벨을 PROTECTED로 설정해두었습니다.
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="member_id")
    private Long id;

    /**
     * email, nickname 필드에 unique 제약조건을 추가해 인덱스 형성과 중복제약 조건을 추가함
     */
    @Column(nullable = false, length = 30, unique = true)
    private String email;

    private String password;

    @Column(nullable = false, length =  20)
    private String username;

    @Column(nullable = false, length = 20, unique = true)
    private String nickname;
    /**
     * role은 사용자의 권한을 나타낸다.
     * 한 명의 사용자는 여러 권한을 가질 수 있다.
     * 권한은 여러개의 사용자를 가질 수 있다. --> OneToMany
     */
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MemberRole> roles = new java.util.LinkedHashSet<>();

    public Member(String email, String password, String username, String nickname, List<Role> roles) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.nickname = nickname;
        this.roles = roles.stream().map(r -> new MemberRole(this, r)).collect(toSet());
    }

    /**
     * 닉네임 업데이트 요구사항 충족을 위해
     */
    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }
}

