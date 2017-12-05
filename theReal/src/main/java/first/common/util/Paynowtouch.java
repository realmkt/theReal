package first.common.util;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
import first.common.util.Sha256;



public class Paynowtouch {


public static void posUPlus(HttpServletRequest request, HttpServletResponse response, String json) throws IOException{
		// TODO Auto-generated method stub
String UplresData = null;
		
		/*타임아웃30초설정*/
		int timeout = 30;
		
		/*timestamp 값구하기*/
		Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));
        System.out.println("timestamp 값 =="+today);
		
		/*signature 생성을 위한 변수선언  */
		String mbrNo 			= "100011" ; 						 /*가맹점번호*/
		String mbrRefNo 	= "10102201010011";		 /*주문번호*/
		String amount 		= "1000";							 /*결제금액*/	
		String apikey 			= "Q1NRVUFSRS0xMDAwMTEyMDE2MDYyNzExNTk1MjUwOTE4Nw=="; 								/*가맹점등록시 key생성됨*/
		String timestamp	= today; 	 					   /*타임스탬프*/
		System.out.println("\n■암호화처리전 signaturer값 확인■=="+mbrNo+"|"+mbrRefNo+"|"+amount+"|"+apikey+"|"+timestamp);
		
		 /*암호화처리 및 signature 생성*/  		
		String signature = Sha256.encryptString(mbrNo+"|"+mbrRefNo+"|"+amount+"|"+apikey+"|"+timestamp);
		System.out.println("\n ■암호화 데이터 확인■=="+signature);
		
		/*UTF8로 인코딩 처리*/
		String custName = "손종희";
		String goodName ="축구공";
		custName = URLEncoder.encode(custName,"UTF8");
		goodName = URLEncoder.encode(goodName, "UTF-8");
		System.out.println("\n■인코딩 값 확인■ =="+custName+ "\n "+goodName );
		
		
		/*요청 파라메타 세팅*/
		String sendData = "";
		sendData += "mbrNo=100011&";																					 					 /*가맹점번호*/
		sendData += "mbrRefNo=10102201010010&";																					 /*주문번호*/ 
		sendData += "amount=1000&";																										 /*결제금액*/
		sendData += "apiKey=Q1NRVUFSRS0xMDAwMTEyMDE2MDYyNzExNTk1MjUwOTE4Nw==&";	 /*가맹점등록시 key생성됨 ★★*/
		sendData += "timestamp="+today+"&";				           									 							     /*타임스탬프*/
		sendData += "customerName="+custName+"&";		    	                 											 /*구매자명*/
		sendData += "customerTelNo=01012341234&";				                 												 /*구매자 휴대폰번호*/
		sendData += "goodsName="+goodName+"&";					                 										     /*상품명*/
		sendData += "charset=UTF-8&";							                 															 /*캐릭터셋*/
		sendData += "clientType=pos&";		                 												    							 /*클라이언트 타입(고정)*/
		sendData += "signature="+signature;						                 														 /*signature*/
		System.out.println("■요청 파라메터 값 확인 ■=="+sendData);
		
	try {
		  /* URL 설정 */
		    HttpPost httpost = new HttpPost(new URI("http://117.52.97.40/theReal/receipt/reqTheRealToUpLus.do"));
		    RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout * 1000)
		   .setConnectionRequestTimeout(timeout * 1000)
		   .setSocketTimeout(timeout * 1000).build(); 
			
		    CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build(); 
		    RequestConfig.Builder requestBuilder = RequestConfig.custom();
		    HttpClientBuilder builder = HttpClientBuilder.create();
		    builder.setDefaultRequestConfig(requestBuilder.build());
		    List<NameValuePair> params = new ArrayList<NameValuePair>();
		    params.add(new BasicNameValuePair("mbrNo", "100011"));
		    params.add(new BasicNameValuePair("mbrRefNo", "10102201010011"));
		    params.add(new BasicNameValuePair("amount", "1000"));
		    params.add(new BasicNameValuePair("customerName",custName));
		    params.add(new BasicNameValuePair("customerTelNo", "01077003578"));
		    params.add(new BasicNameValuePair("goodsName", goodName));
		    params.add(new BasicNameValuePair("timestamp", today));
		    params.add(new BasicNameValuePair("apiKey", "Q1NRVUFSRS0xMDAwMTEyMDE2MDYyNzExNTk1MjUwOTE4Nw=="));
		    params.add(new BasicNameValuePair("clientType", "pos"));
		    params.add(new BasicNameValuePair("signature", signature));
		    
			System.out.println(new UrlEncodedFormEntity(params, "UTF-8"));
		    System.out.println("■sendDATA 확인 ==■"+params);
		    try {
		    	httpost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		    
		    	
		    } catch (UnsupportedEncodingException e) {
		        // writing error to Log
		        e.printStackTrace();
		    }
		    
		    /*응답데이터를 받기위한 서비스호출*/
			HttpResponse response0 = httpClient.execute(httpost);
			HttpEntity resEntity = response0.getEntity();
			UplresData = EntityUtils.toString(resEntity);
			System.out.println("■■유플러스 응답데이터■■=="+UplresData);			
	
			
			/*	if(resEntity != null) {
				resData = EntityUtils.toString(resEntity);
				System.out.println("■■유플러스 응답데이터■■=="+resData);
			
			}else {
			System.out.println("응답없음 재적립 및 망취소요망");	
			}*/
		    
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}    	
	}
}
