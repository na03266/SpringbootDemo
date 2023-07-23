package me.hwangje.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.hwangje.springbootdeveloper.domain.Article;
import me.hwangje.springbootdeveloper.dto.AddArticleRequest;
import me.hwangje.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service // 빈으로 등록
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }


}