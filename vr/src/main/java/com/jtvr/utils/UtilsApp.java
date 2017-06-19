package com.jtvr.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * Created by jt on 2017/4/10.
 */
public class UtilsApp {
    static UtilsApp ua;
    static Context mC;
    public static void initUtilsApp(Context mContext){
        if(ua == null){
            ua = new UtilsApp();
        }
        mC = mContext;
    }

    public static UtilsApp getIntance(){
        return ua;
    }
    public String getToken(){
        return SharedPreferencesUtils.get(mC,"token","").toString();
    }
    public String getVersion(){
        String versionName = "";
        try {
            PackageManager pm = mC.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(mC.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null ) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }
}
