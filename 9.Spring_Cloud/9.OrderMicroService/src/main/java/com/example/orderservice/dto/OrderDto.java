package com.example.orderservice.dto;

import lombok.Data;

/**
 * packageName    : com.example.orderservice.dto
 * fileName       : OrderDto
 * author         : namhyeop
 * date           : 2022/09/13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/13        namhyeop       최초 생성
 */
@Data
public class OrderDto {

    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;

    private String orderId;
    private String userId;
}
