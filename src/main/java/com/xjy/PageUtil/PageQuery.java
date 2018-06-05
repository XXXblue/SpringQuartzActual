package com.xjy.PageUtil;


/**
 * @Author: XBlue
 * @Date: Create in 2018/1/3121:26
 * @Description:
 * @Modified By:
 */
public class PageQuery {


    private int page=1;


    private int limit=10;

    private int offset;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }


    public int getOffset(){
        return  (page-1)*limit;
    }
}
