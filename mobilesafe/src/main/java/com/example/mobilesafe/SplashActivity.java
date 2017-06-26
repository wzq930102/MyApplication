package com.example.mobilesafe;

import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import com.example.mobilesafe.utils.StreamUtil;
import com.example.mobilesafe.utils.ToastUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SplashActivity extends AppCompatActivity {
    /**
     * 更新新版本的状态码
     */
    public static final int UPDATE_VERSION = 100;
    public static final int IO_ERROR = 104;

    public static final int JSON_ERROR =103 ;
    /**
     * url地址出错状态码
     */
    public static final int URL_ERROR = 102;
    /**
     * 进入应用程序主界面的状态码
     */
    public static final int ENTER_HOME = 101;
    private String mVersionDex;
    private String mDawnloadUrl;
    private TextView tv_version_name;
    private int mLocalVersionCode;
    String tag = "SplashActivity";
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_VERSION:
                    //弹出对话框，提示用户更新
                    showUpdateDialog();
                    break;
                case IO_ERROR:
                    ToastUtil.show(getApplicationContext(),"读取异常");
                    enterHome();
                    break;
                case JSON_ERROR:
                    ToastUtil.show(getApplicationContext(),"JSON解析异常");
                    enterHome();
                    break;
                case URL_ERROR:
                    ToastUtil.show(getApplicationContext(),"url异常");
                    enterHome();
                    break;
                case ENTER_HOME:
                    //进入应用 程序主界面，activity跳转过程
                    enterHome();
                    break;
        }
    }  /**
         * 弹出对话框，提示用户更新
         */

        protected void showUpdateDialog() {
            //对话框,是依赖于activity存在的
            final AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
            //设置左上角图标
            builder.setIcon(R.drawable.ic_lungcher);
            builder.setTitle("版本更新");
            //设置描述内容
            builder.setMessage(mVersionDex);
            //积极按钮，立即更新
            builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //下载apk，apk链接地址，downloadUrl
                    downloadApk();
                }
            });
           builder.setNegativeButton("稍后在说", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                    //取消对话框，进入主界面
                   enterHome();
               }
           });

            builder.show();
        }

        private void downloadApk() {
            //apk下载链接地址，放置apk所在路径

            //1，判断sd卡是否可用，是否挂在上
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                //2,获取sd卡路径
               String path =  Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator
                       +"mobilesafe.apk";
                //3,发送请求获取apk,并且放置到指定的路径
                HttpUtils httpUtils = new HttpUtils();
                //4，发送请求，传递参数（下载地址，下载应用放置的位置）
                httpUtils.download(mDawnloadUrl, path, new RequestCallBack<File>() {
                    @Override
                    public void onSuccess(ResponseInfo<File> responseInfo) {
                        //下载成功(下载过后的放置在sd卡中apk)
                        Log.i(tag,"下载成功");
                        File file = responseInfo.result;
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Log.i(tag,"下载失败");
                        //下载失败
                    }
                    //刚刚开始下载方法

                    @Override
                    public void onStart() {
                        Log.i(tag,"刚刚开始下载");
                        super.onStart();
                    }
                    //下载过程中的方法(下载apk总大小，当前的下载位置，是否正在下载)
                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        Log.i(tag,"下载中.......");
                        Log.i(tag,"total = "+total);
                        Log.i(tag,"current = "+current);
                        super.onLoading(total, current, isUploading);
                    }
                });
            }
        }
    };

    /**
     * 进入应用程序主界面
     */
    protected void enterHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        //在开启一个新的界面后，将导航界面关闭（导航界面只可见一次）
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去除掉当前activity头title
        setContentView(R.layout.activity_splash);

        //初始化UI
        initUI();
        //初始化数据
        initData();
    }
    /**
     * 获取数据方法
     */
    private void initData() {
        //1,应用版本名称
        tv_version_name.setText("版本名称："+ getVersionName());
        //检测（本地版本号和服务器版本号比对）是否有更新，如果有更新，提示用户下载（member）
        //2，获取本地版本号
        mLocalVersionCode = getVersionCode();
        //3,获取服务器版本号（客户端发请求，服务员端给响应（json,xml））
        //http://www.oxxx.com/update74.json？key=value 返回200请求成功，流的方式将数据读取下来
        //json中内容包含
        /**
         *  更新版本的版本名称
         *  新版本的描述信息
         *  服务器版本号
         *  新版本apk下载地址
         */
        checkVersion();


    }

    /**
     * 检测版本号
     */
    private void checkVersion() {
        new Thread(){




            public void run(){
                //发送请求获取数据,参数则为请求json的连接地址
                Message msg = Message.obtain();
                long startTime = System.currentTimeMillis();
                try {
                    //1,封装url地址
                    URL url = new URL("https://raw.githubusercontent.com/wzq930102/MyApplication/master/json");
                    //2，开启一个链接
                    try {
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        //3，设置常见请求参数（请求头）

                        //请求超时
                        connection.setConnectTimeout(2000);
                        //读取超时
                        connection.setReadTimeout(2000);

//                        //默认就是get请求方式
//                        connection.setRequestMethod("POST");

                        //4，获取请求成功响应码
                        if (connection.getResponseCode() == 200){
                            //5,以流的形式，将数据获取下来
                            InputStream is =connection.getInputStream();
                            //6,将流转换成字符串（工具类封装）
                            String json = StreamUtil.streamToString(is);

                            Log.e(tag,json);
                            //7,
                            try {
                                JSONObject jsonObject = new JSONObject(json);
                                String versionName = jsonObject.getString("version_Name");
                                mVersionDex = jsonObject.getString("description");
                                String versionCode = jsonObject.getString("version_Code");
                                mDawnloadUrl = jsonObject.getString("download_Url");

                                Log.i(tag,versionName);
                                Log.i(tag, mVersionDex);
                                Log.i(tag,versionCode);
                                Log.i(tag, mDawnloadUrl);

                                //8,比对版本号（服务器版本号大于本地版本号，提示用户更新）
                                if (mLocalVersionCode<Integer.parseInt(versionCode)){
                                    //提示用户更新，弹出对话框（UI），消息机制
                                    msg.what = UPDATE_VERSION;
                                }else{
                                    //进入应用程序主界面，跳转主界面
                                    msg.what = ENTER_HOME;
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                msg.what = JSON_ERROR;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        msg.what = IO_ERROR;
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    msg.what = URL_ERROR;
                }finally {
                    //指定睡眠时间，请求网络的时长超过4秒则不做处理
                    //请求网络的时长小于4秒，强制让其睡眠满4秒钟
                    long endTime = System.currentTimeMillis();
                    if (endTime-startTime<4000){
                        try {
                            Thread.sleep(4000-(endTime-startTime));
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
     * 返回版本号
     * @return
     *
     */
    private int getVersionCode() {
        //1，包管理者对象packageManager
        PackageManager pm = getPackageManager();
        //2,从包的管理者对象中获取指定包名的基本信息,传0代表过去基本信息
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
     * 获取版本名称：清单文件中
     * @return  应用版本名称 返回null代表异常
     */
    private String getVersionName() {
        //1，包管理者对象packageManager
        PackageManager pm = getPackageManager();
        //2,从包的管理者对象中获取指定包名的基本信息,传0代表过去基本信息
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
     * 初始化UI方法
     */
    private void initUI() {
        tv_version_name = (TextView)findViewById(R.id.tv_version_name);
    }


}
