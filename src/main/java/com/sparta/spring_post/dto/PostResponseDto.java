// Client <-Dto-> Controller <-Dto-> Service <-Dto-> Repository <-Entity-> DB
package com.sparta.spring_post.dto;

import com.sparta.spring_post.entity.Post;
import com.sparta.spring_post.entity.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    // 필드 : 생성날짜, 수정날짜, 아이디, 작성자, 제목, 내용
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long id;
    private String author;
    private String title;
    private String content;

    // Constructor
    public PostResponseDto(Post post) {
        // PostResponseDto 클래스의 생성자
        // Post 객체를 매개변수로 받아 해당 객체의 필드값을 PostResponseDto 의 필드에 복사하는 역할
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.id = post.getId();
        this.author = post.getAuthor();
        this.title = post.getTitle();
        this.content = post.getContent();
        // Post 객체에 대한 의존성을 가지고 있기 때문에 Post 객체가 변경되었을 때 PostResponseDto 객체도 변경되어야 한다면
        // 불필요한 코드 수정이 필요해질 수 있음
        // -> PostResponseDto 클래스와 Post 클래스가 서로에 대한 의존성을 최소화하는 방향으로 리팩토링하는 것이 좋음
    }

}
