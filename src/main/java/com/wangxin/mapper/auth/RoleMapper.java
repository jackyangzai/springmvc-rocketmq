package com.wangxin.mapper.auth;


import com.wangxin.entity.auth.Role;
import com.wangxin.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper extends BaseMapper<String, Role> {

    /**
     * 根据用户查询对应所有角色
     *
     * @param userId 用户
     * @return roles 所有角色
     */
    public List<Role> findRoleByUserId(String userId);

    /**
     * 根据编码查询角色
     *
     * @param code 角色编码
     * @return
     */
    public Role findRoleByCode(String code);

}
