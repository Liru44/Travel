package com.travel.travel.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Getter
@Setter
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

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getMedia() {
        return media;
    }

    public void setMedia(byte[] media) {
        this.media = media;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }

    public MemberEntity getOriginator() {
        return originator;
    }

    public void setOriginator(MemberEntity originator) {
        this.originator = originator;
    }

    @Lob
    private byte[] media;  // 사진 및 동영상 (BLOB)

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;  // 생성일

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)  // Location 테이블과 FK 연결
    private LocationEntity location;

    @ManyToOne
    @JoinColumn(name = "originator", nullable = false)
    private MemberEntity originator;  // 작성자

    // 저장 시 자동으로 현재 시간 입력
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}