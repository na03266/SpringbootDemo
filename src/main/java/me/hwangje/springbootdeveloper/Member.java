package me.hwangje.springbootdeveloper;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자
@AllArgsConstructor
@Getter
@Entity //앤티티로 지정
public class Member {
    
    @Id // id 필드를 기본키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키를 자동으로 1 씩 증가
    @Column(name = "id", updatable = false)
    private long id; //DB테이블의 'id 컬럼과 매칭

    @Column(name = "name", nullable = false) // name이라는 not null 컬럼과 매핑
    private String name; //DB 테이블의 'name' 컬럼과 매칭

}
