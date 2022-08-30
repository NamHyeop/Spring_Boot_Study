package com.example.springbatch_9_1_flatfileitemwriter_delimetedandformat;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * packageName    : com.example.springbatch_9_1_flatfileitemwriter_delimetedandformat
 * fileName       : Customer
 * author         : namhyeop
 * date           : 2022/08/14
 * description    :
 * Customer 객체
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/14        namhyeop       최초 생성
 */
@Data
@AllArgsConstructor
public class Customer {
    private long id;
    private String name;
    private int age;
}
