package com.jtvr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jt.base.R;
import com.jtvr.model.VIPBean;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private List<VIPBean> data;
    private OnRecyclerViewItemClickListener mOnItemClickListener;
    private MyViewHolder holder;
    private int layoutPosition;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    public CustomAdapter(Context context, List<VIPBean> data) {
        this.context = context;
        this.data = data;
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.vip_item, parent, false);
        holder = new MyViewHolder(itemView);
        return holder;
    }

    @Override

    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.title.setText(data.get(position).getTitle());
        holder.num.setText(data.get(position).getNum() + "元");
        holder.parent.setTag(data.get(position));

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取当前点击的位置
                layoutPosition = holder.getLayoutPosition();
                notifyDataSetChanged();
                mOnItemClickListener.onItemClick(view, layoutPosition);
            }
        });

        //更改状态
        if (position == layoutPosition) {
//            holder.parent.setBackgroundResource(R.drawable.bg_unselect);
            holder.parent.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.shape_vip_sel));
        } else {
            holder.parent.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.shape_vip));
//            holder.parent.setTextColor(Color.BLUE);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final RelativeLayout parent;
        private TextView title, num;

        public MyViewHolder(View itemView) {
            super(itemView);
            parent = (RelativeLayout) itemView.findViewById(R.id.vip_item_parent);
            title = (TextView) itemView.findViewById(R.id.vip_item_title);
            num = (TextView) itemView.findViewById(R.id.vip_item_num);
        }
    }
}
