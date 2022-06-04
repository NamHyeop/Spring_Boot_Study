package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV2;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 전략 패턴, 전략을 파라미터 형태로 전달하는 테스트
 */

@Slf4j
class ContextV2Test {
    /**
     * 전략 패턴 적용, 전략을 미리 정의한 클래스를 파라미터로 넘기는 구현
     */
    @Test
    public void strategyV1 () throws Exception{
        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }

    /**
     * 전략 패턴 적용, 익명 객체를 파라미터에 정의하여 넘기는 구현
     * @throws Exception
     */
    @Test
    public void strategyV2() throws Exception{
        ContextV2 context = new ContextV2();
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });

        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });
    }

    /**
     * 전략 패턴 적용, 람다를 이용한 익명 내부 클래스 사용
     */
    @Test
    public void strategyV3() throws Exception{
        ContextV2 context = new ContextV2();
        context.execute(()->log.info("비즈니스 로직1 실행"));
        context.execute(()->log.info("비즈니스 로직2 실행"));
    }
}