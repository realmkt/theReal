package com.rjfun.cordova.sms;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;

 
public class SmsMainActivity extends Activity
{
    BroadcastReceiver myReceiver = new SmsBroadCast();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        TelephonyManager telephony =  (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String  telPhoneNo = telephony.getLine1Number();  

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction(Intent.ACTION_BOOT_COMPLETED);
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(myReceiver, intentFilter);
        Log.d("onCreate()","브로드캐스트리시버 등록됨");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
        Log.d("onDestory()","브로드캐스트리시버 해제됨");
    }

}