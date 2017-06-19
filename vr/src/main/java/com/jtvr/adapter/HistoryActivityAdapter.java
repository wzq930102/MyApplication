package com.jtvr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jt.base.R;
import com.jtvr.jtInterface.MyClickListenner;
import com.jtvr.model.HistoryBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yy on 2017/4/12.
 */
public class HistoryActivityAdapter extends RecyclerView.Adapter<HistoryActivityAdapter.HistoryViewHolder> {
    private List<HistoryBean.BusinessBean.ListBean> list;
    private Context context;
    private MyClickListenner myClickListenner;

    public HistoryActivityAdapter(List<HistoryBean.BusinessBean.ListBean> list, Context context, MyClickListenner myClickListenner) {
        this.list = list;
        this.context = context;
        this.myClickListenner = myClickListenner;
    }

    public void setData(List<HistoryBean.BusinessBean.ListBean> data) {
        this.list=data;
        notifyDataSetChanged();
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HistoryViewHolder viewHolder = new HistoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, final int position) {
        holder.historyItemName.setText(list.get(position).getTitle());
        holder.historyItemNum.setText(list.get(position).getTime());
        Picasso.with(context).load(list.get(position).getImg()).into(holder.historyItemImage);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClickListenner.MyRecyclerViewClickListenner(v, position);
            }
        });
    }



    @Override
    public int getItemCount() {
        return  list != null ? list.size() : 0;
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.history_item_image)
        ImageView historyItemImage;
        @BindView(R.id.history_item_name)
        TextView historyItemName;
        @BindView(R.id.history_item_num)
        TextView historyItemNum;
        @BindView(R.id.history_item_parent)
        RelativeLayout relativeLayout;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
