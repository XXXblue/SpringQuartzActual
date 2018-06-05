package com.xjy.pojo.vo;

import com.xjy.PageUtil.PageQuery;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/511:24
 * @Description:
 * @Modified By:
 */
public class ScheduleJobQueryVo {
    private ScheduleJobEntityCustom scheduleJobEntityCustom;
    private PageQuery pageQuery;

    public PageQuery getPageQuery() {
        return pageQuery;
    }

    public void setPageQuery(PageQuery pageQuery) {
        this.pageQuery = pageQuery;
    }

    public ScheduleJobEntityCustom getScheduleJobEntityCustom() {
        return scheduleJobEntityCustom;
    }

    public void setScheduleJobEntityCustom(ScheduleJobEntityCustom scheduleJobEntityCustom) {
        this.scheduleJobEntityCustom = scheduleJobEntityCustom;
    }
}
