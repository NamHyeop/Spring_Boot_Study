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
    //중요. 정상 흐름이 반환되기 때문에 이런 방식으로 응답할거면 응답 코드가 정상 200이 반환됨, 응답 코드도 400을 요청하고 싶으면 @ResponseStatus를 설정해야함
    //200이 반환되는 이뉴는 ModelAndView로 객체를 반환하기때문에 정상 프로세스로 종료되기 때문이다.
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
    //위와 아래는 같다. 매개변수의 자료형이 기본값으로 설정되어 있다.
//    @ExceptionHandler(UserException.class)
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
