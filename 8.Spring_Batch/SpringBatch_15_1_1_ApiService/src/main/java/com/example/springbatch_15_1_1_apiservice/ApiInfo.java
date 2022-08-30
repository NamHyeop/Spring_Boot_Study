package com.example.springbatch_15_1_1_apiservice;

import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain.ApiRequestVO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * packageName    : com.example.springbatch_15_1_1_apiservice
 * fileName       : ApiInfo
 * author         : namhyeop
 * date           : 2022/08/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/30        namhyeop       최초 생성
 */
@Data
@Builder
public class ApiInfo {

    private String url;
    private List<? extends ApiRequestVO> apiRequestList;
}
