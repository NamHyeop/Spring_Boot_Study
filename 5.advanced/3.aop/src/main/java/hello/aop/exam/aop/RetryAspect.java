package hello.aop.exam.aop;

import hello.aop.exam.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * 서버 요청 실패시 재전송 Asepect-Retry
 */
@Slf4j
@Aspect
public class RetryAspect {
    @Around("@annotation(retry)")
    public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        log.info("[retry] {} retry={}", joinPoint.getSignature(), retry);

        int maxRetry = retry.value();
        //Repository에서 Exception 발생할 경우 밑의 catch에 에러를 잡는다.
        //그후 maxRetry만큼 서버로 재전송을 요청하면서 시도를 수행한다.
        Exception exceptionHolder = null;
//        log.info("sequence trace 0");
        for(int retryCount = 1; retryCount <= maxRetry; retryCount++){
//            log.info("sequence trace 1");
            try{
//                log.info("sequence trace 2");
                log.info("[retry] try count={}/{}", retryCount, maxRetry);
                return joinPoint.proceed();
            }catch (Exception e){
//                log.info("sequence Exception");
                exceptionHolder = e;
            }
        }
        throw exceptionHolder;
    }
}
