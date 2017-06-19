package com.jtvr.model;

/**
 * Created by jt on 2017/4/9.
 */
public class SuccedInfo {

    /**
     * status : 99
     * msg : 修改成功
     */

    private String status;
    private String msg;
    private String errmsg;

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
