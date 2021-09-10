package hello.hellospring.domain;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //데이터 베이스에 저장되는 임의의 값
    //private String username;
    private String name; //아이디 변수

    // @Column(name = "username") //culumn을 추가하면 앞에 변수이름(name)이 뒤에 변수( username)인 것이랑 매칭이 되게 해주는 설정이다.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
