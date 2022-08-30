package com.example.springbatch_14_1_springbatchtest;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * packageName    : com.example.springbatch_14_1_springbatchtest
 * fileName       : Customer
 * author         : namhyeop
 * date           : 2022/08/26
 * description    :
 * Customer Dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/26        namhyeop       최초 생성
 */
@Data
@AllArgsConstructor
public class Customer {

    private long id;
    private String firstName;
    private String lastName;
    private Date birthdate;
}
