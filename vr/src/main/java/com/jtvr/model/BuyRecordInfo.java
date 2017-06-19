package com.jtvr.model;

import java.util.List;

/**
 * Created by jt on 2017/4/10.
 */
public class BuyRecordInfo {

    /**
     * business : {"list":[{"fires":"80100","money":"80","time":"2017-04-09 16:43:43.0"},{"fires":"80100","money":"20","time":"2017-04-09 16:58:32.0"},{"fires":"80100","money":"80","time":"2017-04-09 16:42:04.0"},{"fires":"80100","money":"20","time":"2017-04-10 10:38:15.0"}]}
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
             * fires : 80100
             * money : 80
             * time : 2017-04-09 16:43:43.0
             */

            private String fires;
            private String money;
            private String time;

            public String getFires() {
                return fires;
            }

            public void setFires(String fires) {
                this.fires = fires;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
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
