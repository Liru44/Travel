package com.travel.travel.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Getter
@NoArgsConstructor
public class PostsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동 증가 ID
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;  // 제목

    @Lob
    @Column(nullable = false)
    private String content;  // 내용 (긴 텍스트)

    @Lob
    private byte[] media;  // 사진 및 동영상 (BLOB)

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;  // 생성일

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)  // Location 테이블과 FK 연결
    private LocationEntity location;

    // 저장 시 자동으로 현재 시간 입력
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}