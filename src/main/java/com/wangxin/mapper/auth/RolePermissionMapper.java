package com.wangxin.mapper.auth;

import com.wangxin.entity.auth.RolePermission;
import com.wangxin.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionMapper extends BaseMapper<String, RolePermission> {

    public RolePermission findRolePermission(RolePermission per);

}
