package com.example.mobilesafe.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by wzq930102 on 2017/6/26.
 */

public class ToastUtil {
    //打印吐司

    /**
     *
     * @param ctx   上下文环境
     * @param msg   打印文本内容
     */
    public static void show(Context ctx,String msg){
        Toast.makeText(ctx,msg,0).show();
    }
}
