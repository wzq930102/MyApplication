package com.jtvr.fragment.Game_Fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jt.base.R;
import com.jtvr.adapter.GameFragmentAdapter.GameRecyclerAdapter;
import com.jtvr.base.BaseFragment;
import com.jtvr.view.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 */
public class GameRecommendFragment extends BaseFragment {
    ArrayList images, titles;
    @BindView(R.id.game_recommend_banner)
    public Banner banner;
    @BindView(R.id.game_recommend_RecyclerView)
    RecyclerView recyclerView;
    private GameRecyclerAdapter gameRecyclerAdapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_game_recommend;
    }


    @Override
    public void initView(View view) {
        ButterKnife.bind(this, view);
        TextView textView = (TextView) view.findViewById(R.id.game_fragment_content1).findViewById(R.id.vr_tv_text);
        textView.setText("游戏推荐");
        TextView textView2 = (TextView) view.findViewById(R.id.game_fragment_content2).findViewById(R.id.vr_tv_text);
        textView2.setText("新游精选");
        initData();
        setBannerStype();
        gameRecyclerAdapter = new GameRecyclerAdapter();
        recyclerView.setAdapter(gameRecyclerAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    private void initData() {
        images = new ArrayList();
        images.add("http://img2.imgtn.bdimg.com/it/u=2929841458,2540810060&fm=21&gp=0.jpg");
        images.add("http://attach.games.hehagame.com/uploads/110818/63_095042_1.jpg");
        images.add("http://pic.58pic.com/58pic/15/13/86/08Y58PICmnf_1024.jpg");
        images.add("http://pic.58pic.com/58pic/17/25/04/66p58PICVqF_1024.jpg");
        titles = new ArrayList();
        titles.add("涂鸦骑手");
        titles.add("爆裂赛车");
        titles.add("仙境弹珠");
        titles.add("Bullet Sky");
    }

    private void setBannerStype() {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置指示器样式
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

}
