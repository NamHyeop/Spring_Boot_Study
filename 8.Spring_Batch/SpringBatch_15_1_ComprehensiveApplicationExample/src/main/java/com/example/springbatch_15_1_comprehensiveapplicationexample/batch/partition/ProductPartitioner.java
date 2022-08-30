package com.example.springbatch_15_1_comprehensiveapplicationexample.batch.partition;

import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain.ProductVO;
import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.job.api.QueryGenerator;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : com.example.springbatch_15_1_comprehensiveapplicationexample.batch.partition
 * fileName       : ProductPartioner
 * author         : namhyeop
 * date           : 2022/08/29
 * description    :
 * Partion 작업을 할당할 Thread 생성 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/29        namhyeop       최초 생성
 */
public class ProductPartitioner implements Partitioner {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        ProductVO[] productList = QueryGenerator.getProductList(dataSource);
        HashMap<String, ExecutionContext> result = new HashMap<>();

        int number = 0;

        for(int i = 0; i < productList.length; i++){
            ExecutionContext value = new ExecutionContext();
            result.put("partition" + number, value);
            value.put("product", productList[i]);
            number++;
        }
        return result;
    }
}
