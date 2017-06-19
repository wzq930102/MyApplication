package com.jtvr.app;


import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.telephony.TelephonyManager;

import com.jtvr.utils.ResolutionUtil;
import com.jtvr.utils.UtilsApp;

/**
 * Created by jt on 2017/3/1.
 */
public class VRApplication extends Application {


    private static Context context;
    private static Handler handler;
    private static int mainThreadId;
    private static String IMEI;
    private Boolean isVersion = false;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
        mainThreadId = android.os.Process.myTid();
        UtilsApp.initUtilsApp(this);
        ResolutionUtil.getInstance().init(this);

        TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        IMEI = TelephonyMgr.getDeviceId();
    }

    public Boolean getVersion() {
        return isVersion;
    }

    public void setVersion(Boolean version) {
        isVersion = version;
    }

    public static String getIMEI() {
        return IMEI;
    }

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

}
