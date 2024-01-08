package com.example.bds.repository;

import com.example.bds.entity.RentAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentAreaRepository extends JpaRepository<RentAreaEntity, Long> {
    List<RentAreaEntity> findByBuildingId(Long buildingId);
    List<RentAreaEntity> findByBuildingIdAndAndValue(Long id,Integer value);
}
