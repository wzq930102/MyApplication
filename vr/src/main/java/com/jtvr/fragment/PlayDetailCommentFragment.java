package com.jtvr.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jt.base.R;
import com.jtvr.adapter.MyCommentExListViewAdapter;
import com.jtvr.http.RequestEnum;
import com.jtvr.http.VrHttp;
import com.jtvr.model.PlayCommentDetail;
import com.jtvr.model.SuccedInfo;
import com.jtvr.utils.SharedPreferencesUtils;
import com.jtvr.utils.UIUtils;
import com.jtvr.view.widget.MyExpandableListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by jt on 2017/4/19.
 */
public class PlayDetailCommentFragment extends Fragment {
    @BindView(R.id.playdetailcomment_sum)
    TextView mPlaydetailcommentSum;
    @BindView(R.id.playdetailcomment_rb)
    RatingBar mPlaydetailcommentRb;
    @BindView(R.id.playdetailcomment_grade)
    TextView mPlaydetailcommentGrade;
    @BindView(R.id.playdetailcomment_con)
    TextView mplaydetailcomment_con;
    @BindView(R.id.playdetailcomment_photo)
    ImageView mPlaydetailcommentPhoto;
    @BindView(R.id.playdetailcomment_edit)
    EditText mPlaydetailcommentEdit;
    @BindView(R.id.main_commentList_elv)
    MyExpandableListView mCommentExLV;
    private MyCommentExListViewAdapter mCommentExListViewAdapter;
    private String videoCode;
    private Context mContext;
    private PlayCommentDetail pcdetail;
    private String score;
    private String ratingBarScore;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playdetailcomment, null);
        ButterKnife.bind(this, view);
        mContext = getActivity();
        initView();
        RefreshCommentList();
        initClick();
        return view;
    }

    private void initView() {
        UIUtils.setImgeView(SharedPreferencesUtils.get(mContext,"img","").toString(),mPlaydetailcommentPhoto);
        mPlaydetailcommentGrade.setText(score+"分");
        if(" ".equals(score)){
            ratingBarScore = "0.0";
        }else{
            ratingBarScore = score;
            mPlaydetailcommentRb.setIsIndicator(true);
            mplaydetailcomment_con.setClickable(false);
            mplaydetailcomment_con.setText("您已评价");
        }
        mPlaydetailcommentRb.setRating(Float.parseFloat(ratingBarScore)/2);
    }

    private void initClick() {
        mPlaydetailcommentRb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mPlaydetailcommentGrade.setText(v*2+"分");
                ratingBarScore = v*2+"";
            }
        });
        mPlaydetailcommentEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_GO){
                    if(mPlaydetailcommentEdit.getText().toString().equals("")){
                        UIUtils.showToast("请填写评价");
                    }else{
                        PlayCommentCommit(mPlaydetailcommentEdit.getText().toString(),"");
                    }
                    return true;
                }
                return false;
            }
        });
        mplaydetailcomment_con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commintScore();
            }
        });
    }
    private void commintScore() {
        VrHttp.getInstance().requestForResult(RequestEnum.COMMITSCORE, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {
                SuccedInfo si = new Gson().fromJson(response.toString(),SuccedInfo.class);
                if(si.getStatus()!=null){
                    if("41".equals(si.getStatus())){
                        Toast.makeText(mContext,si.getErrmsg(),Toast.LENGTH_SHORT).show();
                        UIUtils.startLoginActivity(mContext);
                    } else if("99".equals(si.getStatus())){
                        Toast.makeText(mContext,si.getMsg(),Toast.LENGTH_SHORT).show();
                        mPlaydetailcommentRb.setIsIndicator(true);
                        mplaydetailcomment_con.setText("您已评价");
                        mplaydetailcomment_con.setClickable(false);
                    }
                }
            }
            @Override
            public void onError(Call call, Exception e, int id) {
            }
        }, RequestEnum.COMMITSCORE.addIntent(videoCode,ratingBarScore));
    }

    //回复
    private void PlayCommentCommit(String content,String commentCode){
        VrHttp.getInstance().requestForResult(RequestEnum.PLAYDETAILCOMMENTCOMMIT, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {
                SuccedInfo si = new Gson().fromJson(response.toString(),SuccedInfo.class);
                        if(si.getStatus()!=null){
                            if("41".equals(si.getStatus())){
                                Toast.makeText(mContext,si.getErrmsg(),Toast.LENGTH_SHORT).show();
                                UIUtils.startLoginActivity(mContext);
                            } else if("99".equals(si.getStatus())){
                                Toast.makeText(mContext,si.getMsg(),Toast.LENGTH_SHORT).show();
                                RefreshCommentList();
                                mPlaydetailcommentEdit.setText("");
                            }
                        }
            }
            @Override
            public void onError(Call call, Exception e, int id) {
            }
        }, RequestEnum.PLAYDETAILCOMMENTCOMMIT.addIntent(videoCode,content,commentCode));
    }
    private void RefreshCommentList() {
        VrHttp.getInstance().requestForResult(RequestEnum.PLAYDETAILCOMMENT, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {
                pcdetail = new Gson().fromJson(response.toString(),PlayCommentDetail.class);
                if(pcdetail.getStatus()!=null){
                    if("99".equals(pcdetail.getStatus())){
                        mPlaydetailcommentSum.setText("评论（"+pcdetail.getBusiness().getList().size()+")");
                        mCommentExListViewAdapter = new MyCommentExListViewAdapter(mContext,pcdetail.getBusiness().getList());
                        mCommentExLV.setAdapter(mCommentExListViewAdapter);
                        mCommentExListViewAdapter.setOnClickListen(new MyCommentExListViewAdapter.OnClickListen() {
                            @Override
                            public void OnClick(int group) {
                                showEditDilog(group);
//                                showNormalDialog();
                            }
                        });
                        mCommentExLV.setGroupIndicator(null);
                        expandAllGroup();
                    }
                }
            }
            @Override
            public void onError(Call call, Exception e, int id) {
            }
        }, RequestEnum.PLAYDETAILCOMMENT.addIntent(1,10,videoCode));
    }
    private void expandAllGroup() {
        // 默认展开每一个分组
        for (int i = 0; i < mCommentExListViewAdapter.getGroupCount(); i++) {
            mCommentExLV.expandGroup(i);
        }
    }

    public String getVideoCode() {
        return videoCode;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setVideoCode(String videoCode) {
        this.videoCode = videoCode;
    }
    private void showEditDilog(final int group){

        final EditText edit = new EditText(mContext);
        new android.support.v7.app.AlertDialog.Builder(mContext)
                .setTitle("提交回复")//提示框标题
                .setView(edit)
                .setPositiveButton("提交",//提示框的两个按钮
                        new android.content.DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                String msg = edit.getText().toString();
                                PlayCommentCommit(msg,pcdetail.getBusiness().getList().get(group).getFirstCommentCode());

                            }
                        }).setNegativeButton("取消", null).create().show();
    }
}
