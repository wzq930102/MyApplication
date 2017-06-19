package com.jtvr.avtivity.MyActivity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jt.base.R;
import com.jtvr.base.BaseActivity;
import com.jtvr.utils.UIUtils;
import com.jtvr.view.widget.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jt on 2017/4/14.
 */
public class ProfessionActivity extends BaseActivity {
    @BindView(R.id.title_title)
    TextView mTitleTitle;
    @BindView(R.id.title_back)
    ImageView mTitleBack;
    @BindView(R.id.title_register)
    TextView mTitleRegister;
    @BindView(R.id.profession_edt)
    ClearEditText mProfessionEdt;
    @BindView(R.id.profession_commit)
    TextView mProfessionCommit;
    String profession;

    @Override
    public void initView() {
        setContentView(R.layout.activity_profession);
        ButterKnife.bind(this);
        mTitleTitle.setText("修改职业");
        mTitleRegister.setVisibility(View.GONE);
    }

    @Override
    public void initClick() {

    }
    @OnClick({R.id.title_back, R.id.profession_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.profession_commit:
                profession = mProfessionEdt.getText().toString();
                if (profession == null||"".equals(profession)) {
                    UIUtils.showToast("请输入职业");
                } else {
                    Intent i = new Intent();
                    i.putExtra("profession",profession);
                    setResult(RESULT_OK,i);
                    finish();
                }
                break;
        }
    }
}
