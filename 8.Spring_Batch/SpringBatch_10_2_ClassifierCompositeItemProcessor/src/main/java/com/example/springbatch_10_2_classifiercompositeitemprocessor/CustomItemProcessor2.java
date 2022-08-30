package com.example.springbatch_10_2_classifiercompositeitemprocessor;

import org.springframework.batch.item.ItemProcessor;

/**
 * packageName    : com.example.springbatch_10_2_classifiercompositeitemprocessor
 * fileName       : CustomItemProcessor1
 * author         : namhyeop
 * date           : 2022/08/16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/16        namhyeop       최초 생성
 */
public class CustomItemProcessor2 implements ItemProcessor<ProcessorInfo, ProcessorInfo> {

    @Override
    public ProcessorInfo process(ProcessorInfo processorInfo) throws Exception {
        System.out.println("Checked CustomItemProcessor2");
        System.out.println("=============================");
        return processorInfo;
    }
}
