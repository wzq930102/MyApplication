package com.jtvr.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/10/27.
 */
public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "vr.db";
    public static final int DB_VERSION = 1;

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //影视下载
        db.execSQL("CREATE TABLE IF NOT EXISTS download(_id INTEGER PRIMARY KEY AUTOINCREMENT,videocode,title,img,num,time,finish,args1,args2,args3,args4,args5,args6)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS download");
            //创建新表
            onCreate(db); //创建新表
        }
    }
}
