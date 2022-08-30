package com.example.springbatch_12_2_multithreade_step;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * packageName    : com.example.springbatch_12_2_multithreade_step
 * fileName       : CustomerRowMapper
 * author         : namhyeop
 * date           : 2022/08/21
 * description    :
 * RowMapper 구현체, DB 값 매칭 용도
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/21        namhyeop       최초 생성
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
