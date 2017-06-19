package com.jtvr.adapter.MovieDetailAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jt.base.R;
import com.jtvr.model.commentInfo;
import com.jtvr.utils.UIUtils;

import java.util.List;

/**
 * Created by jt on 2017/3/15.
 */
public class MovieDateilAdapter extends BaseAdapter {
    private Context conten;
    private List<commentInfo> date;
    public MovieDateilAdapter(Context conten,List<commentInfo> date){
        this.conten = conten;
        this.date = date;
    }
    @Override
    public int getCount() {
        return date.size();
    }

    @Override
    public commentInfo getItem(int i) {
        return date.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = LayoutInflater.from(conten).inflate(R.layout.item_moviedateil_pl, viewGroup, false);
            holder = new ViewHolder();
            holder.iv= (ImageView) convertView.findViewById(R.id.item_moviedatecomment_iv);
            holder.name=(TextView)convertView.findViewById(R.id.item_moviedatecomment_name);
            holder.content=(TextView)convertView.findViewById(R.id.item_moviedatecomment_content);
            holder.time=(TextView)convertView.findViewById(R.id.item_moviedatecomment_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        commentInfo item= getItem(i);
        if(!item.getUrl().equals("")){
            UIUtils.setImgeView(item.getUrl(),holder.iv);
        }
        holder.name.setText(item.getName());
        holder.content.setText(item.getContext());
        holder.time.setText(item.getTime());
        return convertView;
    }
    class ViewHolder{
        ImageView iv;
        TextView name;
        TextView content;
        TextView time;
    }
}
