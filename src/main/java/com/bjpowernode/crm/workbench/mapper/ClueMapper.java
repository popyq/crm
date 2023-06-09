package com.bjpowernode.crm.workbench.mapper;

import com.bjpowernode.crm.workbench.domain.Clue;

public interface ClueMapper {


    /**
     * 保存创建的线索
     * @param clue
     * @return
     */
    int insertClue(Clue clue);


    /**
     * 根据id查询线索的明细
     * @param id
     * @return
     */
    Clue selectClueDetailById(String id);
}