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
		
		//A01
		
		String divAppCoCd = "";
		divAppCo = divAppCo.toLowerCase().replaceAll(" ", "");
		System.out.println("appCoDiv Test"  +  divAppCo );
		if (divAppCo.contains("키친") ||divAppCo.contains("푸드") ||divAppCo.contains("베니건스") || divAppCo.contains("아웃백") || divAppCo.contains("tgi") || divAppCo.contains("드마리스") || divAppCo.contains("vips") || divAppCo.contains("빕스") || divAppCo.contains("애슐리") || divAppCo.contains("블랙스미스") || divAppCo.contains("세븐스프링스") || divAppCo.contains("마이어스") || divAppCo.contains("샤브") || divAppCo.contains("바이킹스") || divAppCo.contains("보노보노") || divAppCo.contains("스테이크") || divAppCo.contains("하우스") || divAppCo.contains("라미니") || divAppCo.contains("버거") || divAppCo.contains("비케이알") || divAppCo.contains("템포돠르다토") || divAppCo.contains("외식") || divAppCo.contains("키친") || divAppCo.contains("케밥") || divAppCo.contains("플레이트비") || divAppCo.contains("함바그") || divAppCo.contains("함박") || divAppCo.contains("오아시스") || divAppCo.contains("뜨리앙") || divAppCo.contains("보나세라") || divAppCo.contains("뚜또베네") || divAppCo.contains("쉐이크쉑") || divAppCo.contains("맥도날드") || divAppCo.contains("롯데리아") || divAppCo.contains("kfc")  || divAppCo.contains("다운타우너") || divAppCo.contains("페어링룸") || divAppCo.contains("테이블") || divAppCo.contains("그라노") || divAppCo.contains("만지오네") || divAppCo.contains("오스테리아꼬또") || divAppCo.contains("르꽁뜨와") || divAppCo.contains("키친") || divAppCo.contains("그랑씨엘") || divAppCo.contains("리스토란테 에오") || divAppCo.contains("볼피노") || divAppCo.contains("h450") || divAppCo.contains("주옥") || divAppCo.contains(" 스테키") || divAppCo.contains("더플레이트") || divAppCo.contains("아우어다이닝") || divAppCo.contains("파스타") || divAppCo.contains("Chef") || divAppCo.contains("샤누리") || divAppCo.contains("남경") || divAppCo.contains("한솥") || divAppCo.contains("도시락") || divAppCo.contains("오봉") || divAppCo.contains("평가옥") || divAppCo.contains("불백") || divAppCo.contains("칼국수") ||
											divAppCo.contains("아쿠아리오") || divAppCo.contains("마누테라스") || divAppCo.contains("피자") || divAppCo.contains("샤이바나") || divAppCo.contains("배드파머스") || divAppCo.contains("가디록") || divAppCo.contains("보트르메종") || divAppCo.contains("치폴라로쏘") || divAppCo.contains("라페름") || divAppCo.contains("루트") || divAppCo.contains("케이크") || divAppCo.contains("pasto") || divAppCo.contains("루트") || divAppCo.contains("와하카") || divAppCo.contains("보테가로") || divAppCo.contains("블랙스톤") || divAppCo.contains("타코") || divAppCo.contains("매드포갈릭") || divAppCo.contains("카퍼룸") || divAppCo.contains("그릴") || divAppCo.contains("스파게티") || divAppCo.contains("파티오42") || divAppCo.contains("헥사곤") || divAppCo.contains("파파존스") || divAppCo.contains("샌드위치") || divAppCo.contains("일피아또") || divAppCo.contains("grill") || divAppCo.contains("중국") || divAppCo.contains("짜장면") || divAppCo.contains("짬뽕") || divAppCo.contains("자장면") || divAppCo.contains("도담그루") || divAppCo.contains("화이") || divAppCo.contains("동래원") || divAppCo.contains("대가방") || divAppCo.contains("몽중헌") || divAppCo.contains("딤섬") || divAppCo.contains("차이린") || divAppCo.contains("방점") || divAppCo.contains("중식") || divAppCo.contains("차이나레스토랑") || divAppCo.contains("대흥각") || divAppCo.contains("향원") || divAppCo.contains("중화") || divAppCo.contains("요리") || divAppCo.contains("탕수육") || divAppCo.contains("전가복") || divAppCo.contains("그랜드마오") || divAppCo.contains("연화산") || divAppCo.contains("서래향") || divAppCo.contains("수타") || divAppCo.contains("북경") || divAppCo.contains("냉면") || divAppCo.contains("찌개") || divAppCo.contains("김치") || divAppCo.contains("제육") || divAppCo.contains("볶음") || divAppCo.contains("라면") || divAppCo.contains("찜닭") || divAppCo.contains("감자탕") || divAppCo.contains("국밥") || divAppCo.contains("해물") || divAppCo.contains("해산물") || divAppCo.contains("한정식") || divAppCo.contains("한식") || divAppCo.contains("식당") || divAppCo.contains("새벽집") || divAppCo.contains("수담") || divAppCo.contains("한일관") || divAppCo.contains("영천영화") || divAppCo.contains("고기") || divAppCo.contains("등심") || divAppCo.contains("곱창") || divAppCo.contains("갈비") || divAppCo.contains("새우")  || divAppCo.contains("횟집") || divAppCo.contains("바다") || divAppCo.contains("가든") || divAppCo.contains("참치") || divAppCo.contains("스시") || divAppCo.contains("막창") || divAppCo.contains("돈가") || divAppCo.contains("족발") || divAppCo.contains("보쌈") || divAppCo.contains("장어") || divAppCo.contains("밥상") || divAppCo.contains("닭발") || divAppCo.contains("오돌뼈") || divAppCo.contains("쭈꾸미") || divAppCo.contains("삼겹살") || divAppCo.contains("초밥") || divAppCo.contains("라멘") || divAppCo.contains("일식") || divAppCo.contains("마코토") || divAppCo.contains("이치에") || divAppCo.contains("이자와") || divAppCo.contains("연어") || divAppCo.contains("돈가스") || divAppCo.contains("돈까스") || divAppCo.contains("샤브샤브")	 || divAppCo.contains("콰이") || divAppCo.contains("미소야") || divAppCo.contains("우아한형제") || divAppCo.contains("더스푼")  || divAppCo.contains("종가집")) {
			divAppCoCd = "001";//음식/식사
		} else if (divAppCo.contains("커피") || divAppCo.contains("카페") || divAppCo.contains("스타벅") || divAppCo.contains("스타벅스") || divAppCo.contains("탐앤탐스") || divAppCo.contains("엔젤리너스") || divAppCo.contains("이디아") || divAppCo.contains("이디야") || divAppCo.contains("달콤") || divAppCo.contains("커핀") || divAppCo.contains("커핀그루나루") || divAppCo.contains("투썸") || divAppCo.contains("오이사") || divAppCo.contains("524") || divAppCo.contains("바게트") || divAppCo.contains("뚜레주르") || divAppCo.contains("베이커리") || divAppCo.contains("café") || divAppCo.contains("coffee") || divAppCo.contains("cafe")  || divAppCo.contains("ediya")  || divAppCo.contains("포엠") || divAppCo.contains("시실리") || divAppCo.contains("르풀") || divAppCo.contains("가무") || divAppCo.contains("드롭탑") || divAppCo.contains("coin") || divAppCo.contains("토스마이슈") || divAppCo.contains("밀키비") || divAppCo.contains("오시정") || divAppCo.contains("빈스빈스") || divAppCo.contains("공차") || divAppCo.contains("아티제") || divAppCo.contains("오랑오랑") || divAppCo.contains("브라운하우스") || divAppCo.contains("폴바셋") || divAppCo.contains("아티제") || divAppCo.contains("엘리") || divAppCo.contains("델리카한스") || divAppCo.contains("쿼트블랙") || divAppCo.contains("나무사이로")  || divAppCo.contains("김병진과자점")  || divAppCo.contains("과자") || divAppCo.contains("웰빙쌀빵")) {
			divAppCoCd = "002";//카페/베이커리
		} else if (divAppCo.contains("포차") || divAppCo.contains("포장마차") || divAppCo.contains("이자카야") || divAppCo.contains("사케")  || divAppCo.contains("소주") || divAppCo.contains("맥주") || divAppCo.contains("선술집") || divAppCo.contains("호프") || divAppCo.contains("양주") || divAppCo.contains("보드카") || divAppCo.contains("위스키") || divAppCo.contains("칵테일") || divAppCo.contains("와인") || divAppCo.contains("야랑") || divAppCo.contains("청춘촌놈") || divAppCo.contains("주류") || divAppCo.contains("청담이상") || divAppCo.contains("반샤쿠") || divAppCo.contains("바오밥") || divAppCo.contains("대포") || divAppCo.contains("남오토코") || divAppCo.contains("요미") || divAppCo.contains("비어") || divAppCo.contains("주점") || divAppCo.contains("코젤다크하우스") || divAppCo.contains("달쪽") || divAppCo.contains("부엉이키친") || divAppCo.contains("브롱스") || divAppCo.contains("더핸드") || divAppCo.contains("티엘") || divAppCo.contains("라꾸긴") || divAppCo.contains("야시장") || divAppCo.contains("주캠야포") || divAppCo.contains("도란도란") || divAppCo.contains("이목구비") || divAppCo.contains("아맛나") || divAppCo.contains("투엔디") || divAppCo.contains("펍") || divAppCo.contains("pop") || divAppCo.contains(" 사이야") || divAppCo.contains("몽로") || divAppCo.contains("에도") || divAppCo.contains("남오토코") || divAppCo.contains("품앗이") || divAppCo.contains("행복한오타쿠") || divAppCo.contains("오렌지룸") || divAppCo.contains("무릉도원") || divAppCo.contains("히카리") || divAppCo.contains("다트엔젤스") || divAppCo.contains("군선생") || divAppCo.contains("달동네") || divAppCo.contains("시장통") || divAppCo.contains("청춘싸롱") || divAppCo.contains("쿠니") || divAppCo.contains("제이하우스") || divAppCo.contains("육회한연어") || divAppCo.contains("칭구칭구") || divAppCo.contains("주모리") || divAppCo.contains("주막") || divAppCo.contains("꾼") || divAppCo.contains("헤이판") || divAppCo.contains("토베") || divAppCo.contains("노래타운") || divAppCo.contains("야차이") || divAppCo.contains("지짐이") || divAppCo.contains("시나브로") || divAppCo.contains("온바") || divAppCo.contains("오꼬만") || divAppCo.contains("치킨철파니야") || divAppCo.contains("주경야술") || divAppCo.contains("해와달") || divAppCo.contains("팍스팍스") || divAppCo.contains("수리야") || divAppCo.contains("라이트70780") || divAppCo.contains("와라와라") || divAppCo.contains("칸꼬시") || divAppCo.contains("투투") || divAppCo.contains("사스가") || divAppCo.contains("또바기") || divAppCo.contains("모로미쿠시") || divAppCo.contains("쭈오사") || divAppCo.contains("노래방") || divAppCo.contains("클럽") || divAppCo.contains("club") || divAppCo.contains("감성주점") || divAppCo.contains("그린라이트") || divAppCo.contains("bar") || divAppCo.contains("치킨")  || divAppCo.contains("김밥")  || divAppCo.contains("통닭") || divAppCo.contains("산쪼메")  || divAppCo.contains("라멘")  || divAppCo.contains("육반장")) {
			divAppCoCd = "003";//술/유흥
		} else if (divAppCo.contains("쎄븐일레븐") || divAppCo.contains("세븐일레븐") || divAppCo.contains("gs25") || divAppCo.contains("미니스톱") || divAppCo.contains("롯데마트") || divAppCo.contains("마트") || divAppCo.contains("mart") || divAppCo.contains(" 편의점") || divAppCo.contains("bestall") || divAppCo.contains("코스트코") || divAppCo.contains("슈퍼") || divAppCo.contains("수퍼") || divAppCo.contains("홈플러스") || divAppCo.contains("CU") || divAppCo.contains("위드미") || divAppCo.contains("개그스토리") || divAppCo.contains("할인") || divAppCo.contains("매장") || (divAppCo.contains("마켓") && !(divAppCo.contains("지마켓") || divAppCo.contains("g마켓")))|| divAppCo.contains("비지에프리테일") || divAppCo.contains("BGF") || divAppCo.contains("씨유") || divAppCo.contains("ministop")) {
			divAppCoCd = "004";//마트/편의
		} else if (divAppCo.contains("헤어") || divAppCo.contains("네일") || divAppCo.contains("타투") || divAppCo.contains("tatto") || divAppCo.contains("헤나") || divAppCo.contains("hena") || divAppCo.contains("이발소") || divAppCo.contains("미용실") || divAppCo.contains("blueclub") || divAppCo.contains("블루클럽") || divAppCo.contains("뷰티") || divAppCo.contains("hair") || divAppCo.contains("살롱") || divAppCo.contains("애브뉴준오") || divAppCo.contains("본샵") || divAppCo.contains("파이벤에이치") || divAppCo.contains("순수") || divAppCo.contains("더풋샵") || divAppCo.contains("마사지") || divAppCo.contains("컬쳐앤네이처") || divAppCo.contains("비에라") || divAppCo.contains("왁싱") || divAppCo.contains("클레르몽드") || divAppCo.contains("로이드밤") || divAppCo.contains("스파") || divAppCo.contains("에이컨셉") || divAppCo.contains("끌림") || divAppCo.contains("알루") || divAppCo.contains("hair")) {
			divAppCoCd = "005";//뷰티/미용
		} else if (divAppCo.contains("다이소") || divAppCo.contains("프라그랑스") || divAppCo.contains("미니소") || divAppCo.contains("디자인") || divAppCo.contains("인테리어") || divAppCo.contains("주거") || divAppCo.contains("오누이작업실") || divAppCo.contains("하늘세상") || divAppCo.contains("DESIGN") || divAppCo.contains("design") || divAppCo.contains("인테리어") || divAppCo.contains("신진건재") || divAppCo.contains("건축") || divAppCo.contains("건물") || divAppCo.contains("아랑주") || divAppCo.contains("프라그랑스") || divAppCo.contains("에이랜드") || divAppCo.contains("옵티크") || divAppCo.contains("알앤택") || divAppCo.contains("납청유기") || divAppCo.contains("아이벨류") || divAppCo.contains("올리브리스") || divAppCo.contains("jaju") || divAppCo.contains("디지털팩토리") || divAppCo.contains("르크루제") || divAppCo.contains("스위스골드") || divAppCo.contains("가구") || divAppCo.contains("MUJI") || divAppCo.contains("도예") || divAppCo.contains("그릇") || divAppCo.contains("이노템") || divAppCo.contains("세미바이오") || divAppCo.contains("씨앰글로벌") || divAppCo.contains("엠비언트하우즈") || divAppCo.contains("로얄토토") || divAppCo.contains("쿠첸") || divAppCo.contains("쿠쿠") || divAppCo.contains("토탈그린실업") || divAppCo.contains("toto") || divAppCo.contains("이불")) {
			divAppCoCd = "006";//주거/생활용품
		} else if (divAppCo.contains("gs칼텍스") || divAppCo.contains("오일") || divAppCo.contains("oil") || divAppCo.contains("lpg") || divAppCo.contains("e1") || divAppCo.contains("주유") || divAppCo.contains("에너지") || divAppCo.contains("버스") || divAppCo.contains("지하철") || divAppCo.contains("전철") || divAppCo.contains("택시") || divAppCo.contains("철도") || divAppCo.contains("코레일") || divAppCo.contains("터미널")|| divAppCo.contains("렌트") || divAppCo.contains("ssancar") || divAppCo.contains("랜트") || divAppCo.contains("국민지앤엘") || divAppCo.contains("아마존카") || divAppCo.contains("카패리스") || divAppCo.contains("렌터") || divAppCo.contains("랜터") || divAppCo.contains("세영카") || divAppCo.contains("스마트스카이") || divAppCo.contains("에스카") || divAppCo.contains("오토리더스") || divAppCo.contains("카일이삼제스퍼") || divAppCo.contains("카맵코리아") || divAppCo.contains("리스") || divAppCo.contains("위즈카") || divAppCo.contains("오토글로리") || divAppCo.contains("쏘카") || divAppCo.contains("그린카") || divAppCo.contains("car") || divAppCo.contains("주차")) {
			divAppCoCd = "007";//교통/주유
		} else if (divAppCo.contains("통신") || divAppCo.contains("필트론") || divAppCo.contains("에이투스") || divAppCo.contains("금호테크원") || divAppCo.contains("지트") || (divAppCo.contains("kt") && !divAppCo.contains("스퀘어")) || divAppCo.contains("skt") || divAppCo.contains("대리점") || divAppCo.contains("올레") || divAppCo.contains("플래닛") || divAppCo.contains("모바일") || divAppCo.contains("테크") || divAppCo.contains("폰") || divAppCo.contains("네트웍스") || divAppCo.contains("네트워크") || divAppCo.contains("텔레콤")   || divAppCo.contains("솔루션") || divAppCo.contains("넷") || divAppCo.contains("olleh") || divAppCo.contains("raim") || divAppCo.contains("lgu") || divAppCo.contains("세이프존") || divAppCo.contains("쏘넷") || divAppCo.contains("무선") || divAppCo.contains("유선") || divAppCo.contains("맥스브로") || divAppCo.contains("서울E&T") || divAppCo.contains("디투씨") || divAppCo.contains("솔루션") || divAppCo.contains("빙글") || divAppCo.contains("픽스나우")|| divAppCo.contains("나모인터랙티브") || divAppCo.contains("케이블") || divAppCo.contains("일진씨투씨") || divAppCo.contains("푸리오") || divAppCo.contains("알테스") || divAppCo.contains("아이티") || divAppCo.contains("프라임넷") || divAppCo.contains("오엘텍") || divAppCo.contains("테라스타")  || divAppCo.contains("암니스") || divAppCo.contains("벤처피플") || divAppCo.contains("문자") || divAppCo.contains("케이티") ||  divAppCo.contains("에스케이티") || divAppCo.contains("명보eng") || divAppCo.contains("미디어") || divAppCo.contains("웹비전스") || divAppCo.contains("화라") || divAppCo.contains("디브이인사이드") || divAppCo.contains("클루엠") || divAppCo.contains("보안") || divAppCo.contains("모이어") || divAppCo.contains("카카오") ) {
			divAppCoCd = "008";//통신
		} else if (divAppCo.contains("abc") ||divAppCo.contains("백화점") || divAppCo.contains("아울렛") || divAppCo.contains("이케아") || divAppCo.contains("타임스퀘어") || divAppCo.contains("스타필드") || divAppCo.contains("신세계") || divAppCo.contains("면세점") || divAppCo.contains("커버낫") || divAppCo.contains("오프라인") || divAppCo.contains("위드스페이스") || divAppCo.contains("텐바이텐") || divAppCo.contains("문고리닷컴") || divAppCo.contains("믹스엑스믹스") || divAppCo.contains("바레코컴퍼니") || divAppCo.contains("샤오미") || divAppCo.contains("하늘라운지") || divAppCo.contains("올리브영") || divAppCo.contains("더페이스샵") || divAppCo.contains("두리맘") || divAppCo.contains("판다북") || divAppCo.contains("아베크") || divAppCo.contains("로로샤") || divAppCo.contains("리피니피") || divAppCo.contains("피규어") || divAppCo.contains("코코블랙부띠크") || divAppCo.contains("11step") || divAppCo.contains("라헨느") || divAppCo.contains("빤스생각") || divAppCo.contains("세컨드스토어") || divAppCo.contains("브로그맨") || divAppCo.contains("브론디") || divAppCo.contains("어리본") || divAppCo.contains("아리따움") || divAppCo.contains("화장품") || divAppCo.contains("스와니코코") || divAppCo.contains("디지털프라자") || divAppCo.contains("굿모닝보청기") || divAppCo.contains("열쇠") || divAppCo.contains("안경") || divAppCo.contains("상사") || divAppCo.contains("철물") || divAppCo.contains("웰메이드") || divAppCo.contains("방앗간") || divAppCo.contains("청과") || divAppCo.contains("정육") ||  divAppCo.contains("플라자") || divAppCo.contains("상회") || divAppCo.contains("미센스") || divAppCo.contains("콘택트") || divAppCo.contains("아이스왕국") || divAppCo.contains("정장") || divAppCo.contains("양장점") || divAppCo.contains("성보당") || divAppCo.contains("농약사") || divAppCo.contains("시장계란집") || divAppCo.contains("pat") || divAppCo.contains("초상화") || divAppCo.contains("조이너스") || divAppCo.contains("미스미스터") || divAppCo.contains("르까프") || divAppCo.contains("퍼스트올로") || divAppCo.contains("오휘") || divAppCo.contains("유니온베이")  || divAppCo.contains("신발") || divAppCo.contains("공구") || divAppCo.contains("두발로")  || divAppCo.contains("크로커다일") || divAppCo.contains("전자") || divAppCo.contains("상회") || divAppCo.contains("한복") || divAppCo.contains("식품") || divAppCo.contains("축산") || divAppCo.contains("하얀집") || divAppCo.contains("매점")  || divAppCo.contains("여우야") || divAppCo.contains("요넥스") || divAppCo.contains("의료기") || divAppCo.contains("개발") || divAppCo.contains("인테리어") || divAppCo.contains("중앙표구사") || divAppCo.contains("샷시") || divAppCo.contains("가공소") || divAppCo.contains("건설") || divAppCo.contains("컴퓨터") || divAppCo.contains("건강원") || divAppCo.contains("부품") || divAppCo.contains("라즐리") || divAppCo.contains("귀뚜라미") || divAppCo.contains("철강") || divAppCo.contains("풋젠") || divAppCo.contains("생수") || divAppCo.contains("모이몰른") || divAppCo.contains("전기") || divAppCo.contains("조명") || divAppCo.contains("라이트맨") || divAppCo.contains("동영") || divAppCo.contains("물류") || divAppCo.contains("의료기기") || divAppCo.contains("led") || divAppCo.contains("가로등") || divAppCo.contains("기계") || divAppCo.contains("애니멀") || divAppCo.contains("몰리스펫샵") || divAppCo.contains("애완") || divAppCo.contains("동물미용") || divAppCo.contains("폴리파크") || divAppCo.contains("펫") || divAppCo.contains("팻") || divAppCo.contains("애견") || divAppCo.contains("캣") || divAppCo.contains("강아지") || divAppCo.contains("고양이") || divAppCo.contains("도그") || divAppCo.contains("애니몰") || divAppCo.contains("난원") || divAppCo.contains("회원") || divAppCo.contains("꽃") || divAppCo.contains("플라워") || divAppCo.contains("식물") || divAppCo.contains("정원") || divAppCo.contains("떡") || divAppCo.contains("방앗간")  || divAppCo.contains("패션")	 || divAppCo.contains("라뜰리") || divAppCo.contains("미소") || divAppCo.contains("쇼핑")) {
			divAppCoCd = "009";//쇼핑
		} else if (divAppCo.contains("gmarket") || divAppCo.contains("쥐마켓") || divAppCo.contains("지마켓") || divAppCo.contains("쇼핑몰") || divAppCo.contains("쥬얼리") || divAppCo.contains("주얼리") || divAppCo.contains("닷컴") || divAppCo.contains("11번가") || divAppCo.contains("11st") || divAppCo.contains("몰") || divAppCo.contains("도서") || divAppCo.contains("쿠팡") || divAppCo.contains("coupang") || divAppCo.contains("티몬") || divAppCo.contains("티켓몬스터") || divAppCo.contains("인터파크") || divAppCo.contains("interpark") || divAppCo.contains("스타일") || divAppCo.contains("style") || divAppCo.contains("중고") || divAppCo.contains("장터") || divAppCo.contains("위콘샵") || divAppCo.contains("유통21") || divAppCo.contains("나르샵") || divAppCo.contains("큐니걸스") || divAppCo.contains("olssen") || divAppCo.contains("인포마당") || divAppCo.contains("스타일") || divAppCo.contains("mall") || divAppCo.contains("어패럴")  || divAppCo.contains("홈쇼핑")  || divAppCo.contains("구글플레이")) {
			divAppCoCd = "010";//온라인쇼핑
		} else if ( divAppCo.contains("cgv") || divAppCo.contains("씨지브이") || divAppCo.contains("롯데시네마") || divAppCo.contains("시네마") || divAppCo.contains("메가박스") || divAppCo.contains("megabox") || divAppCo.contains(" 영화") || divAppCo.contains("극장") || divAppCo.contains("소극장") || divAppCo.contains("대극장") || divAppCo.contains("뮤지컬") || divAppCo.contains("아트홀") || divAppCo.contains("세종문화회관") || divAppCo.contains("코엑스") || divAppCo.contains("coex") || divAppCo.contains("   박물관") || divAppCo.contains("미술관") || divAppCo.contains("전시") || divAppCo.contains("전시회") || divAppCo.contains("그림") || divAppCo.contains("예술") || divAppCo.contains("미술") || divAppCo.contains("작품") || divAppCo.contains("아트센터") || divAppCo.contains("예홀") || divAppCo.contains("엠팟") || divAppCo.contains("콘서트") || divAppCo.contains("스페이스 바움") || divAppCo.contains("민속") || divAppCo.contains("우림씨어터") || divAppCo.contains("keukeu") || divAppCo.contains("떼아뜨루삐우") || divAppCo.contains("엠아크") || divAppCo.contains("예스링크") || divAppCo.contains("엑스스페이스") || divAppCo.contains("연회장") || divAppCo.contains("밀레니엄엘앤알") || divAppCo.contains("컬쳐센터") || divAppCo.contains("stardeum") || divAppCo.contains("스타디움") || divAppCo.contains("두레홀") || divAppCo.contains("슬로우시티") || divAppCo.contains("써클이앤엠") || divAppCo.contains("M스테이지") || divAppCo.contains("티오엠") || divAppCo.contains("웨스티스") || divAppCo.contains("유니플렉스") || divAppCo.contains("콘텐츠박스") || divAppCo.contains("국악당") || divAppCo.contains("씨어터") || divAppCo.contains("문화") || divAppCo.contains("전당") || divAppCo.contains("댕로홀") || divAppCo.contains("갤러리") || divAppCo.contains("구민회관") || divAppCo.contains("kbs홀") || divAppCo.contains("극단해오름") || divAppCo.contains("뮬리") || divAppCo.contains("광진교8번가") || divAppCo.contains("scc홀") || divAppCo.contains("kt스퀘어") || divAppCo.contains("남산창작센터") || divAppCo.contains("mufac") || divAppCo.contains("ckl스테이지") || divAppCo.contains("웃찾사전용관") || divAppCo.contains("국악원")) {
			divAppCoCd = "011";//문화/예술
		} else if (divAppCo.contains("서점") || divAppCo.contains("문고") || divAppCo.contains("전시장") || divAppCo.contains("문구") || divAppCo.contains("책") || divAppCo.contains("북") || divAppCo.contains("book") || divAppCo.contains(" 서적") || divAppCo.contains("팬시") || divAppCo.contains("아트박스") || divAppCo.contains("artbox") || divAppCo.contains("북스") || divAppCo.contains("프렌테") || divAppCo.contains("팬시") || divAppCo.contains("프렌즈") || divAppCo.contains("링코") || divAppCo.contains("버터") || divAppCo.contains("드림디포") || divAppCo.contains("알파") || divAppCo.contains("가챠샵") || divAppCo.contains("클립스") || divAppCo.contains("항소") || divAppCo.contains(" 드림오피스") || divAppCo.contains("바른손") || divAppCo.contains("타이니빅") || divAppCo.contains("동아") || divAppCo.contains("샤인") || divAppCo.contains("문화사") || divAppCo.contains("모닝글로리") || divAppCo.contains("이네이션") || divAppCo.contains("오피세이브") || divAppCo.contains("몰드디자인") || divAppCo.contains("두보양행") || divAppCo.contains("티티경인") || divAppCo.contains("오피스테이션") || divAppCo.contains("기프트") || divAppCo.contains("서플라이") || divAppCo.contains("aceink") || divAppCo.contains(" 문방구") || divAppCo.contains("책방") || divAppCo.contains("파크") || divAppCo.contains("yes24") || divAppCo.contains("알라딘") || divAppCo.contains("북티크") || divAppCo.contains("로얄커처커프레이션") || divAppCo.contains("월드메거진") || divAppCo.contains("piece") || divAppCo.contains("잉크앤페더") || divAppCo.contains("오르다코리아") || divAppCo.contains("노르웨이숲") || divAppCo.contains("엘케이씨지엠") || divAppCo.contains("아람북스") || divAppCo.contains("출판사") || divAppCo.contains("미디어자몽") || divAppCo.contains("반디앤루니스") || divAppCo.contains("이스턴미디어") || divAppCo.contains("이엘앤티") || divAppCo.contains("글방") || divAppCo.contains("스마트베어") || divAppCo.contains("에주브레인") || divAppCo.contains("헌책") || divAppCo.contains("중고책") || divAppCo.contains("세이몰") || divAppCo.contains("라비블") || divAppCo.contains("프루스트의 서재") || divAppCo.contains("문광서원") || divAppCo.contains("두란노서원") || divAppCo.contains("엠이디") || divAppCo.contains("보물선") || divAppCo.contains("자스민") || divAppCo.contains("아름다운가게") || divAppCo.contains("개똥이네") || divAppCo.contains("아시아저널") || divAppCo.contains("휴먼스페이스") || divAppCo.contains("양국서림") || divAppCo.contains("페이펄")) {
			divAppCoCd = "012";//서점/문구
		} else if (divAppCo.contains("클라이밍") || divAppCo.contains("낚시") || divAppCo.contains("보드") || divAppCo.contains("야구") || divAppCo.contains("축구") || divAppCo.contains("농구") || divAppCo.contains("배구") || divAppCo.contains("배드민턴") || divAppCo.contains("테니스") || divAppCo.contains("레저") || divAppCo.contains("스포츠") || divAppCo.contains("스키") || divAppCo.contains("래프팅") || divAppCo.contains("패러글라이딩") || divAppCo.contains("수상") || divAppCo.contains("당구") || divAppCo.contains("스쿠버") || divAppCo.contains("스쿠바") || divAppCo.contains("헬스") || divAppCo.contains("짐") || divAppCo.contains("휘트니스") || divAppCo.contains("골프") || divAppCo.contains("요가") || divAppCo.contains("트레이닝") || divAppCo.contains("퍼스널") || divAppCo.contains("필라테스") || divAppCo.contains("복싱") || divAppCo.contains("태권도") || divAppCo.contains("무에타이") || divAppCo.contains("주짓수") || divAppCo.contains("댄스") || divAppCo.contains("기원") || divAppCo.contains("바둑") || divAppCo.contains("오목") || divAppCo.contains("장기") || divAppCo.contains("스피닝") || divAppCo.contains("야사노")) {
			divAppCoCd = "013";//레저/스포츠
		} else if (divAppCo.contains("약국") || divAppCo.contains("병원") || divAppCo.contains("의원") || divAppCo.contains("한의원") || divAppCo.contains("이비인후과") || divAppCo.contains("메디컬") || divAppCo.contains("소아과") || divAppCo.contains("외과") || divAppCo.contains("안과") || divAppCo.contains("내과") || divAppCo.contains("치과") || divAppCo.contains("산부인과") || divAppCo.contains("정신과") || divAppCo.contains("피부과") || divAppCo.contains("비뇨기과") || divAppCo.contains("의학과 ") || divAppCo.contains("동물병원") || divAppCo.contains("동물의료센터") || divAppCo.contains("가정의학과") || divAppCo.contains("의료") || divAppCo.contains("보험")) {
			divAppCoCd = "014";//의료/건강
		} else if (divAppCo.contains("호텔") || divAppCo.contains("hotel") || divAppCo.contains("모텔") || divAppCo.contains("여관") || divAppCo.contains("여인숙") || divAppCo.contains("motel") || divAppCo.contains("신라스테이") || divAppCo.contains("알로프트") || divAppCo.contains("파크하얏트") || divAppCo.contains("에이치에비뉴") || divAppCo.contains("서울레지던스") || divAppCo.contains("무인텔") || divAppCo.contains("리조트") || divAppCo.contains("펜션") || divAppCo.contains("콘도") || divAppCo.contains("하우스") || divAppCo.contains("하숙") || divAppCo.contains("컨벤션") || divAppCo.contains("고시원") || divAppCo.contains("고시텔") || divAppCo.contains("리빙텔") || divAppCo.contains("인터컨티넨탈") || divAppCo.contains("오크우드") || divAppCo.contains("팰리스") || divAppCo.contains("글래드라이브") || divAppCo.contains("알로프트") || divAppCo.contains("르 메르디앙") || divAppCo.contains("이비스스타일") || divAppCo.contains("삼성베드스테이션") || divAppCo.contains("힐탑") || divAppCo.contains("인더시티레지던스") || divAppCo.contains("도미인") || divAppCo.contains("포레힐") || divAppCo.contains("휴먼터치") || divAppCo.contains("CULLA") || divAppCo.contains("일성레저산업") || divAppCo.contains("미림장") || divAppCo.contains("모투") || divAppCo.contains("쿨라") || divAppCo.contains("카라반클럽") || divAppCo.contains("MO2클래식") || divAppCo.contains("대우로얄카운티") || divAppCo.contains("레지던스") || divAppCo.contains("수농") || divAppCo.contains("원룸텔") || divAppCo.contains("컨벤션") || divAppCo.contains("house") || divAppCo.contains(" 스카이프라자") || divAppCo.contains("어씀") || divAppCo.contains("야놀자") || divAppCo.contains("여기어때") || divAppCo.contains("yaja") || divAppCo.contains("Avenue ") || divAppCo.contains("리치웰") || divAppCo.contains("아드리게") || divAppCo.contains("맨하탄") || divAppCo.contains("하이웨이") || divAppCo.contains("vole") || divAppCo.contains("라트리") || divAppCo.contains("latree") || divAppCo.contains("블랑") || divAppCo.contains("yam") || divAppCo.contains("하이웨이") || divAppCo.contains("투어") || divAppCo.contains("항공") || divAppCo.contains("드림아일랜드") || divAppCo.contains("여행") || divAppCo.contains("스마트인피니") || divAppCo.contains("하마씨") || divAppCo.contains("트리프렌드") || divAppCo.contains("코리아헤럴드") || divAppCo.contains("투플") || divAppCo.contains("바스코마케팅") || divAppCo.contains("항공") || divAppCo.contains("더메인즈") || divAppCo.contains("인더월드") || divAppCo.contains("엔터크루즈") || divAppCo.contains("월리스") || divAppCo.contains("동신항운") || divAppCo.contains("트래블로드") || divAppCo.contains("힐팩") || divAppCo.contains("관광") || divAppCo.contains("트래블") || divAppCo.contains("트레블") || divAppCo.contains("비즈비") || divAppCo.contains("코리아비전") || divAppCo.contains("아름다운tea") || divAppCo.contains("미국이야기") || divAppCo.contains("유학") || divAppCo.contains("재패니안") || divAppCo.contains("에이전시")   || divAppCo.contains("agency") || divAppCo.contains("더실크로드")) {
			divAppCoCd = "015";//여행/숙박
		} else if (divAppCo.contains("어린이집") || divAppCo.contains("유치원") || divAppCo.contains("물댄동산농원") || divAppCo.contains("학원") || divAppCo.contains("대학교") || divAppCo.contains("대학원") || divAppCo.contains("수련원") || divAppCo.contains("연수원") || divAppCo.contains("한국코칭센터") || divAppCo.contains("독서실") || divAppCo.contains("도서관") || divAppCo.contains("교습") || divAppCo.contains("수학") || divAppCo.contains("영어") || divAppCo.contains("국어") || divAppCo.contains("언어") || divAppCo.contains("외국어") || divAppCo.contains("토익") || divAppCo.contains("피아노") || divAppCo.contains("미술") || divAppCo.contains("교육") || divAppCo.contains("인재개발원")) {
			divAppCoCd = "016";//교육
		} else if (divAppCo.contains("육아") || divAppCo.contains("더앙젤로코") || divAppCo.contains("마미키트") || divAppCo.contains("산후조리") || divAppCo.contains("아이커머스") || divAppCo.contains("베이비") || divAppCo.contains("아기") || divAppCo.contains("맘") || divAppCo.contains("맘스") || divAppCo.contains("해피랜드") || divAppCo.contains("트윈키즈365") || divAppCo.contains("장난감") || divAppCo.contains("원더키즈") || divAppCo.contains("베이비") || divAppCo.contains("컬리수") || divAppCo.contains("제로투세븐") || divAppCo.contains("주니룸") ||  divAppCo.contains("리틀브렌") || divAppCo.contains("꼬까방") || divAppCo.contains("모이푸푸") || divAppCo.contains("메네메네") || divAppCo.contains("컬리수마산") || divAppCo.contains("꼬망스") || divAppCo.contains("모이몰론") || divAppCo.contains("꼬마옷짱") || divAppCo.contains("슈슈앤크라") || divAppCo.contains("제로투세븐") || divAppCo.contains("베베드피노") || divAppCo.contains("무냐무냐") || divAppCo.contains("그랑베베") || divAppCo.contains("애플망고") || divAppCo.contains("율이네옷장") || divAppCo.contains("민트콩이") || divAppCo.contains("올리오시") || divAppCo.contains("여우별") || divAppCo.contains("아이") || divAppCo.contains("뽀밍") || divAppCo.contains("아오") || divAppCo.contains("핑크엔젤") || divAppCo.contains("헬로리본") || divAppCo.contains("크레파스") || divAppCo.contains("도로시") || divAppCo.contains("포래즈성서") || divAppCo.contains("실로퐁퐁") || divAppCo.contains("쿠키하우스") || divAppCo.contains("더데이걸") || divAppCo.contains("로빈") || divAppCo.contains("아동") || divAppCo.contains("프렌치캣") || divAppCo.contains(" 바이랑") || divAppCo.contains("치크") || divAppCo.contains("젤리멜로") || divAppCo.contains("포래즈") || divAppCo.contains("꼬마엔젤") || divAppCo.contains("키즈") || divAppCo.contains("리틀모델") || divAppCo.contains("헬로쥬시") || divAppCo.contains("코코리따") || divAppCo.contains("스타일메리제인")) {
			divAppCoCd = "017";//유아/아동용품
		} else if (divAppCo.contains("경조사") || divAppCo.contains("한일문화사") || divAppCo.contains("컨벤션") || divAppCo.contains("웨딩") || divAppCo.contains("계룡스파텔") || divAppCo.contains("예식장") || divAppCo.contains("더클래스") || divAppCo.contains("교회") || divAppCo.contains("기도원") || divAppCo.contains("성당") || divAppCo.contains("함양여악사") || divAppCo.contains("가섭사지") || divAppCo.contains("수양관") || divAppCo.contains("불당") || divAppCo.contains("법당") || divAppCo.contains("금강굴") || divAppCo.contains("가조학천사") || divAppCo.contains("묘적사") || divAppCo.contains("극락전")) {
			divAppCoCd = "018";//경조사/종교
		} else if (divAppCo.contains("은행") || divAppCo.contains("증권") || divAppCo.contains("농협") || divAppCo.contains("NH농협") || divAppCo.contains("nh농협") || divAppCo.contains("새마을금고") || divAppCo.contains("금융") || divAppCo.contains("넥스리치") || divAppCo.contains("홀딩스") || divAppCo.contains("대부") || divAppCo.contains("ATM") || divAppCo.contains("크레딧") || divAppCo.contains("크레디트") || divAppCo.contains("에셋") || divAppCo.contains("투자") || divAppCo.contains("캐시") || divAppCo.contains("자문") || divAppCo.contains("흥창") || divAppCo.contains("컨설팅") || divAppCo.contains("다빈치에프앤") || divAppCo.contains("머니") || divAppCo.contains("캐피탈") || divAppCo.contains("스누라") || divAppCo.contains("씨앤씨노바") || divAppCo.contains("펀드") || divAppCo.contains("펀딩")  || divAppCo.contains("우체국")  || divAppCo.contains("우정사업")  ) {
			divAppCoCd = "019";//증권/우체국
		} else if (divAppCo.contains("세금") || divAppCo.contains("세무") || divAppCo.contains("회계") || divAppCo.contains("자문")  || divAppCo.contains("시청") || divAppCo.contains("구청")  || divAppCo.contains("펀딩")  || divAppCo.contains("동사무소")  || divAppCo.contains("시설관") ) {
			divAppCoCd = "020";//세금
		} else if (divAppCo.contains("웰빙코퍼레이션") || divAppCo.contains("코자브코리아") || divAppCo.contains("심플리아웃피터스") || divAppCo.contains("몰린") || divAppCo.contains("브레인박스") || divAppCo.contains("autocos") || divAppCo.contains("에이블텍") || divAppCo.contains("z4코리아") || divAppCo.contains("에스씨네비") || divAppCo.contains("테마사운드") || divAppCo.contains("애플코팅") || divAppCo.contains("지피에스피아") || divAppCo.contains("오토브라이트") || divAppCo.contains("위드핀") || divAppCo.contains("티맥스") || divAppCo.contains("한국코리스") || divAppCo.contains("모토플랜") || divAppCo.contains("ig부스터") || divAppCo.contains("매직사운드") || divAppCo.contains("와이드오픈") || divAppCo.contains("더원") || divAppCo.contains("gps123") || divAppCo.contains("형제지프") || divAppCo.contains("자동차용품") || divAppCo.contains("리스캣")) {
			divAppCoCd = "021";//자동차/용품
		} else {
			divAppCoCd = "999";//미확인 //기타 000
		}
		return divAppCoCd;
	}

}
