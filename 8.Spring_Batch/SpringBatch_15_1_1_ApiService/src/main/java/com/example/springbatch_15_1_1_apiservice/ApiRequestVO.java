package com.example.springbatch_15_1_1_apiservice;

import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain.ProductVO;
import lombok.Builder;
import lombok.Data;

/**
 * packageName    : com.example.springbatch_15_1_1_apiservice
 * fileName       : ApiRequestVO
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
public class ApiRequestVO {

    private long id;
    private ProductVO productVO;
}
