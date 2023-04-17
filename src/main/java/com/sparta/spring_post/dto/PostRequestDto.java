// Client <-Dto-> Controller <-Dto-> Service <-Dto-> Repository <-Entity-> DB
package com.sparta.spring_post.dto;

import com.sparta.spring_post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto {
    private String author;
    private String password;
    private String title;
    private String content;

    public Post toEntity() {
        return Post.builder()
                .author(author)
                .password(password)
                .title(title)
                .content(content)
                .build();
    }
}
