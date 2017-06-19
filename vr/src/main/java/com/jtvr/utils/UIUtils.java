package com.jtvr.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.jtvr.app.VRApplication;
import com.jtvr.avtivity.MyFragmentToActivity.LoginActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by jt on 2017/3/2.
 */
public class UIUtils {
    static OnClickIfLoginListen lis;
    public static Context getContext() {
        return VRApplication.getContext();
    }

    public static Handler getHandler() {
        return VRApplication.getHandler();
    }

    public static int getMainThreadId() {
        return VRApplication.getMainThreadId();
    }

    // /////////////////加载资源文件 ///////////////////////////

    // 获取字符串
    public static String getString(int id) {
        return getContext().getResources().getString(id);
    }

    // 获取字符串数组
    public static String[] getStringArray(int id) {
        return getContext().getResources().getStringArray(id);
    }

    // 获取图片
    public static Drawable getDrawable(int id) {
        return getContext().getResources().getDrawable(id);
    }

    // 获取颜色
    public static int getColor(int id) {
        return getContext().getResources().getColor(id);
    }

    //根据id获取颜色的状态选择器
    public static ColorStateList getColorStateList(int id) {
        return getContext().getResources().getColorStateList(id);
    }

    // 获取尺寸
    public static int getDimen(int id) {
        return getContext().getResources().getDimensionPixelSize(id);// 返回具体像素值
    }

    // /////////////////dip和px转换//////////////////////////

    public static int dip2px(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }
    /**
     * dp转px
     */
    public static int dp2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static float px2dip(int px) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return px / density;
    }

    // /////////////////加载布局文件//////////////////////////
    public static View inflate(int id) {
        return View.inflate(getContext(), id, null);
    }
    //加载布局文件二
//    public static View inflateFragment(int id) {
//        ContextThemeWrapper ctx = new ContextThemeWrapper(getContext(), R.style.MyTabLayout);
//        return LayoutInflater.from(ctx)
//                .inflate(R.layout.fragment_home, null);
//    }

    // /////////////////判断是否运行在主线程//////////////////////////
    public static boolean isRunOnUIThread() {
        // 获取当前线程id, 如果当前线程id和主线程id相同, 那么当前就是主线程
        int myTid = android.os.Process.myTid();
        if (myTid == getMainThreadId()) {
            return true;
        }

        return false;
    }

    // 运行在主线程
    public static void runOnUIThread(Runnable r) {
        if (isRunOnUIThread()) {
            // 已经是主线程, 直接运行
            r.run();
        } else {
            // 如果是子线程, 借助handler让其运行在主线程
            getHandler().post(r);
        }
    }

    //toast
    public static void showToast(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    //picasso
    public static void setImgeView(String url, ImageView iv) {
        if (url != null && !"".equals(url)) {
            Picasso.with(getContext()).load(url).into(iv);
        }
    }

    public static String formatDuring(long mss) {
        long days = mss / (1000 * 60 * 60 * 24);
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60) + days
                * 24;
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (mss % (1000 * 60)) / 1000;
        String HH = (hours > 0) ? String.valueOf(hours) : "00";
        String mm = (minutes > 0) ? String.valueOf(minutes) : "00";
        String ss = (seconds > 0) ? String.valueOf(seconds) : "00";
        HH = (HH.length() == 1) ? ("0" + HH) : (HH);
        mm = (mm.length() == 1) ? ("0" + mm) : (mm);
        ss = (ss.length() == 1) ? ("0" + ss) : (ss);
        return HH + ":" + mm + ":" + ss;    }
    public static void startTvActivity(Context mContext,Intent intent){
        if(!("".equals(SharedPreferencesUtils.get(mContext,"name","")))){
            mContext.startActivity(intent);
        }else{
            intent = new Intent(mContext, LoginActivity.class);
            mContext.startActivity(intent);
            showToast("请先登录");
        }
    }
    public static void setOnClickLoginListen(Context mContext,OnClickIfLoginListen listen){
        lis = listen;
        if(!("".equals(SharedPreferencesUtils.get(mContext,"name","")))){
            listen.OnClick();
        }else{
            startLoginActivity(mContext);
        }
    }
    public static void startLoginActivity(Context mContext){
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
        showToast("请先登录");
    }
    public static Boolean isLogin(Context mContext){
        if("".equals(SharedPreferencesUtils.get(mContext,"name",""))){
            return false;
        }else {
            return true;
        }
    }
    //字符串转化为数组
    public static String[] getStringArry(String s){
        String [] stringArr= s.split(",");
        return stringArr;
    }
    public interface OnClickIfLoginListen{
        void OnClick();
    }
}
