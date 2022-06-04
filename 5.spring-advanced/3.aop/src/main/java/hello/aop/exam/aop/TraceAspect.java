package hello.aop.exam.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 서버 요청 Trace 출력 Aspcet
 */

@Slf4j
@Aspect
public class TraceAspect {
    @Before("@annotation(hello.aop.exam.annotation.Trace)")
    public void doTrace(JoinPoint joinPoint){
//        log.info("this is TraceAspect");
        Object[] args = joinPoint.getArgs();
        log.info("[trace] {} args={}", joinPoint.getSignature(), args);

    }
}
