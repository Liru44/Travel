package com.travel.travel.Controller;

import com.travel.travel.DTO.PostsDTO;
import com.travel.travel.DTO.PostsResponseDTO;
import com.travel.travel.Entity.PostsEntity;
import com.travel.travel.Service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostsController {
    @Autowired
    private PostsService postService;

    // 게시글 등록
    @PostMapping(value = "/newPost", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> newPosts(@ModelAttribute PostsDTO posts, Authentication authentication) throws IOException {
        String userId = authentication.getName();
        postService.newPost(posts, userId);
        return ResponseEntity.ok("게시글 등록 성공");
    }

    // 게시글 조회 (전체 or 해당 위치)
    @GetMapping("/getPosts")
    public List<PostsResponseDTO> getAllPosts(@RequestParam(required = false) Double lat, @RequestParam(required = false) Double lng) {
        List<PostsEntity> posts = (lat != null && lng != null)
                ? postService.getPostsByLatLng(lat, lng)
                : postService.getAllPosts();

        return posts.stream()
                .map(PostsResponseDTO::new)
                .toList();
    }

    // 특정 ID 게시글 조회
    @GetMapping("/getPosts/{id}")
    public ResponseEntity<PostsResponseDTO> getPostById(@PathVariable Long id) {
        return postService.getPostById(id)
                .map(post -> ResponseEntity.ok(new PostsResponseDTO(post)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 게시글 삭제
    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}