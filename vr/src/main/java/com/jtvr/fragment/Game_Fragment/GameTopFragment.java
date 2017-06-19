package com.jtvr.fragment.Game_Fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.flyco.tablayout.SegmentTabLayout;
import com.jt.base.R;
import com.jtvr.adapter.GameFragmentAdapter.GameRecyclerAdapter;
import com.jtvr.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 */
public class GameTopFragment extends BaseFragment {
    @BindView(R.id.game_top_SegmentTabLayout)
    SegmentTabLayout tabLayout;
    @BindView(R.id.game_top_RecyclerView)
    RecyclerView recyclerView;


    private GameRecyclerAdapter gameRecyclerAdapter;
    private String[] mTitles = {"热度", "下载量", "上线时间"};
    @Override
    public int setLayout() {
        return R.layout.fragment_game_top;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this, view);
        tabLayout.setTabData(mTitles);
        gameRecyclerAdapter = new GameRecyclerAdapter();
        recyclerView.setAdapter(gameRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
