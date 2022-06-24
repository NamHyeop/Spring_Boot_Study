package connected.communication.advice;//package connected.communication.advice;

import connected.communication.dto.MemberEmailAlreadyExistsException;
import connected.communication.dto.MemberNicknameAlreadyExistsException;
import connected.communication.dto.response.Response;
import connected.communication.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
/**
 * ExceptionHandler에 예외 클래스를 지정해주면, 실행 중 지정한 예외가 발생시 해당 메소드를 실행해준다.
 */
public class ExceptionAdvice {
    /**
     * 의도하지 않은 예외 발생시(현재 예시 기준 Exception.class 오류를 의미) 오류 큐드와 오류메시지를 같이 응답하게 함.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response exception(Exception e){
        log.info("e = {}", e.getMessage());
        return Response.failure(-1000,"오류가 발생하였습니다.");
    }

    /**
     * 인가되지 않은 사용자가 접근할 경우 응답 401번 응답
     */
    @ExceptionHandler(AuthenticationEntryPointException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Response authenticationEntryPoint(){
        return Response.failure(-1001, "인증되지 않은 사용자입니다.");
    }

    /**
     * 인가되지 않은 사용자가 접근할 경우 응답 401번 응답
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Response accessDeniedException(){
        return Response.failure(-1002, "접근이 거부 되었습니다.");
    }

    /**
     * validation 수행시 MethodArgumentNotValidException이 발생한다.
     * 각 검증 어노테이션 별로 지정해놨던 메시지를 응답해준다.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response methodArgumentNotValidException(MethodArgumentNotValidException e){
        return Response.failure(-1003, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * 아이디 또는 비밀번호 오류로 로그인에 실패할 경우 401 응답을 내려준다
     */
    @ExceptionHandler(LoginFailureException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Response loginFailureException(){
        return Response.failure(-1004, "로그인에 실패하였습니다.");
    }

    /**
     * 닉네임 또는 이메일 중복이 발생했다면, 409 응답 반환
     */
    @ExceptionHandler(MemberEmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Response memberEmailAlreadyExistsException(MemberEmailAlreadyExistsException e){
        return Response.failure(-1005, e.getMessage() + "은 중복된 이메일입니다.");
    }

    /**
     * 닉네임 또는 이메일 중복이 발생했다면, 409 응답을 반환한다.
     */
    @ExceptionHandler(MemberNicknameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Response memberNicknameAlreadyExistsException(MemberNicknameAlreadyExistsException e){
        return Response.failure(-1006, e.getMessage() + "은 중복된 닉네임 입니다.");
    }

    /**
     * 요청한 자원을 찾을 수 없다면, 404 응답을 반환한다.
     */
    @ExceptionHandler(MemberNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response MemberNotFoundException(){
        return Response.failure(-1007,"요청한 회원을 찾을 수 없습니다.");
    }

    /**
     * 요청한 자원을 찾을 수 없다면, 404 응답을 반환한다.
     */
    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response roleNotFoundException(){
        return Response.failure(-1008, "요청한 권한 등급을 찾을 수 없다.");
    }
}