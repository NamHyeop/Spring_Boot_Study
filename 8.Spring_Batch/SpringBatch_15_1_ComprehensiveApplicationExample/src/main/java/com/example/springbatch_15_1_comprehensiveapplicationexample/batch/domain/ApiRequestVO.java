package com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain;

import lombok.Builder;
import lombok.Data;

/**
 * packageName    : com.example.springbatch_15_1_comprehensiveapplicationexample.batch.job.api
 * fileName       : ApiRequestVO
 * author         : namhyeop
 * date           : 2022/08/29
 * description    :
 * ProductV0를 바로 전달해도 상관없지만 제품에 대한 정보이기 때문에 한 번더 ApiRequestVO에 감싸서 전달한다.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/29        namhyeop       최초 생성
 */

@Data
@Builder
public class ApiRequestVO {

    private long id;
    private ProductVO productVO;
    private ApiResponseVO apiResponseVO;
}
