package com.example.springbatch_15_1_comprehensiveapplicationexample.batch.classifier;

import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain.ApiRequestVO;
import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain.ProductVO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.classify.Classifier;

import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : com.example.springbatch_15_1_comprehensiveapplicationexample.batch.classfier
 * fileName       : ProcessClassifier
 * author         : namhyeop
 * date           : 2022/08/29
 * description    :
 * Classifier를 통해 type의 숫자에 따라 숫자와 같은 Thread에 작업이 떨어지도록 구분해주는 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/29        namhyeop       최초 생성
 */
public class ProcessorClassifier<C,T> implements Classifier<C, T> {

    private Map<String , ItemProcessor<ProductVO, ApiRequestVO>> processorMap = new HashMap<>();

    //classifiable에는 ProductVO가 들어오게된다, getType을 통해 1,2,3번인지 구분한다.
    @Override
    public T classify(C classifiable) {
        return (T)processorMap.get(((ProductVO)classifiable).getType());
    }

    public void setProcessorMap(Map<String, ItemProcessor<ProductVO, ApiRequestVO>> processorMap) {
        this.processorMap = processorMap;
    }
}
