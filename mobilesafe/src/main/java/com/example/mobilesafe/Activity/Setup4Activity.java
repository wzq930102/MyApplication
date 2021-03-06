package com.example.mobilesafe.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import utils.ConstantValue;
import utils.SpUtil;
import utils.ToastUtil;

/**
 * Created by wzq930102 on 2017/6/30.
 */
public class Setup4Activity extends Activity{

    private CheckBox cb_box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup4);

        initUI();
    }

    private void initUI() {
        cb_box = (CheckBox)findViewById(R.id.cb_box);
        //1,是否选中状态的回显过程
        boolean open_security = SpUtil.getBoolean(this,ConstantValue.OPEN_SECURITY,false);
        //2,根据状态修改checkbox后续的文字显示
        cb_box.setChecked(open_security);
        if(open_security){
            cb_box.setText("安全设置已开启");
        }else{
            cb_box.setText("安全设置已关闭");
        }
        //3,点击过程中,监听选中状态发生改变过程,
//        cb_box.setChecked(!cb_box.isChecked());
        cb_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //4,isChecked点击后的状态,存储点击后状态
                SpUtil.putBoolean(getApplicationContext(),ConstantValue.OPEN_SECURITY,!cb_box.isChecked());
                //5,根据开启关闭状态,去修改显示的文字
                if(isChecked){
                    cb_box.setText("安全设置已开启");
                }else{
                    cb_box.setText("安全设置已关闭");
                }
            }
        });
    }

    public void nextPage(View view) {
        boolean open_secyrity =  SpUtil.getBoolean(this,ConstantValue.OPEN_SECURITY,false);
        if (open_secyrity){
        Intent intent = new Intent(getApplicationContext(), SetupOverActivity.class);
        startActivity(intent);

        finish();
        SpUtil.putBoolean(this, ConstantValue.SETUP_OVER,true);
    }else {
            ToastUtil.show(getApplicationContext(),"请开启防盗保护");
        }
    }
    public void prePage(View view){
        Intent intent = new Intent(getApplicationContext(), Setup3Activity.class);
        startActivity(intent);

        finish();
        overridePendingTransition(R.anim.pre_in_anim, R.anim.pre_out_anim);
    }
}
