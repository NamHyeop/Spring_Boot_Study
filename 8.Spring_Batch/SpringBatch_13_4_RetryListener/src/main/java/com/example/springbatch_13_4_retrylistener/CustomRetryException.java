package com.example.springbatch_13_4_retrylistener;

/**
 * packageName    : com.example.spring_batch_13_3_skiplistener_retrylistener.skiplistener
 * fileName       : CustomRetryException
 * author         : namhyeop
 * date           : 2022/08/26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/26        namhyeop       최초 생성
 */
public class CustomRetryException extends Exception {

    public CustomRetryException(String msg) {
        super(msg);
    }

}
