package hello.login.web.argumentresolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Target은 어노테이션이 사용될 수 있는 영역을 지정해주는것, 현재 예제 기준 파라미터에만 사용한다
@Target(ElementType.PARAMETER)
//리플렉션 등을 활용할 수 있도록 런타임까지 에노테이션 정보가 남아있는다.
@Retention(RetentionPolicy.RUNTIME)
public @interface Login {
}
