package com.travel.travel.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "location")
@Getter
@NoArgsConstructor
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동 증가 ID
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;  // 장소명

    @Column(nullable = false)
    private Double latitude;  // 위도

    @Column(nullable = false)
    private Double longitude;  // 경도

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<PostsEntity> posts;  // 해당 위치에 작성된 게시글 목록
}