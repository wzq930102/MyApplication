package com.jtvr.avtivity.MyFragmentToActivity;


import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.jt.base.R;
import com.jtvr.base.BaseActivity;
import com.jtvr.utils.DataCleanManager;
import com.jtvr.utils.SPUtils;
import com.jtvr.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {
    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.title_register)
    TextView titleRegister;
    @BindView(R.id.setting_switch)
    SwitchCompat settingSwitch;
    @BindView(R.id.setting_num)
    TextView settingNum;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                DataCleanManager.clearAllCache(SettingActivity.this);
                getCacheSize();
                UIUtils.showToast("缓存清理完毕");
            }
        }
    };

    @Override
    public void initView() {
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        titleTitle.setText("系统设置");
        titleRegister.setVisibility(View.GONE);
        Boolean b = (Boolean) SPUtils.get(this, "keepwifi", false);
        if (b) {
            settingSwitch.setChecked(true);
        } else {
            settingSwitch.setChecked(false);
        }
        getCacheSize();

    }

    private void getCacheSize() {
        try {
            settingNum.setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initClick() {
        settingSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SPUtils.put(SettingActivity.this, "keepwifi", true);
                } else {
                    SPUtils.put(SettingActivity.this, "keepwifi", false);
                }
            }
        });
    }

    @OnClick({R.id.title_back, R.id.setting_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.setting_delete:

                handler.sendEmptyMessageDelayed(1, 2000);
                break;
        }
    }


}
