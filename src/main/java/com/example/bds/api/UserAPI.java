package com.example.bds.api;

import com.example.bds.dto.ResponseDTO;
import com.example.bds.dto.StaffDTO;
import com.example.bds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserAPI {

    @Autowired
    private UserService userService;
    @GetMapping("/all-staff")
    public ResponseDTO<List<StaffDTO>> loadStaff(@RequestParam(required = false) Map<String, Object> params){
        ResponseDTO<List<StaffDTO>> result = new ResponseDTO<>();
        List<StaffDTO> staffs = userService.getStaffEnable(params);
        result.setMessage("Lấy thành công");
        result.setData(staffs);
        return result;
    }

}
