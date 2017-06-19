package com.jtvr.adapter;

import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jt.base.R;
import com.jtvr.jtInterface.MyClickListenner;

/**
 * Created by yy on 2017/3/23.
 */
public class SpecialAvtivityAdapter extends RecyclerView.Adapter<SpecialAvtivityAdapter.SpecialViewHolder> {


    private MyClickListenner myClickListenner;

    public SpecialAvtivityAdapter(MyClickListenner myClickListenner) {
        this.myClickListenner = myClickListenner;
    }


    @Override
    public SpecialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SpecialViewHolder viewHolder = new SpecialViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.special_recycler_item, parent, false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SpecialViewHolder holder, final int position) {
        if(position==0){
            holder.image.setImageBitmap(BitmapFactory.decodeResource(holder.image.getResources(), R.mipmap.timg_22));
        }else if (position==1){
           holder.image.setImageBitmap(BitmapFactory.decodeResource(holder.image.getResources(), R.mipmap.q5));
            holder.title.setText("如临其境 动作大片集锦");
        }

        holder.image.setOnClickListener(new View.OnClickListener() {
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

    class SpecialViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title, content;

        public SpecialViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.special_item_image);
            title= (TextView) view.findViewById(R.id.special_item_title);
            content= (TextView) view.findViewById(R.id.special_item_content);
        }
    }
}
