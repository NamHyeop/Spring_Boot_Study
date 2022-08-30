package com.example.springbatch_9_5_jobbatchitemwriter;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * packageName    : com.example.springbatch_9_5_jobbatchitemwriter
 * fileName       : Customer
 * author         : namhyeop
 * date           : 2022/08/15
 * description    :
 * Customer 객체
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/15        namhyeop       최초 생성
 */
@Data
@AllArgsConstructor
public class Customer {

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final Date birthdate;
}
