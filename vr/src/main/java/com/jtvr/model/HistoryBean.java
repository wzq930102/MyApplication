package com.jtvr.model;

import java.util.List;

/**
 * Created by yy on 2017/4/12.
 */
public class HistoryBean {

    /**
     * business : {"list":[{"watchCode":"1231232","videoCode":"04c5c4c6-f86c-4f37-b73f-6b197df765f5","img":"http://211.159.155.91/vrtext/upload/video/2017/03/31/20170331142850588_small.jpg","img2":"","img3":"","img4":"","img5":"","img6":"","url":"http://img.moviebase.cn/img/video/2017/03/e190ebe5706a6819019de0feb9ff895d.mp4","title":"什么叫魔性演技？盘点一线大咖们的尬舞桥段","visOriginal":"非原创","vclarity":"标清","videoIntro":"本片讲述的是国际空间站宇航员在对一份从火星取回的样本检测后，发现其中显现出生命迹象，而且是一种比人类预料的智慧的多的生命。","playCount":"1","property":"2D","power":"普通视频"},{"watchCode":"23233","videoCode":"04c5c4c6-f86c-4f37-b73f-6b197df765f5","img":"http://211.159.155.91/vrtext/upload/video/2017/03/31/20170331142850588_small.jpg","img2":"","img3":"","img4":"","img5":"","img6":"","url":"http://img.moviebase.cn/img/video/2017/03/e190ebe5706a6819019de0feb9ff895d.mp4","title":"什么叫魔性演技？盘点一线大咖们的尬舞桥段","visOriginal":"非原创","vclarity":"标清","videoIntro":"本片讲述的是国际空间站宇航员在对一份从火星取回的样本检测后，发现其中显现出生命迹象，而且是一种比人类预料的智慧的多的生命。","playCount":"1","property":"2D","power":"普通视频"},{"watchCode":"123123123","videoCode":"04c5c4c6-f86c-4f37-b73f-6b197df765f5","img":"http://211.159.155.91/vrtext/upload/video/2017/03/31/20170331142850588_small.jpg","img2":"","img3":"","img4":"","img5":"","img6":"","url":"http://img.moviebase.cn/img/video/2017/03/e190ebe5706a6819019de0feb9ff895d.mp4","title":"什么叫魔性演技？盘点一线大咖们的尬舞桥段","visOriginal":"非原创","vclarity":"标清","videoIntro":"本片讲述的是国际空间站宇航员在对一份从火星取回的样本检测后，发现其中显现出生命迹象，而且是一种比人类预料的智慧的多的生命。","playCount":"1","property":"2D","power":"普通视频"}]}
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
             * watchCode : 1231232
             * videoCode : 04c5c4c6-f86c-4f37-b73f-6b197df765f5
             * img : http://211.159.155.91/vrtext/upload/video/2017/03/31/20170331142850588_small.jpg
             * img2 :
             * img3 :
             * img4 :
             * img5 :
             * img6 :
             * url : http://img.moviebase.cn/img/video/2017/03/e190ebe5706a6819019de0feb9ff895d.mp4
             * title : 什么叫魔性演技？盘点一线大咖们的尬舞桥段
             * visOriginal : 非原创
             * vclarity : 标清
             * videoIntro : 本片讲述的是国际空间站宇航员在对一份从火星取回的样本检测后，发现其中显现出生命迹象，而且是一种比人类预料的智慧的多的生命。
             * playCount : 1
             * property : 2D
             * power : 普通视频
             */
            private String keepCode;
            private String watchCode;
            private String videoCode;
            private String img;
            private String img2;
            private String img3;
            private String img4;
            private String img5;
            private String img6;
            private String url;
            private String title;
            private String visOriginal;
            private String vclarity;
            private String videoIntro;
            private String playCount;
            private String property;
            private String power;
            private String time;

            public String getKeepCode() {
                return keepCode;
            }

            public void setKeepCode(String keepCode) {
                this.keepCode = keepCode;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getWatchCode() {
                return watchCode;
            }

            public void setWatchCode(String watchCode) {
                this.watchCode = watchCode;
            }

            public String getVideoCode() {
                return videoCode;
            }

            public void setVideoCode(String videoCode) {
                this.videoCode = videoCode;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getImg2() {
                return img2;
            }

            public void setImg2(String img2) {
                this.img2 = img2;
            }

            public String getImg3() {
                return img3;
            }

            public void setImg3(String img3) {
                this.img3 = img3;
            }

            public String getImg4() {
                return img4;
            }

            public void setImg4(String img4) {
                this.img4 = img4;
            }

            public String getImg5() {
                return img5;
            }

            public void setImg5(String img5) {
                this.img5 = img5;
            }

            public String getImg6() {
                return img6;
            }

            public void setImg6(String img6) {
                this.img6 = img6;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getVisOriginal() {
                return visOriginal;
            }

            public void setVisOriginal(String visOriginal) {
                this.visOriginal = visOriginal;
            }

            public String getVclarity() {
                return vclarity;
            }

            public void setVclarity(String vclarity) {
                this.vclarity = vclarity;
            }

            public String getVideoIntro() {
                return videoIntro;
            }

            public void setVideoIntro(String videoIntro) {
                this.videoIntro = videoIntro;
            }

            public String getPlayCount() {
                return playCount;
            }

            public void setPlayCount(String playCount) {
                this.playCount = playCount;
            }

            public String getProperty() {
                return property;
            }

            public void setProperty(String property) {
                this.property = property;
            }

            public String getPower() {
                return power;
            }

            public void setPower(String power) {
                this.power = power;
            }
        }
    }
}
