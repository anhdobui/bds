package com.example.bds.converter;

import com.example.bds.dto.StaffDTO;
import com.example.bds.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserConverter {
    @Autowired
    private ModelMapper modelMapper;

    public StaffDTO convertToStaffDto(UserEntity entity, List<Long> staffIdsOfBuilding){
        StaffDTO result = new StaffDTO();
        result.setFullName(entity.getFullName());
        result.setStaffId(entity.getId());
        result.setChecked(staffIdsOfBuilding.contains(entity.getId()));
        return result;
    }
    public StaffDTO convertToStaffDto(UserEntity entity, boolean isExits){
        StaffDTO result = new StaffDTO();
        result.setFullName(entity.getFullName());
        result.setStaffId(entity.getId());
        result.setChecked(isExits);
        return result;
    }
}
