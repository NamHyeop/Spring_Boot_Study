package com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.example.springbatch_15_1_comprehensiveapplicationexample.batch.job.file
 * fileName       : ProductV0
 * author         : namhyeop
 * date           : 2022/08/29
 * description    :
 * Product Dto, Read 용도
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/29        namhyeop       최초 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVO {

    private Long id;
    private String name;
    private int price;
    private String type;
}
