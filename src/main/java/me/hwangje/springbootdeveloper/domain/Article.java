package me.hwangje.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Entity //엔티티로 지정
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Article {

    @Id // id 필드를 기본으로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키를 자동으로 1씩 증가
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false) //title 이라는 not null 컬럼과 매핑
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder // 빌더 패턴으로 객체 생성
    /*
    빌더 패턴을 이용하면 각 객체가 어디에 들어가는지 파악하기 쉬움
     */
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
