package com.jtvr.model;

/**
 * Created by yy on 2017/4/20.
 */
public class VIPBean {
    private String title;
    private String num;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public VIPBean(String title, String num) {
        this.title = title;
        this.num = num;
    }
}
