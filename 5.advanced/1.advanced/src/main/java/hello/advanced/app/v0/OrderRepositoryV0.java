package hello.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {
    //저장 로직
    public void save(String itemId) {
        //장애 발생 기준 - ex 입력이 들어올 경우 정상적 경로 호출이 아니라고 판단
        if (itemId.equals("ex")) {
            throw new IllegalArgumentException("예외 발생");
        }
        sleep(1000);

    }

    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
