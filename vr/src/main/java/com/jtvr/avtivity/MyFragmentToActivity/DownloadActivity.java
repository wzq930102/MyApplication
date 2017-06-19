package com.jtvr.avtivity.MyFragmentToActivity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.jt.base.R;
import com.jtvr.adapter.DownloadAcivityAdapter;
import com.jtvr.base.BaseActivity;
import com.jtvr.db.SQLiteUtils;
import com.jtvr.jtInterface.MyClickListenner;
import com.jtvr.model.DBBean;
import com.jtvr.model.MessageEvent;
import com.jtvr.service.DownloadService;
import com.jtvr.utils.DividerItemDecoration;
import com.jtvr.utils.LogUtils;
import com.jtvr.utils.NetworkHelper;
import com.jtvr.utils.SPUtils;
import com.jtvr.utils.SharedPreferencesUtils;
import com.jtvr.utils.UIUtils;
import com.jtvr.vrplaymedia.VrPlayMediaActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DownloadActivity extends BaseActivity implements MyClickListenner {
    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.title_register)
    TextView titleRegister;
    @BindView(R.id.download_recyclerView)
    RecyclerView downloadRecyclerView;
    @BindView(R.id.xrefreshview)
    XRefreshView xRefreshView;
    @BindView(R.id.downloadAll)
    TextView downloadAll;
    @BindView(R.id.downloadDelete)
    TextView downloadDelete;
    @BindView(R.id.download_parent1)
    LinearLayout linearLayout;
    private DownloadAcivityAdapter adapter;
    private SQLiteUtils sqLiteUtils;
    private List<DBBean> list;
    private List<Boolean> listCheck;
    private List<DBBean> data;
    private DownloadService.DownloadBinder downloadBinder;
    private LocalBroadcastManager localBroadcastManager;
    private MyBroadcastReceiver myBroadcastReceiver;
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int contentLengthSS = intent.getIntExtra("contentLengthSS", 0);//总进度
            int progressSS = intent.getIntExtra("progressSS", 0);
            int totalSS = intent.getIntExtra("totalSS", 0);
            list.get(0).setArgs2(totalSS + "");
            list.get(0).setArgs3(contentLengthSS + "");
            list.get(0).setArgs4(progressSS + "");
            adapter.notifyItemChanged(0);
            String success = intent.getStringExtra("success");
            if (success != null) {
                list.get(0).setFinish("1");
                sqLiteUtils.Update("1", list.get(0).getVideocode());
                List<DBBean> list2 = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    if ("1".equals(list.get(i).getFinish())) {
                        //下载完成
                        list2.add(list.get(i));
                    }
                }
                Iterator<DBBean> it = list.iterator();
                while (it.hasNext()) {
                    DBBean x = it.next();
                    if ("1".equals(x.getFinish())) {
                        it.remove();
                    }
                }

                list.addAll(list2);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_download);
        ButterKnife.bind(this);
        titleTitle.setText("我的缓存");
        titleRegister.setText("编辑");
        sqLiteUtils = new SQLiteUtils(this);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);//本地广播
        myBroadcastReceiver = new MyBroadcastReceiver();
        localBroadcastManager.registerReceiver(myBroadcastReceiver, new IntentFilter("LOCAL_ACTION"));
        Intent intent = new Intent(this, DownloadService.class);


        startService(intent);  //启动服务
        bindService(intent, connection, BIND_AUTO_CREATE);//绑定服务

        String name = (String) SharedPreferencesUtils.get(this, "name", "");
        if ("".equals(name)) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {
            initData();
            initListener();
            /**
             *运行时权限处理：我们需要再用到权限的地方，每次都要检查是否APP已经拥有权限
             * 下载功能，需要些SD卡的权限，我们在写入之前检查是否有WRITE_EXTERNAL_STORAGE权限,没有则申请权限
             */
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }

        }

    }

    private void initData() {
        list = sqLiteUtils.selectAllData();
        data = new ArrayList<>();
        listCheck = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            listCheck.add(false);
        }
        for (int i = 0; i < list.size(); i++) {
            if ("1".equals(list.get(i).getFinish())) {
                //下载完成
                data.add(list.get(i));
            }
        }
        Iterator<DBBean> it = list.iterator();
        while (it.hasNext()) {
            DBBean x = it.next();
            if ("1".equals(x.getFinish())) {
                it.remove();
            }
        }
        for (int i = 1; i < list.size(); i++) {
            if (!"1".equals(list.get(i).getFinish())) {
                list.get(i).setFinish("4");
            }
        }
        list.addAll(data);
        downloadRecyclerView.setHasFixedSize(true);
        xRefreshView.setPullLoadEnable(false);
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setPullRefreshEnable(false);//关闭下拉加载
        adapter = new DownloadAcivityAdapter(list, this, listCheck, this);
        downloadRecyclerView.setAdapter(adapter);
        downloadRecyclerView.setItemAnimator(null);
        downloadRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        downloadRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }

    private void initListener() {
        adapter.setOnItemListener(new DownloadAcivityAdapter.OnItemClickListener() {
            @Override
            public void setOnItemClick(int position, boolean isCheck) {
//                listCheck.set(position,isCheck);
            }

            @Override
            public void setOnItemCheckedChangeds(int position, boolean isCheck) {
                listCheck.set(position, isCheck);
                int num = 0;
                for (int i = 0; i < listCheck.size(); i++) {
                    if (listCheck.get(i)) {
                        num = num + 1;
                    }
                }
                if (num == 0) {
                    downloadDelete.setText("删除");
                } else {
                    downloadDelete.setText("删除" + "(" + num + ")");
                }
                if (num == listCheck.size()) {
                    downloadAll.setText("全不选");
                } else {
                    downloadAll.setText("全选");
                }
            }
        });
    }

    @Override
    public void initClick() {

    }

    @OnClick({R.id.title_back, R.id.title_register, R.id.downloadDelete, R.id.downloadAll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_register:
                if ("编辑".equals(titleRegister.getText().toString())) {
                    titleRegister.setText("取消");
                    adapter.isShow = true;
                    adapter.notifyDataSetChanged();
                    linearLayout.setVisibility(View.VISIBLE);
                } else {
                    titleRegister.setText("编辑");
                    adapter.isShow = false;
                    adapter.notifyDataSetChanged();
                    linearLayout.setVisibility(View.GONE);
                    for (int i = 0; i < listCheck.size(); i++) {
                        listCheck.set(i, false);
                    }
                    downloadAll.setText("全选");
                }
                break;
            case R.id.downloadAll:
                if ("全选".equals(downloadAll.getText().toString())) {
                    downloadAll.setText("全不选");
                    for (int i = 0; i < listCheck.size(); i++) {
                        listCheck.set(i, true);
                    }
                } else {
                    downloadAll.setText("全选");
                    for (int i = 0; i < listCheck.size(); i++) {
                        listCheck.set(i, false);
                    }
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.downloadDelete:
                int length = listCheck.size();
                for (int i = length - 1; i >= 0; i--) {
                    if (listCheck.get(i)) {
                        sqLiteUtils.deleteAll(list.get(i).getVideocode());
                        adapter.notifyItemRemoved(i);
                        adapter.notifyItemRangeChanged(0, list.size());
                        sqLiteUtils.deleteAll(list.get(i).getVideocode());
                        if (i == 0) {
                            downloadBinder.cancelDownload();
                            String[] str = list.get(i).getArgs1().split("/");
                            String fileName = str[str.length - 2] + str[str.length - 1];
                            //下载文件存放的目录
                            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                            //创建一个文件
                            File file = new File(directory + "/" + fileName);
                            deleteFile(file);
                        } else {
                            String[] str = list.get(i).getArgs1().split("/");
                            String fileName = str[str.length - 2] + str[str.length - 1];
                            //下载文件存放的目录
                            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                            //创建一个文件
                            File file = new File(directory + "/" + fileName);
                            deleteFile(file);
                        }
                        list.remove(i);
                        listCheck.remove(i);
                    }
                }

                downloadDelete.setText("删除");
                downloadAll.setText("全选");
                titleRegister.setText("编辑");
                adapter.isShow = false;
                adapter.notifyDataSetChanged();
                linearLayout.setVisibility(View.GONE);
                for (int i = 0; i < listCheck.size(); i++) {
                    listCheck.set(i, false);
                }
                break;
        }
    }

    private boolean isDown = false;

    @Override
    public void MyRecyclerViewClickListenner(View view, int position) {
        if (!"取消".equals(titleRegister.getText().toString()) && "1".equals(list.get(position).getFinish())) {
            Intent intent = new Intent(this, VrPlayMediaActivity.class);
            intent.putExtra("video", list.get(position).getVideocode());
            startActivity(intent);
        }
        if ("2".equals(list.get(position).getFinish())) {
            downloadBinder.pauseDownload();
            isDown = true;
        }
        if ("2".equals(list.get(position).getFinish()) && isDown) {
            Boolean b = (Boolean) SPUtils.get(this, "keepwifi", false);
            if (b) {
                if (NetworkHelper.getConnectionType(this)==1) {
                    //是WIFI
                    downloadBinder.startDownload(list.get(position).getArgs1());
                } else {
                    //不是WIFI
                    UIUtils.showToast("您已设置WIFI环境下进行离线缓存，请连接WIFI后下载。");
                }
            } else {
                if (NetworkHelper.getConnectionType(this)==1) {
                    //是WIFI
                    downloadBinder.startDownload(list.get(position).getArgs1());
                } else {
                    //不是WIFI
                    UIUtils.showToast("当前是数据流量哦，请注意！");
                    downloadBinder.startDownload(list.get(position).getArgs1());
                }
            }


        }
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
        //解除绑定服务
        unbindService(connection);
        if (localBroadcastManager != null && myBroadcastReceiver != null) {
            //取消注册本地广播
            localBroadcastManager.unregisterReceiver(myBroadcastReceiver);
        }
    }

    public void deleteFile(File file) {
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile()) { // 判断是否是文件
                file.delete(); // delete()方法 你应该知道 是删除的意思;
            }
        }
    }

    /**
     * 在Ui线程执行
     * 不管发送消息在那个线程  都在主线程接收
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MessageEvent messageEvent) {
        if (messageEvent.getType() == 1) {
            LogUtils.e("-------");
            downloadBinder.pauseDownload();
        }

    }
}
