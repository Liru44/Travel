package com.travel.travel.DTO;

import com.travel.travel.Entity.PostsEntity;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.Base64;

@Getter
public class PostsResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String originator;
    private String originate;
    private String location;
    private String mediaBase64;

    public PostsResponseDTO(PostsEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.originator = entity.getOriginator().getUsername();  // 작성자 이름
        this.originate = entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.location = entity.getLocation().getName();

        if (entity.getMedia() != null) {
            this.mediaBase64 = Base64.getEncoder().encodeToString(entity.getMedia());
        }
    }
}