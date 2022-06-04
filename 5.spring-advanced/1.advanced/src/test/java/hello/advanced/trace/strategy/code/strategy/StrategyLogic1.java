package hello.advanced.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 전략 패턴, 클래스를 활용한 주입 테스트를 위한 class
 */
@Slf4j
public class StrategyLogic1 implements Strategy{
    @Override
    public void call() {
        log.info("비즈니스 로직1 실행");
    }
}
