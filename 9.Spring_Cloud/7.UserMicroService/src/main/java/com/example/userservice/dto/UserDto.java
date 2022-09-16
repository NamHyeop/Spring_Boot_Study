package com.example.userservice.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * packageName    : com.example.userservice.dto
 * fileName       : UserDto
 * author         : namhyeop
 * date           : 2022/09/11
 * description    :
 * Service 영역에서 사용되는 Dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/11        namhyeop       최초 생성
 */
@Data
public class UserDto {

    private String email;
    private String name;
    private String pwd;
    private String userId;
    private Date createAt;

    private String encryptedPwd;

    private List<ResponseOrder> orders;
}
