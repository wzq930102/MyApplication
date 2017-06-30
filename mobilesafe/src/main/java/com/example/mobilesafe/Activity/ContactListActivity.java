package com.example.mobilesafe.Activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;

import java.util.HashMap;

import static com.example.mobilesafe.Activity.SplashActivity.tag;


/**
 * Created by wzq930102 on 2017/6/30.
 */
public class ContactListActivity extends Activity{
    private ListView lv_contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        initUI();
        initDate();
    }

    /**
     * 获取系统联系人数据方法
     */
    private void initDate() {
        //因为读取系统联系人,可能是一个耗时操作,放置到子线程中处理
        new Thread() {
            public void run() {
                //1,获取内容解析器对象
                ContentResolver contentResolver = getContentResolver();
                //2,做查询系统联系人数据库表过程(读取联系人权限)
                Cursor cursor = contentResolver.query(Uri.parse("content://com.android.contacts/raw_contacts"),
                        new String[]{"contact_id"}, null, null, null);
                //3,循环游标,直到没有数据为止
                
                while (cursor.moveToNext()) {
                    String id = cursor.getString(0);
                    Log.i(tag, "id = "+id);
                    //4,根据用户唯一性id值,查询data表和mimetype表生成的视图,获取data以及mimetype字段
                    Cursor indexCursor = contentResolver.query(
                            Uri.parse("content://com.android.contacts/data"),
                            new String[]{"data1","mimetype"},
                            "raw_contact_id = ?", new String[]{id}, null);
                    //5,循环获取每一个联系人的电话号码以及姓名,数据类型
                    HashMap<String, String> hashMap = new HashMap<String, String>();
                    while(indexCursor.moveToNext()){
                        String data = indexCursor.getString(0);
                        String type = indexCursor.getString(1);

                        //6,区分类型去给hashMap填充数据
                        if(type.equals("vnd.android.cursor.item/phone_v2")){
                            //数据非空判断
                            if(!TextUtils.isEmpty(data)){
                                hashMap.put("phone", data);
                            }
                        }else if(type.equals("vnd.android.cursor.item/name")){
                            if(!TextUtils.isEmpty(data)){
                                hashMap.put("name", data);
                            }
                        }
                    }
                    indexCursor.close();
//                    contactList.add(hashMap);
                }
                cursor.close();



            }
        }.start();
    }
    private void initUI() {
        lv_contact = (ListView) findViewById(R.id.lv_contact);
    }
}
