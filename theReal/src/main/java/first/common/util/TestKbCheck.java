package first.common.util;

import java.util.HashMap;

public class TestKbCheck {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		// 15881688
		String test = "[Web발신]\n[KB]03/09 16:06\n546902**352\n티머니택시\n체크카드출금\n12,200\n잔액174,690";
		String[] test1 = test.replace("\r", "").split("\n");
		map.put("REC_TEN_NO", "01022086378");
		map.put("SEND_TEN_NO", "16449999");
		for (int i = 0; i < test1.length; i++) {
			System.out.println(test1[i]);
			// 사용일시
			if (test1[i].contains(":") && test1[i].contains("/")) {
				map.put("APP_DT",
						test1[i].substring(0, (test1[i].indexOf(":") + 3)).replace("[KB]", "").replace("\n", ""));
			}
			// 승인및 체크카드여부
			else if (test1[i].contains("체크")) {
				map.put("CARD_APP_DIV", "승인");
				if (test1[i].contains("체크")) {
					map.put("CARD_APP_CO_DIV", "KB국민체크");
					map.put("CARD_APP_CO", "KB체크");
					map.put("INST_DIV", "체크");
				}
				// map.put("INST_DIV",test1[i].substring(0,(test1[i].indexOf("."))).replace("[",
				// ""));
			} else if (test1[i].contains("취소")) {
				map.put("CARD_APP_DIV", "취소");
			} else if (test1[i].contains(",") && !test1[i].contains("잔액")) {
				map.put("APP_AMOUNT", test1[i].replace("원", "").replace(",", ""));
			} else if (test1[i].contains("잔액")) {
				// map.put("APP_AMOUNT",test1[i].replace("원",
				// "").replace(",",""));
			} else {
				if (test1[i].contains(" 사용")) {
					map.put("APP_CO", test1[i].substring(0, (test1[i].indexOf(" 사용"))));
				}
				map.put("APP_CO", test1[i]);
			}
		}
		System.out.println("map:" + map);

	}

}
