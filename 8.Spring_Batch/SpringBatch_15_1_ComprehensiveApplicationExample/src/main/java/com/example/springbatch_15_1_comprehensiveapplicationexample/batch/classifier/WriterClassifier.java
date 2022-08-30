package com.example.springbatch_15_1_comprehensiveapplicationexample.batch.classifier;

import com.example.springbatch_15_1_comprehensiveapplicationexample.batch.domain.ApiRequestVO;
import org.springframework.batch.item.ItemWriter;
import org.springframework.classify.Classifier;

import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : com.example.springbatch_15_1_comprehensiveapplicationexample.batch.classifier
 * fileName       : WriterClassifier
 * author         : namhyeop
 * date           : 2022/08/29
 * description    :
 * Writer 종류에 맞게 구분해주는 class
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/29        namhyeop       최초 생성
 */
public class WriterClassifier<C,T> implements Classifier<C,T> {

    private Map<String, ItemWriter<ApiRequestVO>> writerMap = new HashMap<>();

    @Override
    public T classify(C classifiable) {
        return (T) writerMap.get(((ApiRequestVO)classifiable).getProductVO().getType());
    }

    public void setWriterMap(Map<String, ItemWriter<ApiRequestVO>> writerMap){
        this.writerMap = writerMap;
    }
}
