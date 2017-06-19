package com.jtvr.fragment.Movie_Fragment.Movie_include;


import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jt.base.R;
import com.jtvr.adapter.HomeFragmentAdapter.HomeRecommendAdapter;
import com.jtvr.base.BaseFragment;
import com.jtvr.jtInterface.MyClickListenner;
import com.jtvr.model.videoInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * MovieVIPFragment页面
 */
public class MovieVIPFragment extends BaseFragment implements MyClickListenner {
    @BindView(R.id.movieFragment_vip_RecyclerView)
    RecyclerView recyclerView;
    private HomeRecommendAdapter adapter;
    private List<videoInfo> data;

    @Override
    public int setLayout() {
        return R.layout.fragment_movie_vip;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this, view);
        initData();
//        adapter = new HomeRecommendAdapter(data, getContext(), this);
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
//        DividerGridItemDecoration dividerGridItemDecoration = new DividerGridItemDecoration(getContext());
//        dividerGridItemDecoration.setDivider(R.drawable.divider_bg);
//        recyclerView.addItemDecoration(dividerGridItemDecoration);
//        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            videoInfo vi = new videoInfo();
            vi.setIv("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489034789391&di=9d2a572585934da18aac0dac23757d9a&imgtype=0&src=http%3A%2F%2Fent.southcn.com%2F8%2Fimages%2Fattachement%2Fjpg%2Fsite4%2F20150721%2F13%2F17501126388833009329.jpg");
            vi.setContent("大闹天宫后四百年多年。");
            vi.setTitle("大圣归来");
            data.add(vi);
        }
    }

    @Override
    public void MyRecyclerViewClickListenner(View view, int position) {
    }
}
