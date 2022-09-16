package connected.communication.exception;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

//Api 문서를 만들지 않도록 지정하는 어노테이션, SpringSecurity에서 발생한 예외를 Redirct를 통해 Advice에서 잡아내기 위해 작성한 것이기 때문에
//문서화할 필요가 없다.
@ApiIgnore
@RestController
public class ExceptionController {
    @GetMapping("/exception/entry-point")
    public void entryPoint(){
        throw new AuthenticationEntryPointException();
    }

    @GetMapping("/exception/access-denied")
    public void accessDenied(){
        throw new AccessDeniedException();
    }
}