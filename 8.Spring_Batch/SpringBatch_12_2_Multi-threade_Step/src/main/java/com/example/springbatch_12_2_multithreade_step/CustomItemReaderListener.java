package com.example.springbatch_12_2_multithreade_step;

import org.springframework.batch.core.ItemReadListener;

/**
 * packageName    : com.example.springbatch_12_2_multithreade_step
 * fileName       : CustomItemReaderListener
 * author         : namhyeop
 * date           : 2022/08/21
 * description    :
 * CustomItemReaderListener
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/21        namhyeop       최초 생성
 */
public class CustomItemReaderListener implements ItemReadListener<Customer> {
    @Override
    public void beforeRead() {

    }

    @Override
    public void afterRead(Customer item) {
        System.out.println("Thread : " + Thread.currentThread().getName() + ", read item : " + item.getId());

    }

    @Override
    public void onReadError(Exception ex) {

    }
}
