package com.jtvr.fragment;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.google.gson.Gson;
import com.jt.base.R;
import com.jtvr.adapter.MyPagerAdapter;
import com.jtvr.avtivity.MyFragmentToActivity.CollectionActivity;
import com.jtvr.avtivity.MyFragmentToActivity.DownloadActivity;
import com.jtvr.avtivity.MyFragmentToActivity.HistoryActivity;
import com.jtvr.avtivity.MyFragmentToActivity.SearchActivity;
import com.jtvr.avtivity.MyFragmentToActivity.SignActivity;
import com.jtvr.base.BaseFragment;
import com.jtvr.fragment.Movie_Fragment.Movie_include.MovieHotFragment;
import com.jtvr.fragment.Movie_Fragment.Movie_include.MovieIncludeFragment;
import com.jtvr.http.RequestEnum;
import com.jtvr.http.VrHttp;
import com.jtvr.model.MovieType;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 影视Fragment
 */
public class MovieFragment extends BaseFragment {
    private String[] mTitles;

    @BindView(R.id.movie_fragment_tabLayout)
    SlidingTabLayout movieFragmentTabLayout;
    @BindView(R.id.movie_fragment_viewPager)
    ViewPager movieFragmentViewPager;
    @BindView(R.id.movieFragment_select)
    ImageView movieFragmentSelect;

    private MyPagerAdapter mAdapter;
    private ArrayList<Fragment> mFragments;
    private MovieHotFragment movieHotFragment;

    private MovieType movieType;

    @Override
    public int setLayout() {
        return R.layout.fragment_movie;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this, view);

        initHttp();
        initListener();
    }

    private void initListener() {
        movieFragmentSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindow(v);
            }
        });
    }


    private void initHttp() {
        VrHttp.getInstance().requestForResult(RequestEnum.MOVIETYPE, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {

                movieType = new Gson().fromJson(response, MovieType.class);
                mTitles = new String[movieType.getBusiness().getList().size()];
                for (int i = 0; i < movieType.getBusiness().getList().size(); i++) {
                    mTitles[i] = movieType.getBusiness().getList().get(i).getName();
                }
                initData();
            }

            @Override
            public void onError(Call call, Exception e, int id) {

            }
        }, RequestEnum.MOVIETYPE.addIntent(""));

    }

    private void initData() {
        mFragments = new ArrayList<>();
        movieHotFragment = new MovieHotFragment();
        mFragments.add(movieHotFragment);
        for (int i = 1; i < mTitles.length; i++) {
            mFragments.add(MovieIncludeFragment.
                    getInstance(movieType.getBusiness().getList().get(i).getCode(), movieType.getBusiness().getList().get(i).getFirstCode()));
        }
        mAdapter = new MyPagerAdapter(getChildFragmentManager(), mTitles, mFragments);
        movieFragmentViewPager.setAdapter(mAdapter);
        movieFragmentViewPager.setOffscreenPageLimit(mTitles.length);
        movieFragmentTabLayout.setViewPager(movieFragmentViewPager, mTitles);

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


    @OnClick(R.id.movieFragment_search)
    public void onClick() {
        startActivity(new Intent(getContext(), SearchActivity.class));
    }
}
