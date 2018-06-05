package com.xjy.PageUtil;

import com.alibaba.druid.sql.PagerUtils;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/511:51
 * @Description:
 * @Modified By:
 */
public class PageResultUtil {
    public static PageResult success(Object o,int total){
        PageResult pageResult = new PageResult();
        pageResult.setCode(0);
        pageResult.setCount(total);
        pageResult.setData(o);
        pageResult.setMsg("success");
        return pageResult;
    }
}
