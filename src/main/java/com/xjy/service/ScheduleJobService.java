package com.xjy.service;


import com.xjy.pojo.ScheduleJobEntity;
import com.xjy.pojo.vo.ScheduleJobEntityCustom;
import com.xjy.pojo.vo.ScheduleJobQueryVo;

import java.util.List;
import java.util.Map;

/**
 * 类ScheduleJobService的功能描述:
 * 定时任务
 * @auther hxy
 * @date 2017-08-25 16:20:22
 */
public interface ScheduleJobService {

	/**
	 * 根据ID，查询定时任务
	 */
	ScheduleJobEntity queryObject(Long jobId);
	
	/**
	 * 查询定时任务列表
	 */
	List<ScheduleJobEntityCustom> queryList(ScheduleJobQueryVo scheduleJobQueryVo);
	
	/**
	 * 查询总数
	 */
	public int queryListCount(ScheduleJobQueryVo scheduleJobQueryVo);
	
	/**
	 * 保存定时任务
	 */
	void save(ScheduleJobEntity scheduleJob);
	
	/**
	 * 更新定时任务
	 */
	void update(ScheduleJobEntity scheduleJob);
	
	/**
	 * 批量删除定时任务
	 */
	void deleteBatch(Long[] jobIds);
	
	/**
	 * 批量更新定时任务状态
	 */
	void updateBatch(Long[] jobIds, int status);
	
	/**
	 * 立即执行
	 */
	void run(Long[] jobIds);
	
	/**
	 * 暂停运行
	 */
	void pause(Long[] jobIds);

	/**
	 * 恢复运行
	 */
	void resume(Long[] jobIds);


	ScheduleJobEntity selectScheduleJobEntityByJobId(Long jobId);

    void clean();
}
