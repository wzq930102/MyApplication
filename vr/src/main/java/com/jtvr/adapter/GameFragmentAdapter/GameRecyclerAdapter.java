package com.jtvr.adapter.GameFragmentAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jt.base.R;


/**
 * Created by yy on 2017/3/9.
 */
public class GameRecyclerAdapter extends RecyclerView.Adapter<GameRecyclerAdapter.GameRecyclerViewHolder> {
    @Override
    public GameRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GameRecyclerViewHolder holder = new GameRecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gamerecycler_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(GameRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class GameRecyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView name, title;

        public GameRecyclerViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.game_recycler_image);
            name = (TextView) view.findViewById(R.id.game_recycler_name);
            title = (TextView) view.findViewById(R.id.game_recycler_title);

        }
    }


}
