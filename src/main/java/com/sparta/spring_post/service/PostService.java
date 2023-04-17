// Client <-Dto-> Controller <-Dto-> Service <-Dto-> Repository <-Entity-> DB
package com.sparta.spring_post.service;

import com.sparta.spring_post.dto.PostRequestDto;
import com.sparta.spring_post.dto.PostResponseDto;
import com.sparta.spring_post.entity.Post;
import com.sparta.spring_post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    // PostRepository 연결
    private final PostRepository postRepository;

    // 목록 조회
    @Transactional(readOnly = true)
    public List<PostResponseDto> getAllPosts() {
        List<Post> posts = postRepository.findAllByOrderByModifiedAtDesc();
        List<PostResponseDto> dtos = new ArrayList<>();
        for (Post post : posts) {
            dtos.add(new PostResponseDto(post));
        }
        return dtos;
    }

    // 상세 조회
    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        PostResponseDto dtos = new PostResponseDto(post);
        dtos.getClass();
        return dtos;
    }

    // 추가
    @Transactional
    public PostResponseDto createPost(PostRequestDto postRequestDto) {
        Post post = postRepository.save(postRequestDto.toEntity());
        return new  PostResponseDto(post);
    }

    // 수정
    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(id).orElseThrow();
        PostResponseDto dtos = new PostResponseDto(post);
        if (!post.getPassword().equals(postRequestDto.getPassword())) {
            return dtos;
        }
        post.update(postRequestDto);
        return new PostResponseDto(post);
    }

    // 삭제
    @Transactional
    public String deletePost(Long id, String password) {
        Post post = postRepository.findById(id).orElseThrow();
        if (!post.getPassword().equals(password)) {
            return "비밀번호가 일치하지 않습니다.";
        }
        postRepository.deleteById(id);
        return "성공적으로 삭제되었습니다.";
    }

}
