package com.wangxin.service.auth.impl;

import java.util.List;

import com.wangxin.common.exception.BusinessException;
import com.wangxin.common.utils.UUIDUtil;
import com.wangxin.entity.auth.Permission;
import com.wangxin.mapper.auth.PermissionMapper;
import com.wangxin.service.auth.PermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> getPermissions(String userId) {
        return permissionMapper.findPermissionByUserId(userId);
    }

    @Override
    public void addPermission(Permission permission) {
        if (permission == null || StringUtils.isBlank(permission.getKey()) || StringUtils.isBlank(permission.getParentKey()) || StringUtils.isBlank(permission.getName())) {
            throw new BusinessException("## 创建菜单出错；菜单项数据不完整，无法进行创建。");
        }
        permission.setId(UUIDUtil.getRandom32PK());
        permissionMapper.insert(permission);
    }

}
