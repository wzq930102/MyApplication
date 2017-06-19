package com.jtvr.vrplaymedia;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jt.base.R;
import com.jtvr.adapter.MovieDetailAdapter.MovieDateilAdapter;
import com.jtvr.adapter.PlaydateAdapter;
import com.jtvr.avtivity.MyFragmentToActivity.LoginActivity;
import com.jtvr.avtivity.MyFragmentToActivity.VIPActivity;
import com.jtvr.db.SQLiteUtils;
import com.jtvr.fragment.PlayDetailCommentFragment;
import com.jtvr.http.RequestEnum;
import com.jtvr.http.VrHttp;
import com.jtvr.model.CodeBean;
import com.jtvr.model.PlayDateilInfo;
import com.jtvr.model.SuccedInfo;
import com.jtvr.model.playMovieBean;
import com.jtvr.service.DownloadService;
import com.jtvr.utils.LogUtils;
import com.jtvr.utils.UIUtils;
import com.jtvr.view.widget.ActionSheetDialog;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.player.data.panoramas.PanoramaData;
import com.player.panoplayer.IPanoPlayerListener;
import com.player.panoplayer.IPanoPlayerVideoPluginListener;
import com.player.panoplayer.PanoPlayer;
import com.player.panoplayer.PanoPlayerUrl;
import com.player.panoplayer.Plugin;
import com.player.panoplayer.plugin.VideoPlugin;
import com.player.renderer.PanoPlayerSurfaceView;
import com.player.util.ViewMode;

import org.opencv.android.OpenCVLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import detutv.danmaku.ijk.media.player.IjkMediaPlayer;
import okhttp3.Call;

/**
 * Created by jt on 2017/3/22.
 */
public class VrPlayMediaActivity extends Activity implements IPanoPlayerListener, IPanoPlayerVideoPluginListener {
    @BindView(R.id.player_movie_name)
    TextView mPlayerMovieName;
    @BindView(R.id.player_movie_time)
    TextView mPlayerMovieTime;
    @BindView(R.id.player_movie_address)
    TextView mPlayerMovieAddress;
    @BindView(R.id.player_movie_pc)
    TextView mPlayerMoviePc;
    @BindView(R.id.player_movie_owner)
    TextView mPlayerMovieOwner;
    @BindView(R.id.player_movie_expend)
    ExpandableTextView mPlayerMovieExpend;
    @BindView(R.id.iv_is_vr2)
    TextView miv_is_vr2;
    @BindView(R.id.playdateil_right)
    ImageView mplaydateil_right;
    @BindView(R.id.include_pause)
    TextView minclude_pause;
    @BindView(R.id.playdata_batv)
    TextView mplaydata_batv;
    @BindView(R.id.vrplay_hits)
    TextView mvrplay_hits;
    @BindView(R.id.video_control_collect)
    ImageView mvideo_control_collect;
    @BindView(R.id.video_control_download)
    ImageView mvideo_control_download;
    @BindView(R.id.video_control_share)
    ImageView mvideo_control_share;
    @BindView(R.id.playdate_recyclerview)
    RecyclerView mrecyclerview;
    @BindView(R.id.iv_play_re)
    RelativeLayout miv_play_re;
    @BindView(R.id.player_movie_li)
    LinearLayout mplayer_movie_li;
    @BindView(R.id.playdetail_buyln)
    LinearLayout mplaydetail_buyln;//购买vip
    @BindView(R.id.playdetail_buyln_buy)
    TextView mplaydetail_buyln_buy;
    @BindView(R.id.playdetail_buyln_vip)
    TextView mplaydetail_buyln_vip;
    @BindView(R.id.playdetail_buyln_log)
    TextView mplaydetail_buyln_log;
    @BindView(R.id.vrplay_grade)
    TextView mvrplay_grade;
    PlaydateAdapter myAdapter;
    private Dialog progressDialog;
    private SQLiteUtils sqLiteUtils;
    private DownloadService.DownloadBinder downloadBinder;
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder) service;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    public static final int UPDATE_PALY_TIME = 0x01;//更新播放时间
    private PlayDateilInfo pdInfo;
    public static final int UPDATE_TIME = 800;
    public static final int HIDE_CONTROL_BAR = 0x02;//隐藏控制条
    public static final int HIDE_TIME = 5000;//隐藏控制条时间
    public static final int SHOW_CENTER_CONTROL = 0x03;//显示中间控制
    public static final int SHOW_CONTROL_TIME = 1000;
    private MovieDateilAdapter mdadpter;
    private Context context;
    private PanoPlayer panoplayer_renderer;
    private playMovieBean pmb;//细节对象
    private PanoPlayerSurfaceView glview;
    private FrameLayout mVideoLayout;
    protected Handler handler = new Handler();
    private PanoPlayer.PanoVideoPluginStatus playerStatus = PanoPlayer.PanoVideoPluginStatus.VIDEO_STATUS_STOP;
    private boolean isSeekBarDragging;
    private VideoPlugin videoplugin;
    private boolean isBeginPlaylive = false;
    private boolean mIsFullScreen = false;//是否为全屏
    private String url;
    private int stype = 0;//模式
    private RelativeLayout controlTop;
    private ImageView ivplay;
    private ImageView isFullScreen;
    private SeekBar sb;
    private TextView tvtimecu, tvtimesum;
    private RelativeLayout ivback;
    private int i;
    private TextView isvr;
    private boolean isHide = false;
    private String fireNum ;
    private String videoCode;
    private Boolean isCollect = false;
    private Handler myHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case HIDE_CONTROL_BAR://隐藏控制条
                    hideBT();
                    break;
                case SHOW_CENTER_CONTROL://显示控制条
                    showBT();
                    break;
            }
            return false;
        }
    });
    static {
        if (!OpenCVLoader.initDebug()) {
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vrplaymedia);
        intWidget();//初始化控件
        intNetdata();//获取数据
        initAddNum();//点击率
        initAddHistory();//浏览记录
        intPlayDate();//初始化播放器
        initClick();//点击事件
        initService();
    }

    private void initService() {
        Intent intent = new Intent(this, DownloadService.class);
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

    private void initDB() {
        if (!sqLiteUtils.SlelectID(videoCode)) {
            sqLiteUtils.InsertData(videoCode, pmb.getBusiness().getTitle(), pmb.getBusiness().getImg(), "0", pmb.getBusiness().getLength(), "2", pmb.getBusiness().getUrl());
            UIUtils.showToast("成功添加至我的缓存列表");
            downloadBinder.startDownload(pmb.getBusiness().getUrl());
        } else {
            UIUtils.showToast("请勿重复添加");
        }
    }

    //用户浏览记录
    private void initAddHistory() {
        VrHttp.getInstance().requestForResult(RequestEnum.ADDHSITORY, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {
            }
            @Override
            public void onError(Call call, Exception e, int id) {
            }
        }, RequestEnum.ADDHSITORY.addIntent(videoCode));
    }

    //点击提交上传点击率
    private void initAddNum() {
        VrHttp.getInstance().requestForResult(RequestEnum.COMMITNUM, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {
            }

            @Override
            public void onError(Call call, Exception e, int id) {

            }
        }, RequestEnum.COMMITNUM.addIntent(videoCode));
    }
    public void initClick() {
        //火种充值
        mplaydetail_buyln_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIUtils.setOnClickLoginListen(context, new UIUtils.OnClickIfLoginListen() {
                    @Override
                    public void OnClick() {
                        showSexSheet();
                    }
                });
            }
        });
        //购买VIP
        mplaydetail_buyln_vip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, VIPActivity.class);
                startActivity(i);
            }
        });
        //登录
        mplaydetail_buyln_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIUtils.startLoginActivity(context);
            }
        });
        //VR动画
        mplaydateil_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIUtils.showToast("敬请期待");
            }
        });
         //收藏
        mvideo_control_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repeat();
                if(isCollect){//已经关注
                    unCollectVideo(pmb.getBusiness().getKeepCode());
                }else{//没有关注
                    collectVideo(videoCode);
                }
            }
        });
        /**
         * 下载
         */
        mvideo_control_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDB();

            }
        });

        /**
         * 分享
         */
        mvideo_control_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "敬请期待", Toast.LENGTH_SHORT).show();
            }
        });
        /**
         * 点击眼镜
         */
        miv_is_vr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(VrPlayMediaActivity.this,VrImageActivty.class);
//                startActivityForResult(intent,1);
            }
        });
        /**
         * 中间播放
         */
        minclude_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isBeginPlaylive) {
                    beiginPlay();
                }
            }
        });
        /**
         * 播放、暂停
         */
        miv_play_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (!isBeginPlaylive) {
                    beiginPlay();
                } else {
                    Log.e("", "click btn_play");
                    switch (playerStatus) {
                        case VIDEO_STATUS_PAUSE:
                            videoplugin.start();
                            Log.e("", "click btn_play to start");
                            break;
                        case VIDEO_STATUS_STOP:
                            videoplugin.start();
                            Log.e("", "click btn_play to start");
                            break;
                        case VIDEO_STATUS_PLAYING:
                            videoplugin.pause();
                            Log.e("", "click btn_play to pause");
                            break;
                        default:
                            break;
                    }
                    myHandler.removeMessages(HIDE_CONTROL_BAR);
                    myHandler.sendEmptyMessageDelayed(HIDE_CONTROL_BAR, HIDE_TIME);
                }
            }
        });
         //为SeekBar添加监听
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isSeekBarDragging = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (isBeginPlaylive) {
                    if (playerStatus == PanoPlayer.PanoVideoPluginStatus.VIDEO_STATUS_FINISH) {
                        playVideo();
                        isSeekBarDragging = false;
                        videoplugin.seekTo(sb.getProgress());
                    } else {//正常
                        isSeekBarDragging = false;
                        videoplugin.seekTo(sb.getProgress());
                    }
                } else {
                    sb.setProgress(0);
                }
            }
        });
        //全屏
        isFullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsFullScreen) {
                    setupUnFullScreen();
                } else {
                    setupFullScreen();
                }
                myHandler.removeMessages(HIDE_CONTROL_BAR);
                myHandler.sendEmptyMessageDelayed(HIDE_CONTROL_BAR, HIDE_TIME);
            }
        });
        //返回
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (mIsFullScreen) {
                    setupUnFullScreen();
                } else {
                    finish();
                }
            }
        });
         //显示、隐藏
        findViewById(R.id.include_re).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("----", "include_re");
                if (isHide) {
                    myHandler.sendEmptyMessage(SHOW_CENTER_CONTROL);
                } else {
                    myHandler.removeMessages(HIDE_CONTROL_BAR);
                    myHandler.sendEmptyMessage(HIDE_CONTROL_BAR);
                }
            }
        });
        //模式
        isvr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (panoplayer_renderer != null) {
                    switch (i){
                        case 0://vr/360
                            stype++;
                            int a = stype%2;
                            switch (a){
                                case 0://vr
                                    panoplayer_renderer.setViewMode(ViewMode.VIEWMODE_VR);
                                    panoplayer_renderer.setGyroEnable(true);
                                    isvr.setText("VR");
                                    break;
                                case 1://360
                                    panoplayer_renderer.setGyroEnable(true);
                                    panoplayer_renderer.setViewMode(ViewMode.VIEWMODE_DEF);
                                    isvr.setText("360°");
                                    break;
                            }
                            break;//vr/360
                        case 1:
                            stype++;
                            int a1 = stype%2;
                            switch (a1){
                                case 0://vr
                                    panoplayer_renderer.setViewMode(ViewMode.VIEWMODE_VR);
                                    panoplayer_renderer.setGyroEnable(true);
                                    isvr.setText("VR");
                                    break;
                                case 1://360
                                    panoplayer_renderer.setGyroEnable(true);
                                    panoplayer_renderer.setViewMode(ViewMode.VIEWMODE_DEF);
                                    isvr.setText("360°");
                                    break;
                            }
                            break;
                    }
                }
                myHandler.removeMessages(HIDE_CONTROL_BAR);
                myHandler.sendEmptyMessageDelayed(HIDE_CONTROL_BAR, HIDE_TIME);
            }
        });
    }

    private void repeat() {
        mvideo_control_collect.setClickable(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mvideo_control_collect.setClickable(true);
            }
        },2000);
    }
    private void unCollectVideo(String keepCode) {
        showProgressDialog("正在取消");
        VrHttp.getInstance().requestForResult(RequestEnum.CLEANCOLLECTION, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {
                CodeBean codeBean = new Gson().fromJson(response, CodeBean.class);
                if ("99".equals(codeBean.getStatus())) {
                    hideProgressDialog();
                    isCollect = false;
                    mvideo_control_collect.setImageResource(R.drawable.nav_icon_collect);
                    UIUtils.showToast(codeBean.getMsg());
                } else {
                    UIUtils.showToast(codeBean.getMsg());
                }
            }
            @Override
            public void onError(Call call, Exception e, int id) {

            }
        }, RequestEnum.CLEANCOLLECTION.addIntent(keepCode));

    }

    //收藏视频
    public void collectVideo( String videoCode) {
        showProgressDialog("正在提交");
        VrHttp.getInstance().requestForResult(RequestEnum.PUTCOLLECTION, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {
                Gson gson=new Gson();
                CodeBean codeBean = gson.fromJson(response, CodeBean.class);
                if("99".equals(codeBean.getStatus())){
                    hideProgressDialog();
                    mvideo_control_collect.setImageResource(R.drawable.nav_icon_collect_yellow);
                    isCollect = true;
                    pmb.getBusiness().setKeepCode(codeBean.getKeepCode());
                    UIUtils.showToast(codeBean.getMsg());
                }else if("41".equals(codeBean.getStatus())){
                    UIUtils.showToast(codeBean.getErrmsg());
                    hideProgressDialog();
                    startActivity(new Intent(VrPlayMediaActivity.this,LoginActivity.class));
                }else if("49".equals(codeBean.getStatus())){
                    UIUtils.showToast(codeBean.getMsg());
                    hideProgressDialog();
                }
            }
            @Override
            public void onError(Call call, Exception e, int id) {

            }
        },RequestEnum.PUTCOLLECTION.addIntent(videoCode));
    }

    private void beiginPlay() {
        playVideo();
        minclude_pause.setVisibility(View.GONE);
        findViewById(R.id.progressbar).setVisibility(View.VISIBLE);
        ivplay.setImageResource(R.drawable.video_pause);
    }
    private void intWidget() {
        ButterKnife.bind(this);
        context = VrPlayMediaActivity.this;
        sqLiteUtils=new SQLiteUtils(context);
        videoCode = getIntent().getStringExtra("video");
        mVideoLayout = (FrameLayout) findViewById(R.id.video_layout);
        glview = (PanoPlayerSurfaceView) findViewById(R.id.glview);
        controlTop = (RelativeLayout) findViewById(R.id.control_top);//控制器顶
        ivplay = (ImageView) findViewById(R.id.iv_play);//播放按钮
        isFullScreen = (ImageView) findViewById(R.id.iv_is_fullscreen);//满屏
        sb = (SeekBar) findViewById(R.id.seekbar);//滚动条
        tvtimecu = (TextView) findViewById(R.id.tv_time_cu);//播放时间
        tvtimesum = (TextView) findViewById(R.id.tv_time_sum);//播放时间
        ivback = (RelativeLayout) findViewById(R.id.iv_back);//返回
        isvr = (TextView) findViewById(R.id.iv_is_vr);
        panoplayer_renderer = new PanoPlayer(glview,context);
        panoplayer_renderer.setListener(this);
        panoplayer_renderer.setVideoPluginListener(this);
        panoplayer_renderer.setViewMode(ViewMode.VIEWMODE_VR);
        glview.setRenderer(panoplayer_renderer);
        findViewById(R.id.player_movie_sv).scrollTo(0,0);

    }
    private void intPlayDate() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .imageScaleType(ImageScaleType.NONE).cacheInMemory()
                .cacheOnDisc().build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                // .writeDebugLogs()
                .tasksProcessingOrder(QueueProcessingType.FIFO).build();
        ImageLoader.getInstance().init(config);
    }
    private void initdata() {
         //设置电影模式 vr:0   360 1   普通 2
        setMevioStype();
         //横向
        myAdapter = new PlaydateAdapter(context,pmb.getBusiness().getSimilarity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        mrecyclerview.setLayoutManager(gridLayoutManager);
        mrecyclerview.setAdapter(myAdapter);
        myAdapter.setOnClickItemList(new PlaydateAdapter.OnRecyclerViewClickListen() {
            @Override
            public void OnclickItem(int position) {
                Intent ie  = new Intent(VrPlayMediaActivity.this,VrPlayMediaActivity.class);
                ie.putExtra("video",pmb.getBusiness().getSimilarity().get(position).getVideoCode());
                startActivity(ie);
            }
        });
        //初始化评价
        PlayDetailCommentFragment playdetailfragment = new PlayDetailCommentFragment();
        playdetailfragment.setVideoCode(videoCode);
        playdetailfragment.setScore(pmb.getBusiness().getGrad());
        getFragmentManager().beginTransaction().replace(R.id.playdata_fragment,playdetailfragment ).commit();

    }

    private void setMevioStype() {
        switch (stype){
            case 0:
                panoplayer_renderer.setViewMode(ViewMode.VIEWMODE_VR);
                panoplayer_renderer.setGyroEnable(true);
                isvr.setText("VR");
                break;
            case 1:
                panoplayer_renderer.setGyroEnable(true);
                panoplayer_renderer.setViewMode(ViewMode.VIEWMODE_DEF);
                isvr.setText("360°");
                break;
            case 2:
                panoplayer_renderer.setViewMode(ViewMode.VIEWMODE_PLANE);
                panoplayer_renderer.setGyroEnable(false);
                isvr.setText("3D");
                isvr.setClickable(false);
                break;
            case 3:
                panoplayer_renderer.setViewMode(ViewMode.VIEWMODE_PLANE);
                panoplayer_renderer.setGyroEnable(false);
                isvr.setText("2D");
                isvr.setClickable(false);
                break;
        }
    }

    private void playVideo() {
        // TODO Auto-generated method stub
        isBeginPlaylive = true;
        PanoPlayerUrl panoplayerurl = new PanoPlayerUrl();
        panoplayerurl
                .SetVideoUrlImage(
                        url,
                        "");
        panoplayer_renderer.Play(panoplayerurl);
    }
    //直播
    public void playLive(){
        isBeginPlaylive=true;
        PanoPlayerUrl panoplayerurl = new PanoPlayerUrl();
        //播放 方式一:  setXmlContent(String content);  content 必须是如下格式的XML 文本 才可以播放
        //播放方式二:  setXmlUrl(String url); url 地址 必须返回的是 如上格式 的XML 文本才可以播放
        //panoplayerurl.setXmlUrl("http://www.detu.com/live/xinlan/live-test.xml");
        String PanoPlayer_Template = "<DetuVr> "
                + "<settings init=\"pano1\" initmode=\"flat\" enablevr=\"false\"  title=\"\"/>"
                + 	"<scenes>"
                + 		"<scene name=\"pano1\"  title=\"\"    thumburl=\"\"   >"
                + 			"<image type=\"video\" url=\"%s\" rx=\"0\" ry=\"0\" rz=\"0\"/>"
                +            "<view hlookat=\"0\" vlookat=\"0\" fov=\"100\" vrfov=\"95\" vrz=\"0.5\" righteye=\"0.1\" fovmax=\"130\" defovmax=\"95\" gyroEnable=\"false\"/>"
                + 		"</scene>"
                + 	"</scenes>"
                + "</DetuVr>";
        String xmlstring = String.format(PanoPlayer_Template,"http://om6k32y5f.bkt.clouddn.com/5.%E6%9F%94%E6%9C%AF%E4%B8%89%E4%BA%BA%E8%A1%A8%E6%BC%94.m3u8");
        panoplayerurl.setXmlContent(xmlstring);
        panoplayer_renderer.Play(panoplayerurl);
    }
    @Override
    public void PluginVideOnPlayerError(PanoPlayer.PanoPlayerErrorStatus s, String errorstr) {
        Log.d("PanoPlay", "PluginVideOnPlayerError" + errorstr);
        UIUtils.showToast(errorstr);
        isBeginPlaylive = false;
        findViewById(R.id.progressbar).setVisibility(View.GONE);
    }
    @Override
    public void PluginVideoOnProgressChanged(final int curTime, int bufTime,
                                             final int maxTime) {
        if (!isSeekBarDragging) {
            sb.setMax(maxTime);
            sb.setSecondaryProgress(bufTime);
            sb.setProgress(curTime);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    tvtimecu.setText(UIUtils.formatDuring(curTime));
                    tvtimesum.setText(UIUtils.formatDuring(maxTime));
                }
            });
        }
    }

    @Override
    public void PluginVideoOnSeekFinished() {
        Log.d("PanoPlay", "PluginVideoOnSeekFinished");
    }
    @Override
    public void PluginVideoOnStatusChanged(PanoPlayer.PanoVideoPluginStatus s) {
        playerStatus = s;
        switch (s) {
            case VIDEO_STATUS_PAUSE:
                ivplay.post(new Runnable() {
                    public void run() {
                        ivplay.setImageResource(R.drawable.video_play);
                    }
                });
                Log.d("PanoPlay", "PluginVideoOnStatusChanged to pause");
                break;
            case VIDEO_STATUS_STOP:
                ivplay.post(new Runnable() {
                    public void run() {
                        ivplay.setImageResource(R.drawable.video_play);
                    }
                });
                Log.d("PanoPlay", "PluginVideoOnStatusChanged to stop");
                sb.setProgress(0);
                break;
            case VIDEO_STATUS_PLAYING:
                ivplay.post(new Runnable() {
                    public void run() {
                        ivplay.setImageResource(R.drawable.video_pause);
                    }
                });
                myHandler.removeMessages(HIDE_CONTROL_BAR);
                myHandler.sendEmptyMessageDelayed(HIDE_CONTROL_BAR,HIDE_TIME);
                Log.d("PanoPlay", "PluginVideoOnStatusChanged to play");
                break;
            case VIDEO_STATUS_FINISH://视频播放完成
                Log.d("PanoPlay", "PluginVideoOnStatusChanged to FINISH");
                isHide = false;
                isBeginPlaylive = false;
                minclude_pause.setVisibility(View.VISIBLE);
                findViewById(R.id.control_top).setVisibility(View.VISIBLE);
                findViewById(R.id.control_bottom).setVisibility(View.VISIBLE);
                ivplay.setImageResource(R.drawable.video_play);
                break;
            case VIDEO_STATUS_BUFFER_EMPTY:
                Log.d("PanoPlay", "PluginVideoOnStatusChanged to BUFFER_EMPTY");
                break;
            default:
                Log.d("PanoPlay", "PluginVideoOnStatusChanged to UNPREPARED;");
                break;
        }
    }
    @Override
    public void PanoPlayOnEnter(PanoramaData arg0) {
        Log.d("PanoPlay", "PanoPlayOnEnter");
    }
    @Override
    public void PanoPlayOnError(PanoPlayer.PanoPlayerErrorCode e) {
        Log.d("PanoPlay", "PanoPlayOnError" + e);
    }
    @Override
    public void PanoPlayOnLeave(PanoramaData arg0) {
        Log.d("PanoPlay", "PanoPlayOnLeave");
    }
    //加载完成
    @Override
    public void PanoPlayOnLoaded() {
        Log.d("PanoPlay", "PanoPlayOnLoaded");
        ivplay.setImageResource(R.drawable.video_pause);
        findViewById(R.id.progressbar).setVisibility(View.GONE);
        setMevioStype();
        myHandler.sendEmptyMessageDelayed(HIDE_CONTROL_BAR,HIDE_TIME);
    }
    @Override
    public void PanoPlayOnLoading() {
    }
    @Override
    public void PluginVideoOnInit() {
        Plugin plugin = panoplayer_renderer.getCurPlugin();
        if (plugin != null && plugin instanceof VideoPlugin) {
            videoplugin = (VideoPlugin) plugin;
            videoplugin.setLogLevel(IjkMediaPlayer.IJK_LOG_DEFAULT);
        }
    }
     //全屏
    public void setupFullScreen(){
        //设置窗口模式
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //获取屏幕尺寸
        WindowManager manager = this.getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        //设置Video布局尺寸
        mVideoLayout.getLayoutParams().width = metrics.widthPixels;
        mVideoLayout.getLayoutParams().height = metrics.heightPixels;
        //        设置为全屏拉伸
        //        glview.setVideoLayout(1, 0);
        //        mIvIsFullScreen.setImageResource(R.drawable.not_fullscreen);
        mIsFullScreen = true;
    }
   //no全屏
    private void setupUnFullScreen() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setAttributes(attrs);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        float width = getResources().getDisplayMetrics().heightPixels;
        float height = UIUtils.dp2px(250.f);
        mVideoLayout.getLayoutParams().width = (int) width;
        mVideoLayout.getLayoutParams().height = (int) height;
        mIsFullScreen = false;
    }
    //显示
    public void showBT(){
        isHide = false;
        findViewById(R.id.control_top).setVisibility(View.VISIBLE);
        findViewById(R.id.control_bottom).setVisibility(View.VISIBLE);
        myHandler.sendEmptyMessageDelayed(HIDE_CONTROL_BAR,HIDE_TIME);
    }
    //隐藏状态
    public void hideBT(){
        isHide = true;
        findViewById(R.id.control_top).setVisibility(View.GONE);
        findViewById(R.id.control_bottom).setVisibility(View.GONE);
    }
    @Override
    protected void onPause() {
        super.onPause();
        if(isBeginPlaylive){
            videoplugin.pause();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(playerStatus== PanoPlayer.PanoVideoPluginStatus.VIDEO_STATUS_PAUSE){
            LogUtils.i("---",playerStatus+"");
            videoplugin.pause();
            LogUtils.i("---",playerStatus+"");
        }
        LogUtils.i("---",playerStatus+"");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(videoplugin!=null){
            videoplugin.release();
        }

    }
    public void intNetdata(){
       showProgressDialog("正在获取数据");
        VrHttp.getInstance().requestForResult(RequestEnum.PLAYMEDIADETAIL, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {
                hideProgressDialog();
                pmb = new Gson().fromJson(response.toString(),playMovieBean.class);
                if(pmb.getStatus()!=null){
                    if("99".equals(pmb.getStatus())){
                        intData();
                    }
                }else{
                    UIUtils.showToast("数据获取失败！");
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
               hideProgressDialog();
            }
        },RequestEnum.PLAYMEDIADETAIL.addIntent(videoCode));
    }
    private void intData() {
            mplaydata_batv.setText(pmb.getBusiness().getTitle());
            mPlayerMovieName.setText(pmb.getBusiness().getTitle());
            mvrplay_hits.setText(pmb.getBusiness().getPlayCount()+"次播放");
            mPlayerMovieTime.setText(pmb.getBusiness().getTime());
            mPlayerMoviePc.setText(pmb.getBusiness().getLength());
            mPlayerMovieAddress.setText(pmb.getBusiness().getAddress());
            mPlayerMovieOwner.setText(pmb.getBusiness().getActor());
            mPlayerMovieExpend.setText(pmb.getBusiness().getVideoIntro());
            mvrplay_grade.setText(pmb.getBusiness().getScore());
            addTypeTextView();
            url = pmb.getBusiness().getUrl();
            if("y".equals(pmb.getBusiness().getWcanSee())){
                minclude_pause.setVisibility(View.VISIBLE);
            }else{
                showPlayVip();
                if("请先登录".equals(pmb.getBusiness().getFire())){

                }else{
                    mplaydetail_buyln_log.setVisibility(View.GONE);
                    fireNum =  pmb.getBusiness().getFire();
                }
            }
            if("y".equals(pmb.getBusiness().getKeepVideo())){
                mvideo_control_collect.setImageResource(R.drawable.nav_icon_collect_yellow);
                isCollect = true;
            }
            if(pmb.getBusiness().getProperty().equals("VR")){
                stype = 0 ;
            }else if(pmb.getBusiness().getProperty().equals("3D")){
                stype = 2;
            }else if(pmb.getBusiness().getProperty().equals("全景")){
                stype = 1 ;
            }else{
                stype = 3 ;
            }
            //赋值
            initdata();
    }
    private void addTypeTextView() {
        String[] sarr = UIUtils.getStringArry(pmb.getBusiness().getType());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        for(int i = 0 ;i<sarr.length;i++){
            TextView textView = new TextView(this);
            layoutParams.setMargins(5, 0, 5, 0);
            textView.setPadding(4,4,4,4);
            textView.setTextSize(12);
            textView.setBackgroundResource(R.color.vr_bg);
            textView.setTextColor(Color.parseColor("#999999"));
            textView.setText(sarr[i]);
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(layoutParams);
            mplayer_movie_li.addView(textView);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            setupFullScreen();
        }
    }
    @Override
    public void onBackPressed() {
        LogUtils.i("按返回键了");
        if (mIsFullScreen) {
            setupUnFullScreen();
        } else {
            setResult(RESULT_OK);
            finish();
        }
    }
    public void showProgressDialog(String msg){
        if(progressDialog==null){
            progressDialog = new Dialog(context, R.style.progress_dialog);
        }
        progressDialog.setContentView(R.layout.progressdialog);
        progressDialog.setCancelable(true);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView meg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
        meg.setText(msg);
        if(!progressDialog.isShowing()){
            progressDialog.show();
        }
    }
    public void hideProgressDialog(){
        if(progressDialog!=null){
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtils.i("换了");
        findViewById(R.id.player_movie_sv).scrollTo(0,0);
    }
    //显示购买
    public void showPlayVip(){
        mplaydetail_buyln.setVisibility(View.VISIBLE);
        miv_play_re.setClickable(false);
    }
    private void showSexSheet() {
        new ActionSheetDialog(context)
                .builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("确定购买此视频", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                buyVedio();
                            }
                        }).show();
    }
    public void buyVedio(){
        VrHttp.getInstance().requestForResult(RequestEnum.BUYVIP, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {
                SuccedInfo si = new Gson().fromJson(response.toString(),SuccedInfo.class);
                if(si.getStatus()!=null){
                    if("41".equals(si.getStatus())){
                        Toast.makeText(context,si.getErrmsg(),Toast.LENGTH_SHORT).show();
                    } else if("99".equals(si.getStatus())){
                        Toast.makeText(context,si.getMsg(),Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
            @Override
            public void onError(Call call, Exception e, int id) {
            }
        }, RequestEnum.BUYVIP.addIntent("2",Integer.parseInt(fireNum),"",videoCode,0));
    }
}
