package com.jtvr.model;

import java.util.List;

/**
 * Created by yy on 2017/4/9.
 */
public class MovieMoreBean {

    /**
     * status : 99
     * business : {"list":[{"vrtCode":"29d813d9-cd3a-4e88-9714-e34823aa7691","videoCode":"1d8b2ac3-2890-4a9d-8665-69aa76df59a0","name":"发扬中国传统文化","visOriginal":"非原创","vclarity":"标清","videoIntro":"昕昕在现场不仅上演了电影《龙门飞甲》的经典片段，一场时空穿越的大秀就此展开。不仅如此，设计师还远赴新疆邀请绣娘共同亮相，将疆绣与中国原创设计结合在一起，真正继承并发扬了中国传统文化！VR视频是最新的一种虚拟现实视频体验，要达到最好的体验效果","img":"http://211.159.155.91/vrtext/upload/video/2017/03/31/20170331143536465_small.jpg","playCount":"51","url":"http://om6k321y5f.bkt.clouddn.com/3.发扬中国传统文化.m3u8","property":"3D","power":"普通视频"},{"vrtCode":"322e1702-7522-4557-a729-064f24a2164e","videoCode":"45206f1b-63c2-47e7-9d85-8b94bd9db9ae","name":"《攻壳机动队》制作特辑","visOriginal":"非原创","vclarity":"标清","videoIntro":"《攻壳机动队》是根据日本漫画家士郎正宗创作的同名漫画改编，讲述了在未来的日本，全世界被庞大信息网络连为一体，人类的各种组织器官均可被人造化。","img":"http://211.159.155.91/vrtext/upload/video/2017/03/31/20170331143642480_small.jpg","playCount":"22","url":"http://img.moviebase.cn/img/video/2017/03/7e28d3d22c2af703d34c912cbd2ac759.mp41","property":"2D","power":"普通视频"},{"vrtCode":"e98a5b66-7c96-4524-85db-af350f668fd2","videoCode":"4dbe8449-110a-4053-9323-a99836d669d8","name":"巴哈马海岛旅游","visOriginal":"非原创","vclarity":"标清","videoIntro":"2017年春节，Yami美食之旅来到了加勒比海的巴哈马Bahamas,带领我们的粉丝来一场美景，美食之旅","img":"http://211.159.155.91/vrtext/upload/video/2017/03/31/20170331145136787_small.jpg","playCount":"38","url":"http://om6k32y5f.bkt.cloudd1n.com/1.巴哈马海岛旅游.m3u8","property":"VR","power":"普通视频"}]}
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
             * vrtCode : 29d813d9-cd3a-4e88-9714-e34823aa7691
             * videoCode : 1d8b2ac3-2890-4a9d-8665-69aa76df59a0
             * name : 发扬中国传统文化
             * visOriginal : 非原创
             * vclarity : 标清
             * videoIntro : 昕昕在现场不仅上演了电影《龙门飞甲》的经典片段，一场时空穿越的大秀就此展开。不仅如此，设计师还远赴新疆邀请绣娘共同亮相，将疆绣与中国原创设计结合在一起，真正继承并发扬了中国传统文化！VR视频是最新的一种虚拟现实视频体验，要达到最好的体验效果
             * img : http://211.159.155.91/vrtext/upload/video/2017/03/31/20170331143536465_small.jpg
             * playCount : 51
             * url : http://om6k321y5f.bkt.clouddn.com/3.发扬中国传统文化.m3u8
             * property : 3D
             * power : 普通视频
             */

            private String vrtCode;
            private String videoCode;
            private String name;
            private String visOriginal;
            private String vclarity;
            private String videoIntro;
            private String img;
            private String playCount;
            private String url;
            private String property;
            private String power;

            public String getVrtCode() {
                return vrtCode;
            }

            public void setVrtCode(String vrtCode) {
                this.vrtCode = vrtCode;
            }

            public String getVideoCode() {
                return videoCode;
            }

            public void setVideoCode(String videoCode) {
                this.videoCode = videoCode;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getPlayCount() {
                return playCount;
            }

            public void setPlayCount(String playCount) {
                this.playCount = playCount;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
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
