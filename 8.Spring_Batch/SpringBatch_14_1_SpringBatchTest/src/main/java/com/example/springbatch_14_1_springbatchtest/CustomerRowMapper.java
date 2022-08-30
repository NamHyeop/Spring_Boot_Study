package com.example.springbatch_14_1_springbatchtest;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * packageName    : com.example.springbatch_14_1_springbatchtest
 * fileName       : CustomerRowMapper
 * author         : namhyeop
 * date           : 2022/08/26
 * description    :
 * DB 데이터를 읽고 매핑하는 RowMapper
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/26        namhyeop       최초 생성
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
