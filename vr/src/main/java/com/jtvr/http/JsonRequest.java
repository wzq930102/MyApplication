package com.jtvr.http;

import android.content.Intent;

import com.jtvr.utils.UtilsApp;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by jt on 2017/3/31.
 */
public class JsonRequest {
    private RequestEnum re;
    private Intent i;
    public JsonRequest(RequestEnum re, Intent  i) {
        this.re = re;
        this.i = i;
    }
    public String getbizData(){
        JSONObject biz = new JSONObject();
        try {
            biz.put("reqType",re.getreqType());
            biz.put("appId","vr2017");
            biz.put("appKey","vr2017");
            biz.put("token", UtilsApp.getIntance().getToken());
            biz.put("version",UtilsApp.getIntance().getVersion());
            biz.put("business",re.getJson(i));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return biz.toString();
    }
    public String getdigest(){
        return getEncrypt(uncode.string2Unicode(getbizData()));
    }
    /**
     * 加密
     * @param str
     * @return
     */
    public String getEncrypt(String str){
        return Encrypt(str,"SHA-256");
    }
    public String Encrypt(String strSrc, String encName) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            System.out.println("签名失败！");
            return null;
        }
        return strDes;
    }
    public String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

}
