package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import hello.advanced.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {
    private ThreadLocalService service = new ThreadLocalService();

    @Test
    public void field() throws Exception{
        //given
        log.info("main start");
        //when
        //일반 표현, 이 내용을 람다식으로 변경했다.
//        Runnable userA = new Runnable() {
//            @Override
//            public void run() {
//                fieldService.logic("userA");
//            }
//        };
        Runnable userA = () ->{
            service.logic("userA");
        };
        Runnable userB =() ->{
            service.logic("userB");
        };
        //then
        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start(); //A 실행
        sleep(200); //200으로 변경시 동시성 문제 발생
        threadB.start();
        sleep(3000);
        log.info("main exit");
    }

    private void sleep(int millis){
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
