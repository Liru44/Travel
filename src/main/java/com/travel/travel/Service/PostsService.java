package com.travel.travel.Service;

import com.travel.travel.DTO.PostsDTO;
import com.travel.travel.Entity.LocationEntity;
import com.travel.travel.Entity.MemberEntity;
import com.travel.travel.Entity.PostsEntity;
import com.travel.travel.Repository.LocationRepository;
import com.travel.travel.Repository.MemberRepository;
import com.travel.travel.Repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PostsService {
    @Autowired
    private PostsRepository postsRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private LocationRepository locationRepository;

    // 게시글 저장
    public void newPost(PostsDTO posts, String userId) throws IOException {
        // 1. 작성자 확인
        MemberEntity member = memberRepository.findById(userId).orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다. "));

        // 2. 위치 확인 또는 생성
        double lat = posts.getLat();
        double lng = posts.getLng();
        String address = posts.getAddress();
        LocationEntity location = locationRepository.findByName(address).
                orElseGet(() -> {
                    LocationEntity newLoc = new LocationEntity();
                    newLoc.setLatitude(lat);
                    newLoc.setLongitude(lng);
                    newLoc.setName(address);
                    return locationRepository.save(newLoc);
                });

        PostsEntity newPost = new PostsEntity();
        newPost.setTitle(posts.getTitle());
        newPost.setContent(posts.getContent());
        if (posts.getMedia() != null && !posts.getMedia().isEmpty()) {
            newPost.setMedia(posts.getMedia().getBytes());
        }
        newPost.setLocation(location);
        newPost.setOriginator(member);

        postsRepository.save(newPost);
    }

    // 게시글 전체 조회
    public List<PostsEntity> getAllPosts() {
        return postsRepository.findAll();
    }

    // 특정 장소 게시글 조회
    public List<PostsEntity> getPostsByLatLng(Double lat, Double lon) {
        return postsRepository.findByLocationLatitudeAndLocationLongitude(lat, lon);
    }

    // 특정 ID 게시글 조회
    public Optional<PostsEntity> getPostById(Long id) {
        return postsRepository.findById(id);
    }

    // 게시글 수정
    public void editPost(PostsDTO posts) throws IOException {
        PostsEntity postsEntity = postsRepository.findById(posts.getId())
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

        try {
            postsEntity.setTitle(posts.getTitle());
            postsEntity.setContent(posts.getContent());
            if (posts.getMedia() != null) {
                postsEntity.setMedia(posts.getMedia().getBytes());
            }

            postsRepository.save(postsEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 게시글 삭제
    public void deletePost(Long id) {
        postsRepository.deleteById(id);
    }
}
