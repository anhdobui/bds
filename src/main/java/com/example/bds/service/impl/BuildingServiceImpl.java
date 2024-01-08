package com.example.bds.service.impl;

import com.example.bds.builder.BuildingSearchBuilder;
import com.example.bds.constant.SystemConstant;
import com.example.bds.converter.BuildingConverter;
import com.example.bds.dto.BuildingDTO;
import com.example.bds.dto.BuildingSearchDTO;
import com.example.bds.dto.ResponseDTO;
import com.example.bds.entity.BuildingEntity;
import com.example.bds.entity.RentAreaEntity;
import com.example.bds.exception.NotFoundException;
import com.example.bds.repository.BuildingRepository;
import com.example.bds.repository.RentAreaRepository;
import com.example.bds.service.BuildingService;
import com.example.bds.utils.StringUtils;
import com.example.bds.utils.UploadFileUtils;
import jakarta.transaction.Transactional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private UploadFileUtils uploadFileUtils;
    @Override
    @Transactional
    public BuildingDTO save(BuildingDTO buildingDTO) {
        Long buildingId = buildingDTO.getId();
        BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
        if (buildingId != null) { // update
            BuildingEntity foundBuilding = buildingRepository.findById(buildingId)
                    .orElse(null);
            if(foundBuilding != null){
                buildingEntity.setAvatar(foundBuilding.getAvatar());
                buildingEntity.setStaffs(foundBuilding.getStaffs());
                buildingEntity.setRentAreas(foundBuilding.getRentAreas());
            }
        }
        saveThumbnail(buildingDTO, buildingEntity);
        if(!StringUtils.isNullOrEmpty(buildingDTO.getRentArea())){
            handleSetNewRentAreas(buildingDTO.getRentArea(),buildingEntity);
        }
        buildingEntity =  buildingRepository.save(buildingEntity);
        buildingDTO = buildingConverter.convertToDto(buildingEntity,buildingEntity.getRentAreas());

        return buildingDTO;
    }

    @Override
    public Page<BuildingDTO> findBuilding(BuildingSearchDTO buildingSearch, Pageable pageable) {
        List<BuildingDTO> buildingDTOs = new ArrayList<>();
        BuildingSearchBuilder buildingSearchBuilder = convertToBuildingSearchBuilder(buildingSearch);
        List<BuildingEntity> buildingEntities = buildingRepository.findBuilding(buildingSearchBuilder,pageable);
        long totalItem= buildingRepository.countBuildingFound(buildingSearchBuilder);
        buildingEntities.stream().forEach(item -> {
            List<RentAreaEntity> areaEntities = rentAreaRepository.findByBuildingId(item.getId());
            BuildingDTO buildingDTO = buildingConverter.convertToDto(item, areaEntities);
            buildingDTOs.add(buildingDTO);
        });
        Page<BuildingDTO> result = new PageImpl<>(buildingDTOs,pageable,totalItem);
        return result;
    }

    @Override
    @Transactional
    public Integer delete(List<Long> ids) {
        if(ids.size() >0){
            Long count = buildingRepository.countByIdIn(ids);
            if(count != ids.size()){
                throw new NotFoundException(SystemConstant.BUILDING_NOT_FOUND);
            }
            return buildingRepository.deleteByIdIn(ids);
        }
        return null;
    }

    @Override
    public BuildingDTO findBuildingById(Long buildingId) {
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId).orElse(null);
        if(buildingEntity != null){
            BuildingDTO result = buildingConverter.convertToDto(buildingEntity,buildingEntity.getRentAreas());
            return result;
        }
        return null;
    }

    private BuildingSearchBuilder convertToBuildingSearchBuilder(BuildingSearchDTO buildingserch) {
        BuildingSearchBuilder result = BuildingSearchBuilder.builder()
                .name(buildingserch.getName())
                .floorArea(buildingserch.getFloorArea())
                .level(buildingserch.getLevel())
                .costRentFrom(buildingserch.getCostRentFrom())
                .costRentTo(buildingserch.getCostRentTo())
                .direction(buildingserch.getDirection())
                .districtCode(buildingserch.getDistrictCode())
                .managerName(buildingserch.getManagerName())
                .managerPhone(buildingserch.getManagerPhone())
                .numberOfBasement(buildingserch.getNumberOfBasement())
                .ward(buildingserch.getWard())
                .types(buildingserch.getTypes())
                .rentAreaFrom(buildingserch.getRentAreaFrom())
                .rentAreaTo(buildingserch.getRentAreaTo())
                .staffId(buildingserch.getStaffId())
                .street(buildingserch.getStreet())
                .build();
        return result;
    }
    private void handleSetNewRentAreas(String rentAreaValues, BuildingEntity buildingEntityEdit) {
        List<Integer> newValuesOfRentarea = handleSplitRentareas(rentAreaValues);
        if(buildingEntityEdit.getRentAreas() != null){
            buildingEntityEdit.getRentAreas().removeIf(item -> !newValuesOfRentarea.contains(item.getValue()));
            newValuesOfRentarea.removeAll(buildingEntityEdit.getRentAreas().stream()
                    .map(RentAreaEntity::getValue)
                    .collect(Collectors.toList()));
        }else{
            buildingEntityEdit.setRentAreas(new ArrayList<>());
        }

        List<RentAreaEntity> newRentAreas =  newValuesOfRentarea.stream().map(item -> {
            RentAreaEntity newRentAreaEntity = new RentAreaEntity();
            newRentAreaEntity.setBuilding(buildingEntityEdit);
            newRentAreaEntity.setValue(item);
            return newRentAreaEntity;
        }).collect(Collectors.toList());
        buildingEntityEdit.getRentAreas().addAll(newRentAreas);
    }

    private List<Integer> handleSplitRentareas(String rentAreaValues) {
        List<Integer> result = Arrays.stream(rentAreaValues.split(","))
                .map(area -> {
                    try {
                        return Integer.parseInt(area.trim());
                    } catch (NumberFormatException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return result;
    }
    private void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        String path = "/building/" + buildingDTO.getImageName();
        if (null != buildingDTO.getImageBase64()) {
            if (null != buildingEntity.getAvatar()) {
                if (!path.equals(buildingEntity.getAvatar())) {
//                    File file = new File("C://home/office" + buildingEntity.getAvatar());
//                    file.delete();
                }
            }
            byte[] bytes = Base64.decodeBase64(Arrays.toString(buildingDTO.getImageBase64().getBytes()));
            uploadFileUtils.writeOrUpdate(path, bytes);
            buildingEntity.setAvatar(path);
        }
    }
}
