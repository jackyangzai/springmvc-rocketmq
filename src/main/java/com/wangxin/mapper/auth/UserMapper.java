package com.wangxin.mapper.auth;

import java.util.List;

import com.wangxin.entity.auth.User;
import com.wangxin.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<String, User> {


    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return user 用户
     */
    public User findUserByName(String username);

    /**
     * 查询店铺所有用户
     *
     * @param organizeId 店铺ID
     * @return
     * @author wangxin
     */
    public List<User> findUserByShop(String organizeId);

    /**
     * 查询组织下所有客服员工
     *
     * @return
     */
    public List<User> findUsers();

    /**
     * 根据条件（店铺、名称）查询客服人员
     *
     * @param shopId  店铺ID
     * @param empName 客服人员名称
     * @return
     */
    public List<User> findEmp(String roleCode, Integer status, String shopId, String empName);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return user 用户
     */
    public List<User> findUserByRoleCode(@Param("roleCode") String username);

}
