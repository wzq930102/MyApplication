package com.example.helloworld;

import android.app.Activity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.helloworld.R.id.t1;
import static com.example.helloworld.R.id.t2;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case t1:    //按钮1的响应事件
                        //设置TextView2可见
                        v.setVisibility(View.GONE);
                        break;

                    case t2:   //按钮2的响应事件
                        //设置TextView2不可见
                        v.setVisibility(View.GONE);
                        break;
                }
            }


        };
    }
}