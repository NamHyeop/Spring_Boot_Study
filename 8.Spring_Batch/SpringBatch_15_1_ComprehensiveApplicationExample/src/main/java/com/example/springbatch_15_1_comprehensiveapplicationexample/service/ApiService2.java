package com.example.springbatch_15_1_comprehensiveapplicationexample.service;

import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain.ApiInfo;
import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain.ApiResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * packageName    : com.example.springbatch_15_1_comprehensiveapplicationexample.service
 * fileName       : ApiService2
 * author         : namhyeop
 * date           : 2022/08/29
 * description    :
 * 8082에 요청을 보내는 Service
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/29        namhyeop       최초 생성
 */

@Service
public class ApiService2 extends AbstractApiService{

    @Override
    protected ApiResponseVO doApiService(RestTemplate restTemplate, ApiInfo apiInfo) {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8082/api/product/2", apiInfo, String.class);
        int statusCodeValue = responseEntity.getStatusCodeValue();
        ApiResponseVO apiResponseVO = ApiResponseVO.builder().status(statusCodeValue).msg(responseEntity.getBody()).build();

        return apiResponseVO;
    }
}
