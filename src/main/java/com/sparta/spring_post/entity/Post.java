package com.sparta.spring_post.entity;

import com.sparta.spring_post.dto.PostDto;
import jakarta.persistence.*;
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

    public Post(PostDto postDto) {
        this.author = postDto.getAuthor();
        this.password = postDto.getPassword();
        this.title = postDto.getTitle();
        this.content = postDto.getContent();
    }

    public void update(PostDto postDto) {
        this.author = postDto.getAuthor();
        this.password = postDto.getPassword();
        this.title = postDto.getTitle();
        this.content = postDto.getContent();
    }
}
