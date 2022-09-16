package connected.communication.controller.sign;

import connected.communication.dto.response.Response;
import connected.communication.dto.sign.SignInRequest;
import connected.communication.dto.sign.SignUpRequest;
import connected.communication.service.SignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

import static connected.communication.dto.response.Response.success;

//@Api는 Swagger로 인해 API 문서가 작성될것이라는것을 알려주는 어노테이션이다.
@Api(value = "Sign Controller", tags = "Sign")
@RestController
@RequiredArgsConstructor
public class SignController {

    private final SignService signService;

    @ApiOperation(value="회원가입", notes = "회원가입을 한다.")
    @PostMapping("/api/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public Response signUp(@Valid @RequestBody SignUpRequest req){
        signService.signUp(req);
        return success();
    }

    @ApiOperation(value = "로그인", notes = "로그인을 한다.")
    @PostMapping("/api/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public Response signIn(@Valid @RequestBody SignInRequest req){
        return success(signService.signIn(req));
    }

    @ApiOperation(value="토큰 재발급", notes = "리프레시 토큰으로 새로운 엑세스 토큰을 발급 받는다.")
    @PostMapping("/api/refresh-token")
    @ResponseStatus(HttpStatus.OK)
    //Authorization헤더는 Swagger.config에서 이미 전역으로 지정되도록 설정했기 때문에 @ApiIgnore를 선언해준
    public Response refreshToken(@ApiIgnore @RequestHeader(value="Authorization") String refreshToken){
        return success(signService.refreshToken(refreshToken));
    }
}
