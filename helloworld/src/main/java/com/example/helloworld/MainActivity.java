package com.example.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.helloworld.R.id.t1;
import static com.example.helloworld.R.id.t2;

public class MainActivity extends Activity {

    //    private Button tn_0, tn_1, tn_2, tn_3, tn_4;//各个按钮
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button tn_0 = (Button) findViewById(R.id.tn_0);
        Button tn_1 = (Button) findViewById(R.id.tn_1);

        tn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);

            }
        });

        tn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main4Activity.class);
                startActivity(intent);

            }
        });
//        View.OnClickListener listener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()) {
//                    case R.id.tn_0:    //按钮1的响应事件
//                        //设置TextView2可见
//                        v.setVisibility(View.GONE);
//                        break;
//
//                    case R.id.tn_1:   //按钮2的响应事件
//                        //设置TextView2不可见
//                        v.setVisibility(View.GONE);
//                        break;
//
//                }
//            }
//        };
    }
}
//        //获得各个按钮
//        tn_0 = (Button) findViewById(R.id.tn_0);
//        tn_1 = (Button) findViewById(R.id.tn_1);
//        tn_2 = (Button) findViewById(R.id.tn_2);
//        tn_3 = (Button) findViewById(R.id.tn_3);
//        tn_4 = (Button) findViewById(R.id.tn_4);
//        //设置时间监听器
//        tn_0.setOnClickListener(this);
//        tn_1.setOnClickListener(this);
//        tn_2.setOnClickListener(this);
//        tn_3.setOnClickListener(this);
//        tn_4.setOnClickListener(this);

//    }
//}
