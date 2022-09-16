package com.example.orderservice.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

/**
 * packageName    : com.example.orderservice.entity
 * fileName       : Order
 * author         : namhyeop
 * date           : 2022/09/13
 * description    :
 * Order Entity
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/13        namhyeop       최초 생성
 */
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120, unique = true)
    private String productId;
    @Column(nullable = false)
    private Integer qty;
    @Column(nullable = false)
    private Integer unitPrice;
    @Column(nullable = false)
    private Integer totalPrice;

    @Column(nullable = false)
    private String userId;
    @Column(nullable = false, unique = true)
    private String orderId;
    @Column(nullable = false, updatable = false, insertable = false)
    /**
     * ColumnDefault는 DB에서 현재 시간을 조회한다
     */
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private Date createdAt;
}
