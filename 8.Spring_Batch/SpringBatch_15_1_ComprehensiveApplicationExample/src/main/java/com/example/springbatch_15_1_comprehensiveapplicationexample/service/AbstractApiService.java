package com.example.springbatch_15_1_comprehensiveapplicationexample.service;

import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain.ApiInfo;
import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain.ApiRequestVO;
import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain.ApiResponseVO;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpHead;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

/**
 * packageName    : com.example.springbatch_15_1_comprehensiveapplicationexample.service
 * fileName       : AbstractApiService
 * author         : namhyeop
 * date           : 2022/08/29
 * description    :
 * Rest 요청을 보내는 기능을 가지는 추상클래스 선언, doApiService만 재정의하면된다.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/29        namhyeop       최초 생성
 */
@Service
public abstract class AbstractApiService {

    public ApiResponseVO service(List<? extends ApiRequestVO> apiRequest){

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.errorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {

            }
        }).build();

        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        ApiInfo apiInfo = ApiInfo.builder().apiRequestList(apiRequest).build();

        return doApiService(restTemplate, apiInfo);
    }

    protected abstract ApiResponseVO doApiService(RestTemplate restTemplate, ApiInfo apiInfo);
}
