package com.jtvr.model;

/**
 * Created by jt on 2017/4/9.
 */
public class ImgBean {

    /**
     * status : 99
     * business : {"img":"http://192.168.1.110:8080/vrtext/upload/user/2017/04/09/20170409142412422_small.png"}
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
         * img : http://192.168.1.110:8080/vrtext/upload/user/2017/04/09/20170409142412422_small.png
         */

        private String img;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
