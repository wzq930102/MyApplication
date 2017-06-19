package com.jtvr.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.jtvr.app.VRApplication;
import com.jtvr.model.MessageEvent;
import com.jtvr.utils.LogUtils;
import com.jtvr.utils.NetworkHelper;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by yy on 2017/4/20.
 */
public class WifiReceiver extends BroadcastReceiver {
    private MessageEvent messageEvent = new MessageEvent();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(WifiManager.RSSI_CHANGED_ACTION)) {
            //signal strength changed
        } else if (intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {//wifi连接上与否
            LogUtils.e("网络状态改变");
            NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if (info.getState().equals(NetworkInfo.State.DISCONNECTED)) {
                LogUtils.e("wifi网络连接断开");
                if (NetworkHelper.getConnectionType(VRApplication.getContext()) != 1) {
                    messageEvent.setType(1);
                    EventBus.getDefault().post(messageEvent);
                }
            } else if (info.getState().equals(NetworkInfo.State.CONNECTED)) {
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                //获取当前wifi名称
                LogUtils.e("连接到网络 " + wifiInfo.getSSID());
            }
        } else if (intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {//wifi打开与否
            int wifistate = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_DISABLED);
            if (wifistate == WifiManager.WIFI_STATE_DISABLED) {
                LogUtils.e("系统关闭wifi");
            } else if (wifistate == WifiManager.WIFI_STATE_ENABLED) {
                LogUtils.e("系统开启wifi");
            }
        }

    }
}