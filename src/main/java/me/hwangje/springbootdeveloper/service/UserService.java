package me.hwangje.springbootdeveloper.service;


import lombok.RequiredArgsConstructor;
import me.hwangje.springbootdeveloper.domain.User;
import me.hwangje.springbootdeveloper.dto.AddUserRequest;
import me.hwangje.springbootdeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return userRepository.save(User.builder()
                .email(dto.getEmail())
                //패스워드 암호화
                .password(encoder.encode(dto.getPassword()))
                .build()).getId();
    }

    public User findById(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email) //OAuth2에서 제공하는 이메일은 유일 값이므로 이 메서드로  유저 검색 가능
                .orElseThrow(() ->new IllegalArgumentException("Unexpected user"));
    }
}
