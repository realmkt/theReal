package first.sample.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import first.sample.service.ReceiptService;
import first.sample.theRealShop.TheRealShopToUplus;

@Controller
public class UplusController {
	private static final Logger log = Logger.getLogger(UplusController.class.getName());
	@Resource(name = "receiptService")
	private ReceiptService receiptService;

	// --------------------------------------
	// Lg paynowtouch 승인
	// --------------------------------------
	@RequestMapping(value = "/receipt/reqRealApproval.do")
	@ResponseBody
	public JSONObject reqRealApproval(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		String str;
		String UplresData = null;
		StringBuffer paramData = new StringBuffer();
		JSONParser jsonParser = new JSONParser();

		// JSON데이터를 넣어 JSON Object 로 만들어 준다.
		JSONObject jsonObject = null;
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
			// while ((str = br.readLine()) != null)
			while ((str = br.readLine()) != null) {
				paramData.append(str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} finally {

			UplresData = TheRealShopToUplus.reqTheRealApproval(request, response, paramData.toString());
			// UplresData = LgTest.theRealToUPlus(request, response,
			// paramData.toString());

			jsonObject = (JSONObject) jsonParser.parse(UplresData);
		}
		return jsonObject;
	}

	// --------------------------------------
	// Lg paynowtouch 승인
	// --------------------------------------
	@RequestMapping(value = "/receipt/reqRealApprovalTextPlain.do")
	@ResponseBody
	public JSONObject reqRealApprovalTextPlain(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		String str;
		String UplresData = null;
		StringBuffer paramData = new StringBuffer();
		JSONParser jsonParser = new JSONParser();

		// JSON데이터를 넣어 JSON Object 로 만들어 준다.
		JSONObject jsonObject = null;
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
			// while ((str = br.readLine()) != null)
			while ((str = br.readLine()) != null) {
				paramData.append(str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} finally {

			UplresData = TheRealShopToUplus.reqTheRealApprovalTextPlain(request, response, paramData.toString());
			// UplresData = LgTest.theRealToUPlus(request, response,
			// paramData.toString());

			jsonObject = (JSONObject) jsonParser.parse(UplresData);
		}
		return jsonObject;
	}

	// --------------------------------------
	// Lg paynowtouch 취소
	// --------------------------------------

	@RequestMapping(value = "/receipt/reqRealCancle.do")
	@ResponseBody
	public JSONObject reqRealCancle(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		String str;
		String UplresData = "";
		StringBuffer paramData = new StringBuffer();
		JSONParser jsonParser = new JSONParser();

		// JSON데이터를 넣어 JSON Object 로 만들어 준다.
		JSONObject jsonObject = null;

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
			// while ((str = br.readLine()) != null)
			while ((str = br.readLine()) != null) {
				paramData.append(str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} finally {

			UplresData = TheRealShopToUplus.reqTheRealCancle(request, response, paramData.toString());
			jsonObject = (JSONObject) jsonParser.parse(UplresData);
		}

		return jsonObject;
	}

	// --------------------------------------
	// Lg paynowtouch 취소
	// --------------------------------------

	@RequestMapping(value = "/receipt/reqRealCancleTextPlain.do")
	@ResponseBody
	public JSONObject reqRealCancleTextPlain(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		String str;
		String UplresData = "";
		StringBuffer paramData = new StringBuffer();
		JSONParser jsonParser = new JSONParser();

		// JSON데이터를 넣어 JSON Object 로 만들어 준다.
		JSONObject jsonObject = null;

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
			// while ((str = br.readLine()) != null)
			while ((str = br.readLine()) != null) {
				paramData.append(str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} finally {

			UplresData = TheRealShopToUplus.reqTheRealCancleTextPlain(request, response, paramData.toString());
			jsonObject = (JSONObject) jsonParser.parse(UplresData);
		}

		return jsonObject;
	}

}