package com.example.springbatch_12_2_multithreade_step;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * packageName    : com.example.springbatch_12_2_multithreade_step
 * fileName       : Customer
 * author         : namhyeop
 * date           : 2022/08/21
 * description    :
 * Customer Dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/21        namhyeop       최초 생성
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private Long id;
    private String firstName;
    private String lastName;
    private Date birthdate;
}
