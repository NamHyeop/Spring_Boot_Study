package hello.aop.member.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Trace AOP 적용을 위한 어노테이션
 */

@Target(ElementType.TYPE) //클래스단위에 붙일 어노테이션이라는것을 명시
@Retention(RetentionPolicy.RUNTIME) // 실행중일때까지 생존 명시
public @interface ClassAop {
}
