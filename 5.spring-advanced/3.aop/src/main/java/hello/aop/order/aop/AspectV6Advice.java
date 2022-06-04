package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @Aspect 사용 연습, 내부 Aspect 종류(@Before, @AfterReturning, @AfterThrowing, @After) 테스트
 */

@Slf4j
@Aspect
public class AspectV6Advice {

    @Pointcut("hello.aop.order.aop.Pointcuts.orderAndService()")
    public void allService(){}
//    @Around("hello.aop.order.aop.Pointcuts.orderAndService()")
    @Around("allService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable{
        try{
            //@Before 실행영역
            log.info("[around][트랜잭션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            //@AfterReturning 실행영역
            log.info("[around][트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;
        }catch (Exception e){
            //@AfterThrowing 실행영역
            log.info("[around][트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        }finally {
            //@After 실행영역
            log.info("[around][리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }

//    @Before("hello.aop.order.aop.Pointcuts.orderAndService()")
    @Before("allService()")
    public void deBefore(JoinPoint joinPoint){
        log.info("[before] {}", joinPoint.getSignature());
    }

    //reutrning값으로 명시한 객체가 반환 가능, 즉 반환 객체 변경 불가능, result 자료형을 받을때 형식에 어긋나면 Aspect자체가 호출이 안됌
//    @AfterReturning(value = "hello.aop.order.aop.Pointcuts.orderAndService()", returning = "result")
    @AfterReturning(value = "allService()", returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result){
        log.info("[return] {} return={}", joinPoint.getSignature(), result);
    }

    //Exception 자료형이 현재보다 상위 클래스일 경우 Aspect 자체가 호출이 안된다.
//    @AfterThrowing(value = "hello.aop.order.aop.Pointcuts.orderAndService()", throwing = "ex")
    @AfterThrowing(value = "allService()", throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex){
        log.info("[ex] {} messag={}", joinPoint.getSignature(), ex.getMessage());
    }

//    @After(value = "hello.aop.order.aop.Pointcuts.orderAndService()")
    @After(value = "allService()")
    public void doAfter(JoinPoint joinPoint){
        log.info("[afet] {}", joinPoint.getSignature());
    }
}
