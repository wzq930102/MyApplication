package com.jtvr.adapter.HomeFragmentAdapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jt.base.R;
import com.jtvr.jtInterface.MyClickListenner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yy on 2017/3/16.
 */
public class MovieVRLiveRecyclerAdapter extends RecyclerView.Adapter<MovieVRLiveRecyclerAdapter.LiveViewHolder> {
    private List<Bitmap> image;
    private MyClickListenner clickListenner;
    private RecyclerView recyclerView;

    public MovieVRLiveRecyclerAdapter(List<Bitmap> image, MyClickListenner clickListenner) {
        this.image = image;
        this.clickListenner = clickListenner;
    }

    public void getData(List<Bitmap> data){
        image.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public LiveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_live_item, parent, false);
        LiveViewHolder holder = new LiveViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListenner.MyRecyclerViewClickListenner(v, recyclerView.getChildAdapterPosition(v));
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(LiveViewHolder holder, int position) {
        holder.movieLiveContent.setImageBitmap(image.get(position));
    }

    @Override
    public int getItemCount() {
        return image != null ? image.size() : 0;
    }

    class LiveViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movie_live_content)
        ImageView movieLiveContent;
        @BindView(R.id.movie_live_title)
        ImageView movieLiveTitle;
//        @BindView(R.id.movie_live_textName)
//        TextView movieLiveTextName;
//        @BindView(R.id.movie_live_textContent)
//        TextView movieLiveTextContent;

        public LiveViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
