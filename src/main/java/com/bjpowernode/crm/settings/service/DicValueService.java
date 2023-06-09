package com.bjpowernode.crm.settings.service;

import com.bjpowernode.crm.settings.domain.DicValue;

import java.util.Dictionary;
import java.util.List;

/**
 * @Author potato
 * @PackageName:com.bjpowernode.crm.settings.service
 * @Description: TODO
 * @Date 2023-04-17 21:08
 */

public interface DicValueService {

    /**
     * 根据typeCode查询数据字典值
     * @param typeCode
     * @return
     */
    List<DicValue> queryDicValueByTypeCode(String typeCode);
}
