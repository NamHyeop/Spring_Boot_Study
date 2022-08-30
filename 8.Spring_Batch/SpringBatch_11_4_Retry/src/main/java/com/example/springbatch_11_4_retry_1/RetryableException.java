package com.example.springbatch_11_4_retry_1;

/**
 * packageName    : com.example.springbatch_11_4_retry_1
 * fileName       : RetryableException
 * author         : namhyeop
 * date           : 2022/08/16
 * description    :
 * Exception file
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/16        namhyeop       최초 생성
 */
public class RetryableException extends RuntimeException {

    public RetryableException(){
        super();
    }
    public RetryableException(String message){
        super(message);
    }
}
