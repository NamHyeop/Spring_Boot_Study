package hello.proxy.proxyfactory;

import hello.proxy.app.v1.OrderServiceV1Impl;
import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ConcreteService;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

@Slf4j
public class ProxyFactoryTest {

    @Test
    @DisplayName("인터페이스가 있으면 JDK 동적 프록시 사용")
    public void interfaceProxy() throws Exception{
        //타겟 선언
        ServiceInterface target = new ServiceImpl();
        //프록시 팩토리 및 target 선언
        ProxyFactory proxyFactory = new ProxyFactory(target);
        //프록시에서 돌아갈 기능 설정
        proxyFactory.addAdvice(new TimeAdvice());
        //프록시 생산
        ServiceInterface proxy = (ServiceInterface)proxyFactory.getProxy();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
        proxy.save(); //프록시의 실행할 메소드를 사용
        proxy.find();

        Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
        //인터페이스 기반 target을 설정해주었으므로 advice에서 adviceInvocationHandler를 사용했기에 CGLIBProxy는 거짓이 나와야함
        Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isFalse();
    }

    @Test
    @DisplayName("구체 클래스만 있으면 CGLIB 사용")
    public void concreteProxy() throws Exception{
        //타겟 선언
        ConcreteService target = new ConcreteService();
        //프록시 팩토리 및 target 선언
        ProxyFactory proxyFactory = new ProxyFactory(target);
        //프록시에서 돌아갈 기능 설정
        proxyFactory.addAdvice(new TimeAdvice());
        //프록시 생산
        ConcreteService proxy = (ConcreteService)proxyFactory.getProxy();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
        proxy.call(); //프록시의 실행할 메소드를 사용

        Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }

    @Test
    @DisplayName("ProxyTargetClass 옵션을 사용하여 인터페이스가 있어도 CGLIB를 통한 구체 타입 설정을 사요하기")
    public void proxyTargetClass() throws Exception{
        //타겟 선언
        ServiceInterface target = new ServiceImpl();
        //프록시 팩토리 및 target 선언
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true); //구체타입(class타입)으로 사용하겠다고 설정하여 CGLIB 사용을 통한 Proxy 생성
        //프록시에서 돌아갈 기능 설정
        proxyFactory.addAdvice(new TimeAdvice());
        //프록시 생산
        ServiceInterface proxy = (ServiceInterface)proxyFactory.getProxy();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
        proxy.save(); //프록시의 실행할 메소드를 사용
        proxy.find();

        Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }
}
