package com.example.springbatch_8_8_jpacursoritemreader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * packageName    : com.example.springbatch_8_8_jpacursoritemreader
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
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id @GeneratedValue
    private Long id;
    private String firstname;
    private String lastname;
    private String birthdate;
}
