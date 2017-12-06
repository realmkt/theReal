package first.sample.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import first.common.common.SendMailTest;
import first.common.filter.SmsParse;
import first.common.util.AES256;
import first.common.util.CharTokenizer;
import first.common.util.CommonUtils;
import first.common.util.GoogleChartDTO;
import first.common.util.JsonParser;
import first.common.util.Sha256;
import first.common.util.Var;
import first.common.util.consoleMail;
import first.sample.service.ReceiptService;
import first.sample.theRealShop.TheRealShopToUplus;


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
	// 금액 3자리 단위로 ,찍어주는 메서드
	// -----------------------------------------------------------------------
	static public String replaceComma(int value) {
		String result = "";
		String val = String.valueOf(value);
		String temp = new StringBuffer(val).reverse().toString();
		for (int i = 0; i < temp.length(); i += 3) {
			if (i + 3 < temp.length()) {
				result += temp.substring(i, i + 3) + ",";
			} else {
				result += temp.substring(i);
			}
		}
		val = new StringBuffer(result).reverse().toString();

		return val;
	}
	// -----------------------------------------------------------------------
	// SMS 금액인지 아닌지 구분
	// -----------------------------------------------------------------------
	public static boolean isAmount(String st){
		boolean flag = true;
		if(st.contains("원")){
			if(!(st.contains("누적") || st.contains("잔액"))){
				try {
					st = st.replace("원", "").replace(",", "");
					int integ = Integer.parseInt(st);
					flag =  true;
				} catch (Exception e) {
					flag = false;
				}
			}else{
			flag=false;
			}
		}else{
			flag = false;
		}
		return flag;
	}

	/*
	 * 샘플 게시판 작성 화면
	 */
	@RequestMapping(value = "/receipt/openBoardWrite.do")
	public ModelAndView openBoardWrite(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/sample/boardWrite");

		return mv;
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
	/*@RequestMapping(value = "/receipt/sendMail.do")
	public ModelAndView E01SendMail(CommandMap commandMap, HttpServletRequest request) throws Exception {
		String date = (String)commandMap.get("dealDate");
		System.out.println("DATE::::::::::::::"+date);
		SendMailTest.sendMail(request, date);
		ModelAndView mv = new ModelAndView("/E/E01");
		return mv;
	}*/
	
	
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
		String date = (String)commandMap.get("date");
		date = date.substring(0, 4)+"-"+date.substring(4);
		String telNo = (String)request.getParameter("telNo");
		
		System.out.println("DATE::::::::::::::"+request.getParameter("telNo")+date);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("date", date);		
		map.put("telNo", telNo);
		String eMailChk = receiptService.eMailChk(telNo);
		
		HashMap<String, Object> resultMap = receiptService.monthAllDate(map);
		
		System.out.println("■□■□■□■□■□■□■□■□■□result : " + resultMap);
		
		ArrayList list = (ArrayList) resultMap.get("resultMap");
		System.out.println("■□■□■□■□■□■□■□■□■□list : " + list);
		System.out.println("■□■□■□■□■□■□■□■□■□result size : " + list.size());
		
		if(list.size()>0){
			//SendMailTest.monthAllSendMail(list, date, eMailChk);
			
				System.out.println("■■■■■■■■■file 접근■■■■■■■■");
				File file = new File("../test.xlsx");
				
				System.out.println("■■■■■■■■■File pass■■■■■■■■");
				
				
				org.apache.poi.xssf.usermodel.XSSFWorkbook wb = new org.apache.poi.xssf.usermodel.XSSFWorkbook();
				
				
				System.out.println("■■■■■■■■■Workbook pass ■■■■■■■■");
				
				org.apache.poi.xssf.usermodel.XSSFSheet sheet = wb.createSheet((String)date);
				
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
					
					Map map2 = (Map)list.get(i-1);
					System.out.println(i + " ----" + map2);
					cell = row.createCell(0);
					cell.setCellValue(i);
					
					cell = row.createCell(1);
					String dateTime = ((Date)map2.get("FRT_CREA_DTM")).toString();
					cell.setCellValue(dateTime.substring(0, dateTime.length()-2));
					cell = row.createCell(2);
					cell.setCellValue((String)map2.get("APP_CO"));
					cell = row.createCell(3);
					cell.setCellValue((String)map2.get("CARD_APP_DIV"));
					cell = row.createCell(4);
					cell.setCellValue((String)map2.get("CARD_APP_CO"));
					cell = row.createCell(5);
					cell.setCellValue((String)map2.get("ACC_SUM_AMMOUNT"));
					cell = row.createCell(6);
					cell.setCellValue((String)map2.get("INST_DIV"));
					cell = row.createCell(7);
					cell.setCellValue((String)map2.get("APP_AMOUNT"));
				}
				
				System.out.println("■■■■■■■■■XSSFRow/XSSFCell Insert pass ■■■■■■■■");
				
				FileOutputStream fos = new FileOutputStream(file);
				
				System.out.println("■■■■■■■■■FileOutputStream pass■■■■■■■■");
				wb.write(fos);
				System.out.println("■■■■■■■■■Write pass ■■■■■■■■");
				fos.close();
				
			
			
			json.put("msg", "이메일 전송 확인");
		}else{
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
	@RequestMapping(value = "/receipt/insertReceiptData.do")
	@ResponseBody
	// public ModelAndView insertReceiptData(CommandMap commandMap,
	// HttpServletRequest request) throws Exception{
	public JSONObject insertReceiptData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String uplusUserKey =  "";
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

			Var var = JsonParser.object(new CharTokenizer(paramData.toString()));
			String telNo = "";
			
			HashMap<String, Object> detailMap = new HashMap<String, Object>();
			//System.out.println("@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#");
			//System.out.println("@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#"+var.find("salesInfo.salesType").toString());
			/**
			 * 회원이메일을 가져오기 위한 로직 추가
			 */
			log.debug("cancle resultMap :: "+var.toString());
			
			if( var.find("salesInfo.salesType").toString().equals("RCP02")){
				log.debug("====================================================");
				log.debug("■■■■■■■■■■■■■■■■■RCP02 전자영수증 취소건■■■■■■■■■■■■■■■■■■");
				String eMailChk = receiptService.eMailChk(telNo);
				HashMap<String,Object> cancleMap = new HashMap<String,Object>();
				
				log.debug(var);
				cancleMap.put("originRecNo", var.find("salesInfo.originRecNo").toString());
				cancleMap.put("shopBizNo", var.find("shopInfo.bizNo").toString());
				//cancleMap.put("businessNumber", var.find("shopInfo.bizNo").toString());
				
				Map<String,Object> resultMap = new HashMap<String,Object>();
				resultMap = receiptService.cancleReceipt(cancleMap);
				telNo = (String)resultMap.get("USER_KEY");
				uplusUserKey = (String)resultMap.get("UPLUS_USER_KEY");
				
				System.out.println("가져왔는가~" + resultMap);
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
				resultMap.put("salesBarCode",var.find("salesInfo.salesBarCode").toString());
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
				resultMap.put("recNO",var.find("salesInfo.recNO").toString());
				resultMap.put("originRecNo",var.find("salesInfo.originRecNo").toString());
				resultMap.put("originSalesDate",var.find("salesInfo.originSalesDate").toString());
				try {
					receiptService.insertReceiptData((HashMap) resultMap);
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
					
					temp.put("uplusUserKey", uplusUserKey);
					temp.put("shopBizNo", temp.get("SHOP_BIZNO"));
					temp.put("salesBarCode", temp.get("SALES_BARCODE"));
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
			}else{
				log.debug("====================================================");
				log.debug("■■■■■■■■■■■■■■■■■RCP01 전자영수증 승인건■■■■■■■■■■■■■■■■■■");
			
			telNo = var.find("userKey").toString();
			uplusUserKey= receiptService.uPlusChk(telNo);
			
			
				 
			if(!(telNo == "" || telNo == null)){
				
				String eMailChk = receiptService.eMailChk(telNo);
			
			log.debug(eMailChk);
			log.debug("eMailChk:" + eMailChk + "END");
			if ("".equals(eMailChk) || eMailChk == null) {
				jsonResData = "{";
				jsonResData += "    \"result\":\"PI602\",";
				jsonResData += "    \"message\":\"고객식별번호값(userKey value)과 매칭되는 사용자를 찾지 못했습니다.\"";
				jsonResData += "}";

				log.debug("@@@@@@@@@@@@11test11@@@@@@@@@@@@@@@@@@@");
				log.debug("@@@@@@@@@@@@11test11@@@@@@@@@@@@@@@@@@@");
			} else {
				String getpushKey = receiptService.pushChk(eMailChk);
				Sender sender = new Sender("AIzaSyDraPQzpXKPRvc_mWGzkppQbrYDRYu0UjM"); // 서버
																						// API
																						// Key
																						// 입력
				String regId = getpushKey;
				String sendTlt = "더리얼 마케팅";
				String sendMsg = "새로운 전자영수증메시지가 수신되었습니다.";
				Message message = new Message.Builder().addData("title", sendTlt).addData("message", sendMsg).delayWhileIdle(false).build();

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
			if(userKey.length() == 10){
				userKey = userKey.substring(0,3) + "0" + userKey.substring(3);
			}
			map.put("userKey", userKey);
			/*if(var.find("userKey").toString().equals("") || var.find("userKey").toString() == null){
				map.put("userKey","");
			}else{
			map.put("userKey", var.find("userKey").toString());
			
			}*/
			
			
			map.put("uplusUserKey", uplusUserKey);
			map.put("eMail", eMailChk);

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
			
			/*log.debug("========================redNO 테스트 ================ ");
			log.debug( var.get("salesInfo.recNO").);
			if(var.find("salesInfo.recNO") == null){
				log.debug("if 접근 %%");
				map.put("originSalesDate","");
				map.put("recNO", "");
				map.put("originRecNo", "");
			}else{
				log.debug("else 접근 %%");
			map.put("originSalesDate", var.find("salesInfo.originSalesDate").toString());
			map.put("recNO", var.find("salesInfo.recNO").toString());
			map.put("originRecNo", var.find("salesInfo.originRecNo").toString());
			}*/
			
			if (var.find("cardInfo").size() > 0 && var.find("cashInfo").size() > 0) {
				map.put("cardAmt", var.find("cardInfo.cardAmt").toString());
				map.put("cardInstallment", var.find("cardInfo.cardInstallment").toString());
				map.put("cardAppNo", var.find("cardInfo.cardAppNo").toString());
				map.put("cardDate", var.find("cardInfo.cardDate").toString());
				map.put("cardICom", "");
				map.put("cardPCom", "");
				if(var.find("cardInfo.cardICom").toString().contains("L포인트")){
					if(var.find("cardInfo.cardICom").toString().contains("사용")){
						map.put("pointCard", "L.POINT 사용");
					}else{
						map.put("pointCard", "L.POINT 적립");
					}
				}
				map.put("cardNo", var.find("cardInfo.cardNo").toString());

				map.put("cashAmt", var.find("cashInfo.cashAmt").toString());
				map.put("cashType", var.find("cashInfo.cashType").toString());
				map.put("cashNo", var.find("cashInfo.cashNo").toString());
				map.put("cashAppNo", var.find("cashInfo.cashAppNo").toString());
				map.put("cashDate", var.find("cashInfo.cashDate").toString());

			} else if (var.find("cashInfo").size() > 0) {
				map.put("cashAmt", var.find("cashInfo.cashAmt").toString());
				map.put("cashType", var.find("cashInfo.cashType").toString());
				map.put("cashNo", var.find("cashInfo.cashNo").toString());
				map.put("cashAppNo", var.find("cashInfo.cashAppNo").toString());
				map.put("cashDate", var.find("cashInfo.cashDate").toString());

				map.put("cardAmt", "");
				map.put("cardInstallment", "");
				map.put("cardAppNo", "");
				map.put("cardICom", "");
				map.put("cardPCom", "");
				map.put("cardNo", "");
			} else if (var.find("cardInfo").size() > 0) {
				map.put("cardAmt", var.find("cardInfo.cardAmt").toString());
				map.put("cardInstallment", var.find("cardInfo.cardInstallment").toString());
				map.put("cardAppNo", var.find("cardInfo.cardAppNo").toString());
				map.put("cardDate", var.find("cardInfo.cardDate").toString());
				map.put("cardICom", "");
				map.put("cardPCom", "");
				if(var.find("cardInfo.cardICom").toString().contains("L포인트")){
					if(var.find("cardInfo.cardICom").toString().contains("사용")){
						map.put("pointCard", "L.POINT 사용");
					}else{
						map.put("pointCard", "L.POINT 적립");
					}
				}else{
				map.put("cardICom", var.find("cardInfo.cardICom").toString());
				map.put("cardPCom", var.find("cardInfo.cardPCom").toString());
				}
				map.put("cardNo", var.find("cardInfo.cardNo").toString());

				map.put("cashAmt", "");
				map.put("cashType", "");
				map.put("cashNo", "");
				map.put("cashAppNo", "");
				map.put("cashDate", "");
			}

			map.put("pointAmt", var.find("salesInfo.pointamt").toString());
			map.put("getPoint", var.find("customerInfo.getPoint").toString());
			map.put("customerCode", var.find("customerInfo.customerCode").toString());
			map.put("customerPoint", var.find("customerInfo.customerPoint").toString());

			System.out.println("@@@" + map);
			receiptService.insertReceiptData(map);
			
			
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

			
			if (uplusUserKey != null) {
				// resStr += " \"userKey\": \""+randomSetValue+"\"";
				String uplusSendStr = "{";
				uplusSendStr += "\"userKey\": \"" + uplusUserKey + "\"";
				uplusSendStr += "\"erecNo\": \"" + var.find("salesInfo.salesBarCode").toString() + "\"";
				if ("uplus".equals(var.find("etcInfo.connectionCom").toString())) {
					uplusSendStr += "\"uplusUseCd\": \"1\"";
				} else {
					uplusSendStr += "\"uplusUseCd\": \"0\"";
				}
				uplusSendStr += "\"shopInfo\": {															";
				uplusSendStr += "	\"name\": \"" + var.find("shopInfo.name").toString() + "\"";
				uplusSendStr += "},                                                                         ";
				uplusSendStr += "\"salesInfo\": {                                                            ";
				uplusSendStr += "	\"salesDate\": \"" + var.find("salesInfo.salesDate").toString() + "\"";
				uplusSendStr += "	\"paidAmt\": \"" + var.find("salesInfo.paidAmt").toString() + "\"";
				uplusSendStr += "}                                                                          ";
				System.out.println("uplusSendStr:" + uplusSendStr);

			}
			}else{
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
			
			
			
			map.put("REC_TEN_NO", recTelNo);
			map.put("SEND_TEN_NO", sendTelNo);
			
			String sms = var.find("message").toString();
			System.out.println("sms - "+ sms);
			HashMap<String, Object> insertMap = SmsParse.smsCardParse(sendTelNo, recTelNo, sms);
			if(!insertMap.get("REC_TEN_NO").equals("") || insertMap.get("REC_TEN_NO")!= null){
				receiptService.insertSmsData(insertMap);
			}else{
				insertMap = null;
			}
				
			if(insertMap != null || insertMap.get("REC_TEN_NO")!= null){
				jsonResData = "{";
				jsonResData += "    \"result\":\"PI100\",";
				jsonResData += "    \"message\":\"전송 성공.\"";
				jsonResData += "}";
			}else{
				jsonResData = "{";
				jsonResData += "    \"result\":\"PI101\",";
				jsonResData += "    \"message\":\"전송 실패.\"";
				jsonResData += "}";
			}
		} catch (IOException e) {
			// 실패시
			jsonResData = "{";
			jsonResData += "    \"result\":\"PI101\",";
			jsonResData += "    \"message\":\"수신된 데이터 빈값 입니다. - "+e+"\"";
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
		map.put("telNo", commandMap.get("telNo"));

		Map<String, Object> resultMap = receiptService.startCurData(map);
		resultMap.put("code", "OK");

		return resultMap;
	}

	@RequestMapping(value = "/receipt/startUserData.do")
	@ResponseBody
	public Object startUserData(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("telNo", commandMap.get("telNo"));

		Map<String, Object> resultMap = receiptService.startUserData(map);
		resultMap.put("code", "OK");

		return resultMap;
	}

	@RequestMapping(value = "/receipt/startSumData.do")
	@ResponseBody
	public Object startSumData(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("telNo", commandMap.get("telNo"));
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
		map.put("telNo", commandMap.get("telNo"));
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

	@RequestMapping(value = "/receipt/latestData.do")
	@ResponseBody
	public Object latestData(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("telNo", commandMap.get("telNo"));
		System.out.println("dateSrchCd" + commandMap.get("dateSrchCd"));
		System.out.println("startDay" + commandMap.get("startDay"));
		System.out.println("endDay" + commandMap.get("endDay"));
		System.out.println("payDivCd" + commandMap.get("payDivCd"));
		System.out.println("orderByCd" + commandMap.get("orderByCd"));
		System.out.println("dateDivCd" + commandMap.get("dateDivCd"));
		System.out.println("smsDivCd" + commandMap.get("smsDivCd"));
		System.out.println("endSeq" + commandMap.get("endSeq"));
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
		System.out.println("--------------");
		map.put("startSeq", 0);
		map.put("endSeq", Integer.parseInt((String) commandMap.get("endSeq")));
		System.out.println("map" + map);
		Map<String, Object> resultMap = receiptService.latestData(map);

		
		System.out.println();
		map.put("endSeq", 2147483647);
		Map maxMap = receiptService.latestData(map);
		int maxSize = ((List)maxMap.get("resultMap")).size();
		resultMap.put("maxSize", maxSize);
		resultMap.put("code", "OK");
		System.out.println("프로그램이 끝났습니다@@@@@@@@@@@@@@@@@@@");
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
		
		System.out.println("#####################@@@@@@@###########"+commandMap.get("salesType"));
		
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

		String svcTxSeqno = (String) commandMap.get("smsSvcTxSeqno");
		String name = (String) commandMap.get("smsName");
		String birthday = (String) commandMap.get("smsBirthday");
		String sex = (String) commandMap.get("smsGender");
		String nation = (String) commandMap.get("smsNation");
		String telComCd = (String) commandMap.get("smsComCd");
		String mbphnNo = (String) commandMap.get("smsMbphnNo");
		String smsReSndYn = (String) commandMap.get("smsReSndYn");
		String rsv1 = (String) commandMap.get("smsRsv1");
		String rsv2 = (String) commandMap.get("smsRsv2");
		String rqstMsrCd = (String) commandMap.get("smsRqstMsrCd");
		String memId = (String) commandMap.get("smsMemId");
		String serverIp = (String) commandMap.get("smsServerIp");
		String siteUrl = (String) commandMap.get("smsSiteUrl");
		String siteDomain = (String) commandMap.get("smsSiteDomain");
		String endPointUrl = (String) commandMap.get("smsEndPointURL");
		String logPath = (String) commandMap.get("smsLogPath");
		String options = (String) commandMap.get("smsOption");
		String rqstCausCd = "10";

		log.debug("svcTxSeqno==" + (String) commandMap.get("smsSvcTxSeqno"));
		log.debug("name==" + (String) commandMap.get("smsName"));
		log.debug("birthday==" + (String) commandMap.get("smsBirthday"));
		log.debug("sex==" + (String) commandMap.get("smsGender"));
		log.debug("nation==" + (String) commandMap.get("smsNation"));
		log.debug("telComCd==" + (String) commandMap.get("smsComCd"));
		log.debug("mbphnNo==" + (String) commandMap.get("smsMbphnNo"));
		log.debug("smsReSndYn==" + (String) commandMap.get("smsReSndYn"));
		log.debug("rsv1==" + (String) commandMap.get("smsRsv1"));
		log.debug("rsv2==" + (String) commandMap.get("smsRsv2"));
		log.debug("rqstMsrCd==" + rqstCausCd);
		log.debug("serverIp==" + (String) commandMap.get("smsServerIp"));
		log.debug("smsRqstCausCd==" + (String) commandMap.get("smsRqstCausCd"));
		log.debug("memId==" + (String) commandMap.get("smsMemId"));
		log.debug("smsSiteUrl==" + (String) commandMap.get("smsSiteUrl"));
		log.debug("siteDomain==" + (String) commandMap.get("smsSiteDomain"));
		log.debug("endPointUrl==" + (String) commandMap.get("smsEndPointURL"));
		log.debug("logPath==" + (String) commandMap.get("smsLogPath"));
		log.debug("options==" + (String) commandMap.get("smsOption"));

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
	public int KcbVerification(HttpServletRequest request, HttpServletResponse response, CommandMap commandMap)
			throws IOException, ParseException {

		String svcTxSeqno = (String) commandMap.get("smsSvcTxSeqno");
		String mbphnNo = (String) commandMap.get("smsMbphnNo");
		String smsCertNo = (String) commandMap.get("smsCertNo");
		String memId = (String) commandMap.get("smsMemId");
		String serverIp = (String) commandMap.get("smsServerIp");
		String endPointUrl = (String) commandMap.get("smsEndPointURL");
		String logPath = (String) commandMap.get("smsLogPath");
		String options = (String) commandMap.get("smsOption");

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
		try {
			kcb.jni.Okname okname = new kcb.jni.Okname();
			ret = okname.exec(cmd, result);

			if (ret == 0) {// 성공일 경우 변수를 결과에서 얻음
				retcode = (String) result.get(0);
				log.debug("결과값 리스트0==" + (String) result.get(0));
				log.debug("결과값 리스트1==" + (String) result.get(1));
				log.debug("결과값 리스트2==" + (String) result.get(2));
				log.debug("결과값 리스트3==" + (String) result.get(3));
				log.debug("결과값 리스트4==" + (String) result.get(4));
				log.debug("결과값 리스트5==" + (String) result.get(5));
				log.debug("결과값 리스트5==" + (String) result.get(5));
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
		String eMail = (String) commandMap.getMap().get("eMail");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("startCurDate", startCurDate);
		map.put("endCurDate", endCurDate);
		map.put("eMail", eMail);

		Map<String, Object> resultMap = receiptService.houseHold(map);
		resultMap.put("code", "OK");

		return resultMap;
	}

	@RequestMapping(value = "/receipt/houseHoldDetail.do")
	@ResponseBody
	public Object houseHoldDetail(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		// String id = (String) session.getAttribute("id");
		String startDate = (String) commandMap.getMap().get("startDate");
		String telNo = (String) commandMap.getMap().get("telNo");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", startDate);
		map.put("telNo", telNo);

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
		log.debug("동 : "+dong);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dong", dong.replace(" ", ""));
		
		resultMap = receiptService.affliateData(map);
			
		double realLat = Double.valueOf((String)commandMap.get("lat"));
		double realLng = Double.valueOf((String)commandMap.get("lng"));
		double attLat  = 0;
		double attLng  = 0;
		
//		System.out.println("경위도 : "+realLat+"/"+realLng);
		
		List<Map<String,Object>> list = (List)resultMap.get("resultMap");
		int size = list.size();
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		
		System.out.println("list@@@  "+ list );
		
		for (int i = 0; i < size; i++) {
			Map<String,Object> tempMap = new HashMap<>();
			
			tempMap = list.get(i);
			attLat = Double.valueOf((String) tempMap.get("AFF_DETAIL_LAT"));
			attLng = Double.valueOf((String) tempMap.get("AFF_DETAIL_LNG"));
			
			String dist = distance(realLat, realLng, attLat, attLng);
			tempMap.put("distance", dist);
			
			resultList.add(tempMap);
		}
		
		for (int i = 0; i < size; i++) {
        	for (int j = i; j < size; j++) {
        		Map<String,Object> map1 = new HashMap<>();
				Map<String,Object> map2 = new HashMap<>();
				Map<String,Object> temp = new HashMap<>();
        		map1 = resultList.get(i);
        		map2 = resultList.get(j);
        		if(Integer.valueOf((String)map1.get("distance")) > Integer.valueOf((String)map2.get("distance"))){
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
			
			String distance = (String)temp.get("distance");
			
			if(distance.length() < 4){
	        	distance = distance + "m";
	        }else if(distance.length()==4){
	        	String meter = distance.substring(0,distance.length()-2);
	        	meter = meter.substring(meter.length()-1, meter.length());
	        	distance = distance.substring(0, distance.length()-3);
	        	distance = distance + "." + meter +"km";
	        }else{
	        	distance = distance.substring(0, distance.length()-3) + "km"; 
	        }
			
			temp.put("distance", distance);
			resultList.set(i, temp);
		}
		
		
		System.out.println("resultList : "+resultList);
		System.out.println("list size : "+size);
		return resultList;
	}
	
	//--------------------------------------------------------------
	//가맹점 디테일 정보 가져오기 
	//--------------------------------------------------------------
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/receipt/affDetail.do")
	@ResponseBody
	public Object affDetail(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		String affliateNo = (String) commandMap.getMap().get("affliateNo");
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("affliateNo", affliateNo);
		
		Map<String, Object> resultMap = receiptService.affDetail(map); 
		
		
		System.out.println("resultmap@@@   "+resultMap);
		

		JSONObject json = new JSONObject();
		
		json.put("lat", (String)resultMap.get("AFF_DETAIL_LAT"));
		json.put("lng", (String)resultMap.get("AFF_DETAIL_LNG"));
		
		json.put("affAddr", (String)resultMap.get("JIBUN_ADDR"));
		json.put("telNo", (String)resultMap.get("SHOP_TEL_NO"));
		json.put("affName", (String)resultMap.get("AFFLIATE_NM"));
		json.put("affExp", (String)resultMap.get("AFF_DETAIL_EXPLAIN"));

		String time = (String)resultMap.get("AFF_DETAIL_TIME");
		String timeType = null;
		System.out.println("ttttttttttttttttttttime  --  "+time );
		if(!time.isEmpty()){
			System.out.println("time 접근   ");
			if(time.matches(".*&.*")){
				timeType = "week";
				String weekday = time.substring(3,5) + ":" + time.substring(5,7) + " ~ " + time.substring(8,10) + ":" + time.substring(10,12);
				String weekend = time.substring(16,18) + ":" + time.substring(18,20) + " ~ " + time.substring(21,23) + ":" + time.substring(23,25);
				
				json.put("weekday",weekday);
				json.put("weekend",weekend);
			}else{
				timeType = "all";
				String dayTime = time.substring(3,5) + ":" + time.substring(5,7) + " ~ " + time.substring(8,10) + ":" + time.substring(10,12);
				json.put("dayTime",dayTime);
			}
			json.put("timeType", timeType);
		}
		
		
		String menu = (String)resultMap.get("AFF_DETAIL_MENU");
		String[] menuList = menu.split("/");
		int menuSize = menuList.length;
		
		
		List menuName = new ArrayList<>();
		List menuPrice = new ArrayList<>();
		
		for (int i = 0; i < menuSize; i++) {
			menuName.add(i, ((String)menuList[i]).substring(0, ((String)menuList[i]).indexOf("#")));
			int Price = Integer.parseInt(((String)menuList[i]).substring(((String)menuList[i]).indexOf("#")+1));
			menuPrice.add(i,replaceComma(Price));		
		}
		json.put("menuSize", menuSize);
		json.put("menuName", menuName);
		json.put("menuPrice", menuPrice);
		
		
		String image = (String)resultMap.get("AFF_DETAIL_IMAGE");
		String[] imageList = image.split("/");
		int imageSize = imageList.length;
		List imageArray = new ArrayList<>();
		for (int i = 0; i < imageSize; i++) {
			imageArray.add(i,imageList[i]);
		}
		json.put("imageSize", imageSize);
		json.put("image", imageArray);
		
		
		
		
		System.out.println(json);
		return json;
	}
	
 
	
	
	//--------------------------------------------------------------
	//가맹점 MAP
	//--------------------------------------------------------------
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/receipt/affMapView.do")
	@ResponseBody
	public Object affMapView(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		String affliateNo = (String) commandMap.getMap().get("affliateNo");
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("affliateNo", affliateNo);
		
		Map<String, Object> resultMap = receiptService.affDetail(map); 
		
		
		System.out.println("resultmap@@@   "+resultMap);
		

		JSONObject json = new JSONObject();
		
		json.put("lat", (String)resultMap.get("AFF_DETAIL_LAT"));
		json.put("lng", (String)resultMap.get("AFF_DETAIL_LNG"));
		
		json.put("affAddr", (String)resultMap.get("JIBUN_ADDR"));
		json.put("telNo", (String)resultMap.get("SHOP_TEL_NO"));
		json.put("affName", (String)resultMap.get("AFFLIATE_NM"));
		json.put("affExp", (String)resultMap.get("AFF_DETAIL_EXPLAIN"));

		
		System.out.println(json);
		return json;
	}
	
	
	

	private static String distance(double realLat, double realLng, double attLat, double attLng){  //두 좌표 거리구하기
		
		double theta = realLng - attLng;
		double dist = Math.sin(deg2rad(realLat)) * Math.sin(deg2rad(attLat)) + Math.cos(deg2rad(realLat)) * Math.cos(deg2rad(attLat)) * Math.cos(deg2rad(theta));
        
		dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
		 
        dist = dist * 1609.344;
        
        
        String distance = String.valueOf(dist);
        
        distance = distance.substring(0,distance.indexOf("."));
        
       /* if(distance.length() < 4){
        	distance = distance + "m";
        }else if(distance.length()==4){
        	String meter = distance.substring(0,distance.length()-2);
        	meter = meter.substring(meter.length()-1, meter.length());
        	distance = distance.substring(0, distance.length()-3);
        	distance = distance + "." + meter +"km";
        }else{
        	distance = distance.substring(0, distance.length()-3) + "km"; 
        }
        System.out.println(distance);*/
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
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("nowDay", nowDay);
		map.put("procDivCd", procDivCd);

		Map<String, Object> resultMap = receiptService.couponList(map);
		resultMap.put("code", "OK");

		return resultMap;
	}
	
	@RequestMapping(value = "/receipt/couponRead.do")
	@ResponseBody
	public Object couponRead(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		String seq = (String) commandMap.getMap().get("seq");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("seq", seq);

		receiptService.coucntAdd(map);
		
		Map<String, Object> couponDownMap = receiptService.couponDown(map);
		
		
		long downCnt = ((long)couponDownMap.get("CNT"));
		int totalCnt = Integer.parseInt((String)couponDownMap.get("COUPON_TOTAL"));
		
		
		
		Map<String, Object> resultMap = receiptService.couponRead(map);
		resultMap.put("code", "OK");
		
		if(totalCnt<=downCnt){
			resultMap.put("downCnt",true);
		}

		return resultMap;
	}
	
	@RequestMapping(value = "/receipt/couponAdd.do")
	@ResponseBody
	public Object couponAdd(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		String nowDay = (String) commandMap.getMap().get("nowDay");
		String seq = (String) commandMap.getMap().get("seq");
		String telNo = (String) commandMap.getMap().get("telNo");
		String uuId = (String) commandMap.getMap().get("uuId");
		
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("seq", seq);
		map.put("nowDay", nowDay);
		map.put("telNo", telNo);
		map.put("uuId", uuId);

		Map<String, Object> affReadMap = receiptService.couponRead(map);
		
		
		
		int valueInt = receiptService.valiCoupon(map);
		
		
		
		if(valueInt==0){
			Map<String, Object> couponDownMap = receiptService.couponDown(map);
			
			long downCnt = ((long)couponDownMap.get("CNT"));
			int totalCnt = Integer.parseInt((String)couponDownMap.get("COUPON_TOTAL"));
			
			if(totalCnt<=downCnt){
				valueInt=3;
			}else{
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
		String telNo = (String) commandMap.getMap().get("telNo");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("telNo",telNo);

		Map<String, Object> resultMap = receiptService.couponMyList(map);
		
		return resultMap;
	}
	
	
	@RequestMapping(value = "/receipt/couponDetail.do")
	@ResponseBody
	public Object couponDetail(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		String telNo = (String) commandMap.getMap().get("telNo");
		String seq = (String) commandMap.getMap().get("seq");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("telNo",telNo);
		map.put("seq",seq);

		Map<String, Object> resultMap = receiptService.couponDetail(map);
		
		
		
		return resultMap;
	}
	
	@RequestMapping(value = "/receipt/couponUsing.do")
	@ResponseBody
	public Object couponUsing(CommandMap commandMap, HttpSession session, ServletRequest request) throws Exception {
		String telNo = (String) commandMap.getMap().get("telNo");
		String seq = (String) commandMap.getMap().get("seq");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("telNo",telNo);
		map.put("seq",seq);

		receiptService.couponUsing(map);
		
		return 1;
	}
	
	
	
	
	
	//uplus 전자영수증 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
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
		
		AES256 aes = new AES256("LGU+210987654321");
		
		System.out.println("ipChk"+CommonUtils.ipChk()); 
		
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
		/*if(!var.find("userNm").toString().equals("real")){
			System.out.println("Test page - real   ");
			String enc = aes.encryptStringToBase64(var.toString());
			
			dec = aes.decryptBase64String(enc);
			
			System.out.println("Test page - real   " + dec);
		}else{
			*/
		
			System.out.print("dec test @@@");
			dec = aes.decryptBase64String(value);
			//dec = aes.decryptBase64String("Bgrr6Z+i6rUclIALVyNfw0pxjRKoNMjxJOnE3DhXFAUk/sccrOADR6Z9hX1UXnfxZ68xLMVGYWh4YduuNN+S/SvC0VzxzBNagMNmACZlXpZLjiVueSaqKZhCfIGvoBJlS4i6k+JN4bDOJngIshf3Rg/q+pOmmXr/HPjcr+J2uidDolyfqmm1z2GrmUmOGRan");
			System.out.println(dec);
		//}
		
		Var var2 = JsonParser.object(new CharTokenizer(dec));
		String telNo = var2.find("telNo").toString();
		String userNm = URLDecoder.decode(var2.find("userNm").toString() , "UTF-8"); 
		String userBirth = var2.find("userBirth").toString();  
		String sexDivCd = var2.find("sexDivCd").toString();
		String localDivCd = var2.find("localDivCd").toString();
		transactionId = var2.find("transactionId").toString();
		String userState = "01";
		
		System.out.println(telNo +"//" + userBirth);
		
		map.put("telNo", telNo);
		map.put("userNm", userNm);
		map.put("userBirth", userBirth);
		map.put("sexDivCd", sexDivCd);
		map.put("localDivCd", localDivCd);
		map.put("userState", userState);
		Integer upluJoinChk = receiptService.selectUplusJoinChk(map);
		Integer usedTelChk = receiptService.selectUsedTelChk(map);
		
		System.out.println("upluJoinChk:" + upluJoinChk);
		String randomSetValue = TheRealShopToUplus.randomSetValue();
		map.put("uplusUserKey", randomSetValue);
		
		
			
		
		//1:더리얼 기 가입자 0:신규가입자 
		if (upluJoinChk > 0) {
			//기존 사용하던 번호로 가입시 기존 데이터 백업
			/*if(usedTelChk>0){
					System.out.println("!!기존 사용하는 번호는 id로 변환합니다!!");
					String email = receiptService.selectEmail(map);
					String id = email.substring(0, email.indexOf("@")+1);
					System.out.println("@@ email : " + email +"\n @@ id    : " + id);
					map.put("id", id);
					
					receiptService.updateUsedData(map);
					
					System.out.println("!!기존 "+telNo+" 데이터는 "+ id +" 로 변경되었습니다!!");
				}else{
					System.out.println("!!더리얼샵 기존 가입자입니다. U+ userKey 입력 Process입니다!!");
					
					
				}*/
			
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
			//기존 사용하던 번호로 가입시 기존 데이터 삭제
				if(usedTelChk>0){
				//기존 번호의 유플러스 유저키 가져오기
				
				System.out.println("!!Uplus user key 백업 시작!!");
				map.put("originUplusUserKey", (String)receiptService.originUplusUserKey(map));
				map.put("userState", "05");
				receiptService.updateUplusData(map);
				
				System.out.println("!!Uplus user key 백업 완료!!");
				}
			
			receiptService.receiveUplusJoin(map);
			System.out.println("!!Uplus 회원가입이 완료되었습니다!!\n\n");
			
			
			resStr += "{";
			resStr += "	\"result\": \"UJ001\",";
			resStr += "	\"message\": \"회원 가입이 완료되었습니다.(더리얼 미 가입 회원)\",";
			resStr += "	\"userKey\": \"" + randomSetValue + "\"";
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
		try{
		System.out.println("@@1111paramData22@@: " + paramData);
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		AES256 aes = new AES256("LGU+210987654321");
		
		String value = paramData.toString();
		String dec = "";
		
		/*if(!var.find("userNm").toString().equals("real")){
			System.out.println("Test page - real   ");
			String enc = aes.encryptStringToBase64(var.toString());
			
			dec = aes.decryptBase64String(enc);
			
			System.out.println("Test page - real   " + dec);
		}else{
			*/
			System.out.print("dec test @@@");
			dec = aes.decryptBase64String(value);
			//dec = aes.decryptBase64String("Bgrr6Z+i6rUclIALVyNfw0pxjRKoNMjxJOnE3DhXFAUk/sccrOADR6Z9hX1UXnfxZ68xLMVGYWh4YduuNN+S/SvC0VzxzBNagMNmACZlXpZLjiVueSaqKZhCfIGvoBJlS4i6k+JN4bDOJngIshf3Rg/q+pOmmXr/HPjcr+J2uidDolyfqmm1z2GrmUmOGRan");
			System.out.println(dec);
		//}
		
		Var var2 = JsonParser.object(new CharTokenizer(dec));
		
		
		
		
		
		
		
		String telNo = var2.find("telNo").toString();
		String userKey = var2.find("userKey").toString();
		//String userKey = "U20171112164037899390";
		transactionId = var2.find("transactionId").toString();
		String userState = "02";

		map.put("telNo", telNo);
		map.put("originUplusUserKey", userKey);
		map.put("userState", userState);
		//Integer upluJoinChk = receiptService.selectUplusJoinChk(map);
		//Integer usedTelChk = receiptService.selectUsedTelChk(map);
		
		
			
		int resDrop = receiptService.updateUplusDrop(map);
		System.out.println(resDrop +" : 처리결과");
			if(resDrop>0){
				resStr += "{";
				resStr += "	\"result\": \"UJ100\",";
				resStr += "	\"message\": \"탈퇴처리가 정상적으로 되었습니다.\",";
				resStr += "	\"transactionId\": \"" + transactionId + "\"";
				resStr += "}";
			}else{
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
			
			System.out.println("error - "+e);
		}
	
		JSONObject jsonObject2 = null;
		JSONParser jsonParser2 = new JSONParser();
		
		
		System.out.println("resStrRESULT:" + resStr);
		jsonObject2 = (JSONObject) jsonParser2.parse(resStr);
		String enc = AES256.encrypt("LGU+210987654321", jsonObject2.toString());
		System.out.println("enc = " + enc);
		return enc;
			
		
	}
	
	// -----------------------------------------------------------------------
	// 전자영수증 메인페이지
	// -----------------------------------------------------------------------
	@RequestMapping(value = "/receipt/uplusReceipt.do")
	@ResponseBody
	public Object uplusRecipt(CommandMap commandMap,HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> resultMap = null;
		Map<String,Object> maxMap = null;
		int listSize =0;
		String str;
		String jsonResData = "";
		StringBuffer paramData = new StringBuffer();
		
		ModelAndView mv = new ModelAndView();
		
		AES256 aes = new AES256("LGU+210987654321");

		System.out.println("ipChk"+CommonUtils.ipChk()); 
		
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
				System.out.println(str);
				paramData.append(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		String resStr = "";
		String transactionId = "";
		try {
			
		System.out.println("@@paramData@@:" + paramData);
		
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		//String telNo ="2idwEtgYslaHJ+jwJF5+kA==";
		//String userKey = "LR5VTt5I2YBXFNyS5k/aPaY0JwooJI/3Y7tBtLKsjbQ=";
		String telNo = (String)commandMap.get("telNo");
		String userKey =  (String)commandMap.get("userKey");
		if(telNo.contains(" ")){
			System.out.println("수정");
			telNo = telNo.replaceAll(" ", "+");
		}
		if(userKey.contains(" ")){
			userKey = userKey.replaceAll(" ", "+");
		}
		System.out.println("parameter data - "+telNo +"      /         "+userKey);
		
		
		String decTelNo = aes.decryptBase64String(telNo);
		String decUserKey = aes.decryptBase64String(userKey);
		
		System.out.println("디코딩 결과 - " + decTelNo + "      /         " + decUserKey );
		
		map.put("smsDivCd", "ONLYREC");
		map.put("startSeq", 0);
		map.put("endSeq", 10);
		
		map.put("telNo", decTelNo);
		map.put("userKey", decUserKey);
		
		resultMap = receiptService.uplusReceiptData(map);
		List resultList = (List)resultMap.get("resultMap");
		
		listSize = resultList.size();
		/*int sumAmt = 0;
		for (int i = 0; i < resultList.size(); i++) {
			Map<String, Object> tempMap = (Map<String, Object>)resultList.get(i);
			sumAmt += (int)tempMap.get("TOTAL_AM");
		}
		System.out.println(sumAmt);*/
		
		System.out.println("size" + listSize);
		
		map.put("endSeq", 2147483647);
		maxMap = receiptService.uplusReceiptData(map);
		int maxSize = ((List)maxMap.get("resultMap")).size();
		
		mv.addObject("maxSize",maxSize);
		mv.addObject("telNo", telNo);
		mv.addObject("userKey", userKey);
		mv.addObject("decTelNo", decTelNo);
		mv.addObject("decUserKey", decUserKey);
		//mv.addObject("sumAmt", sumAmt);
		
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
	public Object uplusReciptReload(CommandMap commandMap,HttpSession session, ServletRequest request) throws Exception {
		Map<String,Object> resultMap = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		int listSize =0;
		String str;
		AES256 aes = new AES256("LGU+210987654321");
		
		JSONObject json = null;
		try {
			
		String receiptCnt = (String)commandMap.get("receiptCnt");
		String telNo = (String)commandMap.get("telNo");
		String userKey = (String)commandMap.get("userKey");
		//String decTelNo = aes.decryptBase64String(telNo);
		//String decUserKey = aes.decryptBase64String(userKey);
		String smsDivCd = (String)commandMap.get("smsDivCd");
		String startDate = (String)commandMap.get("startDate");
		String endDate = (String)commandMap.get("endDate");
		String payDivCd = (String)commandMap.get("payDivCd");
		String orderByCd = (String)commandMap.get("orderByCd");
		String dateDivCd = (String)commandMap.get("dateDivCd");
		String dateSrchCd = (String)commandMap.get("dateSrchCd");
		if(dateSrchCd.equals("SRCHY")){
			map.put("dateSrchCd", "SRCHY");
		}
		map.put("smsDivCd", "ONLYREC");
		map.put("startSeq", 0);
		map.put("endSeq", Integer.parseInt((String)commandMap.get("receiptCnt")));
		map.put("startDay", request.getParameter("startDate"));
		map.put("endDay",  request.getParameter("endDate"));
		map.put("payDivCd", commandMap.get("payDivCd"));
		map.put("orderByCd", commandMap.get("orderByCd"));
		map.put("startDate", commandMap.get("payDivCd"));
		map.put("dateDivCd", commandMap.get("dateDivCd"));

		map.put("telNo", telNo);
		map.put("userKey", userKey);
		
		
		resultMap = receiptService.uplusReceiptData(map);
		List resultList = (List)resultMap.get("resultMap");
		
		listSize = resultList.size();

		} catch (Exception e) {
			System.out.println(e);
		}
		
		return resultMap;
	}
	
	
	// -----------------------------------------------------------------------
	// 전자영수증 메인페이지
	// -----------------------------------------------------------------------
	
	@RequestMapping(value = "/receipt/uplusReceiptDetail.do")
	@ResponseBody
	public Object uplusReceiptDetail(CommandMap commandMap,HttpSession session, ServletRequest request) throws Exception {
		Map<String,Object> shopMap = null;
		Map<String,Object> resultMap = null;
		ModelAndView mv = new ModelAndView();
		HashMap<String, Object> map = new HashMap<String, Object>();
		int listSize =0;
		String str;
		
		JSONObject json = null;
		try {
			
		String telNo = (String)commandMap.get("telNo");
		String userKey = (String)commandMap.get("userKey");
		String barcode = (String)commandMap.get("barcode");
		String bizNo = (String)commandMap.get("bizNo");
		
		map.put("telNo", telNo);
		map.put("userKey", userKey);
		map.put("barcode",  barcode);
		map.put("bizNo", bizNo);
		
		shopMap = receiptService.getShopInfo(map);
		
		System.out.println(shopMap);
		map.put("salesType", shopMap.get("SALES_TYPE"));
		
		String date = (String)shopMap.get("SALES_DATE");
		date = date.substring(0, 4)+"-"+date.substring(4, 6)+"-"+date.substring(6, 8)+" "+date.substring(8, 10)+":"+date.substring(10, 12)+":"+date.substring(12, 14);
		mv.addObject("salesDate", date);
		mv.addObject("shopInfo", shopMap);
		
		resultMap = receiptService.uplusReceiptDetail(map);
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
			
	
	//진호씨 
	
	
	@SuppressWarnings("unchecked")
	   @RequestMapping(value = "/receipt/sendMail.do")
	   @ResponseBody
	   public void sendMail(@RequestParam(value="barcode[]", required=false) String[] barcode, HttpServletRequest request, String email, CommandMap commandMap) throws Exception{
		  Map<String, Object> userDataMap = new HashMap<String, Object>();
	      Map<String, Object> userDataDtailMap = new HashMap<String, Object>();
	      Map<String, Object> telMap = new HashMap();
	      /*
    	  telMap.put("telNo", (String)commandMap.get("telNo"));
	      Map<String,Object> userMap = receiptService.startUserData(telMap);
	      System.out.println(userMap);
	      telMap = (Map<String,Object>) userMap.get("reslutMap");
	      String userNm = (String)telMap.get("USER_NM");*/
	      List<String> list = new ArrayList<String>();
	      
	      Map<String, Object> result = new HashMap<>();
	      Map<String, Object> detailResult = new HashMap<>();
	      for (int i = 0; i < barcode.length; i++) {
	         list.add(barcode[i]);
	      }
	      System.out.println(list);
	      //data
	      result = receiptService.latestDataEmail(list);
	      System.out.println("result :: "+result);
	      ArrayList resultList= (ArrayList) result.get("resultMap");
	      /*//trans 증가
	      sampleService.transUp(list);*/
	      detailResult = receiptService.latestDataEmailDetail(list);
	      ArrayList<HashMap<String, Object>> resultDetailList= (ArrayList<HashMap<String, Object>>) detailResult.get("resultMap");
	      String[] body = new String[200];
	      String forResult = "";
	      for (int i = 0; i < resultList.size(); i++) {
	         userDataMap = (HashMap<String, Object>) resultList.get(i);
	      
	      //data 반복
	    
	      body[i]="<html>"
	            +"<body><table style='background: #f2f2f2; width: 690px; font-family: Dotum,; font-size:12px; color:#999999;'><tr><td><img src='http://117.52.97.40/theReal/images/top.jpg'></td>"
	              +"<tr><td></td></tr><tr><td align='center'><table style='background: #e5e5e5; width: 410px; border: 1px solid #ddd; margin: 50px;'></tr>"
	            +"<tr><td height='4px' style='background: #e5e5e5;'></td></tr><tr><td align='center'>"
	              +"<table style='background: #fff; width: 400px; border: 1px solid #ddd;'><tr><td><img src='http://117.52.97.40/theReal/images/paper_top.gif'></td></tr>"
	              +"<tr><td align='center' style='padding: 0px 30px 30px 30px;'><p></p><p></p><p style='font-size: 12px; color: gray; clear:both;' align='left'>Mobile Life Service</p>"
	              +"<div class='rt_txt01'style='text-align: left; font-size: 1em; color: #666; clear: both;'></div><div class='affli_div'style='text-align: center; font-size: 20px; padding: 13px 0px; color: #000; font-weight: 700;'>"+userDataMap.get("SHOP_NAME")+"</div><div></div><div></div>"
	              +"<div class='rt_txt01'style='text-align: left; font-size: 12px; color: gray; clear:both; padding: 0px 10px;'><div class='rt_txt01'style='text-align: left; font-size: 1em; color: #666; clear: both;'></div><br><span>대표자명 : "+userDataMap.get("SHOP_CEO")+"</span></div>"
	              +"<div class='rt_txt01'style='text-align: left; font-size: 12px; color: gray; clear:both; padding: 0px 10px;'><span>전화번호 : "+userDataMap.get("SHOP_TEL_NUM")+"</span></div>"
	              +"<div class='rt_txt01'style='text-align: left; font-size: 12px; color: gray; clear:both; padding: 0px 10px;'><span>사업자번호 : "+userDataMap.get("SHOP_BIZNO")+"</span></div>"
	              +"<div class='rt_txt01'style='text-align: left; font-size: 12px; color: gray;  clear:both; padding: 0px 10px;'>주소 : "+userDataMap.get("SHOP_ADDR")+"<span></span></div>"
	              +"<br><br><hr width='100%' style='border:1px dashed #cccccc'>"
	              +"<table width='100%' cellspacing='0' cellpadding='0'><tr style='border-bottom-width: 1px; border-bottom-style: dotted; border-bottom-color: #CCCCCC;'>"
	              +"<th style='13px gulim; padding:4px; color:#666; font-size: 12px;' align='left'>품명</th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='padding: 6px; color:#666; font-size: 12px;'>단가</th>"
	              +"<th style='13px gulim; color:#666;  padding:6px; font-size: 12px;'>수량</th><th style='13px gulim; color:#666;  padding:6px; font-size: 12px;'>금액</th></tr>";
	          
	          //detail반복   
	            for (int j = 0; j < resultDetailList.size(); j++) {
	               userDataDtailMap = (HashMap<String, Object>) resultDetailList.get(j);
	               if (userDataMap.get("SALES_BARCODE").equals(userDataDtailMap.get("SALES_BARCODE"))) {
	                  
	               
	            body[i] += "<tr><th style='padding:4px;  color:#666; font-size: 12px;' align='left'>"
	                  + userDataDtailMap.get("SALES_PNAME")
	                  + "</th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='padding: 6px; color:#666; font-size: 12px;'>"+replaceComma(Integer.parseInt((String)userDataDtailMap.get("SALES_PPRICE")))+"</th>"
	                  + "<th style='13px gulim;  padding:6px; color:#666; font-size: 12px;'>"+userDataDtailMap.get("SALES_QTY")+"</th><th style='13px gulim;  padding:6px; color:#666; font-size: 12px; font-size: 12px;'>"+replaceComma(Integer.parseInt((String)userDataDtailMap.get("SALES_FP_AMT")))+"</th></tr>";

	               }
	            }
	            System.out.println("eeeeeeeeeeee");
	            body[i]+= "<tr>"
	                
	              +"</table>"
	              +"<br><hr width='100%' style='border:1px dashed #cccccc'><table width='100%' cellspacing='0' cellpadding='0'><tr>"
	              +"<th style='color: black; padding: 6px; font-size: 17px' align='left' >과세물품가액</th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th>"
	              +"<th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: black; padding: 6px; font:bold 13px gulim; font-size: 17px'  >"+replaceComma(Integer.parseInt((String)userDataMap.get("SALES_SUM_FP_AMT")))+"</th></tr>"
	              +"<tr><th style='color: black; padding: 6px; font-size: 17px' align='left'>부가세</th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th>"
	              +"<th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th>"
	              +"<th style='color: black; padding: 6px; font:bold 13px gulim; font-size: 17px'>"+replaceComma(Integer.parseInt((String)userDataMap.get("SALES_SUM_TAX_AMT")))+"</th></tr>"
	              +"<tr><th style='color: black; padding: 6px; font:bold 18px gulim; font-size: 17px' align='left'>합계금액</th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th>"
	              +"<th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th>"
	            +"<th style='color: #5263bd; padding: 6px;'></th><th style='color: black; padding: 6px; font:bold 18px gulim; font-size: 17px'>"+replaceComma(Integer.parseInt((String)userDataMap.get("SALES_PAID_AMT")))+"</th></tr>"
	              +"<tr><th style='color: black; padding: 6px; font:bold 18px gulim; font-size: 17px' align='left'>반환금액</th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th>"
	            +"<th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th><th style='color: #5263bd; padding: 6px;'></th>"
	              +"<th style='color: #5263bd; padding: 6px;'></th><th style='color: black; padding: 6px; font:bold 18px gulim; font-size: 17px'>0</th></tr>"
	            +"</table><hr width='100%' style='border:1px dashed #cccccc'>"
	              +"<div class='rt_etc'style='text-align: left; font-size: 8px; font-weight: 700; color: #333; clear: both; padding: 10px; border-bottom: 1px dashed #aaa'><div class='rt_copy'style='text-align: center; font-size: 12px;'>"
	            +"***신용승인정보(고객용)***";
	            String cardIcom = (String) userDataMap.get("CARD_ICOM");
	            if(cardIcom==null){
	                 
	               body[i]+=
	                       "<div class='rt_txt01'style='text-align: left; font-size: 10px; color: #666; clear: both'>거래종류: 현금</div>";
	              }else{
	                    
	                 body[i]+=
	                       "<div class='rt_txt01'style='text-align: left; font-size: 10px; color: #666; clear: both'>거래종류: 카드</div>";
	              }
	              
	              body[i]+="<div class='rt_txt01'style='text-align: left; font-size: 10px; color: #666; clear: both'>거래일시:: "+userDataMap.get("FRT_CREA_DTM")+"</div></div>"
	            +"</div><div class='rt_copy'style='text-align: center; font-size: 12px;'>본 전자영수증은 거래의 참고용으로 사용하시기 바랍니다.</div>"
	              +"</td></tr></table></td></tr></table></td>"
	            +"</tr></table></body></html>";
	      forResult += body[i];
	      }
	      FileWriter fWriter = null;
	      BufferedWriter writer = null;
	      try{
	         fWriter = new FileWriter("../test -TheReal 이메일영수증.html");
	         writer = new BufferedWriter(fWriter);
	         writer.write(forResult);
	         writer.newLine(); // this is not actually needed for html files - can
	                        // make your code more readable though
	         writer.close(); // make sure you close the writer object
	      }catch(Exception e)
	      {
	    	  e.printStackTrace();
	      }
	      
	            String emailMsgTxt = "";                                    //내용
	             String emailSubjectTxt = "";                              //제목
	             String emailFromAddress = "rhkdhlgkrtod@naver.com"; //보내는 이 이메일주소
	             String[] emailList = {email}; 
	             
	            try {
	                  consoleMail smtpMailSender = new consoleMail();
	                  smtpMailSender.postMail(emailList, emailSubjectTxt, emailMsgTxt, emailFromAddress, "test");
	                  
	               } catch (Exception e) {
	            	   System.out.println(e);
	                  e.printStackTrace();
	               }
	      }
	
	
	
	
	
	
	
	

}

