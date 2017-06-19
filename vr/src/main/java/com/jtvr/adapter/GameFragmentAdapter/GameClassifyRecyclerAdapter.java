package com.jtvr.adapter.GameFragmentAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jt.base.R;
import com.jtvr.model.GameClassify;

import java.util.List;

public class GameClassifyRecyclerAdapter extends RecyclerView.Adapter<GameClassifyRecyclerAdapter.GameClassifyViewHolder> {

    private List<GameClassify> list;

    @Override
    public GameClassifyRecyclerAdapter.GameClassifyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GameClassifyViewHolder holder = new GameClassifyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.game_classify_recycler_item1, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(GameClassifyRecyclerAdapter.GameClassifyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class GameClassifyViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title, content;

        public GameClassifyViewHolder(View view) {
            super(view);
        }
    }

}
