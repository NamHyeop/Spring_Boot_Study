package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component //spring Bin에 등록하는 과정
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") //어느 구역에 AOP를 걸지 타겟팅 하는 과정 *은 실행하다의 의미. 해석:실행한다 hello패키지의 hellospring 그 밑에 실행한다 하위 폴더들을
    //Service패키지만 실행하고 싶을 때 예시 @Around("* hello.hellospring.service..*(..))"), 추가적인 검색 방식을 알고싶다면 Around 키워드로 검색하면 된다.
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try{
            //proceed를 사용하면 다음 메소드로 진행이 가능하다.
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
