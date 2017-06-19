package com.jtvr.model;

import java.util.List;

/**
 * Created by jt on 2017/4/20.
 */
public class PlayCommentDetail {


    /**
     * business : {"list":[{"firstCommentCode":"1","firstIsSubject":"1","firstContent":"非常好","firstUpvote":"1","firstUser":"WW","firstImg":"http://192.168.1.110:8080/vrtext/upload/user/2017/04/13/20170413110025011_small.png","firstTime":"2017-04-19 16:49:13.0","secondComment":[{"secondCommentCode":"2","secondIsSubject":"1","secondContent":"非常好","secondCommentUser":"","secondTime":"2017-04-19 16:50:58.0","secondImg":"http://192.168.1.110:8080/vrtext/upload/user/2017/04/13/20170413110025011_small.png","secondUpvote":"1","secondUser":"WW"},{"secondCommentCode":"4","secondIsSubject":"0","secondContent":"非常好","secondCommentUser":"WW","secondTime":"2017-04-21 10:14:28.0","secondImg":"http://192.168.1.110:8080/vrtext/upload/user/2017/04/13/20170413155127003_small.png","secondUpvote":"0","secondUser":"哈哈哈"},{"secondCommentCode":"af513f44-5c11-4b96-a955-968a3583370f","secondIsSubject":null,"secondContent":"4323423423","secondCommentUser":"","secondTime":"2017-04-20 18:43:25.0","secondImg":"http://192.168.1.110:8080/vrtext/upload/user/2017/04/13/20170413110025011_small.png","secondUpvote":"0","secondUser":"WW"},{"secondCommentCode":"4efb6acc-4176-486f-bf93-fccc95815d9d","secondIsSubject":null,"secondContent":"123","secondCommentUser":"","secondTime":"2017-04-20 18:43:17.0","secondImg":"http://192.168.1.110:8080/vrtext/upload/user/2017/04/13/20170413110025011_small.png","secondUpvote":"0","secondUser":"WW"}]},{"firstCommentCode":"3","firstIsSubject":"0","firstContent":"非常好","firstUpvote":"0","firstUser":"WW","firstImg":"http://192.168.1.110:8080/vrtext/upload/user/2017/04/13/20170413110025011_small.png","firstTime":"2017-04-19 16:50:55.0","secondComment":[]},{"firstCommentCode":"e9b74bd9-9355-4818-b571-af2c5ae125a8","firstIsSubject":null,"firstContent":"好看的不的见","firstUpvote":"0","firstUser":"WW","firstImg":"http://192.168.1.110:8080/vrtext/upload/user/2017/04/13/20170413110025011_small.png","firstTime":"2017-04-20 16:12:21.0","secondComment":[]},{"firstCommentCode":"3eba5826-67cd-4400-9c0b-bd303e9c964f","firstIsSubject":null,"firstContent":"哈哈","firstUpvote":"0","firstUser":"WW","firstImg":"http://192.168.1.110:8080/vrtext/upload/user/2017/04/13/20170413110025011_small.png","firstTime":"2017-04-20 16:11:51.0","secondComment":[]},{"firstCommentCode":"a37722fe-0556-4155-b8cf-63e9435d524e","firstIsSubject":null,"firstContent":"哈哈哈","firstUpvote":"0","firstUser":"WW","firstImg":"http://192.168.1.110:8080/vrtext/upload/user/2017/04/13/20170413110025011_small.png","firstTime":"2017-04-20 15:26:35.0","secondComment":[]},{"firstCommentCode":"807ab9bd-b155-4f5d-8acf-589662a0e34e","firstIsSubject":null,"firstContent":"11啊啊啊","firstUpvote":"0","firstUser":"WW","firstImg":"http://192.168.1.110:8080/vrtext/upload/user/2017/04/13/20170413110025011_small.png","firstTime":"2017-04-20 15:05:33.0","secondComment":[]},{"firstCommentCode":"2db1bbef-9611-4b30-8581-fc4bfce2cbed","firstIsSubject":null,"firstContent":"3sfsdfsdfsdfdsfsdf","firstUpvote":"0","firstUser":"WW","firstImg":"http://192.168.1.110:8080/vrtext/upload/user/2017/04/13/20170413110025011_small.png","firstTime":"2017-04-20 11:08:53.0","secondComment":[{"secondCommentCode":"a0c36e97-203a-4ab7-a052-835ac5471f61","secondIsSubject":null,"secondContent":"3sfsdfsdfsdfdsfsdf","secondCommentUser":"WW","secondTime":"2017-04-20 11:10:34.0","secondImg":"http://192.168.1.110:8080/vrtext/upload/user/2017/04/13/20170413110025011_small.png","secondUpvote":"0","secondUser":"WW"},{"secondCommentCode":"fc9467c6-85e6-4ff3-ac2a-4f585a1e0547","secondIsSubject":null,"secondContent":"3sfsdfsdfsdfdsfsdf","secondCommentUser":"","secondTime":"2017-04-20 11:09:46.0","secondImg":"http://192.168.1.110:8080/vrtext/upload/user/2017/04/13/20170413110025011_small.png","secondUpvote":"0","secondUser":"WW"}]}]}
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
             * firstCommentCode : 1
             * firstIsSubject : 1
             * firstContent : 非常好
             * firstUpvote : 1
             * firstUser : WW
             * firstImg : http://192.168.1.110:8080/vrtext/upload/user/2017/04/13/20170413110025011_small.png
             * firstTime : 2017-04-19 16:49:13.0
             * secondComment : [{"secondCommentCode":"2","secondIsSubject":"1","secondContent":"非常好","secondCommentUser":"","secondTime":"2017-04-19 16:50:58.0","secondImg":"http://192.168.1.110:8080/vrtext/upload/user/2017/04/13/20170413110025011_small.png","secondUpvote":"1","secondUser":"WW"},{"secondCommentCode":"4","secondIsSubject":"0","secondContent":"非常好","secondCommentUser":"WW","secondTime":"2017-04-21 10:14:28.0","secondImg":"http://192.168.1.110:8080/vrtext/upload/user/2017/04/13/20170413155127003_small.png","secondUpvote":"0","secondUser":"哈哈哈"},{"secondCommentCode":"af513f44-5c11-4b96-a955-968a3583370f","secondIsSubject":null,"secondContent":"4323423423","secondCommentUser":"","secondTime":"2017-04-20 18:43:25.0","secondImg":"http://192.168.1.110:8080/vrtext/upload/user/2017/04/13/20170413110025011_small.png","secondUpvote":"0","secondUser":"WW"},{"secondCommentCode":"4efb6acc-4176-486f-bf93-fccc95815d9d","secondIsSubject":null,"secondContent":"123","secondCommentUser":"","secondTime":"2017-04-20 18:43:17.0","secondImg":"http://192.168.1.110:8080/vrtext/upload/user/2017/04/13/20170413110025011_small.png","secondUpvote":"0","secondUser":"WW"}]
             */

            private String firstCommentCode;
            private String firstIsSubject;
            private String firstContent;
            private String firstUpvote;
            private String firstUser;
            private String firstImg;
            private String firstTime;
            private List<SecondCommentBean> secondComment;

            public String getFirstCommentCode() {
                return firstCommentCode;
            }

            public void setFirstCommentCode(String firstCommentCode) {
                this.firstCommentCode = firstCommentCode;
            }

            public String getFirstIsSubject() {
                return firstIsSubject;
            }

            public void setFirstIsSubject(String firstIsSubject) {
                this.firstIsSubject = firstIsSubject;
            }

            public String getFirstContent() {
                return firstContent;
            }

            public void setFirstContent(String firstContent) {
                this.firstContent = firstContent;
            }

            public String getFirstUpvote() {
                return firstUpvote;
            }

            public void setFirstUpvote(String firstUpvote) {
                this.firstUpvote = firstUpvote;
            }

            public String getFirstUser() {
                return firstUser;
            }

            public void setFirstUser(String firstUser) {
                this.firstUser = firstUser;
            }

            public String getFirstImg() {
                return firstImg;
            }

            public void setFirstImg(String firstImg) {
                this.firstImg = firstImg;
            }

            public String getFirstTime() {
                return firstTime;
            }

            public void setFirstTime(String firstTime) {
                this.firstTime = firstTime;
            }

            public List<SecondCommentBean> getSecondComment() {
                return secondComment;
            }

            public void setSecondComment(List<SecondCommentBean> secondComment) {
                this.secondComment = secondComment;
            }

            public static class SecondCommentBean {
                /**
                 * secondCommentCode : 2
                 * secondIsSubject : 1
                 * secondContent : 非常好
                 * secondCommentUser :
                 * secondTime : 2017-04-19 16:50:58.0
                 * secondImg : http://192.168.1.110:8080/vrtext/upload/user/2017/04/13/20170413110025011_small.png
                 * secondUpvote : 1
                 * secondUser : WW
                 */

                private String secondCommentCode;
                private String secondIsSubject;
                private String secondContent;
                private String secondCommentUser;
                private String secondTime;
                private String secondImg;
                private String secondUpvote;
                private String secondUser;

                public String getSecondCommentCode() {
                    return secondCommentCode;
                }

                public void setSecondCommentCode(String secondCommentCode) {
                    this.secondCommentCode = secondCommentCode;
                }

                public String getSecondIsSubject() {
                    return secondIsSubject;
                }

                public void setSecondIsSubject(String secondIsSubject) {
                    this.secondIsSubject = secondIsSubject;
                }

                public String getSecondContent() {
                    return secondContent;
                }

                public void setSecondContent(String secondContent) {
                    this.secondContent = secondContent;
                }

                public String getSecondCommentUser() {
                    return secondCommentUser;
                }

                public void setSecondCommentUser(String secondCommentUser) {
                    this.secondCommentUser = secondCommentUser;
                }

                public String getSecondTime() {
                    return secondTime;
                }

                public void setSecondTime(String secondTime) {
                    this.secondTime = secondTime;
                }

                public String getSecondImg() {
                    return secondImg;
                }

                public void setSecondImg(String secondImg) {
                    this.secondImg = secondImg;
                }

                public String getSecondUpvote() {
                    return secondUpvote;
                }

                public void setSecondUpvote(String secondUpvote) {
                    this.secondUpvote = secondUpvote;
                }

                public String getSecondUser() {
                    return secondUser;
                }

                public void setSecondUser(String secondUser) {
                    this.secondUser = secondUser;
                }
            }
        }
    }
}
