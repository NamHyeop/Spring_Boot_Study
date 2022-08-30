package com.example.springbatch_8_1_flatfileitemreader;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.LineTokenizer;

/**
 * packageName    : com.example.springbatch_8_1_flatfileitemreader
 * fileName       : DefaultLineMapper
 * author         : namhyeop
 * date           : 2022/08/08
 * description    :
 * DefaultLineMapper 예제, customer의 정보를 읽고 token화 하기 위해서 필요
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/08        namhyeop       최초 생성
 */
public class DefaultLineMapper <T> implements LineMapper {

    private LineTokenizer tokenizer;
    private FieldSetMapper<T> fieldSetMapper;

    @Override
    public T mapLine(String line, int lineNumber) throws Exception{
        return fieldSetMapper.mapFieldSet(tokenizer.tokenize(line));
    }

    public void setLineTokenizer(LineTokenizer tokenizer){
        this.tokenizer = tokenizer;
    }

    public void setFieldSetMapper(FieldSetMapper<T> fieldSetMapper){
        this.fieldSetMapper = fieldSetMapper;
    }
}
