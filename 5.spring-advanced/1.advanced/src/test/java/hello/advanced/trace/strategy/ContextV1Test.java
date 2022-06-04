package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 전략 패턴 구현 테스트
 */

@Slf4j
public class ContextV1Test {
    /**
     * 1. 전략 패턴 미사용 구현
     */
    @Test
    public void strategyV0() throws Exception {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        log.info("비즈니스 로직1 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        log.info("비즈니스 로직2 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }

    /**
     * 2.전략 패턴, 클래스를 생성해서 넘기는 구현
     * @throws Exception
     */
    @Test
    public void strategyV1() throws Exception {
        //given
        Strategy strategyLogic1 = new StrategyLogic1();
        Strategy strategyLogic2 = new StrategyLogic2();
        //when
        ContextV1 context1 = new ContextV1(strategyLogic1);
        ContextV1 context2 = new ContextV1(strategyLogic2);
        //then
        context1.execute();
        context2.execute();
    }

    /**
     * 3.전략 패턴, 익명 내부 클래스를 사용한 구현
     * @throws Exception
     */
    @Test
    public void strategyV2() throws Exception {
        //given
        Strategy strategyLogic1 = new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        };
        //when
        ContextV1 context1 = new ContextV1(strategyLogic1);
        log.info("strategyLogic1={}", strategyLogic1.getClass());
        context1.execute();

        Strategy strategyLogic2 = new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        };
        ContextV1 context2 = new ContextV1(strategyLogic2);
        //then
        log.info("strategyLogic2={}", strategyLogic2.getClass());
        context2.execute();
    }

    /**
     * 4.전략 패턴, 익명 내부 클래스를 사용한 구현, 생성자 위치에서 바로 Strategy를 생성해서 변수를 생성하는 줄을 생략
     * @throws Exception
     */
    @Test
    public void strategyV3() throws Exception {
        //given
        ContextV1 context1 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });
        context1.execute();

        ContextV1 context2 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });
        context2.execute();
    }

    /**
     * 4.전략 패턴, 람다를 활용한 구현
     * @throws Exception
     */
    @Test
    public void strategyV4() throws Exception{
        ContextV1 context1 = new ContextV1(() -> log.info("비즈니스 로직1 실행"));
        context1.execute();

        ContextV1 context2 = new ContextV1(()-> log.info("비즈니스 로직 2실행"));
        context2.execute();
    }
}
