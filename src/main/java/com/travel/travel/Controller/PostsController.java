package com.travel.travel.Controller;

import com.travel.travel.DTO.PostsDTO;
import com.travel.travel.DTO.PostsResponseDTO;
import com.travel.travel.Entity.PostsEntity;
import com.travel.travel.Service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

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
    @GetMapping("/post")
    public List<PostsResponseDTO> getAllPosts(@RequestParam(required = false) Double lat, @RequestParam(required = false) Double lon) {
        List<PostsEntity> posts = (lat != null && lon != null)
                ? postService.getPostsByLatLng(lat, lon)
                : postService.getAllPosts();

        return posts.stream()
                .map(PostsResponseDTO::new)
                .toList();
    }

    // 특정 ID 게시글 조회
    @GetMapping("/post/{id}")
    public ResponseEntity<PostsResponseDTO> getPostById(@PathVariable Long id) {
        return postService.getPostById(id)
                .map(post -> ResponseEntity.ok(new PostsResponseDTO(post)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 게시글 수정
    @PutMapping(value = "/edit/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> editPost(@ModelAttribute PostsDTO posts) throws IOException {
        checkUser(posts.getId());
        postService.editPost(posts);
        return ResponseEntity.ok("게시글 수정 성공");
    }

    // 게시글 삭제
    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable Long id) {
        checkUser(id);
        postService.deletePost(id);
    }

    //작성자와 현재 접속 유저 확인
    public void checkUser(Long id) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        PostsEntity post = postService.getPostById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다."));

        if (!post.getOriginator().getUsername().equals(currentUsername)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "작성자만 수정/삭제할 수 있습니다.");
        }
    }
}