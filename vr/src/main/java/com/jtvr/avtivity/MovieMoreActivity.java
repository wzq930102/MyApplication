package com.jtvr.avtivity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.google.gson.Gson;
import com.jt.base.R;
import com.jtvr.adapter.MovieMoreAdapter;
import com.jtvr.base.BaseActivity;
import com.jtvr.http.RequestEnum;
import com.jtvr.http.VrHttp;
import com.jtvr.jtInterface.MyClickListenner;
import com.jtvr.model.MovieMoreBean;
import com.jtvr.utils.DividerItemDecoration;
import com.jtvr.utils.UIUtils;
import com.jtvr.vrplaymedia.VrPlayMediaActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class MovieMoreActivity extends BaseActivity implements MyClickListenner {
    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.title_register)
    TextView titleRegister;
    @BindView(R.id.more_recyclerView)
    RecyclerView moreRecyclerView;
    @BindView(R.id.more_refreshView)
    XRefreshView xRefreshView;
    private String menuCode;
    private String title;
    private int pageNo = 1;
    private int pageSize = 10;
    private MovieMoreAdapter movieMoreAdapter;
    private List<MovieMoreBean.BusinessBean.ListBean> list;

    @Override
    public void initView() {
        setContentView(R.layout.activity_movie_more);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        menuCode = intent.getStringExtra("menuCode");
        title = intent.getStringExtra("title");
        titleTitle.setText(title);
        titleRegister.setVisibility(View.INVISIBLE);
        initRecyclerView();
        initHttp();

    }

    private void initRecyclerView() {
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setPullRefreshEnable(false);//关闭下拉加载
        moreRecyclerView.setHasFixedSize(true);
        list = new ArrayList<>();
        movieMoreAdapter = new MovieMoreAdapter(this, list, this);
        moreRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        moreRecyclerView.setAdapter(movieMoreAdapter);
        moreRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }

    private void initHttp() {
        VrHttp.getInstance().requestForResult(RequestEnum.MOVIEMORE, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {
                Gson gson = new Gson();
                MovieMoreBean movieMoreBean = gson.fromJson(response, MovieMoreBean.class);
                if ("99".equals(movieMoreBean.getStatus())) {
                    list.addAll(movieMoreBean.getBusiness().getList());
                    movieMoreAdapter.setData(list);
                    if (movieMoreBean.getBusiness().getList().size() > 0) {
                        xRefreshView.stopLoadMore(true);//加载成功
                    } else {
                        xRefreshView.stopLoadMore(true);//加载失败
                        UIUtils.showToast("已没有更多");
                        pageNo--;
                    }
                }

            }

            @Override
            public void onError(Call call, Exception e, int id) {

            }
        }, RequestEnum.MOVIEMORE.addIntent(menuCode, pageNo, pageSize));
    }

    @Override
    public void initClick() {
        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                pageNo++;
                initHttp();
            }
        });
    }


    @OnClick({R.id.title_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
        }
    }

    @Override
    public void MyRecyclerViewClickListenner(View view, int position) {
        Intent intent = new Intent(this, VrPlayMediaActivity.class);
        intent.putExtra("video", list.get(position).getVideoCode());
        startActivity(intent);
    }
}
