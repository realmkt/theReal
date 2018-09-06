package first.sample.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.gson.Gson;

import first.common.common.CommandMap;
import first.common.filter.SmsParse;
import first.common.util.AES256;
import first.common.util.CharTokenizer;
import first.common.util.CommonUtils;
import first.common.util.FileUtils;
import first.common.util.GoogleChartDTO;
import first.common.util.JsonParser;
import first.common.util.Sha256;
import first.common.util.Var;
import first.common.util.consoleMail;
import first.sample.service.ReceiptService;
import first.sample.theRealShop.TheRealShopToUplus;
import first.sample.controller.function.*;

@Controller
public class ReceiptController {
	private static final int Object = 0;
	Logger log = Logger.getLogger(this.getClass());
	/*
	 * @Value("#{code['code.response.str1']}") private String str1;
	 * 
	 * @Value("#{code['code.response.str2']}") private String str2;
	 * 
	 * @Value("#{code['code.response.str3']}") private String str3;
	 */
	@Resource(name = "receiptService")
	private ReceiptService receiptService;
	// private Object reqJsonObj;
	int i = 0;

	/*
	 * 버전 체크
	 */
	@ResponseBody
	@RequestMapping(value = "/receipt/versionChk.do")
	public Object versionChk(CommandMap commandMap) throws Exception {
		String version = (String) commandMap.get("version");

		int result = 1;
		// 허용 가능 버전
		String usingVersion = "v1.0.5";

		if (!usingVersion.equals(version)) {
			result = 0;
		}
		return result;
	}

	/*
	 * 샘플 게시판 리스트 화면
	 */
	@RequestMapping(value = "/receipt/openBoardList.do")
	public ModelAndView openBoardList(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/sample/boardList");

		Map<String, Object> resultMap = receiptService.selectBoardList(commandMap.getMap());

		mv.addObject("paginationInfo", resultMap.get("paginationInfo"));
		mv.addObject("list", resultMap.get("result"));

		return mv;
	}

	// -----------------------------------------------------------------------
	// 한글 인코딩
	// -----------------------------------------------------------------------
	static public StringBuffer convertStringType(HttpServletRequest request, StringBuffer paramData) throws Exception {
		BufferedReader br = null;
		String[] charsets = { "UTF-8", "EUC-KR", "ISO-8859-1", "CP1251", "KSC5601" };
		String returnStr = null;

		String hangle = "/^[가-힣]*$";
		String test = "asdasd";
		if (hangle.contains(test)) {
			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■");
			System.out.println("■■■■■■■■■■■■YES■■■■■■■■■■■■");
			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		}

		br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		String str1 = null;

		while ((str1 = br.readLine()) != null) {
			System.out.println("str :::: " + str1);
			paramData.append(str1);

		}

		br = new BufferedReader(new InputStreamReader(request.getInputStream(), "EUC-KR"));

		String str2 = null;

		while ((str2 = br.readLine()) != null) {
			System.out.println("str :::: " + str2);
			paramData.append(str2);

		}

		System.out.println("STR1 :::::::::::: " + str1);
		System.out.println("STR2 :::::::::::: " + str2);

		/*
		 * for (int i = 0; i < charsets.length; i++) { br = new
		 * BufferedReader(new InputStreamReader(request.getInputStream(),
		 * charsets[i])); String str = null; String tos = null;
		 * 
		 * System.out.println("// type : " + charsets[i]);
		 * 
		 * //System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■" +
		 * br.readLine()); while ((str = br.readLine()) != null) {
		 * System.out.println("str :::: "+ str ); paramData.append(str);
		 * 
		 * } System.out.println("TTTTTTTTTTTTTTT"+ paramData.toString()); tos =
		 * paramData.toString(); System.out.println(tos);
		 * 
		 * if(hangle.contains(tos)){ returnStr = tos; }
		 * 
		 * }
		 */

		System.out.println("return message :: " + returnStr);

		return paramData;
	}

	

	// -----------------------------------------------------------------------
	// 코드 바꿔주는 메서드
	// -----------------------------------------------------------------------
	static public String switchPayType(String paymentType) {
		System.out.println("복합결제 코드 스위치 " + paymentType);
		String payType = "";
		switch (paymentType) {
		case "01":
			payType = "현금";
			break;
		case "02":
			payType = "카드";
			break;
		case "03":
			payType = "모바일";
			break;
		case "04":
			payType = "쿠폰";
			break;
		case "05":
			payType = "포인트";
			break;

		default:
			break;
		}

		return payType;
	}

	// -----------------------------------------------------------------------
	// SMS 금액인지 아닌지 구분
	// -----------------------------------------------------------------------
	public static boolean isAmount(String st) {
		boolean flag = true;
		if (st.contains("원")) {
			if (!(st.contains("누적") || st.contains("잔액"))) {
				try {
					st = st.replace("원", "").replace(",", "");
					int integ = Integer.parseInt(st);
					flag = true;
				} catch (Exception e) {
					flag = false;
				}
			} else {
				flag = false;
			}
		} else {
			flag = false;
		}
		return flag;
	}

	/*
	 * d 샘플 게시판 작성 화면
	 */
	@RequestMapping(value = "/receipt/openBoardWrite.do")
	public ModelAndView openBoardWrite(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/sample/boardWrite");

		return mv;
	}
	
	
	/*
	 * 포스 위젯 업데이트 모듈
	 */
	@ResponseBody
	@RequestMapping(value = "/update/currentUpdateVer.do")  
	public Object currentUpdateVer(CommandMap commandMap, HttpServletRequest request) throws Exception {
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		StringBuffer paramData = new StringBuffer();
		String str = null;
		JSONParser json = new JSONParser();
		
		while ((str = br.readLine()) != null) {
			paramData.append(str);
			
		}
		JSONObject reqVerObj = (JSONObject) json.parse(paramData.toString());
		//System.out.println("PD "+paramData );  request
		//String path = this.getClass().getResource("/").getPath();

		FileInputStream file = new FileInputStream("/home/ftpuser/get_update_ver.txt");
		
		Scanner s = new Scanner(file);
		String nowVer = "";
		
		while(s.hasNext()){
			nowVer += s.nextLine();
		}
		
		JSONObject nowVerObj = (JSONObject) json.parse(nowVer);
		
		
		if(reqVerObj.get("request").equals("get_widget_Update_Ver")){
			System.out.println("get_widget_Update_Ver :: "+nowVerObj.get("get_widget_Update_Ver"));
			return nowVerObj.get("get_widget_Update_Ver");
		}else{
			System.out.println("get_prg_Update_Ver :: "+nowVerObj.get("get_prg_Update_Ver"));
			return nowVerObj.get("get_prg_Update_Ver");
		}
		
	}

	
	

	/*
	 * 샘플 게시판 글 작성
	 */
	@RequestMapping(value = "/receipt/insertBoard.do")
	public ModelAndView insertBoard(CommandMap commandMap, HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView("redirect:/theReal/receipt/openBoardList.do");
		receiptService.insertBoard(commandMap.getMap(), request);
		return mv;

	}

	/*
	 * 샘플 게시판 상세화면
	 */
	@RequestMapping(value = "/receipt/openBoardDetail.do")
	public ModelAndView openBoardDetail(CommandMap commandMap) throws Exception {

		ModelAndView mv = new ModelAndView("/sample/boardDetail");

		Map<String, Object> map = receiptService.selectBoardDetail(commandMap.getMap());
		mv.addObject("map", map.get("map"));
		mv.addObject("list", map.get("list"));

		return mv;
	}

	/*
	 * 샘플 게시판 수정화면
	 */
	@RequestMapping(value = "/receipt/openBoardUpdate.do")
	public ModelAndView openBoardUpdate(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/sample/boardUpdate");

		Map<String, Object> map = receiptService.selectBoardDetail(commandMap.getMap());
		mv.addObject("map", map.get("map"));
		mv.addObject("list", map.get("list"));

		return mv;
	}

	/*
	 * 샘플 게시판 수정
	 */
	@RequestMapping(value = "/receipt/updateBoard.do")
	public ModelAndView updateBoard(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/theReal/receipt/openBoardDetail.do");

		receiptService.updateBoard(commandMap.getMap(), request);

		mv.addObject("IDX", commandMap.get("IDX"));
		return mv;
	}

	/*
	 * 샘플 게시판 삭제
	 */
	@RequestMapping(value = "/receipt/deleteBoard.do")
	public ModelAndView deleteBoard(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/theReal/receipt/openBoardList.do");

		receiptService.deleteBoard(commandMap.getMap());

		return mv;
	}

	/**
	 * 사용안함
	 * 
	 * @param commandMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/login.do")
	public ModelAndView insertReceiptInfo(CommandMap commandMap, ServletRequest request) throws Exception {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		ModelAndView mv = new ModelAndView("/login/loginResult");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("password", password);

		// String resultInt = receiptService.selectLgnChk(map);
		// receiptService.insertReceiptInfo(commandMap.getMap());
		/*
		 * if("1".equals(resultInt)){ log.debug("@@mv SUCESS@@@");
		 * mv.addObject("chkStr",resultInt); } log.debug("@@mv START@@@"+mv);
		 */

		return mv;
	}

	/**
	 * 회원가입 시 중복아이디 체크로직
	 * 
	 * @param id
	 * @param password
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/memberChk.do")
	@ResponseBody
	public int memberChk(String id, String password, HttpSession session) throws Exception {
		System.out.println("id:" + id);
		System.out.println("password:" + password);

		// mckiro MD5 비밀번호 암호화
		// password = MD5.encryptString(password);
		System.out.println("[INFO]1: " + id + " : " + password);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("password", password);
		System.out.println("map:" + map);
		Integer resultInt = receiptService.selectMemberChk(map);
		if (0 == resultInt) {
			receiptService.insertMember(map);
			session.setAttribute("id", id);
		}
		return resultInt;
	}

	/**
	 * 아이디/비밀번호 체크 후 값 리턴 1=성공 0=실패
	 * 
	 * @param id
	 * @param password
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/loginChk.do")
	@ResponseBody
	public int login(String id, String password, HttpSession session) throws Exception {

		// mckiro MD5 비밀번호 암호화
		// password = MD5.encryptString(password);
		System.out.println("[INFO]2 : " + id + " : " + password);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("password", password);
		Integer resultInt = receiptService.selectLgnChk(map);
		if (1 == resultInt) {
			session.setAttribute("id", id);
		}
		;
		return resultInt;
	}

	/**
	 * 로그인 성공 시 메인화면에 보여줄 정보 리턴
	 * 
	 * @param commandMap
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/appLoginSucess.do")
	@ResponseBody
	public Object appLoginSucess(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		String id = request.getParameter("id");
		// String id = (String) session.getAttribute("id");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);

		Map<String, Object> resultMap = receiptService.selectUserInfoList(map);
		resultMap.put("code", "OK");

		return resultMap;
	}

	/**
	 * 사용안함
	 * 
	 * @param commandMap
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/loginSucess.do")
	public ModelAndView loginSucess(CommandMap commandMap, HttpSession session, ServletRequest request)
			throws Exception {
		String id = request.getParameter("id");
		// String id = (String) session.getAttribute("id");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		Map<String, Object> resultMap = receiptService.selectUserInfoList(map);
		ModelAndView mv = new ModelAndView("/login/loginResult");
		if ("".equals(id) || id == null) {
			mv = new ModelAndView("redirect:/receipt/index.do");
		}
		mv.addObject("list", resultMap);

		return mv;
	}

	/**
	 * 샘플포스 작성화면
	 * 
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/posDemo.do")
	public ModelAndView posDemo(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/index");
		return mv;
	}

	/**
	 * 샘플 포스입력화면 저장
	 * 
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/posResult.do")
	public ModelAndView posResult(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/login/loginResult2");

		JSONParser parser = new JSONParser();

		ObjectMapper om = new ObjectMapper();

		// Map or List Object 를 JSON 문자열로 변환
		String jsonStr = om.writeValueAsString(commandMap.getMap());
		// Object obj = parser.parse(new
		// FileReader("C:\\testchart\\test2.json"));
		Object obj = parser.parse(jsonStr);

		JSONObject jsonObject = (JSONObject) obj;

		String to = (String) jsonObject.get("jsonObject");

		Integer autoKeyValue = receiptService.insertReceiptInfo(commandMap.getMap());
		mv.addObject("dtl", commandMap.getMap());
		// {PAY_GOODS=god8702259999, ETP_NM=god8702259999, TNB=8702259999,
		// SEQ=44, PAY_AM=8702259999, EMAIL=god8702259999@naver.com,
		// BZN=8702259999, PAY_DIV_CD=A1, PAY_DIV=god8702259999}
		// commandMap.getMap().get("divCd");
		String payGoods[] = { "PAY_GOODS", "ETP_NM", "TNB", "SEQ", "PAY_AM", "EMAIL", "BZN", "PAY_DIV_CD", "PAY_DIV" };
		for (int i = 0; i < commandMap.getMap().size(); i++) {

		}
		return mv;
	}

	/**
	 * 사용안함
	 * 
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/index.do")
	public ModelAndView login(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/login/index");
		return mv;
	}

	/**
	 * 사용안함
	 * 
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/member.do")
	public ModelAndView member(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/login/member");
		return mv;
	}

	/**
	 * 사용안함
	 * 
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/chart.do")
	public ModelAndView chart(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/sample/chart");
		return mv;
	}

	/**
	 * 사용안함
	 * 
	 * @param commandMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/logout.do")
	public ModelAndView logout(CommandMap commandMap, HttpSession session) throws Exception {
		session.invalidate();
		ModelAndView mv = new ModelAndView("/login/index");
		return mv;
	}

	/**
	 * 사용안함
	 * 
	 * @param commandMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/E01.do")
	public ModelAndView E01(CommandMap commandMap, HttpSession session) throws Exception {
		String id = (String) session.getAttribute("id");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		ModelAndView mv = new ModelAndView("/E/E01");
		Map<String, Object> resultMap = receiptService.selectE01List(map);
		if ("".equals(id) || id == null) {
			mv = new ModelAndView("redirect:/receipt/index.do");
		}
		mv.addObject("list", resultMap);
		return mv;
	}

	/**
	 * 최근사용내역 조회
	 * 
	 * @param commandMap
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/appA01.do")
	@ResponseBody
	public Object appA01(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		String id = request.getParameter("id");
		// String id = (String) session.getAttribute("id");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);

		Map<String, Object> resultMap = receiptService.selectE01List(map);
		resultMap.put("code", "OK");

		return resultMap;
	}

	/**
	 * 영수증 확인 조회
	 * 
	 * @param commandMap
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/appB01.do")
	@ResponseBody
	public Object appB01(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		// String id = (String) session.getAttribute("id");
		String id = request.getParameter("id");
		String year = request.getParameter("year");
		String mon = request.getParameter("mon");
		String lsatDay = request.getParameter("lsatDay");
		String orderByCd = (String) commandMap.getMap().get("orderByCd");
		System.out.println("orderByCd:" + orderByCd);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("orderByCd", orderByCd);
		if (year == null) {
			java.util.Calendar cal = java.util.Calendar.getInstance();
			String tmpYear = String.valueOf(cal.get(Calendar.YEAR));
			int tmpMonth = cal.get(Calendar.MONTH) + 1;
			if (tmpMonth < 10) {
				map.put("startDay", tmpYear + "0" + tmpMonth + "01");
				map.put("endDay", tmpYear + "0" + tmpMonth + "31");
			} else {
				map.put("startDay", tmpYear + "" + tmpMonth + "01");
				map.put("endDay", tmpYear + "" + tmpMonth + "31");
			}
		} else {
			map.put("startDay", year + mon + "01");
			map.put("endDay", year + mon + lsatDay);
		}
		Map<String, Object> resultMap = receiptService.selectF01List(map);
		return resultMap;
	}

	/**
	 * 사용안함
	 * 
	 * @param commandMap
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/F01.do")
	public ModelAndView F01(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		String id = (String) session.getAttribute("id");
		String year = request.getParameter("year");
		String mon = request.getParameter("mon");
		String lsatDay = request.getParameter("lsatDay");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		if (year == null) {
			java.util.Calendar cal = java.util.Calendar.getInstance();
			String tmpYear = String.valueOf(cal.get(Calendar.YEAR));
			int tmpMonth = cal.get(Calendar.MONTH) + 1;
			if (tmpMonth < 10) {
				map.put("startDay", tmpYear + "0" + tmpMonth + "01");
				map.put("endDay", tmpYear + "0" + tmpMonth + "31");
			} else {
				map.put("startDay", tmpYear + "" + tmpMonth + "01");
				map.put("endDay", tmpYear + "" + tmpMonth + "31");
			}
		} else {
			map.put("startDay", year + mon + "01");
			map.put("endDay", year + mon + lsatDay);
		}
		ModelAndView mv = new ModelAndView("/F/F01");
		Map<String, Object> resultMap = receiptService.selectF01List(map);
		if ("".equals(id) || id == null) {
			mv = new ModelAndView("redirect:/receipt/index.do");
		}
		// mv.clear();
		mv.addObject("list", resultMap);
		mv.addObject("eMail", id);
		return mv;
	}

	/**
	 * 영수증 확인 / 리스트 탭
	 * 
	 * @param commandMap
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/appB01MonthList.do")
	@ResponseBody
	public Object appB01MonthList(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		/* String id = (String) session.getAttribute("id"); */
		String id = request.getParameter("id");
		String startDate = (String) commandMap.getMap().get("startDate");
		String endDate = (String) commandMap.getMap().get("endDate");
		String orderByCd = (String) commandMap.getMap().get("orderByCd");
		System.out.println("orderByCd:" + orderByCd);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("orderByCd", orderByCd);
		map.put("eMail", id);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		Map<String, Object> resultMap = receiptService.selectMonthF01List(map);
		resultMap.put("code", "OK");

		return resultMap;
	}

	/**
	 * 사용안함
	 * 
	 * @param commandMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/MonthListF01.do")
	@ResponseBody
	public Object MonthListF01(CommandMap commandMap, HttpSession session) throws Exception {
		String id = (String) session.getAttribute("id");
		String startDate = (String) commandMap.getMap().get("startDate");
		String endDate = (String) commandMap.getMap().get("endDate");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("eMail", id);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		Map<String, Object> resultMap = receiptService.selectMonthF01List(map);
		resultMap.put("code", "OK");

		return resultMap;
	}

	/**
	 * 영수증 확인에서 더보기 버튼 클릭 시 추가 조회
	 * 
	 * @param commandMap
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/appB01MonthListAdd.do")
	@ResponseBody
	public Object appB01MonthListAdd(CommandMap commandMap, HttpSession session, ServletRequest request)
			throws Exception {
		// String id = (String) session.getAttribute("id");
		String id = request.getParameter("id");
		String startDate = (String) commandMap.getMap().get("startDate");
		String endDate = (String) commandMap.getMap().get("endDate");
		String eMail = (String) commandMap.getMap().get("eMail");
		String orderByCd = (String) commandMap.getMap().get("orderByCd");
		String divCd = (String) commandMap.getMap().get("divCd");
		System.out.println("orderByCd:" + orderByCd);
		System.out.println("divCd:" + divCd);
		int addStartCnt = Integer.parseInt((String) commandMap.getMap().get("addStartCnt"));
		int addEndCnt = Integer.parseInt((String) commandMap.getMap().get("addEndCnt"));
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("orderByCd", orderByCd);
		map.put("eMail", eMail);
		map.put("addStartCnt", addStartCnt);
		map.put("addEndCnt", addEndCnt);
		map.put("divCd", divCd);

		Map<String, Object> resultMap = receiptService.selectAddListF01(map);
		resultMap.put("code", "OK");

		return resultMap;
	}

	/**
	 * 사용안함
	 * 
	 * @param commandMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/selectAddListF01.do")
	@ResponseBody
	public Object selectAddListF01(CommandMap commandMap, HttpSession session) throws Exception {
		String id = (String) session.getAttribute("id");
		String startDate = (String) commandMap.getMap().get("startDate");
		String endDate = (String) commandMap.getMap().get("endDate");
		String eMail = (String) commandMap.getMap().get("eMail");
		int addStartCnt = Integer.parseInt((String) commandMap.getMap().get("addStartCnt"));
		int addEndCnt = Integer.parseInt((String) commandMap.getMap().get("addEndCnt"));
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("eMail", eMail);
		map.put("addStartCnt", addStartCnt);
		map.put("addEndCnt", addEndCnt);

		Map<String, Object> resultMap = receiptService.selectAddListF01(map);
		resultMap.put("code", "OK");

		return resultMap;
	}

	/**
	 * 영수증 확인 / 달력 탭
	 * 
	 * @param commandMap
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/appB01MonthCal.do")
	@ResponseBody
	public Object appB01MonthCal(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		// String id = (String) session.getAttribute("id");
		String id = request.getParameter("id");
		String startDate = (String) commandMap.getMap().get("startDate");
		String endDate = (String) commandMap.getMap().get("endDate");
		String eMail = (String) commandMap.getMap().get("eMail");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("eMail", eMail);

		Map<String, Object> resultMap = receiptService.selectDateCntListF01(map);
		resultMap.put("code", "OK");

		return resultMap;
	}

	/**
	 * 사용안함
	 * 
	 * @param commandMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/selectDateCntListF01.do")
	@ResponseBody
	public Object selectDateCntListF01(CommandMap commandMap, HttpSession session) throws Exception {
		String id = (String) session.getAttribute("id");
		String startDate = (String) commandMap.getMap().get("startDate");
		String endDate = (String) commandMap.getMap().get("endDate");
		String eMail = (String) commandMap.getMap().get("eMail");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("eMail", id);

		Map<String, Object> resultMap = receiptService.selectDateCntListF01(map);
		resultMap.put("code", "OK");

		return resultMap;
	}

	/**
	 * 영수증 확인 / 달력 탭에서 날짜 클릭 시 리스트
	 * 
	 * @param commandMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/AppB01MonthCalList.do")
	@ResponseBody
	public Object selectAppB01MonthCal(CommandMap commandMap, HttpSession session) throws Exception {
		String date = (String) commandMap.getMap().get("date");
		String eMail = (String) commandMap.getMap().get("eMail");
		String orderByCd = (String) commandMap.getMap().get("orderByCd");
		System.out.println("orderByCd:" + orderByCd);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("date", date);
		map.put("eMail", eMail);
		map.put("orderByCd", orderByCd);

		Map<String, Object> resultMap = receiptService.selectDailyListF01(map);
		resultMap.put("code", "OK");

		return resultMap;
	}

	/**
	 * 사용안함
	 * 
	 * @param commandMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/selectDailyList.do")
	@ResponseBody
	public Object selectDailyList(CommandMap commandMap, HttpSession session) throws Exception {
		String id = (String) session.getAttribute("id");
		String date = (String) commandMap.getMap().get("date");
		String eMail = (String) commandMap.getMap().get("eMail");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("date", date);
		map.put("eMail", id);

		Map<String, Object> resultMap = receiptService.selectDailyListF01(map);
		resultMap.put("code", "OK");

		return resultMap;
	}

	/**
	 * 메일전송
	 * 
	 * @param commandMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	/*
	 * @RequestMapping(value = "/receipt/sendMail.do") public ModelAndView
	 * E01SendMail(CommandMap commandMap, HttpServletRequest request) throws
	 * Exception { String date = (String)commandMap.get("dealDate");
	 * System.out.println("DATE::::::::::::::"+date);
	 * SendMailTest.sendMail(request, date); ModelAndView mv = new
	 * ModelAndView("/E/E01"); return mv; }
	 */

	/**
	 * 총 사용 내역 메일전송
	 * 
	 * @param commandMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	@RequestMapping(value = "/receipt/monthAllSendMail.do")
	@ResponseBody
	public ModelAndView monthAllSendMail(CommandMap commandMap, HttpServletRequest request) throws Exception {
		JSONObject json = new JSONObject();
		String date = (String) commandMap.get("date");
		date = date.substring(0, 4) + "-" + date.substring(4);
		String CI = (String) request.getParameter("CI");

		System.out.println("DATE::::::::::::::" + request.getParameter("telNo") + date);

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("date", date);
		map.put("telNo", CI);
		String eMailChk = receiptService.eMailChk(CI);

		HashMap<String, Object> resultMap = receiptService.monthAllDate(map);

		System.out.println("■□■□■□■□■□■□■□■□■□result : " + resultMap);

		ArrayList list = (ArrayList) resultMap.get("resultMap");
		System.out.println("■□■□■□■□■□■□■□■□■□list : " + list);
		System.out.println("■□■□■□■□■□■□■□■□■□result size : " + list.size());

		if (list.size() > 0) {
			// SendMailTest.monthAllSendMail(list, date, eMailChk);

			System.out.println("■■■■■■■■■file 접근■■■■■■■■");
			File file = new File("../test.xlsx");

			System.out.println("■■■■■■■■■File pass■■■■■■■■");

			org.apache.poi.xssf.usermodel.XSSFWorkbook wb = new org.apache.poi.xssf.usermodel.XSSFWorkbook();

			System.out.println("■■■■■■■■■Workbook pass ■■■■■■■■");

			org.apache.poi.xssf.usermodel.XSSFSheet sheet = wb.createSheet((String) date);

			System.out.println("■■■■■■■■■XSSFSheet pass ■■■■■■■■");

			org.apache.poi.xssf.usermodel.XSSFRow row = null;
			org.apache.poi.xssf.usermodel.XSSFCell cell = null;

			System.out.println("■■■■■■■■■XSSFRow/XSSFCell pass ■■■■■■■■");

			row = sheet.createRow(0);

			cell = row.createCell(0);
			cell.setCellValue("no");

			cell = row.createCell(1);
			cell.setCellValue("날짜");

			cell = row.createCell(2);
			cell.setCellValue("가맹점 이름");

			cell = row.createCell(3);
			cell.setCellValue("승인/취소");

			cell = row.createCell(4);
			cell.setCellValue("카드정보");

			cell = row.createCell(5);
			cell.setCellValue("누적금액");

			cell = row.createCell(6);
			cell.setCellValue("할부");

			cell = row.createCell(7);
			cell.setCellValue("결제금액");

			for (int i = 1; i <= list.size(); i++) {
				row = sheet.createRow(i);

				Map map2 = (Map) list.get(i - 1);
				System.out.println(i + " ----" + map2);
				cell = row.createCell(0);
				cell.setCellValue(i);

				cell = row.createCell(1);
				String dateTime = ((Date) map2.get("FRT_CREA_DTM")).toString();
				cell.setCellValue(dateTime.substring(0, dateTime.length() - 2));
				cell = row.createCell(2);
				cell.setCellValue((String) map2.get("APP_CO"));
				cell = row.createCell(3);
				cell.setCellValue((String) map2.get("CARD_APP_DIV"));
				cell = row.createCell(4);
				cell.setCellValue((String) map2.get("CARD_APP_CO"));
				cell = row.createCell(5);
				cell.setCellValue((String) map2.get("ACC_SUM_AMMOUNT"));
				cell = row.createCell(6);
				cell.setCellValue((String) map2.get("INST_DIV"));
				cell = row.createCell(7);
				cell.setCellValue((String) map2.get("APP_AMOUNT"));
			}

			System.out.println("■■■■■■■■■XSSFRow/XSSFCell Insert pass ■■■■■■■■");

			FileOutputStream fos = new FileOutputStream(file);

			System.out.println("■■■■■■■■■FileOutputStream pass■■■■■■■■");
			wb.write(fos);
			System.out.println("■■■■■■■■■Write pass ■■■■■■■■");
			fos.close();

			json.put("msg", "이메일 전송 확인");
		} else {
			json.put("msg", "이메일 전송 실패");
		}

		ModelAndView mv = new ModelAndView("/E/E01");
		return mv;
	}

	/**
	 * 사용안함
	 * 
	 * @param commandMap
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/F03.do")
	public ModelAndView moveToF03(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("/F/F03");
		String dailyUseDate = (String) commandMap.getMap().get("dailyUseDate");
		String dailyPayDiv = (String) commandMap.getMap().get("dailyPayDiv");
		String dailyEtpNm = (String) commandMap.getMap().get("dailyEtpNm");
		String dailyPayAm = (String) commandMap.getMap().get("dailyPayAm");
		String dailyEmail = (String) commandMap.getMap().get("dailyEmail");
		String dailyUseDateOrg = (String) commandMap.getMap().get("dailyUseDateOrg");
		String dailyPayGoods = (String) commandMap.getMap().get("dailyPayGoods");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("dailyUseDate", dailyUseDate);
		map.put("dailyPayDiv", dailyPayDiv);
		map.put("dailyEtpNm", dailyEtpNm);
		map.put("dailyPayAm", dailyPayAm);
		map.put("dailyEmail", dailyEmail);
		map.put("dailyUseDateOrg", dailyUseDateOrg);
		map.put("dailyPayGoods", dailyPayGoods);
		mv.addObject("data", map);
		return mv;
	}

	/**
	 * 사용안함
	 * 
	 * @param commandMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/goEventPage.do")
	public ModelAndView goEventPage(CommandMap commandMap, HttpSession session) throws Exception {
		String id = (String) session.getAttribute("id");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		ModelAndView mv = new ModelAndView("/board/event/event");
		if ("".equals(id) || id == null) {
			mv = new ModelAndView("redirect:/receipt/index.do");
		}

		return mv;
	}

	/**
	 * 공지사항
	 * 
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/appD01.do")
	@ResponseBody
	public Object appD01(CommandMap commandMap) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startNo", 0);
		map.put("endNo", 10);

		Map<String, Object> resultMap = receiptService.selectNoticeMove(map);
		return resultMap;
	}

	/**
	 * 이벤트 배너
	 * 
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/appE01.do")
	@ResponseBody
	public Object appE01(CommandMap commandMap) throws Exception {
		Map<String, Object> resultMap = receiptService.selectAppE01();
		return resultMap;
	}

	/**
	 * 공지사항 클릭 시 조회수 증가
	 * 
	 * @param commandMap
	 */
	@RequestMapping(value = "/receipt/appD01AddCnt.do")
	@ResponseBody
	public void appD01AddCnt(CommandMap commandMap) {
		receiptService.updateAppD01AddCnt(commandMap.getMap());
	}

	/**
	 * 사용안함
	 * 
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/notice.do")
	public ModelAndView notice(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/board/notice/notice");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startNo", 0);
		map.put("endNo", 10);

		Map<String, Object> resultMap = receiptService.selectNoticeMove(map);
		mv.addObject("list", resultMap);
		return mv;
	}

	/**
	 * 공지사항 다음버튼 클릭
	 * 
	 * @param commandMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/appD01Move.do")
	@ResponseBody
	public Object appD01Move(CommandMap commandMap, HttpSession session) throws Exception {
		int startNo = Integer.parseInt((String) commandMap.getMap().get("startNo"));
		int endNo = Integer.parseInt((String) commandMap.getMap().get("endNo"));
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startNo", startNo);
		map.put("endNo", endNo);

		Map<String, Object> resultMap = receiptService.selectNoticeMove(map);
		resultMap.put("code", "OK");

		return resultMap;
	}

	/**
	 * 사용안함
	 * 
	 * @param commandMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/noticeMove.do")
	@ResponseBody
	public Object noticeMove(CommandMap commandMap, HttpSession session) throws Exception {
		int startNo = Integer.parseInt((String) commandMap.getMap().get("startNo"));
		int endNo = Integer.parseInt((String) commandMap.getMap().get("endNo"));
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startNo", startNo);
		map.put("endNo", endNo);

		Map<String, Object> resultMap = receiptService.selectNoticeMove(map);
		resultMap.put("code", "OK");

		return resultMap;
	}

	/**
	 * 영수증 확인 / 분류탭
	 * 
	 * @param commandMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/appB01MonthDivList.do")
	@ResponseBody
	public Object appB01MonthDivList(CommandMap commandMap, HttpSession session) throws Exception {
		int startDate = Integer.parseInt((String) commandMap.getMap().get("startDate"));
		int endDate = Integer.parseInt((String) commandMap.getMap().get("endDate"));
		String eMail = (String) commandMap.getMap().get("eMail");
		String divCd = (String) commandMap.getMap().get("divCd");
		String orderByCd = (String) commandMap.getMap().get("orderByCd");
		System.out.println("orderByCd:" + orderByCd);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("eMail", eMail);
		map.put("divCd", divCd);
		map.put("orderByCd", orderByCd);
		Map<String, Object> resultMap = receiptService.selectDivSearch(map);
		resultMap.put("code", "OK");

		return resultMap;
	}

	/**
	 * 사용안함
	 * 
	 * @param commandMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/divSearch.do")
	@ResponseBody
	public Object divSearch(CommandMap commandMap, HttpSession session) throws Exception {
		int startDate = Integer.parseInt((String) commandMap.getMap().get("startDate"));
		int endDate = Integer.parseInt((String) commandMap.getMap().get("endDate"));
		String eMail = (String) commandMap.getMap().get("eMail");
		String divCd = (String) commandMap.getMap().get("divCd");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("eMail", eMail);
		map.put("divCd", divCd);
		Map<String, Object> resultMap = receiptService.selectDivSearch(map);
		resultMap.put("code", "OK");

		return resultMap;
	}

	/*
	 * ADMIN
	 */

	/**
	 * 공지사항 게시판리스트
	 * 
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/Admin/boardMng.do")
	public ModelAndView boardMng(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/board/boardMng");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startNo", 0);
		map.put("endNo", 10);
		Map<String, Object> resultMap = receiptService.selectBoardMng(map);
		mv.addObject("list", resultMap);
		return mv;
	}

	/**
	 * 공지사항 게시판 작성
	 * 
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/Admin/boardMngWrite.do")
	public ModelAndView boardMngWrite(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/board/boardMngWrite");
		if (!"".equals(commandMap.getMap())) {
			Map<String, Object> resultMap = receiptService.selectBoardWriteDetail(commandMap.getMap());
			mv.addObject("list", resultMap);
		}
		return mv;
	}

	/**
	 * 공지사항 게시판 글 삭제
	 * 
	 * @param commandMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/Admin/deleteAdminBoard.do")
	@ResponseBody
	public ModelAndView deleteAdminBoard(CommandMap commandMap, HttpSession session) throws Exception {
		receiptService.deleteBoard(commandMap.getMap());
		ModelAndView mv = new ModelAndView("redirect:/receipt/Admin/boardMng.do");
		return mv;
	}

	/**
	 * 공지사항 게시판 삭제
	 * 
	 * @param commandMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/Admin/updateAdminBoard.do")
	@ResponseBody
	public ModelAndView updateAdminBoard(CommandMap commandMap, HttpServletRequest request) throws Exception {
		receiptService.updateAdminBoard(commandMap.getMap());
		ModelAndView mv = new ModelAndView("redirect:/receipt/Admin/boardMng.do");
		return mv;
	}

	/**
	 * 에디터 파일 업로드
	 * 
	 * @param request
	 * @param response
	 * @param editor
	 * @return
	 */
	@RequestMapping("/receipt/Admin/file_uploader.do")
	public String file_uploader(HttpServletRequest request, HttpServletResponse response, Editor editor) {
		String return1 = request.getParameter("callback");
		String return2 = "?callback_func=" + request.getParameter("callback_func");
		String return3 = "";
		String name = "";
		try {
			if (editor.getFiledata() != null && editor.getFiledata().getOriginalFilename() != null
					&& !editor.getFiledata().getOriginalFilename().equals("")) {
				// 기존 상단 코드를 막고 하단코드를 이용
				name = editor.getFiledata().getOriginalFilename()
						.substring(editor.getFiledata().getOriginalFilename().lastIndexOf(File.separator) + 1);
				String filename_ext = name.substring(name.lastIndexOf(".") + 1);
				filename_ext = filename_ext.toLowerCase();
				String[] allow_file = { "jpg", "png", "bmp", "gif" };
				int cnt = 0;
				for (int i = 0; i < allow_file.length; i++) {
					if (filename_ext.equals(allow_file[i])) {
						cnt++;
					}
				}
				if (cnt == 0) {
					return3 = "&errstr=" + name;
				} else {
					// 파일 기본경로
					String dftFilePath = request.getSession().getServletContext().getRealPath("/");
					// 파일 기본경로 _ 상세경로
					String filePath = dftFilePath + "resources" + File.separator + "editor" + File.separator + "upload"
							+ File.separator;
					File file = new File(filePath);
					if (!file.exists()) {
						file.mkdirs();
					}
					String realFileNm = "";
					SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
					String today = formatter.format(new java.util.Date());
					realFileNm = today + UUID.randomUUID().toString() + name.substring(name.lastIndexOf("."));
					String rlFileNm = filePath + realFileNm;
					///////////////// 서버에 파일쓰기 /////////////////
					editor.getFiledata().transferTo(new File(rlFileNm));
					///////////////// 서버에 파일쓰기 /////////////////
					return3 += "&bNewLine=true";
					return3 += "&sFileName=" + name;
					return3 += "&sFileURL=/resources/editor/upload/" + realFileNm;
				}
			} else {
				return3 += "&errstr=error";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:" + return1 + return2 + return3;
	}

	/**
	 * 에디터 파일업로드 html5
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/receipt/Admin/file_uploader_html5.do")
	public void file_uploader_html5(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 파일정보
			String sFileInfo = "";
			// 파일명을 받는다 - 일반 원본파일명
			String filename = request.getHeader("file-name");
			// 파일 확장자
			String filename_ext = filename.substring(filename.lastIndexOf(".") + 1);
			// 확장자를소문자로 변경
			filename_ext = filename_ext.toLowerCase();

			// 이미지 검증 배열변수
			String[] allow_file = { "jpg", "png", "bmp", "gif" };

			// 돌리면서 확장자가 이미지인지
			int cnt = 0;
			for (int i = 0; i < allow_file.length; i++) {
				if (filename_ext.equals(allow_file[i])) {
					cnt++;
				}
			}

			// 이미지가 아님
			if (cnt == 0) {
				PrintWriter print = response.getWriter();
				print.print("NOTALLOW_" + filename);
				print.flush();
				print.close();
			} else {
				// 이미지이므로 신규 파일로 디렉토리 설정 및 업로드
				// 파일 기본경로
				String dftFilePath = request.getSession().getServletContext().getRealPath("/");
				// String dftFilePath =
				// request.getServletContext().getRealPath("/");
				// 파일 기본경로 _ 상세경로
				String filePath = dftFilePath + "resources" + File.separator + "editor" + File.separator + "multiupload"
						+ File.separator;
				// String filePath = "resources" + File.separator + "editor" +
				// File.separator +"multiupload" + File.separator;
				File file = new File(filePath);
				if (!file.exists()) {
					file.mkdirs();
				}

				String realFileNm = "";
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
				String today = formatter.format(new java.util.Date());
				realFileNm = today + UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
				String rlFileNm = filePath + realFileNm;
				///////////////// 서버에 파일쓰기 /////////////////
				InputStream is = request.getInputStream();
				OutputStream os = new FileOutputStream(rlFileNm);
				int numRead;
				byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
				while ((numRead = is.read(b, 0, b.length)) != -1) {
					os.write(b, 0, numRead);
				}
				if (is != null) {
					is.close();
				}

				os.flush();
				os.close();
				///////////////// 서버에 파일쓰기 /////////////////

				// 정보 출력
				sFileInfo += "&bNewLine=true";
				// img 태그의 title 속성을 원본파일명으로 적용시켜주기 위함
				sFileInfo += "&sFileName=" + filename;
				;
				// sFileInfo +=
				// "&sFileURL="+"/resources/editor/multiupload/"+realFileNm;
				sFileInfo += "&sFileURL=" + "/receipt/resources/editor/multiupload/" + realFileNm;
				PrintWriter print = response.getWriter();
				print.print(sFileInfo);
				print.flush();
				print.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 가계부 챠트 그리기 샘플
	 * 
	 * @param commandMap
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/appChartTest.do")
	@ResponseBody
	public Object appChartTest(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		String id = request.getParameter("id");
		String year = request.getParameter("year");
		String mon = request.getParameter("mon");
		String lsatDay = request.getParameter("lsatDay");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("startDate", "20160101");
		map.put("endDate", "20160131");
		Map<String, Object> resultMap = receiptService.selectAppChart(map);

		JSONParser parser = new JSONParser();

		ObjectMapper om = new ObjectMapper();

		// Map or List Object 를 JSON 문자열로 변환
		String jsonStr = om.writeValueAsString(resultMap);
		// Object obj = parser.parse(new
		// FileReader("C:\\testchart\\test2.json"));
		Object obj = parser.parse(jsonStr);

		JSONObject jsonObject = (JSONObject) obj;

		String to = (String) jsonObject.get("jsonObject");
		String jsonStr2 = "";
		jsonStr2 = jsonStr.replaceAll(jsonStr, "{");
		jsonStr2 = jsonStr.replaceAll(jsonStr, "}");

		// String resultTest = (String) resultMap.get("TOTAL_AM");
		GoogleChartDTO go = new GoogleChartDTO();
		go.addColumn("month", "string");
		go.addColumn("A product", "number");
		go.addColumn("B product", "number");
		go.createRows(7);

		go.addCell(0, "", "호프");
		go.addCell(0, 105000);
		go.addCell(0, 400);
		go.addCell(1, "", "치킨");
		go.addCell(1, 145000);
		go.addCell(1, 200);
		go.addCell(2, "", "커피");
		go.addCell(2, 105000);
		go.addCell(2, 665);
		go.addCell(3, "", "약국");
		go.addCell(3, 35000);
		go.addCell(3, 800);
		go.addCell(4, "", "게임");
		go.addCell(4, 0);
		go.addCell(4, 150);
		go.addCell(5, "", "병원");
		go.addCell(5, 0);
		go.addCell(5, 150);
		go.addCell(6, "", "영화");
		go.addCell(6, 0);
		go.addCell(6, 150);

		Gson gson = new Gson();
		String json = gson.toJson(go.getResult());

		return json;
	}

	/**
	 * 가계부 챠트 그리기
	 * 
	 * @param commandMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/appChartTest2.do")
	@ResponseBody
	public Object appChartTest2(CommandMap commandMap, ServletRequest request) throws Exception {
		String id = request.getParameter("id");
		String year = request.getParameter("year");
		String mon = request.getParameter("mon");
		String lsatDay = request.getParameter("lsatDay");
		// String id = (String) session.getAttribute("id");

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("id", id);
		map.put("startDate", "20160101");
		map.put("endDate", "20161231");
		Map<String, Object> resultMap = receiptService.appChartTest2(map);

		GoogleChartDTO go = new GoogleChartDTO();
		go.addColumn("month", "string");
		go.addColumn("A product", "string");
		go.addColumn("B product", "number");

		String test = (String) resultMap.get("TOTAL_AM");
		String test1 = (String) resultMap.get("TOTAL_AM1");
		String test2 = (String) resultMap.get("TOTAL_AM2");
		String test3 = (String) resultMap.get("TOTAL_AM3");
		String test4 = (String) resultMap.get("TOTAL_AM4");
		String test5 = (String) resultMap.get("TOTAL_AM5");
		String test6 = (String) resultMap.get("TOTAL_AM6");

		if (test != null) {
			test = test.replaceAll(",", "");
		} else {
			test = "0";
		}
		if (test1 != null) {
			test1 = test1.replaceAll(",", "");
		} else {
			test1 = "0";
		}
		if (test2 != null) {
			test2 = test2.replaceAll(",", "");
		} else {
			test2 = "0";
		}
		if (test3 != null) {
			test3 = test3.replaceAll(",", "");
		} else {
			test3 = "0";
		}
		if (test4 != null) {
			test4 = test4.replaceAll(",", "");
		} else {
			test4 = "0";
		}
		if (test5 != null) {
			test5 = test5.replaceAll(",", "");
		} else {
			test5 = "0";
		}
		if (test6 != null) {
			test6 = test6.replaceAll(",", "");
		} else {
			test6 = "0";
		}

		int testi = Integer.parseInt(test);
		int testi1 = Integer.parseInt(test1);
		int testi2 = Integer.parseInt(test2);
		int testi3 = Integer.parseInt(test3);
		int testi4 = Integer.parseInt(test4);
		int testi5 = Integer.parseInt(test5);
		int testi6 = Integer.parseInt(test6);

		go.createRows(7);
		go.addCell(0, "", "호프");
		go.addCell(0, testi);
		go.addCell(0, 400);
		go.addCell(1, "", "치킨");
		go.addCell(1, testi1);
		go.addCell(1, 200);
		go.addCell(2, "", "커피");
		go.addCell(2, testi2);
		go.addCell(2, 665);
		go.addCell(3, "", "약국");
		go.addCell(3, testi3);
		go.addCell(3, 800);
		go.addCell(4, "", "게임");
		go.addCell(4, testi4);
		go.addCell(4, 150);
		go.addCell(5, "", "병원");
		go.addCell(5, testi5);
		go.addCell(5, 150);
		go.addCell(6, "", "영화");
		go.addCell(6, testi6);
		go.addCell(6, 150);

		Gson gson = new Gson();
		String json = gson.toJson(go.getResult());
		return json;

	}

	/*
	 * 전송받은 전자영수증 데이터 저장
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/receipt/insertReceiptData.do")
	@ResponseBody
	// public ModelAndView insertReceiptData(CommandMap commandMap,
	// HttpServletRequest request) throws Exception{
	public JSONObject insertReceiptData(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String telNo = "";
		Var var = null;
		String uplusUserKey = "";
		String str;
		String jsonResData = "";
		StringBuffer paramData = new StringBuffer();
		String resStrEnc = "";
		System.out.println("@@paramDataparamData@@:" + paramData);
		BufferedReader br = null;
		System.out.println("TEST");
		try {
			// br = new BufferedReader(new
			// InputStreamReader(request.getInputStream(), "EUC-KR"));
			br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));

			System.out.println("@@br@@:");

			// paramData = convertStringType(request, paramData);

			while ((str = br.readLine()) != null) {
				paramData.append(str);
				// System.out.println("@@str@@:"+str);

			}

			System.out.println("@@paramData22@@:" + paramData);

			String test = paramData.toString();
			System.out.println("*********************************test :: " + test);

			// 성공시
			jsonResData = "{";
			jsonResData += "    \"result\":\"PI000\",";
			jsonResData += "    \"message\":\"전송성공\"";
			jsonResData += "}";
			System.out.println("jsonResData:" + jsonResData);
		} catch (IOException e) {
			// 실패시
			jsonResData = "{";
			jsonResData += "    \"result\":\"PI101\",";
			jsonResData += "    \"message\":\"수신된 데이터 빈값 입니다.\"";
			jsonResData += "}";
			e.printStackTrace();
			throw e;
		}
		try {

			var = JsonParser.object(new CharTokenizer(paramData.toString()));

			HashMap<String, Object> detailMap = new HashMap<String, Object>();
			// System.out.println("@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#");
			// System.out.println("@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#"+var.find("salesInfo.salesType").toString());
			/**
			 * 회원이메일을 가져오기 위한 로직 추가
			 */
			log.debug("cancle resultMap :: " + var.toString());

			Map<String, Object> resultMap = new HashMap<String, Object>();
			HashMap<String, Object> cancleMap = new HashMap<String, Object>();

			if (var.find("salesInfo.salesType").toString().equals("RCP02")) {

				log.debug(var);
				cancleMap.put("originRecNo", var.find("salesInfo.originRecNo").toString());
				cancleMap.put("shopBizNo", var.find("shopInfo.bizNo").toString());

				resultMap = receiptService.cancleReceipt(cancleMap);

				telNo = (String) resultMap.get("USER_KEY");

			} else {
				telNo = var.find("userKey").toString();
			}

			Map<String, Object> telMap = new HashMap<>();
			telMap.put("telNo", telNo);
			String CI = (String) receiptService.getCi(telMap);
			if (var.find("salesInfo.salesType").toString().equals("RCP02")) {
				log.debug("====================================================");
				log.debug("■■■■■■■■■■■■■■■■■RCP02 전자영수증 취소건■■■■■■■■■■■■■■■■■■");

				String eMailChk = receiptService.eMailChk(telNo);

				// cancleMap.put("businessNumber",
				// var.find("shopInfo.bizNo").toString());

				telNo = (String) resultMap.get("USER_KEY");
				uplusUserKey = (String) resultMap.get("UPLUS_USER_KEY");

				resultMap.put("CI", CI);
				resultMap.put("userKey", telNo);
				resultMap.put("salesType", "RCP02");
				resultMap.put("uplusUserKey", uplusUserKey);
				resultMap.put("eMail", eMailChk);
				resultMap.put("memo", resultMap.get("ETC_MEMO"));
				resultMap.put("event", resultMap.get("ETC_EVENT"));
				resultMap.put("name", resultMap.get("SHOP_NAME"));
				resultMap.put("businessNumber", resultMap.get("SHOP_BIZNO"));
				resultMap.put("address", resultMap.get("SHOP_ADDR"));
				resultMap.put("ceo", resultMap.get("SHOP_CEO"));
				resultMap.put("phone", resultMap.get("SHOP_TEL_NUM"));
				resultMap.put("cashier", resultMap.get("SHOP_CASHIER"));
				resultMap.put("salesBarCode", var.find("salesInfo.salesBarCode").toString() + "P02");
				resultMap.put("salesDate", resultMap.get("SALES_DATE"));
				resultMap.put("printDate", resultMap.get("SALES_PRINT_DATE"));
				resultMap.put("sumDfAmt", resultMap.get("SALES_SUM_DF_AMT"));
				resultMap.put("sumFpAmt", resultMap.get("SALES_SUM_FP_AMT"));
				resultMap.put("sumTaxAmt", resultMap.get("SALES_SUM_TAX_AMT"));
				resultMap.put("sumSlAmt", resultMap.get("SALES_SUM_ALL_AMT"));
				resultMap.put("sumOpAmt", resultMap.get("SALES_SUM_OP_AMT"));
				resultMap.put("chgAmt", resultMap.get("SALES_CHG_AMT"));
				resultMap.put("paidAmt", resultMap.get("SALES_PAID_AMT"));
				resultMap.put("rePrint", resultMap.get("SALES_RE_PRINT"));
				resultMap.put("detilCnt", resultMap.get("SALES_DT_CNT"));
				resultMap.put("cashAmt", resultMap.get("CASH_AMT"));
				resultMap.put("cashType", resultMap.get("CASH_TYPE"));
				resultMap.put("cashNo", resultMap.get("CASH_NO"));
				resultMap.put("cashAppNo", resultMap.get("CASH_APP_N0"));
				resultMap.put("cashDate", resultMap.get("CASH_DATE"));
				resultMap.put("cardAmt", resultMap.get("CARD_AMT"));
				resultMap.put("cardInstallment", resultMap.get("CARD_INSTALLMENT"));
				resultMap.put("cardAppNo", resultMap.get("CARD_APP_NO"));
				resultMap.put("cardDate", resultMap.get("CARD_DATE"));
				resultMap.put("cardICom", resultMap.get("CARD_ICOM"));
				resultMap.put("cardPCom", resultMap.get("CARD_PCOM"));
				resultMap.put("cardNo", resultMap.get("CARD_NO"));
				resultMap.put("pointCard", resultMap.get("POINT_CARD"));
				resultMap.put("pointAmt", resultMap.get("POINT_AMT"));
				resultMap.put("getPoint", resultMap.get("GET_POINT"));
				resultMap.put("customerCode", resultMap.get("CUSTOMER_CODE"));
				resultMap.put("customerPoint", resultMap.get("CUSTOMER_POINT"));
				resultMap.put("recNO", var.find("salesInfo.recNO").toString());
				resultMap.put("originRecNo", var.find("salesInfo.originRecNo").toString());
				resultMap.put("originSalesDate", var.find("salesInfo.originSalesDate").toString());
				try {
					receiptService.insertReceiptData((HashMap<String, Object>) resultMap);
				} catch (Exception e) {
					jsonResData = "{";
					jsonResData += "    \"result\":\"PI603\",";
					jsonResData += "    \"message\":\"정상결제된 바코드번호가 존재하지 않습니다.결제 승인 되지 않은 영수증입니다.\"";
					jsonResData += "}";
				}

				detailMap = (HashMap<String, Object>) receiptService.getDetailReceipt(cancleMap);
				List detailList = (List) detailMap.get("resultMap");

				for (int i = 0; i < detailList.size(); i++) {
					HashMap<String, Object> temp = (HashMap<String, Object>) detailList.get(i);

					temp.put("CI", CI);
					temp.put("uplusUserKey", uplusUserKey);
					temp.put("shopBizNo", temp.get("SHOP_BIZNO"));
					temp.put("salesBarCode", temp.get("SALES_BARCODE") + "P02");
					temp.put("userKey", telNo);
					temp.put("salesType", "RCP02");
					temp.put("seqNo", temp.get("SALES_SEQ_NO"));
					temp.put("pName", temp.get("SALES_PNAME"));
					temp.put("pPrice", temp.get("SALES_PPRICE"));
					temp.put("oPrice", temp.get("SALES_OPRICE"));
					temp.put("qty", temp.get("SALES_QTY"));
					temp.put("dfAmt", temp.get("SALES_DF_AMT"));
					temp.put("fpAmt", temp.get("SALES_FP_AMT"));
					temp.put("taxAmt", temp.get("SALES_TAX_AMT"));
					temp.put("slAmt", temp.get("SALES_SL_AMT"));
					temp.put("opAmt", temp.get("SALES_OP_AMT"));

					receiptService.insertReceiptDeatailData(temp);
				}

				log.debug("취소 TelNo get ::: " + telNo);
			} else {
				log.debug("====================================================");
				log.debug("■■■■■■■■■■■■■■■■■RCP01 전자영수증 승인건■■■■■■■■■■■■■■■■■■");

				uplusUserKey = receiptService.uPlusChk(telNo);

				if (!(telNo == "" || telNo == null)) {

					/*
					 * String eMailChk = receiptService.eMailChk(telNo);
					 * 
					 * log.debug(eMailChk); log.debug("eMailChk:" + eMailChk +
					 * "END");
					 */
					if ("".equals(CI) || CI == null) {
						jsonResData = "{";
						jsonResData += "    \"result\":\"PI602\",";
						jsonResData += "    \"message\":\"고객식별번호값(userKey value)과 매칭되는 사용자를 찾지 못했습니다.\"";
						jsonResData += "}";

						log.debug("@@@@@@@@@@@@11test11@@@@@@@@@@@@@@@@@@@");
						log.debug("@@@@@@@@@@@@11test11@@@@@@@@@@@@@@@@@@@");
					} else {
						String getpushKey = receiptService.pushChk(CI);
						Sender sender = new Sender("AIzaSyDraPQzpXKPRvc_mWGzkppQbrYDRYu0UjM"); // 서버
																								// API
																								// Key
																								// 입력
						String regId = getpushKey;
						String sendTlt = "더리얼 마케팅";
						String sendMsg = "새로운 전자영수증메시지가 수신되었습니다.";
						Message message = new Message.Builder().addData("title", sendTlt).addData("message", sendMsg)
								.delayWhileIdle(false).build();

						List<String> list = new ArrayList<String>();
						list.add(regId);
						MulticastResult multiResult;
						try {
							multiResult = sender.send(message, list, 5);
							if (multiResult != null) {
								List<Result> resultList = multiResult.getResults();
								for (Result result : resultList) {
									System.out.println(result.getMessageId());
								}
							}
						} catch (IOException e) {
							e.printStackTrace();
							e.getMessage();
						}
					}

					log.debug("@@@@@@@@@@@@2222tes222222t@@@@@@@@@@@@@@@@@@@");
					log.debug("@@@@@@@@@@@@2222test2222@@@@@@@@@@@@@@@@@@@");

					HashMap<String, Object> map = new HashMap<String, Object>();

					String userKey = var.find("userKey").toString();
					if (userKey.length() == 10) {
						userKey = userKey.substring(0, 3) + "0" + userKey.substring(3);
					}
					map.put("userKey", userKey);
					/*
					 * if(var.find("userKey").toString().equals("") ||
					 * var.find("userKey").toString() == null){
					 * map.put("userKey",""); }else{ map.put("userKey",
					 * var.find("userKey").toString());
					 * 
					 * }
					 */

					map.put("uplusUserKey", uplusUserKey);
					map.put("CI", CI);
					map.put("salesType", var.find("salesInfo.salesType").toString());
					map.put("memo", var.find("etcInfo.memo").toString());
					map.put("event", var.find("etcInfo.event").toString());
					map.put("name", var.find("shopInfo.name").toString());
					map.put("businessNumber", var.find("shopInfo.bizNo").toString());
					map.put("address", var.find("shopInfo.address").toString());
					map.put("ceo", var.find("shopInfo.ceo").toString());
					map.put("phone", var.find("shopInfo.phone").toString());
					map.put("cashier", var.find("shopInfo.cashier").toString());
					map.put("salesBarCode", var.find("salesInfo.salesBarCode").toString());
					map.put("salesDate", var.find("salesInfo.salesDate").toString());
					map.put("printDate", var.find("salesInfo.printDate").toString());
					map.put("sumDfAmt", var.find("salesInfo.sumDfAmt").toString());
					map.put("sumFpAmt", var.find("salesInfo.sumFpAmt").toString());
					map.put("sumTaxAmt", var.find("salesInfo.sumTaxAmt").toString());
					map.put("sumSlAmt", var.find("salesInfo.sumSlAmt").toString());
					map.put("sumOpAmt", var.find("salesInfo.sumOpAmt").toString());
					map.put("chgAmt", var.find("salesInfo.chgAmt").toString());
					map.put("paidAmt", var.find("salesInfo.paidAmt").toString());
					map.put("rePrint", var.find("salesInfo.rePrint").toString());
					map.put("detilCnt", var.find("salesInfo.dtCnt").toString());

					/*
					 * log.debug(
					 * "========================redNO 테스트 ================ ");
					 * log.debug( var.get("salesInfo.recNO").);
					 * if(var.find("salesInfo.recNO") == null){ log.debug(
					 * "if 접근 %%"); map.put("originSalesDate","");
					 * map.put("recNO", ""); map.put("originRecNo", ""); }else{
					 * log.debug("else 접근 %%"); map.put("originSalesDate",
					 * var.find("salesInfo.originSalesDate").toString());
					 * map.put("recNO", var.find("salesInfo.recNO").toString());
					 * map.put("originRecNo",
					 * var.find("salesInfo.originRecNo").toString()); }
					 * 
					 * if (var.find("cardInfo").size() > 0 &&
					 * var.find("cashInfo").size() > 0) { map.put("cardAmt",
					 * var.find("cardInfo.cardAmt").toString());
					 * map.put("cardInstallment",
					 * var.find("cardInfo.cardInstallment").toString());
					 * map.put("cardAppNo",
					 * var.find("cardInfo.cardAppNo").toString());
					 * map.put("cardDate",
					 * var.find("cardInfo.cardDate").toString());
					 * map.put("cardICom",
					 * var.find("cardInfo.cardICom").toString());
					 * map.put("cardPCom",
					 * var.find("cardInfo.cardPCom").toString());
					 * if(var.find("cardInfo.cardICom").toString().contains(
					 * "L포인트")){
					 * if(var.find("cardInfo.cardICom").toString().contains("사용"
					 * )){ map.put("pointCard", "L.POINT 사용"); }else{
					 * map.put("pointCard", "L.POINT 적립"); } } map.put("cardNo",
					 * var.find("cardInfo.cardNo").toString());
					 * 
					 * map.put("cashAmt",
					 * var.find("cashInfo.cashAmt").toString());
					 * map.put("cashType",
					 * var.find("cashInfo.cashType").toString());
					 * map.put("cashNo",
					 * var.find("cashInfo.cashNo").toString());
					 * map.put("cashAppNo",
					 * var.find("cashInfo.cashAppNo").toString());
					 * map.put("cashDate",
					 * var.find("cashInfo.cashDate").toString()); }
					 */
					map.put("cashAmt", "");
					map.put("cashType", "");
					map.put("cashNo", "");
					map.put("cashAppNo", "");
					map.put("cashDate", "");

					map.put("cardAmt", "");
					map.put("cardInstallment", "");
					map.put("cardAppNo", "");
					map.put("cardDate", "");
					map.put("cardICom", "");
					map.put("cardPCom", "");
					map.put("cardNo", "");
					map.put("cardCompound", "");
					map.put("pointIcom", "");
					map.put("pointCardNo", "");
					map.put("pointType", "");
					map.put("pointAmt", "");
					map.put("getPoint", "");
					map.put("customerCode", "");

					map.put("pointAmt", var.find("salesInfo.pointamt").toString());
					map.put("getPoint", var.find("customerInfo.getPoint").toString());
					map.put("customerCode", var.find("customerInfo.customerCode").toString());
					map.put("customerPoint", var.find("customerInfo.customerPoint").toString());

					if (var.find("cashInfo").size() > 0) {
						map.put("cashAmt", var.find("cashInfo.cashAmt").toString());
						map.put("cashType", var.find("cashInfo.cashType").toString());
						map.put("cashNo", var.find("cashInfo.cashNo").toString());
						map.put("cashAppNo", var.find("cashInfo.cashAppNo").toString());
						map.put("cashDate", var.find("cashInfo.cashDate").toString());
					}

					if (var.find("pointInfo").size() > 0) {
						for (int i = 0; i < var.find("pointInfo").size(); i++) {
							map.put("pointIcom", var.find("pointInfo[" + i + "].pointIcom").toString());
							map.put("pointCardNo", var.find("pointInfo[" + i + "].pointCardNo").toString());
							map.put("pointType", var.find("pointInfo[" + i + "].pointType").toString());
							map.put("pointAmt", var.find("pointInfo[" + i + "].pointAmt").toString());
							map.put("getPoint", var.find("pointInfo[" + i + "].getPoint").toString());
							map.put("customerCode", var.find("pointInfo[" + i + "].customerCode").toString());
						}
					}

					if (var.find("cardInfo").size() > 0) {
						for (int i = 0; i < var.find("cardInfo").size(); i++) {
							if (i == 0) {
								map.put("cardAmt", var.find("cardInfo[" + i + "].cardAmt").toString());
								map.put("cardInstallment", var.find("cardInfo[" + i + "].cardInstallment").toString());
								map.put("cardAppNo", var.find("cardInfo[" + i + "].cardAppNo").toString());
								map.put("cardDate", var.find("cardInfo[" + i + "].cardDate").toString());
								map.put("cardICom", var.find("cardInfo[" + i + "].cardPCom").toString());
								map.put("cardPCom", var.find("cardInfo[" + i + "].cardPCom").toString());
								map.put("cardNo", var.find("cardInfo[" + i + "].cardNo").toString());
								if (var.find("cardInfo").size() > 1) {
									map.put("cardCompound", "Y");
								} else {
									map.put("cardCompound", "N");
								}
							} else {
								map = new HashMap<>();
								map.put("userKey", "");
								map.put("salesBarCode", var.find("salesInfo.salesBarCode").toString());
								map.put("businessNumber", var.find("shopInfo.bizNo").toString());
								map.put("cardAmt", var.find("cardInfo[" + i + "].cardAmt").toString());
								map.put("cardInstallment", var.find("cardInfo[" + i + "].cardInstallment").toString());
								map.put("cardAppNo", var.find("cardInfo[" + i + "].cardAppNo").toString());
								map.put("cardDate", var.find("cardInfo[" + i + "].cardDate").toString());
								map.put("cardICom", var.find("cardInfo[" + i + "].cardPCom").toString());
								map.put("cardPCom", var.find("cardInfo[" + i + "].cardPCom").toString());
								map.put("cardNo", var.find("cardInfo[" + i + "].cardNo").toString());
								map.put("cardCompound", var.find("salesInfo.salesBarCode").toString());
							}
							System.out.println("cardInfo Use@@@" + map);
							receiptService.insertReceiptData(map);
						}

					} else {
						System.out.println("cardInfo None@@@" + map);
						receiptService.insertReceiptData(map);
					}

					for (int i = 0; i < var.find("salesList").size(); i++) {
						detailMap.put("uplusUserKey", uplusUserKey);
						detailMap.put("shopBizNo", var.find("shopInfo.bizNo").toString());
						detailMap.put("salesBarCode", var.find("salesInfo.salesBarCode").toString());
						detailMap.put("userKey", userKey);
						detailMap.put("salesType", var.find("salesInfo.salesType").toString());
						detailMap.put("seqNo", var.find("salesList[" + i + "].seqNo").toString());
						detailMap.put("pName", var.find("salesList[" + i + "].pName").toString());
						// detailMap.put("pPrice",
						// var.find("salesList["+i+"].pPrice").toString());
						detailMap.put("pPrice", var.find("salesList[" + i + "].oPrice").toString());
						log.debug("pPrice:" + var.find("salesList[" + i + "].oPrice").toString());
						detailMap.put("oPrice", var.find("salesList[" + i + "].oPrice").toString());
						detailMap.put("qty", var.find("salesList[" + i + "].qty").toString());
						detailMap.put("dfAmt", var.find("salesList[" + i + "].dfAmt").toString());
						detailMap.put("fpAmt", var.find("salesList[" + i + "].fpAmt").toString());
						detailMap.put("taxAmt", var.find("salesList[" + i + "].taxAmt").toString());
						detailMap.put("slAmt", var.find("salesList[" + i + "].slAmt").toString());
						detailMap.put("opAmt", var.find("salesList[" + i + "].opAmt").toString());
						receiptService.insertReceiptDeatailData(detailMap);
					}

					// 유플러스코드03
					String uplusRes = "";

					if (uplusUserKey != null) {
						String sa = paramData.toString();

						System.out.println("@@@@@@@@@@@@@@@@@@@" + sa + "@@@@@@@@@@@@@@@@@@");

						try {
							String resStr = "{ \"NfcSgw\" : ";

							resStr += "{ \"Header\" : { \"TransactionId\": \"1232142112421\" , \"ResultMsg\" : \"\" , \"ResultCode\" : \"\" }, \"Body\" : ";

							resStr = resStr + "{";
							resStr = resStr + "\"userKey\": \"" + uplusUserKey + "\",";
							resStr = resStr + "\"CTN\": \"" + telNo + "\",";
							resStr = resStr + "\"erecNo\": \"" + var.find("shopInfo.bizNo") + "_"
									+ var.find("salesInfo.salesBarCode") + "\",";
							resStr = resStr + "\"etcInfo\": [ ";
							resStr = resStr + "{";
							resStr = resStr + "\"memo\": \"" + var.find("etcInfo.memo") + "\",";
							resStr = resStr + "\"event\": \"" + var.find("etcInfo.event") + "\"";
							resStr = resStr + "}  ";
							resStr = resStr + " ], ";
							resStr = resStr + "\"shopInfo\": [ ";
							resStr = resStr + "{";
							resStr = resStr + "\"name\": \"" + var.find("shopInfo.name") + "\",";
							resStr = resStr + "\"bizNo\": \"" + var.find("shopInfo.bizNo") + "\",";
							resStr = resStr + "\"addr\": \"" + var.find("shopInfo.address") + "\",";
							resStr = resStr + "\"ceo\": \"" + var.find("shopInfo.ceo") + "\",";
							resStr = resStr + "\"phone\": \"" + var.find("shopInfo.phone") + "\",";
							resStr = resStr + "\"cashier\": \"" + var.find("shopInfo.cashier") + "\"";
							resStr = resStr + "}  ";
							resStr = resStr + " ], ";
							resStr = resStr + "\"salesInfo\": [ ";
							resStr = resStr + "{";
							resStr = resStr + "\"salesBarcode\": \"" + var.find("salesInfo.salesBarCode") + "\",";
							resStr = resStr + "\"salesDate\": \"" + var.find("salesInfo.salesDate") + "\",";
							resStr = resStr + "\"printDate\": \"" + var.find("salesInfo.printDate") + "\",";
							resStr = resStr + "\"salesType\": \"" + var.find("salesInfo.salesType") + "\",";
							resStr = resStr + "\"sumDfAmt\": \"" + var.find("salesInfo.sumDfAmt") + "\",";
							resStr = resStr + "\"sumFpAmt\": \"" + var.find("salesInfo.sumFpAmt") + "\",";
							resStr = resStr + "\"sumTaxAmt\": \"" + var.find("salesInfo.sumTaxAmt") + "\",";
							resStr = resStr + "\"sumAllAmt\": \"" + var.find("salesInfo.sumAllAmt") + "\",";
							resStr = resStr + "\"sumOpAmt\": \"" + var.find("salesInfo.sumOpAmt") + "\",";
							resStr = resStr + "\"chgAmt\": \"" + var.find("salesInfo.chgAmt") + "\",";
							resStr = resStr + "\"paidAmt\": \"" + var.find("salesInfo.paidAmt") + "\",";
							resStr = resStr + "\"dtCnt\": \"" + var.find("salesInfo.dtCnt") + "\"";
							resStr = resStr + "}  ";
							resStr = resStr + " ], ";
							resStr = resStr + "\"salesList\": [ ";

							for (int i = 0; i < var.find("salesList").size(); i++) {

								resStr = resStr + "{";
								resStr = resStr + "\"seqNo\": \"" + var.find("salesList[" + i + "].seqNo") + "\",";
								resStr = resStr + "\"pName\": \"" + var.find("salesList[" + i + "].pName") + "\",";
								resStr = resStr + "\"pPrice\": \"" + var.find("salesList[" + i + "].pPrice") + "\",";
								resStr = resStr + "\"oPrice\": \"" + var.find("salesList[" + i + "].oPrice") + "\",";
								resStr = resStr + "\"qty\": \"" + var.find("salesList[" + i + "].qty") + "\",";
								resStr = resStr + "\"dfAmt\": \"" + var.find("salesList[" + i + "].dfAmt") + "\",";
								resStr = resStr + "\"fpAmt\": \"" + var.find("salesList[" + i + "].fpAmt") + "\",";
								resStr = resStr + "\"taxAmt\": \"" + var.find("salesList[" + i + "].taxAmt") + "\",";
								resStr = resStr + "\"slAmt\": \"" + var.find("salesList[" + i + "].slAmt") + "\",";
								resStr = resStr + "\"opAmt\": \"" + var.find("salesList[" + i + "].opAmt") + "\"";

								if (i == var.find("salesList").size() - 1)
									resStr = resStr + "}  ";
								else {
									resStr = resStr + "} , ";
								}

							}

							resStr = resStr + " ], ";

							resStr = resStr + "\"paidList\": [ ";

							resStr = resStr + "{";

							String pmType = "";

							if (var.find("cardInfo").size() > 0) {
								for (int i = 0; i < var.find("cardInfo").size(); i++) {

									if (var.find("cardInfo[" + i + "].cardNo").toString().contains("01")
											&& var.find("cardInfo[0].cardNo").toString().length() <= 12) {
										pmType = "01";
									} else if (var.find("pointInfo").size() > 0) {
										pmType = "04";
									} else if (!var.find("cardInfo[" + i + "].cardIcom").toString().equals("")) {
										pmType = "02";
									} else {
										pmType = "03";
									}

									resStr = resStr + "\"pmType\": \"" + pmType + " \",";
									resStr = resStr + "\"cardIcom\": \"" + var.find("cardInfo[" + i + "].cardIcom")
											+ "\",";
									resStr = resStr + "\"cardInstallment\": \""
											+ var.find("cardInfo[" + i + "].cardInstallment") + "\",";
									resStr = resStr + "\"pointAmt\": \"" + var.find("customerInfo.pointAmt") + "\"";
								}

								resStr = resStr + "}";
								resStr = resStr + "]";
							}

							else {

								if (var.find("pointInfo").size() > 0) {
									pmType = "04";
								} else {
									pmType = "03";
								}

								resStr = resStr + "\"pmType\": \"" + pmType + "\",";
								resStr = resStr + "\"cardIcom\": \"\",";
								resStr = resStr + "\"cardInstallment\": \"\",";
								resStr = resStr + "\"pointAmt\": \"" + var.find("customerInfo.pointAmt") + "\"";
								resStr = resStr + "}";
								resStr = resStr + "]";
							}

							resStr = resStr + "} } }";

							System.out.println("@@@@@@@@@@@@@@@@@@@resStr@@@@@@@@@@@@@@@@@@@@@@@@@");
							System.out.println(resStr);
							System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

							uplusRes = resStr;
							try {

								AES256 aes = null;

								if (CommonUtils.ipChk()) {
									aes = new AES256("LGU+DEV258010247");
								} else {
									aes = new AES256("LGU+210987654321");
								}

								aes = new AES256("LGU+DEV258010247");

								resStrEnc = aes.encryptStringToBase64(resStr);
							} catch (Exception e) {

								System.out.println("@@@@@@@@@@@@@암호화 오류@@@@@@@@@@@@@@@");
								e.printStackTrace();
								e.getMessage();
								System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
							}

							System.out.println("@@@@@@@@@@@@@@@@전송데이터암호화@@@@@@@@@@@@@@@@@@");
							System.out.println(resStrEnc);
							System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

						} catch (Exception e) {
							System.out.println("@@@@@@@@@@@@resStr에러@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
							System.out.println(e.getMessage());
						}

						// URL 전송
						try {

							String url = "";
							url = "http://dev-wallet-partner.uplus.co.kr:19090/etc/ReceiptPush";
							// url =
							// "https://wallet-partners.uplus.co.kr:19092/etc/ReceiptPush";

							int timeout = 10;

							HttpPost httpost = new HttpPost(new URI(url));
							RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout * 5000)
									.setConnectionRequestTimeout(timeout * 5000).setSocketTimeout(timeout * 5000)
									.build();
							CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config)
									.build();

							RequestConfig.Builder requestBuilder = RequestConfig.custom();
							HttpClientBuilder builder = HttpClientBuilder.create();
							builder.setDefaultRequestConfig(requestBuilder.build());
							org.apache.http.client.HttpClient client = builder.build();

							StringEntity stringEntity = new StringEntity(resStrEnc,ContentType.create("application/json", "UTF-8"));
							httpost.setEntity(stringEntity);

							HttpResponse response0 = httpClient.execute(httpost);
							System.out.println("==============================");
							System.out.println("==============================");
							System.out.println(response0);
							System.out.println("==============================");
							System.out.println("==============================");

							HttpEntity resEntity = response0.getEntity();

							log.debug("■■resEntity■■" + resEntity);

							String resData;
							if (resEntity != null) {
								resData = EntityUtils.toString(resEntity);
								log.debug("■■ 응답데이터■■==" + resData);
							}

						} catch (Exception e) {
							System.out.println("@@@@@@@@@@@@전송 오류 @@@@@@@@@@@@@@@@@@@@@@@@");

							e.printStackTrace();
							e.getMessage();

							System.out.println("@@@@@@@@@@@@전송 오류 @@@@@@@@@@@@@@@@@@@@@@@@");
						}

					}
				} else {
					jsonResData = "{";
					jsonResData += "    \"result\":\"PI101\",";
					jsonResData += "    \"message\":\"전자영수증을 발급 받은 사용자가 아닙니다.\"";
					jsonResData += "}";
				}
			}
		} catch (Exception e) {
			System.out.println("insertReceiptData error - " + e);
			jsonResData = "{";
			jsonResData += "    \"result\":\"PI101\",";
			jsonResData += "    \"message\":\"수신된 데이터에 오류가 있습니다.\"";
			jsonResData += "}";
			// TODO: handle exception
		}

		String paramData2 = "{";
		paramData2 += "\"cashInfo\": {															";
		paramData2 += "	\"cashDate\": \"20161012190557\",                                       ";
		paramData2 += "	\"cashAppNo\": \"\",                                                    ";
		paramData2 += "	\"cashType\": \"CH02\",                                                 ";
		paramData2 += "	\"cashNo\": \"\",                                                       ";
		paramData2 += "	\"cashAmt\": \"11200\"                                                  ";
		paramData2 += "},                                                                         ";
		paramData2 += "\"cardInfo\": {                                                            ";
		paramData2 += "	\"cardNo\": \"\",                                                       ";
		paramData2 += "	\"cardAmt\": \"\",                                                      ";
		paramData2 += "	\"cardDate\": \"\",                                                     ";
		paramData2 += "	\"cardAppNo\": \"\",                                                    ";
		paramData2 += "	\"cardICom\": \"\",                                                     ";
		paramData2 += "	\"cardInstallment\": \"\",                                              ";
		paramData2 += "	\"cardPCom\": \"\"                                                      ";
		paramData2 += "},                                                                         ";
		paramData2 += "\"userKey\": \"01071064573\",                                              ";
		paramData2 += "\"shopInfo\": {                                                            ";
		paramData2 += "	\"ceo\": \"realmarketing\",                                             ";
		paramData2 += "	\"phone\": \"01012345678\",                                             ";
		paramData2 += "	\"address\": \"서울 강남구 압구정로10길 13 마사빌딩 4F\",               ";
		paramData2 += "	\"name\": \"The Real Marketing\",                                       ";
		paramData2 += "	\"bizNo\": \"000-00-00000\",                                            ";
		paramData2 += "	\"cashier\": \"realmarketing\"                                          ";
		paramData2 += "},                                                                         ";
		paramData2 += "\"customerInfo\": {                                                        ";
		paramData2 += "	\"customerCode\": \"\",                                                 ";
		paramData2 += "	\"pointAmt\": \"\",                                                     ";
		paramData2 += "	\"customerPoint\": \"\",                                                ";
		paramData2 += "	\"getPoint\": \"\"                                                      ";
		paramData2 += "},                                                                         ";
		paramData2 += "\"salesList\": [{                                                          ";
		paramData2 += "	\"fpAmt\": \"10182\",                                                   ";
		paramData2 += "	\"oPrice\": \"2800\",                                                   ";
		paramData2 += "	\"taxAmt\": \"1018\",                                                   ";
		paramData2 += "	\"dfAmt\": \"0\",                                                       ";
		paramData2 += "	\"pName\": \"아메리카노\",                                              ";
		paramData2 += "	\"opAmt\": \"11200\",                                                   ";
		paramData2 += "	\"seqNo\": \"1\",                                                       ";
		paramData2 += "	\"slAmt\": \"11200\",                                                   ";
		paramData2 += "	\"qty\": \"4\",                                                         ";
		paramData2 += "	\"pPrice\": \"2800\"                                                        ";
		paramData2 += "}],                                                                        ";
		paramData2 += "\"salesInfo\": {                                                           ";
		paramData2 += "	\"salesType\": \"RCP01\",                                         ";
		paramData2 += "	\"sumSlAmt\": \"11200\",                                                ";
		paramData2 += "	\"sumTaxAmt\": \"1018\",                                                ";
		paramData2 += "	\"rePrint\": \"0\",                                                     ";
		paramData2 += "	\"salesDate\": \"20161012190557\",                                      ";
		paramData2 += "	\"dtCnt\": \"1\",                                                       ";
		paramData2 += "	\"paidAmt\": \"11200\",                                                 ";
		paramData2 += "	\"chgAmt\": \"11200\",                                                  ";
		paramData2 += "	\"sumDfAmt\": \"0\",                                                    ";
		paramData2 += "	\"printDate\": \"20161012190557\",                                      ";
		paramData2 += "	\"salesBarCode\": \"2016101210016\",                                    ";
		paramData2 += "	\"sumOpAmt\": \"11200\",                                                ";
		paramData2 += "	\"sumFpAmt\": \"10182\"                                                 ";
		paramData2 += "},                                                                         ";
		paramData2 += "\"etcInfo\": {                                                             ";
		paramData2 += "	\"memo\": \"memo\",                                                     ";
		paramData2 += "	\"event\": \"event\"                                                    ";
		paramData2 += "}                                                                          ";
		paramData2 += "}                                                                          ";

		String paramData3 = "{                                                                             ";
		paramData3 += "	\"cashInfo\": {                                                             ";
		paramData3 += "		\"cashDate\": \"20161014174537\",                                       ";
		paramData3 += "		\"cashAppNo\": \"\",                                                    ";
		paramData3 += "		\"cashType\": \"CH02\",                                                 ";
		paramData3 += "		\"cashNo\": \"\",                                                       ";
		paramData3 += "		\"cashAmt\": \"5600\"                                                   ";
		paramData3 += "	},																			";
		paramData3 += "	\"cardInfo\": {																";
		paramData3 += "		\"cardNo\": \"\",                                                       ";
		paramData3 += "		\"cardAmt\": \"\",                                                      ";
		paramData3 += "		\"cardDate\": \"\",                                                     ";
		paramData3 += "		\"cardAppNo\": \"\",                                                    ";
		paramData3 += "		\"cardICom\": \"\",                                                     ";
		paramData3 += "		\"cardInstallment\": \"\",                                              ";
		paramData3 += "		\"cardPCom\": \"\"                                                      ";
		paramData3 += "	},                                                                          ";
		paramData3 += "	\"userKey\": \"01071064573\",                                               ";
		paramData3 += "	\"shopInfo\": {                                                             ";
		paramData3 += "		\"ceo\": \"realmarketing\",                                             ";
		paramData3 += "		\"phone\": \"01012345678\",                                             ";
		paramData3 += "		\"address\": \"서울 강남구 압구정로10길 13 마사빌딩 4F\",               ";
		paramData3 += "		\"name\": \"The Real Marketing\",                                       ";
		paramData3 += "		\"bizNo\": \"000-00-00000\",                                            ";
		paramData3 += "		\"cashier\": \"realmarketing\"                                          ";
		paramData3 += "	},                                                                          ";
		paramData3 += "	\"customerInfo\": {                                                         ";
		paramData3 += "		\"customerCode\": \"\",                                                 ";
		paramData3 += "		\"pointAmt\": \"\",                                                     ";
		paramData3 += "		\"customerPoint\": \"\",                                                ";
		paramData3 += "		\"getPoint\": \"\"                                                      ";
		paramData3 += "	},                                                                          ";
		paramData3 += "	\"salesList\": [{                                                           ";
		paramData3 += "		\"fpAmt\": \"5091\",                                                    ";
		paramData3 += "		\"oPrice\": \"2800\",                                                   ";
		paramData3 += "		\"taxAmt\": \"509\",                                                    ";
		paramData3 += "		\"dfAmt\": \"0\",                                                       ";
		paramData3 += "		\"pName\": \"아메리카노\",                                              ";
		paramData3 += "		\"opAmt\": \"5600\",                                                    ";
		paramData3 += "		\"seqNo\": \"1\",                                                       ";
		paramData3 += "		\"slAmt\": \"5600\",                                                    ";
		paramData3 += "		\"qty\": \"2\",                                                         ";
		paramData3 += "		\"pPrice\": \"2800\"                                                    ";
		paramData3 += "	}],                                                                         ";
		paramData3 += "	\"salesInfo\": {                                                            ";
		paramData3 += "		\"salesType\": \"RCP01\",                                               ";
		paramData3 += "		\"sumSlAmt\": \"5600\",                                                 ";
		paramData3 += "		\"sumTaxAmt\": \"509\",                                                 ";
		paramData3 += "		\"rePrint\": \"0\",                                                     ";
		paramData3 += "		\"salesDate\": \"20161014174537\",                                      ";
		paramData3 += "		\"dtCnt\": \"1\",                                                       ";
		paramData3 += "		\"paidAmt\": \"5600\",                                                  ";
		paramData3 += "		\"chgAmt\": \"5600\",                                                   ";
		paramData3 += "		\"sumDfAmt\": \"0\",                                                    ";
		paramData3 += "		\"printDate\": \"20161014174537\",                                      ";
		paramData3 += "		\"salesBarCode\": \"2016101410010\",                                    ";
		paramData3 += "		\"sumOpAmt\": \"5600\",                                                 ";
		paramData3 += "		\"sumFpAmt\": \"5091\"                                                  ";
		paramData3 += "	},                                                                          ";
		paramData3 += "	\"etcInfo\": {                                                              ";
		paramData3 += "		\"memo\": \"memo\",                                                     ";
		paramData3 += "		\"event\": \"event\"                                                    ";
		paramData3 += "	}                                                                           ";
		paramData3 += "}                                                                            ";

		/////////////////////////////// 알림톡///////////////////////////////////////

		try {
			System.out.println(
					"///////////////////////////////알림톡start//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
			String url = "http://www.apiorange.com/api/send/notice.do";
			int timeout = 10;

			// Map<String, Object> kakaoMap = receiptService.getKakao(map)

			AES256 aes = null;

			if (CommonUtils.ipChk()) {
				aes = new AES256("LGU+DEV258010247");
			} else {
				aes = new AES256("LGU+210987654321");
			}

			JSONObject json = new JSONObject();

			json.put("tmp_number", "2456");
			json.put("kakao_sender", "02-540-3111");
			json.put("kakao_phone", telNo.toString());
			json.put("kakao_name", telNo.substring(telNo.length() - 4));
			json.put("kakao_080", "Y");
			json.put("TRAN_REPLACE_TYPE", "S");
			json.put("kakao_add1", var.find("shopInfo.name").toString());
			String td = var.find("salesInfo.salesDate").toString();
			System.out.println(td);
			if (td.length() < 17) {
				td = td.substring(0, 4) + "." + td.substring(4, 6) + "." + td.substring(6, 8) + " "
						+ td.substring(8, 10) + ":" + td.substring(10, 12) + ":" + td.substring(12, 14);
			}
			json.put("kakao_add2", td);
			json.put("kakao_add3", var.find("shopInfo.name").toString());
			json.put("kakao_add4", first.common.util.CommonUtils.replaceComma(Integer.parseInt(var.find("salesInfo.paidAmt").toString())) + "원");

			String kakaoBarcode = var.find("salesInfo.salesBarCode").toString();

			json.put("kakao_url1_1",
					"http://110.45.190.114:28080/theReal/receipt/kakaoReceipt.do?No="
							+ URLEncoder.encode(aes.encryptStringToBase64(kakaoBarcode), "UTF-8") + "&t="
							+ URLEncoder.encode(aes.encryptStringToBase64(telNo), "UTF-8"));
			// json.put("kakao_add5",
			// "http://110.45.190.114:28080/theReal/receipt/kakaoReceipt.do?No="+URLEncoder.encode(aes.encryptStringToBase64(kakaoBarcode),"UTF-8")+"&t="+URLEncoder.encode(aes.encryptStringToBase64(telNo),"UTF-8"));

			System.out.println(json.toString());

			HttpPost httpost = new HttpPost(new URI(url));

			httpost.addHeader("Authorization", "NvMMEL2bEB1aeSeUK0Mgd5ymKwfQGUv6LNUo/vuY2f0=");

			RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout * 1000)
					.setConnectionRequestTimeout(timeout * 1000).setSocketTimeout(timeout * 1000).build();
			CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();

			RequestConfig.Builder requestBuilder = RequestConfig.custom();
			HttpClientBuilder builder = HttpClientBuilder.create();
			builder.setDefaultRequestConfig(requestBuilder.build());
			org.apache.http.client.HttpClient client = builder.build();

			StringEntity stringEntity = new StringEntity(json.toJSONString(),
					ContentType.create("application/json", "UTF-8"));
			httpost.setEntity(stringEntity);

			HttpResponse response0 = httpClient.execute(httpost);
			HttpEntity resEntity = response0.getEntity();

			log.debug("■■resEntity■■" + resEntity);

			String resData;
			if (resEntity != null) {
				resData = EntityUtils.toString(resEntity);
				log.debug("■■ 응답데이터■■==" + resData);
			}

			System.out.println(
					"///////////////////////////////알림톡end///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
			////////////////////////////// 알림톡///////////////////////////////////////

		} catch (Exception e) {
			// TODO: handle exception
		}
		// log.debug("paramData2:"+paramData2);
		// String eRecResData = TheRealShopToERec.theRealToEReceipt2(request,
		// response, paramData.toString());
		// log.debug("eRecResData:"+eRecResData);
		// JSON데이터를 넣어 JSON Object 로 만들어 준다.
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = null;
		jsonObject = (JSONObject) jsonParser.parse(jsonResData);

		return jsonObject;
	}

	/*
	 * 전자영수증 통합 플랫폼
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/receipt/receiptData.do")
	@ResponseBody
	// public ModelAndView insertReceiptData(CommandMap commandMap,
	// HttpServletRequest request) throws Exception{
	public Object receiptData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//알림톡 초기화
		kakaoArlimtalk kakaoArl = new kakaoArlimtalk();
		Map<String, String> kakaoMap = new HashMap<String, String>();
		
		//변수 설정
		String CI = "";
		Var var = null;
		String delBarcode = "";
		String delBizNo = "";
		String uplusUserKey = "";
		String jsonResData = "";
		String str = null;
		String resStrEnc = "";
		String paymentType = "";
		String paymentTypeCode = "";
		boolean dateDel = false;
		String errorCol = "";
		String errorDeCol = "";
		
		
		//암호화 모듈
		AES256 aes = null;
		if (CommonUtils.ipChk()) {
			aes = new AES256("LGU+DEV258010247");
		} else {
			aes = new AES256("LGU+210987654321");
		}

		StringBuffer paramData = new StringBuffer();
		BufferedReader br = null;
		// System.out.println("전달 받은 데이터 :" + paramData);

		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream(), "EUC-KR"));

			while ((str = br.readLine()) != null) {
				paramData.append(str);
				// System.out.println("@@str@@:"+str);

			}

			System.out.println("Request Data ::::  " + paramData.toString());

			// 성공시
			jsonResData = "{";
			jsonResData += "    \"result\":\"PI000\",";
			jsonResData += "    \"message\":\"전송성공\"";
			jsonResData += "}";
			System.out.println("jsonResData:" + jsonResData);

		} catch (Exception e) {
			// 실패시
			jsonResData = "{";
			jsonResData += "    \"result\":\"PI101\",";
			jsonResData += "    \"message\":\"수신된 데이터 빈값 입니다.\"";
			jsonResData += "}";
			e.printStackTrace();
			throw e;
		}

		
		////////////
		/*String tempStr = " { \"cashInfo\" : [{         \"cashAmt\" : \"2000\", \"cashType\" : \"CH02\"  , \"cashNo\" : \"\", \"cashAppNo\":\"\" , \"cashDate\" : \"\"    }], \"compoundYN\" : \"N\", \"etcInfo\" : {       \"connectionCom\" : \"OK\", \"event\" : \"\", \"memo\" : \"\"    },		\"paymentList\" : [{          \"paymentType\" : \"01\"       }],	     \"salesInfo\" : {       \"chgAmt\" : \"11000\", \"discountAmt\" : \"0\", \"detailCnt\" : \"1\", \"paidAmt\" : \"11000\", \"printDate\" : \"20180412\", \"rePrint\" : \"1\", \"salesBarcode\" : \"010072\", \"salesDate\" : \"20180412\", \"salesType\" : \"RCP01\", \"dfAmt\" : \"0\", \"surtaxAmt\" : \"10000\", \"taxAmt\" : \"1000\", \"totAmt\" : \"11000\"    },	\"salseList\" : [{          \"discountPrice\" : \"0\", \"pPrice\" : \"11000\", \"oPrice\" : \"11000\", \"pName\" : \"김밥\", \"pPrice\" : \"10000\", \"paidPrice\" : \"11000\", \"qty\" : \"1\", \"seqNo\" : \"1\", \"taxPrice\" : \"1000\"       }],		\"shopInfo\" : {       \"addr\" : \"서울 금천구 벚꽃로 234 (가산동, 에이스하이엔드타워6차) 지하10층\", \"bizNo\" : \"2208115770\", \"cashier\" : \"김성곤\", \"ceo\" : \"김성곤\", \"ercpRegNo\" : \"\", \"shopName\" : \"DT0055\", \"phone\" : \"111-1111-1111\"    } , \"userKey\": \"01074505585\", \"pointType\":\"00\"} ";*/

	/*	paramData =  new StringBuffer();
		paramData.append(tempStr);
		*/
		
		//System.out.println("Request Data ::::  " + paramData.toString());
		
		
		//////////
		
		var = JsonParser.object(new CharTokenizer(paramData.toString()));
		HashMap<String, Object> insertMap = new HashMap<String, Object>();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		HashMap<String, Object> detailMap = new HashMap<String, Object>();
		
		//catch 오류 시 삭제를 위한 컬럼
		delBarcode = var.find("salesInfo.salesBarCode").toString();
		delBizNo = var.find("shopInfo.bizNo").toString();
		HashMap<String, Object> delMap = new HashMap<String, Object>();
		delMap.put("delBarcode", delBarcode);
		delMap.put("delBizNo", delBizNo);
		
		try {
			if (var.find("salesInfo.salesType").toString().equals("RCP01")) {
				log.debug("====================================================");
				log.debug("■■■■■■■■■■■■■■■■■RCP01 전자영수증 승인건■■■■■■■■■■■■■789■■■■■");
				
				delMap.put("salesType", "RCP01");
				
				System.out.println(var.find("paymentList").size());
				errorCol = "compoundYN";
				if (var.find("compoundYN").toString().equals("Y")) {
					for (int i = 0; i < var.find("paymentList").size(); i++) {
						errorDeCol = "paymentType";
						paymentType += switchPayType(var.find("paymentList["+i+"].paymentType").toString()); errorDeCol = "paymentTypeCode";
						paymentTypeCode += var.find("paymentList["+i+"].paymentType").toString();
						errorDeCol = "";
					}
				} else {
					errorDeCol = "paymentType";
					paymentType = switchPayType(var.find("paymentList[0].paymentType").toString()); errorDeCol = "paymentTypeCode";
					paymentTypeCode = var.find("paymentList[0].paymentType").toString();
					errorDeCol = "";
				}
				errorCol = "";
				System.out.println(paymentType);
				errorCol = "userKey";
				insertMap.put("userKey", var.find("userKey").toString());
				insertMap.put("telNo", var.find("userKey").toString());
	
				uplusUserKey = receiptService.uPlusChk(var.find("userKey").toString());
				CI = (String) receiptService.getCi(insertMap);
				errorCol = "CI";
				insertMap.put("CI", CI);
				System.out.println();
				errorCol = "etcInfo";
				System.out.println("::::etcInfo DATA::::");
				// etcInfo
				insertMap.put("connectionCom", var.find("etcInfo.connectionCom").toString());
				insertMap.put("memo", var.find("etcInfo.memo").toString());
				insertMap.put("event", var.find("etcInfo.event").toString());
				
				System.out.println();
				errorCol = "shopInfo";
				System.out.println("::::shopInfo DATA::::");
				// shopInfo
				insertMap.put("shopName", var.find("shopInfo.shopName").toString());
				insertMap.put("branchName", var.find("shopInfo.branchName").toString());
				insertMap.put("ercpRegNo", var.find("shopInfo.ercpRegNo").toString());
				insertMap.put("bizNo", var.find("shopInfo.bizNo").toString());
				insertMap.put("addr", var.find("shopInfo.addr").toString());//수정
				insertMap.put("ceo", var.find("shopInfo.ceo").toString());
				insertMap.put("phone", var.find("shopInfo.phone").toString());
				insertMap.put("cashier", var.find("shopInfo.cashier").toString());
				
				System.out.println();
				errorCol = "salesInfo";
				System.out.println("::::salesInfo DATA::::");
				// salesInfo
				errorDeCol = "salesBarCode";
				insertMap.put("salesBarCode", var.find("salesInfo.salesBarCode").toString()); errorDeCol = "salesDate";
				insertMap.put("salesDate", var.find("salesInfo.salesDate").toString()); errorDeCol = "printDate";
				insertMap.put("printDate", var.find("salesInfo.printDate").toString()); errorDeCol = "salesType";
				insertMap.put("salesType", var.find("salesInfo.salesType").toString()); errorDeCol = "totAmt";
				insertMap.put("totAmt", var.find("salesInfo.totAmt").toString()); errorDeCol = "discountAmt";
				insertMap.put("discountAmt", var.find("salesInfo.discountAmt").toString()); errorDeCol = "chgAmt";
				insertMap.put("chgAmt", var.find("salesInfo.chgAmt").toString()); errorDeCol = "paidAmt";
				insertMap.put("paidAmt", var.find("salesInfo.paidAmt").toString()); errorDeCol = "surtaxAmt";
				insertMap.put("surtaxAmt", var.find("salesInfo.surtaxAmt").toString()); errorDeCol = "dfAmt";
				insertMap.put("dfAmt", var.find("salesInfo.dfAmt").toString()); errorDeCol = "taxAmt";
				insertMap.put("taxAmt", var.find("salesInfo.taxAmt").toString()); errorDeCol = "rePrint";
				insertMap.put("rePrint", var.find("salesInfo.rePrint").toString()); errorDeCol = "detailCnt";
				insertMap.put("detailCnt", var.find("salesInfo.detailCnt").toString()); 
				
				// paymentType.
				System.out.println();
				errorCol = "paymentType";
				errorDeCol = "";
				System.out.println("::::paymentType DATA::::");
				insertMap.put("compoundYN", var.find("compoundYN").toString());
				if (paymentType.contains("현금")) {
					errorDeCol = "cashAmt";
					insertMap.put("cashAmt", var.find("cashInfo[0].cashAmt").toString()); errorDeCol = "cashType";
					insertMap.put("cashType", var.find("cashInfo[0].cashType").toString()); errorDeCol = "cashNo";
					insertMap.put("cashNo", var.find("cashInfo[0].cashNo").toString()); errorDeCol = "cashAppNo";
					insertMap.put("cashAppNo", var.find("cashInfo[0].cashAppNo").toString()); errorDeCol = "cashDate";
					insertMap.put("cashDate", var.find("cashInfo[0].cashDate").toString());
					errorDeCol ="";
				} else {
					insertMap.put("cashAmt", "");
					insertMap.put("cashType", "");
					insertMap.put("cashNo", "");
					insertMap.put("cashAppNo", "");
					insertMap.put("cashDate", "");
				}
				// 카드는 복합이 가능하여 For문으로 반복
				if (paymentType.contains("카드")) {
					String cardAmt = "";
					String cardInstallment = "";
					String cardAppNo = "";
					String cardDate = "";
					String cardIcom = "";
					String cardPcom = "";
					String cardNo = "";
	
					for (int i = 0; i < var.find("cardInfo").size(); i++) {
						errorDeCol = "cardAmt";
						cardAmt += var.find("cardInfo[" + i + "].cardAmt").toString() + "/"; errorDeCol = "cardInstallment";
						cardInstallment += var.find("cardInfo[" + i + "].cardInstallment").toString() + "/"; errorDeCol = "cardAppNo";
						cardAppNo += var.find("cardInfo[" + i + "].cardAppNo").toString() + "/"; errorDeCol = "cardDate";
						cardDate += var.find("cardInfo[" + i + "].cardDate").toString() + "/"; errorDeCol = "cardIcom";
						cardIcom += var.find("cardInfo[" + i + "].cardIcom").toString() + "/"; errorDeCol = "cardPcom";
						cardPcom += var.find("cardInfo[" + i + "].cardPcom").toString() + "/"; errorDeCol = "cardNo";
						cardNo += var.find("cardInfo[" + i + "].cardNo").toString() + "/";
						errorDeCol ="";
					}
					insertMap.put("cardAmt", cardAmt.substring(0, cardAmt.length() - 1));
					insertMap.put("cardInstallment", cardInstallment.substring(0, cardInstallment.length() - 1));
					insertMap.put("cardAppNo", cardAppNo.substring(0, cardAppNo.length() - 1));
					insertMap.put("cardDate", cardDate.substring(0, cardDate.length() - 1));
					insertMap.put("cardIcom", cardIcom.substring(0, cardIcom.length() - 1));
					insertMap.put("cardPcom", cardPcom.substring(0, cardPcom.length() - 1));
					insertMap.put("cardNo", cardNo.substring(0, cardNo.length() - 1));
				} else {
					insertMap.put("cardAmt", "");
					insertMap.put("cardInstallment", "");
					insertMap.put("cardAppNo", "");
					insertMap.put("cardDate", "");
					insertMap.put("cardIcom", "");
					insertMap.put("cardPcom", "");
					insertMap.put("cardNo", "");
				}
				if (paymentType.contains("모바일")) {
					String payAmt = "";
					String payAppNo = "";
					String payDate = "";
					String payIcom = "";
	
					for (int i = 0; i < var.find("payInfo").size(); i++) {
						errorDeCol = "payAmt";
						payAmt += var.find("payInfo[" + i + "].payAmt").toString() + "/"; errorDeCol = "payAppNo";
						payAppNo += var.find("payInfo[" + i + "].payAppNo").toString() + "/"; errorDeCol = "payDate";
						payDate += var.find("payInfo[" + i + "].payDate").toString() + "/"; errorDeCol = "payIcom";
						payIcom += var.find("payInfo[" + i + "].payIcom").toString() + "/";
						errorDeCol ="";
					}
					insertMap.put("payAmt", payAmt.substring(0, payAmt.length() - 1));
					insertMap.put("payAppNo", payAppNo.substring(0, payAppNo.length() - 1));
					insertMap.put("payDate", payDate.substring(0, payDate.length() - 1));
					insertMap.put("payIcom", payIcom.substring(0, payIcom.length() - 1));
				} else {
					insertMap.put("couponNo", "");
					insertMap.put("couponType", "");
					insertMap.put("couponAmt", "");
					insertMap.put("couponCashYN", "");
				}
				if (paymentType.contains("쿠폰")) {
					errorDeCol = "couponNo";
					insertMap.put("couponNo", var.find("couponInfo.payAmt").toString());  errorDeCol = "couponType";
					insertMap.put("couponType", var.find("couponInfo.payAppNo").toString()); errorDeCol = "couponAmt";
					insertMap.put("couponAmt", var.find("couponInfo.couponAmt").toString()); errorDeCol = "couponCashYN";
					insertMap.put("couponCashYN", var.find("couponInfo.couponCashYN").toString());
					errorDeCol ="";
				} else {
					insertMap.put("couponNo", "");
					insertMap.put("couponType", "");
					insertMap.put("couponAmt", "");
					insertMap.put("couponCashYN", "");
				}
				errorCol = "pointType";
				insertMap.put("pointType", var.find("pointType").toString());
				if (insertMap.get("pointType").equals("01") || insertMap.get("pointType").equals("02") || insertMap.get("pointType").equals("03")) {
					 errorDeCol = "payAmt";
					insertMap.put("payAmt", var.find("payInfo.payAmt").toString()); errorDeCol = "payAppNo";
					insertMap.put("payAppNo", var.find("payInfo.payAppNo").toString()); errorDeCol = "payDate";
					insertMap.put("payDate", var.find("payInfo.payDate").toString()); errorDeCol = "payIcom";
					insertMap.put("payIcom", var.find("payInfo.payIcom").toString()); errorDeCol = "pointGet";
					insertMap.put("pointGet", var.find("payInfo.pointGet").toString());
					errorDeCol ="";
				} else {
					insertMap.put("payAmt", "");
					insertMap.put("payAppNo", "");
					insertMap.put("payDate", "");
					insertMap.put("payIcom", "");
					insertMap.put("pointGet", "");
				}
	
				
				dateDel = true;
				System.out.println("INSERT MAP ::: "+insertMap);
				receiptService.insertReceiptDataRenew(insertMap);
				errorDeCol ="";
				// 디테일
				System.out.println();
				errorCol = "salesList";
				System.out.println("::::detail DATA::::" + var.find("salesList").size());
				for (int i = 0; i < var.find("salesList").size(); i++) {
					errorDeCol = "CI";
					detailMap.put("CI", CI);
					errorDeCol = "uplusUserKey";
					detailMap.put("uplusUserKey", uplusUserKey);
					errorDeCol = "uplusshopBizNoUserKey";
					detailMap.put("shopBizNo", var.find("shopInfo.bizNo").toString());
					errorDeCol = "salesBarCode";
					detailMap.put("salesBarCode", var.find("salesInfo.salesBarCode").toString()); 
					errorDeCol = "userKey";
					detailMap.put("userKey", var.find("userKey").toString());
					errorDeCol = "salesType";					
					detailMap.put("salesType", var.find("salesInfo.salesType").toString());
					errorDeCol = "seqNo";
					detailMap.put("seqNo", var.find("salesList[" + i + "].seqNo").toString());
					errorDeCol = "pName";
					detailMap.put("pName", var.find("salesList[" + i + "].pName").toString());
					errorDeCol = "pPrice";
					detailMap.put("pPrice", var.find("salesList[" + i + "].pPrice").toString());
					errorDeCol = "taxPrice";
					detailMap.put("taxPrice", var.find("salesList[" + i + "].taxPrice").toString());
					errorDeCol = "fpPrice";
					detailMap.put("fpPrice", var.find("salesList[" + i + "].fpPrice").toString());
					errorDeCol = "qty";
					detailMap.put("qty", var.find("salesList[" + i + "].qty").toString());
					errorDeCol = "oPrice";
					detailMap.put("oPrice", var.find("salesList[" + i + "].oPrice").toString());
					errorDeCol = "discountPrice";
					detailMap.put("discountPrice", var.find("salesList[" + i + "].discountPrice").toString());
					errorDeCol = "paidPrice";
					detailMap.put("paidPrice", var.find("salesList[" + i + "].paidPrice").toString());
					errorDeCol = "";
					receiptService.insertReceiptDeatailDataRenew(detailMap);
				}
	
				dateDel = false;
				/////////////////////////////// 알림톡 renew RCP01///////////////////////////////////////
				
				try {
					//Link URL 작성
					String kakaoBarcode = var.find("salesInfo.salesBarCode").toString();
					String kakaoUrl = "http://110.45.190.114:28080/theReal/receipt/kakaoReceiptRenew.do?No=" + URLEncoder.encode(aes.encryptStringToBase64(kakaoBarcode), "UTF-8") + "&t=" + URLEncoder.encode(aes.encryptStringToBase64(var.find("userKey").toString()), "UTF-8")+ "&POS=" + URLEncoder.encode(aes.encryptStringToBase64("OK"), "UTF-8");
					
					//고정 입력 값 (주석 사용x 추후 사용 가능)
					kakaoMap.put("tmp_number", "5580");										//템플릿 번호
					kakaoMap.put("kakao_sender", "02-540-3111");							//발송 번호
					kakaoMap.put("kakao_phone", var.find("userKey").toString());			//발신 번호
					kakaoMap.put("kakao_name", (var.find("userKey").toString()).substring(var.find("userKey").toString().length() - 4));		//발신자 이름
					kakaoMap.put("kakao_080", "Y");											//080 무료 수신 유무
					kakaoMap.put("TRAN_REPLACE_TYPE", "S");									//대체문자 S: SMS L:LMS
					//추가정보 이하 동
					kakaoMap.put("kakao_add1", var.find("shopInfo.shopName").toString());	//업체 명		
					kakaoMap.put("kakao_add2", var.find("shopInfo.branchName").toString());	//매장 명
					kakaoMap.put("kakao_add3", var.find("salesInfo.salesDate").toString());	//결제일시 (ex YYYY.MM.DD)
					kakaoMap.put("kakao_add4", var.find("salesInfo.paidAmt").toString());	//금액       (2,000원)
					//String kakao_add5 	= "";
					//모바일 링크
					kakaoMap.put("kakao_url1_1", kakaoUrl);
					/*
					String kakao_url1_2 = "";	
					String kakao_url2_1 = "";		
					String kakao_url2_2 = "";	
					*/
					
					
					String arlResult = kakaoArl.kakaoArlimtalk(kakaoMap);
					System.out.println(arlResult);
					
				} catch (Exception e) {
					jsonResData = "{";
					jsonResData += "    \"result\":\"PI801\",";
					jsonResData += "    \"message\":\"알림톡 오류 \" ";
					jsonResData += "}";
					System.out.println("jsonResData:" + jsonResData);
				}
				
				
				
				
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////RCP02 *취소 영수증 발급
			} else {
				delMap.put("salesType", "RCP02");
				errorCol = "bizNo";
				String bizNo = var.find("shopInfo.bizNo").toString(); errorCol = "cashier";
				String cashier = var.find("shopInfo.cashier").toString(); errorCol = "oriSalesBarCode";
				String oriSalesBarCode = var.find("salesInfo.oriSalesBarCode").toString(); errorCol = "salesBarCode";
				String salesBarCode = var.find("salesInfo.salesBarCode").toString(); errorCol = "oriSalesDate";
				String oriSalesDate = var.find("salesInfo.oriSalesDate").toString(); errorCol = "salesDate";
				String salesDate = var.find("salesInfo.salesDate").toString(); errorCol = "DB 데이터 형식 오류 / 중복 SalesBarCode 확인";
				
				insertMap.put("bizNo", bizNo);
				insertMap.put("cashier", cashier);
				insertMap.put("oriSalesBarCode", oriSalesBarCode);
				insertMap.put("salesBarCode", salesBarCode);
				insertMap.put("oriSalesDate", oriSalesDate);
				insertMap.put("salesDate", salesDate);
				insertMap.put("telNo", var.find("userKey").toString());
				
				
				
				
				resultMap = receiptService.cancleGetReceipt(insertMap);
				String userKey = resultMap.get("USER_KEY").toString();
				resultMap.put("telNo", userKey);
				uplusUserKey = receiptService.uPlusChk(userKey);
				CI = (String) receiptService.getCi(resultMap);
				
				resultMap.put("salesBarCode", salesBarCode);
				resultMap.put("oriSalesDate", oriSalesDate);
				resultMap.put("mainSalesBarCode", "RCP02"+salesBarCode);
				resultMap.put("oriSalesDate", oriSalesDate);
				resultMap.put("salesDate", salesDate);
				resultMap.put("CI",CI);
				
				resultMap.put("userKey", resultMap.get("USER_KEY"));
				resultMap.put("memo", resultMap.get("ETC_MEMO"));
				resultMap.put("event", resultMap.get("ETC_EVENT"));
				resultMap.put("shopName", resultMap.get("SHOP_NAME"));
				resultMap.put("branchName", resultMap.get("SHOP_BRANCH"));
				resultMap.put("bizNo", resultMap.get("SHOP_BIZNO"));
				resultMap.put("addr", resultMap.get("SHOP_ADDR"));
				resultMap.put("ceo", resultMap.get("SHOP_CEO"));
				resultMap.put("phone", resultMap.get("SHOP_TEL_NUM"));
				resultMap.put("cashier", resultMap.get("SHOP_CASHIER"));
				resultMap.put("salesDate", resultMap.get("SALES_DATE"));
				resultMap.put("printDate", resultMap.get("SALES_PRINT_DATE"));
				resultMap.put("totAmt", resultMap.get("SALES_TOT_AMT"));
				resultMap.put("discountAmt", resultMap.get("SALES_DISCOUNT_AMT"));
				resultMap.put("chgAmt", resultMap.get("SALES_CHG_AMT"));
				resultMap.put("paidAmt", resultMap.get("SALES_PAID_AMT"));
				resultMap.put("surtaxAmt", resultMap.get("SALES_SURTAX_AMT"));
				resultMap.put("dfAmt", resultMap.get("SALES_DF_AMT"));	
				resultMap.put("taxAmt", resultMap.get("SALES_TAX_AMT"));
				resultMap.put("detailCnt", resultMap.get("SALES_DETAIL_CNT"));
				resultMap.put("rePrint", resultMap.get("SALES_RE_PRINT"));
				resultMap.put("cashAmt", resultMap.get("CASH_AMT"));
				resultMap.put("cashType", resultMap.get("CASH_TYPE"));
				resultMap.put("cashNo", resultMap.get("CASH_NO"));
				resultMap.put("cashAppNo", resultMap.get("CASH_APP_N0"));
				resultMap.put("cashDate", resultMap.get("CASH_DATE"));
				resultMap.put("cardAmt", resultMap.get("CARD_AMT"));
				resultMap.put("cardInstallment", resultMap.get("CARD_INSTALLMENT"));
				resultMap.put("cardAppNo", resultMap.get("CARD_APP_NO"));
				resultMap.put("cardDate", resultMap.get("CARD_DATE"));
				resultMap.put("cardICom", resultMap.get("CARD_ICOM"));
				resultMap.put("cardPCom", resultMap.get("CARD_PCOM"));
				resultMap.put("cardNo", resultMap.get("CARD_NO"));
				resultMap.put("pointCardNo", resultMap.get("POINT_CARD"));
				resultMap.put("pointAmt", resultMap.get("POINT_AMT"));
				resultMap.put("pointType", resultMap.get("POINT_TYPE"));
				resultMap.put("pointIcom", resultMap.get("POINT_ICOM"));
				resultMap.put("pointGet", resultMap.get("POINT_GET"));
				resultMap.put("eMail", resultMap.get("EMAIL"));
				resultMap.put("salesBarCode", resultMap.get("SALES_BARCODE"));
				resultMap.put("uplusUserKey", resultMap.get("UPLUS_USER_KEY"));
				dateDel = true;
				receiptService.insertCancleReceiptData(resultMap);
				
				
				dateDel = false;
				/////////////////////////////// 알림톡 renew RCP02///////////////////////////////////////
	
				try {
					//Link URL 작성
					String kakaoBarcode = resultMap.get("SALES_BARCODE").toString();
					String kakaoUrl = "http://110.45.190.114:28080/theReal/receipt/kakaoReceiptRenew.do?No=" + URLEncoder.encode(aes.encryptStringToBase64(kakaoBarcode), "UTF-8") + "&t=" + URLEncoder.encode(aes.encryptStringToBase64(var.find("userKey").toString()), "UTF-8")+ "&POS=" + URLEncoder.encode(aes.encryptStringToBase64("OK"), "UTF-8");
					
					//고정 입력 값 (주석 사용x 추후 사용 가능)
					kakaoMap.put("tmp_number", "5581");										//템플릿 번호
					kakaoMap.put("kakao_sender", "02-540-3111");							//발송 번호
					kakaoMap.put("kakao_phone", resultMap.get("USER_KEY").toString());			//발신 번호
					kakaoMap.put("kakao_name", resultMap.get("USER_KEY").toString().substring(resultMap.get("USER_KEY").toString().length() - 4));		//발신자 이름
					kakaoMap.put("kakao_080", "Y");											//080 무료 수신 유무
					kakaoMap.put("TRAN_REPLACE_TYPE", "S");									//대체문자 S: SMS L:LMS
					//추가정보 이하 동
					kakaoMap.put("kakao_add1", resultMap.get("SHOP_NAME").toString());	//업체 명		
					kakaoMap.put("kakao_add2", resultMap.get("SHOP_BRANCH").toString());	//매장 명
					kakaoMap.put("kakao_add3", resultMap.get("SALES_DATE").toString());	//결제일시 (ex YYYY.MM.DD)
					kakaoMap.put("kakao_add4", resultMap.get("SALES_PAID_AMT").toString());	//금액       (2,000원)
					//String kakao_add5 	= "";
					//모바일 링크
					kakaoMap.put("kakao_url1_1", kakaoUrl);
					/*
					String kakao_url1_2 = "";	
					String kakao_url2_1 = "";		
					String kakao_url2_2 = "";	
					*/
					
					String arlResult = kakaoArl.kakaoArlimtalk(kakaoMap);
					System.out.println(arlResult);
					
				} catch (Exception e) {
					jsonResData = "{";
					jsonResData += "    \"result\":\"PI801\",";
					jsonResData += "    \"message\":\"알림톡 오류 \" ";
					jsonResData += "}";
					System.out.println("jsonResData:" + jsonResData);
				}
			}
		
		} catch (Exception e) {
			

			jsonResData = "{";
			jsonResData += "    \"result\":\"PI102\",";
			if(errorDeCol.equals("")){
				jsonResData += "    \"message\":\"JSON형식 오류 ( " + errorCol +" 필드 확인 필요 )\" ";
			}else if(errorDeCol.equals("DB")){
				jsonResData += "    \"message\":\"" + errorCol + ")\" ";
			}else{
				jsonResData += "    \"message\":\"JSON형식 오류 ( " + errorCol + " - "+errorDeCol+" 필드 확인 필요 )\" ";
			}
			
			jsonResData += "}";
			System.out.println("jsonResData:" + jsonResData);
			
			
			if(dateDel){
				receiptService.deleteFailDate(delMap);
			}
			
			
			
			
		}
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = null;
		jsonObject = (JSONObject) jsonParser.parse(jsonResData);

		return jsonObject;
	}

	/*
	 * 전송받은 전자영수증 데이터 저장
	 */
	@RequestMapping(value = "/receipt/smsCardInfo.do")
	@ResponseBody
	// public ModelAndView insertReceiptData(CommandMap commandMap,
	// HttpServletRequest request) throws Exception{
	public JSONObject smsCardInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String str;
		String jsonResData = "";
		StringBuffer paramData = new StringBuffer();

		System.out.println("@@paramDataparamData@@:" + paramData);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			System.out.println("@@br@@:" + br);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

		try {
			while ((str = br.readLine()) != null) {
				paramData.append(str);
				// System.out.println("@@str@@:"+str);
				System.out.println("@@paramData22@@:" + paramData);
			}
			Var var = JsonParser.object(new CharTokenizer(paramData.toString()));

			/*
			 * HashMap<String,Object> smsMsgMap = new HashMap<String,Object>();
			 * smsMsgMap.put("origNumber", var.find("origNumber").toString());
			 * smsMsgMap.put("message", var.find("message").toString());
			 * smsMsgMap.put("phoneNumber", var.find("phoneNumber").toString());
			 */
			// {"origNumber":"01071064573","message":"[Web발신]\n삼성카드승인9360설*진\r\n12\/13
			// 07:37 씨유수원대한\r\n2,200원
			// 일시불\r\n누적2,037,450원","phoneNumber":"01077003578"}

			String recTelNo = var.find("phoneNumber").toString();
			String sendTelNo = var.find("origNumber").toString();
			log.debug("recTelNo:" + recTelNo);
			log.debug("sendTelNo:" + sendTelNo);
			log.debug("message:" + var.find("message").toString());
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("telNo", recTelNo);
			String CI = receiptService.getCi(map);

			map.put("REC_TEN_NO", recTelNo);
			map.put("SEND_TEN_NO", sendTelNo);

			String sms = var.find("message").toString();
			System.out.println("sms - " + sms);
			HashMap<String, Object> insertMap = SmsParse.smsCardParse(sendTelNo, recTelNo, sms);
			System.out.println(":::::::::::::::::::::::");
			System.out.println("SMS CI :: " + CI);
			System.out.println(":::::::::::::::::::::::");
			insertMap.put("CI", CI);
			if (!insertMap.get("REC_TEN_NO").equals("") || insertMap.get("REC_TEN_NO") != null) {
				receiptService.insertSmsData(insertMap);
			} else {
				insertMap = null;
			}

			if (insertMap != null || insertMap.get("REC_TEN_NO") != null) {
				jsonResData = "{";
				jsonResData += "    \"result\":\"PI100\",";
				jsonResData += "    \"message\":\"전송 성공.\"";
				jsonResData += "}";
			} else {
				jsonResData = "{";
				jsonResData += "    \"result\":\"PI101\",";
				jsonResData += "    \"message\":\"전송 실패.\"";
				jsonResData += "}";
			}
		} catch (IOException e) {
			// 실패시
			jsonResData = "{";
			jsonResData += "    \"result\":\"PI101\",";
			jsonResData += "    \"message\":\"수신된 데이터 빈값 입니다. - " + e + "\"";
			jsonResData += "}";

			e.printStackTrace();
			throw e;
		}

		// JSON데이터를 넣어 JSON Object 로 만들어 준다.
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = null;
		jsonObject = (JSONObject) jsonParser.parse(jsonResData);

		return jsonObject;
	}

	@RequestMapping(value = "/receipt/joinChkId.do")
	@ResponseBody
	public Integer joinChkId(CommandMap commandMap) throws Exception {

		log.debug(commandMap);
		String eMail = (String) commandMap.get("eMail");
		Integer resultInt = receiptService.joinChkId(eMail);
		return resultInt;
	}

	@RequestMapping(value = "/receipt/lgnChk.do")
	@ResponseBody
	public Object lgnChk(CommandMap commandMap) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("eMail", (String) commandMap.get("eMail"));
		map.put("pwd", Sha256.encryptString((String) commandMap.get("pwd")));
		// Integer resultInt = receiptService.lgnChk(map);
		Map<String, Object> resultMap = receiptService.lgnChk(map);
		return resultMap;
	}

	@RequestMapping(value = "/receipt/lgnChk2.do")
	@ResponseBody
	public Object lgnChk2(CommandMap commandMap) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("CI", (String) commandMap.get("CI"));
		map.put("telNo", (String) commandMap.get("telNo"));
		map.put("deviceId", (String) commandMap.get("deviceId"));
		// Integer resultInt = receiptService.lgnChk(map);
		Map<String, Object> resultMap = receiptService.lgnChk2(map);

		return resultMap;
	}

	@RequestMapping(value = "/receipt/getId.do")
	@ResponseBody
	public Object getId(CommandMap commandMap) throws Exception {

		String telNo = (String) commandMap.getMap().get("telNo");
		String telComDiv = (String) commandMap.getMap().get("telComDiv");
		String userNm = (String) commandMap.getMap().get("userNm");

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("telNo", telNo);
		map.put("telComDiv", telComDiv);
		map.put("userNm", userNm);
		// Integer resultInt = receiptService.lgnChk(map);
		Map<String, Object> resultMap = receiptService.getId(map);
		return resultMap;
	}

	@RequestMapping(value = "/receipt/getPw.do")
	@ResponseBody
	public String getPw(CommandMap commandMap) throws Exception {

		String email = (String) commandMap.getMap().get("email");
		String telNo = (String) commandMap.getMap().get("telNo");
		String telComDiv = (String) commandMap.getMap().get("telComDiv");
		String userNm = (String) commandMap.getMap().get("userNm");

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("telNo", telNo);
		map.put("telComDiv", telComDiv);
		map.put("userNm", userNm);
		// Integer resultInt = receiptService.lgnChk(map);
		String getPw = receiptService.getPw(map);
		System.out.println(getPw);
		getPw = first.common.util.Sha256.encryptString(getPw);
		System.out.println(getPw);
		return getPw;
	}

	@RequestMapping(value = "/receipt/compareUuId.do")
	@ResponseBody
	public Integer compareUuId(CommandMap commandMap) throws Exception {
		Integer resultInt = null;
		log.debug(commandMap.get("pushDuv"));
		log.debug(commandMap.get("curTelNo"));
		log.debug(commandMap.get("curUuid"));
		log.debug(commandMap.get("dbTelNo"));
		log.debug((String) commandMap.get("curTelNo") + (String) commandMap.get("curUuid"));
		String curUuId = Sha256.encryptString((String) commandMap.get("curTelNo") + (String) commandMap.get("curUuid"));
		String dbUuId = (String) commandMap.get("dbUuId");
		String curTelNo = (String) commandMap.get("curTelNo");
		String dbTelNo = (String) commandMap.get("dbTelNo");
		log.debug("curUuId:" + curUuId);
		log.debug("dbUuId:" + dbUuId);
		if (dbTelNo.equals(curTelNo)) {
			resultInt = 1;
		} else {
			resultInt = 0;
		}
		log.debug(resultInt);
		return resultInt;
	}

	/**
	 * 
	 * 신규 디자인 관련 소스-회원가입
	 * 
	 */

	@RequestMapping(value = "/receipt/appMemberInsert.do")
	@ResponseBody
	public int appMemberInsert(CommandMap commandMap, HttpSession session) throws Exception {
		log.debug("commandMap:" + commandMap);
		System.out.println("commandMap:" + commandMap);
		HashMap<String, Object> map = new HashMap<String, Object>();
		System.out.println("UUID ::: " + (String) commandMap.get("uuId"));
		map.put("userNm", commandMap.get("userNm"));
		map.put("eMail", commandMap.get("eMail"));
		map.put("birthDay", commandMap.get("birthDay"));
		map.put("sex", commandMap.get("sex"));
		map.put("nation", commandMap.get("nation"));
		map.put("tService", commandMap.get("tService"));
		map.put("telNo", commandMap.get("telNo"));
		map.put("pushKey", commandMap.get("pushKey"));
		map.put("uuId", Sha256.encryptString((String) commandMap.get("uuId")));
		map.put("pwd", Sha256.encryptString((String) commandMap.get("pwd")));
			
		Integer resultInt;
		try {
			receiptService.appMemberInsert(map);
			resultInt = 1;
		} catch (Exception e) {
			resultInt = 0;
			// TODO: handle exception
		}
		return resultInt;
	}

	@RequestMapping(value = "/receipt/telNumInit.do")
	@ResponseBody
	public void telNumInit(CommandMap commandMap, HttpSession session) throws Exception {
		log.debug("commandMap:" + commandMap);
		System.out.println("commandMap:" + commandMap);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("telNo", commandMap.get("telNo"));

		Integer resultInt;
		try {
			receiptService.telNumInit(map);
			resultInt = 1;
		} catch (Exception e) {
			resultInt = 0;
			// TODO: handle exception
		}
		return;
	}

	@RequestMapping(value = "/receipt/startCurData.do")
	@ResponseBody
	public Object startCurData(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String CI = (String) commandMap.get("CI");

		map.put("CI", CI);

		Map<String, Object> resultMap = receiptService.startCurData(map);
		resultMap.put("code", "OK");

		return resultMap;
	}

	@RequestMapping(value = "/receipt/startUserData.do")
	@ResponseBody
	public Object startUserData(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String CI = (String) commandMap.get("CI");

		map.put("CI", CI);
		Map<String, Object> resultMap = receiptService.startUserData(map);
		resultMap.put("code", "OK");

		return resultMap;
	}

	@RequestMapping(value = "/receipt/startSumData.do")
	@ResponseBody
	public Object startSumData(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();

		String CI = (String) commandMap.get("CI");

		map.put("CI", CI);
		map.put("startDate", commandMap.get("startDate"));
		map.put("endDate", commandMap.get("endDate"));

		Map<String, Object> resultMap = receiptService.startSumData(map);
		resultMap.put("code", "OK");
		resultMap.put("startDate", commandMap.get("startDate"));
		resultMap.put("endDate", commandMap.get("endDate"));

		return resultMap;
	}

	@RequestMapping(value = "/receipt/startRecYnData.do")
	@ResponseBody
	public Object startRecYnData(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String CI = (String) commandMap.get("CI");

		map.put("CI", CI);
		map.put("startDate", commandMap.get("startDate"));
		map.put("endDate", commandMap.get("endDate"));

		Map<String, Object> resultMap = receiptService.startRecYnData(map);
		resultMap.put("code", "OK");

		return resultMap;
	}

	@RequestMapping(value = "/receipt/smsLatestData.do")
	@ResponseBody
	public Object smsLatestData(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("telNo", commandMap.get("telNo"));
		System.out.println("dateSrchCd" + commandMap.get("dateSrchCd"));
		System.out.println("startDay" + commandMap.get("startDay"));
		System.out.println("endDay" + commandMap.get("endDay"));
		System.out.println("payDivCd" + commandMap.get("payDivCd"));
		System.out.println("orderByCd" + commandMap.get("orderByCd"));
		System.out.println("dateDivCd" + commandMap.get("dateDivCd"));
		if ("Y".equals(commandMap.get("dateSrchCd"))) {
			map.put("dateSrchCd", "SRCHY");
			map.put("startDay", commandMap.get("startDay"));
			map.put("endDay", commandMap.get("endDay"));
		}
		map.put("payDivCd", commandMap.get("payDivCd"));
		map.put("orderByCd", commandMap.get("orderByCd"));
		map.put("dateDivCd", commandMap.get("dateDivCd"));
		Map<String, Object> resultMap = receiptService.smsLatestData(map);
		resultMap.put("code", "OK");

		return resultMap;
	}

	@RequestMapping(value = "/receipt/depth01List.do")
	@ResponseBody
	public Object depth01List(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("CI", commandMap.get("CI"));
		map.put("telNo", commandMap.get("telNo"));
		map.put("startDate", commandMap.get("startDate"));
		map.put("endDate", commandMap.get("endDate"));
		map.put("startDateMonth", commandMap.get("startDateMonth"));
		map.put("endDateMonth", commandMap.get("endDateMonth"));
		String mobTab = (String) commandMap.get("mobTab");
		Map<String, Object> resultMap = receiptService.depth01List(map);
		if ("CARD".equals(mobTab)) {
			Map<String, Object> resultMapCard02List = receiptService.depth02CardList(map);
			resultMap.put("resultMapCard01List", resultMapCard02List);
		} else if ("DAY".equals(mobTab)) {
			Map<String, Object> resultMapDay02List = receiptService.depth02DayList(map);
			resultMap.put("resultMapCard01List", resultMapDay02List);
		} else if ("DIV".equals(mobTab)) {
			Map<String, Object> resultMapDiv02List = receiptService.depth02DivList(map);
			resultMap.put("resultMapCard01List", resultMapDiv02List);
		}
		System.out.println("map" + map);
		Map<String, Object> resultMapCard03List = receiptService.depth03CardList(map);
		resultMap.put("code", "OK");
		resultMap.put("resultMapCard02List", resultMapCard03List);

		return resultMap;
	}

	@RequestMapping(value = "/receipt/userMinDate.do")
	@ResponseBody
	public Object userMinDate(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("CI", commandMap.get("CI"));

		String minDate = receiptService.userMinDate(map);

		if (minDate == null) {
			Date date = new Date();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMM");
			String today = transFormat.format(date);

			minDate = today;
		}

		return minDate;
	}

	@RequestMapping(value = "/receipt/mobileList.do")
	@ResponseBody
	public Object mobileList(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		map.put("CI", commandMap.get("CI"));
		map.put("days", commandMap.get("days"));
		String listType = (String) commandMap.get("listType");

		System.out.println("map ::::" + map);

		Map<String, Object> list01Map = null;
		Map<String, Object> list02Map = null;
		Map<String, Object> list03Map = null;

		list01Map = receiptService.list01(map);
		System.out.println("::::::::::::");
		System.out.println(((List<Map<String, Object>>) list01Map.get("resultMap")).get(0).get("TOTAL_AM"));
		System.out.println("::::::::::::");
		if (((List<Map<String, Object>>) list01Map.get("resultMap")).get(0).get("TOTAL_AM") == null) {

			((List<Map<String, Object>>) list01Map.get("resultMap")).get(0).put("TOTAL_AM", "0");

		}
		System.out.println(((List<Map<String, Object>>) list01Map.get("resultMap")).get(0).get("TOTAL_AM"));

		if (listType.equals("day")) {
			list02Map = receiptService.dayList02(map);
			list03Map = receiptService.dayList03(map);
		} else if (listType.equals("card")) {
			list02Map = receiptService.cardList02(map);
			list03Map = receiptService.cardList03(map);
		} else if (listType.equals("div")) {
			list02Map = receiptService.divList02(map);
			list03Map = receiptService.divList03(map);
		}

		resultMap.put("list01Map", list01Map);
		resultMap.put("list02Map", list02Map);
		resultMap.put("list03Map", list03Map);

		return resultMap;
	}

	@RequestMapping(value = "/receipt/mobileDayList.do")
	@ResponseBody
	public Object mobileDayList(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		map.put("CI", commandMap.get("CI"));
		map.put("telNo", commandMap.get("telNo"));
		map.put("startDate", commandMap.get("startDate"));
		map.put("endDate", commandMap.get("endDate"));
		map.put("startDateMonth", commandMap.get("startDateMonth"));
		map.put("endDateMonth", commandMap.get("endDateMonth"));

		Map<String, Object> dayList01Map = receiptService.list01(map);

		Map<String, Object> dayList02Map = receiptService.dayList02(map);

		Map<String, Object> dayList03Map = receiptService.dayList03(map);

		System.out.println("=============================================");
		System.out.println("resultMap01  :::" + dayList01Map);
		System.out.println("=============================================");
		System.out.println("resultMap02  :::" + dayList02Map);
		System.out.println("=============================================");
		System.out.println("resultMap03  :::" + dayList03Map);

		resultMap.put("code", "OK");
		resultMap.put("dayList01Map", dayList01Map);
		resultMap.put("dayList02Map", dayList02Map);
		resultMap.put("dayList03Map", dayList03Map);

		return resultMap;
	}

	@RequestMapping(value = "/receipt/mobileCardList.do")
	@ResponseBody
	public Object mobileCardList(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		map.put("CI", commandMap.get("CI"));
		map.put("telNo", commandMap.get("telNo"));
		map.put("startDate", commandMap.get("startDate"));
		map.put("endDate", commandMap.get("endDate"));
		map.put("startDateMonth", commandMap.get("startDateMonth"));
		map.put("endDateMonth", commandMap.get("endDateMonth"));

		Map<String, Object> cardList02Map = receiptService.cardList02(map);

		Map<String, Object> cardList03Map = receiptService.cardList03(map);

		System.out.println("=============================================");
		System.out.println("resultMap02  :::" + cardList02Map);
		System.out.println("=============================================");
		System.out.println("resultMap03  :::" + cardList03Map);

		resultMap.put("code", "OK");
		resultMap.put("cardList02Map", cardList02Map);
		resultMap.put("cardList03Map", cardList03Map);

		return resultMap;
	}

	@RequestMapping(value = "/receipt/mobileDivList.do")
	@ResponseBody
	public Object mobileDivList(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		map.put("CI", commandMap.get("CI"));
		map.put("telNo", commandMap.get("telNo"));
		map.put("startDate", commandMap.get("startDate"));
		map.put("endDate", commandMap.get("endDate"));
		map.put("startDateMonth", commandMap.get("startDateMonth"));
		map.put("endDateMonth", commandMap.get("endDateMonth"));

		Map<String, Object> divList02Map = receiptService.divList02(map);

		Map<String, Object> divList03Map = receiptService.divList03(map);

		System.out.println("=============================================");
		System.out.println("resultMap02  :::" + divList02Map);
		System.out.println("=============================================");
		System.out.println("resultMap03  :::" + divList03Map);

		resultMap.put("code", "OK");
		resultMap.put("divList02Map", divList02Map);
		resultMap.put("divList03Map", divList03Map);

		System.out.println(resultMap);

		return resultMap;
	}

	@RequestMapping(value = "/receipt/cateChange.do")
	@ResponseBody
	public Object cateChange(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		map.put("CI", commandMap.get("CI"));
		map.put("appDiv", commandMap.get("appDiv"));
		map.put("appDivOri", commandMap.get("appDivOri"));
		map.put("appCo", commandMap.get("appCo"));
		map.put("smsNo", commandMap.get("smsNo"));

		System.out.println("map :::" + map);
		try {
			receiptService.cateChange(map);

			receiptService.cateChangeBack(map);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return commandMap.get("smsNo");
	}

	@RequestMapping(value = "/receipt/mobileDayList03.do")
	@ResponseBody
	public Object mobileDayList03(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("CI", commandMap.get("CI"));
		map.put("yearMon", commandMap.get("yearMon"));

		Map<String, Object> resultMap = receiptService.dayList03(map);

		System.out.println("=============================================");
		System.out.println("resultMap03  :::" + resultMap);
		System.out.println("=============================================");
		resultMap.put("code", "OK");
		resultMap.put("dayList03Map", resultMap);

		return resultMap;
	}

	@RequestMapping(value = "/receipt/userCreaDate.do")
	@ResponseBody
	public Object userCreaDate(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("CI", commandMap.get("CI"));

		String userCreaDate = receiptService.getCreaDate(map);

		return userCreaDate;
	}

	@RequestMapping(value = "/receipt/latestData.do")
	@ResponseBody
	public Object latestData(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String CI = (String) commandMap.get("CI");

		map.put("CI", CI);
		System.out.println("CI" + CI);
		System.out.println("dateSrchCd" + commandMap.get("dateSrchCd"));
		System.out.println("startDay" + commandMap.get("startDay"));
		System.out.println("endDay" + commandMap.get("endDay"));
		System.out.println("payDivCd" + commandMap.get("payDivCd"));
		System.out.println("orderByCd" + commandMap.get("orderByCd"));
		System.out.println("dateDivCd" + commandMap.get("dateDivCd"));
		System.out.println("smsDivCd" + commandMap.get("smsDivCd"));
		System.out.println("endSeq" + commandMap.get("endSeq"));
		System.out.println("transMission" + commandMap.get("transMission"));
		if ("Y".equals(commandMap.get("dateSrchCd"))) {
			// map.put("dateSrchCd", commandMap.get("dateSrchCd"));
			map.put("dateSrchCd", "SRCHY");
			map.put("startDay", commandMap.get("startDay"));
			map.put("endDay", commandMap.get("endDay"));
		}
		map.put("smsDivCd", commandMap.get("smsDivCd"));
		map.put("payDivCd", commandMap.get("payDivCd"));
		map.put("orderByCd", commandMap.get("orderByCd"));
		map.put("dateDivCd", commandMap.get("dateDivCd"));
		map.put("transMission", commandMap.get("transMission"));
		System.out.println("--------------");
		map.put("startSeq", 0);
		map.put("endSeq", Integer.parseInt((String) commandMap.get("endSeq")));
		System.out.println("map" + map);
		Map<String, Object> resultMap = receiptService.latestData(map);

		System.out.println();
		map.put("endSeq", 2147483647);
		Map maxMap = receiptService.latestData(map);
		List<Map<String, Object>> maxList = (ArrayList<Map<String, Object>>) maxMap.get("resultMap");
		int maxSize = maxList.size();

		int smsTotal = 0, smsCnt = 0, recCnt = 0;
		for (int i = 0; i < maxSize; i++) {
			Map<String, Object> temp = maxList.get(i);
			if (temp.get("SMS_DIV").equals("00")) {
				recCnt++;
			} else {
				smsCnt++;
				smsTotal += Integer.parseInt((String) temp.get("TOTAL_AM"));
			}
		}

		resultMap.put("recCnt", recCnt);
		resultMap.put("smsCnt", smsCnt);
		resultMap.put("smsTotal", smsTotal);

		resultMap.put("maxSize", maxSize);
		resultMap.put("code", "OK");
		System.out.println("프로그램이 끝났습니다@@@@@@@@@@@@@@@@@@@");
		return resultMap;
	}

	// -----------------------------------------------------------------------
	// 전자영수증 uplus 상세페이지
	// -----------------------------------------------------------------------

	@RequestMapping(value = "/receipt/latestDataDetail.do")
	@ResponseBody
	public Object latestDataDetail(CommandMap commandMap, HttpSession session, ServletRequest request)
			throws Exception {
		Map<String, Object> shopMap = null;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		int listSize = 0;
		String str;
		String CI = null;
		try {

			String type = (String) commandMap.get("type");
			if (type.equals("00")) {
				map.put("type", type);
				map.put("seq", (String) commandMap.get("seq"));
				map.put("barcode", (String) commandMap.get("barcode"));
				map.put("biz", (String) commandMap.get("biz"));

				shopMap = receiptService.getShopInfo(map);

				resultMap = receiptService.ReceiptDetail(map);
				resultMap.put("shopInfo", shopMap);

				String date = (String) shopMap.get("SALES_DATE");
				date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8) + " "
						+ date.substring(8, 10) + ":" + date.substring(10, 12) + ":" + date.substring(12, 14);
				resultMap.put("salesDate", date);

			} else {
				CI = (String) commandMap.get("CI");
				map.put("CI", CI);
				map.put("type", (String) commandMap.get("type"));
				map.put("seq", (String) commandMap.get("seq"));

				resultMap = receiptService.smsDetailData(map);

				System.out.println("sms result" + resultMap);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println("resssssss :: " + resultMap);

		return resultMap;
	}

	@RequestMapping(value = "/receipt/latestDetailData.do")
	@ResponseBody
	public Object latestDetailData(CommandMap commandMap, HttpSession session, ServletRequest request)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("telNo", commandMap.get("telNo"));
		map.put("salesBarcode", commandMap.get("salesBarcode"));
		map.put("salesType", commandMap.get("salesType"));

		System.out.println("#####################@@@@@@@###########" + commandMap.get("salesType"));

		Map<String, Object> resultMap = receiptService.latestDetailData(map);

		// String type = (String)receiptService.getSalesType(map);
		// resultMap.put("SALES_TYPE",
		// (String)receiptService.getSalesType(map));
		receiptService.latestUpdateData(map);
		resultMap.put("code", "OK");

		// System.out.println("#######################################"+type);
		return resultMap;
	}

	/**
	 * 
	 * KCB 휴대폰 본인인증 인증번호 요청
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/receipt/KcbCertified.do")
	public int KcbCertified(HttpServletRequest request, HttpServletResponse response, CommandMap commandMap)
			throws IOException, ParseException {

		String svcTxSeqno = (String) commandMap.get("svcTxSeqno");
		String name = (String) commandMap.get("name");
		String birthday = (String) commandMap.get("birthday");
		String sex = (String) commandMap.get("gender");
		String nation = (String) commandMap.get("nation");
		String telComCd = (String) commandMap.get("comCd");
		String mbphnNo = (String) commandMap.get("mbphnNo");
		String smsReSndYn = (String) commandMap.get("smsReSndYn");
		String rsv1 = (String) commandMap.get("rsv1");
		String rsv2 = (String) commandMap.get("rsv2");
		String rqstMsrCd = (String) commandMap.get("rqstMsrCd");
		String memId = (String) commandMap.get("memId");
		String serverIp = (String) commandMap.get("serverIp");
		String siteUrl = (String) commandMap.get("siteUrl");
		String siteDomain = (String) commandMap.get("siteDomain");
		String endPointUrl = (String) commandMap.get("endPointURL");
		String logPath = (String) commandMap.get("logPath");
		String options = (String) commandMap.get("option");
		String rqstCausCd = "10";

		log.debug("svcTxSeqno==" + (String) commandMap.get("svcTxSeqno"));
		log.debug("name==" + (String) commandMap.get("name"));
		log.debug("birthday==" + (String) commandMap.get("birthday"));
		log.debug("sex==" + (String) commandMap.get("gender"));
		log.debug("nation==" + (String) commandMap.get("nation"));
		log.debug("telComCd==" + (String) commandMap.get("comCd"));
		log.debug("mbphnNo==" + (String) commandMap.get("mbphnNo"));
		log.debug("smsReSndYn==" + (String) commandMap.get("smsReSndYn"));
		log.debug("rsv1==" + (String) commandMap.get("rsv1"));
		log.debug("rsv2==" + (String) commandMap.get("rsv2"));
		log.debug("rqstMsrCd==" + rqstCausCd);
		log.debug("serverIp==" + (String) commandMap.get("serverIp"));
		log.debug("smsRqstCausCd==" + (String) commandMap.get("rqstCausCd"));
		log.debug("memId==" + (String) commandMap.get("memId"));
		log.debug("smsSiteUrl==" + (String) commandMap.get("siteUrl"));
		log.debug("siteDomain==" + (String) commandMap.get("siteDomain"));
		log.debug("endPointUrl==" + (String) commandMap.get("endPointURL"));
		log.debug("logPath==" + (String) commandMap.get("logPath"));
		log.debug("options==" + (String) commandMap.get("option"));

		String[] cmd = new String[19];
		cmd[0] = svcTxSeqno;
		cmd[1] = name;
		cmd[2] = birthday;
		cmd[3] = sex;
		cmd[4] = nation;
		cmd[5] = telComCd;
		cmd[6] = mbphnNo;
		cmd[7] = smsReSndYn;
		cmd[8] = rsv1;
		cmd[9] = rsv2;
		cmd[10] = rqstMsrCd;
		cmd[11] = rqstCausCd;
		cmd[12] = memId;
		cmd[13] = serverIp;
		cmd[14] = siteUrl;
		cmd[15] = siteDomain;
		cmd[16] = endPointUrl;
		cmd[17] = logPath;
		cmd[18] = options;

		String retcode = null;
		List result = new ArrayList(); // 인증결과
		log.debug("결과값 리스트==" + result);
		int ret = -999; // 프로세스 리턴값
		try {
			kcb.jni.Okname okname = new kcb.jni.Okname();
			ret = okname.exec(cmd, result);

			if (ret == 0) {// 성공일 경우 변수를 결과에서 얻음
				retcode = (String) result.get(0);
			} else {
				java.text.DecimalFormat dcf = new java.text.DecimalFormat("000");
				log.debug("결과값 리스트222==" + result);

				if (ret <= 200) {
					retcode = "B" + dcf.format(ret);

				} else {
					retcode = "S" + dcf.format(ret);
				}
			}
			log.debug("retcode값확인==" + retcode);
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		// 리턴해줄결과값
		Integer resCode;
		if (retcode.equals("B000")) {
			resCode = 1;
		} else {
			resCode = 0;
		}
		log.debug("결과값확인==" + resCode);

		return resCode;
	}

	/**
	 * 
	 * KCB 휴대폰 본인인증 인증번호 검증
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/receipt/KcbVerification.do")
	public Object KcbVerification(HttpServletRequest request, HttpServletResponse response, CommandMap commandMap)
			throws IOException, ParseException {

		String encCi = "";

		String svcTxSeqno = (String) commandMap.get("svcTxSeqno");
		String mbphnNo = (String) commandMap.get("mbphnNo");
		String smsCertNo = (String) commandMap.get("smsCertNo");
		String memId = (String) commandMap.get("memId");
		String serverIp = (String) commandMap.get("serverIp");
		String endPointUrl = (String) commandMap.get("endPointURL");
		String logPath = (String) commandMap.get("logPath");
		String options = (String) commandMap.get("option");

		log.debug("svcTxSeqno==" + svcTxSeqno);
		log.debug("mbphnNo==" + mbphnNo);
		log.debug("smsCertNo==" + smsCertNo);
		log.debug("memId==" + memId);
		log.debug("smsServerIp==" + serverIp);
		log.debug("smsEndPointURL" + endPointUrl);
		log.debug("smsLogPath==" + logPath);
		log.debug("smsOption==   " + options);

		String[] cmd = new String[8];
		cmd[0] = svcTxSeqno;
		cmd[1] = mbphnNo;
		cmd[2] = smsCertNo;
		cmd[3] = memId;
		cmd[4] = serverIp;
		cmd[5] = endPointUrl;
		cmd[6] = logPath;
		cmd[7] = options;

		String retcode = null;
		List result = new ArrayList(); // 인증결과
		log.debug("결과값 리스트==" + result);
		int ret = -999; // 프로세스 리턴값
		Integer resCode = 0;
		Integer joinType = 0;
		String CI = null;
		try {
			kcb.jni.Okname okname = new kcb.jni.Okname();
			ret = okname.exec(cmd, result);

			if (ret == 0) {// 성공일 경우 변수를 결과에서 얻음
				retcode = (String) result.get(0);
				log.debug("결과값 결과코드	==" + (String) result.get(0));
				log.debug("결과값 결과코드설명	==" + (String) result.get(1));
				log.debug("결과값 회원사 id 	==" + (String) result.get(2));
				log.debug("결과값 거래번호 	==" + (String) result.get(3));
				log.debug("결과값 DI값		==" + (String) result.get(4));
				log.debug("결과값 CI값 		==" + (String) result.get(5));
			} else {
				java.text.DecimalFormat dcf = new java.text.DecimalFormat("000");
				retcode = (String) result.get(0);
				log.debug("결과값 리스트222==" + result);

				log.debug("dcf ::::" + dcf);
				log.debug("retcode ::::" + retcode);

				if (ret <= 200) {
					retcode = "B" + dcf.format(ret);

				} else {
					retcode = "S" + dcf.format(ret);
				}
				log.debug("retcode22 ::::" + retcode);
			}

			CI = (String) result.get(5);
			System.out.println("CI :: " + CI);
			HashMap<String, Object> map = new HashMap<String, Object>();

			encCi = URLEncoder.encode(CI, "UTF-8");

			System.out.println("원래 키 	: " + CI);
			System.out.println("인코딩 키 	: " + encCi);
			System.out.println("디코딩 키 	: " + URLDecoder.decode(encCi, "UTF-8"));

			map.put("CI", encCi);

			int joinChk = (Integer) receiptService.joinChk(map);

			if (retcode.equals("B000")) {
				resCode = 1;

				map.put("mbphnNo", mbphnNo);
				map.put("name", (String) commandMap.get("name"));
				map.put("birthday", (String) commandMap.get("birthday"));
				map.put("gender", (String) commandMap.get("gender"));
				map.put("nation", (String) commandMap.get("nation"));
				map.put("comCd", (String) commandMap.get("comCd"));
				map.put("uuId", (String) commandMap.get("uuId"));
				map.put("pushKey", (String) commandMap.get("pushKey"));
				if (joinChk < 1) {
					System.out.println("신규 가입자 입니다.");
					joinType = 0;
					receiptService.appMemberInsert(map);
					System.out.println("신규 회원 가입이 완료되었습니다.");

				} else if (joinChk >= 1) {
					System.out.println("기존 가입된 회원입니다. 로그인 및 데이터 동기화 실행");
					joinType = 1;
					receiptService.appMemberUpdate(map);
					System.out.println("로그인이 완료되었습니다.");
				}
			} else {
				resCode = 0;
			}

			log.debug("retcode값확인==" + retcode);
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		log.debug("결과값확인==" + resCode);

		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("resCode", resCode);
		resultMap.put("retcode", retcode);
		resultMap.put("joinType", joinType);
		resultMap.put("CI", encCi);
		System.out.println(resultMap.get("CI") + "인코딩된 CI");
		return resultMap;
	}

	/**
	 * 영수증 확인 / 달력 탭
	 * 
	 * @param commandMap
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/receipt/houseHold.do")
	@ResponseBody
	public Object houseHold(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		// String id = (String) session.getAttribute("id");
		String id = request.getParameter("id");
		String startDate = (String) commandMap.getMap().get("startDate");
		String endDate = (String) commandMap.getMap().get("endDate");
		String startCurDate = (String) commandMap.getMap().get("startCurDate");
		String endCurDate = (String) commandMap.getMap().get("endCurDate");
		String CI = (String) commandMap.getMap().get("CI");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("startCurDate", startCurDate);
		map.put("endCurDate", endCurDate);
		map.put("CI", CI);

		Map<String, Object> resultMap = receiptService.houseHold(map);
		resultMap.put("code", "OK");

		return resultMap;
	}

	@RequestMapping(value = "/receipt/houseHoldDetail.do")
	@ResponseBody
	public Object houseHoldDetail(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		// String id = (String) session.getAttribute("id");
		String startDate = (String) commandMap.getMap().get("startDate");
		String CI = (String) commandMap.getMap().get("CI");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", startDate);
		map.put("CI", CI);

		Map<String, Object> resultMap = receiptService.houseHoldDetail(map);
		resultMap.put("code", "OK");

		return resultMap;
	}

	@RequestMapping(value = "/receipt/monthChartData.do")
	@ResponseBody
	public Object monthChartData(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		// String id = (String) session.getAttribute("id");
		String telNo = (String) commandMap.getMap().get("telNo");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("telNo", telNo);

		Map<String, Object> resultMap = receiptService.monthChartData(map);
		resultMap.put("code", "OK");

		return resultMap;
	}

	@RequestMapping(value = "/receipt/dayDivChartData.do")
	@ResponseBody
	public Object dayDivChartData(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		// String id = (String) session.getAttribute("id");
		String telNo = (String) commandMap.getMap().get("telNo");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("telNo", telNo);

		Map<String, Object> resultMap = receiptService.dayDivChartData(map);
		resultMap.put("code", "OK");

		return resultMap;
	}

	@RequestMapping(value = "/receipt/instDivChartData.do")
	@ResponseBody
	public Object instDivChartData(CommandMap commandMap, HttpSession session, ServletRequest request)
			throws Exception {
		// String id = (String) session.getAttribute("id");
		String telNo = (String) commandMap.getMap().get("telNo");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("telNo", telNo);

		Map<String, Object> resultMap = receiptService.instDivChartData(map);
		resultMap.put("code", "OK");

		return resultMap;
	}

	@RequestMapping(value = "/receipt/dayOfMonthChartData.do")
	@ResponseBody
	public Object dayOfMonthChartData(CommandMap commandMap, HttpSession session, ServletRequest request)
			throws Exception {
		// String id = (String) session.getAttribute("id");
		String telNo = (String) commandMap.getMap().get("telNo");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("telNo", telNo);

		Map<String, Object> resultMap = receiptService.dayOfMonthChartData(map);
		resultMap.put("code", "OK");

		return resultMap;

	}

	@RequestMapping(value = "/receipt/testIp.do")
	@ResponseBody
	public void testIp(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		InetAddress Address = InetAddress.getLocalHost();
		String IP = Address.getHostAddress();
		log.debug("IP:" + IP);
		log.debug("ipChk():" + CommonUtils.ipChk());

	}

	@RequestMapping(value = "/receipt/updatePushYn.do")
	@ResponseBody
	public void updatePushYn(CommandMap commandMap) throws Exception {
		String pushYn = (String) commandMap.getMap().get("pushYn");
		String telNo = (String) commandMap.getMap().get("telNo");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pushYn", pushYn);
		map.put("telNo", telNo);
		receiptService.updatePushYn(map);
	}

	@RequestMapping(value = "/receipt/settingChkPw.do")
	@ResponseBody
	public void settingChkPw(CommandMap commandMap) throws Exception {
		String pwd = (String) commandMap.getMap().get("pwd");
		String telNo = (String) commandMap.getMap().get("telNo");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pwd", Sha256.encryptString(pwd));
		map.put("telNo", telNo);

		receiptService.settingChkPw(map);
	}

	@RequestMapping(value = "/receipt/eventBoardList.do")
	@ResponseBody
	public Object eventBoardList(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		// String id = (String) session.getAttribute("id");
		String boardDiv = (String) commandMap.getMap().get("boardDiv");
		String nowDay = (String) commandMap.getMap().get("nowDay");
		String procDivCd = (String) commandMap.getMap().get("procDivCd");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("boardDiv", boardDiv);
		map.put("nowDay", nowDay);
		map.put("procDivCd", procDivCd);

		Map<String, Object> resultMap = receiptService.eventBoardList(map);
		resultMap.put("code", "OK");

		return resultMap;
	}

	/*
	 * 전송받은 전자영수증 데이터 저장
	 */
	@RequestMapping(value = "/receipt/receiveUplusDtlData.do")
	@ResponseBody
	public JSONObject receiveUplusDtlData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*
		 * String str; String jsonResData = ""; StringBuffer paramData = new
		 * StringBuffer();
		 * 
		 * System.out.println("@@paramDataparamData@@:"+paramData);
		 * BufferedReader br = null;
		 * 
		 * //JSON데이터를 넣어 JSON Object 로 만들어 준다. JSONParser jsonParser = new
		 * JSONParser(); JSONObject jsonObject = null;
		 * 
		 * try { br = new BufferedReader(new
		 * InputStreamReader(request.getInputStream(), "UTF-8"));
		 * System.out.println("@@br@@:"+br);
		 * 
		 * } catch (UnsupportedEncodingException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); throw e; } catch (IOException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); throw e; }
		 * try { while ((str = br.readLine()) != null) { paramData.append(str);
		 * } } catch (IOException e) { e.printStackTrace(); throw e; }
		 * System.out.println("@@1111paramData22@@:"+paramData); Var var =
		 * JsonParser.object(new CharTokenizer(paramData.toString())); String
		 * erecNo = var.find("erecNo").toString(); String userKey =
		 * var.find("userKey").toString();
		 */
		HashMap<String, Object> map = new HashMap<String, Object>();
		String erecNo = "2016121410011";
		String userKey = "U20170228132038107125";
		map.put("erecNo", erecNo);
		map.put("userKey", userKey);

		Map<String, Object> resultMap = receiptService.receiveUplusDtlData(map);
		// MAP의 KEY값을 이용하여 VALUE값 가져오기
		HashMap<String, Object> masterMap = new HashMap<String, Object>();
		HashMap<String, Object> etcInfoMap = new HashMap<String, Object>();
		HashMap<String, Object> shopInfoMap = new HashMap<String, Object>();
		HashMap<String, Object> salesInfoMap = new HashMap<String, Object>();
		HashMap<String, Object> customerInfoMap = new HashMap<String, Object>();
		HashMap<String, Object> salesListMap = new HashMap<String, Object>();
		for (String mapkey : resultMap.keySet()) {
			// System.out.println("key:"+mapkey+",value:"+resultMap.get(mapkey));
			// masterMap.put(mapkey, resultMap.get(mapkey));
		}
		masterMap.put("UPLUS_USER_KEY", resultMap.get("UPLUS_USER_KEY"));
		masterMap.put("SALES_BARCODE", resultMap.get("SALES_BARCODE"));
		masterMap.put("TEL_NO", resultMap.get("TEL_NO"));

		etcInfoMap.put("ETC_MEMO", resultMap.get("ETC_MEMO"));
		etcInfoMap.put("ETC_EVENT", resultMap.get("ETC_EVENT"));

		shopInfoMap.put("SHOP_NAME", resultMap.get("SHOP_NAME"));
		shopInfoMap.put("SHOP_ADDR", resultMap.get("SHOP_ADDR"));
		shopInfoMap.put("SHOP_TEL_NUM", resultMap.get("SHOP_TEL_NUM"));
		shopInfoMap.put("SHOP_CASHIER", resultMap.get("SHOP_CASHIER"));
		shopInfoMap.put("SHOP_BIZNO", resultMap.get("SHOP_BIZNO"));
		shopInfoMap.put("SHOP_CEO", resultMap.get("SHOP_CEO"));

		salesInfoMap.put("SALES_TYPE", resultMap.get("SALES_TYPE"));
		salesInfoMap.put("SALES_DATE", resultMap.get("SALES_DATE"));
		salesInfoMap.put("SALES_CHG_AMT", resultMap.get("SALES_CHG_AMT"));
		salesInfoMap.put("SALES_RE_PRINT", resultMap.get("SALES_RE_PRINT"));
		salesInfoMap.put("SALES_PAID_AMT", resultMap.get("SALES_PAID_AMT"));
		salesInfoMap.put("SALES_SUM_DF_AMT", resultMap.get("SALES_SUM_DF_AMT"));
		salesInfoMap.put("SALES_DT_CNT", resultMap.get("SALES_DT_CNT"));
		salesInfoMap.put("SALES_SUM_OP_AMT", resultMap.get("SALES_SUM_OP_AMT"));
		salesInfoMap.put("SALES_SUM_TAX_AMT", resultMap.get("SALES_SUM_TAX_AMT"));
		salesInfoMap.put("SALES_PRINT_DATE", resultMap.get("SALES_PRINT_DATE"));
		salesInfoMap.put("SALES_SUM_FP_AMT", resultMap.get("SALES_SUM_FP_AMT"));
		salesInfoMap.put("SALES_SUM_ALL_AMT", resultMap.get("SALES_SUM_ALL_AMT"));

		customerInfoMap.put("POINT_AMT", resultMap.get("POINT_AMT"));

		List<Map<String, Object>> resultMapDtl = receiptService.receiveUplusDtlArrayData(map);
		masterMap.put("etcInfo", etcInfoMap);
		masterMap.put("shopInfo", shopInfoMap);
		masterMap.put("salesInfo", salesInfoMap);
		masterMap.put("customerInfo", customerInfoMap);
		masterMap.put("salesList", resultMapDtl);
		log.debug("list.size:" + resultMapDtl.size());
		for (int i = 0; i < resultMapDtl.size(); i++) {
			log.debug(resultMapDtl.get(i));
			log.debug(resultMapDtl.get(i).get("SALES_SEQ_NO"));
			log.debug(resultMapDtl.get(i).get("SALES_PNAME"));
			log.debug(resultMapDtl.get(i).get("SALES_PPRICE"));
			log.debug(resultMapDtl.get(i).get("SALES_OPRICE"));
			log.debug(resultMapDtl.get(i).get("SALES_QTY"));
			log.debug(resultMapDtl.get(i).get("SALES_DF_AMT"));
			log.debug(resultMapDtl.get(i).get("SALES_FP_AMT"));
			log.debug(resultMapDtl.get(i).get("SALES_TAX_AMT"));
			log.debug(resultMapDtl.get(i).get("SALES_SL_AMT"));
			log.debug(resultMapDtl.get(i).get("SALES_OP_AMT"));
		}

		Gson gson = new Gson();
		String json = gson.toJson(masterMap);
		log.debug(json);
		JSONObject jsonObject2 = null;
		JSONParser jsonParser2 = new JSONParser();
		System.out.println("resStrRESULT:" + json);
		jsonObject2 = (JSONObject) jsonParser2.parse(json);

		return jsonObject2;
	}

	@RequestMapping(value = "/receipt/eMailChk.do")
	@ResponseBody
	public String eMailChk(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		String eMailChk = receiptService.eMailChk("01077003578");
		System.out.println("eMailChk:" + eMailChk);
		return eMailChk;
	}

	@RequestMapping(value = "/receipt/searchAff.do")
	@ResponseBody
	public Object searchAff(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		System.out.println("test");
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("dong", (String) commandMap.getMap().get("dong"));

		return resultMap;

	}

	@RequestMapping(value = "/receipt/affliateData.do")
	@ResponseBody
	public Object affliateData(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		System.out.println("affliate.do");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String dong = (String) commandMap.get("dong");
		log.debug("동 : " + dong);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dong", dong.replace(" ", ""));

		resultMap = receiptService.affliateData(map);

		double realLat = Double.valueOf((String) commandMap.get("lat"));
		double realLng = Double.valueOf((String) commandMap.get("lng"));
		double attLat = 0;
		double attLng = 0;

		// System.out.println("경위도 : "+realLat+"/"+realLng);

		List<Map<String, Object>> list = (List<Map<String, Object>>) resultMap.get("resultMap");
		int size = list.size();
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

		System.out.println("list@@@  " + list);

		for (int i = 0; i < size; i++) {
			Map<String, Object> tempMap = new HashMap<>();

			tempMap = list.get(i);
			attLat = Double.valueOf((String) tempMap.get("AFF_DETAIL_LAT"));
			attLng = Double.valueOf((String) tempMap.get("AFF_DETAIL_LNG"));

			String dist = distance(realLat, realLng, attLat, attLng);
			tempMap.put("distance", dist);

			resultList.add(tempMap);
		}

		for (int i = 0; i < size; i++) {
			for (int j = i; j < size; j++) {
				Map<String, Object> map1 = new HashMap<>();
				Map<String, Object> map2 = new HashMap<>();
				Map<String, Object> temp = new HashMap<>();
				map1 = resultList.get(i);
				map2 = resultList.get(j);
				if (Integer.valueOf((String) map1.get("distance")) > Integer.valueOf((String) map2.get("distance"))) {
					temp = map1;
					map1 = map2;
					map2 = temp;

					resultList.set(i, map1);
					resultList.set(j, map2);
				}
			}
		}
		for (int i = 0; i < resultList.size(); i++) {
			Map<String, Object> temp = new HashMap<String, Object>();

			temp = resultList.get(i);

			String distance = (String) temp.get("distance");

			if (distance.length() < 4) {
				distance = distance + "m";
			} else if (distance.length() == 4) {
				String meter = distance.substring(0, distance.length() - 2);
				meter = meter.substring(meter.length() - 1, meter.length());
				distance = distance.substring(0, distance.length() - 3);
				distance = distance + "." + meter + "km";
			} else {
				distance = distance.substring(0, distance.length() - 3) + "km";
			}

			temp.put("distance", distance);
			resultList.set(i, temp);
		}

		System.out.println("resultList : " + resultList);
		System.out.println("list size : " + size);
		return resultList;
	}

	// --------------------------------------------------------------
	// 가맹점 디테일 정보 가져오기
	// --------------------------------------------------------------
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/receipt/affDetail.do")
	@ResponseBody
	public Object affDetail(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		String affliateNo = (String) commandMap.getMap().get("affliateNo");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("affliateNo", affliateNo);

		Map<String, Object> resultMap = receiptService.affDetail(map);

		System.out.println("resultmap@@@   " + resultMap);

		JSONObject json = new JSONObject();

		json.put("lat", (String) resultMap.get("AFF_DETAIL_LAT"));
		json.put("lng", (String) resultMap.get("AFF_DETAIL_LNG"));

		json.put("affAddr", (String) resultMap.get("JIBUN_ADDR"));
		json.put("telNo", (String) resultMap.get("SHOP_TEL_NO"));
		json.put("affName", (String) resultMap.get("AFFLIATE_NM"));
		json.put("affExp", (String) resultMap.get("AFF_DETAIL_EXPLAIN"));

		String time = (String) resultMap.get("AFF_DETAIL_TIME");
		String timeType = null;
		System.out.println("ttttttttttttttttttttime  --  " + time);
		if (!time.isEmpty()) {
			System.out.println("time 접근   ");
			if (time.matches(".*&.*")) {
				timeType = "week";
				String weekday = time.substring(3, 5) + ":" + time.substring(5, 7) + " ~ " + time.substring(8, 10) + ":"
						+ time.substring(10, 12);
				String weekend = time.substring(16, 18) + ":" + time.substring(18, 20) + " ~ " + time.substring(21, 23)
						+ ":" + time.substring(23, 25);

				json.put("weekday", weekday);
				json.put("weekend", weekend);
			} else {
				timeType = "all";
				String dayTime = time.substring(3, 5) + ":" + time.substring(5, 7) + " ~ " + time.substring(8, 10) + ":"
						+ time.substring(10, 12);
				json.put("dayTime", dayTime);
			}
			json.put("timeType", timeType);
		}

		String menu = (String) resultMap.get("AFF_DETAIL_MENU");
		String[] menuList = menu.split("/");
		int menuSize = menuList.length;

		List<Object> menuName = new ArrayList<>();
		List<Object> menuPrice = new ArrayList<>();

		for (int i = 0; i < menuSize; i++) {
			menuName.add(i, ((String) menuList[i]).substring(0, ((String) menuList[i]).indexOf("#")));
			int Price = Integer.parseInt(((String) menuList[i]).substring(((String) menuList[i]).indexOf("#") + 1));
			menuPrice.add(i, CommonUtils.replaceComma(Price));
		}
		json.put("menuSize", menuSize);
		json.put("menuName", menuName);
		json.put("menuPrice", menuPrice);

		String image = (String) resultMap.get("AFF_DETAIL_IMAGE");
		String[] imageList = image.split("/");
		int imageSize = imageList.length;
		List<Object> imageArray = new ArrayList<>();
		for (int i = 0; i < imageSize; i++) {
			imageArray.add(i, imageList[i]);
		}
		json.put("imageSize", imageSize);
		json.put("image", imageArray);

		System.out.println(json);
		return json;
	}

	// --------------------------------------------------------------
	// 가맹점 MAP
	// --------------------------------------------------------------
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/receipt/affMapView.do")
	@ResponseBody
	public Object affMapView(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		String affliateNo = (String) commandMap.getMap().get("affliateNo");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("affliateNo", affliateNo);

		Map<String, Object> resultMap = receiptService.affDetail(map);

		System.out.println("resultmap@@@   " + resultMap);

		JSONObject json = new JSONObject();

		json.put("lat", (String) resultMap.get("AFF_DETAIL_LAT"));
		json.put("lng", (String) resultMap.get("AFF_DETAIL_LNG"));

		json.put("affAddr", (String) resultMap.get("JIBUN_ADDR"));
		json.put("telNo", (String) resultMap.get("SHOP_TEL_NO"));
		json.put("affName", (String) resultMap.get("AFFLIATE_NM"));
		json.put("affExp", (String) resultMap.get("AFF_DETAIL_EXPLAIN"));

		System.out.println(json);
		return json;
	}

	private static String distance(double realLat, double realLng, double attLat, double attLng) { // 두
																									// 좌표
																									// 거리구하기

		double theta = realLng - attLng;
		double dist = Math.sin(deg2rad(realLat)) * Math.sin(deg2rad(attLat))
				+ Math.cos(deg2rad(realLat)) * Math.cos(deg2rad(attLat)) * Math.cos(deg2rad(theta));

		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;

		dist = dist * 1609.344;

		String distance = String.valueOf(dist);

		distance = distance.substring(0, distance.indexOf("."));

		/*
		 * if(distance.length() < 4){ distance = distance + "m"; }else
		 * if(distance.length()==4){ String meter =
		 * distance.substring(0,distance.length()-2); meter =
		 * meter.substring(meter.length()-1, meter.length()); distance =
		 * distance.substring(0, distance.length()-3); distance = distance + "."
		 * + meter +"km"; }else{ distance = distance.substring(0,
		 * distance.length()-3) + "km"; } System.out.println(distance);
		 */
		return (distance);

	}

	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

	@RequestMapping(value = "/receipt/couponList.do")
	@ResponseBody
	public Object couponList(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		String nowDay = (String) commandMap.getMap().get("nowDay");
		String procDivCd = (String) commandMap.getMap().get("procDivCd");
		String webType = (String) commandMap.getMap().get("webType");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("nowDay", nowDay);
		map.put("procDivCd", procDivCd);

		System.out.println("TTTTTTTTTTTTTTTTTTTT :::: " + webType);

		Map<String, Object> resultMap = receiptService.couponList(map);
		resultMap.put("code", "OK");

		return resultMap;
	}

	@RequestMapping(value = "/receipt/couponRead.do")
	@ResponseBody
	public Object couponRead(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String seq = (String) commandMap.getMap().get("seq");
		String CI = (String) commandMap.getMap().get("CI");
		map.put("seq", seq);
		map.put("CI", CI);

		receiptService.coucntAdd(map);

		Map<String, Object> couponDownMap = receiptService.couponDown(map);

		long downCnt = ((long) couponDownMap.get("CNT"));
		int totalCnt = Integer.parseInt((String) couponDownMap.get("COUPON_TOTAL"));

		Map<String, Object> resultMap = receiptService.couponRead(map);
		resultMap.put("code", "OK");

		int valueInt = receiptService.valiCoupon(map);

		resultMap.put("valueInt", valueInt);
		if (totalCnt <= downCnt) {
			resultMap.put("downCnt", true);
		}

		return resultMap;
	}

	@RequestMapping(value = "/receipt/couponAdd.do")
	@ResponseBody
	public Object couponAdd(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		String nowDay = (String) commandMap.getMap().get("nowDay");
		String seq = (String) commandMap.getMap().get("seq");
		String CI = (String) commandMap.getMap().get("CI");
		String uuId = (String) commandMap.getMap().get("uuId");

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("seq", seq);
		map.put("nowDay", nowDay);
		map.put("CI", CI);
		map.put("uuId", uuId);

		Map<String, Object> affReadMap = receiptService.couponRead(map);

		int valueInt = receiptService.valiCoupon(map);

		if (valueInt == 0) {
			Map<String, Object> couponDownMap = receiptService.couponDown(map);

			long downCnt = ((long) couponDownMap.get("CNT"));
			int totalCnt = Integer.parseInt((String) couponDownMap.get("COUPON_TOTAL"));

			if (totalCnt <= downCnt) {
				valueInt = 3;
			} else {
				map.put("couponTitle", affReadMap.get("COUPON_TITLE"));
				map.put("affliateNo", affReadMap.get("AFFLIATE_NO"));
				map.put("couponType", affReadMap.get("COUPON_TYPE"));
				map.put("couponVal", affReadMap.get("COUPON_VAL"));
				map.put("couponOut", affReadMap.get("COUPON_EXP"));

				receiptService.couponAdd(map);
			}
		}

		return valueInt;
	}

	@RequestMapping(value = "/receipt/couponMyList.do")
	@ResponseBody
	public Object couponMyList(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		String CI = (String) commandMap.getMap().get("CI");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("CI", CI);

		Map<String, Object> resultMap = receiptService.couponMyList(map);

		return resultMap;
	}

	@RequestMapping(value = "/receipt/couponDetail.do")
	@ResponseBody
	public Object couponDetail(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		String telNo = (String) commandMap.getMap().get("telNo");
		String seq = (String) commandMap.getMap().get("seq");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("telNo", telNo);
		map.put("seq", seq);

		Map<String, Object> resultMap = receiptService.couponDetail(map);

		return resultMap;
	}

	@RequestMapping(value = "/receipt/couponUsing.do")
	@ResponseBody
	public Object couponUsing(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		String telNo = (String) commandMap.getMap().get("telNo");
		String CI = (String) commandMap.getMap().get("CI");
		String seq = (String) commandMap.getMap().get("seq");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("telNo", telNo);
		map.put("CI", CI);
		map.put("seq", seq);

		System.out.println("##############" + map);
		receiptService.couponUsing(map);

		return 1;
	}

	// uplus 전자영수증
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	/*
	 * 유플러스 가입
	 */
	@RequestMapping(value = "/receipt/receiveUplusJoin.do")
	@ResponseBody
	// public ModelAndView insertReceiptData(CommandMap commandMap,
	// HttpServletRequest request) throws Exception{
	public String receiveUplusJoin(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String str;
		String jsonResData = "";
		StringBuffer paramData = new StringBuffer();

		AES256 aes = null;

		if (CommonUtils.ipChk()) {
			aes = new AES256("LGU+DEV258010247");
		} else {
			aes = new AES256("LGU+210987654321");
		}
		System.out.println("ipChk" + CommonUtils.ipChk());

		System.out.println("@@paramDataparamData@@:" + paramData);
		BufferedReader br = null;

		// JSON데이터를 넣어 JSON Object 로 만들어 준다.
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = null;

		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			System.out.println("@@br@@:" + br);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		}
		String resStr = "";
		String transactionId = "";
		try {

			System.out.println("@@1111paramData22@@:" + paramData);

			HashMap<String, Object> map = new HashMap<String, Object>();

			String value = paramData.toString();
			String dec = "";
			System.out.println("더리얼 테스트:: " + value);
			/*
			 * if(!var.find("userNm").toString().equals("real")){
			 * System.out.println("Test page - real   "); String enc =
			 * aes.encryptStringToBase64(var.toString());
			 * 
			 * dec = aes.decryptBase64String(enc);
			 * 
			 * System.out.println("Test page - real   " + dec); }else{
			 */

			System.out.print("dec test @@@");
			dec = aes.decryptBase64String(value);
			// dec =
			// aes.decryptBase64String("Bgrr6Z+i6rUclIALVyNfw0pxjRKoNMjxJOnE3DhXFAUk/sccrOADR6Z9hX1UXnfxZ68xLMVGYWh4YduuNN+S/SvC0VzxzBNagMNmACZlXpZLjiVueSaqKZhCfIGvoBJlS4i6k+JN4bDOJngIshf3Rg/q+pOmmXr/HPjcr+J2uidDolyfqmm1z2GrmUmOGRan");
			System.out.println(dec);
			// }

			Var var2 = JsonParser.object(new CharTokenizer(dec));
			String telNo = var2.find("telNo").toString();
			// String userNm = URLDecoder.decode(var2.find("userNm").toString()
			// , "UTF-8");
			String userBirth = var2.find("userBirth").toString().substring(0, 5);
			String sexDivCd = var2.find("userBirth").toString().substring(5);
			// String localDivCd = var2.find("localDivCd").toString();
			transactionId = var2.find("transactionId").toString();
			String userState = "01";

			System.out.println(telNo + "//" + userBirth);

			map.put("telNo", telNo);
			map.put("userNm", "");
			map.put("userBirth", userBirth);
			map.put("sexDivCd", sexDivCd);
			map.put("localDivCd", "");
			map.put("userState", userState);
			Integer upluJoinChk = receiptService.selectUplusJoinChk(map);
			Integer usedTelChk = receiptService.selectUsedTelChk(map);

			System.out.println("upluJoinChk:" + upluJoinChk);
			String randomSetValue = TheRealShopToUplus.randomSetValue();
			map.put("uplusUserKey", randomSetValue);

			// 1:더리얼 기 가입자 0:신규가입자
			if (upluJoinChk > 0) {
				// 기존 사용하던 번호로 가입시 기존 데이터 백업
				/*
				 * if(usedTelChk>0){ System.out.println(
				 * "!!기존 사용하는 번호는 id로 변환합니다!!"); String email =
				 * receiptService.selectEmail(map); String id =
				 * email.substring(0, email.indexOf("@")+1); System.out.println(
				 * "@@ email : " + email +"\n @@ id    : " + id); map.put("id",
				 * id);
				 * 
				 * receiptService.updateUsedData(map);
				 * 
				 * System.out.println("!!기존 "+telNo+" 데이터는 "+ id +" 로 변경되었습니다!!"
				 * ); }else{ System.out.println(
				 * "!!더리얼샵 기존 가입자입니다. U+ userKey 입력 Process입니다!!");
				 * 
				 * 
				 * }
				 */

				receiptService.receiveUplusJoin(map);

				// 업데이트 컬럼
				// 성공시
				resStr += "{";
				resStr += "	\"result\": \"UJ000\",";
				resStr += "	\"message\": \"회원가입이 완료되었습니다.(더리얼 가입 회원) U+ 키 생성 완료. \",";
				resStr += "	\"userKey\": \"" + randomSetValue + "\"";
				resStr += "	\"transactionId\": \"" + transactionId + "\"";
				resStr += "}";
				System.out.println("!!Uplus 회원가입이 완료되었습니다!!\n\n");
				System.out.println("resStr:" + resStr);

			} else {
				// 기존 사용하던 번호로 가입시 기존 데이터 삭제
				if (usedTelChk > 0) {
					// 기존 번호의 유플러스 유저키 가져오기

					System.out.println("!!Uplus user key 백업 시작!!");
					map.put("originUplusUserKey", (String) receiptService.originUplusUserKey(map));
					map.put("userState", "05");
					receiptService.updateUplusData(map);

					System.out.println("!!Uplus user key 백업 완료!!");
				}

				receiptService.receiveUplusJoin(map);
				System.out.println("!!Uplus 회원가입이 완료되었습니다!!\n\n");

				resStr += "{";
				resStr += "	\"result\": \"UJ001\",";
				resStr += "	\"message\": \"회원 가입이 완료되었습니다.(더리얼 미 가입 회원)\",";
				resStr += "	\"userKey\": \"" + randomSetValue + "\",";
				resStr += "	\"transactionId\": \"" + transactionId + "\"";
				resStr += "}";
				System.out.println("resStr:" + resStr);

			}

		} catch (Exception e) {
			resStr += "{";
			resStr += "	\"result\": \"UJ002\",";
			resStr += "	\"message\": \"가입에 실패하였습니다.\",";
			resStr += "	\"transactionId\": \"" + transactionId + "\"";
			resStr += "}";
			System.out.println("resStr:" + resStr + "\n" + e);
		}
		JSONObject jsonObject2 = null;
		JSONParser jsonParser2 = new JSONParser();
		System.out.println("resStrRESULT:" + resStr);
		jsonObject2 = (JSONObject) jsonParser2.parse(resStr);
		String enc = aes.encryptStringToBase64(jsonObject2.toString());
		System.out.println("enc = " + enc);
		return enc;

	}

	/*
	 * 유플러스 탈퇴
	 */
	@RequestMapping(value = "/receipt/receiveUplusDrop.do")
	@ResponseBody
	// public ModelAndView insertReceiptData(CommandMap commandMap,
	// HttpServletRequest request) throws Exception{
	public String receiveUplusDrop(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String str;
		StringBuffer paramData = new StringBuffer();

		System.out.println("@@paramDataparamData@@:" + paramData);
		BufferedReader br = null;

		// JSON데이터를 넣어 JSON Object 로 만들어 준다.

		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			System.out.println("@@br@@:" + br);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		}

		String transactionId = "";
		String resStr = "";
		try {
			System.out.println("@@1111paramData22@@: " + paramData);
			HashMap<String, Object> map = new HashMap<String, Object>();

			AES256 aes = null;

			if (CommonUtils.ipChk()) {
				aes = new AES256("LGU+DEV258010247");
			} else {
				aes = new AES256("LGU+210987654321");
			}

			String value = paramData.toString();
			String dec = "";

			/*
			 * if(!var.find("userNm").toString().equals("real")){
			 * System.out.println("Test page - real   "); String enc =
			 * aes.encryptStringToBase64(var.toString());
			 * 
			 * dec = aes.decryptBase64String(enc);
			 * 
			 * System.out.println("Test page - real   " + dec); }else{
			 */
			System.out.print("dec test @@@");
			dec = aes.decryptBase64String(value);
			// dec =
			// aes.decryptBase64String("Bgrr6Z+i6rUclIALVyNfw0pxjRKoNMjxJOnE3DhXFAUk/sccrOADR6Z9hX1UXnfxZ68xLMVGYWh4YduuNN+S/SvC0VzxzBNagMNmACZlXpZLjiVueSaqKZhCfIGvoBJlS4i6k+JN4bDOJngIshf3Rg/q+pOmmXr/HPjcr+J2uidDolyfqmm1z2GrmUmOGRan");
			System.out.println(dec);
			// }

			Var var2 = JsonParser.object(new CharTokenizer(dec));

			String telNo = var2.find("telNo").toString();
			String userKey = var2.find("userKey").toString();
			// String userKey = "U20171112164037899390";
			transactionId = var2.find("transactionId").toString();
			String userState = "02";

			map.put("telNo", telNo);
			map.put("originUplusUserKey", userKey);
			map.put("userState", userState);
			// Integer upluJoinChk = receiptService.selectUplusJoinChk(map);
			// Integer usedTelChk = receiptService.selectUsedTelChk(map);

			int resDrop = receiptService.updateUplusDrop(map);
			System.out.println(resDrop + " : 처리결과");
			if (resDrop > 0) {
				resStr += "{";
				resStr += "	\"result\": \"UJ100\",";
				resStr += "	\"message\": \"탈퇴처리가 정상적으로 되었습니다.\",";
				resStr += "	\"transactionId\": \"" + transactionId + "\"";
				resStr += "}";
			} else {
				resStr += "{";
				resStr += "	\"result\": \"UJ101\",";
				resStr += "	\"message\": \"탈퇴 실패 - 일치하는 사용자가 없습니다.\",";
				resStr += "	\"transactionId\": \"" + transactionId + "\"";
				resStr += "}";
			}

		} catch (Exception e) {
			resStr += "{";
			resStr += "	\"result\": \"UJ102\",";
			resStr += "	\"message\": \"탈퇴처리가 실패하였습니다.\",";
			resStr += "	\"transactionId\": \"" + transactionId + "\"";
			resStr += "}";

			System.out.println("error - " + e);
		}

		JSONObject jsonObject2 = null;
		JSONParser jsonParser2 = new JSONParser();

		System.out.println("resStrRESULT:" + resStr);
		jsonObject2 = (JSONObject) jsonParser2.parse(resStr);

		AES256 aes = null;

		if (CommonUtils.ipChk()) {
			aes = new AES256("LGU+DEV258010247");
		} else {
			aes = new AES256("LGU+210987654321");
		}

		String enc = aes.encryptStringToBase64(jsonObject2.toString());

		System.out.println("enc = " + enc);
		return enc;

	}

	// -----------------------------------------------------------------------
	// 전자영수증 메인페이지
	// -----------------------------------------------------------------------
	@RequestMapping(value = "/receipt/uplusReceipt.do")
	@ResponseBody
	public Object uplusRecipt(CommandMap commandMap, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> resultMap = null;
		Map<String, Object> maxMap = null;
		int listSize = 0;
		String str;
		StringBuffer paramData = new StringBuffer();

		ModelAndView mv = new ModelAndView();

		AES256 aes = null;

		if (CommonUtils.ipChk()) {
			aes = new AES256("LGU+DEV258010247");
		} else {
			aes = new AES256("LGU+210987654321");
		}
		System.out.println("ipChk" + CommonUtils.ipChk());

		System.out.println("@@paramDataparamData@@:" + paramData);
		BufferedReader br = null;

		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			System.out.println("@@br@@:" + br);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		try {
			while ((str = br.readLine()) != null) {
				System.out.println(str);
				paramData.append(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		try {

			System.out.println("@@paramData@@:" + paramData);

			HashMap<String, Object> map = new HashMap<String, Object>();

			String telNo = (String) commandMap.get("telNo");
			String userKey = (String) commandMap.get("userKey");
			if (telNo.contains(" ")) {
				telNo = telNo.replaceAll(" ", "+");
			}
			if (userKey.contains(" ")) {
				userKey = userKey.replaceAll(" ", "+");
			}
			System.out.println("parameter data - " + telNo + "      /         " + userKey);

			if (telNo.length() > 11) {
				telNo = aes.decryptBase64String(telNo);
			}
			if (userKey.length() > 21) {
				userKey = aes.decryptBase64String(userKey);
			}

			System.out.println("디코딩 결과 - " + telNo + "      /         " + userKey);

			map.put("smsDivCd", "ONLYREC");
			map.put("startSeq", 0);
			map.put("endSeq", 10);

			map.put("telNo", telNo);
			map.put("userKey", userKey);

			resultMap = receiptService.uplusReceiptData(map);
			List resultList = (List) resultMap.get("resultMap");

			listSize = resultList.size();

			System.out.println("size" + listSize);

			map.put("endSeq", 2147483647);
			maxMap = receiptService.uplusReceiptData(map);
			int maxSize = ((List) maxMap.get("resultMap")).size();

			mv.addObject("maxSize", maxSize);
			mv.addObject("telNo", telNo);
			mv.addObject("userKey", userKey);

		} catch (Exception e) {
			System.out.println(e);
		}
		mv.addObject("receiptCnt", listSize);
		mv.addObject("resultMap", resultMap);

		mv.setViewName("/uplusReceipt");

		return mv;
	}

	// -----------------------------------------------------------------------
	// 전자영수증 메인페이지
	// -----------------------------------------------------------------------

	@RequestMapping(value = "/receipt/uplusReceiptReload.do")
	@ResponseBody
	public Object uplusReciptReload(CommandMap commandMap, HttpSession session, ServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		int listSize = 0;
		String str;

		AES256 aes = null;

		if (CommonUtils.ipChk()) {
			aes = new AES256("LGU+DEV258010247");
		} else {
			aes = new AES256("LGU+210987654321");
		}

		JSONObject json = null;
		try {

			String receiptCnt = (String) commandMap.get("receiptCnt");
			String telNo = (String) commandMap.get("telNo");
			String userKey = (String) commandMap.get("userKey");
			// String decTelNo = aes.decryptBase64String(telNo);
			// String decUserKey = aes.decryptBase64String(userKey);
			String smsDivCd = (String) commandMap.get("smsDivCd");
			String startDate = (String) commandMap.get("startDate");
			String endDate = (String) commandMap.get("endDate");
			String payDivCd = (String) commandMap.get("payDivCd");
			String orderByCd = (String) commandMap.get("orderByCd");
			String dateDivCd = (String) commandMap.get("dateDivCd");
			String dateSrchCd = (String) commandMap.get("dateSrchCd");
			if (dateSrchCd.equals("SRCHY")) {
				map.put("dateSrchCd", "SRCHY");
			}
			map.put("smsDivCd", "ONLYREC");
			map.put("startSeq", 0);
			map.put("endSeq", Integer.parseInt((String) commandMap.get("receiptCnt")));
			map.put("startDay", request.getParameter("startDate"));
			map.put("endDay", request.getParameter("endDate"));
			map.put("payDivCd", commandMap.get("payDivCd"));
			map.put("orderByCd", commandMap.get("orderByCd"));
			map.put("startDate", commandMap.get("payDivCd"));
			map.put("dateDivCd", commandMap.get("dateDivCd"));
			if (telNo.contains(" ")) {
				System.out.println("수정");
				telNo = telNo.replaceAll(" ", "+");
			}
			if (userKey.contains(" ")) {
				userKey = userKey.replaceAll(" ", "+");
			}
			if (telNo.length() > 11) {
				telNo = aes.decryptBase64String(telNo);
			}
			if (userKey.length() > 21) {
				userKey = aes.decryptBase64String(userKey);
			}
			map.put("telNo", telNo);
			map.put("userKey", userKey);

			resultMap = receiptService.uplusReceiptData(map);
			List resultList = (List) resultMap.get("resultMap");

			listSize = resultList.size();

		} catch (Exception e) {
			System.out.println(e);
		}

		return resultMap;
	}

	// -----------------------------------------------------------------------
	// 전자영수증 uplus 상세페이지
	// -----------------------------------------------------------------------

	@RequestMapping(value = "/receipt/uplusReceiptDetail.do")
	@ResponseBody
	public Object uplusReceiptDetail(CommandMap commandMap, HttpSession session, ServletRequest request)
			throws Exception {
		Map<String, Object> shopMap = null;
		Map<String, Object> resultMap = null;
		ModelAndView mv = new ModelAndView();
		HashMap<String, Object> map = new HashMap<String, Object>();
		int listSize = 0;
		String str;
		AES256 aes = null;

		if (CommonUtils.ipChk()) {
			aes = new AES256("LGU+DEV258010247");
		} else {
			aes = new AES256("LGU+210987654321");
		}
		JSONObject json = null;
		try {

			String telNo = (String) commandMap.get("telNo");
			String userKey = (String) commandMap.get("userKey");
			String barcode = (String) commandMap.get("barcode");
			String bizNo = (String) commandMap.get("bizNo");

			if (telNo.contains(" ")) {
				System.out.println("수정");
				telNo = telNo.replaceAll(" ", "+");
			}
			if (userKey.contains(" ")) {
				userKey = userKey.replaceAll(" ", "+");
			}
			System.out.println("parameter data - " + telNo + "      /         " + userKey);

			if (telNo.length() > 11) {
				telNo = aes.decryptBase64String(telNo);
			}
			if (userKey.length() > 21) {
				userKey = aes.decryptBase64String(userKey);
			}

			map.put("telNo", telNo);
			map.put("userKey", userKey);
			map.put("barcode", barcode);
			map.put("bizNo", bizNo);

			shopMap = receiptService.getShopInfo(map);

			System.out.println(shopMap);
			map.put("salesType", shopMap.get("SALES_TYPE"));

			String date = (String) shopMap.get("SALES_DATE");
			date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8) + " "
					+ date.substring(8, 10) + ":" + date.substring(10, 12) + ":" + date.substring(12, 14);
			mv.addObject("salesDate", date);
			mv.addObject("shopInfo", shopMap);

			resultMap = receiptService.ReceiptDetail(map);
			resultMap.put("shopInfo", shopMap);
			receiptService.latestUpdateData(map);

			mv.addObject("detailMap", resultMap);
			mv.setViewName("/uplusReceiptDetail");
			System.out.println(resultMap);

		} catch (Exception e) {
			System.out.println(e);
		}

		return mv;
	}

	// -----------------------------------------------------------------------
	// 전자영수증 uplus 상세페이지
	// -----------------------------------------------------------------------

	@RequestMapping(value = "/receipt/kakaoReceipt.do")
	@ResponseBody
	public Object kakaoReceiptDetail(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		Map<String, Object> shopMap = null;
		Map<String, Object> resultMap = null;
		ModelAndView mv = new ModelAndView();
		HashMap<String, Object> map = new HashMap<String, Object>();
		int listSize = 0;
		String str;
		AES256 aes = null;

		//if (CommonUtils.ipChk()) {
			aes = new AES256("LGU+DEV258010247");
		/*} else {
			aes = new AES256("LGU+210987654321");
		}*/
		JSONObject json = null;
		try {

			String barcode = (String) commandMap.get("No");
			String telNo = (String) commandMap.get("t");

			if (telNo.length() < 12) {
				barcode = aes.encryptStringToBase64(barcode);
				telNo = aes.encryptStringToBase64(telNo);
			}
			System.out.println(" 복호화전 seq   ::: " + barcode);
			System.out.println(" 복호화전 telNo ::: " + telNo);

			/*
			 * barcode = URLDecoder.decode(barcode); telNo =
			 * URLDecoder.decode(telNo);
			 * 
			 * System.out.println(" URL복호화전 seq   ::: " + barcode);
			 * System.out.println(" URL복호화전 telNo ::: " + telNo);
			 */

			barcode = aes.decryptBase64String(barcode);
			telNo = aes.decryptBase64String(telNo);
			System.out.println(" 복호화후 seq   ::: " + barcode);
			System.out.println(" 복호화후 telNo ::: " + telNo);
			map.put("barcode", barcode);
			map.put("telNo", telNo);
			map.put("type", "01");

			shopMap = receiptService.getShopInfo(map);

			map.put("barcode", shopMap.get("SALES_BARCODE"));

			System.out.println(shopMap);
			map.put("salesType", shopMap.get("SALES_TYPE"));

			String date = (String) shopMap.get("SALES_DATE");
			date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8) + " " + date.substring(8, 10) + ":" + date.substring(10, 12) + ":" + date.substring(12, 14);
			mv.addObject("salesDate", date);
			mv.addObject("shopInfo", shopMap);

			resultMap = receiptService.ReceiptDetail(map);
			resultMap.put("shopInfo", shopMap);
			receiptService.latestUpdateData(map);

			mv.addObject("detailMap", resultMap);
			mv.setViewName("/kakaoReceipt");
			System.out.println(resultMap);

		} catch (Exception e) {
			System.out.println(e);
		}

		return mv;
	}
	
	// -----------------------------------------------------------------------
		// 전자영수증 카카오톡 알림톡
		// -----------------------------------------------------------------------

		@RequestMapping(value = "/receipt/kakaoReceiptRenew.do")
		@ResponseBody
		public Object kakaoReceiptDetailRenew(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
			Map<String, Object> shopMap = null;
			Map<String, Object> resultMap = null;
			ModelAndView mv = new ModelAndView();
			HashMap<String, Object> map = new HashMap<String, Object>();
			int listSize = 0;
			String str;
			AES256 aes = null;

			//if (CommonUtils.ipChk()) {
				aes = new AES256("LGU+DEV258010247");
			/*} else {
				aes = new AES256("LGU+210987654321");
			}*/
			JSONObject json = null;
			try {

				String barcode = (String) commandMap.get("No");
				String telNo = (String) commandMap.get("t");

				if (telNo.length() < 12) {
					barcode = aes.encryptStringToBase64(barcode);
					telNo = aes.encryptStringToBase64(telNo);
				}
				System.out.println(" 복호화전 seq   ::: " + barcode);
				System.out.println(" 복호화전 telNo ::: " + telNo);

				/*
				 * barcode = URLDecoder.decode(barcode); telNo =
				 * URLDecoder.decode(telNo);
				 * 
				 * System.out.println(" URL복호화전 seq   ::: " + barcode);
				 * System.out.println(" URL복호화전 telNo ::: " + telNo);
				 */

				barcode = aes.decryptBase64String(barcode);
				telNo = aes.decryptBase64String(telNo);
				System.out.println(" 복호화후 seq   ::: " + barcode);
				System.out.println(" 복호화후 telNo ::: " + telNo);
				map.put("barcode", barcode);
				map.put("telNo", telNo);
				map.put("type", "01");

				
				log.debug("DB Access getShopInfoRenew");
				shopMap = receiptService.getShopInfoRenew(map);

				map.put("barcode", shopMap.get("SALES_BARCODE"));

				System.out.println(shopMap);
				map.put("salesType", shopMap.get("SALES_TYPE"));

				String date = (String) shopMap.get("SALES_DATE");
				
				if(date.length() <= 8){
					date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
				}else{
					date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8) + " " + date.substring(8, 10) + ":" + date.substring(10, 12) + ":" + date.substring(12, 14);
				}
				
				mv.addObject("salesDate", date);
				mv.addObject("shopInfo", shopMap);

				resultMap = receiptService.kakaoReceiptDetail(map);
				resultMap.put("shopInfo", shopMap);
				receiptService.latestUpdateDataRenew(map);

				mv.addObject("detailMap", resultMap);
				mv.setViewName("/kakaoReceipt");
				System.out.println(resultMap);
 
			} catch (Exception e) {
				System.out.println(e);
			}

			return mv;
		}

	// 진호씨

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/receipt/sendMail.do")
	@ResponseBody
	public void sendMail(String CI, @RequestParam(value = "SEQ[]") String[] SEQ, HttpServletRequest request,
			String email, CommandMap commandMap, String subject, String content, String telNo,
			@RequestParam(value = "barcode[]", required = false) String[] barcode) throws Exception {
		Map<String, Object> userDataMap = new HashMap<String, Object>();
		Map<String, Object> userDataDtailMap = new HashMap<String, Object>();
		Map<String, Object> telMap = new HashMap<String, Object>();
		/*
		 * telMap.put("telNo", (String)commandMap.get("telNo"));
		 * Map<String,Object> userMap = receiptService.startUserData(telMap);
		 * System.out.println(userMap); telMap = (Map<String,Object>)
		 * userMap.get("reslutMap"); String userNm =
		 * (String)telMap.get("USER_NM");
		 */
		List<String> list = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> detailResult = new HashMap<>();

		ArrayList paymentList = (ArrayList) result.get("resultMap");
		Map<String, Object> paymentResultMap = new HashMap<String, Object>();

		for (int i = 0; i < SEQ.length; i++) {
			list.add(SEQ[i]);

		}

		for (int i = 0; i < barcode.length; i++) {
			list2.add(barcode[i]);
		}

		// data
		result = receiptService.latestDataEmail(list);
		System.out.println("result :: " + result);
		ArrayList resultList = (ArrayList) result.get("resultMap");

		detailResult = receiptService.latestDataEmailDetail(list2);
		ArrayList<HashMap<String, Object>> resultDetailList = (ArrayList<HashMap<String, Object>>) detailResult
				.get("resultMap");
		String[] body = new String[200];
		String forResult = "";

		for (int i = 0; i < resultList.size(); i++) {
			userDataMap = (HashMap<String, Object>) resultList.get(i);

			// 복합결제일때
			if (userDataMap.get("CARD_COMPOUND").equals("Y")) {

				Map<String, Object> paymentMap = new HashMap<String, Object>();
				Map<String, Object> payImportant = new HashMap<String, Object>();

				payImportant.put("SALES_BARCODE", userDataMap.get("SALES_BARCODE"));
				/* payImportant.put("SEQ", userDataMap.get("SEQ")); */

				paymentMap = receiptService.payment(payImportant);

				paymentList = (ArrayList) paymentMap.get("resultMap");

				body[i] = "<html>" + "<head><meta charset='utf-8'></head>"
						+ "<body><table style='background: #f2f2f2; width: 690px; font-family: Dotum,; font-size:12px; color:#999999;'><tr><td><img src='http://117.52.97.40/theReal/images/top.jpg'></td>"
						+ "<tr><td></td></tr><tr><td align='center'><table style='background: #e5e5e5; width: 410px; border: 1px solid #ddd; margin: 50px;'></tr>"
						+ "<tr><td height='4px' style='background: #e5e5e5;'></td></tr><tr><td align='center'>"
						+ "<table style='background: #fff; width: 400px; border: 1px solid #ddd;'><tr><td><img src='http://117.52.97.40/theReal/images/paper__top.gif'></td></tr>"
						+ "<tr><td align='center' style='padding: 0px 30px 30px 30px;'><p></p><p></p><p style='font-size: 12px; color: gray; clear:both;' align='left'></p>"
						+ "<div class='rt_txt01'style='text-align: left; font-size: 1em; color: #666; clear: both;'></div><div class='affli_div'style='text-align: center; font-size: 20px; padding: 13px 0px; color: #000; font-weight: 700;'>"
						+ userDataMap.get("SHOP_NAME") + "</div><div></div><div></div>"
						+ "<div class='rt_txt01'style='text-align: left; font-size: 12px; color: gray; clear:both; padding: 0px 10px;'><div class='rt_txt01'style='text-align: left; font-size: 1em; color: #666; clear: both;'></div><br><span>대표자명 : "
						+ userDataMap.get("SHOP_CEO") + "</span></div>"
						+ "<div class='rt_txt01'style='text-align: left; font-size: 12px; color: gray; clear:both; padding: 0px 10px;'><span>전화번호 : "
						+ userDataMap.get("SHOP_TEL_NUM") + "</span></div>"
						+ "<div class='rt_txt01'style='text-align: left; font-size: 12px; color: gray; clear:both; padding: 0px 10px;'><span>사업자번호 : "
						+ userDataMap.get("SHOP_BIZNO") + "</span></div>"
						+ "<div class='rt_txt01'style='text-align: left; font-size: 12px; color: gray;  clear:both; padding: 0px 10px;'>주소 : "
						+ userDataMap.get("SHOP_ADDR") + "<span></span></div>"
						+ "<br><br><hr width='100%' style='border:1px dashed #cccccc'>"
						+ "<table width='100%' cellspacing='0' cellpadding='0'><tr style='border-bottom-width: 1px; border-bottom-style: dotted; border-bottom-color: #CCCCCC;'>"
						+ "<th style='13px gulim; padding:4px; color:#666; font-size: 12px;' align='left'>품명</th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='padding: 6px; color:#666; font-size: 12px;'>단가</th>"
						+ "<th style='13px gulim; color:#666;  padding:6px; font-size: 12px;'>수량</th><th style='13px gulim; color:#666;  padding:6px; font-size: 12px;'>금액</th></tr>";

				// detail반복
				for (int j = 0; j < resultDetailList.size(); j++) {

					userDataDtailMap = resultDetailList.get(j);

					if (userDataMap.get("SALES_BARCODE").equals(userDataDtailMap.get("SALES_BARCODE"))) {

						body[i] += "<tr><th style='padding:4px;  color:#666; font-size: 12px;' align='left'>"
								+ userDataDtailMap.get("SALES_PNAME")
								+ "</th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='padding: 6px; color:#666; font-size: 12px;'>"
								+ CommonUtils.replaceComma(Integer.parseInt((String) userDataDtailMap.get("SALES_PPRICE")))
								+ "</th>" + "<th style='13px gulim;  padding:6px; color:#666; font-size: 12px;'>"
								+ userDataDtailMap.get("SALES_QTY")
								+ "</th><th style='13px gulim;  padding:6px; color:#666; font-size: 12px; font-size: 12px;'>"
								+ CommonUtils.replaceComma(Integer.parseInt((String) userDataDtailMap.get("SALES_FP_AMT")))
								+ "</th></tr>";
					}
				}

				body[i] += "<tr>" + "</table>"
						+ "<br><hr width='100%' style='border:1px dashed #cccccc'><table width='100%' cellspacing='0' cellpadding='0'><tr>"
						+ "<th style='color: black; padding: 6px; font-size: 17px' align='left' >과세물품가액</th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th>"
						+ "<th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: black; padding: 6px; font:bold 13px gulim; font-size: 17px'  >"
						+ CommonUtils.replaceComma(Integer.parseInt((String) userDataMap.get("SALES_SUM_FP_AMT"))) + "</th></tr>"
						+ "<tr><th style='color: black; padding: 6px; font-size: 17px' align='left'>부가세</th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th>"
						+ "<th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th>"
						+ "<th style='color: black; padding: 6px; font:bold 13px gulim; font-size: 17px'>"
						+ CommonUtils.replaceComma(Integer.parseInt((String) userDataMap.get("SALES_SUM_TAX_AMT"))) + "</th></tr>"
						+ "<tr><th style='color: black; padding: 6px; font:bold 18px gulim; font-size: 17px' align='left'>합계금액</th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th>"
						+ "<th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th>"
						+ "<th style='color: #5263bd; padding: 6px;'></th><th style='color: black; padding: 6px; font:bold 18px gulim; font-size: 17px'>"
						+ CommonUtils.replaceComma(Integer.parseInt((String) userDataMap.get("SALES_PAID_AMT"))) + "</th></tr>"
						+ "<tr><th style='color: black; padding: 6px; font:bold 18px gulim; font-size: 17px' align='left'>반환금액</th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th>"
						+ "<th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th>"
						+ "<th style='color: #5263bd; padding: 6px;'></th><th style='color: black; padding: 6px; font:bold 18px gulim; font-size: 17px'>0</th></tr>"
						+ "</table><hr width='100%' style='border:1px dashed #cccccc'>"
						+ "<div class='rt_etc'style='text-align: left; font-size: 8px; font-weight: 700; color: #333; clear: both; padding: 10px; border-bottom: 1px dashed #aaa'><div class='rt_copy'style='text-align: center; font-size: 12px;'>";

				for (int a = 0; a < paymentList.size(); a++) {
					paymentResultMap = (HashMap<String, Object>) paymentList.get(a);

					if (paymentResultMap.get("CARD_ICOM").equals("")
							|| paymentResultMap.get("CARD_ICOM").equals(null)) {

						body[i] += "<div align='letf'>현금결제</div><br>";
						body[i] += "<div align='left' style='float: left;'>승인금액 </div> <div align='right'> "
								+ CommonUtils.replaceComma(Integer.parseInt((String) paymentResultMap.get("CARD_AMT"))) + "</div>"
								+ "<div align='left' style='float: left;'>CARD_APP_NO </div><div align='right'>"
								+ paymentResultMap.get("CARD_APP_NO") + "</div>"
								+ "<div align='left' style='float: left;'>CASH_DATE </div> <div align='right'>"
								+ paymentResultMap.get("CASH_DATE") + "</div>";

					} else {
						body[i] += "<div align='left'>" + paymentResultMap.get("CARD_PCOM") + "</div><br>";
						body[i] += "<div align='left' style='float: left;'>승인금액 </div> <div align='right'> "
								+ CommonUtils.replaceComma(Integer.parseInt((String) paymentResultMap.get("CARD_AMT"))) + "</div>"
								+ "<div align='left' style='float: left;'>CARD_APP_NO </div><div align='right'>"
								+ paymentResultMap.get("CARD_APP_NO") + "</div>"
								+ "<div align='left' style='float: left;'>CARD_DATE </div> <div align='right'>"
								+ paymentResultMap.get("CARD_DATE") + "</div>"
								+ "<div align='left' style='float: left;'>CARD_ICOM </div> <div align='right'> "
								+ paymentResultMap.get("CARD_ICOM") + "</div>";

					}

					body[i] += "<div align='left' style='float: left;'>카드번호 </div> <div align='right'>"
							+ paymentResultMap.get("CARD_NO") + "</div>"
							+ "<hr width='100%' style='border:1px dashed #cccccc'>";
				}

				body[i] += "<br>" + "***신용승인정보(고객용)***"
						+ "<div class='rt_txt01'style='text-align: left; font-size: 10px; color: #666; clear: both' align='left'>거래종류: 복합결제</div>";

				body[i] += "<div class='rt_txt01'style='text-align: left; font-size: 10px; color: #666; clear: both' >거래일시:: "
						+ userDataMap.get("FRT_CREA_DTM") + "</div></div>"
						+ "</div><div class='rt_copy'style='text-align: center; font-size: 12px;'><br>본 전자영수증은 거래의 참고용으로 사용하시기 바랍니다.</div>"
						+ "</td></tr></table></td></tr></tsable></td>" + "</tr></table></body></html>";
				forResult += body[i];
			}

			// 복합결제아닐때
			else {
				// data 반복
				body[i] = "<html>" + "<head><meta charset='utf-8'></head>"
						+ "<body><table style='background: #f2f2f2; width: 690px; font-family: Dotum,; font-size:12px; color:#999999;'><tr><td><img src='http://117.52.97.40/theReal/images/top.jpg'></td>"
						+ "<tr><td></td></tr><tr><td align='center'><table style='background: #e5e5e5; width: 410px; border: 1px solid #ddd; margin: 50px;'></tr>"
						+ "<tr><td height='4px' style='background: #e5e5e5;'></td></tr><tr><td align='center'>"
						+ "<table style='background: #fff; width: 400px; border: 1px solid #ddd;'><tr><td><img src='http://117.52.97.40/theReal/images/paper_top.gif'></td></tr>"
						+ "<tr><td align='center' style='padding: 0px 30px 30px 30px;'><p></p><p></p><p style='font-size: 12px; color: gray; clear:both;' align='left'></p>"
						+ "<div class='rt_txt01'style='text-align: left; font-size: 1em; color: #666; clear: both;'></div><div class='affli_div'style='text-align: center; font-size: 20px; padding: 13px 0px; color: #000; font-weight: 700;'>"
						+ userDataMap.get("SHOP_NAME") + "</div><div></div><div></div>"
						+ "<div class='rt_txt01'style='text-align: left; font-size: 12px; color: gray; clear:both; padding: 0px 10px;'><div class='rt_txt01'style='text-align: left; font-size: 1em; color: #666; clear: both;'></div><br><span>대표자명 : "
						+ userDataMap.get("SHOP_CEO") + "</span></div>"
						+ "<div class='rt_txt01'style='text-align: left; font-size: 12px; color: gray; clear:both; padding: 0px 10px;'><span>전화번호 : "
						+ userDataMap.get("SHOP_TEL_NUM") + "</span></div>"
						+ "<div class='rt_txt01'style='text-align: left; font-size: 12px; color: gray; clear:both; padding: 0px 10px;'><span>사업자번호 : "
						+ userDataMap.get("SHOP_BIZNO") + "</span></div>"
						+ "<div class='rt_txt01'style='text-align: left; font-size: 12px; color: gray;  clear:both; padding: 0px 10px;'>주소 : "
						+ userDataMap.get("SHOP_ADDR") + "<span></span></div>"
						+ "<br><br><hr width='100%' style='border:1px dashed #cccccc'>"
						+ "<table width='100%' cellspacing='0' cellpadding='0'><tr style='border-bottom-width: 1px; border-bottom-style: dotted; border-bottom-color: #CCCCCC;'>"
						+ "<th style='13px gulim; padding:4px; color:#666; font-size: 12px;' align='left'>품명</th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='padding: 6px; color:#666; font-size: 12px;'>단가</th>"
						+ "<th style='13px gulim; color:#666;  padding:6px; font-size: 12px;'>수량</th><th style='13px gulim; color:#666;  padding:6px; font-size: 12px;'>금액</th></tr>";

				// detail반복
				for (int j = 0; j < resultDetailList.size(); j++) {
					userDataDtailMap = resultDetailList.get(j);

					if (userDataMap.get("SALES_BARCODE").equals(userDataDtailMap.get("SALES_BARCODE"))) {
						body[i] += "<tr><th style='padding:4px;  color:#666; font-size: 12px;' align='left'>"
								+ userDataDtailMap.get("SALES_PNAME")
								+ "</th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='padding: 6px; color:#666; font-size: 12px;'>"
								+ CommonUtils.replaceComma(Integer.parseInt((String) userDataDtailMap.get("SALES_PPRICE")))
								+ "</th>" + "<th style='13px gulim;  padding:6px; color:#666; font-size: 12px;'>"
								+ userDataDtailMap.get("SALES_QTY")
								+ "</th><th style='13px gulim;  padding:6px; color:#666; font-size: 12px; font-size: 12px;'>"
								+ CommonUtils.replaceComma(Integer.parseInt((String) userDataDtailMap.get("SALES_FP_AMT")))
								+ "</th></tr>";
					}
				}

				body[i] += "<tr>" + "</table>"
						+ "<br><hr width='100%' style='border:1px dashed #cccccc'><table width='100%' cellspacing='0' cellpadding='0'><tr>"
						+ "<th style='color: black; padding: 6px; font-size: 17px' align='left' >과세물품가액</th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th>"
						+ "<th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: black; padding: 6px; font:bold 13px gulim; font-size: 17px'  >"
						+ CommonUtils.replaceComma(Integer.parseInt((String) userDataMap.get("SALES_SUM_FP_AMT"))) + "</th></tr>"
						+ "<tr><th style='color: black; padding: 6px; font-size: 17px' align='left'>부가세</th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th>"
						+ "<th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th>"
						+ "<th style='color: black; padding: 6px; font:bold 13px gulim; font-size: 17px'>"
						+ CommonUtils.replaceComma(Integer.parseInt((String) userDataMap.get("SALES_SUM_TAX_AMT"))) + "</th></tr>"
						+ "<tr><th style='color: black; padding: 6px; font:bold 18px gulim; font-size: 17px' align='left'>합계금액</th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th>"
						+ "<th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th>"
						+ "<th style='color: #5263bd; padding: 6px;'></th><th style='color: black; padding: 6px; font:bold 18px gulim; font-size: 17px'>"
						+ CommonUtils.replaceComma(Integer.parseInt((String) userDataMap.get("SALES_PAID_AMT"))) + "</th></tr>"
						+ "<tr><th style='color: black; padding: 6px; font:bold 18px gulim; font-size: 17px' align='left'>반환금액</th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th>"
						+ "<th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th>"
						+ "<th style='color: #5263bd; padding: 6px;'></th><th style='color: black; padding: 6px; font:bold 18px gulim; font-size: 17px'>0</th></tr>"
						+ "</table><hr width='100%' style='border:1px dashed #cccccc'>"
						+ "<div class='rt_etc'style='text-align: left; font-size: 8px; font-weight: 700; color: #333; clear: both; padding: 10px; border-bottom: 1px dashed #aaa'><div class='rt_copy'style='text-align: center; font-size: 12px;'>"
						+ "***신용승인정보(고객용)***";

				String cardIcom = (String) userDataMap.get("CARD_ICOM");
				if (cardIcom.equals("") || cardIcom.equals(null)) {

					body[i] += "<div class='rt_txt01'style='text-align: left; font-size: 10px; color: #666; clear: both'>거래종류: 현금결제</div>";
				} else {

					body[i] += "<div class='rt_txt01'style='text-align: left; font-size: 10px; color: #666; clear: both'>거래종류: "
							+ userDataMap.get("CARD_ICOM") + "</div>";
				}

				body[i] += "<div class='rt_txt01'style='text-align: left; font-size: 10px; color: #666; clear: both'>거래일시:: "
						+ userDataMap.get("FRT_CREA_DTM") + "</div></div>"
						+ "</div><div class='rt_copy'style='text-align: center; font-size: 12px;'><br>본 전자영수증은 거래의 참고용으로 사용하시기 바랍니다.</div>"
						+ "</td></tr></table></td></tr></tsable></td>" + "</tr></table></body></html>";
				forResult += body[i];
			}

		}

		// 서버에 파일쓰기 시작
		FileWriter fWriter = null;
		BufferedWriter writer = null;
		try {
			fWriter = new FileWriter("../TheReal 이메일영수증.html");
			writer = new BufferedWriter(fWriter);
			writer.write(forResult);
			writer.newLine(); // this is not actually needed for html files -
								// can
								// make your code more readable though
			writer.close(); // make sure you close the writer object
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 메일전송세팅시작 //제목
		String emailFromAddress = "thereal@realmkt.co.kr";
		String[] emailList = { email };

		try {
			consoleMail smtpMailSender = new consoleMail();
			smtpMailSender.postMail(emailList, subject, content, emailFromAddress, "test");

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}

		try {
			// 진호 수정 발송횟수 증가
			receiptService.transUp(list);
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}

		try {
			// 진호수정 메일비교 후 지난 이메일 업데이트
			String emailResult = receiptService.eMailChk(CI);

			if (!emailResult.equals(email)) {
				Map<String, Object> emailMap = new HashMap<>();
				emailMap.put("email", email);
				emailMap.put("CI", CI);
				receiptService.lastEmailUpdate(emailMap);
			} else {
				return;
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@SuppressWarnings({ "unused", "unchecked" })
	@ResponseBody
	@RequestMapping(value = "/receipt/affliate.do")
	public Map<String, Object> affliate(String affliate_no, String find, int affliate_endSeq, float lat1, float lon1) {

		List<Float> kmList = new ArrayList<Float>();
		Map<String, Object> resultMap = new HashMap<>();
		Map<String, Object> findMap = new HashMap<>();
		Map<String, Object> AffliateMap = new HashMap<>();
		Map<String, Object> kmMap = new HashMap<>();
		int count = 0;

		if (affliate_no.equals(null) || affliate_no.equals("") || affliate_no == null) {

			try {
				if (find.equals(null) || find.equals("") || find == null) {

					findMap.put("find", "");
					findMap.put("startSeq", 0);
					findMap.put("endSeq", affliate_endSeq);
					resultMap = receiptService.affliate(findMap);

				} else {

					findMap.put("find", find);
					findMap.put("endSeq", affliate_endSeq);
					resultMap = receiptService.affliate(findMap);
				}

			} catch (Exception e) {
				e.getMessage();
			}

		} else {

			try {
				resultMap = receiptService.affliateDetail(affliate_no);
				// 1월 26일 추가
				count = receiptService.reviewCount(affliate_no);

			} catch (Exception e) {
				e.getMessage();
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("find", "");
		map.put("startSeq", 0);
		map.put("endSeq", 2147483647);

		Map maxMap = receiptService.affliate(map);

		List<Map<String, Object>> maxList = (ArrayList<Map<String, Object>>) maxMap.get("resultMap");
		int maxSize = maxList.size();
		resultMap.put("maxSize", maxSize);

		ArrayList resultList = (ArrayList) resultMap.get("resultMap");

		String clientId = "dG4rSrwqJ4AxwuRObudf";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "JJuH7Dy7PN";// 애플리케이션 클라이언트 시크릿값";

		for (int i = 0; i < resultList.size(); i++) {

			AffliateMap = (Map<String, java.lang.Object>) resultList.get(i);

			try {
				String addr = URLEncoder.encode((String) AffliateMap.get("ROAD_ADDR"), "utf-8");
				String apiURL = "https://openapi.naver.com/v1/map/geocode?query=" + addr; // json
				// String apiURL =
				// "https://openapi.naver.com/v1/map/geocode.xml?query=" + addr;
				// // xml
				URL url = new URL(apiURL);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();

				con.setRequestMethod("GET");
				con.setRequestProperty("X-Naver-Client-Id", clientId);
				con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

				int responseCode = con.getResponseCode();
				BufferedReader br;
				if (responseCode == 200) { // 정상 호출

					br = new BufferedReader(new InputStreamReader(con.getInputStream()));

				} else { // 에러 발생
					br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				}

				String inputLine;

				StringBuffer response = new StringBuffer();

				while ((inputLine = br.readLine()) != null) {
					response.append(inputLine);
				}

				br.close();

				JSONParser json = new JSONParser();
				JSONObject jobj = (JSONObject) json.parse(response.toString());
				// JsonArray jarr = (JsonArray)jobj.get("result");

				Map<String, Object> tempMap = (HashMap<String, Object>) jobj.get("result");
				ArrayList<Map<String, Object>> tempList = (ArrayList<Map<String, Object>>) tempMap.get("items");
				Map<String, Object> tempMap3 = (HashMap<String, Object>) tempList.get(0);
				Map<String, Object> tempMap4 = (HashMap<String, Object>) tempMap3.get("point");

				double lat2 = (double) tempMap4.get("y");
				double lon2 = (double) tempMap4.get("x");

				float R = 6371;

				float dRat = (float) ((lat2 - lat1) * Math.PI / 180);
				float dLon = (float) ((lon2 - lon1) * Math.PI / 180);
				float a = (float) ((float) (Math.sin(dRat / 2) * Math.sin(dRat / 2)) + Math.cos(lat1 * Math.PI / 180)
						* Math.cos(lat2 * Math.PI / 180) * Math.sin(dLon / 2) * Math.sin(dLon / 2));
				float c = (float) (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)));
				float d = R * c;

				kmList.add(i, d);

			} catch (Exception e) {
				System.out.println(e);
			}

		}

		resultMap.put("kmList", kmList);
		// 1월 26일 추가
		resultMap.put("reviewCount", count);

		return resultMap;
	}

	@ResponseBody
	@RequestMapping(value = "/receipt/reviewList.do")
	public Map<String, Object> reviewList(CommandMap commandMap) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Map<String, Object> reviewMap = new HashMap<String, Object>();

		reviewMap.put("affliate_no", commandMap.get("affliate_no"));
		reviewMap.put("startSeq", 0);
		reviewMap.put("endSeq", Integer.parseInt((String) commandMap.get("endSeq")));

		resultMap = receiptService.reviewList(reviewMap);
		// 1월 17일 수정

		reviewMap.put("affliate_no", commandMap.get("affliate_no"));
		reviewMap.put("startSeq", 0);
		reviewMap.put("endSeq", 2147483647);

		Map maxMap = receiptService.reviewList(reviewMap);
		List<Map<String, Object>> maxList = (ArrayList<Map<String, Object>>) maxMap.get("resultMap");
		int maxSize = maxList.size();

		resultMap.put("maxSize", maxSize);

		return resultMap;

	}

	// 1월 11일 추가
	// 1월 15일 수정
	@ResponseBody
	@RequestMapping(value = "/receipt/reviewInsert.do")
	public void reviewInsert(CommandMap commandMap) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("REVIEW_WRITER", commandMap.get("REVIEW_WRITER"));
		map.put("REVIEW_WRITER_CI", commandMap.get("REVIEW_WRITER_CI"));
		map.put("REVIEW_CONTENT", commandMap.get("REVIEW_CONTENT"));
		map.put("REVIEW_AFFLIATE_NO", commandMap.get("REVIEW_AFFLIATE_NO"));
		map.put("REVIEW_AFFLIATE_STAR", commandMap.get("REVIEW_AFFLIATE_STAR"));

		receiptService.reviewInsert(map);

		float avg = receiptService.reviewStarAvg(map);

		Map<String, Object> starMap = new HashMap<String, Object>();

		starMap.put("avg", avg);
		starMap.put("affliate_no", map.get("REVIEW_AFFLIATE_NO"));

		receiptService.starUpdate(starMap);

		receiptService.starUpdateA(starMap);

		// 1월 29일추가
		receiptService.countUp((String) commandMap.get("REVIEW_AFFLIATE_NO"));
	}

	// 1월 22일 추가
	// 1월 23일 수정

	@ResponseBody
	@RequestMapping(value = "/receipt/reviewDelete.do")
	public void reviewDelete(CommandMap commandMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		float avg = 0;
		receiptService.reviewDelete(Integer.parseInt((String) commandMap.get("review_num")));

		map.put("review_num", Integer.parseInt((String) commandMap.get("review_num")));
		map.put("REVIEW_AFFLIATE_NO", commandMap.get("REVIEW_AFFLIATE_NO"));

		Map<String, Object> starMap = new HashMap<String, Object>();

		try {
			avg = receiptService.reviewStarAvg(map);

		} catch (Exception e) {

			starMap.put("avg", 0);
			starMap.put("affliate_no", map.get("REVIEW_AFFLIATE_NO"));

			receiptService.starUpdate(starMap);

			receiptService.starUpdateA(starMap);

		}

		starMap.put("avg", avg);
		starMap.put("affliate_no", map.get("REVIEW_AFFLIATE_NO"));

		receiptService.starUpdate(starMap);

		receiptService.starUpdateA(starMap);

		// 1월 29일 추가
		receiptService.countDown((String) commandMap.get("REVIEW_AFFLIATE_NO"));
	}

	@ResponseBody
	@RequestMapping(value = "/receipt/mailPush.do")
	public void mailPush(String CI, String mail) {
		Map<String, Object> mailMap = new HashMap<String, Object>();
		mailMap.put("CI", CI);
		mailMap.put("mail", mail);
		receiptService.mailPush(mailMap);
	}

	@ResponseBody
	@RequestMapping(value = "/receipt/emailOk.do")
	public Map<String, Object> emailOk(String telNo) {
		Map<String, Object> resultMap = new HashMap<>();
		System.out.println(telNo);
		try {
			resultMap = receiptService.emailList(telNo);
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}

		return resultMap;
	}

	@ResponseBody
	@RequestMapping(value = "/receipt/leave.do")
	public void leave(CommandMap commandMap) {
		String CI = (String) commandMap.get("CI");

		try {

			receiptService.leave(CI);

		} catch (Exception e) {
			e.getMessage();
		}
	}

	@ResponseBody
	@RequestMapping({ "/receipt/uplusReceiptData.do" })
	public String uplusRecipeData(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String resStr = "";
		String str = "";
		String jsonResData = "";
		StringBuffer paramData = new StringBuffer();
		int totalPaidAmt = 0;

		AES256 aes = null;

		if (CommonUtils.ipChk()) {
			aes = new AES256("LGU+DEV258010247");
		} else {
			aes = new AES256("LGU+210987654321");
		}

		BufferedReader br = null;

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = null;
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

			do {

				paramData.append(str);
			} while ((str = br.readLine()) != null);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		try {

			System.out.println("@@1111paramData22@@:" + paramData);

			HashMap map = new HashMap();

			String value = paramData.toString();

			String dec = "";
			/*
			 * String enc = aes.encryptStringToBase64(value);
			 * 
			 * System.out.println("암호화 *************"+enc);
			 */

			dec = aes.decryptBase64String(value);

			System.out.println("복호화*********" + dec);

			Var var2 = JsonParser.object(new CharTokenizer(dec));

			String userKey = var2.find("userKey").toString();
			String nowPage = var2.find("nowPage").toString();
			String numPage = var2.find("numPage").toString();
			String selectStartDate = var2.find("selectStartDate").toString();
			String selectEndDate = var2.find("selectEndDate").toString();
			String pmInfoType = var2.find("pmInfoType").toString();

			String state = receiptService.userState(userKey);

			if (state == null || state.equals("")) {

				resStr += "{";
				resStr += "\"result\": \"UR001\",";
				resStr += "\"message\": \"리스트 조회 실패 - 없는 userKey입니다.\"";
				resStr += "}";
				String resStrenc = aes.encryptStringToBase64(resStr);

				System.out.println("응답 데이터::::::::::" + resStr);
				return resStrenc;
			}

			if (state.equals("02")) {

				resStr += "{";
				resStr += "\"result\": \"UR002\",";
				resStr += "\"message\": \"리스트 조회 실패 - 탈퇴된 고객의 userKey입니다.\"";
				resStr += "}";
				String resStrenc = aes.encryptStringToBase64(resStr);

				System.out.println("응답 데이터::::::::::" + resStr);
				return resStrenc;
			}

			if (state.equals("05")) {

				resStr += "{";
				resStr += "\"result\": \"UR003\",";
				resStr += "\"message\": \"리스트 조회 실패 - 재가입된 유저의 userKey입니다.\"";
				resStr += "}";
				String resStrenc = aes.encryptStringToBase64(resStr);

				System.out.println("응답 데이터::::::::::" + resStr);
				return resStrenc;
			}

			System.out.println("응답밖데이터 :::::::::");

			Map userMap = new HashMap();
			Map resultMap = new HashMap();

			int startCount = (Integer.parseInt(nowPage) - 1) * Integer.parseInt(numPage);

			userMap.put("userKey", userKey);
			userMap.put("startCount", Integer.valueOf(startCount));
			userMap.put("endCount", Integer.valueOf(numPage));
			userMap.put("selectStartDate", selectStartDate);
			userMap.put("selectEndDate", selectEndDate);
			userMap.put("pmInfoType", pmInfoType);

			System.err.println("@@@@@@@@userMap:::::::::::::" + userMap);

			resultMap = this.receiptService.uplusReceiptData2(userMap);

			ArrayList list = (ArrayList) resultMap.get("resultMap");

			Map selectMap = new HashMap();

			resStr = resStr + "{";
			resStr = resStr + "\t\"userKey\": \"" + userKey + "\",";
			resStr += "\"result\": \"UR000\",";
			resStr += "\"message\": \"리스트가 정상적으로 조회되었습니다.\",";
			resStr = resStr + "\t\"salesInfo\": [ ";

			List shopInfo = new ArrayList();

			Long userTotalCnt = null;

			Map<String, Object> allMap = new HashMap<String, Object>();

			allMap = receiptService.allPaid(userMap);

			ArrayList list2 = (ArrayList) allMap.get("resultMap");

			for (int i = 0; i < list2.size(); i++) {

				HashMap listMap2 = (HashMap) list2.get(i);
				totalPaidAmt += Integer.parseInt((String) listMap2.get("SALES_PAID_AMT"));
			}

			System.out.println("@@@@@@@@listSize@@@@@@@@" + list.size());

			for (int i = 0; i < list.size(); i++) {

				HashMap listMap = (HashMap) list.get(i);

				if (i == 0) {
					System.out.println("cnt111111 ::::  " + i);
					userTotalCnt = (Long) listMap.get("USER_TOTAL_CNT");
					System.out.println("cnt ::::  " + userTotalCnt);
				}

				String bizno = listMap.get("SHOP_BIZNO").toString();

				String erecNo = bizno;

				erecNo = erecNo + "_";
				erecNo = erecNo + listMap.get("SALES_BARCODE").toString();

				resStr = resStr + "{";
				resStr = resStr + "\t\"erecNo\": \"" + erecNo + "\",";
				resStr = resStr + "\t\"name\": \"" + listMap.get("SHOP_NAME") + "\",";
				resStr = resStr + "\t\"salesType\": \"" + listMap.get("SALES_TYPE") + "\",";

				if (((String) listMap.get("CARD_NO")).length() < 12)
					resStr = resStr + "\t\"uplusUseCd\": \"1\",";
				else {
					resStr = resStr + "\t\"uplusUseCd\": \"0\",";
				}
				resStr = resStr + "\t\"salesDate\": \"" + listMap.get("SALES_DATE") + "\",";

				if (listMap.get("CARD_COMPOUND") == "Y")
					resStr = resStr + "\t\"cardIcom\": \"\",";
				else {
					resStr = resStr + "\t\"cardIcom\": \"" + listMap.get("CARD_ICOM") + "\",";
				}
				resStr = resStr + "\t\"paidAmt\": \"" + listMap.get("SALES_PAID_AMT") + "\"";

				if (i == list.size() - 1)
					resStr = resStr + "}  ";
				else {
					resStr = resStr + "},  ";
				}
			}

			System.out.println("@@@@@@@@@@@@@@@@@@@userTotalCnt@@@@@@@@@@@" + userTotalCnt);
			System.out.println("@@@@@@@@@@@@@@@@@@@@현재페이지@@@@@@@@@@" + nowPage);
			System.out.println("@@@@@@@@@@@@@@@@@@@@페이지당 개수@@@@@@@@@@@" + numPage);
			System.out.println();
			System.out.println();
			System.out.println("@@@@@@@@@@@@@@@@@@@시작카운트@@@@@@@@@@@@@@@@@@@@" + Integer.valueOf(startCount));
			System.out.println("@@@@@@@@@@@@@@@@@@@@끝나는카운트@@@@@@@@@@@@@@@@@@@" + Integer.valueOf(numPage));

			int maxPage = 0;

			if (list.size() > 0) {
				float maxPageTemp = Float.parseFloat(userTotalCnt.toString()) / Float.parseFloat(numPage);
				maxPage = (int) Math.ceil(maxPageTemp);
			} else {
				userTotalCnt = Long.valueOf(0L);
			}

			resStr = resStr + " ], ";
			resStr = resStr + "\t\"totalPaidAmt\": \"" + totalPaidAmt + "\",";
			resStr = resStr + "\t\"totalCount\": \"" + userTotalCnt + "\",";
			resStr = resStr + "\t\"pmInfoType\": \"" + pmInfoType + "\",";
			resStr = resStr + "\t\"nowPage\": \"" + nowPage + "\",";
			resStr = resStr + "\t\"maxPage\": \"" + maxPage + "\"";
			resStr = resStr + "\t}";
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}

		System.out.println("json result :: " + resStr);

		String resStrenc = aes.encryptStringToBase64(resStr);
		System.out.println("------------------------");
		System.out.println(resStrenc);
		System.out.println("------------------------");

		return resStrenc;

	}

	@ResponseBody
	@RequestMapping({ "/receipt/uplusReceiptDataDetail.do" })
	public String uplusReceipeDataDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String str = "";
		String resStr = "";
		Map userMap = new HashMap();
		Map userMap2 = new HashMap();
		Map dataMap = new HashMap();
		Map detailMap = new HashMap();

		String jsonResData = "";
		StringBuffer paramData = new StringBuffer();
		int totalPaidAmt = 0;

		AES256 aes = null;

		if (CommonUtils.ipChk()) {
			aes = new AES256("LGU+DEV258010247");
		} else {
			aes = new AES256("LGU+210987654321");
		}

		BufferedReader br = null;

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = null;
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

			do {
				paramData.append(str);
			} while ((str = br.readLine()) != null);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		try {

			System.out.println("@@1111paramData22@@:" + paramData);

			HashMap map = new HashMap();

			String value = paramData.toString();

			/*
			 * String enc = "";
			 * 
			 * enc = aes.encryptStringToBase64(value);
			 * 
			 * System.out.println("암호화******************"+enc);
			 */

			String dec = "";

			System.out.println("************벨류***************" + value);

			dec = aes.decryptBase64String(value);

			System.out.println("*****************복호화***************" + dec);

			Var var2 = JsonParser.object(new CharTokenizer(dec));

			System.out.println("-----------------------------------");
			System.out.println();
			System.out.println(":: " + var2.toString());
			System.out.println();
			System.out.println("-----------------------------------");

			String userKey = var2.find("userKey").toString();
			String erecNo = var2.find("erecNo").toString();
			String[] erecB = erecNo.split("_");
			String barcode = erecNo.substring(erecNo.indexOf("_") + 1, erecNo.length());

			String state = receiptService.userState(userKey);

			if (state == null || state.equals("")) {

				resStr += "{";
				resStr += "\"result\": \"UR101\",";
				resStr += "\"message\": \"상세영수증 조회 실패 - 없는 userKey입니다.\"";
				resStr += "}";
				String resStrenc = aes.encryptStringToBase64(resStr);

				System.out.println("응답 데이터::::::::::" + resStr);
				return resStrenc;
			}

			if (state.equals("02")) {

				resStr += "{";
				resStr += "\"result\": \"UR102\",";
				resStr += "\"message\": \"상세영수증 조회 실패 - 탈퇴된 고객의 userKey입니다.\"";
				resStr += "}";
				String resStrenc = aes.encryptStringToBase64(resStr);

				System.out.println("응답 데이터::::::::::" + resStr);
				return resStrenc;
			}

			if (state.equals("05")) {

				resStr += "{";
				resStr += "\"result\": \"UR103\",";
				resStr += "\"message\": \"상세영수증 조회 실패 - 재가입된 유저의 userKey입니다.\"";
				resStr += "}";
				String resStrenc = aes.encryptStringToBase64(resStr);

				System.out.println("응답 데이터::::::::::" + resStr);
				return resStrenc;
			}

			userMap.put("bizno", erecB[0]);
			userMap.put("barcode", erecB[1]);

			userMap2.put("bizno", erecB[0]);
			userMap2.put("barcode", erecB[1]);

			dataMap = this.receiptService.uplusUserData(userMap);

			detailMap = this.receiptService.uplusReceipeDataDetail(userMap2);

			ArrayList dataList = (ArrayList) dataMap.get("resultMap");

			if (dataList.size() == 0) {
				resStr += "{";
				resStr += "\"result\": \"UR104\",";
				resStr += "\"message\": \"상세 영수증 조회 실패 - 영수증 번호가 존재하지 않습니다.\"";
				resStr += "}";

				String resStrenc = aes.encryptStringToBase64(resStr);

				System.out.println("응답 데이터::::::::::" + resStr);
				return resStrenc;
			}

			ArrayList detailList = (ArrayList) detailMap.get("resultMap");

			if (detailList.size() == 0) {
				resStr += "{";
				resStr += "\"result\": \"UR104\",";
				resStr += "\"message\": \"상세 영수증 조회 실패 - 영수증 번호가 존재하지 않습니다.\"";
				resStr += "}";

				String resStrenc = aes.encryptStringToBase64(resStr);

				System.out.println("응답 데이터::::::::::" + resStr);
				return resStrenc;
			}

			HashMap dataListMap = (HashMap) dataList.get(0);

			resStr = resStr + "{";
			resStr = resStr + "\t\"userKey\": \"" + userKey + "\",";
			resStr += "\"result\": \"UR100\",";
			resStr += "\"message\": \"상세 영수증이 정상적으로 조회되었습니다.\",";
			resStr = resStr + "\t\"etcInfo\": [ ";
			resStr = resStr + "{";
			resStr = resStr + "\t\"memo\": \"" + dataListMap.get("ETC_MEMO") + "\",";
			resStr = resStr + "\t\"event\": \"" + dataListMap.get("ETC_EVENT") + "\"";
			resStr = resStr + "}  ";
			resStr = resStr + " ], ";
			resStr = resStr + "\t\"shopInfo\": [ ";
			resStr = resStr + "{";
			resStr = resStr + "\t\"name\": \"" + dataListMap.get("SHOP_NAME") + "\",";
			resStr = resStr + "\t\"bizNo\": \"" + dataListMap.get("SHOP_BIZNO") + "\",";
			resStr = resStr + "\t\"addr\": \"" + dataListMap.get("SHOP_ADDR") + "\",";
			resStr = resStr + "\t\"ceo\": \"" + dataListMap.get("SHOP_CEO") + "\",";
			resStr = resStr + "\t\"phone\": \"" + dataListMap.get("SHOP_TEL_NUM") + "\",";
			resStr = resStr + "\t\"cashier\": \"" + dataListMap.get("SHOP_CASHIER") + "\"";
			resStr = resStr + "}  ";
			resStr = resStr + " ], ";
			resStr = resStr + "\t\"salesInfo\": [ ";
			resStr = resStr + "{";
			resStr = resStr + "\t\"salesBarcode\": \"" + dataListMap.get("SALES_BARCODE") + "\",";
			resStr = resStr + "\t\"salesDate\": \"" + dataListMap.get("SALES_DATE") + "\",";
			resStr = resStr + "\t\"printDate\": \"" + dataListMap.get("SALES_PRINT_DATE") + "\",";
			resStr = resStr + "\t\"salesType\": \"" + dataListMap.get("SALES_TYPE") + "\",";
			resStr = resStr + "\t\"sumDfAmt\": \"" + dataListMap.get("SALES_SUM_DF_AMT") + "\",";
			resStr = resStr + "\t\"sumFpAmt\": \"" + dataListMap.get("SALES_SUM_FP_AMT") + "\",";
			resStr = resStr + "\t\"sumTaxAmt\": \"" + dataListMap.get("SALES_SUM_TAX_AMT") + "\",";
			resStr = resStr + "\t\"sumAllAmt\": \"" + dataListMap.get("SALES_SUM_ALL_AMT") + "\",";
			resStr = resStr + "\t\"sumOpAmt\": \"" + dataListMap.get("SALES_SUM_OP_AMT") + "\",";
			resStr = resStr + "\t\"chgAmt\": \"" + dataListMap.get("SALES_CHG_AMT") + "\",";
			resStr = resStr + "\t\"paidAmt\": \"" + dataListMap.get("SALES_PAID_AMT") + "\",";
			resStr = resStr + "\t\"dtCnt\": \"" + dataListMap.get("SALES_DT_CNT") + "\"";
			resStr = resStr + "}  ";
			resStr = resStr + " ], ";

			resStr = resStr + "\t\"salesList\": [ ";

			for (int i = 0; i < detailList.size(); i++) {
				HashMap detailListMap = (HashMap) detailList.get(i);

				resStr = resStr + "{";
				resStr = resStr + "\t\"seqNo\": \"" + i + "\",";
				resStr = resStr + "\t\"pName\": \"" + detailListMap.get("SALES_PNAME") + "\",";
				resStr = resStr + "\t\"pPrice\": \"" + detailListMap.get("SALES_PPRICE") + "\",";
				resStr = resStr + "\t\"oPrice\": \"" + detailListMap.get("SALES_OPRICE") + "\",";
				resStr = resStr + "\t\"qty\": \"" + detailListMap.get("SALES_QTY") + "\",";
				resStr = resStr + "\t\"dfAmt\": \"" + detailListMap.get("SALES_DF_AMT") + "\",";
				resStr = resStr + "\t\"fpAmt\": \"" + detailListMap.get("SALES_FP_AMT") + "\",";
				resStr = resStr + "\t\"taxAmt\": \"" + detailListMap.get("SALES_TAX_AMT") + "\",";
				resStr = resStr + "\t\"slAmt\": \"" + detailListMap.get("SALES_SL_AMT") + "\",";
				resStr = resStr + "\t\"opAmt\": \"" + detailListMap.get("SALES_OP_AMT") + "\"";

				if (i == detailList.size() - 1)
					resStr = resStr + "}  ";
				else {
					resStr = resStr + "} , ";
				}

			}

			resStr = resStr + " ], ";

			resStr = resStr + "\t\"paidList\": [ ";

			for (int i = 0; i < dataList.size(); i++) {
				HashMap dataListMap2 = (HashMap) dataList.get(i);

				String card_no = (String) dataListMap.get("CARD_NO");
				String card_icom = (String) dataListMap.get("CARD_ICOM");
				String point_icom = (String) dataListMap.get("POINT_ICOM");

				resStr = resStr + "{";

				if (card_no.startsWith("01") && card_no.length() <= 12) {
					resStr = resStr + "\t\"pmType\": \"01 \",";
				} else if (!point_icom.equals("")) {
					resStr = resStr + "\t\"pmType\": \"04 \",";
				} else if (!card_icom.equals("")) {
					resStr = resStr + "\t\"pmType\": \"02 \",";
				} else {
					resStr = resStr + "\t\"pmType\": \"03 \",";
				}

				resStr = resStr + "\t\"cardIcom\": \"" + dataListMap2.get("CARD_ICOM") + "\",";
				resStr = resStr + "\t\"cardNo\": \"" + dataListMap2.get("CARD_NO") + "\",";
				resStr = resStr + "\t\"cardInstallment\": \"" + dataListMap2.get("CARD_INSTALLMENT") + "\",";
				resStr = resStr + "\t\"pointAmt\": \"" + dataListMap2.get("POINT_AMT") + "\"";

				if (i == dataList.size() - 1)
					resStr = resStr + "}  ";
				else {
					resStr = resStr + "} , ";
				}

			}

			resStr = resStr + " ] ";
			resStr = resStr + "}  ";

		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
		System.out.println("***********조합***********");
		System.out.println(resStr);
		System.out.println("***********조합***********");

		Var var2 = JsonParser.object(new CharTokenizer(resStr));

		System.out.println("****************************");
		System.out.println(var2.toString());
		System.out.println("****************************");

		String resStrenc = aes.encryptStringToBase64(resStr);

		return resStrenc;
	}

	@ResponseBody
	@RequestMapping(value = "/receipt/decrypt.do")
	public Object decryptDev(CommandMap commandMap) {
		String st = (String) commandMap.get("st");
		String dev = (String) commandMap.get("dev");

		AES256 aes = null;

		try {
			if (dev.equals("Y")) {
				aes = new AES256("LGU+DEV258010247");
				System.out.println("개발 복호화");
			} else {
				aes = new AES256("LGU+210987654321");
				System.out.println("운영 복호화");
			}

			System.out.println("복호화 전 :::" + st);
			st = aes.decryptBase64String(st);
			System.out.println("복호화 후 :::" + st);
		} catch (Exception e) {
			e.getMessage();
		}

		return st;
	}

	@ResponseBody
	@RequestMapping(value = "/receipt/encrypt.do")
	public Object encrypt(CommandMap commandMap) {
		String st = (String) commandMap.get("st");
		String dev = (String) commandMap.get("dev");

		AES256 aes = null;
		try {
			if (dev.equals("Y")) {
				aes = new AES256("LGU+DEV258010247");
			} else {
				aes = new AES256("LGU+210987654321");
			}
			System.out.println("암호화 전 :::" + st);
			st = aes.encryptStringToBase64(st);
			System.out.println("암호화 후 :::" + st);

		} catch (Exception e) {
			e.getMessage();
		}

		return st;
	}

}