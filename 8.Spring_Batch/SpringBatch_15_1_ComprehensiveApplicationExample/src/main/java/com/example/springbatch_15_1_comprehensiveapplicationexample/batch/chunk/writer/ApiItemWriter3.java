package com.example.springbatch_15_1_comprehensiveapplicationexample.batch.chunk.writer;

import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain.ApiRequestVO;
import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain.ApiResponseVO;
import com.example.springbatch_15_1_comprehensiveapplicationexample.service.AbstractApiService;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;

import java.util.List;

/**
 * packageName    : com.example.springbatch_15_1_comprehensiveapplicationexample.batch.chunk.writer
 * fileName       : ApiItemWriter1
 * author         : namhyeop
 * date           : 2022/08/29
 * description    :
 * 8083에서 읽은 정보를 파일로 만드는 class
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/29        namhyeop       최초 생성
 */
public class ApiItemWriter3 extends FlatFileItemWriter<ApiRequestVO> {

    private final AbstractApiService apiService;

    public ApiItemWriter3(AbstractApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void write(List<? extends ApiRequestVO> items) throws Exception {

        ApiResponseVO responseVO = apiService.service(items);
        System.out.println("responseVO = " + responseVO);

        //응답 값을 반환 객체 items에 저장
        items.forEach(item -> item.setApiResponseVO(responseVO));

        super.setResource(new FileSystemResource("/Users/namhyeop/Desktop/git자료/Spring_Boot_Study/8.Spring_Batch/SpringBatch_15_1_ComprehensiveApplicationExample/src/main/resources/product3.txt"));
        super.open(new ExecutionContext());
        //구분자 방식 사용
        super.setLineAggregator(new DelimitedLineAggregator<>());
        super.setAppendAllowed(true);
        super.write(items);
    }
}
