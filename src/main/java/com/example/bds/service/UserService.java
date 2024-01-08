package com.example.bds.service;

import com.example.bds.dto.StaffDTO;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<StaffDTO> getStaffEnable(Map<String, Object> params);
}
