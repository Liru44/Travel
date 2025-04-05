package com.travel.travel.Controller;

import com.travel.travel.Entity.PostsEntity;
import com.travel.travel.Service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostsController {
    @Autowired
    private PostsService postService;

    // 게시글 등록 (POST 요청)
    @PostMapping
    public PostsEntity createPost(@RequestBody PostsEntity posts) {
        return postService.createPost(posts);
    }

    // 모든 게시글 조회 (GET 요청)
    @GetMapping
    public List<PostsEntity> getAllPosts() {
        return postService.getAllPosts();
    }

    // 특정 게시글 조회 (GET 요청)
    @GetMapping("/{id}")
    public Optional<PostsEntity> getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    // 게시글 삭제 (DELETE 요청)
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}