package com.jtvr.http;


import android.content.Intent;

import com.jtvr.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;

import okhttp3.Call;

/**
 * Created by jt on 2017/3/31.
 */
public class VrHttp {
    private RequestEnum re;
    private OnGetDataResult ondr;
    private Intent i;
    private static VrHttp vrhttp;
    private static final String HOST = "http://211.159.155.91/vrtext/works.action";
//    private static final String HOST = "http://192.168.1.110:8080/vrtext/works.action";

    public static VrHttp getInstance() {
        vrhttp = new VrHttp();
        return vrhttp;
    }

    public void requestForResult(RequestEnum re, OnGetDataResult ondr, Intent i) {
        this.re = re;
        this.ondr = ondr;
        this.i = i;
        RequestHttp();
    }

    public void requestForResultFils(RequestEnum re, OnGetDataResult ondr, Intent i, String s1, String s2, File by) {
        this.re = re;
        this.ondr = ondr;
        this.i = i;
        RequestHttpFils(s1, s2, by);
    }

    private void RequestHttpFils(String s1, String s2, File by) {
        OkHttpUtils
                .post()
                .addFile(s1, s2, by)
                .url(HOST)
                .addParams("bizData", getbizData())
                .addParams("digest", getdigest())
                .build()
                .execute(new StringCallback() {
                             @Override
                             public void onError(Call call, Exception e, int id) {
                                 LogUtils.i("网络请求错误");
                             }

                             @Override
                             public void onResponse(String response, int id) {
                                 LogUtils.i("请求响应", response.toString());
                                 ondr.Response(response, id);
                             }
                         }
                );

    }

    public void RequestHttp() {
        OkHttpUtils
                .post()
                .url(HOST)
                .addParams("bizData", getbizData())
                .addParams("digest", getdigest())
                .build()
                .execute(new StringCallback() {
                             @Override
                             public void onError(Call call, Exception e, int id) {
                                 ondr.onError(call, e, id);
                             }

                             @Override
                             public void onResponse(String response, int id) {
                                 LogUtils.i("请求响应", response.toString());
                                 ondr.Response(response, id);
                             }
                         }
                );

    }


    public interface OnGetDataResult {
        public abstract void Response(String response, int id);
        public abstract void onError(Call call, Exception e, int id);
    }

    public String getbizData() {
        String s = new JsonRequest(re, i).getbizData();
        LogUtils.i("bizData--请求：", s);
        return s;
    }

    public String getdigest() {
        String s = new JsonRequest(re, i).getdigest();
        LogUtils.i("digest--请求：", s);
        return s;
    }
}
