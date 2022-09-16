package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entity.Order;

/**
 * packageName    : com.example.Orderservice.service
 * fileName       : OrderService
 * author         : namhyeop
 * date           : 2022/09/13
 * description    :
 * OrderService 인터페이스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/13        namhyeop       최초 생성
 */
public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<Order> getOrderByUserId(String userId);
}
