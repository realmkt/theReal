package first.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

import first.common.util.Sha256;

public class PaynowParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String UplresData = null;
		
		/*타임아웃30초설정*/
		int timeout = 30;
		
		/*timestamp 값구하기*/
		Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));
    	
		


        
		/*signature 생성을 위한 변수선언  */
/*		String mbrNo 			= "551553" ; 							 가맹점번호
		String mbrRefNo 	= "10102201010054";				 주문번호
		String amount 		= "100";							 	 	결제금액	
		String apikey 			= "k/CKiKECVPWQ+AcemOaIbBk/gJk/gZc0gBozGRa8gJoYGi=="; 								
		String timestamp	= today;*/ 	 	
        
		String mbrNo 			= "551553" ; 							 /*가맹점번호*/
		String mbrRefNo 	= "201703100001";				 /*주문번호*/
		String amount 		= "1000";							 	 	/*결제금액*/
		String apikey 			= "k/CKiKECVPWQ+AcemOaIbBk/gJk/gZc0gBozGRa8gJoYGi==";
		String timestamp	= today;
		 /*암호화처리 및 signature 생성*/  		
		String signature = Sha256.encryptString(mbrNo+"|"+mbrRefNo+"|"+amount+"|"+apikey+"|"+timestamp);
		
		/*변수 값 선언*/
		String goodName ="유플러스";
		String customerTelNo = "01052812307";
		String orgTranDate = today.substring(2,8);
		
		
	try {
		
		    RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout * 1000)
		   .setConnectionRequestTimeout(timeout * 1000)
		   .setSocketTimeout(timeout * 1000).build(); 
			
		    CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build(); 
		    RequestConfig.Builder requestBuilder = RequestConfig.custom();
		    HttpClientBuilder builder = HttpClientBuilder.create();
		    builder.setDefaultRequestConfig(requestBuilder.build());
		    
		    List<NameValuePair> params = new ArrayList<NameValuePair>();
		  
		    
		    /*승인요청*/
/*	
		    HttpPost httpost = new HttpPost(new URI("http://117.52.97.40/theReal/receipt/reqRealApproval.do"));
		    params.add(new BasicNameValuePair("mbrNo", mbrNo));
		    params.add(new BasicNameValuePair("mbrRefNo", mbrRefNo));
		    params.add(new BasicNameValuePair("amount", amount));
		    params.add(new BasicNameValuePair("customerTelNo", customerTelNo));
		    params.add(new BasicNameValuePair("goodsName", goodName));
		    params.add(new BasicNameValuePair("timestamp", today));
		    params.add(new BasicNameValuePair("charset", "UTF-8"));
		    
		    params.add(new BasicNameValuePair("clientType", "pos"));
		    params.add(new BasicNameValuePair("signature", signature));*/

		    /*승인취소*/
			HttpPost httpost = new HttpPost(new URI("http://117.52.97.40/theReal/receipt/reqRealCancle.do"));
		    params.add(new BasicNameValuePair("mbrNo", mbrNo));
		    params.add(new BasicNameValuePair("amount", amount));
		    params.add(new BasicNameValuePair("mbrRefNo", mbrRefNo));
		    params.add(new BasicNameValuePair("orgTranDate", orgTranDate));
		    params.add(new BasicNameValuePair("orgRefNo", "0310P6000483"));
		    params.add(new BasicNameValuePair("charset", "UTF-8"));
		    params.add(new BasicNameValuePair("signature", signature));
		    params.add(new BasicNameValuePair("clientType", "pos"));
		    params.add(new BasicNameValuePair("timestamp", today));	    
		    System.out.println("■sendDATA 확인 ==■"+params);
	
		    try {
		    	httpost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		    	//System.out.println("■sendDATA 확인 ==■"+new UrlEncodedFormEntity(params, "UTF-8"));
		    //	System.out.println("■sendDATA 확인 ==■"+params);
		    	System.out.println(params.size());
		    	HashMap<String,Object> map = new HashMap<String,Object>();
		    	for(int i=0;i<params.size();i++ ){
		    		map.put(params.get(i).getName(), params.get(i).getValue());
		    	}
		    	
		    	System.out.println(map);
		    	
			    JSONObject json = new JSONObject();
			    json.putAll(map);
			    System.out.println(json);
			    
/*				String test = "mbrNo=551553&mbrRefNo=10102201010054&amount=100&customerTelNo=01077003578&goodsName=축구공&timestamp=20160929190604&charset=UTF-8&clientType=pos&signature=30acc389fbb1d48cf22eb10e6b30f6338c72aa79399277486a9014140b5ca908";
				String[] array;
			    String[] array2;
			    array = test.split("&");
				HashMap<String,Object> map = new HashMap<String,Object>();
			    for(int i=0;i<array.length;i++ ){
			    	array2 = array[i].split("=");
			    	map.put(array2[0], array2[1]);
			    }
			    System.out.println(map);
			    
			    System.out.println(map.get("mbrNo"));
				
  */
			    
    		    StringEntity stringEntity = new StringEntity(json.toJSONString(),
						ContentType.create("application/json", "UTF-8"));
    		    httpost.setEntity(stringEntity);
    		  
    		    
		    } catch (UnsupportedEncodingException e) {
		        // writing error to Log
		        e.printStackTrace();
		    }
		    
		    //응답데이터를 받기위한 서비스호출
			HttpResponse response0 = httpClient.execute(httpost);
			HttpEntity resEntity = response0.getEntity();
			UplresData = EntityUtils.toString(resEntity);
			System.out.println("■■유플러스 응답데이터■■=="+UplresData);			
			
			
			
		    
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
