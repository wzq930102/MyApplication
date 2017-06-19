package com.jtvr.adapter;

import android.content.Context;
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
 * Created by jt on 2017/4/11.
 */
public class PlaydateAdapter extends RecyclerView.Adapter<PlaydateAdapter.myPlaydateViewHoder> {
    Context context;
    List<playMovieBean.BusinessBean.SimilarityBean> similarity;
    OnRecyclerViewClickListen listen;

    public PlaydateAdapter(Context context, List<playMovieBean.BusinessBean.SimilarityBean> similarity){
        this.context = context;
        this.similarity = similarity;
    }
    public void setOnClickItemList(OnRecyclerViewClickListen listen){
        this.listen = listen;
    }
    @Override
    public myPlaydateViewHoder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_palydatail,parent,false);
        myPlaydateViewHoder holder = new myPlaydateViewHoder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(myPlaydateViewHoder holder, int position) {
       UIUtils.setImgeView(similarity.get(position).getImg5(),holder.img);
        holder.name.setText(similarity.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if(similarity.size()>=6) {
            return 6;
        }else{
            return similarity.size();
        }

    }

    class myPlaydateViewHoder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;

        public myPlaydateViewHoder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.item_playdatial_iv);
            name = (TextView) itemView.findViewById(R.id.item_playdatial_tv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listen.OnclickItem(getPosition());
                }
            });
        }
    }
    public interface OnRecyclerViewClickListen {
        public abstract void OnclickItem(int position);
    }
}
