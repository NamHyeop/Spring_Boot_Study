package hello.aop.internalcall;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 구조를 변경하는 방법, 프록시 호출이 안되는 내부 서비스를 아에 새로운 클래스로 구분하여 호출이 발생할 수 있게 분리하는 테스트
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CallServiceV3 {
    private final InternalService internalService;

    public void external(){
        log.info("call external");
        internalService.internal();
    }
}
