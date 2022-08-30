package com.example.springbatch_11_3_skip;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * packageName    : com.example.springbatch_11_3_skip
 * fileName       : SkipItemWriter
 * author         : namhyeop
 * date           : 2022/08/16
 * description    :
 * Skip예제에서 실행될 Writer
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/16        namhyeop       최초 생성
 */
public class SkipItemWriter implements ItemWriter<String> {

    private int cnt = 0;

    @Override
    public void write(List<? extends String> items) throws Exception {
        for (String item : items) {
            if(item.equals("-12")){
                System.out.println("ItemWriter = " + item);
                throw new SkipAbleException("Write failed cnt : "  + cnt);
            }
            else{
                System.out.println("ItemWriter : " + item);
            }
        }
    }
}
