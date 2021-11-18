package hello.exception.exhandler.advice;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//RestControllerAdvice는 @ControllerAdvice + @ResponseBody이다
@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {
    //IllegalArgumentException 발생시 예외 처리
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandle(IllegalArgumentException e){
        log.error("[exceptionHandle] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }


    //UserException 발생시 예외 처리
    //위와 다르게 동적으로 응답메시지를 바꿔서 반환이 가능함. 조건문 사용이 가능하기 때문에
    //ExceptionHandler의 ()매개변수는 메소드의 매개변수로 생략이 가능함. 위의 illegalExhandle 메소드랑 비교해서 보면된다.
    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandle(UserException e){
        log.error("[exceptionHandle] ex", e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity<>(errorResult,  HttpStatus.BAD_REQUEST);
    }

    //Exception은 다른 Exception 오류들의 최상위 부모이다.
    //즉 해결하지 못한 예외 발생시 exHandle이 동작한다.
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandle(Exception e){
        log.error("[exceptionHandle] ex", e);
        return new ErrorResult("EX", "내부 오류");
    }
}
