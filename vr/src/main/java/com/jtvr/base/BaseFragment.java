package com.jtvr.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jt.base.R;


/**
 * Created by jt on 2017/2/24.
 */
public abstract class BaseFragment extends Fragment {
    private Dialog progressDialog;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(setLayout(), container, false);
        initView(view);
        return view;
    }

    // 加载成功的布局, 必须由子类来实现
    public abstract int setLayout();
    //
    public abstract void initView(View view);
    public void showProgressDialog(String msg,Context context){
        if(progressDialog==null){
            progressDialog = new Dialog(context, R.style.progress_dialog);
        }
        progressDialog.setContentView(R.layout.progressdialog);
        progressDialog.setCancelable(true);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView meg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
        meg.setText(msg);
        if(!progressDialog.isShowing()){
            progressDialog.show();
        }
    }
    public void hideProgressDialog(){
        if(progressDialog!=null){
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }
    }
}
