package com.jtvr.avtivity.MyFragmentToActivity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatButton;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jt.base.R;
import com.jtvr.adapter.SignAdapter;
import com.jtvr.base.BaseActivity;
import com.jtvr.fragment.SignDialogFragment;
import com.jtvr.http.RequestEnum;
import com.jtvr.http.VrHttp;
import com.jtvr.model.SignBean;
import com.jtvr.model.SignEntity;
import com.jtvr.utils.ResolutionUtil;
import com.jtvr.utils.SharedPreferencesUtils;
import com.jtvr.view.SignView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class SignActivity extends BaseActivity {
    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.title_register)
    TextView titleRegister;
    @BindView(R.id.activity_main_tv_main_day)
    TextView tvSignDay;
    @BindView(R.id.activity_main_tv_score)
    TextView tvScore;
    @BindView(R.id.activity_main_btn_sign)
    AppCompatButton btnSign;
    @BindView(R.id.activity_main_tv_year)
    TextView tvYear;
    @BindView(R.id.activity_main_tv_month)
    TextView tvMonth;
    @BindView(R.id.activity_main_cv)
    SignView signView;
    @BindView(R.id.activity_main_ll_date)
    LinearLayout activityMainLlDate;
    private List<SignEntity> data;
    private String[] ss;
    private int serialSign;//连续签到天数
    private int accruedFile;//累计获取的火种
    private String fire;//本次的签到获取的火种
    private String signCode;

    @Override
    public void initView() {
        setContentView(R.layout.activity_sign);
        ButterKnife.bind(this);
        titleRegister.setVisibility(View.GONE);
        titleTitle.setText("每日签到");
        String name = (String) SharedPreferencesUtils.get(this, "name", "");
        if ("".equals(name)) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {
            initViews();
            initHttp();
        }


    }

    private void initPutHttp() {
        VrHttp.getInstance().requestForResult(RequestEnum.PUTSIGN, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {
                Log.e("Response: ", response);
            }

            @Override
            public void onError(Call call, Exception e, int id) {

            }
        }, RequestEnum.PUTSIGN.addIntent(fire, signCode));
    }

    private void initHttp() {
        VrHttp.getInstance().requestForResult(RequestEnum.SIGN, new VrHttp.OnGetDataResult() {
            @Override
            public void Response(String response, int id) {
                Gson gson = new Gson();
                SignBean signBean = gson.fromJson(response, SignBean.class);
                if ("99".equals(signBean.getStatus())) {
                    String signDay = signBean.getBusiness().getSugnDay();
                    if (!"".equals(signDay)) {
                        ss = new String[signDay.split(",").length];
                        ss = signDay.split(",");
                    }
                    serialSign = signBean.getBusiness().getSerialSign();
                    accruedFile = signBean.getBusiness().getAccruedFile();
                    fire = signBean.getBusiness().getFire();
                    signCode = signBean.getBusiness().getSignCode();
                    onReady();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {

            }
        }, RequestEnum.SIGN.addIntent());
    }

    @Override
    public void initClick() {

    }

    private void initViews() {

        if (signView != null) {
            signView.setOnTodayClickListener(onTodayClickListener);
        }
        if (btnSign != null) {
            //noinspection deprecation
            btnSign.setSupportBackgroundTintList(getResources().getColorStateList(R.color.color_user_button_submit));
            btnSign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSign();
                }
            });
        }

        //---------------------------------分辨率适配----------------------------------
        ResolutionUtil resolutionUtil = ResolutionUtil.getInstance();

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        layoutParams.topMargin = resolutionUtil.formatVertical(40);
        tvSignDay.setLayoutParams(layoutParams);
        tvSignDay.setTextSize(TypedValue.COMPLEX_UNIT_PX, resolutionUtil.formatVertical(42));

        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        layoutParams.topMargin = resolutionUtil.formatVertical(40);
        tvScore.setLayoutParams(layoutParams);
        tvScore.setTextSize(TypedValue.COMPLEX_UNIT_PX, resolutionUtil.formatVertical(95));

        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, resolutionUtil.formatVertical(130));
        layoutParams.topMargin = resolutionUtil.formatVertical(54);
        View llDate = findViewById(R.id.activity_main_ll_date);
        if (llDate != null) {
            llDate.setLayoutParams(layoutParams);
        }

        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        layoutParams.leftMargin = resolutionUtil.formatHorizontal(43);
        tvYear.setLayoutParams(layoutParams);
        tvYear.setTextSize(TypedValue.COMPLEX_UNIT_PX, resolutionUtil.formatVertical(43));

        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        layoutParams.leftMargin = resolutionUtil.formatHorizontal(44);
        tvMonth.setLayoutParams(layoutParams);
        tvMonth.setTextSize(TypedValue.COMPLEX_UNIT_PX, resolutionUtil.formatVertical(43));

        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, resolutionUtil.formatVertical(818));
        signView.setLayoutParams(layoutParams);

        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, resolutionUtil.formatVertical(142));
        layoutParams.topMargin = resolutionUtil.formatVertical(111);
        layoutParams.leftMargin = layoutParams.rightMargin = resolutionUtil.formatHorizontal(42);
        if (btnSign != null) {
            btnSign.setLayoutParams(layoutParams);
            btnSign.setTextSize(TypedValue.COMPLEX_UNIT_PX, resolutionUtil.formatVertical(54));
        }
    }

    private void onReady() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);

        tvSignDay.setText(Html.fromHtml(String.format(getString(R.string.you_have_sign), "#999999", "#f44336", serialSign)));
        tvScore.setText(String.valueOf(accruedFile));
        tvYear.setText(String.valueOf(calendar.get(Calendar.YEAR)));
        tvMonth.setText(getResources().getStringArray(R.array.month_array)[month]);

        Calendar calendarToday = Calendar.getInstance();
        int dayOfMonthToday = calendarToday.get(Calendar.DAY_OF_MONTH);//今天的日期
        //setDayType 0已签到  1未签到 2代签到
        data = new ArrayList<>();
        for (int i = 0; i < 31; i++) {
            SignEntity signEntity = new SignEntity();
            if (dayOfMonthToday == i + 1) {
                signEntity.setDayType(2);
            } else {
                signEntity.setDayType(1);
            }
            data.add(signEntity);
        }
        if (ss != null && ss.length > 0) {
            for (int i = 0; i < ss.length; i++) {
                if (!"".equals(ss[i])) {
                    SignEntity signEntity = new SignEntity();
                    signEntity.setDayType(0);
                    data.set(Integer.parseInt(ss[i].toString()) - 1, signEntity);
                }
            }
        }
        if (data.get(dayOfMonthToday - 1).getDayType() == 0) {
            btnSign.setEnabled(false);
            btnSign.setText(R.string.have_signed);
        }

        SignAdapter signAdapter = new SignAdapter(data);

        signView.setAdapter(signAdapter);
    }

    private void onSign() {//点击签到后弹出来对话框
        FragmentManager fragmentManager = getSupportFragmentManager();
        SignDialogFragment signDialogFragment = SignDialogFragment.newInstance(Integer.parseInt(fire));
        signDialogFragment.setOnConfirmListener(onConfirmListener);
        signDialogFragment.show(fragmentManager, SignDialogFragment.TAG);
    }

    private void signToday() {
        data.get(signView.getDayOfMonthToday() - 1).setDayType(SignView.DayType.SIGNED.getValue());
        signView.notifyDataSetChanged();
        btnSign.setEnabled(false);
        btnSign.setText(R.string.have_signed);
        int score = Integer.valueOf((String) tvScore.getText());
        tvScore.setText((score + Integer.parseInt(fire)) + "");
        tvSignDay.setText(Html.fromHtml(String.format(getString(R.string.you_have_sign), "#999999", "#f44336", serialSign + 1)));
        initPutHttp();
    }

    private SignView.OnTodayClickListener onTodayClickListener = new SignView.OnTodayClickListener() {
        @Override
        public void onTodayClick() {
// onSign();
        }
    };

    private SignDialogFragment.OnConfirmListener onConfirmListener = new SignDialogFragment.OnConfirmListener() {
        @Override
        public void onConfirm() {
            signToday();
        }
    };

    @OnClick({R.id.title_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;

        }
    }
}
