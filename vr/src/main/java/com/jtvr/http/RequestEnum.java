package com.jtvr.http;

import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jt on 2017/3/31.
 */
public enum RequestEnum {

    VRDATEL {//360vr和视频列表

        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            intent.putExtra("ftypeCode", param[0].toString());
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("ftypeCode", i.getStringExtra("ftypeCode"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jo;
        }

        public String getreqType() {
            return "01";
        }
    }, PLAYMEDIADETAIL {//获取视频详情

        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            intent.putExtra("videoCode", param[0].toString());
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("videoCode", i.getStringExtra("videoCode"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jo;
        }

        public String getreqType() {
            return "02";
        }


    }, MOVIECODE {
        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            intent.putExtra("code", param[0].toString());
            intent.putExtra("firstCode", param[1].toString());
            intent.putExtra("pageno", (int) param[2]);
            intent.putExtra("pagesize", (int) param[3]);
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("code", i.getStringExtra("code"));
                jo.put("firstCode", i.getStringExtra("firstCode"));
                jo.put("pageno", i.getIntExtra("pageno", 1));
                jo.put("pagesize", i.getIntExtra("pagesize", 10));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jo;
        }

        public String getreqType() {
            return "04";
        }
    }, MOVIETYPE {
        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            intent.putExtra("ftype", param[0].toString());
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("ftype", i.getStringExtra("ftype"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jo;
        }

        public String getreqType() {
            return "07";
        }
    }, PERSONINFO {
        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            intent.putExtra("img", param[0].toString());
            intent.putExtra("nickName", param[1].toString());
            intent.putExtra("email", param[2].toString());
            intent.putExtra("province", param[3].toString());
            intent.putExtra("city", param[4].toString());
            intent.putExtra("county", param[5].toString());
            intent.putExtra("qqMem", param[6].toString());
            intent.putExtra("dateOfBirty", param[7].toString());
            intent.putExtra("address", param[8].toString());
            intent.putExtra("sexMem", param[9].toString());
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("img", i.getStringExtra("img"));
                jo.put("nickName", i.getStringExtra("nickName"));
                jo.put("email", i.getStringExtra("email"));
                jo.put("province", i.getStringExtra("province"));
                jo.put("city", i.getStringExtra("city"));
                jo.put("county", i.getStringExtra("county"));
                jo.put("qqMem", i.getStringExtra("qqMem"));
                jo.put("dateOfBirty", i.getStringExtra("dateOfBirty"));
                jo.put("address", i.getStringExtra("address"));
                jo.put("sexMem", i.getStringExtra("sexMem"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jo;
        }

        public String getreqType() {
            return "10";
        }
    }, IMGCOMMIT {
        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            intent.putExtra("name", param[0].toString());
            intent.putExtra("type", param[1].toString());
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("name", i.getStringExtra("name"));
                jo.put("type", i.getStringExtra("type"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jo;
        }

        public String getreqType() {
            return "99";
        }

    }, BUTFIRS {
        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            intent.putExtra("money", param[0].toString());
            intent.putExtra("fires", param[1].toString());
            intent.putExtra("giveFires", param[2].toString());
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("money", i.getStringExtra("money"));
                jo.put("fires", i.getStringExtra("fires"));
                jo.put("giveFires", i.getStringExtra("giveFires"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jo;
        }

        public String getreqType() {
            return "14";
        }
    }, BUYRECORD {
        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            intent.putExtra("pageno", param[0].toString());
            intent.putExtra("pagesize", param[1].toString());
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("pageno", i.getStringExtra("pageno"));
                jo.put("pagesize", i.getStringExtra("pagesize"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jo;
        }

        public String getreqType() {
            return "17";
        }
    }, FIRSCONFIGURATION {
        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            return jo;
        }

        public String getreqType() {
            return "34";
        }
    }, VERSION {
        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            return jo;
        }

        public String getreqType() {
            return "26";
        }
    }, CHONGRECORD {
        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            intent.putExtra("pageno", param[0].toString());
            intent.putExtra("pagesize", param[1].toString());
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("pageno", i.getStringExtra("pageno"));
                jo.put("pagesize", i.getStringExtra("pagesize"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jo;
        }

        public String getreqType() {
            return "16";
        }

    }, PERSONINFOCOMMIT {
        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            intent.putExtra("img", param[0].toString());
            intent.putExtra("nickName", param[1].toString());
            intent.putExtra("email", param[2].toString());
            intent.putExtra("dateOfBirty", param[3].toString());
            intent.putExtra("address", param[4].toString());
            intent.putExtra("sexMem", param[5].toString());
            intent.putExtra("profession", param[6].toString());
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("img", i.getStringExtra("img"));
                jo.put("nickName", i.getStringExtra("nickName"));
                jo.put("email", i.getStringExtra("email"));
                jo.put("dateOfBirty", i.getStringExtra("dateOfBirty"));
                jo.put("address", i.getStringExtra("address"));
                jo.put("sexMem", i.getStringExtra("sexMem"));
                jo.put("profession", i.getStringExtra("profession"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jo;
        }

        public String getreqType() {
            return "10";
        }

    }, COMMITNUM {//提交点击率

        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            intent.putExtra("videoCode", param[0].toString());
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("videoCode", i.getStringExtra("videoCode"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jo;
        }

        public String getreqType() {
            return "05";
        }
    }, PLAYDETAILCOMMENT {//评论

        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            intent.putExtra("pageno", param[0].toString());
            intent.putExtra("pagesize", param[1].toString());
            intent.putExtra("videoCode", param[2].toString());
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("pageno", i.getStringExtra("pageno"));
                jo.put("pagesize", i.getStringExtra("pagesize"));
                jo.put("videoCode", i.getStringExtra("videoCode"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jo;
        }

        public String getreqType() {
            return "30";
        }
    }, PLAYDETAILCOMMENTCOMMIT {//评论提交

        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            intent.putExtra("videoCode", param[0].toString());
            intent.putExtra("content", param[1].toString());
            intent.putExtra("commentCode", param[2].toString());
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("videoCode", i.getStringExtra("videoCode"));
                jo.put("content", i.getStringExtra("content"));
                jo.put("commentCode", i.getStringExtra("commentCode"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jo;
        }

        public String getreqType() {
            return "28";
        }
    }, BUYVEDIO {//购买视频
        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            intent.putExtra("videoCode", param[0].toString());
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("videoCode", i.getStringExtra("videoCode"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jo;
        }

        public String getreqType() {
            return "18";
        }
    }, BUYVIP {//购买vip
        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            intent.putExtra("type", param[0].toString());
            intent.putExtra("fires", param[1].toString());
            intent.putExtra("money", param[2].toString());
            intent.putExtra("videoCode", param[3].toString());
            intent.putExtra("month", (int)param[4]);
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("type", i.getStringExtra("type"));
                jo.put("fires", i.getStringExtra("fires"));
                jo.put("money", i.getStringExtra("money"));
                jo.put("videoCode", i.getStringExtra("videoCode"));
                jo.put("month", i.getIntExtra("month",0));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jo;
        }

        public String getreqType() {
            return "15";
        }
    }, COMMITSCORE {//提交评分
        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            intent.putExtra("videoCode", param[0].toString());
            intent.putExtra("grad", param[1].toString());
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("videoCode", i.getStringExtra("videoCode"));
                jo.put("grad", i.getStringExtra("grad"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jo;
        }

        public String getreqType() {
            return "38";
        }
    }, BUYVIPDATA {//提交vip
        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            return jo;
        }

        public String getreqType() {
            return "37";
        }
    }, GETPERSON {
        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();

            return jo;
        }

        public String getreqType() {
            return "13";
        }

    }, LOGIN {//登录
        private boolean isTrue = false;

        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            intent.putExtra("phone", param[0].toString());

            if ("".equals(param[2].toString())) {
                isTrue = false;
                intent.putExtra("password", param[1].toString());
            } else {
                isTrue = true;
                intent.putExtra("tokens", param[2].toString());
            }
            intent.putExtra("device", param[3].toString());
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("phone", i.getStringExtra("phone"));
                if (isTrue) {
                    jo.put("tokens", i.getStringExtra("tokens"));
                } else {
                    jo.put("password", i.getStringExtra("password"));
                }
                jo.put("device", i.getStringExtra("device"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jo;
        }

        public String getreqType() {
            return "11";
        }
    }, CODE {//获取验证码

        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            intent.putExtra("phone", param[0].toString());
            intent.putExtra("type", param[1].toString());
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("phone", i.getStringExtra("phone"));
                jo.put("type", i.getStringExtra("type"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jo;
        }

        public String getreqType() {
            return "09";
        }
    }, REGISTER {//注册

        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            intent.putExtra("phone", param[0].toString());
            intent.putExtra("yzm", param[1].toString());
            intent.putExtra("password", param[2].toString());
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("phone", i.getStringExtra("phone"));
                jo.put("yzm", i.getStringExtra("yzm"));
                jo.put("password", i.getStringExtra("password"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jo;
        }

        public String getreqType() {
            return "08";
        }
    }, FORGET {//修改密码

        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            intent.putExtra("phone", param[0].toString());
            intent.putExtra("yzm", param[1].toString());
            intent.putExtra("password", param[2].toString());
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("phone", i.getStringExtra("phone"));
                jo.put("yzm", i.getStringExtra("yzm"));
                jo.put("password", i.getStringExtra("password"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jo;
        }

        public String getreqType() {
            return "12";
        }
    }, MOVIEMORE {//更多

        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            intent.putExtra("menuCode", param[0].toString());
            intent.putExtra("pageno", (int)param[1]);
            intent.putExtra("pagesize", (int)param[2]);
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("menuCode", i.getStringExtra("menuCode"));
                jo.put("pageno", i.getIntExtra("pageno", 1));
                jo.put("pagesize", i.getIntExtra("pagesize", 10));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jo;
        }

        public String getreqType() {
            return "03";
        }
    }, PUTCOLLECTION {//提交收藏

        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            intent.putExtra("videoCode", param[0].toString());
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("videoCode", i.getStringExtra("videoCode"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jo;
        }

        public String getreqType() {
            return "19";
        }
    }, COLLECTION {//获取收藏列表

        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            intent.putExtra("pageno", (int) param[0]);
            intent.putExtra("pagesize", (int) param[1]);
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("pageno", i.getIntExtra("pageno", 1));
                jo.put("pagesize", i.getIntExtra("pagesize", 10));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jo;
        }

        public String getreqType() {
            return "20";
        }
    }, CLEANCOLLECTION {//清空收藏列表

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("keepCode", i.getStringExtra("keepCode"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jo;
        }

        @Override
        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            intent.putExtra("keepCode", param[0].toString());
            return intent;
        }

        public String getreqType() {
            return "24";
        }
    }, ADDHSITORY {//提交浏览记录

        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            intent.putExtra("videoCode", param[0].toString());
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("videoCode", i.getStringExtra("videoCode"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jo;
        }

        public String getreqType() {
            return "21";
        }
    }, HISTORY {//获取观看历史列表

        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            intent.putExtra("pageno", (int) param[0]);
            intent.putExtra("pagesize", (int) param[1]);
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("pageno", i.getIntExtra("pageno", 1));
                jo.put("pagesize", i.getIntExtra("pagesize", 10));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jo;
        }

        public String getreqType() {
            return "22";
        }
    }, CLEANLEAVER {//退出登录

        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            return jo;
        }

        public String getreqType() {
            return "23";
        }
    }, CLEANHISTORY {//清空观看历史

        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            return jo;
        }

        public String getreqType() {
            return "25";
        }
    }, SIGN {//获取签到详情

        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            return jo;
        }

        public String getreqType() {
            return "31";
        }
    }, PUTSIGN {//提交签到

        public Intent addIntent(Object... param) {
            Intent intent = new Intent();
            intent.putExtra("fires", param[0].toString());
            intent.putExtra("signCode", param[1].toString());
            return intent;
        }

        public JSONObject getJson(Intent i) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("fires", i.getStringExtra("fires"));
                jo.put("signCode", i.getStringExtra("signCode"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jo;
        }

        public String getreqType() {
            return "32";
        }
    };

    public abstract String getreqType();

    public abstract JSONObject getJson(Intent i);

    public abstract Intent addIntent(Object... param);
}
