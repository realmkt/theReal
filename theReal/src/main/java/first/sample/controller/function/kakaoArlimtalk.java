package first.sample.controller.function;


import java.net.URI;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import first.common.util.AES256;
import first.common.util.CommonUtils;

public class kakaoArlimtalk {
	private static final int Object = 0;
	Logger log = Logger.getLogger(this.getClass());
	
	@SuppressWarnings("unchecked")
	public String kakaoArlimtalk(Map<String, String> kakaoMap) {
<<<<<<< HEAD
		System.out.println("■■■■■■■■■■■■ 알림톡 Start tt ■■■■■■■■■■■■");
=======
		System.out.println("■■■■■■■■■■■■ 알림톡 Starttt ■■■■■■■■■■■■");
>>>>>>> 73e3aa28f6ce4773bf343658352bf1353ce6ce04
		
		JSONObject json = new JSONObject();
		String arlResult = "";
		try {
			String url = "http://www.apiorange.com/api/send/notice.do";
			int timeout = 10;
			
			//암호화
			AES256 aes = null;
			if (CommonUtils.ipChk()) {
				aes = new AES256("LGU+DEV258010247");
			} else {
				aes = new AES256("LGU+210987654321");
			}
			
			
			//알림톡 발송 정보
			json.put("tmp_number", kakaoMap.get("tmp_number"));
			json.put("kakao_sender", kakaoMap.get("kakao_sender"));
			json.put("kakao_phone", kakaoMap.get("kakao_phone"));
			json.put("kakao_name", kakaoMap.get("kakao_name"));
			json.put("kakao_080", kakaoMap.get("kakao_080"));
			json.put("TRAN_REPLACE_TYPE", kakaoMap.get("TRAN_REPLACE_TYPE"));
			json.put("kakao_add1", kakaoMap.get("kakao_add1"));
			json.put("kakao_add2", kakaoMap.get("kakao_add2"));
			
			String sd = kakaoMap.get("kakao_add3");
			if (sd.length() > 10 && sd.length() < 17  ) {
				sd = sd.substring(0, 4) + "." + sd.substring(4, 6) + "." + sd.substring(6, 8) + " " + sd.substring(8, 10) + ":" + sd.substring(10, 12) + ":" + sd.substring(12, 14);
			}else if(sd.length()==8){
				sd = sd.substring(0, 4) + "." + sd.substring(4, 6) + "." + sd.substring(6, 8);
			}
			kakaoMap.put("kakao_add3", sd);
			
			json.put("kakao_add3", kakaoMap.get("kakao_add3"));
			json.put("kakao_add4", CommonUtils.replaceComma(Integer.parseInt(kakaoMap.get("kakao_add4").toString())) + "원");
			json.put("kakao_url1_1", kakaoMap.get("kakao_url1_1"));
			
			System.out.println(json.toString());
			
			HttpPost httpost = new HttpPost(new URI(url));
			
			httpost.addHeader("Authorization", "NvMMEL2bEB1aeSeUK0Mgd5ymKwfQGUv6LNUo/vuY2f0="	);

			RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout * 1000).setConnectionRequestTimeout(timeout * 1000).setSocketTimeout(timeout * 1000).build();
			CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();

			RequestConfig.Builder requestBuilder = RequestConfig.custom();
			HttpClientBuilder builder = HttpClientBuilder.create();
			builder.setDefaultRequestConfig(requestBuilder.build());
			org.apache.http.client.HttpClient client = builder.build();

			StringEntity stringEntity = new StringEntity(json.toJSONString(),ContentType.create("application/json", "UTF-8"));
			httpost.setEntity(stringEntity);

			HttpResponse response0 = httpClient.execute(httpost);
			HttpEntity resEntity = response0.getEntity();

			log.debug("■■resEntity■■" + resEntity);
			
			String resData;
			if (resEntity != null) {
				resData = EntityUtils.toString(resEntity);
				log.debug("■■ 응답데이터■■==" + resData);
			}
			
			
			arlResult = "알림톡 정상 발송";
			
		} catch (Exception e) {
			arlResult = "알림톡 발송 실패 :: " + e.toString(); 
		}
		
		
		return arlResult;
		
	}
	
	
}
