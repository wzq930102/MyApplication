package com.jtvr.model;

/**
 * Created by yy on 2017/4/7.
 */
public class CodeBean {

    /**
     * status : 99
     * msg : 获取成功
     */

    private String status;
    private String msg;
    private String errmsg;
    private String keepCode;

    public String getKeepCode() {
        return keepCode;
    }

    public void setKeepCode(String keepCode) {
        this.keepCode = keepCode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
