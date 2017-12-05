package first.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ErecTest {
	// public static void Test(){

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException, InvalidKeyException, ParseException {
		String ccoAprno = "2016092111847";
		System.out.println("■■■■시스템 호출 시작■■■■ ");

		int timeout = 10;
		String lpointResData = null;
		JSONParser jsonParser = new JSONParser();
		try {
			/* URL 설정 */
			// HttpPost httpost = new HttpPost(new
			// URI("http://117.52.97.40/theReal/receipt/reqTheRealToLpoint.do"));
			HttpPost httpost = new HttpPost(new URI("http://117.52.97.40/theReal/receipt/insertReceiptData.do"));
			// HttpPost httpost = new HttpPost(new
			// URI("http://localhost:8080/theReal/receipt/reqTheRealToLpoint.do"));
			// httpost.addHeader("Content-Type", "application/json");
			/* Time Out 시간설정 */
			RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout * 1000)
					.setConnectionRequestTimeout(timeout * 1000).setSocketTimeout(timeout * 1000).build();

			CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
			System.out.println("■■■■연결성공■■■■ ");
			RequestConfig.Builder requestBuilder = RequestConfig.custom();
			HttpClientBuilder builder = HttpClientBuilder.create();
			builder.setDefaultRequestConfig(requestBuilder.build());
			// HttpClient client = builder.build();

			/* 전문 데이타 셋팅 */
			String jsonData = "{\r\n";
			jsonData += "  \"userKey\": \"01071064573\",\r\n";
			jsonData += "  \"etcInfo\": {\r\n";
			jsonData += "    \"memo\": \"memo\",\r\n";
			jsonData += "    \"event\": \"event\"\r\n";
			jsonData += "  },\r\n";
			jsonData += "  \"shopInfo\": {\r\n";
			jsonData += "    \"name\": \"CU1\",\r\n";
			jsonData += "    \"bizNo\": \"012-34-56789\",\r\n";
			jsonData += "    \"address\": \"경기 수원시 팔달구 화서문로 19 354 호\",\r\n";
			jsonData += "    \"ceo\": \"김기봉\",\r\n";
			jsonData += "    \"phone\": \"0245645678\",\r\n";
			jsonData += "    \"cashier\": \"김기봉\"\r\n";
			jsonData += "  },\r\n";
			jsonData += "  \"salesInfo\": {\r\n";
			jsonData += "    \"salesBarCode\": \"2016082410005\",\r\n";
			jsonData += "    \"salesDate\": \"20161011132657\",\r\n";
			jsonData += "    \"printDate\": \"20161011132657\",\r\n";
			jsonData += "    \"salesType\": \"01234\",\r\n";
			jsonData += "    \"sumDfAmt\": \"10500\",\r\n";
			jsonData += "    \"sumFpAmt\": \"8727\",\r\n";
			jsonData += "    \"sumTaxAmt\": \"873\",\r\n";
			jsonData += "    \"sumSlAmt\": \"20100\",\r\n";
			jsonData += "    \"sumOpAmt\": \"20100\",\r\n";
			jsonData += "    \"chgAmt\": \"20100\",\r\n";
			jsonData += "    \"paidAmt\": \"20100\",\r\n";
			jsonData += "    \"rePrint\": \"0\",\r\n";
			jsonData += "    \"dtCnt\": \"3\"\r\n";
			jsonData += "  },\r\n";
			jsonData += "  \"salesList\": [\r\n";
			jsonData += "    {\r\n";
			jsonData += "      \"seqNo\": \"1\",\r\n";
			jsonData += "      \"pName\": \"메뉴_2000\",\r\n";
			jsonData += "      \"pPrice\": \"\",\r\n";
			jsonData += "      \"oPrice\": \"2000\",\r\n";
			jsonData += "      \"qty\": \"4\",\r\n";
			jsonData += "      \"dfAmt\": \"0\",\r\n";
			jsonData += "      \"fpAmt\": \"7273\",\r\n";
			jsonData += "      \"taxAmt\": \"727\",\r\n";
			jsonData += "      \"slAmt\": \"8000\",\r\n";
			jsonData += "      \"opAmt\": \"8000\"\r\n";
			jsonData += "    },\r\n";
			jsonData += "    {\r\n";
			jsonData += "      \"seqNo\": \"2\",\r\n";
			jsonData += "      \"pName\": \"아이템1\",\r\n";
			jsonData += "      \"pPrice\": \"\",\r\n";
			jsonData += "      \"oPrice\": \"800\",\r\n";
			jsonData += "      \"qty\": \"2\",\r\n";
			jsonData += "      \"dfAmt\": \"0\",\r\n";
			jsonData += "      \"fpAmt\": \"1454\",\r\n";
			jsonData += "      \"taxAmt\": \"146\",\r\n";
			jsonData += "      \"slAmt\": \"1600\",\r\n";
			jsonData += "      \"opAmt\": \"1600\"\r\n";
			jsonData += "    },\r\n";
			jsonData += "    {\r\n";
			jsonData += "      \"seqNo\": \"3\",\r\n";
			jsonData += "      \"pName\": \"메뉴_부가세없음\",\r\n";
			jsonData += "      \"pPrice\": \"\",\r\n";
			jsonData += "      \"oPrice\": \"3500\",\r\n";
			jsonData += "      \"qty\": \"3\",\r\n";
			jsonData += "      \"dfAmt\": \"10500\",\r\n";
			jsonData += "      \"fpAmt\": \"0\",\r\n";
			jsonData += "      \"taxAmt\": \"0\",\r\n";
			jsonData += "      \"slAmt\": \"10500\",\r\n";
			jsonData += "      \"opAmt\": \"10500\"\r\n";
			jsonData += "    }\r\n";
			jsonData += "  ],\r\n";
			jsonData += "  \"cashInfo\": {\r\n";
			jsonData += "    \"cashAmt\": \"20100\",\r\n";
			jsonData += "    \"cashType\": \"CH02\",\r\n";
			jsonData += "    \"cashNo\": \"\",\r\n";
			jsonData += "    \"cashAppNo\": \"\",\r\n";
			jsonData += "    \"cashDate\": \"20160824132658\"\r\n";
			jsonData += "  },\r\n";
			jsonData += "  \"cardInfo\": {\r\n";
			jsonData += "    \"cardAmt\": \"\",\r\n";
			jsonData += "    \"cardInstallment\": \"\",\r\n";
			jsonData += "    \"cardAppNo\": \"\",\r\n";
			jsonData += "    \"cardDate\": \"\",\r\n";
			jsonData += "    \"cardICom\": \"\",\r\n";
			jsonData += "    \"cardPCom\": \"\",\r\n";
			jsonData += "    \"cardNo\": \"\"\r\n";
			jsonData += "  },\r\n";
			jsonData += "  \"customerInfo\": {\r\n";
			jsonData += "    \"pointAmt\": \"\",\r\n";
			jsonData += "    \"getPoint\": 1005.0,\r\n";
			jsonData += "    \"customerCode\": \"10000024\",\r\n";
			jsonData += "    \"customerPoint\": 46608.0\r\n";
			jsonData += "  }\r\n";

			jsonData += "}\r\n";

			/* JSON 데이터를 문자열 엔티티로.. */
			StringEntity stringEntity = new StringEntity(jsonData, ContentType.create("application/json", "UTF-8"));
			httpost.setEntity(stringEntity);

			/* 전송 */
			// client.execute(httpost);
			System.out.println("■■전송종료■■");
			System.out.println("■■전송종료1■■" + stringEntity);

			/* 응답데이터를 받기위한 서비스호출 */

			String resData = "";
			HttpResponse response0 = httpClient.execute(httpost);
			System.out.println("■■response0■■" + response0);
			HttpEntity resEntity = response0.getEntity();
			System.out.println("■■resEntity■■" + resEntity);
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(response0.getEntity().getContent(), "UTF-8"));
			System.out.println("■■bufferedReader■■" + bufferedReader.toString());
			// is = resEntity.getContent();

			if (resEntity != null) {
				resData = EntityUtils.toString(resEntity);
				// resData = new
				// String(EntityUtils.toString(resEntity).getBytes("ISO-8859-1"),"utf-8");
				System.out.println("file.encoding=" + System.getProperty("file.encoding"));

				// resData = new String(resData.getBytes("UTF-8"));
				System.out.println("■■L.POINT 응답데이터■■==" + resData);
				// String encrypted = cipher.encryptString(resData, "UTF-8");
				// System.out.println("■■L.POINT 응답데이터■■=="+encrypted);
				System.out.println("■■L.POINT 응답데이터■■==" + resData);

			} else {
				System.out.println("응답없음 재적립 및 망취소요망");
			}
			// return resData;

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
