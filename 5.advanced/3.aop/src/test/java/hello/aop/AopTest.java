package hello.aop;

import hello.aop.order.OrderRepository;
import hello.aop.order.OrderService;
import hello.aop.order.aop.*;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

/**
 * AspectVn을 테스트하기 위한 class
 */
//@Import(AspectV1.class)
//@Import(AspectV2.class)
//@Import(AspectV3.class)
//@Import(AspectV4.class)
//@Import({AspectV5Order.LogAspect.class, AspectV5Order.TxAspect.class})
@Import(AspectV6Advice.class)
@Slf4j
@SpringBootTest
public class AopTest {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    //프록시 객체인지 여부확인
    @Test
    public void aopInfo() throws Exception{
        System.out.println("isAopProxy, orderService=" + AopUtils.isAopProxy(orderService));
        System.out.println("isAopProxy, orderRepository=" + AopUtils.isAopProxy(orderRepository));
    }

    //컨트롤러 시스템의 흐름 테스트
    @Test
    void success(){
        orderService.orderItem("itemA");
    }

    //컨트롤러 시스템의 예외 처리 테스트
    @Test
    void exception(){
        Assertions.assertThatThrownBy(() -> orderService.orderItem("ex")).isInstanceOf(IllegalStateException.class);
    }
}
