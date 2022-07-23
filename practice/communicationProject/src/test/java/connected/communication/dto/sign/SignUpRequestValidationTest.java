package connected.communication.dto.sign;

import connected.communication.factory.dto.SignUpRequestFactory;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static connected.communication.factory.dto.SignUpRequestFactory.createSignUpRequest;
import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.assertThat;

class SignUpRequestValidationTest {
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    @Test
    public void validateTest() throws Exception{
        //given
        SignUpRequest req = createSignUpRequest();
        //when
        Set<ConstraintViolation<SignUpRequest>> validate = validator.validate(req);
        //then
        assertThat(validate).isEmpty();
    }

    @Test
    public void invalidateByNotFormattedEmailTest() throws Exception{
        //given
        String invalidValue = "email";
        SignUpRequest req = SignUpRequestFactory.createSignUpRequestWithEmail(invalidValue);
        //when
        Set<ConstraintViolation<SignUpRequest>> validate = validator.validate(req);
        //then
        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(toSet())).contains(invalidValue);
    }

    @Test
    public void invalidateByEmptyEmailTest() throws Exception{
        //given
        String invalidValue = null;
        SignUpRequest req = SignUpRequestFactory.createSignUpRequestWithEmail(invalidValue);
        //when
        Set<ConstraintViolation<SignUpRequest>> validate = validator.validate(req);
        //then
        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(toSet())).contains(invalidValue);
    }

    @Test
    public void invalidateByBlankEmailTest() throws Exception{
        //given
        String invalidValue = "";
        SignUpRequest req = SignUpRequestFactory.createSignUpRequestWithEmail(invalidValue);
        //when
        Set<ConstraintViolation<SignUpRequest>> validate = validator.validate(req);
        //then
        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(toSet())).contains(invalidValue);
    }

    @Test
    public void invalidateByEmptyPasswordTest() throws Exception{
        //given
        String invalidValue = null;
        SignUpRequest req = SignUpRequestFactory.createSignUpRequestWithEmail(invalidValue);
        //when
        Set<ConstraintViolation<SignUpRequest>> validate = validator.validate(req);
        //then
        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(toSet())).contains(invalidValue);
    }

    @Test
    public void invalidateByBlankPasswordTest() throws Exception{
        //given
        String invalidValue = "";
        SignUpRequest req = SignUpRequestFactory.createSignUpRequestWithEmail(invalidValue);
        //when
        Set<ConstraintViolation<SignUpRequest>> validate = validator.validate(req);
        //then
        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(toSet())).contains(invalidValue);
    }

    @Test
    public void invalidateByShortPasswordTest() throws Exception{
        //given
        String invalidValue = "!asd12";
        SignUpRequest req = SignUpRequestFactory.createSignUpRequestWithEmail(invalidValue);
        //when
        Set<ConstraintViolation<SignUpRequest>> validate = validator.validate(req);
        //then
        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(toSet())).contains(invalidValue);
    }

    @Test
    void invalidateByNoneAlphabetPasswordTest() {
        // given
        String invalidValue = "123!@#123";
        SignUpRequest req = SignUpRequestFactory.createSignUpRequestWithPassword(invalidValue);

        // when
        Set<ConstraintViolation<SignUpRequest>> validate = validator.validate(req);

        // then
        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(toSet())).contains(invalidValue);
    }

    @Test
    public void invalidateByNoneNumberPasswordTest() throws Exception{
        //given
        String invalidValue = "abc!@#abc";
        SignUpRequest req = SignUpRequestFactory.createSignUpRequestWithPassword(invalidValue);
        //when
        Set<ConstraintViolation<SignUpRequest>> validate = validator.validate(req);
        //then
        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(toSet())).contains(invalidValue);
    }

    @Test
    public void invalidateBySpecialCasePasswordTest() throws Exception{
        //given
        String invalidValue = "ASDasd12345";
        SignUpRequest req = SignUpRequestFactory.createSignUpRequestWithPassword(invalidValue);
        //when
        Set<ConstraintViolation<SignUpRequest>> validate = validator.validate(req);
        //then
        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(toSet())).contains(invalidValue);
    }

    @Test
    public void invalidateByEmptyUsernameTest() throws Exception{
        //given
        String invalidValue = null;
        SignUpRequest req = SignUpRequestFactory.createSignUpRequestWithUsername(invalidValue);
        //when
        Set<ConstraintViolation<SignUpRequest>> validate = validator.validate(req);
        //then
        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(toSet())).contains(invalidValue);
    }

    @Test
    public void invalidateByBlankUsernameTest() throws Exception{
        //given
        String invalidValue = "";
        SignUpRequest req = SignUpRequestFactory.createSignUpRequestWithUsername(invalidValue);
        //when
        Set<ConstraintViolation<SignUpRequest>> validate = validator.validate(req);
        //then
        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(toSet())).contains(invalidValue);
    }

    @Test
    public void invalidateByShoㄱtUsernameTest() throws Exception{
        //given
        String invalidValue = "협";
        SignUpRequest req = SignUpRequestFactory.createSignUpRequestWithUsername(invalidValue);
        //when
        Set<ConstraintViolation<SignUpRequest>> validate = validator.validate(req);
        //then
        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(toSet())).contains(invalidValue);
    }

    @Test
    public void invalidateByNotAlphaOrHangleUsernameTest() throws Exception{
        //given
        String invalidValue = "환123";
        SignUpRequest req = SignUpRequestFactory.createSignUpRequestWithUsername(invalidValue);
        //when
        Set<ConstraintViolation<SignUpRequest>> validate = validator.validate(req);
        //then
        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(toSet())).contains(invalidValue);
    }

    @Test
    public void invalidateByEmptyNicknameTest() throws Exception{
        //given
        String invalidValue = null;
        SignUpRequest req = SignUpRequestFactory.createSignUpRequestWithNickname(invalidValue);
        //when
        Set<ConstraintViolation<SignUpRequest>> validate = validator.validate(req);
        //then
        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(toSet())).contains(invalidValue);
    }

    @Test
    public void invalidateByNotAlphaOrHangleNickTest() throws Exception{
        //given
        String invalidValue = "환123";
        SignUpRequest req = SignUpRequestFactory.createSignUpRequestWithNickname(invalidValue);
        //when
        Set<ConstraintViolation<SignUpRequest>> validate = validator.validate(req);
        //then
        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(toSet())).contains(invalidValue);
    }

    @Test
    public void invalidateByBlanckNicknameTest() throws Exception{
        //given
        String invalidValue = "";
        SignUpRequest req = SignUpRequestFactory.createSignUpRequestWithNickname(invalidValue);
        //when
        Set<ConstraintViolation<SignUpRequest>> validate = validator.validate(req);
        //then
        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(toSet())).contains(invalidValue);
    }
//    private SignUpRequest createRequest(){
//        return new SignUpRequest("email@google.com", "!asd123456", "username", "nickname");
//    }
//
//    private SignUpRequest createRequestWithEmail(String email){
//        return new SignUpRequest(email, "!asd123456", "username", "nickname");
//    }
//
//    private SignUpRequest createRequestWithPassword(String password){
//        return new SignUpRequest("email@google.com", password, "username", "nickname");
//    }
//
//    private SignUpRequest createRequestWithUsername(String username){
//        return new SignUpRequest("email@google.com", "!asd123456", username, "nickname");
//    }
//
//    private SignUpRequest createRequestWithNickname(String nickname){
//        return new SignUpRequest("email@google.com", "!asd123456", "username", nickname);
//    }
}