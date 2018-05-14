package first.sample.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import javax.annotation.Resource;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import first.common.util.CommonUtils;
import first.sample.service.ReceiptService;
import first.sample.theRealShop.TheRealShopToLpoint;
import openpoint.aria.lib.ARIACipher;

@Controller
public class LPointController {
	private static final Logger log = Logger.getLogger(LPointController.class.getName());
	@Resource(name = "receiptService")
	private ReceiptService receiptService;

	/*
	 * Lpoint 연동 컨트로러
	 */
	@RequestMapping(value = "/receipt/reqTheRealToLpoint.do")
	@ResponseBody
	public String reqTheRealToLpoint(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		String str = null;
		String resData = null;
		StringBuffer paramData = new StringBuffer();

		log.debug(getClientIP(request));
		InetAddress tagetIp = InetAddress.getByName(getClientIP(request));
		boolean reachable = tagetIp.isReachable(5000);

		log.debug(reachable);

		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}

		try {
			while ((str = br.readLine()) != null) {
				paramData.append(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			resData = TheRealShopToLpoint.theRealToLpoint(request, response, paramData.toString());
		}
		if (!reachable) {
			log.debug("@@@@@@-망취소-@@@@@@");
			// 망취소프로세스
			resData = theRealToReLpoint(paramData.toString(), resData);
		}
		resData = new String(resData.getBytes("UTF-8"));
		return resData;
	}

	/*
	 * Lpoint 연동 컨트로러(평문)
	 */
	@RequestMapping(value = "/receipt/reqTheRealToLpointPlainText.do")
	@ResponseBody
	public JSONObject reqTheRealToLpointPlainText(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {

		String str = null;
		String resData = null;
		StringBuffer paramData = new StringBuffer();

		log.debug(getClientIP(request));
		InetAddress tagetIp = InetAddress.getByName(getClientIP(request));
		boolean reachable = tagetIp.isReachable(5000);

		log.debug(reachable);

		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}

		try {
			while ((str = br.readLine()) != null) {
				paramData.append(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			log.debug("paramData.toString():" + paramData.toString());
			resData = TheRealShopToLpoint.theRealToLpointPlainText(request, response, paramData.toString());
		}
		if (!reachable) {
			log.debug("@@@@@@-망취소-@@@@@@");
			// 망취소프로세스
			resData = theRealToReLpoint(paramData.toString(), resData);
		}
		resData = new String(resData.getBytes("UTF-8"));

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = null;
		jsonObject = (JSONObject) jsonParser.parse(resData);

		return jsonObject;

		// return resData;
	}

	/**
	 * 
	 * @param resendData
	 *            : 망취소 전문을 날릴 json데이터
	 * @param orgResData
	 *            : 망취소할 추적번호를 가져올 json데이터 (리턴값)
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static String theRealToReLpoint(String resendData, String orgResData) throws IOException, ParseException {
		// 타임아웃설정
		int timeout = 10;
		String lpointResData = null;
		String resData = null;
		String rspc = null;
		String flwNo = null;

		JSONParser jsonParser = new JSONParser();

		// JSON데이터를 넣어 JSON Object 로 만들어 준다.
		JSONObject jsonObject = null;
		JSONObject jsonObject2 = null;
		JSONObject jsonObject3 = null;

		try {
			String lpointUrl;
			/* URL 설정 */
			if (!CommonUtils.ipChk()) {
				System.out.println("★★★★★★★★★★★devop"); 
				
				//P003600002 가맹점 번호 개발
				lpointUrl = "https://devop.lpoint.com:8903/op";
			} else {
				
				//P005100006 가맹점 번호
				System.out.println("★★★★★★★★★★★op");
				lpointUrl = "https://op.lpoint.com/op";
			}
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

				resendData = cipher.decryptString(resendData, "UTF-8");
				orgResData = cipher.decryptString(orgResData, "UTF-8");

				log.debug("복호화 : " + orgResData);
				jsonObject = (JSONObject) jsonParser.parse(orgResData);
				jsonObject2 = (JSONObject) jsonParser.parse(jsonObject.get("control").toString());
				log.debug(jsonObject2.get("flwNo"));
				jsonObject3 = (JSONObject) jsonParser.parse(resendData);

				/*
				 * 추적 번호 생성 - 서비스ID(전문ID) + 기관코드 + 요청일(yyyyMMdd) + 일련번호(6자리)
				 */
				String flwno = (String) jsonObject2.get("flwNo");
				log.debug("flwno.length():" + flwno.length());
				if (flwno.length() == 22) {
					flwNo = flwno;
					log.debug("flwno11:" + flwno);
					rspc = "60";
				}
				JSONObject value = new JSONObject();
				// value.put("flwNo",flwNo);
				value.put("flwNo", flwNo);
				value.remove("rspC");
				value.put("rspC", "60");
				log.debug("value:" + value);
				jsonObject3.put("control", value);
				log.debug("value:" + value);
			} catch (org.json.simple.parser.ParseException e) {
				e.printStackTrace();
			}

			/* JSON 데이터를 문자열 엔티티로.. */
			httpost.addHeader("X-Openpoint", "burC=O180|encYn=Y"); // 암호화 전송 설정

			log.debug("요청 데이터(암호화전) : " + jsonObject3.toJSONString());
			jsonData = cipher.encryptString(jsonObject3.toJSONString(), "UTF-8"); // 암호화
																					// 처리
			log.debug("요청 데이터(암호화) : " + jsonData);

			StringEntity stringEntity = new StringEntity(jsonData, ContentType.create("application/json", "UTF-8"));
			httpost.setEntity(stringEntity);

			/* 응답데이터를 받기위한 서비스호출 */

			HttpResponse response0 = httpClient.execute(httpost);
			HttpEntity resEntity = response0.getEntity();

			if (resEntity != null) {
				resData = EntityUtils.toString(resEntity);
				log.debug("■■TheRealShopCom.theRealToLpointL.POINT 응답데이터■■==" + resData);
				log.debug("복호화 : " + cipher.decryptString(resData, "UTF-8"));
				resData = "";
				resData = cipher.encryptString(resData, "UTF-8");
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
	 * pos ip체크
	 * 
	 * @param request
	 * @return
	 */
	public String getClientIP(HttpServletRequest request) {

		String ip = request.getHeader("X-FORWARDED-FOR");

		if (ip == null || ip.length() == 0) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0) {
			ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
		}
		if (ip == null || ip.length() == 0) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
