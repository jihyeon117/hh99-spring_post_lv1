package com.sparta.spring_post.entity;

import com.sparta.spring_post.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Builder
    public Post(String author, String password, String title, String content) {
        this.author = author;
        this.password = password;
        this.title = title;
        this.content = content;
    }

    public void update(PostRequestDto postRequestDto) {
        this.author = postRequestDto.getAuthor();
        this.password = postRequestDto.getPassword();
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
    }
}
