package hello.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;
/**
 * proxy- InvocationHandler 테스트를 위한 인터페이스 구현체
 */
@Slf4j
public class BImpl implements BInterface{
    @Override
    public String call() {
        log.info("B 호출");
        return "b";
    }
}
