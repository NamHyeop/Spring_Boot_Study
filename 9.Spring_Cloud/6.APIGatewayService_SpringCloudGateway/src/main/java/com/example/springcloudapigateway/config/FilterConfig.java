package com.example.springcloudapigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : com.example.springcloudapigateway.config
 * fileName       : FilterConfig
 * author         : namhyeop
 * date           : 2022/09/09
 * description    :
 * APIGateway 필터 설정 class
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/09        namhyeop       최초 생성
 */
//@Configuration
public class FilterConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r -> r.path("/first-service/**")
                        .filters(f -> f.addRequestHeader("first-request-key", "first-request-header-value")
                                    .addResponseHeader("first-response-key", "first-response-header-value"))
                        .uri("http://localhost:8071"))
                .route(r -> r.path("/second-service/**")
                        .filters(f -> f.addRequestHeader("second-request-key", "second-request-header-value")
                                .addResponseHeader("second-response-key", "second-response-header-value"))
                        .uri("http://localhost:8072"))
                .build();
    }
}
