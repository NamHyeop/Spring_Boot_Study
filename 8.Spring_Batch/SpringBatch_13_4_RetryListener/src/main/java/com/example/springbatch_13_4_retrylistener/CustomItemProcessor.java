package com.example.springbatch_13_4_retrylistener;

import org.springframework.batch.item.ItemProcessor;

/**
 * packageName    : com.example.spring_batch_13_3_skiplistener_retrylistener.skiplistener
 * fileName       : CustomItemProcessor
 * author         : namhyeop
 * date           : 2022/08/26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/26        namhyeop       최초 생성
 */
public class CustomItemProcessor implements ItemProcessor<Integer,String> {

    int count = 0;

    @Override
    public String process(Integer item) throws Exception {
        System.out.println("currentProcessor: " + item);
        if(count < 2){
            if(count % 2 == 0){
                count++;
            }else if(count % 2 == 1){
                count++;
                throw new CustomRetryException("failed");
            }
        }
        return String.valueOf(item);
    }
}
