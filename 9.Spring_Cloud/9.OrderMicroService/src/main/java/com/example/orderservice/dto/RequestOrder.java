package com.example.orderservice.dto;

import lombok.Data;

/**
 * packageName    : com.example.orderservice.controller
 * fileName       : RequestOrder
 * author         : namhyeop
 * date           : 2022/09/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/14        namhyeop       최초 생성
 */
@Data
public class RequestOrder {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
}
