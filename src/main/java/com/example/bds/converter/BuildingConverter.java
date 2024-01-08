package com.example.bds.converter;

import com.example.bds.dto.BuildingDTO;
import com.example.bds.entity.BuildingEntity;
import com.example.bds.entity.RentAreaEntity;
import com.example.bds.enumDefine.DistrictEnum;
import com.example.bds.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {
    @Autowired
    private ModelMapper modelMapper;

    public BuildingDTO convertToDto (BuildingEntity entity, List<RentAreaEntity> rentAreaEntities){
        BuildingDTO result = modelMapper.map(entity, BuildingDTO.class);
        StringBuilder address = new StringBuilder("");
        address.append(!StringUtils.isNullOrEmpty(entity.getStreet())? entity.getStreet()+"-":"");
        address.append(!StringUtils.isNullOrEmpty(entity.getWard())? entity.getWard()+"-":"");
        address.append(!StringUtils.isNullOrEmpty(entity.getDistrictCode()) ? DistrictEnum.valueOf(entity.getDistrictCode()).getName():"");
        result.setAddress(address.toString());
        if(rentAreaEntities != null){
            String rentareaStr = rentAreaEntities.stream().map(item -> item.getValue().toString()).collect(Collectors.joining(","));
            result.setRentArea(rentareaStr);
        }
        result.setImage(entity.getAvatar());
        return result;
    }

    public BuildingEntity convertToEntity (BuildingDTO dto){
        BuildingEntity result = modelMapper.map(dto,BuildingEntity.class);
        result.setAvatar(dto.getImage());
        return result;
    }
}
