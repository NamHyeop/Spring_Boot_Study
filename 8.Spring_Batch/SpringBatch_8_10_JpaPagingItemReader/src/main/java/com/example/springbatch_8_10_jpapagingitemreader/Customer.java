package com.example.springbatch_8_10_jpapagingitemreader;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * packageName    : com.example.springbatch_8_10_jpapagingitemreader
 * fileName       : Customer
 * author         : namhyeop
 * date           : 2022/08/12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/12        namhyeop       최초 생성
 */
@Getter
@Setter
@Entity
public class Customer {
    @Id @GeneratedValue
    private Long id;
    private String username;
    private Long age;

    @OneToOne(mappedBy = "customer")
    private Address address;
}
