package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.ClueRemark;

import java.util.List;

/**
 * @Author potato
 * @PackageName:com.bjpowernode.crm.workbench.service
 * @Description: TODO
 * @Date 2023-04-18 22:55
 */
public interface ClueRemarkService {

    List<ClueRemark> queryClueRemarkForDetailByClueId(String clueId);

}
