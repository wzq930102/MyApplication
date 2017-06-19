package com.jtvr.avtivity.MyFragmentToActivity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jt.base.R;
import com.jtvr.base.BaseActivity;
import com.jtvr.utils.UtilsApp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity {
    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.title_register)
    TextView titleRegister;
    @BindView(R.id.about_version)
    TextView aboutVersion;
    @BindView(R.id.about_parent)
    RelativeLayout aboutParent;
    @BindView(R.id.about_parent2)
    RelativeLayout aboutParent2;
    @BindView(R.id.about_parent3)
    RelativeLayout aboutParent3;
    @BindView(R.id.about_phone)
    TextView aboutPhone;
    @BindView(R.id.about_net)
    TextView aboutNet;
    @Override
    public void initView() {
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        titleTitle.setText("关于我们");
        titleRegister.setVisibility(View.GONE);
        aboutVersion.setText("版本号：" + UtilsApp.getIntance().getVersion());

    }

    @Override
    public void initClick() {
    }

    @OnClick({R.id.title_back, R.id.about_parent, R.id.about_parent3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.about_parent:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + aboutPhone.getText().toString()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.about_parent3:
                Uri uri = Uri.parse("http://"+aboutNet.getText().toString());
                Intent intent2 = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent2);
                break;
        }
    }
}
