package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 프록시 기술중 JDK 동적 프록시와 CGLIB의 타입 캐스팅을 확인한다,
 * JDK 동적 프록시는 인터페이스 타입 기반이므로 구체타입으로는 캐스팅 x
 * CGLIB는 구체 타입 기반이므로 인터페이스 타입으로 캐스팅 O
 */

@Slf4j
public class ProxyCastingTest {
    @Test
    public void jdkProxy() throws Exception{
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        //proxyFactory.setProxyTargetClass(false); //jdk동적 프록시 사용 테스트
        proxyFactory.setProxyTargetClass(true); //CGLIB 프록시 사용 테스트

        //1.프록시를 인터페이스로 캐스팅 성공
        MemberService memberServiceProxy = (MemberService)proxyFactory.getProxy();
        log.info("proxy class={}", memberServiceProxy.getClass());

        //2-1 CGLIB 테스트 - CGLIB를 인터페이스로 받은 타입을 구체타입으로 캐스팅 성공
        MemberService castingMemberService = (MemberServiceImpl)proxyFactory.getProxy();
        log.info("proxy class={}", castingMemberService.getClass());

        //2-2 JDK동적프록시 테스트 - JDK 동적 프록시를 구현 클래스로 캐스팅 시도하지만 실패가 발생하므로 ClassCastException이 발생하게된다.
        //assertThrows(ClassCastException.class, () -> { MemberServiceImpl castingMemberService = (MemberServiceImpl)memberServiceProxy; });
    }
}
