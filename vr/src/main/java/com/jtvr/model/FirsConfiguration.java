package com.jtvr.model;

import java.util.List;

/**
 * Created by jt on 2017/4/18.
 */
public class FirsConfiguration {

    /**
     * busness : {"list":[{"fireCode":"6954801d-283c-40d2-afc6-20d5f303928e","buyFire":200,"money":20,"giveFire":20},{"fireCode":"70d83f20-0978-4ebb-83db-bd2198d41444","buyFire":500,"money":50,"giveFire":50},{"fireCode":"8fdaac21-3b92-41ac-8cc2-8293b659d951","buyFire":1000,"money":100,"giveFire":120},{"fireCode":"d168c369-3bb9-43bb-b183-dd0f7f3bbc7e","buyFire":2000,"money":200,"giveFire":300},{"fireCode":"7688e016-a81b-4dd4-8472-4b90568a8894","buyFire":3000,"money":300,"giveFire":400}]}
     * status : 99
     * msg : 获取成功
     */

    private BusnessBean busness;
    private String status;
    private String msg;

    public BusnessBean getBusness() {
        return busness;
    }

    public void setBusness(BusnessBean busness) {
        this.busness = busness;
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

    public static class BusnessBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * fireCode : 6954801d-283c-40d2-afc6-20d5f303928e
             * buyFire : 200
             * money : 20
             * giveFire : 20
             */

            private String fireCode;
            private int buyFire;
            private int money;
            private int giveFire;

            public String getFireCode() {
                return fireCode;
            }

            public void setFireCode(String fireCode) {
                this.fireCode = fireCode;
            }

            public int getBuyFire() {
                return buyFire;
            }

            public void setBuyFire(int buyFire) {
                this.buyFire = buyFire;
            }

            public int getMoney() {
                return money;
            }

            public void setMoney(int money) {
                this.money = money;
            }

            public int getGiveFire() {
                return giveFire;
            }

            public void setGiveFire(int giveFire) {
                this.giveFire = giveFire;
            }
        }
    }
}
