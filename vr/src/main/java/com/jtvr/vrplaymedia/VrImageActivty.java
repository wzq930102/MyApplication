package com.jtvr.vrplaymedia;

import android.content.res.Configuration;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jt.base.R;
import com.jtvr.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jt on 2017/3/29.
 */
public class VrImageActivty extends BaseActivity {
    @BindView(R.id.vrimage_back)
    ImageView mVrimageBack;
    @BindView(R.id.vriamge_iv)
    ImageView mVriamgeIv;
    @Override
    public void initView() {
        setContentView(R.layout.activtiy_vrimage);
        ButterKnife.bind(this);
        Glide.with(VrImageActivty.this).load("").into(mVriamgeIv);
        Glide.with(VrImageActivty.this).load(R.drawable.gif).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mVriamgeIv);
    }
    @Override
    public void initClick() {
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){//横屏
            setResult(1);
            finish();
        }else{//竖屏
        }
    }
}
