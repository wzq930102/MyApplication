package com.jtvr.adapter.MovieDetailAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by yy on 2017/3/15.
 */
public class MovieVIPRecyclerAdapter {


    class MovieVIPViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title, content;

        public MovieVIPViewHolder(View itemView) {
            super(itemView);
        }
    }
}
