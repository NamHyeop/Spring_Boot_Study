package com.example.orderservice.repository;

import com.example.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.example.orderservice.repository
 * fileName       : OrderRepository
 * author         : namhyeop
 * date           : 2022/09/13
 * description    :
 * Order Repository
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/13        namhyeop       최초 생성
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderId(String orderId);
    Iterable<Order> findByUserId(String userId);
}
