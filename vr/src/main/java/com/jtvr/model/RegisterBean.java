package com.jtvr.model;

/**
 * Created by yy on 2017/4/7.
 */
public class RegisterBean {

    /**
     * status : 99
     * vip : 0
     * fires : 0
     * msg : 注册成功
     * business : {"name":"15562792866","nick":"155****2866","userCode":"88cefd5b-ac51-4a89-97d4-85bae699c760","token":"c67e2268-4eed-495d-9965-8f60c67144a2"}
     */

    private String status;
    private String vip;
    private String fires;
    private String msg;
    private String errmsg;
    private BusinessBean business;

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

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getFires() {
        return fires;
    }

    public void setFires(String fires) {
        this.fires = fires;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BusinessBean getBusiness() {
        return business;
    }

    public void setBusiness(BusinessBean business) {
        this.business = business;
    }

    public static class BusinessBean {
        /**
         * name : 15562792866
         * nick : 155****2866
         * userCode : 88cefd5b-ac51-4a89-97d4-85bae699c760
         * token : c67e2268-4eed-495d-9965-8f60c67144a2
         */

        private String name;
        private String nick;
        private String userCode;
        private String token;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
