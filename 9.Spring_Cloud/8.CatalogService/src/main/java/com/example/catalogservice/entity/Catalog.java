package com.example.catalogservice.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

/**
 * packageName    : com.example.catalogservice.entity
 * fileName       : CatalogEntity
 * author         : namhyeop
 * date           : 2022/09/13
 * description    :
 * Catalog Entity
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/13        namhyeop       최초 생성
 */
@Data
@Entity
@Table(name = "catalog")
public class Catalog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120, unique = true)
    private String productId;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private Integer stock;
    @Column(nullable = false)
    private Integer unitPrice;
    @Column(nullable = false, updatable = false, insertable = false)
    /**
     * DB에서 현재 시간을 조회한다
     */
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private Date createdAt;
}