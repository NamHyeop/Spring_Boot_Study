package com.example.springbatch_15_1_comprehensiveapplicationexample.batch.chunk.processor;

import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain.Product;
import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain.ProductVO;
import org.modelmapper.ModelMapper;
import org.springframework.batch.item.ItemProcessor;

/**
 * packageName    : com.example.springbatch_15_1_comprehensiveapplicationexample.batch.job.file
 * fileName       : FileItemProcessor
 * author         : namhyeop
 * date           : 2022/08/29
 * description    :
 * Reader 작업시 input(ProdcutVO)과 outPut(Product)를 매핑하는 Processor
 * 핵심 기능은 ModelMapper에서 수행한다
 * ModelMapper를 사용하려면 반드시 Dependency에 추가해야한다.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/29        namhyeop       최초 생성
 */
public class FileItemProcessor implements ItemProcessor<ProductVO, Product> {

    @Override
    public Product process(ProductVO item) throws Exception {

        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(item, Product.class);
        return product;
    }
}
