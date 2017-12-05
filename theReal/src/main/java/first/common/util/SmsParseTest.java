package first.common.util;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsParseTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// String test = "[Web발신]\n삼성카드승인9360설*진\r\n12/18 16:14 티머니택시\r\n4,000원
		// 일시불\r\n누적2,289,720원";
		// String test = "[Web발신]\n[일시불.승인]\n4,000원\n우리카드(9297)법인-설*진\n12/18
		// 16:14 \n티머니택시";
		// String test = "[Web발신]\n[승인취소]\n4,000원\n우리카드(9297)법인-설*진\n12/18 16:14
		// \n티머니택시";
		// String test = "(체크승인)\n22,000원\nNH농협카드(9*4*)\n라*준 님\n12/19 12:43\nCJ
		// CGV_온라인예매";
		// String test = "[일시불.승인]\n51,000원\n기업BC(2080)라*준님\n12/25
		// 22:37\n누적820,380원\n엉터리생고기의정";
		String test = "[Web발신]\n신한카드승인 나*준(5*6*) 12/08 12:13 (일시불)9,000원 씨유신사한남 누적359,620원";

		// System.out.println(test);
		// String s = "([4195]-[4209])+([4196]+[4210])";

		// String anni_date = "12/13 07:37";
		String anni_date = "12/13 07:37 씨유수원대한";
		/*
		 * System.out.println(anni_date.indexOf(":"));
		 * System.out.println(anni_date.indexOf("/"));
		 * System.out.println(anni_date.substring(0,
		 * (anni_date.indexOf(":")+3)));
		 * System.out.println(anni_date.substring((anni_date.indexOf(":")+4)));
		 */
		Pattern pattern = Pattern.compile(
				"^(0[1-9]|1[012])([/])?(0[1-9]|[12][0-9]|3[01])(\\s0[1-9]|1[012])([:])?(0[1-9]|[12][0-9]|3[01])$");
		Matcher matcher = pattern.matcher(anni_date);
		/* System.out.println(matcher.find()); */
		String[] test1 = test.replace("[Web발신]\n", "").split(" ");
		HashMap<String, Object> map = new HashMap<String, Object>();
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
		String appDtA1 = (String) map.get("APP_DT_A1");
		String appDtA2 = (String) map.get("APP_DT_A2");
		map.put("APP_DT", appDtA1 + " " + appDtA2);

		System.out.println(map);
	}
	/*
	 * Pattern p = Pattern.compile("\\[(.*?)\\]"); Matcher m = p.matcher(test);
	 * System.out.println(m.find()); while(m.find()) {
	 * System.out.println(m.group(1)); }
	 */

	/*
	 * Pattern p1 = Pattern.compile("\\[0-9](.*?)\\원"); Matcher m1 =
	 * p1.matcher(test); while(m1.find()) { System.out.println(m1.group(1)); }
	 */

	/*
	 * boolean startsWith = test.startsWith("a"); System.out.println(
	 * "startsWith: " + startsWith);
	 * 
	 * boolean endsWith = test.endsWith("t"); System.out.println("endsWith: " +
	 * endsWith);
	 */

}
