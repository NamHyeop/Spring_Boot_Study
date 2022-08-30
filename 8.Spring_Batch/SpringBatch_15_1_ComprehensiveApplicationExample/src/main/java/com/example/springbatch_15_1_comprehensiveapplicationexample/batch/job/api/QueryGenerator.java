package com.example.springbatch_15_1_comprehensiveapplicationexample.batch.job.api;

import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain.ProductVO;
import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.rowmapper.ProductRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName    : com.example.springbatch_15_1_comprehensiveapplicationexample.batch.job.api
 * fileName       : QueryGenerator
 * author         : namhyeop
 * date           : 2022/08/29
 * description    :
 * type에 대한 종류를 구하기 위한 쿼리를 실행하는 class
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/29        namhyeop       최초 생성
 */
public class QueryGenerator {

    public static ProductVO[] getProductList(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //type만 필요하므로 익명 객체를 사용해서 type만 가져온다.
        List<ProductVO> productList = jdbcTemplate.query("select type from product group by type", new ProductRowMapper() {
            @Override
            public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
                return ProductVO.builder().type(rs.getString("type")).build();
            }
        });
        return productList.toArray(new ProductVO[]{});
    }

    public static Map<String,Object> getParameterForQuery(String parameter, String value){
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put(parameter, value);
        return parameters;
    }
}


