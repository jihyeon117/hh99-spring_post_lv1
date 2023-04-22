// Client <-Dto-> Controller <-Dto-> Service <-Dto-> Repository <-Entity-> DB
package com.sparta.spring_post.dto;

import com.sparta.spring_post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter         // Lombok 어노테이션. private 변수에 접근하기 위해 사용함
// @Getter : 인스턴스 변수를 반환 - 변수 앞에 get
// @Setter : 인스턴스 변수를 대입하거나 수정 - 변수 앞에 set
// @Getter, @Setter 둘 다 데이터 무결성을 위해 사용하나
// ( 들어오는 값을 바로 저장하는 게 아닌 한번 검증하고 처리할 수 있도록 하기 때문에 )
// @Setter 는 데이터 무결성을 해칠 수도 있음
// ( 단순하게 @Setter 로 데이터를 수정하면 어떤 부분을 수정하는지 어디서 데이터를 수정하는지 알 수 없음 )
// 데이터 무결성 : 데이터의 정확성과 일관성을 유지하고 보증하는 것
@NoArgsConstructor      // @NoArgsConstructor : 파라미터가 없는 기본 생성자를 생성해줌
public class PostRequestDto {       // 클라이언트와 서버 간에 데이터를 전송하기 위해 사용하는 DTO
    // 필드 : 작성자, 비밀번호, 제목, 내용
    private String author;
    private String password;
    private String title;
    private String content;

    public Post toEntity() {    // toEntity() : PostRequestDto 객체를 Post Entity 객체로 변환하는데 사용됨 ( 사용자 지정 메서드 )
        return Post.builder()
                // builder() : Post 객체의 새 인스턴스를 생성하고 PostRequestDto 객체의 값으로 속성을 설정함
                .author(author)
                .password(password)
                .title(title)
                .content(content)
                .build();
                // build() : 모든 필드가 설정되면 build() 메서드를 호출하여 Post 객체를 만듦
                // 데이터를 DB 에 유지하거나 다른 작업을 수행하는데 사용할 수 있는 불변 Post 객체의 인스턴스를 반환함
    }

}
