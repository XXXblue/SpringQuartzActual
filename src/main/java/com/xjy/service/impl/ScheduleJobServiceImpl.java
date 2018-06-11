package com.xjy.service.impl;

import com.xjy.common.Constant;
import com.xjy.dao.ScheduleJobEntityMapper;
import com.xjy.dao.ScheduleJobEntityMapperCustom;
import com.xjy.pojo.ScheduleJobEntity;
import com.xjy.pojo.vo.ScheduleJobEntityCustom;
import com.xjy.pojo.vo.ScheduleJobQueryVo;
import com.xjy.service.ScheduleJobService;
import com.xjy.utils.CornUtil;
import com.xjy.utils.ScheduleUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("scheduleJobService")
public class ScheduleJobServiceImpl implements ScheduleJobService {
	@Autowired
    private Scheduler scheduler;
	@Autowired
	private ScheduleJobEntityMapper scheduleJobEntityMapper;
	@Autowired
	private ScheduleJobEntityMapperCustom scheduleJobEntityMapperCustom;
	/**
	 * 项目启动时，初始化定时器，这一步的作用目前看不出
	 */
//	@PostConstruct
//	public void init(){
//		List<ScheduleJobEntity> scheduleJobList = schedulerJobDao.queryList(new HashMap<String, Object>());
//		for(ScheduleJobEntity scheduleJob : scheduleJobList){
//			CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
//			//如果不存在，则创建
//			if(cronTrigger == null) {
//				ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
//			}else {
//				ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
//			}
//		}
//	}
	
	@Override
	public ScheduleJobEntity queryObject(Long jobId) {

		return scheduleJobEntityMapper.selectByPrimaryKey(jobId);
	}
	//list
	@Override
	public List<ScheduleJobEntityCustom> queryList(ScheduleJobQueryVo scheduleJobQueryVo) {
		return scheduleJobEntityMapperCustom.queryScheduleJobEntityCustom(scheduleJobQueryVo);
	}
	//数量
	public int queryListCount(ScheduleJobQueryVo scheduleJobQueryVo){
		return scheduleJobEntityMapperCustom.queryScheduleJobEntityCustomCount(scheduleJobQueryVo);
	}


	//新增任务
	@Transactional
	public void save(ScheduleJobEntity scheduleJob) {
		scheduleJob.setCreateTime(new Date());
		scheduleJob.setStatus(scheduleJob.getStatus());
		scheduleJobEntityMapper.insertSelective(scheduleJob);
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
    }
	
	//更新某个任务
	@Transactional
	public void update(ScheduleJobEntity scheduleJob) {
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
		scheduleJobEntityMapper.updateByPrimaryKeySelective(scheduleJob);
    }

	//批量删除
	@Transactional
    public void deleteBatch(Long[] jobIds) {
    	for(Long jobId : jobIds){
    		ScheduleUtils.deleteScheduleJob(scheduler, jobId);
			scheduleJobEntityMapper.deleteByPrimaryKey(jobId);
    	}
	}

	//运行
	@Transactional
    public void run(Long[] jobIds) {
    	for(Long jobId : jobIds){
    		ScheduleUtils.run(scheduler, queryObject(jobId));
    	}
    }
	//暂停
	@Transactional
    public void pause(Long[] jobIds) {
        for(Long jobId : jobIds){
    		ScheduleUtils.pauseJob(scheduler, jobId);
    	}

    	updateBatch(jobIds, Constant.ScheduleStatus.PAUSE.getValue());
    }
	//继续
	@Transactional
    public void resume(Long[] jobIds) {
    	for(Long jobId : jobIds){
    		ScheduleUtils.resumeJob(scheduler, jobId);
    	}

    	updateBatch(jobIds, Constant.ScheduleStatus.NORMAL.getValue());
    }

	@Override
	public ScheduleJobEntity selectScheduleJobEntityByJobId(Long jobId) {
		return scheduleJobEntityMapper.selectByPrimaryKey(jobId);
	}

	@Override
	public void clean() {
		ScheduleJobEntity scheduleJobEntity = new ScheduleJobEntity();
		scheduleJobEntity.setBeanName("cleanService");
		scheduleJobEntity.setMethodName("clean");
		Date date = CornUtil.getCronToDate("24 49 14 11 06 ? 2018");
		scheduleJobEntity.setCronExpression(CornUtil.getCron(date));
		scheduleJobEntity.setCreateTime(new Date());
		scheduleJobEntity.setStatus("1");
		scheduleJobEntityMapper.insertSelective(scheduleJobEntity);
		ScheduleUtils.createScheduleJob(scheduler, scheduleJobEntity);
	}

	//这个是给上面那两个集群任务  关于多个job任务状态的更新
    public void updateBatch(Long[] jobIds, int status){
		List<Long> ids = Arrays.asList(jobIds);
		//这里省略了校验任务是否存在
    	scheduleJobEntityMapper.updateBatch(ids,status);
    }


}
