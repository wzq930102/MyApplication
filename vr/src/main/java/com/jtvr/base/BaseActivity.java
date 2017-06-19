package com.jtvr.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.jt.base.R;
import com.jtvr.utils.StatusBarUtil;

/**
 * Created by jt on 2017/2/24.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private Dialog progressDialog;
    public Bundle savedInstanceState;
    protected Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        StatusBarUtil.setImgTransparent(this);
        initView();
        initClick();
    }

    /**
     * 初始化布局控件
     */
    public abstract void initView();

    /**
     * 点击事件
     */
    public abstract void initClick();

    public void showToast(String msg, Context context) {
        if (null == toast) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }
    public void showProgressDialog(Context context,String msg){
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
