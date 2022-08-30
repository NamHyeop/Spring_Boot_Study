package com.example.springboot_9_6_jpaitemwriter;

import org.modelmapper.ModelMapper;
import org.springframework.batch.item.ItemProcessor;

/**
 * packageName    : com.example.springboot_9_6_jpaitemwriter
 * fileName       : CustomItemProcessor
 * author         : namhyeop
 * date           : 2022/08/15
 * description    :
 * Customer객체를 Customer2로 변환하는 processor
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/15        namhyeop       최초 생성
 */
public class CustomItemProcessor implements ItemProcessor<Customer,Customer2> {

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Customer2 process(Customer item) throws Exception {
        //ModelMapper는 item객체가 들어올 경우 Custsomer2로 전환해준다.
        Customer2 customer2 = modelMapper.map(item, Customer2.class);
        //전환한 customer2를 반환
        return customer2;
    }
}