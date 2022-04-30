package hello.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Proxy 테스트, spring의 org.aoplliance.intercept 패키지의 AOP 모듈 MethodInterceptor 테스트, CGLIB의 MethodInterceptor랑 동명이므로 패키지 사용 주의
 */
@Slf4j
public class TimeAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

        Object result = invocation.proceed();

        long endTime = System.currentTimeMillis();
        long retTime = endTime - startTime;
        log.info("TimeProxy 종료 retTime={}", retTime);
        return result;
    }
}
