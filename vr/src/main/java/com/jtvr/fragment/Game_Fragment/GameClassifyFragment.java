package com.jtvr.fragment.Game_Fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jt.base.R;
import com.jtvr.adapter.GameFragmentAdapter.GameClassifyRecyclerAdapter;
import com.jtvr.base.BaseFragment;
import com.jtvr.utils.DividerItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 */
public class GameClassifyFragment extends BaseFragment {



    @BindView(R.id.game_classify_recyclerView)
    RecyclerView recyclerView;

    @Override
    public int setLayout() {
        return R.layout.fragment_game_classify;
    }

    private GameClassifyRecyclerAdapter recyclerAdapter;

    @Override
    public void initView(View view) {
        ButterKnife.bind(this, view);
        recyclerAdapter = new GameClassifyRecyclerAdapter();
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(recyclerAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}
