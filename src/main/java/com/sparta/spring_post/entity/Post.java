// Client <-Dto-> Controller <-Dto-> Service <-Dto-> Repository <-Entity-> DB
package com.sparta.spring_post.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.spring_post.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity         // @Entity : DB 의 테이블과 일대일로 매칭되는 객체 단위
                // Entity 객체의 인스턴스 하나가 테이블에서 하나의 레코드 값을 의미함
@NoArgsConstructor          // @NoArgsConstructor : 파라미터가 없는 기본 생성자를 생성해줌
public class Post extends Timestamped {
    @Id     // PK ( Primary Key )
    @GeneratedValue(strategy = GenerationType.AUTO)
    // @GeneratedValue : 새로운 레코드가 생성될때마다 마지막 PK 값에서 자동으로 +1 해줌
    // strategy = GenerationType. : PK 값에 대한 생성 전략
    // AUTO : JPA 구현체가 자동으로 생성전략을 결정함
    // IDENTITY : 기본키 생성을 DB 에 위임함
    // SEQUENCE : DB 의 특별한 오브젝트 시퀀스를 사용하여 기본키를 생성함
    // TABLE : DB 에 키 생성 전용 테이블을 하나 만들고 이를 사용하여 기본키를 생성함
    private Long id;

    @Column(nullable = false)
    // @Column : DB 의 테이블에 있는 컬럼과 동일하게 1대1로 매칭되기 때문에 Entity 클래스안에 내부변수로 정의됨
    // 별다른 옵션을 설정하지 않으면 생략 가능함
    // nullable = false : null 허용 X
    // nullable = true : null 허용 O
    private String author;

    @Column(nullable = false)
    @JsonIgnore     // 데이터를 주고받을 때, 해당 데이터 ignore. 응답값 보이지 않음
    private String password;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Builder    // Lombok 어노테이션. 빌더 패턴
    // PostRequestDto 에서 Builder 를 사용하기 위한 어노테이션
    // @Builder : 객체를 생성할 수 있는 빌더를 builder() 함수를 통해 얻고 거기에 셋팅하고자 하는 값을 셋팅하고 마지막에 build()를 통해 빌더를 작동시켜 객체를 생성함
    // Builder 써야하는 이유 : 1. 생성자 파라미터가 많을 경우 가독성이 좋지 않음
    //                       2. 생성자는 정해진 파라미터 순서대로 값을 넣어줘야 하지만 빌더는 순서에 종속적이지 않음
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
