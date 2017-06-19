package com.jtvr.avtivity;

import android.Manifest;
import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.google.gson.Gson;
import com.idealsee.vrsdk.VrSurfaceView;
import com.jt.base.R;
import com.jtvr.base.BaseActivity;
import com.jtvr.fragment.GameFragment;
import com.jtvr.fragment.Home_Recommend_Fragment;
import com.jtvr.fragment.LiveFragment;
import com.jtvr.fragment.MovieFragment;
import com.jtvr.fragment.My_Fragment.MyFragment;
import com.jtvr.http.RequestEnum;
import com.jtvr.http.VrHttp;
import com.jtvr.model.VersionBean;
import com.jtvr.service.DownloadAPKService;
import com.jtvr.utils.UIUtils;
import com.jtvr.utils.UtilsApp;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by jt on 2017/2/24.
 */
public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {
    private BottomNavigationBar mBottomNavigationBar;
    private Home_Recommend_Fragment homeFragment;
    private MovieFragment movieFragment;
    private GameFragment gameFragment;
    private MyFragment myFragment;
    private LiveFragment liveFragment;
    private List<Fragment> list;
    //当前展示的Fragment的位置
    private int currentIndex = 0;
    private Application app;
    private DownloadAPKService.DownloadBinder downloadBinder;
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadAPKService.DownloadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        app = getApplication();
        list = new ArrayList<>();
        homeFragment = new Home_Recommend_Fragment();
        movieFragment = new MovieFragment();
        liveFragment = new LiveFragment();
        gameFragment = new GameFragment();
        myFragment = new MyFragment();
        list.add(homeFragment);
        list.add(movieFragment);
        list.add(liveFragment);
        list.add(gameFragment);
        list.add(myFragment);
        setDefaultFragment();
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.main_bottom_navigation_bar);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
//      mBottomNavigationBar.setBarBackgroundColor(R.color.white);//set background color for navigation bar
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.tabbar_vr_selected, R.string.tab_one).setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.tabbar_vr_normal)).setActiveColorResource(R.color.vr_red).setInActiveColorResource(R.color.vr_565656))
                .addItem(new BottomNavigationItem(R.mipmap.tabbar_film_selected, R.string.tab_two).setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.tabbar_film_normal)).setActiveColorResource(R.color.vr_red).setInActiveColorResource(R.color.vr_565656))
                .addItem(new BottomNavigationItem(R.mipmap.tabbar_live_selected, R.string.tab_three).setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.tabbar_live_normal)).setActiveColorResource(R.color.vr_red).setInActiveColorResource(R.color.vr_565656))
                .addItem(new BottomNavigationItem(R.mipmap.tabbar_game_selected, R.string.tab_four).setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.tabbar_game_normal)).setActiveColorResource(R.color.vr_red).setInActiveColorResource(R.color.vr_565656))
                .addItem(new BottomNavigationItem(R.mipmap.tabbar_my_selected, R.string.tab_five).setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.tabbar_my_normal)).setActiveColorResource(R.color.vr_red).setInActiveColorResource(R.color.vr_565656))
                .setFirstSelectedPosition(0)
                .initialise();
        mBottomNavigationBar.setTabSelectedListener(this);
        //判断当前版本
        getAppVersion();
        Intent intent = new Intent(this, DownloadAPKService.class);
        //这一点至关重要，因为启动服务可以保证DownloadService一直在后台运行，绑定服务则可以让MaiinActivity和DownloadService
        //进行通信，因此两个方法的调用都必不可少。
        startService(intent);  //启动服务
        bindService(intent, connection, BIND_AUTO_CREATE);//绑定服务
        /**
         *运行时权限处理：我们需要再用到权限的地方，每次都要检查是否APP已经拥有权限
         * 下载功能，需要些SD卡的权限，我们在写入之前检查是否有WRITE_EXTERNAL_STORAGE权限,没有则申请权限
         */
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    private void getAppVersion() {
        VrHttp.getInstance().requestForResult(RequestEnum.VERSION, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {
                VersionBean vb = new Gson().fromJson(response.toString(), VersionBean.class);
                if (vb.getStatus() != null) {
                    if (vb.getStatus().equals("99")) {
                        if (!(vb.getBusiness().getVersion().equals(UtilsApp.getIntance().getVersion()))) {
                            showVersionDialog(vb.getBusiness().getUrl());
                        }
                    }
                } else {
                    UIUtils.showToast("数据获取异常");
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
            }
        }, RequestEnum.VERSION.addIntent());

    }

    private void showVersionDialog(final String url) {
        final NormalDialog dialog = new NormalDialog(MainActivity.this);
        dialog.content("天焱TV全新上线！")//
                .style(NormalDialog.STYLE_TWO)//
                .titleTextSize(23)//
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        //开启服务, 下载APK
                        downloadBinder.startDownload(url);
                        dialog.dismiss();
                    }
                });


    }

    /**
     * set the default fragment
     */
    private void setDefaultFragment() {
        //默认显示的Fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.ll_content, list.get(0)).commit();
    }


    @Override
    public void initClick() {

    }

    private void switchFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //当前展示的Fragment
        Fragment currentFragment = list.get(currentIndex);
        //跳转展示的Fragment
        Fragment targeFragment = list.get(position);

        //点击按钮控制Fragment的显示和隐藏
        if (!targeFragment.isAdded()) {
            //如果没有添加到事务中  添加进来目标Fragment  隐藏当前Fragment
            transaction.add(R.id.ll_content, targeFragment).hide(currentFragment).commit();
        } else {
            //如果事物中存在Fragment 显示目标Fragment 隐藏当前Fragment
            transaction.show(targeFragment).hide(currentFragment).commit();
        }
        //替换当前位置
        currentIndex = position;
    }


    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case 0:
                switchFragment(0);
                break;
            case 1:
                switchFragment(1);
                break;
            case 2:
                switchFragment(2);
                break;
            case 3:
                switchFragment(3);

                break;
            case 4:
                switchFragment(4);
                break;
        }


    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    /**
     * 用户选择允许或拒绝后,会回调onRequestPermissionsResult
     *
     * @param requestCode  请求码
     * @param permissions
     * @param grantResults 授权结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
