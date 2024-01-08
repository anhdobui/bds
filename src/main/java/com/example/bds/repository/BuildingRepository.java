package com.example.bds.repository;

import com.example.bds.entity.BuildingEntity;
import com.example.bds.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>, BuildingRepositoryCustom {
    Long countByIdIn(List<Long> ids);
    Integer deleteByIdIn(List<Long> ids);
    boolean existsByIdAndStaffs_Id(Long buildingId,Long staffId);
}
