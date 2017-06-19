package com.jtvr.avtivity.MyFragmentToActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jt.base.R;
import com.jtvr.app.VRApplication;
import com.jtvr.base.BaseActivity;
import com.jtvr.http.RequestEnum;
import com.jtvr.http.VrHttp;
import com.jtvr.model.LoginBean;
import com.jtvr.utils.SPUtils;
import com.jtvr.utils.SharedPreferencesUtils;
import com.jtvr.utils.UIUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_register)
    TextView titleRegister;
    @BindView(R.id.login_username)
    ImageView loginUsername;
    @BindView(R.id.login_password)
    ImageView loginPassword;
    @BindView(R.id.login_input)
    Button loginInput;
    @BindView(R.id.login_edit_username)
    EditText loginEditUsername;
    @BindView(R.id.login_edit_password)
    EditText loginEditPassword;
    private LoginBean loginBean;

    @Override
    public void initView() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        if (!"".equals(SPUtils.get(this, "name", ""))) {
            loginEditUsername.setText((String) SPUtils.get(this, "name", ""));
        }
    }

    @Override
    public void initClick() {

    }

    @OnClick({R.id.title_title, R.id.title_back, R.id.title_register, R.id.login_username, R.id.login_password, R.id.login_input, R.id.login_forget})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_title:
                break;
            case R.id.title_back:
                finish();
                break;
            case R.id.title_register:
                startActivityForResult(new Intent(this, RegisterActivity.class), 3);
                break;
            case R.id.login_username:
                break;
            case R.id.login_password:
                break;
            case R.id.login_input:
                Login();
                break;
            case R.id.login_forget:
                startActivityForResult(new Intent(this, ForgetActivity.class), 3);
                break;
        }
    }

    private void Login() {
        if (UserName() && Password()) {
            if (isPhone(loginEditUsername.getText().toString())) {
                getHttp();
            } else {
                UIUtils.showToast("请输入正确的手机号");
            }
        } else {
            UIUtils.showToast("账号或密码不能为空");
        }
    }

    private boolean isPhone(String value) {
        String regExp = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(14[0,0-9])|(17[0,5-9]))\\d{8}$";

        Pattern p = Pattern.compile(regExp);

        Matcher m = p.matcher(value);
        return m.find();
    }

    private boolean UserName() {//判断账号是否为手机号
        if ("".equals(loginEditUsername.getText().toString())) {
            return false;
        } else {
            return true;
        }
    }

    private boolean Password() {//判断密码是否为空
        if ("".equals(loginEditPassword.getText().toString())) {
            return false;
        } else {
            return true;
        }
    }

    private void getHttp() {
        VrHttp.getInstance().requestForResult(RequestEnum.LOGIN, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {
                Gson gson = new Gson();
                loginBean = gson.fromJson(response, LoginBean.class);
                if ("99".equals(loginBean.getStatus())) {
                    SPUtils.put(LoginActivity.this, "name", loginBean.getBusiness().getName());
                    SharedPreferencesUtils.put(LoginActivity.this, "name", loginBean.getBusiness().getName());
                    SharedPreferencesUtils.put(LoginActivity.this, "nickName", loginBean.getBusiness().getNick());
                    if (loginBean.getBusiness().getImg() != null) {
                        SharedPreferencesUtils.put(LoginActivity.this, "img", loginBean.getBusiness().getImg());
                    } else {
                        SharedPreferencesUtils.put(LoginActivity.this, "img", "");
                    }
                    SharedPreferencesUtils.put(LoginActivity.this, "userCode", loginBean.getBusiness().getUserCode());
                    SharedPreferencesUtils.put(LoginActivity.this, "tokens", loginBean.getBusiness().getTokens());
                    SharedPreferencesUtils.put(LoginActivity.this, "token", loginBean.getBusiness().getToken());
                    SharedPreferencesUtils.put(LoginActivity.this, "fires", loginBean.getBusiness().getFires());
                    SharedPreferencesUtils.put(LoginActivity.this, "vip", loginBean.getBusiness().getVip());
                    SharedPreferencesUtils.put(LoginActivity.this, "isvip", loginBean.getBusiness().getIsvideo());
                    EventBus.getDefault().post(loginBean.getStatus());
                    finish();
                } else {
                    UIUtils.showToast("请输入正确的账号或密码");
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {

            }
        }, RequestEnum.LOGIN.addIntent(loginEditUsername.getText().toString(), loginEditPassword.getText().toString(), "", VRApplication.getIMEI()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3 && resultCode == RESULT_OK && data != null) {
            String result = data.getStringExtra("result");
            if ("success".equals(result)) {
                loginEditPassword.setText("");
            }
        }
    }
}
