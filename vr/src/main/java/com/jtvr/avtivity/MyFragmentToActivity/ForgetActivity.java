package com.jtvr.avtivity.MyFragmentToActivity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jt.base.R;
import com.jtvr.base.BaseActivity;
import com.jtvr.http.RequestEnum;
import com.jtvr.http.VrHttp;
import com.jtvr.model.CodeBean;
import com.jtvr.model.ForgetBean;
import com.jtvr.utils.CountDownTimerUtils;
import com.jtvr.utils.UIUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class ForgetActivity extends BaseActivity {
    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_register)
    TextView titleRegister;
    @BindView(R.id.forget_edit_username)
    EditText forgetEditUsername;//手机号
    @BindView(R.id.forget_edit_code)
    EditText forgetEditCode;//验证码
    @BindView(R.id.forget_getCode)
    TextView forgetGetCode;//获取验证码
    @BindView(R.id.forget_edit_password)
    EditText forgetEditPassword;//密码
    @BindView(R.id.forget_input)
    Button forgetInput;//提交
    private CountDownTimer mCountDownTimer;

    @Override
    public void initView() {
        setContentView(R.layout.activity_forget);
        ButterKnife.bind(this);
        titleRegister.setVisibility(View.GONE);
        titleTitle.setText("找回密码");
        mCountDownTimer = new CountDownTimerUtils(forgetGetCode, 60000, 1000);

    }

    @Override
    public void initClick() {

    }

    private void getCode() {//获取验证码
        VrHttp.getInstance().requestForResult(RequestEnum.CODE, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {
                Gson gson = new Gson();
                CodeBean codeBean = gson.fromJson(response, CodeBean.class);
                if ("49".equals(codeBean.getStatus())) {
                    UIUtils.showToast(codeBean.getErrmsg());
                } else if ("99".equals(codeBean.getStatus())) {
                    mCountDownTimer.start();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {

            }
        }, RequestEnum.CODE.addIntent(forgetEditUsername.getText().toString(),"2"));
    }

    private void forget() {//修改密码
        VrHttp.getInstance().requestForResult(RequestEnum.FORGET, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {
                Gson gson = new Gson();
                ForgetBean forgetBean = gson.fromJson(response, ForgetBean.class);
                if ("99".equals(forgetBean.getStatus())) {
                    UIUtils.showToast("修改成功");
                    Intent intent = new Intent();
                    intent.putExtra("result", "success");
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    UIUtils.showToast(forgetBean.getErrmsg());
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {

            }
        }, RequestEnum.FORGET.addIntent(forgetEditUsername.getText(), forgetEditCode.getText(), forgetEditPassword.getText()));
    }


    private boolean isPhone(String value) {
        String regExp = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(14[0,0-9])|(17[0,5-9]))\\d{8}$";

        Pattern p = Pattern.compile(regExp);

        Matcher m = p.matcher(value);
        return m.find();
    }

    private boolean UserName() {//判断账号是否为空
        if ("".equals(forgetEditUsername.getText().toString())) {
            return false;
        } else {
            return true;
        }
    }

    private boolean Password() {//判断验证码是否为空
        if ("".equals(forgetEditCode.getText().toString())) {
            return false;
        } else {
            return true;
        }
    }

    @OnClick({R.id.title_back, R.id.forget_input, R.id.forget_getCode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                Intent intent = new Intent();
                intent.putExtra("result", "success");
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.forget_input:
                if (UserName()) {
                    if (isPhone(forgetEditUsername.getText().toString())) {

                        if (Password()) {
                            if ("".equals(forgetEditPassword.getText().toString())) {
                                UIUtils.showToast("密码不能为空");
                            } else {
                                forget();
                            }
                        } else {
                            UIUtils.showToast("验证码不能为空");
                        }
                    } else {
                        UIUtils.showToast("请输入正确的手机号");
                    }
                } else {
                    UIUtils.showToast("手机号不能为空");
                }

                break;
            case R.id.forget_getCode:
                if (UserName()) {
                    if (isPhone(forgetEditUsername.getText().toString())) {
                        getCode();
                    } else {
                        UIUtils.showToast("请输入正确的手机号");
                    }
                } else {
                    UIUtils.showToast("手机号不能为空");
                }

                break;

        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("result", "success");
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }
}
