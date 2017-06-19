package com.jtvr.model;

import java.util.List;

/**
 * Created by yy on 2017/4/1.
 */
public class MovieType {

    /**
     * status : 99
     * business : {"list":[{"firstCode":"","name":"精选","code":"f59206c0-9ed5-4c7b-9744-f27f60ed17b2"},{"firstCode":"power","name":"VIP","code":"7d751fe2-cc6c-4e26-ba2a-fae97aee8891"},{"firstCode":"type","name":"动作","code":"9d37ff98-397f-402d-8c19-e7561216815b"},{"firstCode":"type","name":"科幻","code":"42a2f1a4-f736-48c0-bb19-a01c69bcc8e8"},{"firstCode":"type","name":"动画","code":"0641f9e6-c0ea-4646-a475-174474ac76af"},{"firstCode":"type","name":"偶像","code":"56f7d2ac-4fb2-4759-b907-9d619ab7d303"},{"firstCode":"type","name":"偶像","code":"56f7d2ac-4fb2-4759-b907-9d619ab7d303"},{"firstCode":"type","name":"极限","code":"e3583a97-510b-4795-9393-df0651ca8be5"},{"firstCode":"type","name":"娱乐","code":"0061fffe-2f02-4bd0-acee-fad966b0c00e"},{"firstCode":"type","name":"恐怖","code":"fa472a5d-e97f-4788-bd9c-f6a8ca3d211e"}]}
     * msg : 请求成功
     */

    private String status;
    private BusinessBean business;
    private String msg;

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
             * firstCode :
             * name : 精选
             * code : f59206c0-9ed5-4c7b-9744-f27f60ed17b2
             */

            private String firstCode;
            private String name;
            private String code;

            public String getFirstCode() {
                return firstCode;
            }

            public void setFirstCode(String firstCode) {
                this.firstCode = firstCode;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }
        }
    }
}
