package com.jtvr.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jt.base.R;
import com.jtvr.jtInterface.MyClickListenner;
import com.jtvr.model.MovieBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by yy on 2017/3/24.
 */
public class SpecialDetailActivityAdapter extends RecyclerView.Adapter<SpecialDetailActivityAdapter.SpecialDetailViewHolder> {
    private MyClickListenner myClickListenner;
    private List<MovieBean> list;

    public SpecialDetailActivityAdapter(MyClickListenner myClickListenner, List<MovieBean> list) {
        this.myClickListenner = myClickListenner;
        this.list = list;
    }

    @Override
    public SpecialDetailActivityAdapter.SpecialDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SpecialDetailViewHolder viewHolder = new SpecialDetailViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.special_detail_item, parent, false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SpecialDetailActivityAdapter.SpecialDetailViewHolder holder, final int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.content.setText(list.get(position).getContent());
        Picasso.with(holder.image.getContext()).load(list.get(position).getCoverUrl()).into(holder.image);
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

    class SpecialDetailViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title, content;
        private RelativeLayout relativeLayout;

        public SpecialDetailViewHolder(View view) {
            super(view);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.detail_item_layout);
            image = (ImageView) view.findViewById(R.id.detail_item_image);
            title = (TextView) view.findViewById(R.id.detail_item_title);
            content = (TextView) view.findViewById(R.id.detail_item_content);
        }
    }
}
