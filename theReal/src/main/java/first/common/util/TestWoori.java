package first.common.util;

import java.util.HashMap;

public class TestWoori {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String test = "[Web발신]\n[일시불.승인]\n4,000원\n우리카드(9297)법인-설*진\n12/18 16:14 \n(주)광화문 아띠(친시바";
		// String test = "[Web발신]\n[승인취소]\n4,000원\n우리카드(9297)법인-설*진\n12/18 16:14
		// \n티머니택시";
		HashMap<String, Object> map = new HashMap<String, Object>();
		String[] test1 = test.split("\n");

		for (int i = 0; i < test1.length; i++) {
			System.out.println(test1[i]);
			// 사용일시
			if (test1[i].contains(":") && test1[i].contains("/")) {
				map.put("APP_DT", test1[i].substring(0, (test1[i].indexOf(":") + 3)).replace("\n", ""));
			}

			// 승인및 체크카드여부
			else if (test1[i].contains("승인]")) {
				map.put("CARD_APP_DIV", "승인");
				if (test1[i].contains("체크")) {
					map.put("CARD_APP_CO_DIV", "체크카드");
				}
				map.put("INST_DIV", test1[i].substring(0, (test1[i].indexOf("."))).replace("[", ""));
			} else if (test1[i].contains("취소]")) {
				map.put("CARD_APP_DIV", "취소");
			}

			// 사용금액
			else if (test1[i].contains(",")) {
				map.put("APP_AMOUNT", test1[i].replace("원", "").replace(",", ""));
			}

			// 법인,체크카드구분
			else if (test1[i].startsWith("우리")) {
				if (test1[i].contains("-")) {
					map.put("CARD_APP_CO", "우리카드법인");
				} else {
					if ("체크카드".equals(map.get("CARD_APP_CO_DIV"))) {
						map.put("CARD_APP_CO", "우리체크카드");
					} else {
						map.put("CARD_APP_CO", "우리카드");
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
		System.out.println(map);

	}

}
