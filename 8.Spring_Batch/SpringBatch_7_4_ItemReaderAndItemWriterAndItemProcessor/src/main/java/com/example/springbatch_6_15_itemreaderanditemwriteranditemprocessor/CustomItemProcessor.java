package com.example.springbatch_6_15_itemreaderanditemwriteranditemprocessor;

import org.springframework.batch.item.ItemProcessor;

import java.util.Locale;

/**
 * packageName    : com.example.springbatch_6_15_itemreaderanditemwriteranditemprocessor
 * fileName       : CustomItemProcessor
 * author         : namhyeop
 * date           : 2022/08/06
 * description    :
 * 사용자 정의 Processor. reader로 데이터가 넘어올 때마다 customer 객체의 name을 대문자로 바꿔주는 Processor이다.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/06        namhyeop       최초 생성
 */
public class CustomItemProcessor implements ItemProcessor<Customer, Customer> {

    @Override
    public Customer process(Customer customer) throws Exception {
        customer.setName(customer.getName().toUpperCase());
        return customer;
    }
}
