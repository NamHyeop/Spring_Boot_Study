package com.example.userservice.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * packageName    : com.example.userservice.jpa
 * fileName       : UserEntity
 * author         : namhyeop
 * date           : 2022/09/11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/11        namhyeop       최초 생성
 */

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String email;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, unique = true)
    private String userId;
    @Column(nullable = false, unique = true)
    private String encryptedPwd;

}
