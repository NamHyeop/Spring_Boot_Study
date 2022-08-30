package com.example.springbatch_12_2_multithreade_step;

import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

/**
 * packageName    : com.example.springbatch_12_2_multithreade_step
 * fileName       : CustomItmeWriteListener
 * author         : namhyeop
 * date           : 2022/08/21
 * description    :
 * ItemWriteListner 구현체
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/21        namhyeop       최초 생성
 */
public class CustomItemWriteListener implements ItemWriteListener<Customer> {
    @Override
    public void beforeWrite(List<? extends Customer> items) {

    }

    @Override
    public void afterWrite(List<? extends Customer> items) {
        System.out.println("Thread : " + Thread.currentThread().getName() + ", write items : " + items.size());
    }

    @Override
    public void onWriteError(Exception exception, List<? extends Customer> items) {

    }
}
