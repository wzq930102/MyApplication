package com.jtvr.model;

/**
 * Created by jt on 2017/3/28.
 */
public class MovieDateilBean {

    /**
     * members : {"time":"2017年","address":"内地","length":"100分钟","videoIntro":"测试","actor":"王宝强","type":"科幻,喜剧,娱乐","url":"undefined","title":"大闹天竺","videoType":"3"}
     */

    private MembersBean members;

    public MembersBean getMembers() {
        return members;
    }

    public void setMembers(MembersBean members) {
        this.members = members;
    }

    public static class MembersBean {
        /**
         * time : 2017年
         * address : 内地
         * length : 100分钟
         * videoIntro : 测试
         * actor : 王宝强
         * type : 科幻,喜剧,娱乐
         * url : undefined
         * title : 大闹天竺
         * videoType : 3
         */

        private String time;
        private String address;
        private String length;
        private String videoIntro;
        private String actor;
        private String type;
        private String url;
        private String title;
        private String videoType;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLength() {
            return length;
        }

        public void setLength(String length) {
            this.length = length;
        }

        public String getVideoIntro() {
            return videoIntro;
        }

        public void setVideoIntro(String videoIntro) {
            this.videoIntro = videoIntro;
        }

        public String getActor() {
            return actor;
        }

        public void setActor(String actor) {
            this.actor = actor;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        public String getVideoType() {
            return videoType;
        }

        public void setVideoType(String videoType) {
            this.videoType = videoType;
        }
    }
}
