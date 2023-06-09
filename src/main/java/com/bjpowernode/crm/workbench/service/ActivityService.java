package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.Activity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author potato
 * @PackageName:com.bjpowernode.crm.workbench.service
 * @Description: 市场活动
 * @Date 2023-04-12 10:33
 */
@Service
public interface ActivityService {

    /**
     * 保存创建的市场活动
     * @param activity
     * @return
     */
    int saveCreateActivity(Activity activity);

    /**
     *  根据条件分页查询市场活动的列表
     * @param map
     * @return
     */
    List<Activity> queryActivityByConditionForPage(Map<String,Object> map);

    /**
     * 根据条件查询市场活动的总条数
     * @param map
     * @return
     */
    int queryCountOfActivityByCondition(Map<String,Object> map);

    /**
     * 批量删除市场活动
     * @param ids
     * @return
     */
    int deleteActivityByIds(String [] ids);


    /**
     * 根据id查询市场活动的信息
     * @param id
     * @return
     */
    Activity queryActivityById(String id);

    /**
     * 保存修改的市场活动
     * @param activity
     * @return
     */
    int saveEditActivity(Activity activity);

    /**
     * 查询所有的市场活动
     * @return
     */
    List<Activity> queryAllActivitys();

    /**
     * 批量保存创建的市场活动
     * @param activityList
     * @return
     */
    int saveCreateActivityByList(List<Activity> activityList);

    /**
     * 根据id来查询市场活动的明细信息
     * @param id
     * @return
     */
    Activity queryActivityForDetailById(String id);


    /**
     * 根据clueId查询该线索相关关联的市场活动的明确信息
     * @param clueId
     * @return
     */
    List<Activity> queryActivityForDetailByClueId(String clueId);


    /**
     * 根据name来模糊查询市场活动,并且把已经跟clueId关联过的市场活动排除
     * @param map
     * @return
     */
    List<Activity> queryActivityForDetailByNameClueId(Map<String,Object> map);

    /**
     *  根据ids查询市场活动的明细信息
     * @param ids
     * @return
     */
    List<Activity> queryActivityForDetailByIds(String[] ids);
}
