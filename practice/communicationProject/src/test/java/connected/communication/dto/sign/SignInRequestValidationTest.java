package connected.communication.dto.sign;


import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class SignInRequestValidationTest {
    /**
     * 검증 작업을 위한 Validator 생성
     */
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void validateTest() throws Exception{
        //given
        SignInRequest req = createRequest();
        //when
        /**
         * 검증 작업을 수행한다. 제약 조건을 위한한 내용이 있따면 Set<ConstratintViolation<SignRequest>>에 담기게 된다.
         */
        Set<ConstraintViolation<SignInRequest>> validate = validator.validate(req);
        /**
         * 제약조건이 올바르다면 사이즈가 비어있다.
         */
        //then
        assertThat(validate).isEmpty();
    }

    @Test
    public void invalidateByNotFormattedEmailTest() throws Exception{
        //given
        String invalidValue = "email";
        SignInRequest req = createRequestWithEmail(invalidValue);
        //when
        Set<ConstraintViolation<SignInRequest>> validate = validator.validate(req);
        //then
        /**
         * 제약조건을 위반했기에 validate가 비어있지 않다.
         */
        assertThat(validate).isNotEmpty();
        /**
         * 제약 조건을 위반한 객체를 확인하여 given에서 설정해두었던 위반된 값을 가지고 있는지 확인한다.
         */
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(Collectors.toSet())).contains(invalidValue);
    }

    @Test
    public void invalidateByEmptyEmailTest() throws Exception{
        //given
        String invalidValue = null;
        SignInRequest req = createRequestWithEmail(invalidValue);
        //when
        Set<ConstraintViolation<SignInRequest>> validate = validator.validate(req);
        //then
        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(Collectors.toSet())).contains(invalidValue);
    }

    @Test
    public void invalidateByEmptyPasswordTest() throws Exception{
        //given
        String invalidValue = null;
        SignInRequest req = createRequestWithPassword(invalidValue);
        //when
        Set<ConstraintViolation<SignInRequest>> validate = validator.validate(req);
        //then
        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(Collectors.toSet())).contains(invalidValue);
    }

    @Test
    public void invalidateByBlankPasswordTest() throws Exception{
        //given
        String invalidValue = "";
        SignInRequest req = createRequestWithPassword(invalidValue);
        //when
        Set<ConstraintViolation<SignInRequest>> validate = validator.validate(req);
        //then
        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(Collectors.toSet())).contains(invalidValue);
    }

    /**
     * 정상적인 요청 객체를 생성하는 팩토리 메소드.
     */
    private SignInRequest createRequest(){
        return new SignInRequest("email@google.com", "!asd123456");
    }
    /**
     * 전달 받은 email 필드 외에는 정상적인 요청 객체를 생성하는 팩토리 메소드.
     */
    private SignInRequest createRequestWithEmail(String email){
        return new SignInRequest(email, "!asd123456!");
    }
    /**
     * 전달 받은 password 필드 외에는 정상적인 요청 객체를 생성하는 팩토리 메소드.
     */
    private SignInRequest createRequestWithPassword(String password){
        return new SignInRequest("email@google.com", password);
    }
}