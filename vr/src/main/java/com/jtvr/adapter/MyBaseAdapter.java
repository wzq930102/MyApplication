package com.jtvr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jt.base.R;
import com.jtvr.model.videoInfo;
import com.jtvr.utils.UIUtils;

import java.util.ArrayList;

/**
 * Created by jt on 2017/3/3.
 */
public class MyBaseAdapter extends BaseAdapter {
    private ArrayList<videoInfo> data;
    private Context context;
    public MyBaseAdapter(Context context,ArrayList<videoInfo> data){
        this.data = data;
        this.context = context;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }


    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
                     ViewHolder holder = null;
               if (convertView == null) {
                   holder=new ViewHolder();
                   convertView = LayoutInflater.from(context).inflate(R.layout.item_video, null);
                   holder.iv = (ImageView) convertView.findViewById(R.id.item_video_iv);
                   holder.content = (TextView) convertView.findViewById(R.id.item_video_content);
                   holder.title = (TextView) convertView.findViewById(R.id.item_video_title);
                   convertView.setTag(holder);
                }else {
                    holder = (ViewHolder)convertView.getTag();
                }
                    videoInfo vi = (videoInfo) getItem(i);
                    UIUtils.setImgeView(vi.getIv(),holder.iv);
                    holder.content.setText(vi.getContent());
                    holder.title.setText(vi.getTitle());
          return convertView;
    }
    class ViewHolder{
        TextView title;
        TextView content;
        ImageView iv;
    }
}
