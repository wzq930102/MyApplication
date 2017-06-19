package com.example.mylianxi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

public class LianXi2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lian_xi2);
        // 通过ID获取TextView对象
        TextView mt=(TextView)findViewById(R.id.mt);
        // 接收前一屏穿过来的Intent
        Intent intent=getIntent();
        // 取其中的值
        String value = intent.getStringExtra("val");
        // 将值设置到TextView中显示
        mt.setText(value);
    }
}
