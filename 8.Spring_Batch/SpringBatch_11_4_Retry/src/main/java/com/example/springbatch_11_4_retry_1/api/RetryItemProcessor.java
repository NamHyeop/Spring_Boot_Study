package com.example.springbatch_11_4_retry_1.api;

import com.example.springbatch_11_4_retry_1.RetryableException;
import org.springframework.batch.item.ItemProcessor;

/**
 * packageName    : com.example.springbatch_11_4_retry_1
 * fileName       : RetryItemProcesssor
 * author         : namhyeop
 * date           : 2022/08/16
 * description    :
 * Batch 실행중 실행될 프로세스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/16        namhyeop       최초 생성
 */
public class RetryItemProcessor implements ItemProcessor<String, String> {

    private int cnt = 0;

    @Override
    public String process(String item) throws Exception {

//        System.out.println("item = " + item);
        if(item.equals("2") || item.equals("3")) {
            cnt++;
            throw new RetryableException("failed cnt : " + cnt);
//        return null;
        }
        return item;
    }
}
