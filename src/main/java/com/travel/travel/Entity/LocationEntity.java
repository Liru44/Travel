package com.travel.travel.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "location")
@Getter
@Setter
@NoArgsConstructor
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동 증가 ID
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;  // 장소명

    @Column(nullable = false)
    private Double latitude;  // 위도

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<PostsEntity> getPosts() {
        return posts;
    }

    public void setPosts(List<PostsEntity> posts) {
        this.posts = posts;
    }

    @Column(nullable = false)
    private Double longitude;  // 경도

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<PostsEntity> posts;  // 해당 위치에 작성된 게시글 목록
}