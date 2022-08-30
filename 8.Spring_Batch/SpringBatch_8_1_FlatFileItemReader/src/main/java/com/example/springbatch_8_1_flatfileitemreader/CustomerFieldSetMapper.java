package com.example.springbatch_8_1_flatfileitemreader;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 * packageName    : com.example.springbatch_8_1_flatfileitemreader
 * fileName       : CustomerFieldSetMapper
 * author         : namhyeop
 * date           : 2022/08/08
 * description    :
 * Customer 정보를 읽는 자료형 설정
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/08        namhyeop       최초 생성
 */
public class CustomerFieldSetMapper implements FieldSetMapper<Customer> {
    @Override
    public Customer mapFieldSet(FieldSet fs) throws BindException {
        if(fs == null){
            return null;
        }
        Customer customer = new Customer();
        customer.setName(fs.readString(0));
        customer.setAge(fs.readInt(1));
        customer.setYear(fs.readString(2));
        return customer;
    }
}
