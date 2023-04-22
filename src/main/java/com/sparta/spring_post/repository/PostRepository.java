// Client <-Dto-> Controller <-Dto-> Service <-Dto-> Repository <-Entity-> DB
package com.sparta.spring_post.repository;

import com.sparta.spring_post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    // extends JpaRepository : Spring Data JPA 가 인터페이스에 대해서 프록시 구현체를 만든위 DI 받기 때문에 구현체가 없어도 동작할 수 있음
    List<Post> findAllByOrderByModifiedAtDesc();
    // "ModifiedAt" 필드를 기준으로 내림차순으로 정렬하여 게시물 목록을 검색하는 메서드
}
