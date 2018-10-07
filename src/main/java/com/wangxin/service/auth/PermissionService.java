package com.wangxin.service.auth;

import java.util.List;

import com.wangxin.entity.auth.Permission;

/**
 * @author Vincent.wang
 *
 */
public interface PermissionService {

    /**
     * 查询用户所能访问的所有菜单
     *
     * @param userId
     *            用户ID
     * @return permissions 菜单
     */
    public List<Permission> getPermissions(String userId);

    /**
     * 添加 菜单
     *
     * @param permission
     *            菜单项
     */
    public void addPermission(Permission permission);
}
