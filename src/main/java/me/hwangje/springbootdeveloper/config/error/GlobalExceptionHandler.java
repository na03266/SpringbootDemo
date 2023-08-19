package me.hwangje.springbootdeveloper.config.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice //모든 컨트롤러에서 발생하는 예외를 잡아서 처리
public class GlobalExceptionHandler {
}
