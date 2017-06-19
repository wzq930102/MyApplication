package com.example.mytext;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

        public class Text1 extends Activity {
            /**
             * TextView2
             */
            private TextView TV2 = null;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_text1);

                TV2 = (TextView) findViewById(R.id.TV2);
                findViewById(R.id.tn1).setOnClickListener(listener);
                findViewById(R.id.tn2).setOnClickListener(listener);
                findViewById(R.id.tn3).setOnClickListener(listener);
            }

            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.tn1:    //按钮1的响应事件
                            //设置TextView2可见
                            TV2.setVisibility(View.VISIBLE);
                            break;

                        case R.id.tn2:   //按钮2的响应事件
                            //设置TextView2不可见
                            TV2.setVisibility(View.INVISIBLE);
                            break;

                        case R.id.tn3:    //按钮3的响应事件
                            //设置TextView2隐藏
                            TV2.setVisibility(View.GONE);
                            break;

                        default:
                            break;
                    }
                }
            };
        }