package com.xjy.controller;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import com.xjy.PageUtil.PageQuery;
import com.xjy.PageUtil.PageResult;
import com.xjy.PageUtil.PageResultUtil;
import com.xjy.PageUtil.Result;
import com.xjy.common.Constant;
import com.xjy.pojo.ScheduleJobEntity;
import com.xjy.pojo.vo.ScheduleJobQueryVo;
import com.xjy.service.ScheduleJobService;
import com.xjy.utils.ScheduleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


/**
 * @Author: XBlue
 * @Date: Create in 2018/6/310:01
 * @Description:
 * @Modified By:
 */
@Controller
public class TestController {
    @Autowired
    private ScheduleJobService scheduleJobService;

    @ResponseBody
    @RequestMapping("/add")
    public Result add(ScheduleJobQueryVo scheduleJobQueryVo){
        //校验省略
        scheduleJobService.save(scheduleJobQueryVo.getScheduleJobEntityCustom());
        return new Result(true,"提交成功",200);
    }


    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(@RequestBody Long[]ids){
        if(ids!=null&&ids.length>0){
            scheduleJobService.deleteBatch(ids);
        }
        return  new Result(true,"提交成功",200);
    }

//    更新单个任务
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Result update(ScheduleJobQueryVo scheduleJobQueryVo){
        if(scheduleJobQueryVo.getScheduleJobEntityCustom()!=null&&scheduleJobQueryVo.getScheduleJobEntityCustom().getJobId()!=null){
            scheduleJobService.update(scheduleJobQueryVo.getScheduleJobEntityCustom());
        }
        return  new Result(true,"提交成功",200);
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public Result updateStates(@RequestParam("id") Long[]ids, String status){
        if (status != null && status.equals(String.valueOf(Constant.ScheduleStatus.NORMAL.getValue()))){
            scheduleJobService.resume(ids);
        }else if(status != null && status.equals(String.valueOf(Constant.ScheduleStatus.PAUSE.getValue()))){
            scheduleJobService.pause(ids);
        }
        return new Result(true,"success",200);
    }

    @RequestMapping("/list")
    public ModelAndView listPage(){
        return new ModelAndView("list");
    }

    @RequestMapping("/listData")
    @ResponseBody
    public PageResult listPageData(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10")int limit, ScheduleJobQueryVo scheduleJobQueryVo){
        PageQuery pageQuery= new PageQuery();
        pageQuery.setLimit(limit);
        pageQuery.setPage(page);
        scheduleJobQueryVo.setPageQuery(pageQuery);
        scheduleJobService.queryList(scheduleJobQueryVo);
        return PageResultUtil.success(scheduleJobService.queryList(scheduleJobQueryVo),scheduleJobService.queryListCount(scheduleJobQueryVo));
    }

    @RequestMapping("/jobForm/{jobId}")
    public ModelAndView jobForm(@PathVariable("jobId")Long jobId){
        ModelAndView mv = null;
        if(jobId==-1L){
            mv = new ModelAndView("jobForm");
            mv.addObject("flag",false);
            return mv;
        }
        ScheduleJobEntity scheduleJobEntity = scheduleJobService.selectScheduleJobEntityByJobId(jobId);
        mv = new ModelAndView("jobForm");
        mv.addObject("scheduleJobEntity",scheduleJobEntity);
        mv.addObject("flag",true);
        return mv;
    }

//    测试springmvc接收map
    @RequestMapping("/kk")
    @ResponseBody
    public String result(@RequestBody Map<String, Object> params){
        return "ok";
    }

//    定时清理任务
    @RequestMapping("/time")
    @ResponseBody
    public String time(){
        scheduleJobService.clean();
        return "ok";
    }
}
