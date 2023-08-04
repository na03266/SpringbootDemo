package me.hwangje.springbootdeveloper.service;


import lombok.RequiredArgsConstructor;
import me.hwangje.springbootdeveloper.domain.User;
import me.hwangje.springbootdeveloper.dto.AddUserRequest;
import me.hwangje.springbootdeveloper.repository.UserRepositary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepositary userRepositary;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto){
        return userRepositary.save(User.builder()
                .email(dto.getEmail())
                //패스워드 암호화
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }

    public User findById(Long userId){
        return userRepositary.findById(userId).orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}
