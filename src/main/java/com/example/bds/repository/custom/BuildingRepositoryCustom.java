package com.example.bds.repository.custom;

import com.example.bds.builder.BuildingSearchBuilder;
import com.example.bds.entity.BuildingEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BuildingRepositoryCustom {
    Long countBuildingFound(BuildingSearchBuilder builderSearch);
    List<BuildingEntity> findBuilding(BuildingSearchBuilder builderSearch, Pageable pageable);
}
