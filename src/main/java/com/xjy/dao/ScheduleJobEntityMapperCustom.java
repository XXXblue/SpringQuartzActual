package com.xjy.dao;

import com.xjy.pojo.vo.ScheduleJobEntityCustom;
import com.xjy.pojo.vo.ScheduleJobQueryVo;

import java.util.List;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/511:36
 * @Description:
 * @Modified By:
 */
public interface ScheduleJobEntityMapperCustom {

    public List<ScheduleJobEntityCustom> queryScheduleJobEntityCustom(ScheduleJobQueryVo scheduleJobQueryVo);

    public int queryScheduleJobEntityCustomCount(ScheduleJobQueryVo scheduleJobQueryVo);

}
