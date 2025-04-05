package com.travel.travel.Service;

import com.travel.travel.Entity.LocationEntity;
import com.travel.travel.Repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    //특정 지역 선택 시 근처 장소 목록을 DB에서 검색하는 API (범위는 ±0.05)
    public List<LocationEntity> getNearbyLocations(double lat, double lon) {
        return locationRepository.findByLatitudeAndLongitude(lat - 0.05, lat + 0.05, lon - 0.05, lon + 0.05);
    }
}