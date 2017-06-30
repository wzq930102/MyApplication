package com.example.mobilesafe.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by wzq930102 on 2017/6/30.
 */
public class Setup3Activity extends Activity{

    private EditText et_phone_number;
    private Button bt_select_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup3);

        initUI();
    }

    private void initUI() {
        //显示电话号码的输入框
        et_phone_number = (EditText)findViewById(R.id.et_phone_number);
        //获取联系人电话号码回显过程

        //点击选择联系人的对话框
        bt_select_number = (Button) findViewById(R.id.bt_select_number);
        bt_select_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ContactListActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //1,返回到当前界面的时候,接受结果的方法
        //2,将特殊字符过滤(中划线转换成空字符串)
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void nextPage(View view) {
        Intent intent = new Intent(getApplicationContext(), Setup4Activity.class);
        startActivity(intent);

        finish();
    }
    public void prePage(View view){
        Intent intent = new Intent(getApplicationContext(), Setup2Activity.class);
        startActivity(intent);

        finish();
    }
}
