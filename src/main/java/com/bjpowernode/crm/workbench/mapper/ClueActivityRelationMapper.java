package com.bjpowernode.crm.workbench.mapper;

import com.bjpowernode.crm.workbench.domain.ClueActivityRelation;

import java.util.List;

public interface ClueActivityRelationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_activity_relation
     *
     * @mbggenerated Sat Nov 07 14:20:42 CST 2020
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_activity_relation
     *
     * @mbggenerated Sat Nov 07 14:20:42 CST 2020
     */
    int insert(ClueActivityRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_activity_relation
     *
     * @mbggenerated Sat Nov 07 14:20:42 CST 2020
     */
    int insertSelective(ClueActivityRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_activity_relation
     *
     * @mbggenerated Sat Nov 07 14:20:42 CST 2020
     */
    ClueActivityRelation selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_activity_relation
     *
     * @mbggenerated Sat Nov 07 14:20:42 CST 2020
     */
    int updateByPrimaryKeySelective(ClueActivityRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_activity_relation
     *
     * @mbggenerated Sat Nov 07 14:20:42 CST 2020
     */
    int updateByPrimaryKey(ClueActivityRelation record);

    /**
     * 批量保存创建的线索和市场活动的关联关系
     * @param list
     * @return
     */
    int insertClueActivityRelationByList(List<ClueActivityRelation> list);

    /**
     * 根据clueId和activityId删除线索和市场活动的关联关系
     * @param relation
     * @return
     */
    int deleteClueActivityRelationByClueIdActivityId(ClueActivityRelation relation);
}