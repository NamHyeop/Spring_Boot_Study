package com.example.catalogservice.Repository;

import com.example.catalogservice.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.example.catalogservice.entity
 * fileName       : CatalogRepository
 * author         : namhyeop
 * date           : 2022/09/13
 * description    :
 * Catalog Repository
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/13        namhyeop       최초 생성
 */
public interface CatalogRepository extends JpaRepository<Catalog, Long> {
    Catalog findByProductId(String productId);
}
