package com.jtvr.fragment.Movie_Fragment.Movie_include;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XScrollView;
import com.google.gson.Gson;
import com.jt.base.R;
import com.jtvr.adapter.MovieDetailAdapter.MovieHotRecyclerAdapter;
import com.jtvr.avtivity.MovieMoreActivity;
import com.jtvr.base.BaseFragment;
import com.jtvr.http.RequestEnum;
import com.jtvr.http.VrHttp;
import com.jtvr.jtInterface.MovieHotClickListener;
import com.jtvr.model.MovieHotBean;
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
import okhttp3.Call;

public class MovieHotFragment extends BaseFragment implements MovieHotClickListener {

    @BindView(R.id.movie_fragment_hot_Banner)
    Banner banner;
    @BindView(R.id.movie_fragment_hot_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.movieHotFragment_refresh)
    XRefreshView xRefreshView;
    @BindView(R.id.moviehot_parent)
    XScrollView relativeLayout;
    private MovieHotRecyclerAdapter movieHotRecyclerAdapter;
    private MovieHotBean movieHotBean;
    private List<MovieHotBean.BusinessBean.ListBean> data;

    @Override
    public int setLayout() {
        return R.layout.fragment_moviehot;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this, view);
        data = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setLoadComplete(true);//关闭上拉刷新
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setMoveForHorizontal(true);//处理横向移动与XRefreshView的冲突
        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {

                inItHttp();
            }

            @Override
            public void onLoadMore(boolean isSilence) {
//                xRefreshView.stopLoadMore(false);
//                xRefreshView.stopRefresh();
            }

        });

        inItHttp();
        initRecyclerView();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getContext(), VrPlayMediaActivity.class);
                intent.putExtra("video", movieHotBean.getBusiness().getList().get(0).getVideoList().get(position).getVideoCode());
                startActivity(intent);
            }
        });
    }

    private void inItHttp() {
        data.clear();
        VrHttp.getInstance().requestForResult(RequestEnum.VRDATEL, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {
                List<String> images = new ArrayList();
                List<String> titles = new ArrayList();
                data = new ArrayList<MovieHotBean.BusinessBean.ListBean>();
                movieHotBean = new Gson().fromJson(response, MovieHotBean.class);
                for (int i = 0; i < movieHotBean.getBusiness().getList().get(0).getVideoList().size(); i++) {
                    images.add(movieHotBean.getBusiness().getList().get(0).getVideoList().get(i).getImg());
                    titles.add(movieHotBean.getBusiness().getList().get(0).getVideoList().get(i).getName());
                }

                for (int i = 1; i < movieHotBean.getBusiness().getList().size(); i++) {
                    data.add(movieHotBean.getBusiness().getList().get(i));
                }

                movieHotRecyclerAdapter.getData(data);
                setBannerStype(images, titles);
//                if (movieHotBean.getBusiness().getList().get(0).getVideoList().size() > 0 && movieHotBean.getBusiness().getList().get(1).getVideoList().size() > 0) {
//                    relativeLayout.setVisibility(View.VISIBLE);
//
//                } else {
//                    relativeLayout.setVisibility(View.INVISIBLE);
//                    xRefreshView.stopRefresh();
//                }
                xRefreshView.stopRefresh(true);

            }

            @Override
            public void onError(Call call, Exception e, int id) {
                xRefreshView.stopRefresh(false);
            }
        }, RequestEnum.VRDATEL.addIntent("f59206c0-9ed5-4c7b-9744-f27f60ed17b2"));
    }


    private void initRecyclerView() {
        movieHotRecyclerAdapter = new MovieHotRecyclerAdapter(data, this);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(movieHotRecyclerAdapter);
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
    public void MyMovieHotClickListener(View view, int position, int listPosition) {

        if (listPosition == 10) {
            Intent intent = new Intent(getContext(), MovieMoreActivity.class);
            intent.putExtra("menuCode", movieHotBean.getBusiness().getList().get(position + 1).getMenuCode());
            intent.putExtra("title", movieHotBean.getBusiness().getList().get(position + 1).getMenuName());
            startActivity(intent);
        } else {
            try {
                Intent intent = new Intent(getContext(), VrPlayMediaActivity.class);
                intent.putExtra("video", movieHotBean.getBusiness().getList().get(position + 1).getVideoList().get(listPosition).getVideoCode());
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                UIUtils.showToast("数据异常，请稍后尝试");
            }

        }
    }
}
