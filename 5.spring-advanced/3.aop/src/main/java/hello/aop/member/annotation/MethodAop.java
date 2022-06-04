package hello.aop.member.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * RetryAspect AOP 적용을 위한 어노테이션
 */

@Target(ElementType.METHOD) //메소드 단위에 붙일 어노테이션이라는것을 ㅁ여시
@Retention(RetentionPolicy.RUNTIME) // 생존주기를 프로그램이 끝날때까지 생존하게 설정
public @interface MethodAop {
    String value();
}
