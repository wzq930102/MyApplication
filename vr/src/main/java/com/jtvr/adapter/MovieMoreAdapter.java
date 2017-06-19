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
import com.jtvr.model.MovieMoreBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by yy on 2017/4/9.
 */
public class MovieMoreAdapter extends RecyclerView.Adapter<MovieMoreAdapter.MovieMoreViewHolder> {
    private List<MovieMoreBean.BusinessBean.ListBean> list;
    private MyClickListenner myClickListenner;
    private Context context;

    public MovieMoreAdapter(MyClickListenner myClickListenner, List<MovieMoreBean.BusinessBean.ListBean> list, Context context) {
        this.myClickListenner = myClickListenner;
        this.list = list;
        this.context = context;
    }

    public void setData(List<MovieMoreBean.BusinessBean.ListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public MovieMoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MovieMoreViewHolder holder = new MovieMoreViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.special_detail_item, parent, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(MovieMoreViewHolder holder, final int position) {
        MovieMoreBean.BusinessBean.ListBean listBean = list.get(position);
        Picasso.with(context).load(listBean.getImg()).into(holder.image);
        holder.title.setText(listBean.getName());
        holder.content.setText(listBean.getVideoIntro());
        holder.num.setText(listBean.getPlayCount());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClickListenner.MyRecyclerViewClickListenner(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MovieMoreViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title, content, num;
        private RelativeLayout relativeLayout;

        public MovieMoreViewHolder(View view) {
            super(view);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.detail_item_layout);
            image = (ImageView) view.findViewById(R.id.detail_item_image);
            title = (TextView) view.findViewById(R.id.detail_item_title);
            content = (TextView) view.findViewById(R.id.detail_item_content);
            num = (TextView) view.findViewById(R.id.detail_item_num);
        }
    }

}
