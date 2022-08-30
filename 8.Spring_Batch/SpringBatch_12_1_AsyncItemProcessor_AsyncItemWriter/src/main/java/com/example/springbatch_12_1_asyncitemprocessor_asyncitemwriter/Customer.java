package com.example.springbatch_12_1_asyncitemprocessor_asyncitemwriter;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * packageName    : com.example.springbatch_12_1_asyncitemprocessor_asyncitemwriter
 * fileName       : Customer
 * author         : namhyeop
 * date           : 2022/08/19
 * description    :
 * Customer Dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/19        namhyeop       최초 생성
 */
@Data
@AllArgsConstructor
public class Customer {

    private final long id;
    private final String firstName;
    private final String lastName;
    private final Date birthdate;
}
