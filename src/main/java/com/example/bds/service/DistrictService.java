package com.example.bds.service;

import com.example.bds.enumDefine.DistrictEnum;

import java.util.Map;

public interface DistrictService {
    Map<DistrictEnum, String> getDistricts();
}
