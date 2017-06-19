package com.jtvr.avtivity.MyFragmentToActivity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.jt.base.R;
import com.jtvr.adapter.MyPagerAdapter;
import com.jtvr.base.BaseActivity;
import com.jtvr.fragment.MessageActivityFragment.CommentFragment;
import com.jtvr.fragment.MessageActivityFragment.MessageFragment;
import com.jtvr.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageActivity extends BaseActivity {
    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.title_register)
    TextView titleRegister;
    @BindView(R.id.message_tabLayout)
    SlidingTabLayout messageTabLayout;
    @BindView(R.id.message_viewPager)
    ViewPager messageViewPager;

    private MyPagerAdapter myPagerAdapter;
    private String[] mTitles={"系统消息","评论记录"};
    private MessageFragment messageFragment;
    private CommentFragment commentFragment;
    private List<Fragment> fragmentList;
    @Override
    public void initView() {
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        titleTitle.setText("我的消息");
        titleRegister.setText("全部已读");
        initData();
    }

    private void initData() {
        fragmentList = new ArrayList<>();
        messageFragment = new MessageFragment();
        commentFragment = new CommentFragment();
        fragmentList.add(messageFragment);
        fragmentList.add(commentFragment);
        myPagerAdapter=new MyPagerAdapter(getSupportFragmentManager(),mTitles,fragmentList);
        messageViewPager.setAdapter(myPagerAdapter);
        messageViewPager.setOffscreenPageLimit(mTitles.length);
        messageTabLayout.setViewPager(messageViewPager, mTitles);
    }

    @Override
    public void initClick() {

    }


    @OnClick({R.id.title_back,R.id.title_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_register:
                UIUtils.showToast("暂无此功能");
                break;
        }
    }
}
