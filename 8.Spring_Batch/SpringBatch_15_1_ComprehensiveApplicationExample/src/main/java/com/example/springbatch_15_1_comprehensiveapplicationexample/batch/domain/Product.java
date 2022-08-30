package com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * packageName    : com.example.springbatch_15_1_comprehensiveapplicationexample.batch.job.file
 * fileName       : Product
 * author         : namhyeop
 * date           : 2022/08/29
 * description    :
 * Jpa Entity
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/29        namhyeop       최초 생성
 */

@Entity
@Data
public class Product {
    @Id
    private Long id;
    private String name;
    private int price;
    private String type;
}
