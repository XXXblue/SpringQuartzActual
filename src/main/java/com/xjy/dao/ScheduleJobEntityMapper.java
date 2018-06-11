package com.xjy.dao;

import com.xjy.pojo.ScheduleJobEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScheduleJobEntityMapper {
    int deleteByPrimaryKey(Long jobId);

    int insert(ScheduleJobEntity record);

    int insertSelective(ScheduleJobEntity record);

    ScheduleJobEntity selectByPrimaryKey(Long jobId);

    int updateByPrimaryKeySelective(ScheduleJobEntity record);

    int updateByPrimaryKey(ScheduleJobEntity record);

    void updateBatch(@Param("ids") List<Long> ids, @Param("status") int status);

    List<ScheduleJobEntity> queryAllScheduleJobEntity();
}