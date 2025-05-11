package com.travel.travel.DTO;

import com.travel.travel.Entity.PostsEntity;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class PostsDTO {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String title;
    private String content;
    private MultipartFile media;

    private double lat;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMedia(MultipartFile media) {
        this.media = media;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private double lng;
    private String address;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public MultipartFile getMedia() {
        return media;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getAddress() {
        return address;
    }
}
