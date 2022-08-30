package com.example.springbatch_10_1_compositeitemprocessor;

import org.springframework.batch.item.ItemProcessor;

/**
 * packageName    : com.example.springbatch_10_1_compositeitemprocessor
 * fileName       : CustomItemProcessor
 * author         : namhyeop
 * date           : 2022/08/16
 * description    :
 * CompositeItemProcessor를 이루는 두 번째 ItemProcessor
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/16        namhyeop       최초 생성
 */
public class CustomItemProcessor2 implements ItemProcessor<String, String> {

    int cnt = 0;

    @Override
    public String process(String item) throws Exception {
        cnt++;
        return (item + " this is process2 " + cnt);
    }
}
