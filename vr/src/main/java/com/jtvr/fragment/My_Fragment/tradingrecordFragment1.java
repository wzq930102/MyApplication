package com.jtvr.fragment.My_Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jt.base.R;
import com.jtvr.base.BaseFragment;
import com.jtvr.http.RequestEnum;
import com.jtvr.http.VrHttp;
import com.jtvr.model.BuyRecordInfo;
import com.jtvr.utils.UIUtils;
import com.netease.hearttouch.htrefreshrecyclerview.HTLoadMoreListener;
import com.netease.hearttouch.htrefreshrecyclerview.HTRefreshListener;
import com.netease.hearttouch.htrefreshrecyclerview.HTRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by jt on 2017/4/7.
 */
public class tradingrecordFragment1 extends BaseFragment {
    HTRefreshRecyclerView mbuyrecord_recycler;
    BuyRecordInfo brinfo;
    myAdapter adapter;
    List<BuyRecordInfo.BusinessBean.ListBean> dataList;
    int i = 1;
    @Override
    public int setLayout() {
        return R.layout.fragment_tradingrecord1;
    }
    @Override
    public void initView(View view) {
        mbuyrecord_recycler = (HTRefreshRecyclerView) view.findViewById(R.id.buyrecord_recycler);
        dataList = new ArrayList<>();
        setDataRecycler();
        getBuyRecord();
        setRecycleRefresh();
    }

    private void setRecycleRefresh() {
        //若实现HTRefreshListener接口，支持刷新，不设置则默认不支持刷新
        mbuyrecord_recycler.setOnRefreshListener(new HTRefreshListener() {
            @Override
            public void onRefresh() {
                i = 1;
                dataList.clear();
                getBuyRecord();
            }
        });
        //若实现HTLoadMoreListener接口，支持加载更多，不设置则默认不支持加载更多
        mbuyrecord_recycler.setOnLoadMoreListener(new HTLoadMoreListener() {
            @Override
            public void onLoadMore() {
                i++;
                getBuyRecord();

            }
        });
    }
    private void getBuyRecord() {
        VrHttp.getInstance().requestForResult(RequestEnum.CHONGRECORD, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {
                brinfo = new Gson().fromJson(response.toString(),BuyRecordInfo.class);
                  if(brinfo.getStatus()!=null){
                      if(brinfo.getStatus().equals("99")){
                          if(brinfo.getBusiness().getList().size()!=0){
                              dataList.addAll(brinfo.getBusiness().getList());
                              adapter.notifyDataSetChanged();
                          }else{
                              UIUtils.showToast("数据加载完成");
                          }
                      }
                  }else{
                      UIUtils.showToast("数据返回异常！");
                  }
                mbuyrecord_recycler.setRefreshCompleted(true);
            }
            @Override
            public void onError(Call call, Exception e, int id) {
            }
        },RequestEnum.CHONGRECORD.addIntent(i+"","10"));
    }
    private void setDataRecycler() {
        adapter = new myAdapter();
        mbuyrecord_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mbuyrecord_recycler.setAdapter(adapter);
    }
    class myAdapter extends RecyclerView.Adapter<myAdapter.MyViewHolder>{

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder  = new MyViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.item_buyrecord,parent,false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.time.setText(dataList.get(position).getTime());
            holder.firsnum.setText(dataList.get(position).getFires()+"个火种币");
            holder.money.setText("¥  "+dataList.get(position).getMoney());
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {
            TextView time;
            TextView firsnum;
            TextView money;

            public MyViewHolder(View view)
            {
                super(view);
                time = (TextView) view.findViewById(R.id.record_time);
                firsnum = (TextView) view.findViewById(R.id.record_firs);
                money = (TextView) view.findViewById(R.id.record_menoy);
            }
        }
    }


}
