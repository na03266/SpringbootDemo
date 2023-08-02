package me.hwangje.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.hwangje.springbootdeveloper.domain.User;
import me.hwangje.springbootdeveloper.repository.UserRepositary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
//스프링 시큐리티에서 사용자 정보를 가져오는 인터페이스
public class UserDetailService implements UserDetailsService {

    private final UserRepositary userRepositary;


    //사용자 이름(email)으로 사용자의 정보를 가져오는 메서드
    @Override
    public User loadUserByUsername(String email){
        return userRepositary.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException((email)));
    }
}
