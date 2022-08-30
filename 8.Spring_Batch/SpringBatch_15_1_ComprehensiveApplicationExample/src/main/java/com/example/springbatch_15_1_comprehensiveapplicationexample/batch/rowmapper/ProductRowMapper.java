package com.example.springbatch_15_1_comprehensiveapplicationexample.batch.rowmapper;

import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain.ProductVO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * packageName    : com.example.springbatch_15_1_comprehensiveapplicationexample.batch.rowmapper
 * fileName       : ProductRowMapper
 * author         : namhyeop
 * date           : 2022/08/29
 * description    :
 * JDBC를 통해 DB 데이터를 읽어서 ProductVO DTO에 매핑할 때 사용
 * ==================================스=========================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/29        namhyeop       최초 생성
 */
public class ProductRowMapper implements RowMapper<ProductVO> {

    @Override
    public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ProductVO.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .price(rs.getInt("price"))
                .type(rs.getString("type"))
                .build();
    }
}
