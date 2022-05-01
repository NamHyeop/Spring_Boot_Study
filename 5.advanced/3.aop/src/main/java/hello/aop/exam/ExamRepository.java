package hello.aop.exam;

import hello.aop.exam.annotation.Retry;
import hello.aop.exam.annotation.Trace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * AOP 테스트를 위한 Repository
 */
@Repository
@Slf4j
public class ExamRepository {
    private static int seq = 0;

    /**
     * 서버에서 간헐적으로 고객의 요청이 끊기는 상황이 있다고 가정하자
     * 요청은 5번에 1번 실패하는 요청이리 가정하고 테스트 진행
     */
    @Trace
    @Retry
    public String save(String itemId){
//        log.info("this is repository");
        seq++;
        if(seq % 5 == 0){
            throw new IllegalStateException("예외 발생");
        }
        return "ok";
    }
}
