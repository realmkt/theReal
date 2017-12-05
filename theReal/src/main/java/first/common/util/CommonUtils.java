package first.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class CommonUtils {
	private static final Logger log = Logger.getLogger(CommonUtils.class);

	public static boolean isEmpty(Object s) {
		if (s == null) {
			return true;
		}
		if ((s instanceof String) && (((String) s).trim().length() == 0)) {
			return true;
		}
		if (s instanceof Map) {
			return ((Map<?, ?>) s).isEmpty();
		}
		if (s instanceof List) {
			return ((List<?>) s).isEmpty();
		}
		if (s instanceof Object[]) {
			return (((Object[]) s).length == 0);
		}
		return false;
	}

	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static void printMap(Map<String, Object> map) {
		Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
		Entry<String, Object> entry = null;
		log.debug("--------------------printMap--------------------\n");
		while (iterator.hasNext()) {
			entry = iterator.next();
			log.debug("key : " + entry.getKey() + ",\tvalue : " + entry.getValue());
		}
		log.debug("");
		log.debug("------------------------------------------------\n");
	}

	public static void printList(List<Map<String, Object>> list) {
		Iterator<Entry<String, Object>> iterator = null;
		Entry<String, Object> entry = null;
		log.debug("--------------------printList--------------------\n");
		int listSize = list.size();
		for (int i = 0; i < listSize; i++) {
			log.debug("list index : " + i);
			iterator = list.get(i).entrySet().iterator();
			while (iterator.hasNext()) {
				entry = iterator.next();
				log.debug("key : " + entry.getKey() + ",\tvalue : " + entry.getValue());
			}
			log.debug("\n");
		}
		log.debug("------------------------------------------------\n");
	}

	public static String requestPosToRealData(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		String str;
		String jsonResData = "";
		StringBuffer paramData = new StringBuffer();

		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));

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
				System.out.println("@@str@@:" + str);
				System.out.println("@@paramData22@@:" + paramData);
			}
			// 성공시
			jsonResData += "{";
			jsonResData += "    \"result\":\"PI000\",";
			jsonResData += "    \"message\":\"전송성공\"";
			jsonResData += "}";
			System.out.println("jsonResData:" + jsonResData);
		} catch (IOException e) {
			// 실패시
			jsonResData += "{";
			jsonResData += "    \"result\":\"PI101\",";
			jsonResData += "    \"message\":\"수신된 데이터 빈값 입니다.\"";
			jsonResData += "}";
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

		return jsonResData;
	}

	public static boolean ipChk() {
		String serverIp = "117.52.97.40";
		String serverIp2 = "182.162.84.177";
		InetAddress Address;
		try {
			Address = InetAddress.getLocalHost();
			String IP = Address.getHostAddress();
			System.out.println("ip1 -  "+IP);
			if (serverIp.equals(IP)) {
				System.out.println("ip2 -  "+IP);
				return true;
			} else {
				if (serverIp2.equals(IP)) {
					return true;
				} else {
					return false;
				}
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

	public static String smsAppCoDicParse(String divAppCo) {
		String divAppCoCd = "";
		if (divAppCo.contains("CGV") || divAppCo.contains("롯데시네마") || divAppCo.contains("메가박스")
				|| divAppCo.contains("아트홀")) {
			divAppCoCd = "A01";
		} else if (divAppCo.contains("헤어") || divAppCo.contains("네일아트") || divAppCo.contains("타투")
				|| divAppCo.contains("이발소") || divAppCo.contains("미용실") || divAppCo.contains("블루클럽")
				|| divAppCo.contains("뷰티") || divAppCo.contains("살롱") || divAppCo.contains("HAIR")) {
			divAppCoCd = "B01";
		} else if (divAppCo.contains("스타벅스") || divAppCo.contains("탐앤탐스") || divAppCo.contains("엔젤리너스")
				|| divAppCo.contains("이디아") || divAppCo.contains("달콤") || divAppCo.contains("커피빈")
				|| divAppCo.contains("커핀그루나루") || divAppCo.contains("카페베네") || divAppCo.contains("투썸")
				|| divAppCo.contains("커피에반하다") || divAppCo.contains("빠리바게트") || divAppCo.contains("뚜레주르")
				|| divAppCo.contains("베이커리") || divAppCo.contains("커피") || divAppCo.contains("카페")) {
			divAppCoCd = "C01";
		} else if (divAppCo.contains("GS칼텍스") || divAppCo.contains("SK주유소") || divAppCo.contains("오일뱅크")
				|| divAppCo.contains("E1") || divAppCo.contains("LPG") || divAppCo.contains("주유")) {
			divAppCoCd = "E01";
		} else if (divAppCo.contains("냉면") || divAppCo.contains("부대찌개") || divAppCo.contains("순대국")
				|| divAppCo.contains("피자") || divAppCo.contains("짬뽕") || divAppCo.contains("짜장면")
				|| divAppCo.contains("자장면") || divAppCo.contains("떡볶이") || divAppCo.contains("김밥")
				|| divAppCo.contains("찜닭") || divAppCo.contains("국수") || divAppCo.contains("만두")
				|| divAppCo.contains("돈까스") || divAppCo.contains("초밥") || divAppCo.contains("스시")
				|| divAppCo.contains("짜장면") || divAppCo.contains("파스타") || divAppCo.contains("치킨")
				|| divAppCo.contains("호프") || divAppCo.contains("곱창") || divAppCo.contains("닭발")
				|| divAppCo.contains("짜장면") || divAppCo.contains("쭈꾸미") || divAppCo.contains("갈비")
				|| divAppCo.contains("삼겹살") || divAppCo.contains("족발") || divAppCo.contains("보쌈")) {
			divAppCoCd = "E02";
		} else if (divAppCo.contains("베니건스") || divAppCo.contains("아웃백") || divAppCo.contains("TGI")
				|| divAppCo.contains("드마리스") || divAppCo.contains("VIPS") || divAppCo.contains("빕스")
				|| divAppCo.contains("애슐리") || divAppCo.contains("블랙스미스") || divAppCo.contains("세븐스프링스")
				|| divAppCo.contains("마이어스") || divAppCo.contains("샤브향") || divAppCo.contains("마루샤브")
				|| divAppCo.contains("스시앤그릴") || divAppCo.contains("바이킹스") || divAppCo.contains("보노보노")
				|| divAppCo.contains("메드포갈릭")) {
			divAppCoCd = "E02";
		} else if (divAppCo.contains("세븐일레븐") || divAppCo.contains("GS25") || divAppCo.contains("미니스톱")
				|| divAppCo.contains("롯데마트") || divAppCo.contains("이마트") || divAppCo.contains("편의점")
				|| divAppCo.contains("마트") || divAppCo.contains("코스트코") || divAppCo.contains("홈플러스")
				|| divAppCo.contains("CU") || divAppCo.contains("씨유") || divAppCo.contains("위드미")
				|| divAppCo.contains("바이더웨이")) {
			divAppCoCd = "M01";
		} else if (divAppCo.contains("롯데백화점") || divAppCo.contains("신세계") || divAppCo.contains("2002아울렛")
				|| divAppCo.contains("현대아울렛") || divAppCo.contains("라붐아울렛") || divAppCo.contains("뉴코어아울렛")
				|| divAppCo.contains("마리오아울렛") || divAppCo.contains("이케아") || divAppCo.contains("타임스퀘어")
				|| divAppCo.contains("마리오아울렛")) {
			divAppCoCd = "S01";
		} else if (divAppCo.contains("약국") || divAppCo.contains("병원")) {
			divAppCoCd = "M02";
		} else if (divAppCo.contains("교통") || divAppCo.contains("택시")) {
			divAppCoCd = "T01";
		} else if (divAppCo.contains("SK텔레콤") || divAppCo.contains("유플러스")) {
			divAppCoCd = "T02";
		} else {
			divAppCoCd = "ETC";
		}
		return divAppCoCd;
	}

}
