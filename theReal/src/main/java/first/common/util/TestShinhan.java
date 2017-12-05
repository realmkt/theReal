package first.common.util;

import java.util.HashMap;

public class TestShinhan {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HashMap<String, Object> map = new HashMap<String, Object>();
		// String test = "[Web발신]\n신한카드승인 나*준(5*6*) 01/23 14:13 (일시불)82,980원
		// 예스이십사 누적284,915원";
		// String test = "[Web발신]\n신한카드승인 나*준(5*6*) 12/08 12:13 (일시불)9,000원
		// 씨유신사한남 누적359,620원";
		String test = "[Web발신]\n신한카드승인 나*준(5*6*) 02/18 17:55 (일시불)38,670원 Market999독  누적45";
		// String test = "[Web발신]\n신한카드승인 나*준(5*6*) 01/21 17:51 (일시불)21,000원
		// 도담그루 누적202,985원";
		String[] test1 = test.replace("[Web발신]\n", "").replaceAll("[ ]+", " ").split(" ");

		for (int i = 0; i < test1.length; i++) {
			System.out.println(test1[i]);
			// 사용일시
			if (test1[i].contains("/")) {
				map.put("APP_DT_A1", test1[i].substring(0, (test1[i].indexOf("/") + 3)).replace("\n", ""));
			} else if (test1[i].contains(":")) {
				map.put("APP_DT_A2", test1[i].substring(0, (test1[i].indexOf(":") + 3)).replace("\n", ""));
			}

			// 승인및 체크카드여부
			else if (test1[i].endsWith("카드승인")) {
				map.put("CARD_APP_DIV", "승인");
				if (test1[i].contains("체크")) {
					map.put("CARD_APP_CO_DIV", "체크카드");
					map.put("INST_DIV", "체크");
					map.put("CARD_APP_CO", "신한체크카드");
				} else {
					map.put("CARD_APP_CO", "신한카드");
				}
			} else if (test1[i].contains("취소")) {
				map.put("CARD_APP_DIV", "취소");
			}

			// 사용금액
			else if (test1[i].contains(",") && test1[i].contains("원") && !test1[i].contains("누적")) {
				map.put("APP_AMOUNT", test1[i].substring(test1[i].indexOf(")") + 1).replace("원", "").replace(",", ""));
				map.put("INST_DIV", test1[i].substring(0, test1[i].indexOf(")")).replace("(", ""));
			} else if (test1[i].contains(",") && test1[i].contains("누적")) {
				map.put("ACC_SUM_AMOUNT", test1[i].replace("원", "").replace(",", "").replace("누적", ""));
			} else {
				map.put("APP_CO", test1[i]);
			}
		}

		System.out.println("map:" + map);
	}

}
