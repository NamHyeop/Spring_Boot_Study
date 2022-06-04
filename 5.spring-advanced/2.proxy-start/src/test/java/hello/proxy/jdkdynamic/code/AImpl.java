package hello.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

/**
 * proxy- InvocationHandler 테스트를 위한 인터페이스 구현체
 */

@Slf4j
public class AImpl implements AInterface{
    @Override
    public String call() {
        log.info("A 호출");
        return "A";
    }
}
