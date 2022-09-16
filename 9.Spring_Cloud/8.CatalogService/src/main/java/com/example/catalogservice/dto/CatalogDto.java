package com.example.catalogservice.dto;

import lombok.Data;

/**
 * packageName    : com.example.catalogservice.dto
 * fileName       : CatalogDto
 * author         : namhyeop
 * date           : 2022/09/13
 * description    :
 * Controller 영역에서 사용되는 CatalogDto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/13        namhyeop       최초 생성
 */
@Data
public class CatalogDto {

    private String productId;
    //수량
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;

    private String orderId;
    private String userId;
}

