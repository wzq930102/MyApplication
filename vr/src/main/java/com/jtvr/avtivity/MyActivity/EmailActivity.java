package com.jtvr.avtivity.MyActivity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jt.base.R;
import com.jtvr.base.BaseActivity;
import com.jtvr.utils.UIUtils;
import com.jtvr.view.widget.ClearEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jt on 2017/4/14.
 */
public class EmailActivity extends BaseActivity {
    @BindView(R.id.title_title)
    TextView mTitleTitle;
    @BindView(R.id.title_back)
    ImageView mTitleBack;
    @BindView(R.id.title_register)
    TextView mTitleRegister;
    @BindView(R.id.emai_edt)
    ClearEditText mEmaiEdt;
    @BindView(R.id.email_commit)
    TextView mEmailCommit;
    String email;

    @Override
    public void initView() {
        setContentView(R.layout.activity_email);
        ButterKnife.bind(this);
        mTitleTitle.setText("修改邮箱");
        mTitleRegister.setVisibility(View.GONE);
    }

    @Override
    public void initClick() {

    }

    @OnClick({R.id.title_back, R.id.email_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.email_commit:
                email = mEmaiEdt.getText().toString();
                if (email != null) {
                    if (isOK(email)) {
                        Intent i = new Intent();
                        i.putExtra("email",email);
                        setResult(RESULT_OK,i);
                        finish();
                    } else {
                        UIUtils.showToast("请输入正确的邮箱格式");
                    }
                } else {
                    UIUtils.showToast("请输入邮箱");
                }

                break;
        }
    }
    public boolean isOK(String s) {
        String regEx = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(s);
        boolean b = matcher.matches();
        return b;
    }
}
