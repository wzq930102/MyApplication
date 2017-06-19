package com.hanyong.mobilesafe.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.hanyong.mobilesafe.R;
import com.hanyong.mobilesafe.utils.StreamUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.R.attr.versionCode;
import static android.R.attr.versionName;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";
    private TextView tv_version_name;
    private int mLocalVersionCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        //初始化UI
        initUI();
        //初始化数据
        initData();
    }
    /**
     * 获取数据的方法
     */
    private void initData() {
        //1，应用版本名称
        tv_version_name.setText("版本名称："+getVersionName());
        //检测（本地版本号喝服务器版本号比对）是否有更新，如果有更新，提示用户下载(member)
        //2，获取本地版本号
        mLocalVersionCode = getVersionCode();
        //3，获取服务器版本号（客户端发请求，服务端给响应,（json,xml））
        //http:www.oxxx.com.update.json?key=value  返回200  请求成功，流的方式将数据读取下来
        //json中内容包含：
        /*更新版本内容的版本名称
        *新版本的描述信息
        *服务器版本号
        *新版本apk下载地址*/
        checkVersion();

    }

    /**
     * 检测版本号
     */
    private void checkVersion() {
        new Thread(){
            public void run(){
                //发送请求获取数据,参数则为请求json的连接地址
                try {
                    //1,封装url地址
                    URL url = new URL("https://raw.githubusercontent.com/wzq930102/MyApplication/master/json");
                    //2，开启一个连接
                    HttpURLConnection connection =  (HttpURLConnection) url.openConnection();
                    //3,設置常見請求參數（）請求頭

                    //請求超时
                    connection.setConnectTimeout(2000);
                    //读取超时
                    connection.setReadTimeout(2000);

                    //默认就是get请求方式
//                    connection.setRequestMethod("POST");

                    //4，获取响应码
                    if(connection.getResponseCode()==200){
                        //以流的形式，将数据获取下来
                        InputStream is = connection.getInputStream();
                        //6，将流转换成字符串（工具类封装）
                        String json = StreamUtil.streamToString(is);

                        Log.i(TAG,json);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
    }

    /**
     * 返回版本号
     * @return
     *          非0则代表获取成功
     */
        public int getVersionCode() {
            //1，包管理者对象packgeManger
            PackageManager pm = getPackageManager();
            //2，从包的管理者对象中，获取指定包名的基本信息（版本名称，版本号）,传0代表获取基本信息
            try {
                PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
                //3，获取版本名称
                return packageInfo.versionCode;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
    }
    /**
     * 获取版本名称：清单文件中
     * @return 应用版本名称 返回null代表异常
     */
    public String getVersionName() {
        //1，包管理者对象packgeManger
        PackageManager pm = getPackageManager();
        //2，从包的管理者对象中，获取指定包名的基本信息（版本名称，版本号）,传0代表获取基本信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            //3，获取版本名称
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
         return  null;
    }

    /**
     * 初始化UI方法
     */
    private void initUI() {
        tv_version_name = (TextView) findViewById(R.id.tv_version_name);
    }


}
