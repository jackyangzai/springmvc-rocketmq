package com.wangxin.service.auth.impl;

import java.util.List;

import com.wangxin.common.exception.BusinessException;
import com.wangxin.common.utils.UUIDUtil;
import com.wangxin.entity.auth.Permission;
import com.wangxin.entity.auth.Role;
import com.wangxin.entity.auth.RolePermission;
import com.wangxin.mapper.auth.PermissionMapper;
import com.wangxin.mapper.auth.RoleMapper;
import com.wangxin.mapper.auth.RolePermissionMapper;
import com.wangxin.service.auth.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {

    private final static Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    public void addRole(Role role) {
        if (role == null || StringUtils.isBlank(role.getName())) {
            return;
        }
        if (log.isDebugEnabled()) {
            log.debug("## 添加角色 : {}", role.getName());
        }
        Role r = findRoleByCode(role.getCode());
        if (r == null) {
            role.setId(UUIDUtil.getRandom32PK());
            roleMapper.insert(role);
        }
    }

    @Override
    public Role findRoleByCode(String code) {
        if (log.isDebugEnabled()) {
            log.debug("## 根据编码查询角色 :　{}", code);
        }
        return roleMapper.findRoleByCode(code);
    }

    @Override
    public List<Role> findRoleByUserId(String userId) {
        return roleMapper.findRoleByUserId(userId);
    }

    @Override
    public void addRolePermission(String roleCode, String permissionKey) {
        Role role = findRoleByCode(roleCode);
        if (role == null) {
            throw new BusinessException("## 给角色授权失败， 角色编码错误");
        }
        Permission permis = permissionMapper.findPermissionByKey(permissionKey);
        if (permis == null) {
            throw new BusinessException("## 给角色授权失败， 授权KEY错误");
        }

        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(role.getId());
        rolePermission.setPermissionId(permis.getId());

        RolePermission rp = rolePermissionMapper.findRolePermission(rolePermission);
        if (rp == null) {
            rolePermission.setId(UUIDUtil.getRandom32BeginTimePK());
            rolePermissionMapper.insert(rolePermission);
        }

    }
}
