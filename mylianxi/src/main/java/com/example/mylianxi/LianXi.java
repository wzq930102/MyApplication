package com.example.mylianxi;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class LianXi extends AppCompatActivity {
    private EditText et1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lian_xi);
        et1=(EditText)findViewById(R.id.et);
        Button mb=(Button)findViewById(R.id.mb);
        mb.setOnClickListener(new MbListener());
    }
    class MbListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
// TODO Auto-generated method stub
            String val = et1.getText().toString();
//生成一个Intent对象
            Intent intent = new Intent();
//设置传递的参数
            intent.putExtra("val", val);
//从LianXi Activity跳转到 LianXi2Activity
            intent.setClass(LianXi.this, LianXi2.class);
//启动intent的Activity

            LianXi.this.startActivity(intent);
        }
    }
/*
@Override
public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
getMenuInflater().inflate(R.menu.main, menu);
return true;
}
*/

}
