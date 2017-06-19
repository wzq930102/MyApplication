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
import com.jt.base.R;
import com.jtvr.adapter.GameFragmentAdapter.GameViewPagerAdapter;
import com.jtvr.avtivity.MyFragmentToActivity.CollectionActivity;
import com.jtvr.avtivity.MyFragmentToActivity.DownloadActivity;
import com.jtvr.avtivity.MyFragmentToActivity.HistoryActivity;
import com.jtvr.avtivity.MyFragmentToActivity.SignActivity;
import com.jtvr.base.BaseFragment;
import com.jtvr.fragment.Game_Fragment.GameClassifyFragment;
import com.jtvr.fragment.Game_Fragment.GameRecommendFragment;
import com.jtvr.fragment.Game_Fragment.GameTopFragment;
import com.jtvr.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 游戏Fragment
 */
public class GameFragment extends BaseFragment {

    @BindView(R.id.game_fragment_tabLayout)
    SlidingTabLayout gameFragmentTabLayout;
    @BindView(R.id.game_viewPager)
    ViewPager viewPager;
    @BindView(R.id.movieFragment_select)
    ImageView movieFragmentSearch;

    private GameViewPagerAdapter gameViewPagerAdapter;
    private List<Fragment> fragmentList;
    private GameRecommendFragment gameRecommendFragment;
    private GameClassifyFragment gameClassifyFragment;
    private GameTopFragment gameTopFragment;
    private String[] mTitles = {"推荐", "分类", "排行"};

    @Override
    public int setLayout() {
        return R.layout.fragment_game;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this, view);
        initData();
        gameViewPagerAdapter = new GameViewPagerAdapter(getChildFragmentManager(), fragmentList, mTitles);
        viewPager.setAdapter(gameViewPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        gameFragmentTabLayout.setViewPager(viewPager, mTitles);
        movieFragmentSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindow(v);
            }
        });
        view.findViewById(R.id.youxi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIUtils.showToast("敬请期待");
            }
        });
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

    private void initData() {
        fragmentList = new ArrayList<>();
        gameRecommendFragment = new GameRecommendFragment();
        gameClassifyFragment = new GameClassifyFragment();
        gameTopFragment = new GameTopFragment();
        fragmentList.add(gameRecommendFragment);
        fragmentList.add(gameClassifyFragment);
        fragmentList.add(gameTopFragment);
    }


//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (gameRecommendFragment != null) {
//            if (hidden) {
//                gameRecommendFragment.banner.stopAutoPlay();
//            } else {
//                gameRecommendFragment.banner.startAutoPlay();
//            }
//        }
//    }

}
