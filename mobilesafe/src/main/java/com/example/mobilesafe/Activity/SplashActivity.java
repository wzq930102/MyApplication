package com.example.mobilesafe.Activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import utils.StreamUtil;
import utils.ToastUtil;

public class SplashActivity extends Activity {

    /**
     * 更新新版本的状态码
     */
    public static final int UPDATE_VERSION = 100;
    /**
     * 进入应用程序主界面状态码
     */
    public static final int ENTER_HOME = 101;
    protected static final int URL_ERROR = 102;
    protected static final int IO_ERROR = 103;
    protected static final int JSON_ERROR = 104;
    private TextView tv_version_name;
    private int mLocalversionCode;
    private String mVersionDes;
    protected static final String tag = "SplashActivity";
    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_VERSION:
                    //弹出对话框,提示用户更新
                    showUpdateDialog();
                    break;
                case ENTER_HOME:
                    //进入应用程序主界面,activity跳转过程
                    enterHome();
                    break;
                case URL_ERROR:
                    ToastUtil.show(getApplicationContext(), "url异常");
                    enterHome();
                    break;
                case IO_ERROR:
                    ToastUtil.show(getApplicationContext(), "读取异常");
                    enterHome();
                    break;
                case JSON_ERROR:
                    ToastUtil.show(getApplicationContext(), "json解析异常");
                    enterHome();
                    break;
            }
        }





    };
    /**
     * 弹出对话框提示用户更新
     */
    protected void showUpdateDialog() {
        //对话框,是依赖于activity存在的
        Builder builder = new AlertDialog.Builder(this);
        //设置左上角图标
        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("版本更新");
        //设置描述内容
        builder.setMessage(mVersionDes);

        //积极按钮,立即更新
        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //下载apk,apk链接地址,downloadUrl
//                        downloadApk();
                    }
                   });

        builder.setNegativeButton("稍后再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //取消对话框,进入主界面
                enterHome();
            }
        });
        //点击取消事件监听
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                //即使用户点击取消,也需要让其进入应用程序主界面
                enterHome();
                dialog.dismiss();
            }
        });
        builder.show();

    }


    /**
     * 进入应用程序主界面
     */
    protected void enterHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        //在开启一个新的界面后，将导航界面关闭（导航界面可见一次）
        finish();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除当前activity头title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        
        //初始化UI
        initUI();
        //初始化数据
        initDate();
    }

    /**
     * 获取数据方法
     */
    private void initDate() {
        //1，应用版本名称
        tv_version_name.setText("版本名称："+getVersionName());
        //检测（本地版本号喝服务器版本号对比）是否有更新，如果有更新，提示用户下载(number)
        //2，获取本地版本号
        mLocalversionCode = getVersionCode();
        //3,获取服务器版本号（客户端发请求，服务端给响应（json,xml））
        //http://www.oxxx.com/update74.json?key=value  返回200 请求成功,流的方式将数据读取下来
        //json中内容包含:
		/* 更新版本的版本名称
		 * 新版本的描述信息
		 * 服务器版本号
		 * 新版本apk下载地址*/
        CheckVersion();
    }

    /**
     * 检测版本号
     */
    private void CheckVersion() {
        new Thread() {


            private String mVersionDes;

            public void run() {
                //发送请求获取数据,参数则为请求json的链接地址
                Message msg = Message.obtain();
                long startTime = System.currentTimeMillis();
                try {
                    //1，封装url地址
                    URL url = new URL("https://raw.githubusercontent.com/wzq930102/MyApplication/master/json");
                    //2，开启一个链接

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //3,设置常见请求参数（请求头）

                    //请求超时
                    connection.setConnectTimeout(2000);
                    //读取超时
                    connection.setReadTimeout(2000);
                    //默认就是get请求方式
//                    connection.setRequestMethod("POST");

                    //4,获取请求成功响应码
                    if (connection.getResponseCode() == 200) {
                        //5,以流的形式，将数据获取下来
                    }
                    InputStream is = connection.getInputStream();
                    //6,将流转换成字符串（工具类）
                    String json = StreamUtil.streamToString(is);
                    Log.i(tag, json);
                    //7,json解析
                    try {
                        JSONObject jsonObject = new JSONObject(json);

                        //debug调试,解决问题
                        String versionName = jsonObject.getString("version_Name");
                        mVersionDes = jsonObject.getString("description");
                        String versionCode = jsonObject.getString("version_Code");
                        String downloadUrl = jsonObject.getString("download_Url");
                        //日志打印
                        Log.i(tag, versionName);
                        Log.i(tag, mVersionDes);
                        Log.i(tag, versionCode);
                        Log.i(tag, downloadUrl);

                        //8,比对版本号(服务器版本号>本地版本号,提示用户更新)

                        if (mLocalversionCode < Integer.parseInt(versionCode)) {
                            //提示用户更新
                            msg.what = UPDATE_VERSION;
                        } else {
                            //进入应用程序主界面
                            msg.what = ENTER_HOME;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        msg.what = JSON_ERROR;
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    msg.what = URL_ERROR;
                } catch (IOException e) {
                    e.printStackTrace();
                    msg.what = IO_ERROR;
                } finally {
                    //指定睡眠时间，请求网络时常超过4秒则不做处理
                    //请求网络时常小于4秒，强制让其睡眠满4秒
                    long endTime = System.currentTimeMillis();
                    if (endTime - startTime < 4000) {
                        try {
                            Thread.sleep(4000 - (endTime - startTime));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    mHandler.sendMessage(msg);
                }
            }
        }.start();
    }
    /**
     * @return
     * 返回版本号
     *    非0则代表获取成功
     */
    private int getVersionCode() {
        //1，包管理者对象packageManger
        PackageManager pm = getPackageManager();
        //2,从包的管理者对象中获取指定包名的基本信息（版本名称，版本号，0代表获取基本信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            //3,获取对应的版本名称
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @return   应用版本名称    返回null代表异常
     * 获取版本名称：清单文件中
     */
    private String getVersionName() {
        //1，包管理者对象packageManger
        PackageManager pm = getPackageManager();
        //2,从包的管理者对象中获取指定包名的基本信息（版本名称，版本号，0代表获取基本信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            //3,获取对应的版本名称
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 初始化UI
     */
    private void initUI() {
        tv_version_name = (TextView) findViewById(R.id.tv_version_name);
    }
}
