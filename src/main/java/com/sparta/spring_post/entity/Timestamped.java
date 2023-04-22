package com.sparta.spring_post.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass       // @MappedSuperclass : 객체의 입장에서 공통 매핑 정보가 필요할 때 사용
                        // ( 부모 클래스에 선언하고 속성만 상속 받아서 사용하고 싶을 때 )
@EntityListeners(AuditingEntityListener.class)
// @EntityListeners : Entity 가 삽입, 삭제, 수정, 조회 등의 작업을 할 때 전, 후 어떠한 작업을 하기 위해 이벤트 처리를 위한 어노테이션
// EntityListeners 의 인자로 이벤트를 처리하고 요청할 클래스를 지정해주면 되는데
// Auditing 을 수행할 때는 JPA 에서 제공하는 AuditingEntityListener.class 를 인자로 넘기면 됨
// Auditing : 생성일 / 수정일 / 생성자 를 자동화할 때 사용
public class Timestamped {

    @CreatedDate            // Entity 가 생성될 때 시간이 자동으로 저장됨
    private LocalDateTime createdAt;        // 생성시간정보

    @LastModifiedDate       // Entity 값이 변경될 때 시간이 자동으로 저장됨
    private LocalDateTime modifiedAt;       // 수정시간정보
}
