package com.jtvr.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jt.base.R;
import com.jtvr.adapter.HomeFragmentAdapter.MovieVRLiveRecyclerAdapter;
import com.jtvr.adapter.LiveFragmentAdapter.ScrollAdapter;
import com.jtvr.avtivity.MyFragmentToActivity.CollectionActivity;
import com.jtvr.avtivity.MyFragmentToActivity.DownloadActivity;
import com.jtvr.avtivity.MyFragmentToActivity.HistoryActivity;
import com.jtvr.avtivity.MyFragmentToActivity.SignActivity;
import com.jtvr.base.BaseFragment;
import com.jtvr.jtInterface.MyClickListenner;
import com.jtvr.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 直播Fragment
 */
public class LiveFragment extends BaseFragment implements MyClickListenner{


    private MovieVRLiveRecyclerAdapter movieVRLiveRecyclerAdapter;
    private ScrollAdapter scrollAdapter;
    @BindView(R.id.live_recyclerView_horizontal)
    RecyclerView liveRecyclerViewHorizontal;
    @BindView(R.id.live_recyclerView_scroll)
    RecyclerView liveRecyclerViewScroll;
    @BindView(R.id.movieFragment_select)
    ImageView movieFragmentSearch;
    private List<Bitmap> image;

    @Override
    public int setLayout() {
        return R.layout.fragment_live;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this, view);


        view.findViewById(R.id.zhibo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIUtils.showToast("敬请期待");
            }
        });
        image = new ArrayList<>();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.dlrb);
        for (int i = 0; i < 5; i++) {
            image.add(bitmap);
        }
        movieVRLiveRecyclerAdapter = new MovieVRLiveRecyclerAdapter(image,this);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        liveRecyclerViewHorizontal.setLayoutManager(manager);
        liveRecyclerViewHorizontal.setAdapter(movieVRLiveRecyclerAdapter);


        scrollAdapter = new ScrollAdapter(this);
        liveRecyclerViewScroll.setNestedScrollingEnabled(false);
        liveRecyclerViewScroll.setAdapter(scrollAdapter);
        liveRecyclerViewScroll.setLayoutManager(new LinearLayoutManager(getActivity()));

        movieFragmentSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindow(v);
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
    @Override
    public void MyRecyclerViewClickListenner(View view, int position) {
        UIUtils.showToast("敬请期待");
    }
}
