package hello.hellospring.domain;

public class Member {
    private Long id; //데이터 베이스에 저장되는 임의의 값
    private String name; //아이디 변수

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
