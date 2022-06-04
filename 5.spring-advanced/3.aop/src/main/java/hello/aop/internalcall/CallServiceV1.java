package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * target 에서도 자기 자신을 프록시로 호출하는 방법1
 * 스프링 컨테이너에서 프록시를 자동으로 등록한 이후 setter를 적용하기 때문에 setter로 설정해서 주입을 받으면 프록시를 호출한다.
 */
@Slf4j
@Component
public class CallServiceV1 {
    private CallServiceV1 callServiceV1;

    @Autowired

    public void setCallServiceV1(CallServiceV1 callServiceV1) {
        this.callServiceV1 = callServiceV1;
    }

    public void external(){
        log.info("call external");
        callServiceV1.internal(); //외부 메서드 호출
    }

    public void internal(){
        log.info("call internal");
    }
}
