import org.junit.jupiter.api.*;

public class JUnitCycleTest {
    @BeforeAll //전체 테스트 시작 전에 처음으로 한번만 실행, 메서드 static로 선언
    static void beforeAll(){
        System.out.println("@BeforeAll");
    }
    @BeforeEach //테스트 케이스 시작하기 전에 매번 실행, 그래서 메서드는 static이 아니어야함
    public void beforeEach(){
        System.out.println("@BeforeEach");
    }
    @Test
    public void test1(){
        System.out.println("test1" );
    }
    @Test
    public void test2(){
        System.out.println("test2" );
    }
    @Test
    public void test3(){
        System.out.println("test3" );
    }

    @AfterAll // 종료 직전에 한번만 실행함, 전체 테스트 실행주기에서 한번만 실행해야하므로 메서드를 static으로 선언
    static void afterAll(){
        System.out.println("AfterAll");
    }

    @AfterEach // 각 테스트 케이스를 종료하기 전 매번 실행함, 메서드가 static 이면 안됨.
    public void afterEach(){
        System.out.println("@AfterEach");
    }
}
