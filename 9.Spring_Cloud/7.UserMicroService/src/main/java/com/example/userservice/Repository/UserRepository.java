package com.example.userservice.Repository;

import com.example.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.example.userservice.jpa
 * fileName       : UserRepository
 * author         : namhyeop
 * date           : 2022/09/11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/11        namhyeop       최초 생성
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);

    User findByEmail(String username);
}
