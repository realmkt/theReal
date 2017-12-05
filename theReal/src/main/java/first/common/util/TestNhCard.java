package first.common.util;

import java.util.HashMap;

public class TestNhCard {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();

		String test = "(체크승인)\n22,000원\nNH농협카드(9*4*)\n라*준 님\n12/19 12:43\nCJ CGV_온라인예매";
		// String test = "[Web발신]\n[체크승인]\n61,250원\nNH농협카드(2*3*)\n나*준 님\n01/25
		// 10:47\n케이마트";

		String[] test1 = test.split("\n");

		for (int i = 0; i < test1.length; i++) {
			System.out.println(test1[i]);
			// 사용일시
			if (test1[i].contains(":") && test1[i].contains("/")) {
				map.put("APP_DT", test1[i].substring(0, (test1[i].indexOf(":") + 3)).replace("\n", ""));
			}

			// 승인및 체크카드여부
			else if (test1[i].contains("체크승인")) {
				map.put("CARD_APP_DIV", "승인");
				if (test1[i].contains("체크")) {
					map.put("CARD_APP_CO_DIV", "체크카드");
					map.put("INST_DIV", "체크");
				}
				// map.put("INST_DIV",test1[i].substring(0,(test1[i].indexOf("."))).replace("[",
				// ""));
			} else if (test1[i].contains("취소")) {
				map.put("CARD_APP_DIV", "취소");
			}

			// 사용금액
			else if (test1[i].contains(",")) {
				map.put("APP_AMOUNT", test1[i].replace("원", "").replace(",", ""));
			}

			// 법인,체크카드구분
			else if (test1[i].startsWith("NH")) {
				if (test1[i].contains("-")) {
					map.put("CARD_APP_CO", "NH농협카드");
				} else {
					if ("체크카드".equals(map.get("CARD_APP_CO_DIV"))) {
						map.put("CARD_APP_CO", "NH농협카드");
					} else {
						map.put("CARD_APP_CO", "NH농협카드");
					}
				}
			} else if (test1[i].endsWith("개월")) {
				System.out.println("개월");
				map.put("APP_AMOUNT", test1[i].substring(0, (test1[i].indexOf("원") + 1)).replace("\n", "")
						.replace("원", "").replace(",", ""));
				map.put("INST_DIV", test1[i].substring((test1[i].indexOf("원") + 2)));
			} else {
				map.put("APP_CO", test1[i]);
			}
		}

		System.out.println("map.get(CARD_APP_DIV):" + map.get("CARD_APP_DIV"));
		System.out.println("map.get(APP_AMOUNT):" + map.get("APP_AMOUNT"));
		if ("승인".equals(map.get("CARD_APP_DIV")) && !"".equals(map.get("APP_AMOUNT"))
				&& map.get("APP_AMOUNT") != null) {
			System.out.println("map11:" + map);
		}
		System.out.println("map121:" + map);

	}

}
