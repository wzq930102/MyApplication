package com.example.mobilesafe.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by wzq930102 on 2017/6/29.
 */
public class TextActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("TextActivity");
        setContentView(textView);
    }
}
