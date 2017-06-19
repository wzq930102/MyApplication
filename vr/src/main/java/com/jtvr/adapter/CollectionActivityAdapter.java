package com.jtvr.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jt.base.R;
import com.jtvr.avtivity.MyFragmentToActivity.CollectionActivity;
import com.jtvr.jtInterface.MyClickListenner;
import com.jtvr.model.HistoryBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CollectionActivityAdapter extends RecyclerView.Adapter<CollectionActivityAdapter.CollectionViewHolder> {
    private List<HistoryBean.BusinessBean.ListBean> list;
    private CollectionActivity context;
    private List<Boolean> listCheck;
    public boolean isShow = false;
    private MyClickListenner myClickListenner;
    private RecyclerView recyclerView;

    public CollectionActivityAdapter(List<HistoryBean.BusinessBean.ListBean> list, CollectionActivity context, List<Boolean> listCheck, MyClickListenner myClickListenner) {
        this.list = list;
        this.context = context;
        this.listCheck = listCheck;
        this.myClickListenner = myClickListenner;
    }

    public void setData(List<HistoryBean.BusinessBean.ListBean> data, List<Boolean> isCheck) {

        this.list = data;
        this.listCheck = isCheck;
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public CollectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CollectionViewHolder holder = new CollectionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final CollectionViewHolder holder, final int position) {
        holder.position = position;
        holder.historyItemName.setText(list.get(position).getTitle());
        holder.historyItemNum.setText(list.get(position).getTime());
        Picasso.with(context).load(list.get(position).getImg()).into(holder.collectionItemImage);
        holder.collectionItemCheckBox.setChecked(listCheck.get(position));
        if (isShow) {
            holder.collectionItemCheckBox.setVisibility(View.VISIBLE);
        } else {
            holder.collectionItemCheckBox.setVisibility(View.GONE);
        }
        holder.collectionItemParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClickListenner.MyRecyclerViewClickListenner(v, recyclerView.getChildAdapterPosition(v));
            }
        });
    }


    @Override

    public int getItemCount() {
        return list.size();
    }

    class CollectionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
        @BindView(R.id.collection_item_CheckBox)
        CheckBox collectionItemCheckBox;
        @BindView(R.id.collection_item_image)
        ImageView collectionItemImage;
        @BindView(R.id.history_item_name)
        TextView historyItemName;
        @BindView(R.id.history_item_num)
        TextView historyItemNum;
        @BindView(R.id.collection_item_parent)
        RelativeLayout collectionItemParent;
        private int position;

        public CollectionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            collectionItemCheckBox.setOnCheckedChangeListener(this);
            collectionItemParent.setOnClickListener(this);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (collectionItemCheckBox != null) {
                if (isShow) {
                    onItemClickListener.setOnItemCheckedChangeds(position, isChecked);
                }
            }
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                if (collectionItemCheckBox.isChecked()) {
                    collectionItemCheckBox.setChecked(false);
                    onItemClickListener.setOnItemClick(position, false);
                } else {
                    collectionItemCheckBox.setChecked(true);
                    onItemClickListener.setOnItemClick(position, true);
                }
            }
        }


    }

    public interface OnItemClickListener {
        void setOnItemClick(int position, boolean isCheck);

        void setOnItemCheckedChangeds(int position, boolean isCheck);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
