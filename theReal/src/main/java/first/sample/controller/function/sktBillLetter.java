package first.sample.controller.function;

import java.net.URI;
import java.util.HashMap;

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

import com.google.gson.Gson;

import first.common.util.AES256;
import first.common.util.CommonUtils;



public class sktBillLetter {
	private static final int Object = 0;
	Logger log = Logger.getLogger(this.getClass());
	
	public void sktReceipt(String paramData) {
		AES256 aes = null;
		if (CommonUtils.ipChk()) {
			aes = new AES256("MKTDEV7404123456");
		} else {
			aes = new AES256("MKT1107404123456");
		}
		
		try {
			System.out.println("================SKT BillLetter 전자영수증 Start ================");
			String url = "http://182.162.84.177:15000/theReal/receipt/sktReceipt.do";
			//String url = "http://182.162.84.177:18080/theReal/receipt/sktReceipt.do";
			//String url = "http://dev-wallet-partner.uplus.co.kr:19090/etc/ReceiptPush";

			int timeout = 10;

			HttpPost httpost = new HttpPost(new URI(url));
			RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout * 5000).setConnectionRequestTimeout(timeout * 5000).setSocketTimeout(timeout * 5000).build();
			CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();

			RequestConfig.Builder requestBuilder = RequestConfig.custom();
			HttpClientBuilder builder = HttpClientBuilder.create();
			builder.setDefaultRequestConfig(requestBuilder.build());
			org.apache.http.client.HttpClient client = builder.build();

			
			String tmpStr = aes.encryptStringToBase64(paramData.toString());
			System.out.println("paramEncoding ::: " + tmpStr); 
			StringEntity stringEntity = new StringEntity(tmpStr,ContentType.create("application/json", "UTF-8"));
			httpost.setEntity(stringEntity);

			HttpResponse response0 = httpClient.execute(httpost);
			System.out.println(response0);

			HttpEntity resEntity = response0.getEntity();

			log.debug("■■resEntity■■" + resEntity);

			String resData;
			if (resEntity != null) {
				resData = EntityUtils.toString(resEntity);
				log.debug("■■ 응답데이터■■==" + resData);
			}
			
			
			System.out.println("================SKT BillLetter 전자영수증 END ================");

		} catch (Exception e) {
			System.out.println("================SKT BillLetter 전자영수증 ERROR ================");

			e.printStackTrace();
			e.getMessage();

		}
	}
	
	
	public String sktStringToJson(HashMap<String, Object> paramData, String type) {
		Gson gson = new Gson();
		String responData="";
		if(type.equals("main")){
			responData += gson.toJson(paramData).toString();
			responData = responData.substring(0,responData.length()-1);
			responData += ", \"salesList\" : [ ";
		}else{
			responData += gson.toJson(paramData).toString() + " ,";
		}
		
		return responData;
	
	}
}
