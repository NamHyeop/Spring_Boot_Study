package hello.servlet.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    Long id;
    String username;
    int age;

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
