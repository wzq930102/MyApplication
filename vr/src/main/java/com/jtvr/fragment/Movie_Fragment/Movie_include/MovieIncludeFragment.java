package com.jtvr.fragment.Movie_Fragment.Movie_include;


import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.google.gson.Gson;
import com.jt.base.R;
import com.jtvr.adapter.HomeFragmentAdapter.HomeRecommendAdapter;
import com.jtvr.base.BaseFragment;
import com.jtvr.http.RequestEnum;
import com.jtvr.http.VrHttp;
import com.jtvr.jtInterface.MyClickListenner;
import com.jtvr.model.MovieCode;
import com.jtvr.utils.DividerGridItemDecoration;
import com.jtvr.utils.UIUtils;
import com.jtvr.vrplaymedia.VrPlayMediaActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 */
public class MovieIncludeFragment extends BaseFragment implements MyClickListenner {
    @BindView(R.id.movieFragment_vip_RecyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.movieFragment_empty)
    TextView empty;
    @BindView(R.id.movieFragment_vip_Refresh)
    XRefreshView xRefreshView;
    private String code, firstCode;
    private int pageNo = 1, pageSize = 10;
    private HomeRecommendAdapter adapter;
    private List<MovieCode.BusinessBean.ListBean> list;

    @Override
    public int setLayout() {
        return R.layout.fragment_movie_vip;
    }

    public static MovieIncludeFragment getInstance(String code, String firstCode) {
        MovieIncludeFragment mf = new MovieIncludeFragment();
        mf.code = code;
        mf.firstCode = firstCode;
        return mf;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this, view);
        list = new ArrayList<>();
        initHttp();
        initRecycler();
    }

    private void initRecycler() {
        xRefreshView.setPullLoadEnable(true);
        recyclerView.setHasFixedSize(true);
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setPullRefreshEnable(false);//关闭下拉加载
        adapter = new HomeRecommendAdapter(new ArrayList<MovieCode.BusinessBean.ListBean>(), getContext(), this);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        DividerGridItemDecoration dividerGridItemDecoration = new DividerGridItemDecoration(getContext());
        dividerGridItemDecoration.setDivider(R.drawable.divider_bg);
        recyclerView.addItemDecoration(dividerGridItemDecoration);
        recyclerView.setAdapter(adapter);
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

    private void initHttp() {

        VrHttp.getInstance().requestForResult(RequestEnum.MOVIECODE, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {
                MovieCode movieCode = new Gson().fromJson(response, MovieCode.class);
                list.addAll(movieCode.getBusiness().getList());
                if (list.size() == 0) {
                    empty.setVisibility(View.VISIBLE);
                } else {
                    empty.setVisibility(View.INVISIBLE);
                    adapter.getData(movieCode.getBusiness().getList());
                }
                if (movieCode.getBusiness().getList().size() > 0) {
                    xRefreshView.stopLoadMore(true);//加载成功
                } else {
                    xRefreshView.stopLoadMore(true);//加载失败
                    UIUtils.showToast("已没有更多");
                    pageNo--;
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                xRefreshView.stopLoadMore(false);//加载失败
            }
        }, RequestEnum.MOVIECODE.addIntent(code, firstCode, pageNo, pageSize));
    }

    @Override
    public void MyRecyclerViewClickListenner(View view, int position) {
        Intent intent = new Intent(getContext(), VrPlayMediaActivity.class);
        intent.putExtra("video", list.get(position).getVideoCode());
        startActivity(intent);
    }
}