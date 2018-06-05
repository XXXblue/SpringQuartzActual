package com.xjy.utils;

import com.xjy.pojo.ScheduleJobEntity;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.QuartzJobBean;
import java.util.concurrent.Future;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/310:56
 * @Description:
 * @Modified By:
 */
@Slf4j
public class ScheduleJob extends QuartzJobBean {
//    这里把执行任务变成反射，这个类值负责时间到了反射分配任务给线程去执行
//    private ExecutorService service = Executors.newSingleThreadExecutor();
    private static ThreadPoolTaskExecutor taskExecutor;
    static{
        taskExecutor = (ThreadPoolTaskExecutor) SpringContextUtils.getBean("taskExecutor");
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ScheduleJobEntity scheduleJob = (ScheduleJobEntity) jobExecutionContext.getMergedJobDataMap()
                .get(ScheduleJobEntity.JOB_PARAM_KEY);
        //获取spring bean 这个地方获取bean是为了做日志处理
        //任务开始时间
        long startTime = System.currentTimeMillis();
        try {
            //执行任务
            log.info("任务开始");
            log.info("任务准备执行，任务ID：" + scheduleJob.getJobId());
            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(),
                    scheduleJob.getMethodName(), scheduleJob.getParams());
            //任务分发给异步线程池去执行，解决了任务执行阻塞影响性能
            //应该在调度器查询的sql语句加缓存，也可以提高性能
            Future<?> future = taskExecutor.submit(task);
            //任务执行完再继续往下指定
            future.get();
            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            log.info("任务执行完毕，任务ID：" + scheduleJob.getJobId() + "  总共耗时：" + times + "毫秒");
            //记录数据库日志============自己添加
        } catch (Exception e) {
            log.error("任务执行失败，任务ID：" + scheduleJob.getJobId(), e);
            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            //记录数据库日志============自己添加
        }finally {
            //一定要保存日志信息到数据库=========自己添加
            //scheduleJobLogService.save(log);
        }
        log.info("任务结束");
    }
}
