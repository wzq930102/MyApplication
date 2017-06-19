package com.jtvr.model;

/**
 * Created by jt on 2017/4/14.
 */
public class VersionBean {

    /**
     * status : 99
     * business : {"code":"069ce981-2058-4edb-9e93-45d62393b33f","url":"app","version":"1.0.1","flag":"0","appDesc":"app设置"}
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
         * code : 069ce981-2058-4edb-9e93-45d62393b33f
         * url : app
         * version : 1.0.1
         * flag : 0
         * appDesc : app设置
         */

        private String code;
        private String url;
        private String version;
        private String flag;
        private String appDesc;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getAppDesc() {
            return appDesc;
        }

        public void setAppDesc(String appDesc) {
            this.appDesc = appDesc;
        }
    }
}
