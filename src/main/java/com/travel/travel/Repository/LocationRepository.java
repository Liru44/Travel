package com.travel.travel.Repository;

import com.travel.travel.Entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
    @Query("SELECT l FROM LocationEntity l WHERE l.latitude BETWEEN :lat1 AND :lat2 AND l.longitude BETWEEN :lon1 AND :lon2")
    List<LocationEntity> findByLatitudeAndLongitude(@Param("lat1") double lat1, @Param("lat2") double lat2, @Param("lon1") double lon1, @Param("lon2") double lon2);
}