package connected.communication.factory.entity;

import connected.communication.entity.member.Member;
import connected.communication.entity.member.Role;
import connected.communication.factory.dto.SignInRequestFactory;
import connected.communication.factory.dto.SignUpRequestFactory;
import connected.communication.repository.member.MemberRepository;

import java.util.List;

import static java.util.Collections.emptyList;

public class MemberFactory {
    public static Member createMember(){
        return new Member("email@email.com", "123456a!", "username", "nickname", emptyList());
    }

    public static Member createMember(String email, String password, String username, String nickname){
        return new Member(email, password, username, nickname, emptyList());
    }

    public static Member createMember(List<Role> roles){
        return new Member("email@email.com", "123456a!", "username", "nickname", roles);
    }
}
