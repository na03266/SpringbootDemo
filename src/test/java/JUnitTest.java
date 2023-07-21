import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JUnitTest {
    @DisplayName("1+2는 3이다") //테스트 이름
    @Test //테스트 메서드
    public void junitTest(){
        int a = 1;
        int b = 2;
        int sum = 3;

        Assertions.assertEquals(sum, a+b); //값이 같은지 확인

    }
    @DisplayName("1+2는 3이다") //테스트 이름
    @Test //테스트 메서드
    public void junitFailedTest(){
        int a = 1;
        int b = 3;
        int sum = 3;

        Assertions.assertEquals(sum, a+b); //값이 같은지 확인

    }
}
