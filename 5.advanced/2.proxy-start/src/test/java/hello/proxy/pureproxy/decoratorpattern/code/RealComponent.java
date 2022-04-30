package hello.proxy.pureproxy.decoratorpattern.code;

import lombok.extern.slf4j.Slf4j;

/**
 * decorator 패턴, component의 함제 구현체
 */
@Slf4j
public class RealComponent implements Component{
    @Override
    public String operation() {
        log.info("RealComponent 실행");
        return "data";
    }
}
