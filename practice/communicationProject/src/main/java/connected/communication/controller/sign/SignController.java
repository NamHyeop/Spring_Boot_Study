package connected.communication.controller.sign;

import connected.communication.dto.response.Response;
import connected.communication.dto.sign.SignInRequest;
import connected.communication.dto.sign.SignUpRequest;
import connected.communication.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static connected.communication.dto.response.Response.success;


@RestController
@RequiredArgsConstructor
public class SignController {
    private final SignService signService;

    @PostMapping("/api/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public Response signUp(@Valid @RequestBody SignUpRequest req){
        signService.signUp(req);
        return success();
    }

    @PostMapping("/api/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public Response signIn(@Valid @RequestBody SignInRequest req){
        return success(signService.signIn(req));
    }
}
