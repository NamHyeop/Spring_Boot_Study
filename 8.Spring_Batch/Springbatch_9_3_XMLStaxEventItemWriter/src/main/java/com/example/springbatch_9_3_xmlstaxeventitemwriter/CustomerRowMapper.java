package com.example.springbatch_9_3_xmlstaxeventitemwriter;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * packageName    : com.example.springbatch_9_3_xmlstaxeventitemwriter
 * fileName       : CustomerRowMapper
 * author         : namhyeop
 * date           : 2022/08/14
 * description
 * DB의 정보를 객체로 매핑 시켜주는 RowMapper
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/14        namhyeop       최초 생성
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
