package com.example.springbatch_12_5_synchronizeditemstreamreader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * packageName    : com.example.springbatch_12_5_synchronizeditemstreamreader
 * fileName       : Customer
 * author         : namhyeop
 * date           : 2022/08/24
 * description    :
 * Customer Dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/24        namhyeop       최초 생성
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private long id;
    private String firstName;
    private String lastName;
    private Date birthdate;
}
