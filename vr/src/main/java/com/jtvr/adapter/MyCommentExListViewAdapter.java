package com.jtvr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jt.base.R;
import com.jtvr.model.PlayCommentDetail;
import com.jtvr.utils.UIUtils;

import java.util.List;

/**
 * Created by zhongbaojian on 14-8-19.
 * 评论列表数据适配器
 */
public class MyCommentExListViewAdapter extends BaseExpandableListAdapter{

    private Context mContext;
    private LayoutInflater mInflater;
    private OnClickListen listen;
    private List<PlayCommentDetail.BusinessBean.ListBean> commentItemList;
    private int mCurrentGroupPosition = 0;

    public MyCommentExListViewAdapter(Context context, List<PlayCommentDetail.BusinessBean.ListBean> commentItemList) {
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.commentItemList = commentItemList;
    }
    @Override
    public int getGroupCount() {
        return commentItemList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        PlayCommentDetail.BusinessBean.ListBean commentItem = commentItemList.get(groupPosition);
        if (commentItem.getSecondComment() == null) {
            return 0;
        } else {
            return commentItem.getSecondComment().size();
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return commentItemList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        PlayCommentDetail.BusinessBean.ListBean commentItem = commentItemList.get(groupPosition);
        if (commentItem.getSecondComment() == null) {
            return null;
        } else {
            return commentItem.getSecondComment().get(childPosition);
        }
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        CommentViewHolder commentViewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.comment_listview_item, null);
            commentViewHolder = new CommentViewHolder(convertView);
            convertView.setTag(commentViewHolder);
        } else {
            commentViewHolder = (CommentViewHolder) convertView.getTag();
        }
        PlayCommentDetail.BusinessBean.ListBean commentItem = commentItemList.get(groupPosition);
        UIUtils.setImgeView(commentItem.getFirstImg(),commentViewHolder.userHeaderImg);
        commentViewHolder.userNameTv.setText(commentItem.getFirstUser());
        commentViewHolder.contentTv.setText(commentItem.getFirstContent());
        commentViewHolder.replyBtn.setTag(groupPosition);
        commentViewHolder.replyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//点击回复
               listen.OnClick(groupPosition);
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        SubCommentViewHolder subCommentViewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.comment_listview_sub_item, null);
            subCommentViewHolder = new SubCommentViewHolder(convertView);
            convertView.setTag(subCommentViewHolder);
        } else {
            subCommentViewHolder = (SubCommentViewHolder) convertView.getTag();
        }

        PlayCommentDetail.BusinessBean.ListBean commentItem = commentItemList.get(groupPosition);
        if (commentItem.getSecondComment() != null) {
            PlayCommentDetail.BusinessBean.ListBean.SecondCommentBean subCommentItem = commentItem.getSecondComment().get(childPosition);
            UIUtils.setImgeView(subCommentItem.getSecondImg(),subCommentViewHolder.userHeaderImg);
            subCommentViewHolder.userNameTv.setText(subCommentItem.getSecondUser());
            subCommentViewHolder.contentTv.setText(subCommentItem.getSecondContent());

            return convertView;
        } else {
            return null;
        }
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    public void setOnClickListen(OnClickListen listen){
        this.listen = listen;
    }
    public interface OnClickListen{
       void  OnClick(int group);
    }

    }
    class CommentViewHolder {
        ImageView userHeaderImg;
        TextView userNameTv;
        TextView contentTv;
        TextView replyBtn;

        CommentViewHolder(View parent) {
            userHeaderImg = (ImageView) parent.findViewById(R.id.commentListViewItem_userHeader_img);
            userNameTv = (TextView) parent.findViewById(R.id.commentListViewItem_userName_tv);
            contentTv = (TextView) parent.findViewById(R.id.commentListViewItem_commentContent_tv);
            replyBtn = (TextView) parent.findViewById(R.id.commentListViewItem_reply_btn);
        }
    }
    class SubCommentViewHolder {
        ImageView userHeaderImg;
        TextView userNameTv;
        TextView contentTv;

        SubCommentViewHolder(View parent) {
            userHeaderImg = (ImageView) parent.findViewById(R.id.commentListViewChildItem_userHeader_img);
            userNameTv = (TextView) parent.findViewById(R.id.commentListViewChildItem_userName_tv);
            contentTv = (TextView) parent.findViewById(R.id.commentListViewChildItem_content_tv);
        }
    }

