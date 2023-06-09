package com.bjpowernode.crm.settings.mapper;

import com.bjpowernode.crm.settings.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @Author potato
 * @PackageName:com.bjpowernode.crm.settings.mapper
 * @Description: TODO
 * @Date 2023-04-03 23:01
 */

public interface UserMapper {

    /**
     * 根据账号和密码查询用户
     * @param map
     * @return
     */
    User selectUserByLoginActAndPwd(Map<String,Object> map);


    /**
     * 查询所有用户
     * @return
     */
    List<User> selectAllUsers();

}
