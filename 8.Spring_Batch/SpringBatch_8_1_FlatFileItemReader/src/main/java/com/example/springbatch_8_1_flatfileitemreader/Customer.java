package com.example.springbatch_8_1_flatfileitemreader;

import lombok.Data;

/**
 * packageName    : com.example.springbatch_8_1_flatfileitemreader
 * fileName       : Customer
 * author         : namhyeop
 * date           : 2022/08/08
 * description    :
 * Customer Dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/08d        namhyeop       최초 생성
 */
@Data
public class Customer {
    private String name;
    private int age;
    private String year;
}
