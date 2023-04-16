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

    @Transactional
    public Post createPost(PostDto postDto) {
        Post post = new Post(postDto);
        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

}
