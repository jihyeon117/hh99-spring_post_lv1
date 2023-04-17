package com.sparta.spring_post.service;

import com.sparta.spring_post.dto.PostRequestDto;
import com.sparta.spring_post.dto.PostResponseDto;
import com.sparta.spring_post.entity.Post;
import com.sparta.spring_post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    @Autowired
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<PostResponseDto> getAllPosts() {
        List<Post> posts = postRepository.findAllByOrderByModifiedAtDesc();
        List<PostResponseDto> dtos = new ArrayList<>();
        for (Post post : posts) {
            dtos.add(new PostResponseDto(post));
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        PostResponseDto dtos = new PostResponseDto(post);
        dtos.getClass();
        return dtos;
    }

    @Transactional
    public PostResponseDto createPost(PostRequestDto postRequestDto) {
        Post post = postRepository.save(postRequestDto.toEntity());
        return new  PostResponseDto(post);
    }

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
