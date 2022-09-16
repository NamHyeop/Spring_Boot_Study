package com.example.userservice_2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * packageName    : com.example.userservce_1
 * fileName       : FirstServiceController
 * author         : namhyeop
 * date           : 2022/09/09
 * description    :
 * 마이크로서비스 예시를 위한 예시 인스턴스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/09        namhyeop       최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/second-service")
@RequiredArgsConstructor
public class SecondServiceController {

    //(1) serverport 받는 방법-1
    @Value("local.server.port")
    private String serverPort;
    //(2) serverport 받는 방법-2
    private final Environment env;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the second service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("second-request-key") String header){
        log.info(header);
        return "Welcome to the second message service";
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request){
        log.info("Server port={}", request.getServerPort());
        //(1)
        return String.format("Hi, there. This is a message from second check Service on Port = %s.", serverPort);
        //(2)
//        return String.format("Hi, there. This is a message from second check Service.", env.getProperty("local.server.port"));
    }
}
