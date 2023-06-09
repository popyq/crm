package com.bjpowernode.crm.workbench.service;


import com.bjpowernode.crm.workbench.domain.ActivityRemark;

import java.util.List;

/**
 * @Author potato
 * @PackageName:com.bjpowernode.crm.workbench.service
 * @Description: TODO
 * @Date 2023-04-16 16:37
 */
public interface ActivityRemarkService {

    /**
     * 根据activity查询该市场活动下所有备注的明细信息
     * @param activityId
     * @return
     */
    List<ActivityRemark> queryActivityRemarkForDetailByActivityId(String activityId);

    /**
     * 保存创建的市场活动备注
     */
    int saveCreateActivityRemark(ActivityRemark activityRemark);

    /**
     * 根据id删除市场活动备注
     * @param id
     * @return
     */
    int deleteActivityRemarkById(String id);

    /**
     * 保存修改的市场活动备注
     * @param remark
     * @return
     */
    int saveEditActivityRemark(ActivityRemark remark);

}
