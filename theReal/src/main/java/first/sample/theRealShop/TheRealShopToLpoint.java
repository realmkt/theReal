package first.sample.theRealShop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import first.common.util.CommonUtils;
import openpoint.aria.lib.ARIACipher;

public class TheRealShopToLpoint {
	private static final Logger log = Logger.getLogger(TheRealShopToLpoint.class.getName());

	/**
	 * lpoint 암호화통신
	 * 
	 * @param request
	 * @param response
	 * @param json
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static String theRealToLpoint(HttpServletRequest request, HttpServletResponse response, String json)
			throws IOException, ParseException {
		log.debug("■■■■TheRealShopCom.theRealToLpoint시스템 호출 시작■■■■ ");
		int timeout = 10;
		String lpointResData = null;
		String resData = null;
		String rspc = null;
		String flwNo = null;

		JSONParser jsonParser = new JSONParser();

		// JSON데이터를 넣어 JSON Object 로 만들어 준다.
		JSONObject jsonObject = null;
		JSONObject jsonObject2 = null;

		try {
			String lpointUrl;
			/* URL 설정 */
			if (!CommonUtils.ipChk()) {
				lpointUrl = "https://devop.lpoint.com:8903/op";
			} else {
				lpointUrl = "https://op.lpoint.com/op";
			}
			//lpointUrl = "https://devop.lpoint.com:8903/op";
			log.debug("CommonUtils.ipChk():" + CommonUtils.ipChk());
			log.debug("lpointUrl:" + lpointUrl);
			HttpPost httpost = new HttpPost(new URI(lpointUrl));
			/* Time Out 시간설정 */
			RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout * 1000).setConnectionRequestTimeout(timeout * 1000).setSocketTimeout(timeout * 1000).build();

			CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
			log.debug("■■■■TheRealShopCom.theRealToLpoint연결성공■■■■ ");
			RequestConfig.Builder requestBuilder = RequestConfig.custom();
			HttpClientBuilder builder = HttpClientBuilder.create();
			builder.setDefaultRequestConfig(requestBuilder.build());
			HttpClient client = builder.build();

			/* 전문 데이타 셋팅 */
			String jsonData = "";

			byte[] keys = ARIACipher.readKeyFile("/LOGS/O180.dat");
			ARIACipher cipher = new ARIACipher(keys); // 암호화 객체 생성
			try {

				log.debug("암호화 : " + json);
				json = cipher.decryptString(json, "UTF-8");
				log.debug("복호화 : " + json);
				jsonObject = (JSONObject) jsonParser.parse(json);
				jsonObject2 = (JSONObject) jsonParser.parse(jsonObject.get("control").toString());
				log.debug(jsonObject2.get("flwNo"));

				/*
				 * 추적 번호 생성 - 서비스ID(전문ID) + 기관코드 + 요청일(yyyyMMdd) + 일련번호(6자리)
				 */
				String flwno = (String) jsonObject2.get("flwNo");
				log.debug("flwno.length():" + flwno.length());
				if (flwno.length() == 22) {
					flwNo = flwno;
				} else {
					flwNo = jsonObject2.get("flwNo") + "O180" + genFlwNo();
				}
				if ("60".equals(jsonObject2.get("rspC"))) {
					rspc = "60";
				} else {
					rspc = "  ";
				}
				JSONObject value = new JSONObject();
				value.put("flwNo", flwNo);
				value.put("rspC", rspc);
				jsonObject.put("control", value);
			} catch (org.json.simple.parser.ParseException e) {
				e.printStackTrace();
			}

			log.debug("■■TheRealShopCom.theRealToLpointJSON 데이터 셋팅 성공 ■■");

			/* JSON 데이터를 문자열 엔티티로.. */
			/* 암호화 처리 적용시 주석 제거 */
			httpost.addHeader("X-Openpoint", "burC=O180|encYn=Y"); // 암호화 전송 설정

			log.debug("요청 데이터(암호화전) : " + jsonObject.toJSONString());
			jsonData = cipher.encryptString(jsonObject.toJSONString(), "UTF-8"); // 암호화
																					// 처리
			log.debug("요청 데이터(암호화) : " + jsonData);

			StringEntity stringEntity = new StringEntity(jsonData, ContentType.create("application/json", "UTF-8"));
			httpost.setEntity(stringEntity);

			/* 전송 */
			log.debug("■■TheRealShopCom.theRealToLpoint전송종료■■");

			/* 응답데이터를 받기위한 서비스호출 */

			HttpResponse response0 = httpClient.execute(httpost);
			HttpEntity resEntity = response0.getEntity();

			log.debug("■■resEntity■■" + resEntity);

			if (resEntity != null) {
				resData = EntityUtils.toString(resEntity);
				log.debug("■■TheRealShopCom.theRealToLpointL.POINT 응답데이터■■==" + resData);
				log.debug("복호화 : " + cipher.decryptString(resData, "UTF-8"));

			} else {
				log.debug("TheRealShopCom.theRealToLpoint응답없음 재적립 및 망취소요망");
				JSONObject changeValue = new JSONObject();
				jsonObject.get("control");
				changeValue.put("flwNo", flwNo);
				changeValue.put("rspC", "60");
				jsonObject.put("control", changeValue);

				log.debug("■■TheRealShopCom.theRealToLpointJSON 데이터 셋팅 성공 ■■");

				/* JSON 데이터를 문자열 엔티티로.. */
				/* 암호화 처리 적용시 주석 제거 */
				httpost.addHeader("X-Openpoint", "burC=O180|encYn=Y"); // 암호화 전송
																		// 설정

				log.debug("요청 데이터(암호화전) : " + jsonObject.toJSONString());
				jsonData = cipher.encryptString(jsonObject.toJSONString(), "UTF-8"); // 암호화
																						// 처리
				log.debug("요청 데이터(암호화) : " + jsonData);

				StringEntity stringEntity2 = new StringEntity(jsonData,
						ContentType.create("application/json", "UTF-8"));
				httpost.setEntity(stringEntity2);

				/* 전송 */
				log.debug("■■TheRealShopCom.theRealToLpoint전송종료■■");

				/* 응답데이터를 받기위한 서비스호출 */

				HttpResponse response02 = httpClient.execute(httpost);
				HttpEntity resEntity2 = response02.getEntity();

				log.debug("■■resEntity■■" + resEntity2);

				if (resEntity2 != null) {
					resData = EntityUtils.toString(resEntity2);
					resData = "";
					resData = cipher.encryptString(resData, "UTF-8");
					log.debug("■■TheRealShopCom.theRealToLpointL.POINT 응답데이터■■==" + resData);
					log.debug("복호화 : " + cipher.decryptString(resData, "UTF-8"));
				}
			}

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		return resData;
	}

	/**
	 * POS - 더리얼 평문 통신 함수
	 * 
	 * @param request
	 * @param response
	 * @param json
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static String theRealToLpointPlainText(HttpServletRequest request, HttpServletResponse response, String json)
			throws IOException, ParseException {
		log.debug("■■■■TheRealShopCom.theRealToLpoint시스템 호출 시작■■■■ ");
		int timeout = 10;
		String lpointResData = null;
		String resData = null;
		String rspc = null;
		String flwNo = null;

		JSONParser jsonParser = new JSONParser();

		// JSON데이터를 넣어 JSON Object 로 만들어 준다.
		JSONObject jsonObject = null;
		JSONObject jsonObject2 = null;

		try {
			String lpointUrl;
			/* URL 설정 */
			if (!CommonUtils.ipChk()) {
				lpointUrl = "https://devop.lpoint.com:8903/op";
				System.out.println("lpointUrl11■■■■■■■■■■■"+lpointUrl);
			} else {
				lpointUrl = "https://op.lpoint.com/op";
				System.out.println("lpointUrl22■■■■■■■■■■■ "+lpointUrl);
			}
			//lpointUrl = "https://op.lpoint.com/op";
			log.debug("CommonUtils.ipChk():" + CommonUtils.ipChk());
			log.debug("lpointUrl:" + lpointUrl);

			HttpPost httpost = new HttpPost(new URI(lpointUrl));
			/* Time Out 시간설정 */
			RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout * 1000)
					.setConnectionRequestTimeout(timeout * 1000).setSocketTimeout(timeout * 1000).build();

			CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
			log.debug("■■■■TheRealShopCom.theRealToLpoint연결성공■■■■ ");
			RequestConfig.Builder requestBuilder = RequestConfig.custom();
			HttpClientBuilder builder = HttpClientBuilder.create();
			builder.setDefaultRequestConfig(requestBuilder.build());
			HttpClient client = builder.build();

			/* 전문 데이타 셋팅 */
			String jsonData = "";

			byte[] keys = ARIACipher.readKeyFile("/LOGS/O180.dat");
			ARIACipher cipher = new ARIACipher(keys); // 암호화 객체 생성
			try {

				log.debug("암호화 : " + json);
				// json = cipher.decryptString(json, "UTF-8");
				log.debug("복호화 : " + json);
				jsonObject = (JSONObject) jsonParser.parse(json);
				jsonObject2 = (JSONObject) jsonParser.parse(jsonObject.get("control").toString());
				log.debug(jsonObject2.get("flwNo"));

				/*
				 * 추적 번호 생성 - 서비스ID(전문ID) + 기관코드 + 요청일(yyyyMMdd) + 일련번호(6자리)
				 */
				String flwno = (String) jsonObject2.get("flwNo");
				log.debug("flwno.length():" + flwno.length());
				if (flwno.length() == 22) {
					flwNo = flwno;
				} else {
					flwNo = jsonObject2.get("flwNo") + "O180" + genFlwNo();
				}
				if ("60".equals(jsonObject2.get("rspC"))) {
					rspc = "60";
				} else {
					rspc = "  ";
				}
				JSONObject value = new JSONObject();
				value.put("flwNo", flwNo);
				value.put("rspC", rspc);
				jsonObject.put("control", value);
			} catch (org.json.simple.parser.ParseException e) {
				e.printStackTrace();
			}

			log.debug("■■TheRealShopCom.theRealToLpointJSON 데이터 셋팅 성공 ■■");

			/* JSON 데이터를 문자열 엔티티로.. */
			/* 암호화 처리 적용시 주석 제거 */
			httpost.addHeader("X-Openpoint", "burC=O180|encYn=Y"); // 암호화 전송 설정

			log.debug("요청 데이터(암호화전) : " + jsonObject.toJSONString());
			jsonData = cipher.encryptString(jsonObject.toJSONString(), "UTF-8"); // 암호화
																					// 처리
			log.debug("요청 데이터(암호화) : " + jsonData);

			StringEntity stringEntity = new StringEntity(jsonData, ContentType.create("application/json", "UTF-8"));
			httpost.setEntity(stringEntity);

			/* 전송 */
			log.debug("■■TheRealShopCom.theRealToLpoint전송종료■■");

			/* 응답데이터를 받기위한 서비스호출 */

			HttpResponse response0 = httpClient.execute(httpost);
			HttpEntity resEntity = response0.getEntity();

			log.debug("■■resEntity■■" + resEntity);

			if (resEntity != null) {
				resData = EntityUtils.toString(resEntity);
				resData = cipher.decryptString(resData, "UTF-8");
				log.debug("■■TheRealShopCom.theRealToLpointL.POINT 응답데이터■■==" + resData);
				// log.debug("복호화 : " + cipher.decryptString(resData, "UTF-8"));

			} else {
				log.debug("TheRealShopCom.theRealToLpoint응답없음 재적립 및 망취소요망");
				JSONObject changeValue = new JSONObject();
				jsonObject.get("control");
				changeValue.put("flwNo", flwNo);
				changeValue.put("rspC", "60");
				jsonObject.put("control", changeValue);

				log.debug("■■TheRealShopCom.theRealToLpointJSON 데이터 셋팅 성공 ■■");

				/* JSON 데이터를 문자열 엔티티로.. */
				/* 암호화 처리 적용시 주석 제거 */
				httpost.addHeader("X-Openpoint", "burC=O180|encYn=Y"); // 암호화 전송
																		// 설정

				log.debug("요청 데이터(암호화전) : " + jsonObject.toJSONString());
				jsonData = cipher.encryptString(jsonObject.toJSONString(), "UTF-8"); // 암호화
																						// 처리
				log.debug("요청 데이터(암호화) : " + jsonData);

				StringEntity stringEntity2 = new StringEntity(jsonData,
						ContentType.create("application/json", "UTF-8"));
				httpost.setEntity(stringEntity2);

				/* 전송 */
				log.debug("■■TheRealShopCom.theRealToLpoint전송종료■■");

				/* 응답데이터를 받기위한 서비스호출 */

				HttpResponse response02 = httpClient.execute(httpost);
				HttpEntity resEntity2 = response02.getEntity();

				log.debug("■■resEntity■■" + resEntity2);

				if (resEntity2 != null) {
					resData = EntityUtils.toString(resEntity2);
					resData = "";
					// resData = cipher.encryptString(resData, "UTF-8");
					log.debug("■■TheRealShopCom.theRealToLpointL.POINT 응답데이터■■==" + resData);
					log.debug("복호화 : " + cipher.decryptString(resData, "UTF-8"));
				}
			}

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		return resData;
	}

	/**
	 * 추적번호생성로직
	 * 
	 * @return
	 * @throws IOException
	 */
	public static String genFlwNo() throws IOException {
		Date dt = new Date();
		String makeFile = "";
		SimpleDateFormat dtFmt = new SimpleDateFormat("yyyyMMdd");
		if (CommonUtils.ipChk()) {
			// makeFile = "/LOGS/"+dtFmt.format(dt)+".txt";
			makeFile = "/home/realmkt/tomcat7/webapps/theReal/temp/" + dtFmt.format(dt) + ".txt";
		} else {
			makeFile = "./temp/" + dtFmt.format(dt) + ".txt";
		}
		String s = "";
		String resultFlwNo = "";
		File file = new File(makeFile);

		if (!file.exists()) {
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(makeFile));
				s = "1";
				out.write(s);
				out.newLine();
				out.close();
				resultFlwNo = dtFmt.format(dt) + "000001";
			} catch (IOException e) {
				System.err.println(e); // 에러가 있다면 메시지 출력
			}
		} else {
			try {
				BufferedReader in = new BufferedReader(new FileReader(makeFile));
				Integer s1 = Integer.parseInt(in.readLine());
				resultFlwNo = dtFmt.format(dt) + String.format("%1$06d", s1 + 1);
				in.close();
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter(makeFile));
					s = Integer.toString(s1 + 1);
					out.write(s);
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultFlwNo;
	}

}
