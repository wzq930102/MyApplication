package com.jtvr.model;

import java.util.List;

/**
 * Created by jt on 2017/4/14.
 */
public class expenceRecordBean {

    /**
     * business : {"list":[{"fires":"120","intro":"2016-02-03 12:30:20充值电影vip6个月","time":"2017-04-14 09:31:26.0"}]}
     * status : 99
     * msg : 获取成功
     */

    private BusinessBean business;
    private String status;
    private String msg;

    public BusinessBean getBusiness() {
        return business;
    }

    public void setBusiness(BusinessBean business) {
        this.business = business;
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

    public static class BusinessBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * fires : 120
             * intro : 2016-02-03 12:30:20充值电影vip6个月
             * time : 2017-04-14 09:31:26.0
             */

            private String fires;
            private String intro;
            private String time;

            public String getFires() {
                return fires;
            }

            public void setFires(String fires) {
                this.fires = fires;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }
}
