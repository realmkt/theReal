package first.common.util;

import java.util.HashMap;

public class TestKbCard {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		// 15881688
		String test = "[Web발신]\nKB국민체크(0*3*)\n나*준님\n02/05 20:16\n24,000원\n비비큐치킨 사용";
		// String test = "[Web발신]\nKB국민체크(3*5*)\n설*진님\n01/11 21:57\n5,400원\n이비카드
		// 택시 사용";
		// String test = "[Web발신]\n삼성카드승인9360설*진\r\n12/18 16:14 티머니택시\r\n4,000원
		// 일시불\r\n누적2,289,720원";
		// String test = "[Web발신]\n삼성카드승인7624나*준\n01/20 15:17 신세계페이\n75,740원
		// 일시불\n누적3,545,654원";
		// String[] test1 = var.find("message").toString().split("\r");
		String[] test1 = test.replace("\r", "").split("\n");
		map.put("REC_TEN_NO", "01022086378");
		map.put("SEND_TEN_NO", "15888900");
		for (int i = 0; i < test1.length; i++) {
			System.out.println(test1[i]);
			// 사용일시
			if (test1[i].contains(":") && test1[i].contains("/")) {
				map.put("APP_DT", test1[i].substring(0, (test1[i].indexOf(":") + 3)).replace("\n", ""));
			}
			// 승인및 체크카드여부
			else if (test1[i].contains("체크")) {
				map.put("CARD_APP_DIV", "승인");
				if (test1[i].contains("체크")) {
					map.put("CARD_APP_CO_DIV", "KB국민체크");
					map.put("INST_DIV", "체크");
				}
				// map.put("INST_DIV",test1[i].substring(0,(test1[i].indexOf("."))).replace("[",
				// ""));
			} else if (test1[i].contains("취소")) {
				map.put("CARD_APP_DIV", "취소");
			} else if (test1[i].contains(",")) {
				map.put("APP_AMOUNT", test1[i].replace("원", "").replace(",", ""));
			} else {
				if (test1[i].contains(" 사용")) {
					map.put("APP_CO", test1[i].substring(0, (test1[i].indexOf(" 사용"))));
				}
			}
		}
		System.out.println("map:" + map);
	}

}
