package com.jtvr.adapter.MovieDetailAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jt.base.R;

import java.util.List;

/**
 * Created by yy on 2017/3/9.
 */
public class MovieDetailRecyclerAdapter extends RecyclerView.Adapter<MovieDetailRecyclerAdapter.MyViewHolder> {
    private List<String> list;


    public MovieDetailRecyclerAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.myadpter_item, parent, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(list.get(position) + "啦啦啦啦啦啦啦");
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.my_text);
        }
    }
}
