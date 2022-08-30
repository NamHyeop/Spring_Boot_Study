package com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * packageName    : com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain
 * fileName       : ApiInfo
 * author         : namhyeop
 * date           : 2022/08/29
 * description    :
 * ApiRequestVO를 감싸는 용도
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/29        namhyeop       최초 생성
 */
@Data
@Builder
public class ApiInfo {
    private String url;
    private List< ? extends ApiRequestVO> apiRequestList;
}
