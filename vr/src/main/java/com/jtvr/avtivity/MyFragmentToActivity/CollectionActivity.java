package com.jtvr.avtivity.MyFragmentToActivity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.google.gson.Gson;
import com.jt.base.R;
import com.jtvr.adapter.CollectionActivityAdapter;
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

public class CollectionActivity extends BaseActivity implements MyClickListenner {
    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.title_register)
    TextView titleRegister;
    @BindView(R.id.collection_recyclerView)
    RecyclerView collectionRecyclerView;
    @BindView(R.id.collectionAll)
    TextView collectionAll;
    @BindView(R.id.collectionDelete)
    TextView collectionDelete;
    @BindView(R.id.collection_parent1)
    LinearLayout linearLayout;
    @BindView(R.id.xrefreshview)
    XRefreshView xRefreshView;
    private int pageNo = 1;
    private int pageSize = 10;
    private List<HistoryBean.BusinessBean.ListBean> list;
    CollectionActivityAdapter adapter;
    private List<Boolean> listCheck;

    @Override
    public void initView() {
        setContentView(R.layout.activity_collection);
        ButterKnife.bind(this);
        titleTitle.setText("我的收藏");
        titleRegister.setText("编辑");
        String name = (String) SharedPreferencesUtils.get(this, "name", "");
        if ("".equals(name)) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {
            list = new ArrayList<>();
            listCheck = new ArrayList<>();
            collectionRecyclerView.setHasFixedSize(true);
            xRefreshView.setPullLoadEnable(true);
            xRefreshView.setPinnedTime(1000);
            xRefreshView.setMoveForHorizontal(true);
            xRefreshView.setPullRefreshEnable(false);//关闭下拉加载
            adapter = new CollectionActivityAdapter(list, this, listCheck, this);
            collectionRecyclerView.setAdapter(adapter);
            collectionRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            collectionRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
            initHttp();
            initListener();
        }


    }

    private void initListener() {
        adapter.setOnItemListener(new CollectionActivityAdapter.OnItemClickListener() {
            @Override
            public void setOnItemClick(int position, boolean isCheck) {
//                listCheck.set(position,isCheck);
            }

            @Override
            public void setOnItemCheckedChangeds(int position, boolean isCheck) {
                listCheck.set(position, isCheck);
                int num = 0;
                for (int i = 0; i < listCheck.size(); i++) {
                    if (listCheck.get(i)) {
                        num = num + 1;
                    }
                }
                if (num == 0) {
                    collectionDelete.setText("删除");
                } else {
                    collectionDelete.setText("删除" + "(" + num + ")");
                }
                if (num == listCheck.size()) {
                    collectionAll.setText("全不选");
                } else {
                    collectionAll.setText("全选");
                }
            }
        });
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
        VrHttp.getInstance().requestForResult(RequestEnum.COLLECTION, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {
                Gson gson = new Gson();
                HistoryBean historyBean = gson.fromJson(response, HistoryBean.class);
                if ("获取成功".equals(historyBean.getMsg())) {
                    list.addAll(historyBean.getBusiness().getList());
                    for (int i = 0; i < historyBean.getBusiness().getList().size(); i++) {
                        listCheck.add(false);
                    }
                    adapter.setData(list, listCheck);
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
        }, RequestEnum.COLLECTION.addIntent(pageNo, pageSize));
    }


    private void deletHttp() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < listCheck.size(); i++) {
            if (listCheck.get(i)) {
                stringBuffer.append(list.get(i).getKeepCode() + ",");
            }
        }
        VrHttp.getInstance().requestForResult(RequestEnum.CLEANCOLLECTION, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {

            }

            @Override
            public void onError(Call call, Exception e, int id) {

            }
        }, RequestEnum.CLEANCOLLECTION.addIntent(stringBuffer.toString()));
    }

    @Override
    public void initClick() {

    }

    @OnClick({R.id.title_title, R.id.title_back, R.id.title_register, R.id.collectionAll, R.id.collectionDelete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_title:
                break;
            case R.id.title_back:
                finish();
                break;
            case R.id.title_register:
                if ("编辑".equals(titleRegister.getText().toString())) {
                    titleRegister.setText("取消");
                    adapter.isShow = true;
                    adapter.notifyDataSetChanged();
                    linearLayout.setVisibility(View.VISIBLE);
                } else {
                    titleRegister.setText("编辑");
                    adapter.isShow = false;
                    adapter.notifyDataSetChanged();
                    linearLayout.setVisibility(View.GONE);
                    for (int i = 0; i < listCheck.size(); i++) {
                        listCheck.set(i, false);
                    }
                    collectionAll.setText("全选");
                }
                break;
            case R.id.collectionAll:
                if ("全选".equals(collectionAll.getText().toString())) {
                    collectionAll.setText("全不选");
                    for (int i = 0; i < listCheck.size(); i++) {
                        listCheck.set(i, true);
                    }
                } else {
                    collectionAll.setText("全选");
                    for (int i = 0; i < listCheck.size(); i++) {
                        listCheck.set(i, false);
                    }
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.collectionDelete:
                deletHttp();
                int length = listCheck.size();
                for (int i = length - 1; i >= 0; i--) {
                    if (listCheck.get(i)) {
                        list.remove(i);
                        listCheck.remove(i);
                        adapter.notifyItemRemoved(i);
                        adapter.notifyItemRangeChanged(0, list.size());
                    }
                }
                collectionDelete.setText("删除");
                collectionAll.setText("全选");
                titleRegister.setText("编辑");
                adapter.isShow = false;
                adapter.notifyDataSetChanged();
                linearLayout.setVisibility(View.GONE);
                for (int i = 0; i < listCheck.size(); i++) {
                    listCheck.set(i, false);
                }
                break;
        }
    }

    @Override
    public void MyRecyclerViewClickListenner(View view, int position) {
        if (!"取消".equals(titleRegister.getText().toString())) {
            Intent intent = new Intent(this, VrPlayMediaActivity.class);
            intent.putExtra("video", list.get(position).getVideoCode());
            startActivity(intent);
            finish();
        }

    }
}
