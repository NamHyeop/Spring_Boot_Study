package com.example.springboot_9_6_jpaitemwriter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * packageName    : com.example.springbatch_9_5_jobbatchitemwriter
 * fileName       : Customer
 * author         : namhyeop
 * date           : 2022/08/15
 * description    :
 * Customer Entity, Jpa를 사용하기에 Getter,Setter로만 이루어진 pojo객체 필요
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/15        namhyeop       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Customer2 {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthdate;
}
