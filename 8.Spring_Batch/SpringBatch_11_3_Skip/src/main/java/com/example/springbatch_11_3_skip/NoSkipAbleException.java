package com.example.springbatch_11_3_skip;

/**
 * packageName    : com.example.springbatch_11_3_skip
 * fileName       : SkipAbleException
 * author         : namhyeop
 * date           : 2022/08/16
 * description    :
 * Exception.class
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/16        namhyeop       최초 생성
 */
public class NoSkipAbleException extends Exception {
    public NoSkipAbleException(String s) {
        super(s);
    }
}
