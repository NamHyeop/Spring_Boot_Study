package hello.proxy.pureproxy.decoratorpattern.code;

import lombok.extern.slf4j.Slf4j;

/**
 * decorator패턴, client의 요청을 수행하는 class
 */
@Slf4j
public class DecoratorPatternClient {
    private Component component;

    public DecoratorPatternClient(Component component) {
        this.component = component;
    }

    public void execute(){
        String result = component.operation();
        log.info("result={}", result);
    }
}
