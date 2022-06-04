package hello.aop.pointcut;

import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.*;

/**
 * pointcut execution 표현식 테스트
 */

@Slf4j
public class ExecutionTest {
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    public void init() throws  NoSuchMethodException{
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    public void printMethod() throws Exception{
        log.info("helloMethod={}", helloMethod);
    }

    // ====== 포인트컷 exceution 문법 테스트 ======

    //접근제어, 반환, 선언, 메서드이름, 파라미터 전부 명시한 경우
    @Test
    void exactMatch(){
        pointcut.setExpression("execution(public java.lang.String hello.aop.member.MemberServiceImpl.hello(java.lang.String))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    //*을 사용하여 모든 경우의 매치 만들기, 접근제어,선언은 생략가능
    @Test
    void allMatch(){
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    //선언타입 생략하고 메서드이름만 지정하기
    @Test
    public void nameMatch() throws Exception{
        pointcut.setExpression("execution(* hello(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    //메서드 이름에 *응용
    @Test
    public void nameMatchStar1() throws Exception{
        pointcut.setExpression("execution(* hel*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    //메서드 이름에 *응용
    @Test
    public void nameMatchStar2() throws Exception{
        pointcut.setExpression("execution(* *el*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    //메서드 이름에 *응용 실패 사례, 메소드 이름이 존재하지 않음
    @Test
    public void nameMatchFalse() throws Exception{
        pointcut.setExpression("execution(* nono(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    //패키지 경로에 풀네임으로 명시하기 *응용
    @Test
    public void packageExactMatch1() throws Exception{
        pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.hello(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    //패키지 경로에 *응용
    @Test
    public void packageExactMatch2() throws Exception{
        pointcut.setExpression("execution(* hello.aop.member.*.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    //패키지 경로에 *응용 잘못된 사례, .과..을 표현하여 현재와 하위 타입 명시를 정확히 해줘야함
    @Test
    public void packageExactMatchFalse() throws Exception{
        pointcut.setExpression("execution(* hello.aop.*.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    //패키지 경로에 *응용, 위의 실패 사례를 정확히 구현한 경우1
    @Test
    public void packageMatchSubPackage1() throws Exception{
        pointcut.setExpression("execution(* hello.aop.member..*.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    //패키지 경로에 *응용, 위의 실패 사례를 정확히 구현한 경우2
    @Test
    public void packageMatchSubPackage2() throws Exception{
        pointcut.setExpression("execution(* hello.aop..*.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    // ====== 부모타입 허용 테스트 ======
    //1.구체 타입 조회라 당연히 참이다.
    @Test
    void typeExactMatch(){
        pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    //2.인터페이스 타입 조회이나 인터페이스에 있는 메서드를 재정의 했기 때문에 조회가 가능하므로 참이다.
    @Test
    void typeMatchSuperType(){
        pointcut.setExpression("execution(* hello.aop.member.MemberService.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    //3.구체타입에 있는 기능을 조회하므로 참
    @Test
    void typeMatchInternal() throws NoSuchMethodException {
        pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.*(..))");
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
        assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isTrue();
    }

    //4.인터페이스에 있는 기능을 조회해야하는데 구체타입에만 선언되어있는 기능을 조회했으므로 거짓
    @Test
    void typeMatchNoSuperTypeMethodFalse() throws NoSuchMethodException {
        pointcut.setExpression("execution(* hello.aop.member.MemberService.*(..))");
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
        assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isFalse();
    }

    // ====== 파라미터 테스트 ======
    //매개변수가 (String) 으로만 주어지는 경우
    @Test
    public void argsMatch(){
        pointcut.setExpression("execution(* *(String))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    //매개변수가 ()으로만(없어야만) 주어지는 경우
    @Test
    public void argsMatchNoArgs(){
        pointcut.setExpression("execution(* *())");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    //매개변수가 정확히 하나의 파라미터만 허용 하면서 모든 타입을 허용하는 경우
    @Test
    public void argsMatchStar(){
        pointcut.setExpression("execution(* *(*))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    //모든 파라미터, 모든 타입을 허용하는 경우, 파라미터가 없어도 상관없으며 개수도 상관없음
    @Test
    public void argsMatchAll(){
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    //String 타입으로 시작만 한다면 뒤에있는 모든 파라미터, 모든 타입을 허용하는 경우, String 뒤에 오는 경우는 파라미터가 없어도 상관없으며 개수도 상관없음
    @Test
    public void argsMatchComplex(){
        pointcut.setExpression("execution(* *(String, ..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }
}
