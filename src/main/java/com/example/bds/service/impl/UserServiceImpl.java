package com.example.bds.service.impl;

import com.example.bds.converter.UserConverter;
import com.example.bds.dto.StaffDTO;
import com.example.bds.entity.UserEntity;
import com.example.bds.repository.BuildingRepository;
import com.example.bds.repository.CustomerRepository;
import com.example.bds.repository.UserRepository;
import com.example.bds.service.UserService;
import com.example.bds.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserConverter userConverter;
    @Override
    public List<StaffDTO> getStaffEnable(Map<String, Object> params) {
        List<StaffDTO> result = new ArrayList<>();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1,"STAFF");
        staffs.forEach(item -> {
            boolean isExits = false;
            if(params.get("buildingId")!=null || params.get("customerId")!=null ){
                if(params.get("buildingId")!= null ){
                    Long buildingid = MapUtils.getObject(params,"buildingId",Long.class);
                    isExits = buildingRepository.existsByIdAndStaffs_Id(buildingid,item.getId());
                }
                if(params.get("customerId")!=null){
                    Long customerid = MapUtils.getObject(params,"customerId",Long.class);
                    isExits = customerRepository.existsByIdAndStaffs_Id(customerid,item.getId());
                }

            }

            StaffDTO staffDTO = userConverter.convertToStaffDto(item,isExits);
            result.add(staffDTO);
        });
        return result;
    }
}
