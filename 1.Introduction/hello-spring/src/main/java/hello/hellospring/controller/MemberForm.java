package hello.hellospring.controller;

//postMapping시 HTML로부터 받아올 데이터의 양식을 정해주는 클래스
public class MemberForm {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
