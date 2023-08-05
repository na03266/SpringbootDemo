package me.hwangje.springbootdeveloper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.hwangje.springbootdeveloper.config.jwt.JwtFactory;
import me.hwangje.springbootdeveloper.config.jwt.JwtProperties;
import me.hwangje.springbootdeveloper.domain.RefreshToken;
import me.hwangje.springbootdeveloper.domain.User;
import me.hwangje.springbootdeveloper.dto.CreateAccessTokenRequest;
import me.hwangje.springbootdeveloper.repository.RefreshTokenRepository;
import me.hwangje.springbootdeveloper.repository.UserRepositary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TokenApiControllerTest {
    @Autowired
    protected MockMvc  mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    JwtProperties jwtProperties;
    @Autowired
    UserRepositary userRepositary;
    @Autowired
    RefreshTokenRepository refreshTokenRepository;
    
    @BeforeEach
    public void MockSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        userRepositary.deleteAll();
    }
    
    @DisplayName("createNewAccessToken : 새로운 액세스 토큰을 발급한다.")
    @Test
    public void createNewAccessToken() throws Exception{
        //given 테스트 유저를 생성하고 jjwt 라이브러리를 이용해 리프레시 토큰을 만들어 데이터베이스에 저장하고, 
        // 토큰 생성 api의 요청 본문에 리프레시 토큰을 포함하여 요청 객체를 생성
        final String url = "/api/token";

        User testUser = userRepositary.save(User.builder()
                .email("user@gmail.com")
                .password("test")
                .build());
        
        String refreshToken = JwtFactory.builder()
                .claims(Map.of("id", testUser.getId()))
                .build()
                .createToken(jwtProperties);
        
        refreshTokenRepository.save(new RefreshToken(testUser.getId(),refreshToken));

        CreateAccessTokenRequest request = new CreateAccessTokenRequest();
        request.setRefreshToken(refreshToken);
        
        final String requestBody = objectMapper.writeValueAsString(request);
        
        //when 토큰 추가 API 요청 전송, 요청타입 json 기븐절에서 만든 객체를 본문과 함께 보냄
        ResultActions resultActions = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));
        
        //then 응답코드 확인 후 답변토큰 비어있지 않은지 확인
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accessToken").isNotEmpty());
    }
}
