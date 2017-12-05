package first.sample.theRealShop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TheRealShopToERec {
	private static final Logger log = Logger.getLogger(TheRealShopToERec.class.getName());
	public static String theRealToEReceipt(HttpServletRequest request, HttpServletResponse response, String json) throws IOException{
		String eReceiptResData = null;
		
		//타임아웃10초설정
		int timeout = 10;
		
		//Dev
		URL url = new URL("http://220.80.238.28/app/cmu/api/requestptom");
		//Real
		//URL url = new URL("https://op.lpoint.com/op");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setDoOutput(true);
		conn.setRequestMethod("POST"); // 보내는 타입
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("Accept-Charset", "UTF-8");
		conn.setConnectTimeout(timeout);
		//conn.setRequestProperty("Accept-Language", "ko-kr,ko;q=0.8,en-us;q=0.5,en;q=0.3");
		//conn.setRequestProperty("Content-Type", "application/json");
		
		//샘플데이터
		// 전송
		OutputStreamWriter osw = new OutputStreamWriter(
				conn.getOutputStream());
		
		try {
			osw.write(json);
			osw.flush();
			
			// 응답
			BufferedReader br = null;
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			
			String line = null;
			System.out.println("br.readLine():"+br.readLine());
			eReceiptResData = br.readLine();
			System.out.println("br:"+br);
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			
			// 닫기
			osw.close();
			br.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}    	
		
		return eReceiptResData;
	}
	
	public static String theRealToEReceipt2(HttpServletRequest request, HttpServletResponse response, String json) throws IOException, ParseException{
		log.debug("■■■■TheRealShopCom.theRealToErec시스템 호출 시작■■■■ ");
		int timeout = 10;
		String lpointResData = null;
		String resData = null;
		String rspc = null;
		String flwNo = null;
		
        JSONParser jsonParser = new JSONParser();
		 
		//JSON데이터를 넣어 JSON Object 로 만들어 준다.
		JSONObject jsonObject = null;

		try {
		   /*URL 설정 */
		    HttpPost httpost = new HttpPost(new URI("http://220.124.199.28/Ercp/app/cmu/api/requestptom.do"));
		    
		   /*Time Out 시간설정 */
		    RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout * 1000)
		   .setConnectionRequestTimeout(timeout * 1000)
		   .setSocketTimeout(timeout * 1000).build(); 
			
		    CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build(); 
			log.debug("■■■■TheRealShopCom.theRealToErec연결성공■■■■ ");
		    RequestConfig.Builder requestBuilder = RequestConfig.custom();
		    HttpClientBuilder builder = HttpClientBuilder.create();
		    builder.setDefaultRequestConfig(requestBuilder.build());
		    HttpClient client = builder.build();
		 
		    /*전문 데이타 셋팅*/
			String jsonData = "";
		    
			try {
				
				jsonObject = (JSONObject) jsonParser.parse(json);
			} catch (org.json.simple.parser.ParseException e) {
				e.printStackTrace();
			}				
			
		    log.debug("■■TheRealShopCom.theRealToErecJSON 데이터 셋팅 성공 ■■");
		    
			
			log.debug("요청 데이터(암호화전) : " + jsonObject.toJSONString());
			jsonData =jsonObject.toJSONString();
			log.debug("요청 데이터(암호화) : " + jsonData);

			
		    StringEntity stringEntity = new StringEntity(jsonData,
		    													ContentType.create("application/json", "UTF-8"));
		    httpost.setEntity(stringEntity);
		    
		    /*전송*/
		    log.debug("■■TheRealShopCom.theRealToErec전송종료■■");
		    
		    /*응답데이터를 받기위한 서비스호출*/
		    
			HttpResponse response0 = httpClient.execute(httpost);
			HttpEntity resEntity = response0.getEntity();
			
			log.debug("■■resEntity■■"+resEntity);
            			
			if(resEntity != null) {
				resData = EntityUtils.toString(resEntity);
				log.debug("■■TheRealShopCom.theRealToErec응답데이터■■=="+resData);
			
			}
		    
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return resData;
	}
	
	
	
	
	
	
}
