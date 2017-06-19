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
 * Created by jt on 2017/1/16.
 */
public class NickNameActivity extends BaseActivity {
    @BindView(R.id.title_title)
    TextView mTitleTitle;
    @BindView(R.id.title_back)
    ImageView mTitleBack;
    @BindView(R.id.title_register)
    TextView mTitleRegister;
    @BindView(R.id.nickname_edt)
    ClearEditText mNicknameEdt;
    @BindView(R.id.nickname_commit)
    TextView mNicknameCommit;
    String nickName;

    @Override
    public void initView() {
        setContentView(R.layout.activity_nickname);
        ButterKnife.bind(this);
        mTitleTitle.setText("修改昵称");
        mTitleRegister.setVisibility(View.GONE);
    }
    @Override
    public void initClick() {
    }
    public boolean isOK(String s) {
        String regEx = "^[\u4e00-\u9fa5a-zA-Z][\u4e00-\u9fa5a-zA-Z0-9_]{1,15}$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(s);
        boolean b = matcher.matches();
        return b;
    }
    @OnClick({R.id.title_back, R.id.nickname_edt, R.id.nickname_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.nickname_edt:
                break;
            case R.id.nickname_commit:
                nickName = mNicknameEdt.getText().toString();
                if (nickName != null) {
                    if (isOK(nickName)) {
                        Intent i = new Intent();
                        i.putExtra("nickname",nickName);
                        setResult(RESULT_OK,i);
                        finish();
                    } else {
                        UIUtils.showToast("请输入正确的昵称格式");
                    }
                } else {
                    UIUtils.showToast("请输入昵称");
                }
                break;
        }
    }

}
