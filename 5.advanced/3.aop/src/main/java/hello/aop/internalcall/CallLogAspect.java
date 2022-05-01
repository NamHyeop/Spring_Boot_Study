package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * AOP class
 */
@Slf4j
@Aspect
public class CallLogAspect {
    @Before("execution(* hello.aop.internalcall..*.*(..))")
    public void doLog(JoinPoint joinPoint){
        log.info("aop={}", joinPoint.getSignature());
    }
}
