package hello.proxy.pureproxy.proxypattern.code;

import lombok.extern.slf4j.Slf4j;

/**
 *  proxy 패턴, subject의 구현체, 1초 대기후 로그를 찍어주는 기능. 캐쉬 테스트를 할 목적으로 만든 파일
 */
@Slf4j
public class RealSubject implements Subject {

    @Override
    public String operation() {
        log.info("실제 객체 호출");
        sleep(1000);
        return "data";
    }

    private void sleep(int millis) {
        try{
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
