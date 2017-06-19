package com.jtvr.model;

import java.util.List;

/**
 * Created by jt on 2017/4/21.
 */
public class BuyVipData {

    /**
     * business : {"list":[{"money":"10","month":1,"code":"adddb7e9-74a9-4559-8797-5d294d7fe55b","fire":"300"},{"money":"200","month":3,"code":"555c4222-2d2b-4de2-84aa-2f4c90caa1dd","fire":"3000"}]}
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
             * money : 10
             * month : 1
             * code : adddb7e9-74a9-4559-8797-5d294d7fe55b
             * fire : 300
             */

            private String money;
            private int month;
            private String code;
            private String fire;

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getFire() {
                return fire;
            }

            public void setFire(String fire) {
                this.fire = fire;
            }
        }
    }
}
