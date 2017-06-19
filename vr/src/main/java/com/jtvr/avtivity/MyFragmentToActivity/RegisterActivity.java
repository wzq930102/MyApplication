package com.jtvr.avtivity.MyFragmentToActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jt.base.R;
import com.jtvr.base.BaseActivity;
import com.jtvr.http.RequestEnum;
import com.jtvr.http.VrHttp;
import com.jtvr.model.CodeBean;
import com.jtvr.model.RegisterBean;
import com.jtvr.utils.CountDownTimerUtils;
import com.jtvr.utils.UIUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.title_title)
    TextView titleTitle;

    @BindView(R.id.title_register)
    TextView titleRegister;
    @BindView(R.id.register_edit_username)
    EditText registerEditUsername;
    @BindView(R.id.register_edit_code)
    EditText registerEditCode;
    @BindView(R.id.register_getCode)
    TextView registerGetCode;
    @BindView(R.id.register_edit_password_one)
    EditText registerEditPasswordOne;
    @BindView(R.id.register_edit_password_two)
    EditText registerEditPasswordTwo;
    @BindView(R.id.register_text2)
    TextView registerText2;
    @BindView(R.id.register_input)
    Button registerInput;

    private CountDownTimer mCountDownTimer;

    @Override

    public void initView() {
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        titleTitle.setText("注册");
        titleRegister.setVisibility(View.GONE);
        registerText2.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        mCountDownTimer = new CountDownTimerUtils(registerGetCode, 60000, 1000);
    }

    @Override
    public void initClick() {

    }

    private void getCode() {
        if (UserName()) {
            if (isPhone(registerEditUsername.getText().toString())) {

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
                }, RequestEnum.CODE.addIntent(registerEditUsername.getText().toString(), "1"));
            } else {
                UIUtils.showToast("请输入正确的手机号");
            }
        } else {
            UIUtils.showToast("手机号不能为空");
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("result", "success");
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }

    private void register() {
        if (isPhone(registerEditUsername.getText().toString())) {
            if (Code()) {
                if (PasswordOne() && PasswordTwo()) {
                    if (registerEditPasswordOne.getText().toString().equals(registerEditPasswordTwo.getText().toString())) {
                        VrHttp.getInstance().requestForResult(RequestEnum.REGISTER, new VrHttp.OnGetDataResult() {
                            @Override
                            public void Response(String response, int id) {
                                Gson gson = new Gson();
                                RegisterBean registerBean = gson.fromJson(response, RegisterBean.class);
                                if ("99".equals(registerBean.getStatus())) {
                                    UIUtils.showToast("注册成功");
                                    Intent intent = new Intent();
                                    intent.putExtra("result", "success");
                                    setResult(RESULT_OK, intent);
                                    finish();
                                } else {
                                    UIUtils.showToast(registerBean.getErrmsg());
                                }
                            }

                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }
                        }, RequestEnum.REGISTER.addIntent(registerEditUsername.getText().toString(), registerEditCode.getText(), registerEditPasswordOne.getText()));
                    } else {
                        UIUtils.showToast("两次密码不同");
                    }
                } else {
                    UIUtils.showToast("密码不能为空");
                }
            } else {
                UIUtils.showToast("验证码不能为空");
            }
        } else {
            UIUtils.showToast("请输入正确的手机号");
        }

    }

    @OnClick({R.id.title_back, R.id.register_getCode, R.id.register_text2, R.id.register_input})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                Intent intent = new Intent();
                intent.putExtra("result", "success");
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.register_getCode:
                getCode();
                break;
            case R.id.register_text2:
                break;
            case R.id.register_input:
                register();
                break;
        }
    }


    private boolean isPhone(String value) {
        String regExp = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(14[0,0-9])|(17[0,5-9]))\\d{8}$";

        Pattern p = Pattern.compile(regExp);

        Matcher m = p.matcher(value);
        return m.find();
    }

    private boolean UserName() {//判断账号是否为空
        if ("".equals(registerEditUsername.getText().toString())) {
            return false;
        } else {
            return true;
        }
    }

    private boolean Code() {//判断验证码是否为空
        if ("".equals(registerEditCode.getText().toString())) {
            return false;
        } else {
            return true;
        }
    }

    private boolean PasswordOne() {//判断密码1是否为空
        if ("".equals(registerEditPasswordOne.getText().toString())) {
            return false;
        } else {
            return true;
        }
    }

    private boolean PasswordTwo() {//判断密码2是否为空
        if ("".equals(registerEditPasswordTwo.getText().toString())) {
            return false;
        } else {
            return true;
        }
    }
}
