package com.jtvr.avtivity.HomeFragmentToActivity;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jt.base.R;
import com.jtvr.adapter.SpecialDetailActivityAdapter;
import com.jtvr.base.BaseActivity;
import com.jtvr.jtInterface.MyClickListenner;
import com.jtvr.model.MovieBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SpecialDetailActivity extends BaseActivity implements MyClickListenner {
    @BindView(R.id.detail_RecyclerView)
    RecyclerView detailRecyclerView;
    private SpecialDetailActivityAdapter adapter;
    private List<MovieBean> list;

    @Override
    public void initView() {
        setContentView(R.layout.activity_special_detail);
        ButterKnife.bind(this);

    }

    @Override
    public void initClick() {

    }

    @Override
    public void MyRecyclerViewClickListenner(View view, int position) {

    }
}
