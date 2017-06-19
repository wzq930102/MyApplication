package com.jtvr.avtivity.HomeFragmentToActivity;

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
import android.widget.Toast;

import com.jt.base.R;
import com.jtvr.adapter.SpecialAvtivityAdapter;
import com.jtvr.base.BaseActivity;
import com.jtvr.jtInterface.MyClickListenner;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpecialActivity extends BaseActivity implements MyClickListenner {
    @BindView(R.id.movieFragment_select)
    ImageView movieFragmentSelect;
    @BindView(R.id.special_RecyclerView)
    RecyclerView specialRecyclerView;

    private SpecialAvtivityAdapter adapter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_special);
        ButterKnife.bind(this);
        adapter = new SpecialAvtivityAdapter(this);
        specialRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        specialRecyclerView.setAdapter(adapter);
    }

    @Override
    public void initClick() {
        movieFragmentSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindow(v);
            }
        });
    }
    private void initPopupWindow(View v) {
        View view = LayoutInflater.from(SpecialActivity.this).inflate(R.layout.item_popup, null, false);
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
                Toast.makeText(SpecialActivity.this, "你点击了观看历史", Toast.LENGTH_SHORT).show();
                popWindow.dismiss();
            }
        });
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SpecialActivity.this, "你点击了我的收藏", Toast.LENGTH_SHORT).show();
                popWindow.dismiss();
            }
        });
        cache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SpecialActivity.this, "你点击了我的缓存", Toast.LENGTH_SHORT).show();
                popWindow.dismiss();
            }
        });
        local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SpecialActivity.this, "你点击了本地视频", Toast.LENGTH_SHORT).show();
                popWindow.dismiss();
            }
        });
    }
    @Override
    public void MyRecyclerViewClickListenner(View view, int position) {
//       startActivity(new Intent(this,SpecialDetailActivity.class));
    }
}
