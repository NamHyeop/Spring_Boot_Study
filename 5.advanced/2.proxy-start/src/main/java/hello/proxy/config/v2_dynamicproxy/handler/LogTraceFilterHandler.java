package hello.proxy.config.v2_dynamicproxy.handler;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.util.PatternMatchUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * InvocationHandlere를 사용해서 프록시 생성, no-log가 실행 안되게 메소드 이름을 필터링 해줌
 */

public class LogTraceFilterHandler implements InvocationHandler {
    private final Object target;
    private final LogTrace logTrace;
    private final String[] patterns;

    public LogTraceFilterHandler(Object target, LogTrace logTrace, String...patterns) {
        this.target = target;
        this.logTrace = logTrace;
        this.patterns = patterns;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        TraceStatus status = null;
        try{

            //메서드 이름 필터, 메서드 이름에 request, order, save가 안들어가면 로그 출력 X
            String methodName = method.getName();
            if(!PatternMatchUtils.simpleMatch(patterns, methodName)){
                return method.invoke(target, args);
            }

            String message = method.getDeclaringClass().getSimpleName() + "."
                    + method.getName() + "()";
            status = logTrace.begin(message);

            //로직 호출()
            Object result = method.invoke(target, args);

            logTrace.end(status);
            return result;
        }catch (Exception e){
            logTrace.exception(status, e);
            throw e;
        }
    }
}

