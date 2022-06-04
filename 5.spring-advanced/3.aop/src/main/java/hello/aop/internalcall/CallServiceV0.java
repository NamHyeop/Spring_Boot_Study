package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * target에서 프록시를 호출하면 프록시가 호출이 안되는 문제를 보여주는 class
 */

@Slf4j
@Component
public class CallServiceV0 {
    public void external() {
        log.info("call external");
        internal(); //내부 메서드 호출(this.internal());
    }

    public void internal(){
        log.info("call internal");
    }
}
