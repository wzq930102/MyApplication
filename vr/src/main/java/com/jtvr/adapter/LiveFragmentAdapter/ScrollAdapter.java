package com.jtvr.adapter.LiveFragmentAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.jt.base.R;
import com.jtvr.jtInterface.MyClickListenner;


/**
 * Created by yy on 2017/3/9.
 */
public class ScrollAdapter extends RecyclerView.Adapter<ScrollAdapter.LiveRecyclerViewHolder> {
    private MyClickListenner myClickListenner;

    public ScrollAdapter(MyClickListenner myClickListenner) {
        this.myClickListenner = myClickListenner;
    }

    @Override
    public LiveRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LiveRecyclerViewHolder holder = new LiveRecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.live_scroll_recycler_item, parent, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(LiveRecyclerViewHolder holder, final int position) {
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClickListenner.MyRecyclerViewClickListenner(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class LiveRecyclerViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout relativeLayout;

        public LiveRecyclerViewHolder(View itemView) {
            super(itemView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.live_recycler_parent);
        }
    }
}
