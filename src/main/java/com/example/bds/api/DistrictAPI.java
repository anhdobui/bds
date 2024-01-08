package com.example.bds.api;

import com.example.bds.dto.ResponseDTO;
import com.example.bds.enumDefine.DistrictEnum;
import com.example.bds.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/district")
public class DistrictAPI {

    @Autowired
    private DistrictService districtService;
    @GetMapping
    public ResponseDTO<Map<DistrictEnum,String>> getDistricts(){
        ResponseDTO<Map<DistrictEnum,String>> result = new ResponseDTO<>();
        result.setMessage("Lấy thành công");
        result.setData(districtService.getDistricts());
        return result;
    }

}
