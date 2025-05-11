package com.travel.travel.Repository;

import com.travel.travel.DTO.PostsDTO;
import com.travel.travel.Entity.PostsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<PostsEntity, Long> {
    List<PostsEntity> findByLocationLatitudeAndLocationLongitude(Double lat, Double lon);
}