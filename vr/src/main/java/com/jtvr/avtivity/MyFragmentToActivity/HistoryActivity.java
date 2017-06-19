package com.jtvr.avtivity.MyFragmentToActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.google.gson.Gson;
import com.jt.base.R;
import com.jtvr.adapter.HistoryActivityAdapter;
import com.jtvr.base.BaseActivity;
import com.jtvr.http.RequestEnum;
import com.jtvr.http.VrHttp;
import com.jtvr.jtInterface.MyClickListenner;
import com.jtvr.model.HistoryBean;
import com.jtvr.utils.DividerItemDecoration;
import com.jtvr.utils.SharedPreferencesUtils;
import com.jtvr.utils.UIUtils;
import com.jtvr.vrplaymedia.VrPlayMediaActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class HistoryActivity extends BaseActivity implements MyClickListenner {
    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.title_register)
    TextView titleRegister;
    @BindView(R.id.history_recyclerView)
    RecyclerView historyRecyclerView;
    @BindView(R.id.xrefreshview)
    XRefreshView xRefreshView;
    private int pageNo = 1;
    private int pageSize = 10;
    private HistoryActivityAdapter adapter;
    private List<HistoryBean.BusinessBean.ListBean> list;

    @Override
    public void initView() {
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
        String name = (String) SharedPreferencesUtils.get(this, "name", "");
        if ("".equals(name)) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {
            titleTitle.setText("观看历史");
            titleRegister.setText("清空");
            historyRecyclerView.setHasFixedSize(true);
            xRefreshView.setPullLoadEnable(true);
            xRefreshView.setPinnedTime(1000);
            xRefreshView.setMoveForHorizontal(true);
            xRefreshView.setPullRefreshEnable(false);//关闭下拉加载
            list = new ArrayList<>();
            adapter = new HistoryActivityAdapter(list, this, this);
            historyRecyclerView.setAdapter(adapter);
            historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            historyRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
            initHttp();
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


    }

    private void cleanHistoryHttp() {
        VrHttp.getInstance().requestForResult(RequestEnum.CLEANHISTORY, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {
                list.clear();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Call call, Exception e, int id) {

            }
        }, RequestEnum.CLEANHISTORY.addIntent());
    }

    private void initHttp() {
        VrHttp.getInstance().requestForResult(RequestEnum.HISTORY, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {
                Gson gson = new Gson();
                HistoryBean historyBean = gson.fromJson(response, HistoryBean.class);
                if ("获取成功".equals(historyBean.getMsg())) {
                    list.addAll(historyBean.getBusiness().getList());
                    adapter.setData(list);
                    if (historyBean.getBusiness().getList().size() > 0) {
                        xRefreshView.stopLoadMore(true);//加载成功
                    } else {
                        xRefreshView.stopLoadMore(true);//加载失败
                        if (pageNo > 1) {
                            UIUtils.showToast("已没有更多");
                        }
                        pageNo--;
                    }
                }

            }

            @Override
            public void onError(Call call, Exception e, int id) {
                xRefreshView.stopLoadMore(false);//加载失败
            }
        }, RequestEnum.HISTORY.addIntent(pageNo, pageSize));
    }

    @Override
    public void initClick() {

    }


    @OnClick({R.id.title_back, R.id.title_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_register:
                showDialog();
                break;
        }
    }

    @Override
    public void MyRecyclerViewClickListenner(View view, int position) {
        Intent intent = new Intent(this, VrPlayMediaActivity.class);
        intent.putExtra("video", list.get(position).getVideoCode());
        startActivity(intent);
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("是否清空观看历史？");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cleanHistoryHttp();
            }
        });
        builder.show();
    }
}
