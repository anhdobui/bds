package com.example.bds.service.impl;

import com.example.bds.enumDefine.DistrictEnum;
import com.example.bds.service.DistrictService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Override
    public Map<DistrictEnum, String> getDistricts() {
        Map<DistrictEnum, String> result = new LinkedHashMap<>();
        for (DistrictEnum district : DistrictEnum.values()) {
            result.put(district, district.getName());
        }
        return result;
    }
}
