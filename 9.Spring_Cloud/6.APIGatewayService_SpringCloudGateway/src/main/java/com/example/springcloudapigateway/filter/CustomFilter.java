package com.example.springcloudapigateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * packageName    : com.example.springcloudapigateway.filter
 * fileName       : CustomFilter
 * author         : namhyeop
 * date           : 2022/09/09
 * description    :
 * APIGateway Filter에 CustomFilter를 넣는 예시
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/09        namhyeop       최초 생성
 */
@Slf4j
@Component
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {
    public CustomFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            //(1) 현재 구간이 Preprocess 필터 작업 구간
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Custom PRE filter: request uri ->? {}", request.getId());
            //===============================================
            //(2) 현재 구간이 Post process 필터 작업 구간, return 값이 사실상 PostProcess 값이다.
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                log.info("Custom POST filter: response code -> {}", response.getStatusCode());
            }));
        });
    }

    public static class Config{
        //이 구간에 설정 속성들을 넣으면 된다.
    }
}
