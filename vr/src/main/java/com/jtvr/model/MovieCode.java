package com.jtvr.model;

import java.util.List;

/**
 * Created by yy on 2017/4/1.
 */
public class MovieCode {

    /**
     * status : 99
     * business : {"list":[{"videoCode":"a15296bd-18d2-4eaa-af5a-67d820901a36","img":"http://192.168.1.110:8080/vrtext/upload/video/2017/03/28/20170328115833070_small.jpg","url":"","title":"功夫瑜伽","visOriginal":"非原创","vclarity":"1080P","videoIntro":"功夫","playCount":"0","property":"普通视频","power":"普通视频"},{"videoCode":"47052c79-aa29-466f-9267-7b3b078979ac","img":"http://192.168.1.110:8080/vrtext/upload/video/2017/03/31/20170331143700494_small.jpg","url":"http://img.moviebase.cn/img/video/2017/03/171a295cb41f2b878adbcf4d9b2a6d05.mp4","title":"《娃娃老板》预告","visOriginal":"非原创","vclarity":"标清","videoIntro":"该片讲述了7岁男孩提姆对于新婴儿弟弟的到来感到不满，兄弟二人开始展开一场父母爱争夺战的故事。","playCount":"0","property":"普通视频","power":"普通视频"},{"videoCode":"4b635f49-93ee-42b3-8adc-3229e65a91aa","img":"http://192.168.1.110:8080/vrtext/upload/video/2017/03/31/20170331144937463_small.jpg","url":"http://img.moviebase.cn/img/video/2017/03/33b456ad806bf1f3ab0cf498ebd08891.mp4","title":"《早期人类》爆笑预告","visOriginal":"非原创","vclarity":"标清","videoIntro":"是阿德曼公司计划于于2018年上映的一部动画片，讲述了一群居住在山洞里的史前人类尼安德特人是如何利用足球击败了强大敌人、拯救自己的故事。","playCount":"0","property":"普通视频","power":"VIP"},{"videoCode":"4dbe8449-110a-4053-9323-a99836d669d8","img":"http://192.168.1.110:8080/vrtext/upload/video/2017/03/31/20170331145136787_small.jpg","url":"http://om6k32y5f.bkt.clouddn.com/1.巴哈马海岛旅游.m3u8","title":"巴哈马海岛旅游","visOriginal":"非原创","vclarity":"标清","videoIntro":"2017年春节，Yami美食之旅来到了加勒比海的巴哈马Bahamas,带领我们的粉丝来一场美景，美食之旅","playCount":"0","property":"VR","power":"普通视频"}]}
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
             * videoCode : a15296bd-18d2-4eaa-af5a-67d820901a36
             * img : http://192.168.1.110:8080/vrtext/upload/video/2017/03/28/20170328115833070_small.jpg
             * url :
             * title : 功夫瑜伽
             * visOriginal : 非原创
             * vclarity : 1080P
             * videoIntro : 功夫
             * playCount : 0
             * property : 普通视频
             * power : 普通视频
             */

            private String videoCode;
            private String img;
            private String url;
            private String title;
            private String visOriginal;
            private String vclarity;
            private String videoIntro;
            private String playCount;
            private String property;
            private String power;

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
