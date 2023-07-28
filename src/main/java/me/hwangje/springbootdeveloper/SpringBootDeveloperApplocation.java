package me.hwangje.springbootdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.swing.*;

@EnableJpaAuditing //created_at, updated_at 자동 업데이트
@SpringBootApplication
public class SpringBootDeveloperApplocation {
    public static void main(String[] args){
        SpringApplication.run(SpringBootDeveloperApplocation.class, args);
    }
}
