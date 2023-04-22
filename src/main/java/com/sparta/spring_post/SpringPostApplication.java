package com.sparta.spring_post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing              // 어플리케이션의 main method 가 있는 클래스에 적용해야함
                                // JPA Auditing ( 감시, 감사 ) 기능을 활성화하기 위한 어노테이션
public class SpringPostApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPostApplication.class, args);
    }

}
