package me.hwangje.springbootdeveloper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.hwangje.springbootdeveloper.domain.Article;
import me.hwangje.springbootdeveloper.dto.AddArticleRequest;
import me.hwangje.springbootdeveloper.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    BlogRepository blogRepository;

    @BeforeEach //테스트 실행 전 실행하는 메서드
    public void setMockSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        blogRepository.deleteAll();
    }
@DisplayName("addArticle: 블로그 글 추가에 성공한다.")
    @Test
    public void addArticle() throws Exception{
        //given 블로그 글 추가에 필요한 요청 객체 생성
    final String url = "/api/articles";
    final String title = "title";
    final String content = "content";
    final AddArticleRequest userRequest = new AddArticleRequest(title, content);

    //객체 json으로 직렬화
    final String requestBody = objectMapper.writeValueAsString(userRequest);

    //when, 블로그 글 추가에 API요청, 제이슨 타입
    //설정한 내용을 바탕으로 요청 전송
    ResultActions result = mockMvc.perform(post(url)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(requestBody));
    //then
    result.andExpect(status().isCreated());

    List<Article> articles = blogRepository.findAll();

    assertThat(articles.size()).isEqualTo(1);    //크기가 1인지 검증
    assertThat(articles.get(0).getTitle()).isEqualTo(title);
    assertThat(articles.get(0).getContent()).isEqualTo(content);

}
}