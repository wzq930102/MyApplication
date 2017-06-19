package com.jtvr.model;

/**
 * Created by yy on 2017/4/17.
 */
public class SignBean {


    /**
     * business : {"signCode":"7596f122-988d-4b06-bc67-a5357ad09bcd","sugnDay":"","fire":"1","accruedFile":0,"serialSign":0}
     * status : 99
     * msg : 请求成功
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
        /**
         * signCode : 7596f122-988d-4b06-bc67-a5357ad09bcd
         * sugnDay :
         * fire : 1
         * accruedFile : 0
         * serialSign : 0
         */

        private String signCode;
        private String sugnDay;
        private String fire;
        private int accruedFile;
        private int serialSign;

        public String getSignCode() {
            return signCode;
        }

        public void setSignCode(String signCode) {
            this.signCode = signCode;
        }

        public String getSugnDay() {
            return sugnDay;
        }

        public void setSugnDay(String sugnDay) {
            this.sugnDay = sugnDay;
        }

        public String getFire() {
            return fire;
        }

        public void setFire(String fire) {
            this.fire = fire;
        }

        public int getAccruedFile() {
            return accruedFile;
        }

        public void setAccruedFile(int accruedFile) {
            this.accruedFile = accruedFile;
        }

        public int getSerialSign() {
            return serialSign;
        }

        public void setSerialSign(int serialSign) {
            this.serialSign = serialSign;
        }
    }
}
