package com.example.userservice.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * packageName    : com.example.userservice.controller
 * fileName       : Greeting
 * author         : namhyeop
 * date           : 2022/09/11
 * description    :
 * yaml 파일을 받는 다른 방식을 보여주기 위해 필요한 class
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/11        namhyeop       최초 생성
 */
@Data
@Component
public class Greeting {

    @Value("${greeting.message}")
    private String message;
}
