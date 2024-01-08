package com.example.bds.service;

import com.example.bds.dto.BuildingDTO;
import com.example.bds.dto.BuildingSearchDTO;
import com.example.bds.dto.ResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface BuildingService {
    BuildingDTO save(BuildingDTO buildingDTO);

    Page<BuildingDTO> findBuilding(BuildingSearchDTO buildingSearch, Pageable pageable);

    Integer delete(List<Long> ids);

    BuildingDTO findBuildingById(Long buildingId);
}
