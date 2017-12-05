package first.sample.theRealShop;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import first.common.util.Sha256;

public class TheRealShopToUplus {

	private static final Logger log = Logger.getLogger(TheRealShopToUplus.class.getName());
	
//-----------------------------------------------//
//       		 더리얼샵 유플러스 결제 					  //
//-----------------------------------------------//
	@SuppressWarnings("deprecation")
	public static String reqTheRealApproval(HttpServletRequest request, HttpServletResponse response, String json) throws IOException, org.json.simple.parser.ParseException{
		// TODO Auto-generated method stub
				String UplresData = null;
				int timeout = 30;
		        JSONParser jsonParser = new JSONParser();
				 
				//JSON데이터를 넣어 JSON Object 로 만들어 준다.
				JSONObject jsonObject = null;
				
				try {
				    HttpPost httpost = new HttpPost(new URI("https://relay.mainpay.co.kr/v1/api/payments/payment/payyo/trans"));
					
					RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout * 1000)
				   .setConnectionRequestTimeout(timeout * 1000)
				   .setSocketTimeout(timeout * 1000).build(); 
				    CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build(); 
				    RequestConfig.Builder requestBuilder = RequestConfig.custom();
				    HttpClientBuilder builder = HttpClientBuilder.create();
				    builder.setDefaultRequestConfig(requestBuilder.build());
				    
				    
				    List<NameValuePair> params = new ArrayList<NameValuePair>();
				    long startTime = System.nanoTime();
				    log.debug("json:"+json);
				    
					jsonObject = (JSONObject) jsonParser.parse(json);
				    log.debug("mbrNo:"+(String) jsonObject.get("mbrNo"));
				    log.debug("mbrRefNo:"+(String) jsonObject.get("mbrRefNo"));
				    log.debug("amount:"+(String) jsonObject.get("amount"));
				    log.debug("customerTelNo:"+(String) jsonObject.get("customerTelNo"));
				    log.debug("timestamp:"+(String) jsonObject.get("timestamp"));
				    log.debug("signature:"+(String) jsonObject.get("signature"));
				    params.add(new BasicNameValuePair("mbrNo", (String) jsonObject.get("mbrNo")));
				    params.add(new BasicNameValuePair("mbrRefNo",(String) jsonObject.get("mbrRefNo")));
				    params.add(new BasicNameValuePair("amount",(String) jsonObject.get("amount")));
				    params.add(new BasicNameValuePair("customerTelNo",(String) jsonObject.get("customerTelNo")));
				    params.add(new BasicNameValuePair("goodsName",(String) jsonObject.get("goodsName")));
				    params.add(new BasicNameValuePair("timestamp",(String) jsonObject.get("timestamp")));
				    params.add(new BasicNameValuePair("charset", "UTF-8"));
				    params.add(new BasicNameValuePair("clientType", "pos"));
				    params.add(new BasicNameValuePair("signature",(String) jsonObject.get("signature")));				    
				    httpost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));				    
				    log.debug("params:"+params);
				    log.debug("new UrlEncodedFormEntity(params):"+new UrlEncodedFormEntity(params, "UTF-8"));
				    log.debug("httpost:"+httpost);
				    
				    
/*				    String[] array;
	                   String[] array2;
	                   array = json.split("&");
	                  HashMap<String,Object> map = new HashMap<String,Object>();
	                   for(int i=0;i<array.length;i++ ){
	                      array2 = array[i].split("=");
	                      map.put(array2[0], array2[1]);
	                   }
	                   System.out.println("\n @@@@@@"+map);
	                   
	                   System.out.println("\n11111111111111"+map.get("mbrNo"));
				    승인요청
	                // Start time
				    params.add(new BasicNameValuePair("mbrNo", (String) map.get("mbrNo")));
				    params.add(new BasicNameValuePair("mbrRefNo",(String) map.get("mbrRefNo")));
				    params.add(new BasicNameValuePair("amount",(String) map.get("amount")));
				    params.add(new BasicNameValuePair("customerTelNo",(String) map.get("customerTelNo")));
				    params.add(new BasicNameValuePair("goodsName",(String) map.get("goodsName")));
				    params.add(new BasicNameValuePair("timestamp",(String) map.get("timestamp")));
				    params.add(new BasicNameValuePair("charset", "UTF-8"));
				    params.add(new BasicNameValuePair("clientType", "pos"));
				    params.add(new BasicNameValuePair("signature",(String) map.get("signature")));				    
				    httpost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));*/
				    long endTime = System.nanoTime();
				    long lTime = endTime - startTime;
				    log.debug("TIME : " + lTime/1000000.0 + "(ms)");

			        //StringEntity stringEntity = new StringEntity(json,"UTF-8");
			        //httpost.setEntity(new UrlEncodedFormEntity(json, "UTF-8"));
					//httpost.setEntity(stringEntity);
				  
				    /*응답데이터를 받기위한 서비스호출*/
					HttpResponse response0 = httpClient.execute(httpost);
					HttpEntity resEntity = response0.getEntity();
					UplresData = EntityUtils.toString(resEntity);
					log.debug("■■유플러스 보낸데이터■■=="+json);			
					log.debug("■■유플러스 응답데이터■■=="+UplresData);			
					    
			
				} catch (URISyntaxException e) {
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}		
		
		return UplresData ;	
	}
	
	@SuppressWarnings("deprecation")
	public static String reqTheRealApprovalTextPlain(HttpServletRequest request, HttpServletResponse response, String json) throws IOException, org.json.simple.parser.ParseException{
		// TODO Auto-generated method stub
		String UplresData = null;
		int timeout = 30;
		JSONParser jsonParser = new JSONParser();
		
		//JSON데이터를 넣어 JSON Object 로 만들어 준다.
		JSONObject jsonObject = null;
		
		try {
			HttpPost httpost = new HttpPost(new URI("https://relay.mainpay.co.kr/v1/api/payments/payment/payyo/trans"));
			
			RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout * 1000)
					.setConnectionRequestTimeout(timeout * 1000)
					.setSocketTimeout(timeout * 1000).build(); 
			CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build(); 
			RequestConfig.Builder requestBuilder = RequestConfig.custom();
			HttpClientBuilder builder = HttpClientBuilder.create();
			builder.setDefaultRequestConfig(requestBuilder.build());
			
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			long startTime = System.nanoTime();
			log.debug("json:"+json);
			
			jsonObject = (JSONObject) jsonParser.parse(json);
			log.debug("mbrNo:"+(String) jsonObject.get("mbrNo"));
			log.debug("mbrRefNo:"+(String) jsonObject.get("mbrRefNo"));
			log.debug("amount:"+(String) jsonObject.get("amount"));
			log.debug("customerTelNo:"+(String) jsonObject.get("customerTelNo"));
			log.debug("timestamp:"+(String) jsonObject.get("timestamp"));
			log.debug("signature:"+(String) jsonObject.get("signature"));
			String signatureText = Sha256.encryptString((String) jsonObject.get("signature"));
			params.add(new BasicNameValuePair("mbrNo", (String) jsonObject.get("mbrNo")));
			params.add(new BasicNameValuePair("mbrRefNo",(String) jsonObject.get("mbrRefNo")));
			params.add(new BasicNameValuePair("amount",(String) jsonObject.get("amount")));
			params.add(new BasicNameValuePair("customerTelNo",(String) jsonObject.get("customerTelNo")));
			params.add(new BasicNameValuePair("goodsName",(String) jsonObject.get("goodsName")));
			params.add(new BasicNameValuePair("timestamp",(String) jsonObject.get("timestamp")));
			params.add(new BasicNameValuePair("charset", "UTF-8"));
			params.add(new BasicNameValuePair("clientType", "pos"));
			params.add(new BasicNameValuePair("signature",signatureText));				    
			httpost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));				    
			log.debug("params:"+params);
			log.debug("new UrlEncodedFormEntity(params):"+new UrlEncodedFormEntity(params, "UTF-8"));
			log.debug("httpost:"+httpost);
			
			
			/*				    String[] array;
	                   String[] array2;
	                   array = json.split("&");
	                  HashMap<String,Object> map = new HashMap<String,Object>();
	                   for(int i=0;i<array.length;i++ ){
	                      array2 = array[i].split("=");
	                      map.put(array2[0], array2[1]);
	                   }
	                   System.out.println("\n @@@@@@"+map);
	                   
	                   System.out.println("\n11111111111111"+map.get("mbrNo"));
				    승인요청
	                // Start time
				    params.add(new BasicNameValuePair("mbrNo", (String) map.get("mbrNo")));
				    params.add(new BasicNameValuePair("mbrRefNo",(String) map.get("mbrRefNo")));
				    params.add(new BasicNameValuePair("amount",(String) map.get("amount")));
				    params.add(new BasicNameValuePair("customerTelNo",(String) map.get("customerTelNo")));
				    params.add(new BasicNameValuePair("goodsName",(String) map.get("goodsName")));
				    params.add(new BasicNameValuePair("timestamp",(String) map.get("timestamp")));
				    params.add(new BasicNameValuePair("charset", "UTF-8"));
				    params.add(new BasicNameValuePair("clientType", "pos"));
				    params.add(new BasicNameValuePair("signature",(String) map.get("signature")));				    
				    httpost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));*/
			long endTime = System.nanoTime();
			long lTime = endTime - startTime;
			log.debug("TIME : " + lTime/1000000.0 + "(ms)");
			
			//StringEntity stringEntity = new StringEntity(json,"UTF-8");
			//httpost.setEntity(new UrlEncodedFormEntity(json, "UTF-8"));
			//httpost.setEntity(stringEntity);
			
			/*응답데이터를 받기위한 서비스호출*/
			HttpResponse response0 = httpClient.execute(httpost);
			HttpEntity resEntity = response0.getEntity();
			UplresData = EntityUtils.toString(resEntity);
			log.debug("■■유플러스 보낸데이터■■=="+json);			
			log.debug("■■유플러스 응답데이터■■=="+UplresData);			
			
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		return UplresData ;	
	}
	
	
	
	
	
//-----------------------------------------------//
//	 더리얼샵 유플러스 취소승인 						  //
//-----------------------------------------------//
	
@SuppressWarnings("deprecation")
public static String reqTheRealCancle(HttpServletRequest request, HttpServletResponse response, String json) throws IOException, org.json.simple.parser.ParseException{
	// TODO Auto-generated method stub
	   String UplresData = null;
	   int timeout = 30;

	   try {
	      HttpPost httpost = new HttpPost(new URI("https://relay.mainpay.co.kr/v1/api/payments/payment/payyo/cancel"));
	      RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout * 1000)
	      .setConnectionRequestTimeout(timeout * 1000)
	      .setSocketTimeout(timeout * 1000).build(); 
	       CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build(); 
	       RequestConfig.Builder requestBuilder = RequestConfig.custom();
	       HttpClientBuilder builder = HttpClientBuilder.create();
	       builder.setDefaultRequestConfig(requestBuilder.build());
	       long startTime = System.nanoTime();
	       List<NameValuePair> params = new ArrayList<NameValuePair>();
	       
	      //JSON데이터를 넣어 JSON Object 로 만들어 준다.
	      JSONObject jsonObject = null;
	      JSONParser jsonParser = new JSONParser();
	      jsonObject = (JSONObject) jsonParser.parse(json);
	      //Imu 측에서 보내준 JSON데이터 
	        log.debug("mbrNo : "+(String)jsonObject.get("mbrNo"));  
	        log.debug("mbrRefNo : "+(String)jsonObject.get("mbrRefNo"));  
	        log.debug("amount : "+(String)jsonObject.get("amount"));  
	        log.debug("orgTranDate : "+(String)jsonObject.get("orgTranDate"));  
	        log.debug("orgRefNo : "+(String)jsonObject.get("orgRefNo"));  
	        log.debug("timestamp : "+(String)jsonObject.get("timestamp"));  
	        log.debug("charset : "+(String)jsonObject.get("charset"));  
	        log.debug("clientType : "+(String)jsonObject.get("clientType"));  
	        log.debug("signature : "+(String)jsonObject.get("signature"));  
	   
	       params.add(new BasicNameValuePair("mbrNo",(String)jsonObject.get("mbrNo")));
	       params.add(new BasicNameValuePair("amount",(String)jsonObject.get("amount")));
	       params.add(new BasicNameValuePair("mbrRefNo",(String)jsonObject.get("mbrRefNo")));
	       params.add(new BasicNameValuePair("orgTranDate",(String)jsonObject.get("orgTranDate")));
	       params.add(new BasicNameValuePair("orgRefNo",(String)jsonObject.get("orgRefNo")));
	       params.add(new BasicNameValuePair("charset",(String)jsonObject.get("charset")));
	       params.add(new BasicNameValuePair("signature",(String)jsonObject.get("signature")));
	       params.add(new BasicNameValuePair("clientType",(String)jsonObject.get("clientType")));
	       params.add(new BasicNameValuePair("timestamp",(String)jsonObject.get("timestamp")));
	       log.debug("params:"+params);
	       log.debug("new UrlEncodedFormEntity(params):"+new UrlEncodedFormEntity(params, "UTF-8"));
	       log.debug("httpost:"+httpost);
	       
	       
	       
	       /*String 파라미터 값 전송*/
	   /*    String[] array;
	           String[] array2;
	           array = json.split("&");
	          HashMap<String,Object> map = new HashMap<String,Object>();
	           for(int i=0;i<array.length;i++ ){
	              array2 = array[i].split("=");
	              map.put(array2[0], array2[1]);
	           }
	           
	         log.debug("mbrNo:"+(String)map.get("mbrNo"));  
	         log.debug("mbrRefNo"+(String)map.get("mbrRefNo"));  
	         log.debug("amount"+(String)map.get("amount"));  
	         log.debug("orgTranDate"+(String)map.get("orgTranDate"));  
	         log.debug("orgRefNo"+(String)map.get("orgRefNo"));  
	         log.debug("timestamp"+(String)map.get("timestamp"));  
	         log.debug("charset"+(String)map.get("charset"));  
	         log.debug("clientType"+(String)map.get("clientType"));  
	         log.debug("signature"+(String)map.get("signature"));  
	        // Start time
	       params.add(new BasicNameValuePair("mbrNo", (String) map.get("mbrNo")));
	       params.add(new BasicNameValuePair("mbrRefNo",(String) map.get("mbrRefNo")));
	       params.add(new BasicNameValuePair("amount",(String) map.get("amount")));
	       params.add(new BasicNameValuePair("orgTranDate",(String) map.get("orgTranDate")));
	       params.add(new BasicNameValuePair("orgRefNo",(String) map.get("orgRefNo")));
	       params.add(new BasicNameValuePair("timestamp",(String) map.get("timestamp"))); 
	       params.add(new BasicNameValuePair("charset",(String)map.get("charset")));
	       params.add(new BasicNameValuePair("clientType",(String)map.get("clientType")));
	       params.add(new BasicNameValuePair("signature",(String) map.get("signature")));                
	       log.debug("params:"+params);
	       log.debug("new UrlEncodedFormEntity(params):"+new UrlEncodedFormEntity(params, "UTF-8"));
	       log.debug("httpost:"+httpost);*/
	      
	       
	       /*전송*/
	       httpost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
	       long endTime = System.nanoTime();
	       long lTime = endTime - startTime;
	       log.debug("TIME : " + lTime/1000000.0 + "(ms)");
	       
	       
	       
	       
	       
	       /*응답데이터를 받기위한 서비스호출*/
	      HttpResponse response0 = httpClient.execute(httpost);
	      HttpEntity resEntity = response0.getEntity();
	      UplresData = EntityUtils.toString(resEntity);
	      log.debug("■■유플러스 보낸데이터■■=="+json);         
	      log.debug("■■유플러스 응답데이터■■=="+UplresData);         
	      

	   } catch (URISyntaxException e) {
	      e.printStackTrace();
	   } catch (ClientProtocolException e) {
	      e.printStackTrace();
	   } catch (IOException e) {
	      e.printStackTrace();
	   }      

	return UplresData ;    
}


//-----------------------------------------------//
//	 더리얼샵 유플러스 취소승인 						  //
//-----------------------------------------------//

@SuppressWarnings("deprecation")
public static String reqTheRealCancleTextPlain(HttpServletRequest request, HttpServletResponse response, String json) throws IOException, org.json.simple.parser.ParseException{
	// TODO Auto-generated method stub
	   String UplresData = null;
	   int timeout = 30;

	   try {
	      HttpPost httpost = new HttpPost(new URI("https://relay.mainpay.co.kr/v1/api/payments/payment/payyo/cancel"));
	      RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout * 1000)
	      .setConnectionRequestTimeout(timeout * 1000)
	      .setSocketTimeout(timeout * 1000).build(); 
	       CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build(); 
	       RequestConfig.Builder requestBuilder = RequestConfig.custom();
	       HttpClientBuilder builder = HttpClientBuilder.create();
	       builder.setDefaultRequestConfig(requestBuilder.build());
	       long startTime = System.nanoTime();
	       List<NameValuePair> params = new ArrayList<NameValuePair>();
	       
	      //JSON데이터를 넣어 JSON Object 로 만들어 준다.
	      JSONObject jsonObject = null;
	      JSONParser jsonParser = new JSONParser();
	      jsonObject = (JSONObject) jsonParser.parse(json);
	      //Imu 측에서 보내준 JSON데이터 
	        log.debug("mbrNo:"+(String)jsonObject.get("mbrNo"));  
	        log.debug("mbrRefNo"+(String)jsonObject.get("mbrRefNo"));  
	        log.debug("amount"+(String)jsonObject.get("amount"));  
	        log.debug("orgTranDate"+(String)jsonObject.get("orgTranDate"));  
	        log.debug("orgRefNo"+(String)jsonObject.get("orgRefNo"));  
	        log.debug("timestamp"+(String)jsonObject.get("timestamp"));  
	        log.debug("charset"+(String)jsonObject.get("charset"));  
	        log.debug("clientType"+(String)jsonObject.get("clientType"));  
	        log.debug("signature"+(String)jsonObject.get("signature"));  
	        String signatureText = Sha256.encryptString((String) jsonObject.get("signature"));
	       params.add(new BasicNameValuePair("mbrNo",(String)jsonObject.get("mbrNo")));
	       params.add(new BasicNameValuePair("amount",(String)jsonObject.get("amount")));
	       params.add(new BasicNameValuePair("mbrRefNo",(String)jsonObject.get("mbrRefNo")));
	       params.add(new BasicNameValuePair("orgTranDate",(String)jsonObject.get("orgTranDate")));
	       params.add(new BasicNameValuePair("orgRefNo",(String)jsonObject.get("orgRefNo")));
	       params.add(new BasicNameValuePair("charset",(String)jsonObject.get("charset")));
	       params.add(new BasicNameValuePair("signature",signatureText));
	       params.add(new BasicNameValuePair("clientType",(String)jsonObject.get("clientType")));
	       params.add(new BasicNameValuePair("timestamp",(String)jsonObject.get("timestamp")));
	       log.debug("params:"+params);
	       log.debug("new UrlEncodedFormEntity(params):"+new UrlEncodedFormEntity(params, "UTF-8"));
	       log.debug("httpost:"+httpost);
	       
	       
	       
	       /*String 파라미터 값 전송*/
	   /*    String[] array;
	           String[] array2;
	           array = json.split("&");
	          HashMap<String,Object> map = new HashMap<String,Object>();
	           for(int i=0;i<array.length;i++ ){
	              array2 = array[i].split("=");
	              map.put(array2[0], array2[1]);
	           }
	           
	         log.debug("mbrNo:"+(String)map.get("mbrNo"));  
	         log.debug("mbrRefNo"+(String)map.get("mbrRefNo"));  
	         log.debug("amount"+(String)map.get("amount"));  
	         log.debug("orgTranDate"+(String)map.get("orgTranDate"));  
	         log.debug("orgRefNo"+(String)map.get("orgRefNo"));  
	         log.debug("timestamp"+(String)map.get("timestamp"));  
	         log.debug("charset"+(String)map.get("charset"));  
	         log.debug("clientType"+(String)map.get("clientType"));  
	         log.debug("signature"+(String)map.get("signature"));  
	        // Start time
	       params.add(new BasicNameValuePair("mbrNo", (String) map.get("mbrNo")));
	       params.add(new BasicNameValuePair("mbrRefNo",(String) map.get("mbrRefNo")));
	       params.add(new BasicNameValuePair("amount",(String) map.get("amount")));
	       params.add(new BasicNameValuePair("orgTranDate",(String) map.get("orgTranDate")));
	       params.add(new BasicNameValuePair("orgRefNo",(String) map.get("orgRefNo")));
	       params.add(new BasicNameValuePair("timestamp",(String) map.get("timestamp"))); 
	       params.add(new BasicNameValuePair("charset",(String)map.get("charset")));
	       params.add(new BasicNameValuePair("clientType",(String)map.get("clientType")));
	       params.add(new BasicNameValuePair("signature",(String) map.get("signature")));                
	       log.debug("params:"+params);
	       log.debug("new UrlEncodedFormEntity(params):"+new UrlEncodedFormEntity(params, "UTF-8"));
	       log.debug("httpost:"+httpost);*/
	      
	       
	       /*전송*/
	       httpost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
	       long endTime = System.nanoTime();
	       long lTime = endTime - startTime;
	       log.debug("TIME : " + lTime/1000000.0 + "(ms)");
	       
	       
	       
	       
	       
	       /*응답데이터를 받기위한 서비스호출*/
	      HttpResponse response0 = httpClient.execute(httpost);
	      HttpEntity resEntity = response0.getEntity();
	      UplresData = EntityUtils.toString(resEntity);
	      log.debug("■■유플러스 보낸데이터■■=="+json);         
	      log.debug("■■유플러스 응답데이터■■=="+UplresData);         
	      

	   } catch (URISyntaxException e) {
	      e.printStackTrace();
	   } catch (ClientProtocolException e) {
	      e.printStackTrace();
	   } catch (IOException e) {
	      e.printStackTrace();
	   }      

	return UplresData ;    
}

@SuppressWarnings("deprecation")
public static String randomSetValue(){
	
	Calendar calendar = Calendar.getInstance();
    java.util.Date date = calendar.getTime();
    String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));
	
    System.out.println(today);
	
	
	Random random = new Random();
    //0~999999난수발생 시키고 0~99999을 위해 100000을 더해준다. 
	int result = random.nextInt(1000000)+100000;
	//900000 ~ 999999은 100000더하면 7자리가 되므로 -100000을 해준다.
	if(result>1000000){
		//System.out.println("test");
	    result = result - 100000;
	}
	
	System.out.println(Integer.toString(result));
	 		
	System.out.println("U"+today+Integer.toString(result));		
	String randomSetValue = "U"+today+Integer.toString(result);
	return randomSetValue;
	
	
}




}
