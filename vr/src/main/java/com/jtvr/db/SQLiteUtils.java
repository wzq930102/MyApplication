package com.jtvr.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jtvr.model.DBBean;

import java.util.ArrayList;
import java.util.List;

public class SQLiteUtils {
    private Context context;
    private SQLiteHelper helper;
    private SQLiteDatabase db;

    public SQLiteUtils(Context context) {
        this.context = context;
        helper = new SQLiteHelper(context);
        db = helper.getReadableDatabase();
    }

    // 从download表进行查找ID
    public boolean SlelectID(String videocode) {
        List<String> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from download where videocode = ?",
                new String[]{videocode});
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("videocode"));
            list.add(id);
        }
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (videocode.equals(id)) {
                return true;
            }
        }
        return false;
    }


    // 往表插入数据
    public long InsertData(String videocode, String title, String img, String num, String time, String finish, String args1) {
        ContentValues values = new ContentValues();
        values.put("videocode", videocode);
        values.put("title", title);
        values.put("img", img);
        values.put("num", num);
        values.put("time", time);
        values.put("finish", finish);
        values.put("args1", args1);
        long i = db.insert("download", null, values);
        return i;
    }

    //修改数据
    public int Update(String finish, String videocode) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("finish", finish);
        int rowCount = db.update("download", contentValues, "videocode=?", new String[]{videocode});
        return rowCount;
    }

    //修改数据
    public int Update2(String finish, String args1) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("finish", finish);
        int rowCount = db.update("download", contentValues, "args1=?", new String[]{args1});
        return rowCount;
    }

//    // 往History表插入数据
//    public long InsertHistoryData(String id, String name, String icon, String theme, String ranking, String state) {
//        ContentValues values = new ContentValues();
//        values.put("id", id);
//        values.put("name", name);
//        values.put("icon", icon);
//        values.put("theme", theme);
//        values.put("ranking", ranking);
//        values.put("state", state);
//        long i = db.insert("history", null, values);
//        return i;
//    }
//     从collection表中查找数据 并将数据获取到
//    public List<dbBean> selectCollectionGetdata(String ID) {
//        List<dbBean> list=new ArrayList<>();
//        Cursor cursor = db.rawQuery("select * from collection where id = ?",
//                new String[]{ID});
//        while (cursor.moveToNext()) {
//            String id = cursor.getString(cursor.getColumnIndex("id"));
//            String name = cursor.getString(cursor.getColumnIndex("name"));
//            String icon = cursor.getString(cursor.getColumnIndex("icon"));
//            String theme = cursor.getString(cursor.getColumnIndex("theme"));
//            String ranking = cursor.getString(cursor.getColumnIndex("ranking"));
//            String state = cursor.getString(cursor.getColumnIndex("state"));
//            dbBean dbBean=new dbBean();
//            dbBean.setId(id);
//            dbBean.setName(name);
//            dbBean.setIcon(icon);
//            dbBean.setTheme(theme);
//            dbBean.setRanking(ranking);
//            dbBean.setState(state);
//            list.add(dbBean);
//        }
//        return list;
//    }

    // 从collection表中获取全部数据
    public List<DBBean> selectAllData() {
        List<DBBean> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from download", null);
        while (cursor.moveToNext()) {
            String videocode = cursor.getString(cursor.getColumnIndex("videocode"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String img = cursor.getString(cursor.getColumnIndex("img"));
            String num = cursor.getString(cursor.getColumnIndex("num"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String finish = cursor.getString(cursor.getColumnIndex("finish"));
            String args1 = cursor.getString(cursor.getColumnIndex("args1"));
            DBBean dbBean = new DBBean();
            dbBean.setVideocode(videocode);
            dbBean.setTitle(title);
            dbBean.setImg(img);
            dbBean.setNum(num);
            dbBean.setTime(time);
            dbBean.setFinish(finish);
            dbBean.setArgs1(args1);
            list.add(dbBean);
        }
        return list;
    }

//    // 从History表中查找数据 并将数据获取到
//    public void selectHistoryGetdata(String ID) {
//        Cursor cursor = db.rawQuery("select * from history where id = ?",
//                new String[]{ID});
//        while (cursor.moveToNext()) {
//            String id = cursor.getString(cursor.getColumnIndex("id"));
//            String name = cursor.getString(cursor.getColumnIndex("name"));
//            String icon = cursor.getString(cursor.getColumnIndex("icon"));
//            String theme = cursor.getString(cursor.getColumnIndex("theme"));
//            String ranking = cursor.getString(cursor.getColumnIndex("ranking"));
//            String state = cursor.getString(cursor.getColumnIndex("state"));
//        }
//
//    }

    //删除表中的数据
    public int deleteAll(String videocode) {
        int rowCount = db.delete("download", "videocode = ?", new String[]{videocode});
        return rowCount;
    }

    //删除表中的数据
    public int delete() {
        int rowCount = db.delete("download", null, null);
        return rowCount;
    }

}
