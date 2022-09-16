package com.example.catalogservice.service;

import com.example.catalogservice.Repository.CatalogRepository;
import com.example.catalogservice.entity.Catalog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.example.catalogservice.service
 * fileName       : CatalogServiceImpl
 * author         : namhyeop
 * date           : 2022/09/13
 * description    :
 * CatalogService Service 구현체
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/13        namhyeop       최초 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository repository;
    private final Environment env;

    @Override
    public Iterable<Catalog> getAllCatalogs() {
        return repository.findAll();
    }
}
