package com.example.spring_batch_13_3_skiplistener_retrylistener.retrylistenr;

/**
 * packageName    : com.example.spring_batch_13_3_skiplistener_retrylistener
 * fileName       : CustomSkipException
 * author         : namhyeop
 * date           : 2022/08/26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/26        namhyeop       최초 생성
 */
public class CustomSkipException extends RuntimeException {

    public CustomSkipException() { super(); }

    public CustomSkipException(String message) { super(message); }
}
