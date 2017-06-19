package com.jtvr.adapter.MovieDetailAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jt.base.R;
import com.jtvr.model.playMovieBean;
import com.jtvr.utils.UIUtils;

import java.util.List;

/**
 * Created by yy on 2017/3/9.
 */
public class MovieDetailScrollAdapter extends RecyclerView.Adapter<MovieDetailScrollAdapter.MyHengxiangViewHolder> {
    private List<playMovieBean.BusinessBean.SimilarityBean> list;
    clickListent ck;


    public MovieDetailScrollAdapter(List<playMovieBean.BusinessBean.SimilarityBean> list) {
        this.list = list;
    }

    @Override
    public MyHengxiangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHengxiangViewHolder viewHolder = new MyHengxiangViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.hengxiang_item, parent, false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyHengxiangViewHolder holder, int position) {
        UIUtils.setImgeView(list.get(position).getImg(),holder.iv);
        holder.tv.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class MyHengxiangViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;
        public MyHengxiangViewHolder(View view) {
            super(view);
            iv = (ImageView) view.findViewById(R.id.hengxiang_image);
            tv = (TextView) view.findViewById(R.id.hengxiang_title);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ck.itemClickListent(getPosition());
                }
            });
        }


    }
    public interface clickListent{
        abstract void itemClickListent(int i);
    }
    public void setClick(clickListent cl){
        this.ck = cl;
    }


}