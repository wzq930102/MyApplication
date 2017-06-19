package com.jtvr.fragment;


import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XScrollView;
import com.google.gson.Gson;
import com.jt.base.R;
import com.jtvr.adapter.HomeFragmentAdapter.MovieVRLiveRecyclerAdapter;
import com.jtvr.avtivity.HomeFragmentToActivity.SpecialActivity;
import com.jtvr.avtivity.MovieMoreActivity;
import com.jtvr.avtivity.MyFragmentToActivity.CollectionActivity;
import com.jtvr.avtivity.MyFragmentToActivity.DownloadActivity;
import com.jtvr.avtivity.MyFragmentToActivity.HistoryActivity;
import com.jtvr.avtivity.MyFragmentToActivity.SearchActivity;
import com.jtvr.avtivity.MyFragmentToActivity.SignActivity;
import com.jtvr.base.BaseFragment;
import com.jtvr.http.RequestEnum;
import com.jtvr.http.VrHttp;
import com.jtvr.jtInterface.MyClickListenner;
import com.jtvr.model.HomeBean;
import com.jtvr.utils.UIUtils;
import com.jtvr.view.GlideImageLoader;
import com.jtvr.vrplaymedia.VrPlayMediaActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 首页Fragment
 */
public class Home_Recommend_Fragment extends BaseFragment implements MyClickListenner, XScrollView.OnScrollListener {
    @BindView(R.id.home_recommend_banner)
    public Banner banner;
    @BindView(R.id.homeFragment_item2_recyclerView)
    RecyclerView homeFragmentRecyclerView;
    @BindView(R.id.fragment_home_recommend_cd)
    ImageView fragmentHomeRecommendCd;
    @BindView(R.id.homeFragment_scrollview)
    XScrollView homeFragmentScrollview;
    @BindView(R.id.homeFragment_parent)
    RelativeLayout homeFragmentParent;
    @BindView(R.id.homeFragment_item1_image)
    ImageView homeFragmentItem1Image;
    @BindView(R.id.homeFragment_item1_title1)
    TextView homeFragmentItem1Title1;
    @BindView(R.id.homeFragment_item1_image2)
    ImageView homeFragmentItem1Image2;
    @BindView(R.id.homeFragment_item1_title2)
    TextView homeFragmentItem1Title2;
    @BindView(R.id.homeFragment_item1_image3)
    ImageView homeFragmentItem1Image3;
    @BindView(R.id.homeFragment_item1_title3)
    TextView homeFragmentItem1Title3;
    @BindView(R.id.homeFragment_item1_text)
    TextView text1;
    @BindView(R.id.homeFragment_item2_text)
    TextView text2;
    @BindView(R.id.homeFragment_item3_text)
    TextView text3;
    @BindView(R.id.homeFragment_item4_text)
    TextView text4;
    @BindView(R.id.homeFragment_item0_text1)
    TextView title1;
    @BindView(R.id.homeFragment_item0_text2)
    TextView title2;
    @BindView(R.id.homeFragment_item0_text3)
    TextView title3;
    @BindView(R.id.homeFragment_item0_text4)
    TextView title4;
    @BindView(R.id.homeFragment_item0_text5)
    TextView title5;
    @BindView(R.id.homeFragment_refresh)
    XRefreshView xRefreshView;
    private MovieVRLiveRecyclerAdapter movieVRLiveRecyclerAdapter;
    private ImageView[] imageViews, hotImage;
    private TextView[] textViewTitle, textViewContent, title, hotText;
    private HomeBean homeBean;
    private int imageHeight;
    private List<String> images, titles;

    @Override
    public int setLayout() {
        return R.layout.fragment_home_recommend;
    }

    public void initView(View view) {
        ButterKnife.bind(this, view);
        images = new ArrayList();
        titles = new ArrayList();
        findById(view);
        setRecyclerView();
        //请求网络
        initNetdata();
        initListeners();
    }

    private void initNetdata() {
        images.clear();
        titles.clear();

        VrHttp.getInstance().requestForResult(RequestEnum.VRDATEL, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {
                Gson gson = new Gson();
                homeBean = gson.fromJson(response, HomeBean.class);
                //轮播图
                if ("首页banner".equals(homeBean.getBusiness().getList().get(0).getMenuName())) {
                    for (int i = 0; i < homeBean.getBusiness().getList().get(0).getVideoList().size(); i++) {
                        images.add(homeBean.getBusiness().getList().get(0).getVideoList().get(i).getImg());
                        titles.add(homeBean.getBusiness().getList().get(0).getVideoList().get(i).getName());
                    }
                }
                text1.setText(homeBean.getBusiness().getList().get(1).getMenuName());
                text2.setText(homeBean.getBusiness().getList().get(2).getMenuName());
                text3.setText(homeBean.getBusiness().getList().get(3).getMenuName());
                text4.setText(homeBean.getBusiness().getList().get(4).getMenuName());
                //设置专题
                for (int i = 0; i < homeBean.getBusiness().getMenujson().size(); i++) {

                    title[i].setText(homeBean.getBusiness().getMenujson().get(i).getFtname());
                }
                //热播推荐
                for (int i = 0; i < homeBean.getBusiness().getList().get(1).getVideoList().size(); i++) {
                    if (i < 3) {
                        if (i == 0) {
                            UIUtils.setImgeView(homeBean.getBusiness().getList().get(1).getVideoList().get(i).getImg2(), hotImage[i]);
                        } else {
                            UIUtils.setImgeView(homeBean.getBusiness().getList().get(1).getVideoList().get(i).getImg3(), hotImage[i]);
                        }

                        hotText[i].setText(homeBean.getBusiness().getList().get(1).getVideoList().get(i).getName());
                    }
                }
                //猜你喜欢
                for (int i = 0; i < homeBean.getBusiness().getList().get(3).getVideoList().size(); i++) {
                    if (i < 4) {
                        UIUtils.setImgeView(homeBean.getBusiness().getList().get(3).getVideoList().get(i).getImg3(),imageViews[i]);
//                        Picasso.with(getContext()).load(homeBean.getBusiness().getList().get(3).getVideoList().get(i).getImg3()).into();
                        textViewTitle[i].setText(homeBean.getBusiness().getList().get(3).getVideoList().get(i).getName());
                        textViewContent[i].setText(homeBean.getBusiness().getList().get(3).getVideoList().get(i).getVideoIntro());
                    }
                }
                setBannerStype(images, titles);
                xRefreshView.stopRefresh();

            }

            @Override
            public void onError(Call call, Exception e, int id) {

                xRefreshView.stopRefresh(false);

            }


        }, RequestEnum.VRDATEL.addIntent("f59206c0-9ed5-4c7b-9744-f27f60ed17b1"));

    }


    private void findById(View view) {
        View include1 = view.findViewById(R.id.homeFragment_include1);
        View include2 = view.findViewById(R.id.homeFragment_include2);
        View include3 = view.findViewById(R.id.homeFragment_include3);
        View include4 = view.findViewById(R.id.homeFragment_include4);
        ImageView image1 = (ImageView) include1.findViewById(R.id.item_video_iv);
        ImageView image2 = (ImageView) include2.findViewById(R.id.item_video_iv);
        ImageView image3 = (ImageView) include3.findViewById(R.id.item_video_iv);
        ImageView image4 = (ImageView) include4.findViewById(R.id.item_video_iv);
        imageViews = new ImageView[]{image1, image2, image3, image4};
        TextView title1 = (TextView) include1.findViewById(R.id.item_video_title);
        TextView title2 = (TextView) include2.findViewById(R.id.item_video_title);
        TextView title3 = (TextView) include3.findViewById(R.id.item_video_title);
        TextView title4 = (TextView) include4.findViewById(R.id.item_video_title);
        textViewTitle = new TextView[]{title1, title2, title3, title4};
        TextView content1 = (TextView) include1.findViewById(R.id.item_video_content);
        TextView content2 = (TextView) include2.findViewById(R.id.item_video_content);
        TextView content3 = (TextView) include3.findViewById(R.id.item_video_content);
        TextView content4 = (TextView) include4.findViewById(R.id.item_video_content);
        textViewContent = new TextView[]{content1, content2, content3, content4};
        title = new TextView[]{title1, title2, title3, title4, title5};
        hotImage = new ImageView[]{homeFragmentItem1Image, homeFragmentItem1Image2, homeFragmentItem1Image3};
        hotText = new TextView[]{homeFragmentItem1Title1, homeFragmentItem1Title2, homeFragmentItem1Title3};
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setLoadComplete(true);//关闭上拉刷新
//        xRefreshView.setPullRefreshEnable(false);//关闭下拉加载
        xRefreshView.setMoveForHorizontal(true);//处理横向移动与XRefreshView的冲突
        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                initNetdata();
            }

            @Override
            public void onLoadMore(boolean isSilence) {
            }

            @Override
            public void onHeaderMove(double offset, int offsetY) {
                super.onHeaderMove(offset, offsetY);
                if (offsetY > 0) {
                    homeFragmentParent.setVisibility(View.INVISIBLE);
                } else {
                    homeFragmentParent.setVisibility(View.VISIBLE);
                }
            }
        });

    }


    private void setRecyclerView() {
        List<Bitmap> image = new ArrayList<>();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.dlrb);
        for (int i = 0; i < 3; i++) {
            image.add(bitmap);
        }
        movieVRLiveRecyclerAdapter = new MovieVRLiveRecyclerAdapter(new ArrayList<Bitmap>(), this);
        homeFragmentRecyclerView.setAdapter(movieVRLiveRecyclerAdapter);
        movieVRLiveRecyclerAdapter.getData(image);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        homeFragmentRecyclerView.setLayoutManager(manager);
    }

    private void setBannerStype(List<String> images, List<String> titles) {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置指示器样式
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //banner设置方法全部调用完毕时最后调用
        banner.start();


    }

    private void initPopupWindow(View v) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_popup, null, false);
        TextView memory = (TextView) view.findViewById(R.id.popup_memory);
        TextView collect = (TextView) view.findViewById(R.id.popup_collect);
        TextView cache = (TextView) view.findViewById(R.id.popup_cache);
        TextView local = (TextView) view.findViewById(R.id.popup_local);
        //1.构造一个PopupWindow，参数依次是加载的View，宽高
        final PopupWindow popWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        popWindow.setAnimationStyle(R.anim.anim_pop);  //设置加载动画

        //这些为了点击非PopupWindow区域，PopupWindow会消失的，如果没有下面的
        //代码的话，你会发现，当你把PopupWindow显示出来了，无论你按多少次后退键
        //PopupWindow并不会关闭，而且退不出程序，加上下述代码可以解决这个问题
        popWindow.setTouchable(true);
        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));    //要为popWindow设置一个背景才有效


        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popWindow.showAsDropDown(v, 0, -10);

        //设置popupWindow里的按钮的事件
        memory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HistoryActivity.class));
            }
        });
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CollectionActivity.class));
            }
        });
        cache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), DownloadActivity.class));
            }
        });
        local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SignActivity.class));
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        banner.startAutoPlay();

    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {//隐藏
            banner.stopAutoPlay();
        } else {
            banner.startAutoPlay();
        }
    }

//    @OnClick({R.id.homeFragment_item0_text1, R.id.homeFragment_item0_text2, R.id.homeFragment_item0_text3
//            , R.id.homeFragment_item0_text4, R.id.homeFragment_item0_text5,
      @OnClick({R.id.homeFragment_item2_more
            , R.id.homeFragment_item3_more, R.id.homeFragment_item1_more, R.id.homeFragment_item1_image
            , R.id.homeFragment_item1_image2, R.id.homeFragment_item1_image3, R.id.homeFragment_include1
            , R.id.homeFragment_include2, R.id.homeFragment_include3, R.id.homeFragment_include4
            , R.id.homeFragment_item4_more, R.id.homeFragment_item4_download1, R.id.homeFragment_item4_layout1
            , R.id.homeFragment_item4_download2, R.id.homeFragment_item4_layout2, R.id.homeFragment_item4_download3
            , R.id.homeFragment_item4_layout3, R.id.homeFragment_item4_download4, R.id.homeFragment_item4_layout4
            , R.id.fragment_home_search})
    public void onClick(View view) {
        try {
            Intent intent = new Intent();
            Class c = null;
            switch (view.getId()) {
                case R.id.fragment_home_search:
                    c = SearchActivity.class;
                    break;
//                case R.id.homeFragment_item0_text1:
//                    UIUtils.showToast("热门影视");
//                    break;
//                case R.id.homeFragment_item0_text2:
//                    UIUtils.showToast("人气榜单");
//                    break;
//                case R.id.homeFragment_item0_text3:
//                    UIUtils.showToast("美女直播");
//                    break;
//                case R.id.homeFragment_item0_text4:
//                    c = SpecialActivity.class;
//                    break;
//                case R.id.homeFragment_item0_text5:
//                    UIUtils.showToast("VR游戏");
//                    break;
                case R.id.homeFragment_item2_more:
                    UIUtils.showToast("最新专题");
                    break;
                case R.id.homeFragment_item3_more:
                    c = MovieMoreActivity.class;
                    intent.putExtra("menuCode", homeBean.getBusiness().getList().get(3).getMenuCode());
                    intent.putExtra("title", "人气榜单");
                    break;
                case R.id.homeFragment_item1_more:
                    c = MovieMoreActivity.class;
                    intent.putExtra("menuCode", homeBean.getBusiness().getList().get(1).getMenuCode());
                    intent.putExtra("title", "热门推荐");
                    break;
                case R.id.homeFragment_item1_image:

                    c = VrPlayMediaActivity.class;
                    intent.putExtra("video", homeBean.getBusiness().getList().get(1).getVideoList().get(0).getVideoCode());

                    break;
                case R.id.homeFragment_item1_image2:

                    c = VrPlayMediaActivity.class;
                    intent.putExtra("video", homeBean.getBusiness().getList().get(1).getVideoList().get(1).getVideoCode());

                    break;
                case R.id.homeFragment_item1_image3:

                    c = VrPlayMediaActivity.class;
                    intent.putExtra("video", homeBean.getBusiness().getList().get(1).getVideoList().get(2).getVideoCode());

                    break;
                case R.id.homeFragment_include1:

                    c = VrPlayMediaActivity.class;
                    intent.putExtra("video", homeBean.getBusiness().getList().get(3).getVideoList().get(0).getVideoCode());

                    break;
                case R.id.homeFragment_include2:
                    c = VrPlayMediaActivity.class;
                    intent.putExtra("video", homeBean.getBusiness().getList().get(3).getVideoList().get(1).getVideoCode());

                    break;
                case R.id.homeFragment_include3:
                    c = VrPlayMediaActivity.class;
                    intent.putExtra("video", homeBean.getBusiness().getList().get(3).getVideoList().get(2).getVideoCode());

                    break;
                case R.id.homeFragment_include4:
                    c = VrPlayMediaActivity.class;
                    intent.putExtra("video", homeBean.getBusiness().getList().get(3).getVideoList().get(3).getVideoCode());

                    break;
                case R.id.homeFragment_item4_more:
                    UIUtils.showToast("最新应用更多");
                    break;
                case R.id.homeFragment_item4_download1:
                    UIUtils.showToast("下载1");
                    break;
                case R.id.homeFragment_item4_download2:
                    UIUtils.showToast("下载2");
                    break;
                case R.id.homeFragment_item4_download3:
                    UIUtils.showToast("下载3");
                    break;
                case R.id.homeFragment_item4_download4:
                    UIUtils.showToast("下载4");
                    break;
                case R.id.homeFragment_item4_layout1:
                    UIUtils.showToast("游戏1");
                    break;
                case R.id.homeFragment_item4_layout2:
                    UIUtils.showToast("游戏2");
                    break;
                case R.id.homeFragment_item4_layout3:
                    UIUtils.showToast("游戏3");
                    break;
                case R.id.homeFragment_item4_layout4:
                    UIUtils.showToast("游戏4");
                    break;
            }
            if (c != null) {
                ComponentName componentName = new ComponentName(getContext(), c);
                intent.setComponent(componentName);
                startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            UIUtils.showToast("数据异常，请稍后尝试");
        }


    }

    //RecyclerView点击监听事件
    @Override
    public void MyRecyclerViewClickListenner(View view, int position) {

    }

    /**
     * 获取顶部图片高度后，设置滚动监听
     */
    private void initListeners() {

        ViewTreeObserver vto = banner.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                banner.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                imageHeight = banner.getHeight();

                homeFragmentScrollview.setOnScrollListener(Home_Recommend_Fragment.this);
            }
        });
        fragmentHomeRecommendCd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindow(v);
            }
        });
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getContext(), VrPlayMediaActivity.class);
                intent.putExtra("video", homeBean.getBusiness().getList().get(0).getVideoList().get(position).getVideoCode());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onScrollStateChanged(ScrollView view, int scrollState, boolean arriveBottom) {

    }

    @Override
    public void onScroll(int x, int y, int oldl, int oldt) {
        if (y <= 0) {   //设置标题的背景颜色
            homeFragmentParent.setBackgroundColor(Color.argb(0, 244, 67, 54));
        } else if (y > 0 && y <= imageHeight) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / imageHeight;
            float alpha = (255 * scale);
//          homeFragmentParent.setBackgroundColor(Color.argb((int) alpha, 255,255,255));
            homeFragmentParent.setBackgroundColor(Color.argb((int) alpha, 244, 67, 54));
        } else {    //滑动到banner下面设置普通颜色
            homeFragmentParent.setBackgroundColor(Color.argb(255, 244, 67, 54));
        }
    }
}
