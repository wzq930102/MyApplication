package com.jtvr.model;

import java.util.List;

/**
 * Created by yy on 2017/3/28.
 */
public class JTVideoBean {

    /**
     * members : {"status":"99","list":[{"img":"http://192.168.1.110:8080/vrtext/upload/video/2017/03/28/20170328115651380_small.jpg","videoCode":"01547d4f-25cd-4d3b-956b-ad7f95f21755","visOriginal":"2","clarity":"超清","title":"炮弹飞车","videoIntro":"中华之地"},{"img":"http://192.168.1.110:8080/vrtext/upload/video/2017/03/28/20170328115740137_small.jpg","videoCode":"71d368ac-5f23-448e-af20-53a28d29d015","visOriginal":"1","clarity":"1080P","title":"测试","videoIntro":"测试, "},{"img":"http://192.168.1.110:8080/vrtext/upload/video/2017/03/28/20170328115833070_small.jpg","videoCode":"a15296bd-18d2-4eaa-af5a-67d820901a36","visOriginal":"2","clarity":"1080P","title":"功夫瑜伽","videoIntro":"功夫"}]}
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
         * status : 99
         * list : [{"img":"http://192.168.1.110:8080/vrtext/upload/video/2017/03/28/20170328115651380_small.jpg","videoCode":"01547d4f-25cd-4d3b-956b-ad7f95f21755","visOriginal":"2","clarity":"超清","title":"炮弹飞车","videoIntro":"中华之地"},{"img":"http://192.168.1.110:8080/vrtext/upload/video/2017/03/28/20170328115740137_small.jpg","videoCode":"71d368ac-5f23-448e-af20-53a28d29d015","visOriginal":"1","clarity":"1080P","title":"测试","videoIntro":"测试, "},{"img":"http://192.168.1.110:8080/vrtext/upload/video/2017/03/28/20170328115833070_small.jpg","videoCode":"a15296bd-18d2-4eaa-af5a-67d820901a36","visOriginal":"2","clarity":"1080P","title":"功夫瑜伽","videoIntro":"功夫"}]
         */

        private String status;
        private List<ListBean> list;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * img : http://192.168.1.110:8080/vrtext/upload/video/2017/03/28/20170328115651380_small.jpg
             * videoCode : 01547d4f-25cd-4d3b-956b-ad7f95f21755
             * visOriginal : 2
             * clarity : 超清
             * title : 炮弹飞车
             * videoIntro : 中华之地
             */

            private String img;
            private String videoCode;
            private String visOriginal;
            private String clarity;
            private String title;
            private String videoIntro;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getVideoCode() {
                return videoCode;
            }

            public void setVideoCode(String videoCode) {
                this.videoCode = videoCode;
            }

            public String getVisOriginal() {
                return visOriginal;
            }

            public void setVisOriginal(String visOriginal) {
                this.visOriginal = visOriginal;
            }

            public String getClarity() {
                return clarity;
            }

            public void setClarity(String clarity) {
                this.clarity = clarity;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getVideoIntro() {
                return videoIntro;
            }

            public void setVideoIntro(String videoIntro) {
                this.videoIntro = videoIntro;
            }
        }
    }
}
