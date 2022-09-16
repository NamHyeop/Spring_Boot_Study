package com.example.userservice.dto;

import lombok.Data;

import java.util.Date;

/**
 * packageName    : com.example.userservice.dto
 * fileName       : ResponseOrder
 * author         : namhyeop
 * date           : 2022/09/13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/13        namhyeop       최초 생성
 */
@Data
public class ResponseOrder {

    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private Date createdAt;
    private String orderId;
}
