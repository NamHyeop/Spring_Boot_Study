package hello.springmvc.basic;

import lombok.Data;

//lobmok의 어노테이션중 하나 getter, setter, TosTring, EqualAndHashCode, RequiredArgsConstructor를 자동으로 생성해준다
@Data
public class HelloData {
    private String username;
    private int age;
}
