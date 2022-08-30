package com.example.springbatch_7_5_itemstream;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * packageName    : com.example.springbatch_7_5_itemstream
 * fileName       : CustomItemWriter
 * author         : namhyeop
 * date           : 2022/08/06
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/06        namhyeop       최초 생성
 */
public class CustomItemWriter implements ItemStreamWriter<String> {

    @Override
    public void write(List<? extends String> items) throws Exception {
        System.out.println("ItemStreamWriter - write");
        items.forEach(item -> System.out.println(item));
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        System.out.println("ItemStreamWriter - open");

    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        System.out.println("ItemStreamWriter - update");
    }

    @Override
    public void close() throws ItemStreamException {
        System.out.println("ItemStreamWriter - close");
    }
}
