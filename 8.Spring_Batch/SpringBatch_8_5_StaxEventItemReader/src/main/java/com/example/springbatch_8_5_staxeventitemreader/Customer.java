package com.example.springbatch_8_5_staxeventitemreader;

import lombok.Data;

/**
 * packageName    : com.example.springbatch_8_5_staxeventitemreader
 * fileName       : Customer
 * author         : namhyeop
 * date           : 2022/08/11
 * description    :
 * Customer Dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/11        namhyeop       최초 생성
 */
@Data
public class Customer {
    private final long id;
    private final String name ;
    private final int age;
}
