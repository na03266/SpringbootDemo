package me.hwangje.springbootdeveloper;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // 테스트용 어플리케이션 컨텍스트 생성
@AutoConfigureMockMvc //MockMvc 생성 및 자동 구성
class TestControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach // 테스트 실행 전 실행하는 메서드
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach //테스트 실행 후 실행하는 메서드
    public void cleanUp(){
        memberRepository.deleteAll();
    }

    @DisplayName("getAllMembers: 아티클 조회에 성공한다.")
    @Test
    public void getAllMembers() throws Exception{
        //given 멤버를 저장

        final String url = "/test";
        Member savedMember = memberRepository.save(new Member(1L, "홍길동"));

        //when 멤버 리스트를 조회하는 API 호출
        final ResultActions result = mockMvc.perform(get(url)
                /*perform()메소드는 요청을 전송하는 역할을 함. 결과로 ResultAction객체를 받으며
                ResultAction 객체는 반환값을 검증하고 확인하는 andExpect() 메서드를 제공한다. */
                .accept(MediaType.APPLICATION_JSON));
                /*
                accept()는 받는 메세지의 응답 타입을 결정하는 메소드로 여기에서는 json타입으로 받는다.
                 */

        //then 응답 코드가 200 ok이고 반환받은 값 중에 0번째 요소의 id와 name이 저장된 값과 일치하는지 확인한다.
        result.andExpect(status().isOk()); // 응답이 200인지 검증
        //응답의 0번째 값이 DB에 저장한 값과 같은지 확인
        result.andExpect(jsonPath("$[0].id").value(savedMember.getId()));
        result.andExpect(jsonPath("$[0].name").value(savedMember.getName()));
    }
}