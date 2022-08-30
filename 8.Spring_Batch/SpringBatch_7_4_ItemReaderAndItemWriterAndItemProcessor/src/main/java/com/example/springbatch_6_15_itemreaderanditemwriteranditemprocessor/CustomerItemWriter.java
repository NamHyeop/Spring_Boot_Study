package com.example.springbatch_6_15_itemreaderanditemwriteranditemprocessor;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * packageName    : com.example.springbatch_6_15_itemreaderanditemwriteranditemprocessor
 * fileName       : CustomerItemWriter
 * author         : namhyeop
 * date           : 2022/08/06
 * description    :
 * 사용자정의 writer
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/06        namhyeop       최초 생성
 */
public class CustomerItemWriter implements ItemWriter<Customer> {

    @Override
    public void write(List<? extends Customer> items) throws Exception {
        items.forEach(item -> System.out.println(item));
    }
}
