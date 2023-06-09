package com.bjpowernode.crm.settings.service;

import com.bjpowernode.crm.settings.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author potato
 * @PackageName:com.bjpowernode.crm.settings.service
 * @Description: TODO
 * @Date 2023-04-03 23:17
 */
@Service
public interface UserService {

    /**
     * 根据账号和密码查询用户
     * @param map
     * @return
     */
    User queryUserByLoginActAndPwd(Map<String,Object> map);

    /**
     * 查询所有用户
     * @return
     */
    List<User> queryAllUsers();

}
