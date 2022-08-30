package com.example.springbatch_13_4_retrylistener;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

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
public class CustomItemWriter implements ItemWriter<String> {

    int count = 0;

    @Override
    public void write(List<? extends String> items) throws Exception {
        for (String item : items) {
            System.out.println("current : " + item);
            if(count < 2){
                if(count % 2 == 0){
                    count++;
                }else if(count % 2 == 1){
                    count++;
                    System.out.println("========exception=========");
                    throw new CustomRetryException("failed");
                }
            }
            System.out.println("write: " + item);
        }
    }
}
