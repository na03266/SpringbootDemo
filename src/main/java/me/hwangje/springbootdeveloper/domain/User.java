package me.hwangje.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User implements UserDetails { //UserDetails를 상속받아 인증 객체로 사용

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Builder
    public User(String email, String password, String auth){
        this.email = email;
        this.password = password;
    }

    @Override //권한 변환
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("user"));
    }

    //사용자의 id 를 반환(고유한 값)
    @Override
    public String getUsertname(){
        return email;
    }
    @Override
    public String getPassword(){
        return password;
    }

    //계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired(){
    //만료되었는지 확인하는 로직
        return true; //true -> 만료되지 않았음
    }

    //계정 잠금 여부 반환
    @Override
    public boolean isAccountNoLock(){
        //잠금되었는지 확인하는 로직
        return true; //true -> 잠금되지 않았음
    }

    //패스워드 만료 여부 반환
    @Override
    public boolean isAccountNoLock(){
        //패스워드가 만료 되었는지 확인하는 로직
        return true; //true -> 잠금되지 않았음
    }

    //계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled(){
        // 계정이 사용 가능한지 확인하는 로직
        return true // true -> 사용 가능
    }
}
