package com.example.jisuanqi;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7,
            btn_8, btn_9, btn_0, btn_jia, btn_jian, btn_chengyi, btn_dengyu,
            btn_chuyi, btn_baifenhao, btn_qingchu, btn_tuihui, btn_dian;//各个按钮

    public int flag = 0;//为判断是加减乘除四种运算中的哪一种设定的标志位
    private String text1 = "0",  //获得输入的第一个数
                   text2 = "0";  //获得输入的第二个数
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private TextView result;
    private String num1;
    private String num2;
    private String x = "\\+";
    private boolean isCompleted = false;//没有算完


            protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("计算器");
        //获得各个控件
        result = (TextView) findViewById(R.id.textView);
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_jia = (Button) findViewById(R.id.btn_jia);
        btn_jian = (Button) findViewById(R.id.btn_jian);
        btn_chengyi = (Button) findViewById(R.id.btn_chengyi);
        btn_chuyi = (Button) findViewById(R.id.btn_chuyi);
        btn_dengyu = (Button) findViewById(R.id.btn_dengyu);
        btn_dian = (Button) findViewById(R.id.btn_dian);
        btn_baifenhao = (Button) findViewById(R.id.btn_baifenhao);
        btn_qingchu = (Button) findViewById(R.id.btn_qingchu);
        // 设置监听器
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_qingchu.setOnClickListener(this);
        btn_jia.setOnClickListener(this);
        btn_jian.setOnClickListener(this);
        btn_chengyi.setOnClickListener(this);
        btn_chuyi.setOnClickListener(this);
        btn_dian.setOnClickListener(this);
        btn_dengyu.setOnClickListener(this);
        btn_baifenhao.setOnClickListener(this);
        btn_tuihui.setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        if (isCompleted) {
            result.setText("");
            isCompleted = false;
        }

        switch (arg0.getId()) {
            case R.id.btn_0:
                result.setText(result.getText() + "0");
                break;
            case R.id.btn_1:
                result.setText(result.getText() + "1");
                break;
            case R.id.btn_2:
                result.setText(result.getText() + "2");
                break;
            case R.id.btn_3:
                result.setText(result.getText() + "3");
                break;
            case R.id.btn_4:
                result.setText(result.getText() + "4");
                break;
            case R.id.btn_5:
                result.setText(result.getText() + "5");
                break;
            case R.id.btn_6:
                result.setText(result.getText() + "6");
                break;
            case R.id.btn_7:
                result.setText(result.getText() + "7");
                break;
            case R.id.btn_8:
                result.setText(result.getText() + "8");
                break;
            case R.id.btn_9:
                result.setText(result.getText() + "9");
                break;
            case R.id.btn_dian:
                result.setText(result.getText() + ".");
                break;
            case R.id.btn_jia:
                result.setText(result.getText() + "+");
                x = "\\+";
                break;
            case R.id.btn_jian:
                result.setText(result.getText() + "-");
                x = "\\-";
                break;
            case R.id.btn_chengyi:
                result.setText(result.getText() + "*");
                x = "\\*";
                break;
            case R.id.btn_chuyi:
                result.setText(result.getText() + "/");
                x = "/";
                break;
            case R.id.btn_dengyu:
                //等于按钮里面所有的东西
                if (!isCompleted) {
                    char[] ar = x.toCharArray();
                    char b = ar[ar.length - 1];
                    String value = result.getText().toString();
                    String[] name = value.split(x);
                    switch (b) {
                        case '+':
                            int i2 = Integer.parseInt(name[0]) + Integer.parseInt(name[1]);
                            result.setText(result.getText() + "=" + i2);
                            break;
                        case '-':
                            int i3 = Integer.parseInt(name[0]) - Integer.parseInt(name[1]);
                            result.setText(result.getText() + "=" + i3);
                            break;
                        case '*':
                            int i4 = Integer.parseInt(name[0]) * Integer.parseInt(name[1]);
                            result.setText(result.getText() + "=" + i4);
                            break;
                        case '/':
                            int i5 = Integer.parseInt(name[0]) / Integer.parseInt(name[1]);
                            result.setText(result.getText() + "=" + i5);
                            break;
                    }
                }
//                String value =result.getText().toString();
//                String[] names = value.split("\\+");
//                int i2 = Integer.parseInt(names[0])+Integer.parseInt(names[1]);
//                result.setText(result.getText() + "=" + i2);
                isCompleted = true;//表示算完了
                break;

            case R.id.btn_qingchu:
                result.setText("");
                break;
            case R.id.btn_tuihui:
                String string = result.getText().toString();
                String substring = string.substring(0, string.length() - 1);
                result.setText(substring);
                break;
//            char[]ar=x.toCharArray();
//            char b=ar[ar.length-1];
            default:
                break;
        }

    }


}
