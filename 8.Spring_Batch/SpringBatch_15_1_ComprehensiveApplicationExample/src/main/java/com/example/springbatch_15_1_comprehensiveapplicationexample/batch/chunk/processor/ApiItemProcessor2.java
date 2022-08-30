package com.example.springbatch_15_1_comprehensiveapplicationexample.batch.chunk.processor;

import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain.ApiRequestVO;
import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain.ProductVO;
import org.springframework.batch.item.ItemProcessor;

/**
 * packageName    : com.example.springbatch_15_1_comprehensiveapplicationexample.batch.chunk.processor
 * fileName       : ApiItemProcessor2
 * author         : namhyeop
 * date           : 2022/08/29
 * description    :
 * 2번 Thread가 접근하는 Processor
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/29        namhyeop       최초 생성
 */

public class ApiItemProcessor2 implements ItemProcessor<ProductVO, ApiRequestVO> {

    @Override
    public ApiRequestVO process(ProductVO item) throws Exception {
        return ApiRequestVO.builder()
                .id(item.getId())
                .productVO(item)
                .build();
    }
}
