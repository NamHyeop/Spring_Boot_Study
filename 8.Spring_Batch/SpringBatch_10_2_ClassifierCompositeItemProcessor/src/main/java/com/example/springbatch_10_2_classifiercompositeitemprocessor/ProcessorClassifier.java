package com.example.springbatch_10_2_classifiercompositeitemprocessor;

import org.apache.tomcat.jni.Proc;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.classify.Classifier;

import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : com.example.springbatch_10_2_classifiercompositeitemprocessor
 * fileName       : ProcessorClassifier
 * author         : namhyeop
 * date           : 2022/08/16
 * description    :
 * Processor를 등록해주는 역할
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/16        namhyeop       최초 생성
 */
public class ProcessorClassifier<C, T> implements Classifier<C,T> {

    private Map<Integer, ItemProcessor<ProcessorInfo,ProcessorInfo>> processorMap = new HashMap<>();

    @Override
    public T classify(C classifiable) {
        return (T)processorMap.get(((ProcessorInfo)classifiable).getId());
    }

    public void setProcessorMap(Map<Integer, ItemProcessor<ProcessorInfo, ProcessorInfo>> processorMap) {
        this.processorMap = processorMap;
    }
}
