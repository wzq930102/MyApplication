package com.jtvr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jt.base.R;
import com.jtvr.jtInterface.MyClickListenner;
import com.jtvr.model.DBBean;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yy on 2017/4/18.
 */
public class DownloadAcivityAdapter extends RecyclerView.Adapter<DownloadAcivityAdapter.CollectionViewHolder> {
    private List<DBBean> list;
    private Context context;
    private List<Boolean> listCheck;
    public boolean isShow = false;
    private MyClickListenner myClickListenner;
    private RecyclerView recyclerView;
    private DecimalFormat df = new DecimalFormat("######0.00");

    public DownloadAcivityAdapter(List<DBBean> list, Context context, List<Boolean> listCheck, MyClickListenner myClickListenner) {
        this.list = list;
        this.context = context;
        this.listCheck = listCheck;
        this.myClickListenner = myClickListenner;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public CollectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CollectionViewHolder holder = new CollectionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.download_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final CollectionViewHolder holder, final int position) {
        holder.position = position;
        holder.historyItemName.setText(list.get(position).getTitle());

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
        if (!"1".equals(list.get(position).getFinish())) {
            holder.progressBar.setVisibility(View.VISIBLE);
            if (list.get(position).getArgs2() != null) {
                holder.progressBar.setProgress(Integer.parseInt(list.get(position).getArgs4()));
                double a = (double) Integer.parseInt(list.get(position).getArgs3()) / 1024 / 1024;
                double b = (double) Integer.parseInt(list.get(position).getArgs2()) / 1024 / 1024;
                holder.historyItemNum.setText(df.format(b) + "MB" + "/" + df.format(a) + "MB");
            } else {
                holder.progressBar.setVisibility(View.INVISIBLE);
                holder.historyItemNum.setText("等待中");
            }
        } else {
            holder.progressBar.setVisibility(View.INVISIBLE);
            holder.historyItemNum.setText("已完成");
        }
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
        @BindView(R.id.progressBar_horizontal)
        ProgressBar progressBar;
        private int position;

        public CollectionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            collectionItemCheckBox.setOnCheckedChangeListener(this);
            collectionItemParent.setOnClickListener(this);
            progressBar.setMax(100);

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