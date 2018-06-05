package com.xjy.PageUtil;

/**
 * @Author: XBlue
 * @Date: Create in 2018/4/199:23
 * @Description:
 * @Modified By:
 */
public class PageResult {
    private Integer code;
    private String msg;
    private Integer count;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
