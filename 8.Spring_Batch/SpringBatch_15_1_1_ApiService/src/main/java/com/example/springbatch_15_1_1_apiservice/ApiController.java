package com.example.springbatch_15_1_1_apiservice;

import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain.ProductVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName    : com.example.springbatch_15_1_1_apiservice
 * fileName       : ApiController
 * author         : namhyeop
 * date           : 2022/08/30
 * description    :
 * Batch에서 DB의 데이터를 읽은 뒤 API 호출을 할 때 서버가 필요한데 ApiController.class가 서버의 컨트롤러이다.
 * 하나의 메소드를 하나의 서버라고 가정한것이다
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/30        namhyeop       최초 생성
 */
@RestController
public class ApiController {

    @PostMapping("/api/product/1")
    public String product1(@RequestBody ApiInfo apiInfo){
        List<ProductVO> productVOList = apiInfo.getApiRequestList().stream().map(item -> item.getProductVO()).collect(Collectors.toList());
        System.out.println("productVOList= " + productVOList);
        return "product1 was successfully processed";
    }

    @PostMapping("/api/product/2")
    public String product2(@RequestBody ApiInfo apiInfo){
        List<ProductVO> productVOList = apiInfo.getApiRequestList().stream().map(item -> item.getProductVO()).collect(Collectors.toList());
        System.out.println("productVOList= " + productVOList);
        return "product2 was successfully processed";
    }

    @PostMapping("/api/product/3")
    public String product3(@RequestBody ApiInfo apiInfo){
        List<ProductVO> productVOList = apiInfo.getApiRequestList().stream().map(item -> item.getProductVO()).collect(Collectors.toList());
        System.out.println("productVOList= " + productVOList);
        return "product3 was successfully processed";
    }
}
