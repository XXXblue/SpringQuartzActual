package com.xjy.PageUtil;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/515:27
 * @Description:
 * @Modified By:
 */
public class Result {
    private boolean ret;
    private String msg;
    private int code;

    public Result(boolean ret, String msg, int code) {
        this.ret = ret;
        this.msg = msg;
        this.code = code;
    }

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
