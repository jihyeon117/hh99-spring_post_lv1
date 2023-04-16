package com.sparta.spring_post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringPostApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPostApplication.class, args);
    }

}
