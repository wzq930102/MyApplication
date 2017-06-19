package com.jtvr.model;

/**
 * Created by yy on 2017/4/7.
 */
public class LoginBean {

    /**
     * status : 99
     * business : {"name":"18513826708","nick":"WW","img":"http://192.168.1.110:8080/vrtext/upload/user/2017/04/13/20170413110025011_small.png","userCode":"efc55ff7-bd06-4e0d-8ad2-72dabbc483c5","vip":"0","fires":"1101554","isvideo":"N","tokens":"8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92","token":"8b0c6195-ac68-4511-a058-2ccf5b44ca82"}
     */

    private String status;
    private BusinessBean business;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BusinessBean getBusiness() {
        return business;
    }

    public void setBusiness(BusinessBean business) {
        this.business = business;
    }

    public static class BusinessBean {
        /**
         * name : 18513826708
         * nick : WW
         * img : http://192.168.1.110:8080/vrtext/upload/user/2017/04/13/20170413110025011_small.png
         * userCode : efc55ff7-bd06-4e0d-8ad2-72dabbc483c5
         * vip : 0
         * fires : 1101554
         * isvideo : N
         * tokens : 8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92
         * token : 8b0c6195-ac68-4511-a058-2ccf5b44ca82
         */

        private String name;
        private String nick;
        private String img;
        private String userCode;
        private String vip;
        private String fires;
        private String isvideo;
        private String tokens;
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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
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

        public String getIsvideo() {
            return isvideo;
        }

        public void setIsvideo(String isvideo) {
            this.isvideo = isvideo;
        }

        public String getTokens() {
            return tokens;
        }

        public void setTokens(String tokens) {
            this.tokens = tokens;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
