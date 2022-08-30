package com.example.springbatch_6_15_itemreaderanditemwriteranditemprocessor;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.example.springbatch_6_15_itemreaderanditemwriteranditemprocessor
 * fileName       : CustomItemReader
 * author         : namhyeop
 * date           : 2022/08/06
 * description    :
 * 사용자 정의 reader
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/06        namhyeop       최초 생성
 */
public class CustomItemReader implements ItemReader<Customer> {

    private List<Customer> list;

    public CustomItemReader(List<Customer> list){
        this.list = new ArrayList<>(list);
    }

    @Override
    public Customer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(!list.isEmpty()){
            return list.remove(0);
        }
        return null;
    }
}
