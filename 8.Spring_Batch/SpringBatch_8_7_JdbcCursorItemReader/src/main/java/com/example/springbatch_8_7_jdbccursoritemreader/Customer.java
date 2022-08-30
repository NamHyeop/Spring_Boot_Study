package com.example.springbatch_8_7_jdbccursoritemreader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.example.springbatch_8_7_jdbccursoritemreader
 * fileName       : Customer
 * author         : namhyeop
 * date           : 2022/08/12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/12        namhyeop       최초 생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private Long Id;
    private String firstName;
    private String lastName;
    private String birthdate;

}
