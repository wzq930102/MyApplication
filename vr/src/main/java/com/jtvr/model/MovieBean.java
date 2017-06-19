package com.jtvr.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yy on 2017/3/21.
 */
public class MovieBean {
    private String title;
    private String coverUrl;
    private String videoUrl;
    private String content;
    private String videoTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(String videoTime) {
        this.videoTime = videoTime;
    }

    public List<MovieBean> parseJson(String json) {
        List<MovieBean> list = new ArrayList<>();
        try {
            JSONObject o = new JSONObject(json);
            JSONArray jsonArray = o.getJSONArray("infoList");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String type = object.getString("objectType");
                if ("2".equals(type)) {
                    MovieBean movieBean = new MovieBean();
                    JSONObject o2 = object.getJSONObject("object");
                    movieBean.title = o2.getString("title");
                    movieBean.coverUrl = o2.getString("coverUrl");
                    movieBean.videoUrl = o2.getString("videoUrl");
                    movieBean.videoTime = o2.getString("videoTime");
                    list.add(movieBean);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

}
