package com.jtvr.adapter.HomeFragmentAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jt.base.R;
import com.jtvr.jtInterface.MyClickListenner;
import com.jtvr.model.MovieCode;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by yy on 2017/3/9.
 */
public class HomeRecommendAdapter extends RecyclerView.Adapter<HomeRecommendAdapter.ViewHolder> {
    private List<MovieCode.BusinessBean.ListBean> list;
    private Context context;
    private MyClickListenner myClickListenner;

    private RecyclerView recyclerView;


    public HomeRecommendAdapter(List<MovieCode.BusinessBean.ListBean> list, Context context, MyClickListenner myClickListenner) {
        this.list = list;
        this.context = context;
        this.myClickListenner = myClickListenner;
    }

    public void getData(List<MovieCode.BusinessBean.ListBean> data) {
        list.addAll(data);
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
        ViewHolder holder = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClickListenner.MyRecyclerViewClickListenner(v, recyclerView.getChildAdapterPosition(v));
            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.content.setText(list.get(position).getVideoIntro());
        Picasso.with(context).load(list.get(position).getImg()).into(holder.imageView);
        if ("2D".equals(list.get(position).getProperty())) {
            holder.property.setVisibility(View.INVISIBLE);
        } else {
            holder.property.setVisibility(View.VISIBLE);
        }
        holder.property.setText(list.get(position).getProperty());
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title, content, property;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.item_video_iv);
            title = (TextView) view.findViewById(R.id.item_video_title);
            content = (TextView) view.findViewById(R.id.item_video_content);
            property = (TextView) view.findViewById(R.id.item_video_property);
        }
    }


}
