package hello.proxy.cglib.code;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
public class TimeMethodInterCeptor implements MethodInterceptor {
    private final Object target;

    public TimeMethodInterCeptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

        //CJLIB는 Method 보다 MethodProxy가 최적화가 더 잘되어있어서 빠르므로 MethodProxy 사용을 권장하고 있다.
        Object result = proxy.invoke(target, args);

        long endTime = System.currentTimeMillis();
        long retTime = endTime - startTime;
        log.info("TimeProxy 종료 retTime={}", retTime);
        return result;
    }
}

