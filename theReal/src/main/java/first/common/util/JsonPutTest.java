package first.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import openpoint.aria.lib.ARIACipher;

public class JsonPutTest {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub

		/*
		 * String jsonData = "{"; jsonData += "          \"control\" : ";
		 * jsonData += "          { "; jsonData +=
		 * "                    \"flwNo\" : \"1212\""; jsonData +=
		 * "                    \"rspC\" : \"1212\""; jsonData += "          }";
		 * jsonData += "           \"mblNo\" : \"010-0000-0000\""; jsonData +=
		 * "          , \"copMcno\" : \"0000000000\""; jsonData += "}";
		 * System.out.println("■■JSON 데이터 셋팅 성공 ■■");
		 * 
		 * JSONParser jsonParser = new JSONParser();
		 * 
		 * //JSON데이터를 넣어 JSON Object 로 만들어 준다. JSONObject jsonObject = null;
		 * JSONObject jsonObject2 = null; try { jsonObject = (JSONObject)
		 * jsonParser.parse(jsonData); jsonObject2 = (JSONObject)
		 * jsonParser.parse(jsonObject.get("control").toString());
		 * System.out.println(jsonObject.get("control"));
		 * System.out.println(jsonObject2.get("flwNo"));
		 * 
		 * } catch (org.json.simple.parser.ParseException e) {
		 * e.printStackTrace(); }
		 */
		int timeout = 10;
		String lpointResData = null;
		String resData = null;
		try {
			HttpPost httpost = new HttpPost(new URI("http://117.52.97.40/theReal/receipt/reqTheRealToLpoint.do"));
			RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout * 1000)
					.setConnectionRequestTimeout(timeout * 1000).setSocketTimeout(timeout * 1000).build();

			CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
			System.out.println("■■■■TheRealShopCom.theRealToLpoint연결성공■■■■ ");
			RequestConfig.Builder requestBuilder = RequestConfig.custom();
			HttpClientBuilder builder = HttpClientBuilder.create();
			builder.setDefaultRequestConfig(requestBuilder.build());
			HttpClient client = builder.build();

			/* 전문 데이타 셋팅 */
			String jsonData = "";

			JSONParser jsonParser = new JSONParser();

			// JSON데이터를 넣어 JSON Object 로 만들어 준다.
			JSONObject jsonObject = null;
			JSONObject jsonObject2 = null;
			byte[] keys = ARIACipher.readKeyFile("C:/O180.dat");
			ARIACipher cipher = new ARIACipher(keys); // 암호화 객체 생성
			try {

				// String decrypted = cipher.decryptString(json, "UTF-8");
				// System.out.println("복호화 : " + decrypted);
				String jsonData1 = "{";
				jsonData1 += "          \"control\" : "; // 컨트롤영역
				jsonData1 += "          { ";
				jsonData1 += "             \"flwNo\" : \"O100\""; // 추적번호
				jsonData1 += "          }";
				jsonData1 += "          ,\"wcc\" : \"3\""; // WCC(인증방식) /
															// 1:MSR/2:IC/3:KEY
															// IN
				jsonData1 += "          ,\"aprAkMdDc\":\"1\""; // 승인요청방식구분코드/
																// 1:POS
				jsonData1 += "          ,\"cstDrmV\" : \"2\""; // 고객식별 구분코드/
																// 1:카드번호 /2:
																// 고객번호
				jsonData1 += "          , \"cstDrmDc\" : \"0010558756 \""; // 고객식별값/
																			// 20자
				jsonData1 += "          , \"copMcno \": \" P001400002 \""; // 개방형
																			// 제휴가맹점번호/10자(가맹점
																			// 번호
																			// 지원받고
																			// 데이터전송예정)
				jsonData1 += "          , \"posNo\" : \"더리얼\""; // 포스번호
																// 8자(포스번호+점포코드)
				jsonData1 += "          , \"filler\" : \"\""; // filler
				jsonData1 += "}";

				jsonObject = (JSONObject) jsonParser.parse(jsonData1);
				jsonObject2 = (JSONObject) jsonParser.parse(jsonObject.get("control").toString());
				System.out.println(jsonObject2.get("flwNo"));

				/*
				 * 추적 번호 생성 - 서비스ID(전문ID) + 기관코드 + 요청일(yyyyMMdd) + 일련번호(6자리)
				 */

				String flwNo = jsonObject2.get("flwNo") + "O180"
						+ first.sample.theRealShop.TheRealShopToLpoint.genFlwNo();

				JSONObject value = new JSONObject();
				// value.put("flwNo",flwNo);
				value.put("flwNo", flwNo);
				value.put("rspC", "  ");
				jsonObject.put("control", value);
			} catch (org.json.simple.parser.ParseException e) {
				e.printStackTrace();
			}

			System.out.println("■■TheRealShopCom.theRealToLpointJSON 데이터 셋팅 성공 ■■");

			/* JSON 데이터를 문자열 엔티티로.. */
			/* 암호화 처리 적용시 주석 제거 */
			httpost.addHeader("X-Openpoint", "burC=O180|encYn=Y"); // 암호화 전송 설정

			System.out.println("요청 데이터(암호화전) : " + jsonObject.toJSONString());
			jsonData = cipher.encryptString(jsonObject.toJSONString(), "UTF-8"); // 암호화
																					// 처리
			System.out.println("요청 데이터(암호화) : " + jsonData);

			StringEntity stringEntity = new StringEntity(jsonData, ContentType.create("application/json", "UTF-8"));
			httpost.setEntity(stringEntity);

			/* 전송 */
			// client.execute(httpost);
			System.out.println("■■TheRealShopCom.theRealToLpoint전송종료■■");

			/* 응답데이터를 받기위한 서비스호출 */

			HttpResponse response0 = httpClient.execute(httpost);
			HttpEntity resEntity = response0.getEntity();

			System.out.println("■■resEntity■■" + resEntity);
			// is = resEntity.getContent();

			if (resEntity != null) {
				resData = EntityUtils.toString(resEntity);
				System.out.println("■■TheRealShopCom.theRealToLpointL.POINT 응답데이터■■==" + resData);
				resData = cipher.decryptString(resData, "UTF-8");
				System.out.println("복호화 : " + resData);

			} else {
				System.out.println("TheRealShopCom.theRealToLpoint응답없음 재적립 및 망취소요망");
			}
			// return resData;

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
