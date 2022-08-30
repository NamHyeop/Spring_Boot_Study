package com.example.springbatch_12_2_multithreade_step;

import org.springframework.batch.core.ItemProcessListener;

/**
 * packageName    : com.example.springbatch_12_2_multithreade_step
 * fileName       : CustomItemProcessListener
 * author         : namhyeop
 * date           : 2022/08/21
 * description    :
 * ItemProcessListener 구현체
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/21        namhyeop       최초 생성
 */
public class CustomItemProcessListener implements ItemProcessListener<Customer, Customer> {

    @Override
    public void beforeProcess(Customer item) {

    }

    @Override
    public void afterProcess(Customer item, Customer result) {
        System.out.println("Thread : " + Thread.currentThread().getName() + ". process item : " + item.getId());
    }

    @Override
    public void onProcessError(Customer item, Exception e) {

    }
}
