package me.hwangje.springbootdeveloper.repository;

import me.hwangje.springbootdeveloper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositary extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); //이메일로 사용자 정보를 가져옴
}
