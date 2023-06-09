package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.Clue;

/**
 * @Author potato
 * @PackageName:com.bjpowernode.crm.workbench.service
 * @Description: TODO
 * @Date 2023-04-17 22:00
 */
public interface ClueService {

    /**
      重写方法不能缩小被重写方法的访问权限
     * 保存创建的线索
     * @param clue
     * @return
     */
    int saveCreateClue(Clue clue);

    /**
     * 根据id查询线索的明细
     * @param id
     * @return
     */
    Clue queryClueForDetailById(String id);


}
