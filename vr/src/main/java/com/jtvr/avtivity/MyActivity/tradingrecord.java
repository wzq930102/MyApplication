package com.jtvr.avtivity.MyActivity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jt.base.R;
import com.jtvr.base.BaseActivity;
import com.jtvr.fragment.My_Fragment.tradingrecordFragment1;
import com.jtvr.fragment.My_Fragment.tradingrecordFragment2;

/**
 * Created by jt on 2017/4/7.
 */
public class tradingrecord extends BaseActivity {
    TextView mTitleTitle;
    ImageView mTitleBack;
    TextView mTitleRegister;
    TabLayout tabLayout;
    ViewPager viewPager;
    private PagerAdapter adapter;
    private tradingrecordFragment1 tab1;
    private tradingrecordFragment2 tab2;

    @Override
    public void initView() {
        setContentView(R.layout.activity_tradingrecord);
        mTitleTitle = (TextView) findViewById(R.id.title_title);
        mTitleBack = (ImageView) findViewById(R.id.title_back);
        mTitleRegister = (TextView) findViewById(R.id.title_register);
        tabLayout = (TabLayout) findViewById(R.id.tradingrecord_tabLayout);
        viewPager = (ViewPager) findViewById(R.id.tradingrecord_viewPager);
        mTitleTitle.setText("交易记录");
        mTitleRegister.setVisibility(View.GONE);
        tabLayout.addTab(tabLayout.newTab().setText("充值记录"));
        tabLayout.addTab(tabLayout.newTab().setText("消费记录"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tab1 = new tradingrecordFragment1();
        tab2 = new tradingrecordFragment2();
        adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
    @Override
    public void initClick() {
        mTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    public class PagerAdapter extends FragmentStatePagerAdapter {
        int nNumOfTabs;
        public PagerAdapter(FragmentManager fm, int nNumOfTabs)
        {
            super(fm);
            this.nNumOfTabs=nNumOfTabs;
        }
        @Override
        public Fragment getItem(int position) {
            switch(position)
            {
                case 0:
                    return tab1;
                case 1:
                    return tab2;
            }
            return null;
        }

        @Override
        public int getCount() {
            return nNumOfTabs;
        }
    }

}
