package hello.aop.internalcall;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

/**
 * callServiceV0, CallLogAspect Test class
 */

@Import(CallLogAspect.class)
@SpringBootTest
class CallLogAspectTest {
    @Autowired CallServiceV0 callServiceV0;

    //프록시에 의한 external 내부의 internal 호출은 프록시가 적용되지 않는다.
    @Test
    public void external() throws Exception{
        callServiceV0.external();
    }

    //프록시에의해 internal 호출이 적용된다.
    @Test
    public void internal() throws Exception{
        callServiceV0.internal();
    }
}