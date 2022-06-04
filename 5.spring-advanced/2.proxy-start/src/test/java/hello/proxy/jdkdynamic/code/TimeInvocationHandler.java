package hello.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * proxy-Invocation, 프록시가 수행할 명령어, 일종의 반복되는 형식을 프록시로 수행하는 작업 진행
 */
@Slf4j
public class TimeInvocationHandler implements InvocationHandler {
    private final Object target;

    public TimeInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * 프록시가 수행할 메서드를 의미한다.
     * @param proxy : 프록시 자기 자신
     * @param method : 수행할 메소드
     * @param args : 메소드 수행시 필요한 변수
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();
        Object result = method.invoke(target, args);
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeProxy 종료 resultTime={}", resultTime);
        return result;
    }
}
