package com.bjpowernode.crm.commons.domain;

import lombok.Data;

/**
 * @Author potato
 * @PackageName:com.bjpowernode.crm.commons.domain
 * @Description: TODO
 * @Date 2023-04-03 23:58
 */
@Data
public class ReturnObject {

    private String  code;  // 处理成功获失败的标记; 1---成功, 0---失败

    private String message; // 提示信息

    private Object retData; // 其他数据

}
