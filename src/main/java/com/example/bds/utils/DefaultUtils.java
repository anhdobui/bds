package com.example.bds.utils;

import com.example.bds.constant.DefaultConstant;
import com.example.bds.entity.RoleEntity;

public class DefaultUtils {
    public static RoleEntity getRoleDefault(){
        RoleEntity roleDefault = new RoleEntity();
        roleDefault.setCode(DefaultConstant.CODE_ROLE_STAFF);
        roleDefault.setName(DefaultConstant.NAME_ROLE_STAFF);
        return roleDefault;
    }
}
