package com.example.springbatch_9_4_jsonfileitemwriter;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * packageName    : com.example.springbatch_9_4_jsonfileitemwriter
 * fileName       : Cu
 * author         : namhyeop
 * date           : 2022/08/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/14        namhyeop       최초 생성
 */
@Data
@AllArgsConstructor
public class Customer {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final Date birthdate;
}
