package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 *  @Aspect 사용 연습, 포인트컷을 분리해서 재사용 하기 위한 테스트 class
 */
@Slf4j
@Aspect
public class AspectV2 {
    //hello.aop.order 퍀키지와 하위 패키지
    @Pointcut("execution(* hello.aop.order..*(..))") //pointcut expression
    private void allOrder(){} //pointcut signature

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("[log] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }
}
