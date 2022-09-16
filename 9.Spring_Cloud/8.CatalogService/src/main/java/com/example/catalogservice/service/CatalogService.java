package com.example.catalogservice.service;

import com.example.catalogservice.entity.Catalog;

/**
 * packageName    : com.example.catalogservice.service
 * fileName       : CatalogService
 * author         : namhyeop
 * date           : 2022/09/13
 * description    :
 * CatalogService 인터페이스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/13        namhyeop       최초 생성
 */
public interface CatalogService {
    Iterable<Catalog> getAllCatalogs();
}
