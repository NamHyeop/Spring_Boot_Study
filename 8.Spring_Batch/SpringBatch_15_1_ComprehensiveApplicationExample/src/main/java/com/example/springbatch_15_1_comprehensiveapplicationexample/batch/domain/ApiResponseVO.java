package com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain;

import lombok.Builder;
import lombok.Data;

/**
 * packageName    : com.example.springbatch_15_1_comprehensiveapplicationexample.service
 * fileName       : ApiResponseVO
 * author         : namhyeop
 * date           : 2022/08/29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/29        namhyeop       최초 생성
 */

@Data
@Builder
public class ApiResponseVO {

    private int status;
    private String msg;
}
