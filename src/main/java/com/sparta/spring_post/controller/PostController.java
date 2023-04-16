package com.sparta.spring_post.controller;

import com.sparta.spring_post.dto.PostDto;
import com.sparta.spring_post.entity.Post;
import com.sparta.spring_post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/api/posts")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping("/api/post")
    public Post createPost(@RequestBody PostDto postDto) {
        return postService.createPost(postDto);
    }


}
