package com.example.spring_batch_13_3_skiplistener_retrylistener.retrylistenr;

import org.springframework.batch.core.SkipListener;

/**
 * packageName    : com.example.spring_batch_13_3_skiplistener_retrylistener
 * fileName       : CustomSkipListener
 * author         : namhyeop
 * date           : 2022/08/26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/26        namhyeop       최초 생성
 */
public class CustomSkipListener implements SkipListener<Integer, String> {

    @Override
    public void onSkipInRead(Throwable t) {
        System.out.println(">> onSkipRead : " + t.getMessage());
    }

    @Override
    public void onSkipInWrite(String item, Throwable t) {
        System.out.println(">> onSkipWrite : " + item);
        System.out.println(">> onSkipWrite : " + t.getMessage());
    }

    @Override
    public void onSkipInProcess(Integer item, Throwable t) {
        System.out.println(">> onSkipProcess : " + item);
        System.out.println(">> onSkipProcess : " + t.getMessage());

    }
}
