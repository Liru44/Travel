package com.travel.travel.Service;

import com.travel.travel.Entity.PostsEntity;
import com.travel.travel.Repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostsService {
    @Autowired
    private PostsRepository postsRepository;

    // 게시글 저장
    public PostsEntity createPost(PostsEntity posts) {
        return postsRepository.save(posts);
    }

    // 게시글 전체 조회
    public List<PostsEntity> getAllPosts() {
        return postsRepository.findAll();
    }

    // 특정 게시글 조회
    public Optional<PostsEntity> getPostById(Long id) {
        return postsRepository.findById(id);
    }

    // 게시글 삭제
    public void deletePost(Long id) {
        postsRepository.deleteById(id);
    }
}
