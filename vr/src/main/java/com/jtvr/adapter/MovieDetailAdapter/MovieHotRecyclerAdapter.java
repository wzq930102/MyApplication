package com.jtvr.adapter.MovieDetailAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jt.base.R;
import com.jtvr.jtInterface.MovieHotClickListener;
import com.jtvr.model.MovieHotBean;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieHotRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MovieHotBean.BusinessBean.ListBean> list;
    private MovieHotClickListener movieHotClickListener;
    private RecyclerView recyclerView;
    private ImageView[] image;
    private TextView[] titles, contents, property;

    public MovieHotRecyclerAdapter(List<MovieHotBean.BusinessBean.ListBean> list, MovieHotClickListener movieHotClickListener) {
        this.list = list;
        this.movieHotClickListener = movieHotClickListener;
    }

    public void getData(List<MovieHotBean.BusinessBean.ListBean> data) {
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = new ViewHolder1(LayoutInflater.from(parent.getContext()).inflate(R.layout.moviefragment_hot_item1, parent, false));
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder1 holder1 = (ViewHolder1) holder;
        holder1.textName.setText(list.get(position).getMenuName());
        for (int i = 0; i < list.get(position).getVideoList().size(); i++) {
            if (i < 4) {
                Picasso.with(recyclerView.getContext()).load(list.get(position).getVideoList().get(i).getImg()).into(image[i]);
                titles[i].setText(list.get(position).getVideoList().get(i).getName());
                contents[i].setText(list.get(position).getVideoList().get(i).getVideoIntro());
                if ("2D".equals(list.get(position).getVideoList().get(i).getProperty())) {
                    property[i].setVisibility(View.INVISIBLE);
                } else {
                    property[i].setVisibility(View.VISIBLE);
                }
                property[i].setText(list.get(position).getVideoList().get(i).getProperty());

                holder1.id1_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        movieHotClickListener.MyMovieHotClickListener(v, position, 0);
                    }
                });
                holder1.id2_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        movieHotClickListener.MyMovieHotClickListener(v, position, 1);
                    }
                });
                holder1.id3_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        movieHotClickListener.MyMovieHotClickListener(v, position, 2);
                    }
                });
                holder1.id4_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        movieHotClickListener.MyMovieHotClickListener(v, position, 3);
                    }
                });
                holder1.more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        movieHotClickListener.MyMovieHotClickListener(v, position, 10);
                    }
                });
            }

        }

    }


    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        private ImageView id1_image, id2_image, id3_image, id4_image;
        private TextView id1_title, id2_title, id3_title, id4_title;
        private TextView id1_content, id2_content, id3_content, id4_content;
        private TextView id1_property, id2_property, id3_property, id4_property;
        private TextView textName;
        private TextView more;

        public ViewHolder1(View view) {
            super(view);
            textName = (TextView) view.findViewById(R.id.movieFragment_item1_text);
            more = (TextView) view.findViewById(R.id.movieFragment_item1_more);
            ViewGroup view1 = (ViewGroup) view.findViewById(R.id.movieFragment_include1);
            ViewGroup view2 = (ViewGroup) view.findViewById(R.id.movieFragment_include2);
            ViewGroup view3 = (ViewGroup) view.findViewById(R.id.movieFragment_include3);
            ViewGroup view4 = (ViewGroup) view.findViewById(R.id.movieFragment_include4);
            id1_image = (ImageView) view1.findViewById(R.id.item_video_iv);
            id2_image = (ImageView) view2.findViewById(R.id.item_video_iv);
            id3_image = (ImageView) view3.findViewById(R.id.item_video_iv);
            id4_image = (ImageView) view4.findViewById(R.id.item_video_iv);
            image = new ImageView[]{id1_image, id2_image, id3_image, id4_image};
            id1_title = (TextView) view1.findViewById(R.id.item_video_title);
            id2_title = (TextView) view2.findViewById(R.id.item_video_title);
            id3_title = (TextView) view3.findViewById(R.id.item_video_title);
            id4_title = (TextView) view4.findViewById(R.id.item_video_title);
            titles = new TextView[]{id1_title, id2_title, id3_title, id4_title};
            id1_content = (TextView) view1.findViewById(R.id.item_video_content);
            id2_content = (TextView) view2.findViewById(R.id.item_video_content);
            id3_content = (TextView) view3.findViewById(R.id.item_video_content);
            id4_content = (TextView) view4.findViewById(R.id.item_video_content);
            contents = new TextView[]{id1_content, id2_content, id3_content, id4_content};
            id1_property = (TextView) view1.findViewById(R.id.item_video_property);
            id2_property = (TextView) view2.findViewById(R.id.item_video_property);
            id3_property = (TextView) view3.findViewById(R.id.item_video_property);
            id4_property = (TextView) view4.findViewById(R.id.item_video_property);
            property = new TextView[]{id1_property, id2_property, id3_property, id4_property};
        }

    }
}