package com.sparta.spring_post.service;

import com.sparta.spring_post.dto.PostDto;
import com.sparta.spring_post.entity.Post;
import com.sparta.spring_post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional
    public Post getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        post.getClass();
        return post;
    }

    @Transactional
    public Post createPost(PostDto postDto) {
        Post post = new Post(postDto);
        return postRepository.save(post);
    }

    @Transactional
    public Post updatePost(Long id, PostDto postDto) {
        Post post = postRepository.findById(id).orElseThrow();
        if(!post.getPassword().equals(postDto.getPassword())) {
            return post;
        }
        post.update(postDto);
        return post;
    }

    @Transactional
    public String deletePost(Long id, PostDto postDto) {
        Post post = postRepository.findById(id).orElseThrow();
        if(!post.getPassword().equals(postDto.getPassword())) {
            return "비밀번호가 일치하지 않습니다.";
        }
        postRepository.deleteById(id);
        return "성공적으로 삭제되었습니다.";
    }

}
