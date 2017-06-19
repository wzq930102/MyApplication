package com.jtvr.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jt on 2017/3/31.
 */
public class playMovieBean {

    /**
     * status : 99
     * business : {"videoCode":"391af2b2-a6fe-44fb-9fdf-4c3b1684f79b","time":"2016","address":"香港","vclarity":"标清","filmType":"正片","power":"VIP","property":"VR","img":"http://211.159.155.91/vrtext/upload/video/2017/03/31/20170331143630878_small.jpg","img2":"","img3":"","img4":"","img5":"","img6":"","material":"","recommend":"否","visOriginal":"非原创","length":"6:06","playCount":"295","videoIntro":"《爱是怀疑》是收录在香港男歌手陈奕迅第四张国语专辑《反正是我》中的主打歌曲，发行于2001年7月17日，由陈奂仁作词作曲。这首歌在2002年获得了第八届华语音乐榜中榜\u201c最受欢迎歌曲奖\u201d。这段VR版《爱是怀疑》是由本曲的作词作曲者陈奂仁带来的，听起来，是和陈奕迅不一样的感受，但却是一样的感动。","actor":"不求人大乐队","url":"http://1253520711.vod2.myqcloud.com/3035299avodgzp1253520711/f41f62b79031868222900563110/f0.mp4","title":"爱是怀疑","upvode":"0","videoUrl":[{"220":"http://1253520711.vod2.myqcloud.com/e45ccc42vodtransgzp1253520711/f41f62b79031868222900563110/f0.f220.m3u8"},{"230":"http://1253520711.vod2.myqcloud.com/e45ccc42vodtransgzp1253520711/f41f62b79031868222900563110/f0.f230.m3u8"},{"240":"http://1253520711.vod2.myqcloud.com/e45ccc42vodtransgzp1253520711/f41f62b79031868222900563110/f0.f240.m3u8"}],"shareVideo":"http://1253520711.vod2.myqcloud.com/3035299avodgzp1253520711/f41f62b79031868222900563110/f0.mp4","keepCode":"9204aa75-1645-4dba-8071-2f5cf48b5ab5","keepVideo":"y","grad":4.3,"wcanSee":"y","fire":"","upvodeVideo":"n","score":"9.0","vbody":"","type":"娱乐,惊悚,科幻,综艺","similarity":[{"videoCode":"086e399b-dc75-4524-a535-d0f4c283f536","img":"http://211.159.155.91/vrtext/upload/video/2017/03/31/20170331142922817_small.jpg","img2":"","img3":"","img4":"","img5":"","img6":"","name":"海中漫游","visOriginal":"非原创","power":"普通视频"},{"videoCode":"1d8b2ac3-2890-4a9d-8665-69aa76df59a0","img":"http://211.159.155.91/vrtext/upload/video/2017/03/31/20170331143536465_small.jpg","img2":"","img3":"","img4":"","img5":"","img6":"","name":"发扬中国传统文化","visOriginal":"非原创","power":"普通视频"},{"videoCode":"2ce5db83-9f78-475a-bd2b-42c0b814df8a","img":"http://211.159.155.91/vrtext/upload/video/2017/03/31/20170331143600584_small.jpg","img2":"","img3":"","img4":"","img5":"","img6":"","name":"赛道狂飙","visOriginal":"非原创","power":"普通视频"},{"videoCode":"4b635f49-93ee-42b3-8adc-3229e65a91aa","img":"http://211.159.155.91/vrtext/upload/video/2017/03/31/20170331144937463_small.jpg","img2":"","img3":"","img4":"","img5":"","img6":"","name":"《早期人类》爆笑预告","visOriginal":"非原创","power":"VIP"},{"videoCode":"4dbe8449-110a-4053-9323-a99836d669d8","img":"http://211.159.155.91/vrtext/upload/video/2017/03/31/20170331145136787_small.jpg","img2":"","img3":"","img4":"","img5":"","img6":"","name":"巴哈马海岛旅游","visOriginal":"非原创","power":"VIP"},{"videoCode":"dd557adc-1f7f-4a49-9684-4e1d33b7b801","img":"http://211.159.155.91/vrtext/upload/video/2017/03/31/20170331145325871_small.jpg","img2":"","img3":"","img4":"","img5":"","img6":"","name":"气球","visOriginal":"非原创","power":"普通视频"}]}
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
         * videoCode : 391af2b2-a6fe-44fb-9fdf-4c3b1684f79b
         * time : 2016
         * address : 香港
         * vclarity : 标清
         * filmType : 正片
         * power : VIP
         * property : VR
         * img : http://211.159.155.91/vrtext/upload/video/2017/03/31/20170331143630878_small.jpg
         * img2 :
         * img3 :
         * img4 :
         * img5 :
         * img6 :
         * material :
         * recommend : 否
         * visOriginal : 非原创
         * length : 6:06
         * playCount : 295
         * videoIntro : 《爱是怀疑》是收录在香港男歌手陈奕迅第四张国语专辑《反正是我》中的主打歌曲，发行于2001年7月17日，由陈奂仁作词作曲。这首歌在2002年获得了第八届华语音乐榜中榜“最受欢迎歌曲奖”。这段VR版《爱是怀疑》是由本曲的作词作曲者陈奂仁带来的，听起来，是和陈奕迅不一样的感受，但却是一样的感动。
         * actor : 不求人大乐队
         * url : http://1253520711.vod2.myqcloud.com/3035299avodgzp1253520711/f41f62b79031868222900563110/f0.mp4
         * title : 爱是怀疑
         * upvode : 0
         * videoUrl : [{"220":"http://1253520711.vod2.myqcloud.com/e45ccc42vodtransgzp1253520711/f41f62b79031868222900563110/f0.f220.m3u8"},{"230":"http://1253520711.vod2.myqcloud.com/e45ccc42vodtransgzp1253520711/f41f62b79031868222900563110/f0.f230.m3u8"},{"240":"http://1253520711.vod2.myqcloud.com/e45ccc42vodtransgzp1253520711/f41f62b79031868222900563110/f0.f240.m3u8"}]
         * shareVideo : http://1253520711.vod2.myqcloud.com/3035299avodgzp1253520711/f41f62b79031868222900563110/f0.mp4
         * keepCode : 9204aa75-1645-4dba-8071-2f5cf48b5ab5
         * keepVideo : y
         * grad : 4.3
         * wcanSee : y
         * fire :
         * upvodeVideo : n
         * score : 9.0
         * vbody :
         * type : 娱乐,惊悚,科幻,综艺
         * similarity : [{"videoCode":"086e399b-dc75-4524-a535-d0f4c283f536","img":"http://211.159.155.91/vrtext/upload/video/2017/03/31/20170331142922817_small.jpg","img2":"","img3":"","img4":"","img5":"","img6":"","name":"海中漫游","visOriginal":"非原创","power":"普通视频"},{"videoCode":"1d8b2ac3-2890-4a9d-8665-69aa76df59a0","img":"http://211.159.155.91/vrtext/upload/video/2017/03/31/20170331143536465_small.jpg","img2":"","img3":"","img4":"","img5":"","img6":"","name":"发扬中国传统文化","visOriginal":"非原创","power":"普通视频"},{"videoCode":"2ce5db83-9f78-475a-bd2b-42c0b814df8a","img":"http://211.159.155.91/vrtext/upload/video/2017/03/31/20170331143600584_small.jpg","img2":"","img3":"","img4":"","img5":"","img6":"","name":"赛道狂飙","visOriginal":"非原创","power":"普通视频"},{"videoCode":"4b635f49-93ee-42b3-8adc-3229e65a91aa","img":"http://211.159.155.91/vrtext/upload/video/2017/03/31/20170331144937463_small.jpg","img2":"","img3":"","img4":"","img5":"","img6":"","name":"《早期人类》爆笑预告","visOriginal":"非原创","power":"VIP"},{"videoCode":"4dbe8449-110a-4053-9323-a99836d669d8","img":"http://211.159.155.91/vrtext/upload/video/2017/03/31/20170331145136787_small.jpg","img2":"","img3":"","img4":"","img5":"","img6":"","name":"巴哈马海岛旅游","visOriginal":"非原创","power":"VIP"},{"videoCode":"dd557adc-1f7f-4a49-9684-4e1d33b7b801","img":"http://211.159.155.91/vrtext/upload/video/2017/03/31/20170331145325871_small.jpg","img2":"","img3":"","img4":"","img5":"","img6":"","name":"气球","visOriginal":"非原创","power":"普通视频"}]
         */

        private String videoCode;
        private String time;
        private String address;
        private String vclarity;
        private String filmType;
        private String power;
        private String property;
        private String img;
        private String img2;
        private String img3;
        private String img4;
        private String img5;
        private String img6;
        private String material;
        private String recommend;
        private String visOriginal;
        private String length;
        private String playCount;
        private String videoIntro;
        private String actor;
        private String url;
        private String title;
        private String upvode;
        private String shareVideo;
        private String keepCode;
        private String keepVideo;
        private String grad;
        private String wcanSee;
        private String fire;
        private String upvodeVideo;
        private String score;
        private String vbody;
        private String type;
        private List<VideoUrlBean> videoUrl;
        private List<SimilarityBean> similarity;

        public String getVideoCode() {
            return videoCode;
        }

        public void setVideoCode(String videoCode) {
            this.videoCode = videoCode;
        }

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

        public String getVclarity() {
            return vclarity;
        }

        public void setVclarity(String vclarity) {
            this.vclarity = vclarity;
        }

        public String getFilmType() {
            return filmType;
        }

        public void setFilmType(String filmType) {
            this.filmType = filmType;
        }

        public String getPower() {
            return power;
        }

        public void setPower(String power) {
            this.power = power;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
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

        public String getMaterial() {
            return material;
        }

        public void setMaterial(String material) {
            this.material = material;
        }

        public String getRecommend() {
            return recommend;
        }

        public void setRecommend(String recommend) {
            this.recommend = recommend;
        }

        public String getVisOriginal() {
            return visOriginal;
        }

        public void setVisOriginal(String visOriginal) {
            this.visOriginal = visOriginal;
        }

        public String getLength() {
            return length;
        }

        public void setLength(String length) {
            this.length = length;
        }

        public String getPlayCount() {
            return playCount;
        }

        public void setPlayCount(String playCount) {
            this.playCount = playCount;
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

        public String getUpvode() {
            return upvode;
        }

        public void setUpvode(String upvode) {
            this.upvode = upvode;
        }

        public String getShareVideo() {
            return shareVideo;
        }

        public void setShareVideo(String shareVideo) {
            this.shareVideo = shareVideo;
        }

        public String getKeepCode() {
            return keepCode;
        }

        public void setKeepCode(String keepCode) {
            this.keepCode = keepCode;
        }

        public String getKeepVideo() {
            return keepVideo;
        }

        public void setKeepVideo(String keepVideo) {
            this.keepVideo = keepVideo;
        }

        public String getGrad() {
            return grad;
        }

        public void setGrad(String grad) {
            this.grad = grad;
        }

        public String getWcanSee() {
            return wcanSee;
        }

        public void setWcanSee(String wcanSee) {
            this.wcanSee = wcanSee;
        }

        public String getFire() {
            return fire;
        }

        public void setFire(String fire) {
            this.fire = fire;
        }

        public String getUpvodeVideo() {
            return upvodeVideo;
        }

        public void setUpvodeVideo(String upvodeVideo) {
            this.upvodeVideo = upvodeVideo;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getVbody() {
            return vbody;
        }

        public void setVbody(String vbody) {
            this.vbody = vbody;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<VideoUrlBean> getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(List<VideoUrlBean> videoUrl) {
            this.videoUrl = videoUrl;
        }

        public List<SimilarityBean> getSimilarity() {
            return similarity;
        }

        public void setSimilarity(List<SimilarityBean> similarity) {
            this.similarity = similarity;
        }

        public static class VideoUrlBean {
            /**
             * 220 : http://1253520711.vod2.myqcloud.com/e45ccc42vodtransgzp1253520711/f41f62b79031868222900563110/f0.f220.m3u8
             * 230 : http://1253520711.vod2.myqcloud.com/e45ccc42vodtransgzp1253520711/f41f62b79031868222900563110/f0.f230.m3u8
             * 240 : http://1253520711.vod2.myqcloud.com/e45ccc42vodtransgzp1253520711/f41f62b79031868222900563110/f0.f240.m3u8
             */

            @SerializedName("220")
            private String _$220;
            @SerializedName("230")
            private String _$230;
            @SerializedName("240")
            private String _$240;

            public String get_$220() {
                return _$220;
            }

            public void set_$220(String _$220) {
                this._$220 = _$220;
            }

            public String get_$230() {
                return _$230;
            }

            public void set_$230(String _$230) {
                this._$230 = _$230;
            }

            public String get_$240() {
                return _$240;
            }

            public void set_$240(String _$240) {
                this._$240 = _$240;
            }
        }

        public static class SimilarityBean {
            /**
             * videoCode : 086e399b-dc75-4524-a535-d0f4c283f536
             * img : http://211.159.155.91/vrtext/upload/video/2017/03/31/20170331142922817_small.jpg
             * img2 :
             * img3 :
             * img4 :
             * img5 :
             * img6 :
             * name : 海中漫游
             * visOriginal : 非原创
             * power : 普通视频
             */

            private String videoCode;
            private String img;
            private String img2;
            private String img3;
            private String img4;
            private String img5;
            private String img6;
            private String name;
            private String visOriginal;
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

            public String getPower() {
                return power;
            }

            public void setPower(String power) {
                this.power = power;
            }
        }
    }
}
