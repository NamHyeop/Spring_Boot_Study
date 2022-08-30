package com.example.springbatch_8_10_jpapagingitemreader;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * packageName    : com.example.springbatch_8_10_jpapagingitemreader
 * fileName       : Address
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
@ToString
public class Address {
    @Id @GeneratedValue
    private Long id;
    private String location;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
