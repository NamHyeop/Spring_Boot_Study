package com.example.springbatch_8_6_jsonitemreader;

import lombok.Data;

/**
 * packageName    : com.example.springbatch_8_6_jsonitemreader
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
    private Long id;
    private String name;
    private int age;

}

