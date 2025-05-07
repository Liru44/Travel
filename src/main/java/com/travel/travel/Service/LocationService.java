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
}