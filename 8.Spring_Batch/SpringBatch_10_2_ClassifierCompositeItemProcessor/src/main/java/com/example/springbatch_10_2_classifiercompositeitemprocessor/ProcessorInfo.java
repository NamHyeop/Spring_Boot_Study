package com.example.springbatch_10_2_classifiercompositeitemprocessor;

import lombok.Builder;
import lombok.Data;

/**
 * packageName    : com.example.springbatch_10_2_classifiercompositeitemprocessor
 * fileName       : ProcessorInfo
 * author         : namhyeop
 * date           : 2022/08/16
 * description    :
 * 조건에 따른 ItemProcessor를 선택하는 기준이 되는 Class
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/16        namhyeop       최초 생성
 */
@Data
@Builder
public class ProcessorInfo {
    private int id;
}
