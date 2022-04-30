package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterCeptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

/**
 * CGLIB(MethodInterceptor) 프록시 생성을 위한 테스트
 */
@Slf4j
public class CglibTest {
    @Test
    public void cglib() throws Exception{
        ConcreteService target = new ConcreteService();

        //CGLIB 사용을 위한 enhancer 변수 선언
        Enhancer enhancer = new Enhancer();
        //구체타입 Class 상속
        enhancer.setSuperclass(ConcreteService.class);
        //구체타입에 적용시킬 로직 할당
        enhancer.setCallback(new TimeMethodInterCeptor(target));
        //프록시 생성 선언
        ConcreteService proxy = (ConcreteService)enhancer.create();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
        proxy.call();
    }

}