package com.xjy.service.impl;

import com.xjy.dao.ScheduleJobEntityMapper;
import com.xjy.pojo.ScheduleJobEntity;
import com.xjy.service.CleanService;
import com.xjy.utils.CornUtil;
import com.xjy.utils.ScheduleUtils;
import com.xjy.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/1110:38
 * @Description:
 * @Modified By:
 */
@Service("cleanService")
@Slf4j
public class CleanServiceImpl implements CleanService{
    /**
     * 定时清理过期任务
     */
    public void clean(){
        log.info("begin==============================clean");
        ScheduleJobEntityMapper scheduleJobEntityMapper = (ScheduleJobEntityMapper) SpringContextUtils.getBean("scheduleJobEntityMapper");
        Scheduler scheduler = (Scheduler) SpringContextUtils.getBean("scheduler");
        List<ScheduleJobEntity> scheduleJobEntityList = scheduleJobEntityMapper.queryAllScheduleJobEntity();
        for(ScheduleJobEntity scheduleJobEntity:scheduleJobEntityList){
            Date date = CornUtil.getCronToDate(scheduleJobEntity.getCronExpression());
            if(date!=null&&date.before(new Date())){
                ScheduleUtils.deleteScheduleJob(scheduler,scheduleJobEntity.getJobId());
                scheduleJobEntityMapper.deleteByPrimaryKey(scheduleJobEntity.getJobId());
                log.info("delete============================over time");
            }
        }
    }
}
