package first.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.json.simple.parser.ParseException;

public class StudyTest {

	public static void main(String[] args) throws ParseException, UnknownHostException {
		// TODO Auto-generated method stub
		/*
		 * String jsonData = "{"; jsonData += "\"control\" : "; //컨트롤영역 jsonData
		 * += "{ "; jsonData += "\"flwNo\": \"O200\""; //추적번호 jsonData += "}";
		 * jsonData += ", \"wcc\" : \"3\""; //WCC(인증방식) / 1:MSR/2:IC/3:KEY IN
		 * jsonData += ", \"aprAkMdDc\":\"1\""; //승인요청방식구분코드/ 1:POS jsonData +=
		 * ", \"cstDrmDc\" : \"1\""; //고객식별 구분코드/ 1:카드번호 /2: 고객번호 jsonData +=
		 * ", \"cdno\":\"8710350063911021\""; //카드번호 jsonData +=
		 * ", \"cstDrmV\":\"\""; //고객식별값/ 20자 //jsonData +=
		 * ", \"cstDrmV\" : \"0010558756\""; //고객식별값/ 20자 jsonData +=
		 * ", \"copMcno\" : \"P001400002\""; //개방형 제휴가맹점번호 jsonData +=
		 * ", \"ccoAprno\" : \"2016092110043\""; //개방형 제휴사승인번호/19자 jsonData +=
		 * ", \"deDc\" : \"10 \""; //거래구분코드/10:포인트적립 jsonData +=
		 * ", \"deRsc\" : \"100\""; //거래사유코드/100:대금결제 jsonData +=
		 * ", \"rvDc\" : \"1\""; //적립구분코드/1:정상 jsonData +=
		 * ", \"deAkMdDc\" : \"1\""; //거래요청방식구분코드/0:멤버스카드제시/1:휴대폰번호/
		 * 2:영수증(사후적립)/ 9:기타 jsonData += ", \"ptRvDc\" : \"1\"";
		 * //포인트적립구분코드/1:포인트대상값기준 jsonData += ", \"totSlAm\" : 10000";
		 * //INT)총매출금액/모든결제수단금액입력 jsonData += ", \"ptOjAm\" : 10000";
		 * //(INT)포인트대상금액 jsonData += ", \"cshSlAm\" : 10000"; //(INT)현금 매출금액
		 * jsonData += ", \"mbdSlAm\"  : 0"; //INT)상품권 매출금액 jsonData +=
		 * ", \"ccdSlAm\"  : 0"; //INT)신용카드 매출금액 jsonData += ", \"ptSlAm\"  : 0"
		 * ; //INT)포인트 매출금액 jsonData += ", \"etcSlAm\"  : 0"; //INT)기타 매출금액
		 * jsonData += ", \"cponNo\" : \"\""; //쿠폰번호 jsonData +=
		 * ", \"deAkInf\" : \"000-1111-4890 \""; //래요청정보 1이면 휴대폰번호, 2이면 영수증번호
		 * jsonData += ", \"copMbrGdC\" : \"\""; //제휴사회원등급코드 jsonData +=
		 * ", \"filler\" : \"\""; //추가데이터 저장영역 jsonData += ", \"evnInfCt\" : 0";
		 * //이벤트정보건수 jsonData += "\"sttCdCt\"  : 0"; //(INT)결제카드건수 jsonData +=
		 * "}";
		 * 
		 * 
		 * JSONParser jsonParser = new JSONParser();
		 * 
		 * //JSON데이터를 넣어 JSON Object 로 만들어 준다. JSONObject jsonObject = null;
		 * JSONObject jsonObject2 = null;
		 * 
		 * jsonObject = (JSONObject) jsonParser.parse(jsonData); jsonObject2 =
		 * (JSONObject) jsonParser.parse(jsonObject.get("control").toString());
		 * 
		 * String divCd = (String) jsonObject2.get("flwNo");
		 * System.out.println(divCd); String ccoAprno = "";
		 * 
		 * switch (divCd) { case "O200": System.out.println("적립"); break;
		 * default: System.out.println("적립취소"); break; }
		 * 
		 * 
		 * String makeFile = "./temp/test.txt"; File file = new File(makeFile);
		 * if (! file.exists()) { System.out.println("4343"); try {
		 * BufferedWriter out = new BufferedWriter(new FileWriter(makeFile));
		 * out.write("2016092110039"); out.close(); } catch (IOException e) {
		 * e.printStackTrace(); } }else{ try { BufferedReader in = new
		 * BufferedReader(new FileReader(makeFile)); //Integer s1
		 * =Integer.parseInt(in.readLine()); Long s1 =
		 * Long.parseLong(in.readLine()); in.close(); try { BufferedWriter out =
		 * new BufferedWriter(new FileWriter(makeFile)); ccoAprno =
		 * Long.toString(s1+1);
		 * 
		 * System.out.println(ccoAprno); out.write(ccoAprno); out.close(); }
		 * catch (IOException e) { e.printStackTrace(); } } catch (IOException
		 * e) { e.printStackTrace(); }
		 * 
		 * }
		 */

		String tst = "2104444";
		System.out.println(tst.length());
		if (tst.length() == 7) {
			System.out.println("222");
		} else {
			System.out.println("444");
		}

		try {
			InetAddress tagetIp = InetAddress.getByName("117.52.97.40");
			boolean reachable = tagetIp.isReachable(5000);

			if (reachable) {
				System.out.println("Test OK!!!");
			} else {
				System.out.println("Test NG!!!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
