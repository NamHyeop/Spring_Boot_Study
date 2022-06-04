package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {
    @Test
    public void reflection0() throws Exception{
        BasicFunction basicFunction = new BasicFunction();

        //공통 로직1 시작
        log.info("start");
        String ret1 = basicFunction.callA();
        log.info("result={}" , ret1);
        //공통 로직2 종료

        //공통 로직2 시작
        log.info("start");
        String ret2 = basicFunction.callB();
        log.info("result={}", ret2);
        //공통 로직2 종료
    }

    @Test
    public void reflection1() throws Exception{
        //클래스의 메서드 정보 추출
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$BasicFunction");

        BasicFunction target = new BasicFunction();
        //callA 메서드 정보
        Method methodCallA = classHello.getMethod("callA");
        Object result1 = methodCallA.invoke(target);
        log.info("result1={}", result1);

        //callB 메서드 정보
        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(target);
        log.info("result1={}", result2);
    }

    @Test
    public void reflection2() throws Exception{
        //클래스의 메서드 정보 추출
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$BasicFunction");

        BasicFunction target = new BasicFunction();
        //callA 메서드 정보
        Method methodCallA = classHello.getMethod("callA");
        dynamicCall(target, methodCallA);

        //callB 메서드 정보
        Method methodCallB = classHello.getMethod("callB");
        dynamicCall(target, methodCallB);
    }

    private void dynamicCall(Object target, Method methodCallA) throws IllegalAccessException, InvocationTargetException {
        log.info("start");
        Object result = methodCallA.invoke(target);
        log.info("result={}", result);
    }

    @Slf4j
    static class BasicFunction{
        public String callA(){
            log.info("callA");
            return "A";
        }

        public String callB(){
            log.info("callB");
            return "B";
        }
    }
}
