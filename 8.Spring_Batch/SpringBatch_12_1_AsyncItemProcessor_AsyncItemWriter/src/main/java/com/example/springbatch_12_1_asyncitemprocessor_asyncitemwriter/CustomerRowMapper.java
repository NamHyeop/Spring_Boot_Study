package com.example.springbatch_12_1_asyncitemprocessor_asyncitemwriter;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * packageName    : com.example.springbatch_12_1_asyncitemprocessor_asyncitemwriter
 * fileName       : CustomerRowMapper
 * author         : namhyeop
 * date           : 2022/08/19
 * description    :
 * DB 값을 매핑해주는 rowmapper
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/19        namhyeop       최초 생성
 */
public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Customer(rs.getLong("id"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getDate("birthdate"));
    }
}
