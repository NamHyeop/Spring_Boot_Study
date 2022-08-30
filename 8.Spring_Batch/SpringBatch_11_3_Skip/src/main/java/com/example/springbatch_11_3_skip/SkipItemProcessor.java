package com.example.springbatch_11_3_skip;

import org.springframework.batch.item.ItemProcessor;

/**
 * packageName    : com.example.springbatch_11_3_skip
 * fileName       : SkipItemProcessor
 * author         : namhyeop
 * date           : 2022/08/16
 * description    :
 * Skip예제에서 실행될 Processor
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/16        namhyeop       최초 생성
 */
public class SkipItemProcessor implements ItemProcessor<String,String> {

    private int cnt = 0;

    @Override
    public String process(String item) throws Exception {
        if(item.equals("6") || item.equals("7")) {
            System.out.println("ItemProcessor = " + item);
            cnt++;
            throw new SkipAbleException("process failed cnt : " + cnt);
        }else{
            System.out.println("ItemProcess : " + item);
            return String.valueOf(Integer.valueOf(item) * -1);
        }
    }
}
