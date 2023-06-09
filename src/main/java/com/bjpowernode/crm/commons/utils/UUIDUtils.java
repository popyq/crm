package com.bjpowernode.crm.commons.utils;

import java.util.UUID;

/**
 * @Author potato
 * @PackageName:com.bjpowernode.crm.commons.utils
 * @Description: 生成UUID工具类
 * @Date 2023-04-12 11:00
 */

public class UUIDUtils {

    /**
     *  获取UUID的值
     *  @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

}
