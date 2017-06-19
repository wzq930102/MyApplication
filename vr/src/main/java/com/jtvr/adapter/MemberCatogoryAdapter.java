package com.jtvr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jt.base.R;
import com.jtvr.model.FirsConfiguration;

import java.util.List;

/**
 * Created by jt on 2017/4/18.
 */
public class MemberCatogoryAdapter extends RecyclerView.Adapter<MemberCatogoryAdapter.MyViewholder> {
    private Context mContext;
    private List<FirsConfiguration.BusnessBean.ListBean> list;
    private ItemClickListen il;
    public MemberCatogoryAdapter(Context mContext,List<FirsConfiguration.BusnessBean.ListBean> list){
        this.mContext = mContext;
        this.list = list;
    }
    public void setOnClickItemListen(ItemClickListen il){
        this.il = il;
    }
    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_membercatogry,parent,false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                il.ClickItem(viewType);
            }
        });
        MyViewholder viewholder = new MyViewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {
        holder.tv1.setText(list.get(position).getBuyFire()+"");
        holder.tv2.setText("送"+list.get(position).getGiveFire()+"火种");
        holder.tv3.setText("¥"+list.get(position).getMoney());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewholder extends RecyclerView.ViewHolder{
        TextView tv1;
        TextView tv2;
        TextView tv3;
        public MyViewholder(View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.membercategory_left);
            tv2 = (TextView) itemView.findViewById(R.id.membercategory_cent);
            tv3 = (TextView) itemView.findViewById(R.id.membercategory_right);
        }

    }

    public interface ItemClickListen{
        public abstract void ClickItem(int i);
    }
}
