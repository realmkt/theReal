package first.common.common;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class SendMailTest {
	
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
	public static void sendMail(ServletRequest request, String date) {
		String emailSendJsonData = request.getParameter("emailSendJsonData");
		System.out.println("emailSendJsonData:" + emailSendJsonData);
		Properties p = System.getProperties();
		p.put("mail.smtp.starttls.enable", "true"); // gmail은 무조건 true 고정
		p.put("mail.smtp.host", "smtp.gmail.com"); 	// smtp 서버 주소
		p.put("mail.smtp.auth", "true"); 			// gmail은 무조건 true 고정
		p.put("mail.smtp.port", "587"); 			// gmail 포트

		Authenticator auth = new MyAuthentication();

		// session 생성 및 MimeMessage생성
		Session session = Session.getDefaultInstance(p, auth);
		MimeMessage msg = new MimeMessage(session);

		try {
			// 편지보낸시간
			msg.setSentDate(new Date());

			InternetAddress from = new InternetAddress();

			from = new InternetAddress("theReal<theReal@realmkt.co.kr>");

			// 이메일 발신자
			msg.setFrom(from);

			// 이메일 제목
			msg.setSubject("[더리얼마케팅]전자영수증메일 입니다.");

			/*
			 * String message = "<i>"+eMail+"님이 "+useDate+
			 * "사용하신 전자영수증 입니다.</i><br>"; message += "<html>"; message +=
			 * "<div><table id='tg-YqJ3z' class='tg' style='undefined;table-layout: fixed; width: 555px;border-collapse:collapse;border-spacing:0;border-color:#aabcfe;'>"
			 * ; message += "<colgroup>"; message +=
			 * "<col style='width: 101px'>"; message +=
			 * "<col style='width: 177px'>"; message +=
			 * "<col style='width: 101px'>"; message +=
			 * "<col style='width: 176px'>"; message += "</colgroup>"; message
			 * += "  <tr>"; message +=
			 * "    <th style='font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#039;background-color:#b9c9fe;font-weight:bold;font-size:large;text-align:right;vertical-align:top;' class='tg-m3kb' colspan='4'>전자영수증</th>"
			 * ; message += "  </tr>"; message += "  <tr>"; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top' class='tg-f9es'>카드종류</td>"
			 * ; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:left;vertical-align:top'  class='tg-v6ky'>"
			 * +payDiv+"</td>"; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>카드번호</td>"
			 * ; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:right;vertical-align:top'  class='tg-rd6t'>9410-0900-****-7624</td>"
			 * ; message += "  </tr>"; message += "  <tr>"; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>유효기간</td>"
			 * ; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:left;vertical-align:top'  class='tg-v6ky'></td>"
			 * ; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>거래일시</td>"
			 * ; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:right;vertical-align:top'  class='tg-rd6t'>"
			 * +useDate+"</td>"; message += "  </tr>"; message += "  <tr>";
			 * message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>품명</td>"
			 * ; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:left;vertical-align:top'  class='tg-v6ky' colspan='3'>"
			 * +payGoods+"</td>"; message += "  </tr>"; message += "  <tr>";
			 * message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>결제방법</td>"
			 * ; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:left;vertical-align:top'  class='tg-v6ky'>카드결제</td>"
			 * ; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>금액</td>"
			 * ; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:right;vertical-align:top'  class='tg-rd6t'>"
			 * +payAm+"</td>"; message += "  </tr>"; message += "  <tr>";
			 * message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>거래종류</td>"
			 * ; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:left;vertical-align:top'  class='tg-v6ky'>신용 승인</td>"
			 * ; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>세금</td>"
			 * ; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:right;vertical-align:top'  class='tg-rd6t'>0</td>"
			 * ; message += "  </tr>"; message += "  <tr>"; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>대표자</td>"
			 * ; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:left;vertical-align:top'  class='tg-v6ky'>김해성외1</td>"
			 * ; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>봉사료</td>"
			 * ; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:right;vertical-align:top'  class='tg-rd6t'>0</td>"
			 * ; message += "  </tr>"; message += "  <tr>"; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>승인번호</td>"
			 * ; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:left;vertical-align:top'  class='tg-v6ky'>18222747</td>"
			 * ; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>합계</td>"
			 * ; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:right;vertical-align:top'  class='tg-rd6t'>"
			 * +payAm+"</td>"; message += "  </tr>"; message += "  <tr>";
			 * message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>가맹점명</td>"
			 * ; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:left;vertical-align:top'  class='tg-v6ky' colspan='3'>"
			 * +etpNm+"</td>"; message += "  </tr>"; message += "  <tr>";
			 * message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>가맹점주소</td>"
			 * ; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:left;vertical-align:top'  class='tg-v6ky' colspan='3'>서울 중구 퇴계로100</td>"
			 * ; message += "  </tr>"; message += "  <tr>"; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>결제대행사</td>"
			 * ; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:left;vertical-align:top'  class='tg-v6ky' colspan='3'>(주)신세계페이먼츠</td>"
			 * ; message += "  </tr>"; message += "  <tr>"; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>문의전화</td>"
			 * ; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:left;vertical-align:top'  class='tg-v6ky'>1588-4349</td>"
			 * ; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-weight:bold;font-size:12px;color:#333333;text-align:left;vertical-align:top'  class='tg-f9es'>서명</td>"
			 * ; message +=
			 * "    <td style='font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;font-size:12px;background-color:#ffffff;color:#333333;text-align:right;vertical-align:top'  class='tg-rd6t'>나병준</td>"
			 * ; message += "  </tr>"; message += "</table>"; message +=
			 * "</div>"; message += "</body>"; message += "</html>";
			 */

			JSONParser jsonParser = new JSONParser();
			// JSON데이터를 넣어 JSON Object 로 만들어 준다.
			JSONObject jsonObject = null;
			JSONObject jsonObject2 = null;
			JSONObject jsonObject3 = null;
			JSONObject jsonObject4 = null;
			try {
				jsonObject = (JSONObject) jsonParser.parse(emailSendJsonData);
				// 이메일 수신자
				InternetAddress to = new InternetAddress((String) jsonObject.get("emailInfo"));
				msg.setRecipient(Message.RecipientType.TO, to);
				System.out.println("jsonObject - "+jsonObject);
				System.out.println("emailInfo  - "+jsonObject.get("emailInfo"));
				System.out.println("cashDivObj - "+jsonObject.get("cashDivObj"));
				System.out.println("userInfo   - "+jsonObject.get("userInfo"));
				System.out.println(jsonObject2 = (JSONObject) jsonParser.parse(jsonObject.get("cashDivObj").toString()));
				System.out.println(jsonObject4 = (JSONObject) jsonParser.parse(jsonObject.get("dtlInfoObj").toString()));
				System.out.println("jsonObject2 size - "+jsonObject2.size());
				// message contains HTML markups
				String message = "<i>" + jsonObject.get("userInfo") + "님이 사용하신 전자영수증 입니다.</i><br>";
				message += "<!DOCTYPE html>																																							";
				message += "<html xmlns='http://www.w3.org/1999/xhtml'>                                                                                                                             ";
				message += "<head>                                                                                                                                                                  ";
				message += "<meta http-equiv='Content-Type' content='text/html;' />                                                                                                   ";
				
				
				message += "<title>이메일</title>                                                                                                                                                      ";
				message += "</head>                                                                                                                                                                 ";
				message += "<body>                                                                                                                                                                  ";
				message += "<table border='0' cellpadding='0' cellspacing='0' style=' background:#f2f2f2; width:690px; font-family:Dotum, '돋음'; font-size:12px; color:#999999;'>                    ";
				message += "	<tr><td><img src='http://117.52.97.40/theReal/images/top.jpg'></td></tr>                                                                                            ";
				message += "	<tr><td></td></tr>                                                                                                                                   ";
				message += "    <tr><td align='center'>                                                                                                                                             ";
				message += "    <table border='0' cellpadding='0' cellspacing='0' style='background:#e5e5e5; width:410px; border:1px solid #ddd;margin:50px;'>                                                  ";
				message += "    	<tr><td height='4px' style=' background:#e5e5e5;'></td></tr>                                                                                                    ";
				message += "    	<tr><td align='center'>                                                                                                                                         ";
				message += "        <table border='0' cellpadding='0' cellspacing='0' style='background:#fff; width:400px; border:1px solid #ddd;'>                                                 ";
				message += "            <tr><td><img src='http://117.52.97.40/theReal/images/paper_top.gif'></td></tr>                                                                              ";
				message += "            <tr><td align='center' style='padding:0px 30px 30px 30px;'>                                                                                                                                     ";
				
				
				
				message += "	<div class='rt_title' style='width: 40%;float: left;padding:20px 0 0px 0;float:left;'>";
				message += "		<p style='float: left; text-align: center; font-size: 1em; color : #000 clear:both'>THE REAL #</p>";
				message += " 		<p style='float: left; text-align: center; font-size: 1em; color : #000 clear:both'>Mobile Life Service</p>";
				message += "	</div>";
				
				message += "	<div class='rt_title' style='width: 30%;padding: 20px 0 0px 100px;float: right;'>";
				message += "		<p style='float: right;font-size: xx-small;text-align: center; font-size: 1em; color : #000 clear:both'>[모바일 영수증]</p>";
				message += "	</div>  ";
				
				
				message += "<div class='rt_txt01' style='text-align : left; font-size:1em ; color:#666; clear:both;'> ";
				message += "<div class='affli_div' style='text-align :center; font-size:18px; padding:13px 0px; color: #000; font-weight:700;'> "+ jsonObject4.get("shopName")  	+"</div></div>  ";
				message += "<div class='rt_txt01' style='text-align: left; font-size:1em; color:#666; clear: both; padding: 0px 10px;'>대표자명 : " + jsonObject4.get("shopCeo")  					+"</span></div>";
				message += "<div class='rt_txt01' style='text-align: left; font-size:1em; color:#666; clear: both; padding: 0px 10px;'>전화번호 : " + jsonObject4.get("shopTelNum")                 +"</span></div>";
				message += "<div class='rt_txt01' style='text-align: left; font-size:1em; color:#666; clear: both; padding: 0px 10px;'>사업자번호 : "+ jsonObject4.get("shopBizNo")                 	+"</span></div>";
				message += "<div class='rt_txt01' style='text-align: left; font-size:1em; color:#666; clear: both; padding: 0px 10px;'>주소 : "    + jsonObject4.get("shopAddr")                  	+"</span></div>";
				
				
				message	+= "<div class=\"rt_table\" style='border-top: 1px solid #ddd; margin:20px 5px 0px 5px;border-bottom: 1px solid #ddd;'> ";
				message	+= "	<ul class=\"rt_tabletitle\" style='border-bottom: 1px solid #ddd; background:#fff; list-style:none;padding-left:0px;margin: 3px 0px;'>  ";
				message	+= "		<li style='width:45%; float:left; padding:5px 0; text-align:center; color:#333; list-style:none; margin:0;'>상품명</li>";
				message	+= "		<li style='width:20%; float:left; padding:5px 0; text-align:center; color:#333; list-style:none; margin:0;'>단가</li>";
				message	+= "		<li style='width:10%; float:left; padding:5px 0; text-align:center; color:#333; list-style:none; margin:0;'>수량</li>";
				message	+= "		<li style='width:25%; text-aling:right; padding-right:5px; float:left; padding:5px 0; text-align:center; color:#333; list-style:none; margin:0;'>금액</li>";
				message	+= "		<hr style='clear:both; height: 0px; border: 0; margin:0; visibility: hidden'>";
				message	+= "	</ul>";
				
				
				JSONArray dtlGoodsArr = (JSONArray) jsonObject.get("dtlGoodsArr");
				for (int i = 0; i < dtlGoodsArr.size(); i++) {
					JSONObject dtlGoodsObj = (JSONObject) dtlGoodsArr.get(i);
					message	+= "<ul style=' background:#fff; list-style:none;padding-left:0px;margin: 3px 0px;'>";
					message	+= "<li class=\"rt_mlat01\" id=\"dtlGoods"+i+"\" style='width:45%; float:left; padding:5px 0; text-align:center; color:#333; list-style:none; margin:0;'>" +	dtlGoodsObj.get("dtlGoods")+"</li>";
					message	+= "<li class=\"rt_mlat02\" id=\"dtlPPrice"+i+"\" style='width:20%; float:left; padding:5px 0; text-align:center; color:#333; list-style:none; margin:0;'>"+	dtlGoodsObj.get("dtlPPrice")+"</li>";
					message	+= "<li class=\"rt_mlat03\" id=\"dtlQty"+i+"\" style='width:10%; float:left; padding:5px 0; text-align:center; color:#333; list-style:none; margin:0;'>"   +	dtlGoodsObj.get("dtlQty")+"</li>";
					message	+= "<li class=\"rt_mlat04\" id=\"dtlSlAmt"+i+"\" style='width:25%; text-aling:right; padding-right:5px; float:left; padding:5px 0; text-align:center; color:#333; list-style:none; margin:0;'>" +	dtlGoodsObj.get("dtlSlAmt")+"</li>";
					message	+= "<hr style='clear:both; height: 0px; border: 0; margin:0; visibility: hidden'>";
					message	+= "</ul>";
				}
				message	+= "</div>";
				
				message	+= "<div class='rt_etc' style='border-top:0px dashed #aaa;text-align:left; font-size:1.3em; font-weight:700; color:#333; clear:both; padding:10px; border-bottom:1px dashed #aaa'>과세물품가액<span style='text-align:right; float:right;'>"+ replaceComma((Integer.parseInt(((String)jsonObject4.get("sumPaidAmt")).replace(",", ""))-Integer.parseInt(((String) jsonObject4.get("sumTaxAmt")).replace(",", "")))) +"</span></div>";
				message	+= "<div class='rt_etc' style='text-align:left; font-size:1.3em; font-weight:700; color:#333; clear:both; padding:10px; border-bottom:1px dashed #aaa'>부 가 세<span style='text-align:right; float:right;'>"+ jsonObject4.get("sumTaxAmt") +"</span></div>";
				message	+= "<div class='rt_etc' style='text-align:left; font-size:1.3em; font-weight:700; color:#333; clear:both; padding:10px; border-bottom:1px dashed #aaa'>합계금액<span style='text-align:right; float:right;'>"+jsonObject4.get("sumPaidAmt") +"</span></div>";
				message	+= "<div class='rt_etc' style='text-align:left; font-size:1.3em; font-weight:700; color:#333; clear:both; padding:10px; border-bottom:1px dashed #aaa'>반환금액<span style='text-align:right; float:right;'>"+jsonObject4.get("sumChargeAmt")+"</span></div>";
				
				message	+= "<div class='rt_etc' style='text-align:left; font-size:1.3em; font-weight:700; color:#333; clear:both; padding:10px; border-bottom:1px dashed #aaa'>";
				message	+= "<div class='rt_copy' style='text-align:center; font-size:0.7em'>";
				message	+= "*** 신용승인정보(고객용) ***";
				

				if (jsonObject2.size() > 0) {
					System.out.println(jsonObject2.get("cardCashDtlDiv"));
					System.out.println(jsonObject2.get("cardCashRegDate"));
					if(jsonObject2.get("cardCashDtlDiv")!=null){
					message += "<div class='rt_txt01' style='text-align:left; font-size:1em; color: #666; clear:both'>거래종류 : " + jsonObject2.get("cardCashDtlDiv") + "</div>";
					}else{
						message += "<div class='rt_txt01' style='text-align:left; font-size:1em; color: #666; clear:both'>거래종류 : 신용결제 </div>";	
					}
					message	+= "<div class='rt_txt01' style='text-align:left; font-size:1em; color: #666; clear:both'>거래일시 : "+ date + "</div>";
				}
				//System.out.println(jsonObject3 = (JSONObject) jsonParser.parse(jsonObject.get("cardDivObj").toString()));
				jsonObject3 = (JSONObject) jsonParser.parse(jsonObject.get("cardDivObj").toString());
				System.out.println(jsonObject3.size());
				if (jsonObject3.size() > 0) {
					message += "<div class='rt_txt01' style='text-align:left; font-size:1em; color: #666; clear:both'>카드종류 : " + jsonObject3.get("cardICom") + "</div>";
					message += "<div class='rt_txt01' style='text-align:left; font-size:1em; color: #666; clear:both'>거래번호 : " + jsonObject3.get("cardNo") + "</div>";
					message += "<div class='rt_txt01' style='text-align:left; font-size:1em; color: #666; clear:both'>승인번호 : " + jsonObject3.get("cardAppNo") + "</div>";
				}
				
				message	+= "</div></div>";

				msg.setText(message);
			} catch (org.json.simple.parser.ParseException e) {
				e.printStackTrace();
			}

			// 이메일 내용

			// 이메일 헤더
			msg.setHeader("content-Type", "text/html");

			// 메일보내기
			javax.mail.Transport.send(msg);

		} catch (AddressException addr_e) {
			addr_e.printStackTrace();
		} catch (MessagingException msg_e) {
			msg_e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("resource")
	public static void monthAllSendMail(ArrayList list, String date, String email) throws Exception {
		
		System.out.println("emailSendJsonData:" + email);
		Properties p = System.getProperties();
		p.put("mail.smtp.starttls.enable", "true"); // gmail은 무조건 true 고정
		p.put("mail.smtp.host", "smtp.gmail.com"); // smtp 서버 주소
		p.put("mail.smtp.auth", "true"); // gmail은 무조건 true 고정
		p.put("mail.smtp.port", "587"); // gmail 포트

		Authenticator auth = new MyAuthentication();

		// session 생성 및 MimeMessage생성
		Session session = Session.getDefaultInstance(p, auth);
		MimeMessage msg = new MimeMessage(session);

		
		try {
			//xls 파일 제작
			System.out.println("■■■■■■■■■엑셀 테스트 접근 ■■■■■■■■");
			
			//String path = requsest.getServletContext().getRealPath("/");
			
			//System.out.println(path);
			System.out.println("■■■■■■■■■000000000 ■■■■■■■■");
			File file = new File("../kang.xlsx");
			FileOutputStream fos = new FileOutputStream(file);
			System.out.println("■■■■■■■■■eeeeeeeeeeeeeeeeeeeeee ■■■■■■■■");
			Workbook wb = null;
			wb = WorkbookFactory.create(file);
					
			
			System.out.println("■■■■■■■■■1111111111111111 ■■■■■■■■");
		    Sheet sheet = wb.getSheetAt(0);
			
			Row row = null;
			Cell cell = null;
			System.out.println("■■■■■■■■■222222222222222222 ■■■■■■■■");
			
			String[][] data = {{"1","강"},{"2","진"},{"3","우"}};
			
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
			
			cell = row.createCell(8);
			cell.setCellValue("승인날짜");
			System.out.println("■■■■■■■■■3333333333333333333■■■■■■■■");
			for (int i = 1; i <= list.size(); i++) {
				row = sheet.createRow(i);
				
				Map map = (Map)list.get(i-1);
				System.out.println(i + " ----" + map);
				cell = row.createCell(0);
				cell.setCellValue(i);
				
				cell = row.createCell(1);
				cell.setCellValue((String)map.get("APP_DT"));
				
				cell = row.createCell(2);
				cell.setCellValue((String)map.get("APP_CO"));
				
				cell = row.createCell(3);
				cell.setCellValue((String)map.get("CARD_APP"));
				
				cell = row.createCell(4);
				cell.setCellValue((String)map.get("CARD_APP_CO"));
				
				cell = row.createCell(5);
				cell.setCellValue((String)map.get("ACC_SUM_AMOUNT"));
				
				cell = row.createCell(6);
				cell.setCellValue((String)map.get("INST_DIV"));
				
				cell = row.createCell(7);
				cell.setCellValue((String)map.get("APP_MOUNT"));
				
				cell = row.createCell(8);
				cell.setCellValue((String)map.get("FRT_CREA_DTM"));
			}
			
			
			System.out.println("■■■■■■■■■444444444444444444 ■■■■■■■■");
			
			wb.write(fos);
			
			
			
			
			
			
			
			
			
			/*
			// 편지보낸시간
			msg.setSentDate(new Date());

			InternetAddress from = new InternetAddress();

			from = new InternetAddress("theReal<theReal@realmkt.co.kr>");

			// 이메일 발신자
			msg.setFrom(from);

			// 이메일 제목
			msg.setSubject("[더리얼마케팅] ~님의 "+"월 총 사용 내역입니다.", "UTF-8");
			


			JSONParser jsonParser = new JSONParser();
			// JSON데이터를 넣어 JSON Object 로 만들어 준다.
			JSONObject jsonObject = null;
			JSONObject jsonObject2 = null;
			JSONObject jsonObject3 = null;
			JSONObject jsonObject4 = null;
			try {
				// 이메일 수신자
				InternetAddress to = new InternetAddress(email);
				msg.setRecipient(Message.RecipientType.TO, to);
				System.out.println("jsonObject - "+jsonObject);
				System.out.println("emailInfo  - "+jsonObject.get("emailInfo"));
				System.out.println("cashDivObj - "+jsonObject.get("cashDivObj"));
				System.out.println("userInfo   - "+jsonObject.get("userInfo"));
				System.out.println(jsonObject2 = (JSONObject) jsonParser.parse(jsonObject.get("cashDivObj").toString()));
				System.out.println(jsonObject4 = (JSONObject) jsonParser.parse(jsonObject.get("dtlInfoObj").toString()));
				System.out.println("jsonObject2 size - "+jsonObject2.size());
				// message contains HTML markups
				String message = "<i>" + jsonObject.get("userInfo") + "님이 사용하신 전자영수증 입니다.</i><br>";
				message += "<!DOCTYPE html>																																							";
				message += "<html xmlns='http://www.w3.org/1999/xhtml'>                                                                                                                             ";
				message += "<head>                                                                                                                                                                  ";
				message += "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />                                                                                                   ";
				
				
				message += "<title>이메일</title>                                                                                                                                                      ";
				message += "</head>                                                                                                                                                                 ";
				message += "<body>                                                                                                                                                                  ";
				message += "<table border='0' cellpadding='0' cellspacing='0' style=' background:#f2f2f2; width:690px; font-family:Dotum, '돋음'; font-size:12px; color:#999999;'>                    ";
				message += "	<tr><td><img src='http://117.52.97.40/theReal/images/top.jpg'></td></tr>                                                                                            ";
				message += "	<tr><td></td></tr>                                                                                                                                   ";
				message += "    <tr><td align='center'>                                                                                                                                             ";
				message += "    <table border='0' cellpadding='0' cellspacing='0' style='background:#e5e5e5; width:410px; border:1px solid #ddd;margin:50px;'>                                                  ";
				message += "    	<tr><td height='4px' style=' background:#e5e5e5;'></td></tr>                                                                                                    ";
				message += "    	<tr><td align='center'>                                                                                                                                         ";
				message += "        <table border='0' cellpadding='0' cellspacing='0' style='background:#fff; width:400px; border:1px solid #ddd;'>                                                 ";
				message += "            <tr><td><img src='http://117.52.97.40/theReal/images/paper_top.gif'></td></tr>                                                                              ";
				message += "            <tr><td align='center' style='padding:0px 30px 30px 30px;'>                                                                                                                                     ";
				
				
				
				message += "	<div class='rt_title' style='width: 40%;float: left;padding:20px 0 0px 0;float:left;'>";
				message += "		<p style='float: left; text-align: center; font-size: 1em; color : #000 clear:both'>THE REAL #</p>";
				message += " 		<p style='float: left; text-align: center; font-size: 1em; color : #000 clear:both'>Mobile Life Service</p>";
				message += "	</div>";
				
				message += "	<div class='rt_title' style='width: 30%;padding: 20px 0 0px 100px;float: right;'>";
				message += "		<p style='float: right;font-size: xx-small;text-align: center; font-size: 1em; color : #000 clear:both'>[모바일 영수증]</p>";
				message += "	</div>  ";
				
				
				message += "<div class='rt_txt01' style='text-align : left; font-size:1em ; color:#666; clear:both;'> ";
				message += "<div class='affli_div' style='text-align :center; font-size:18px; padding:13px 0px; color: #000; font-weight:700;'> "+ jsonObject4.get("shopName")  	+"</div></div>  ";
				message += "<div class='rt_txt01' style='text-align: left; font-size:1em; color:#666; clear: both; padding: 0px 10px;'>대표자명 : " + jsonObject4.get("shopCeo")  					+"</span></div>";
				message += "<div class='rt_txt01' style='text-align: left; font-size:1em; color:#666; clear: both; padding: 0px 10px;'>전화번호 : " + jsonObject4.get("shopTelNum")                 +"</span></div>";
				message += "<div class='rt_txt01' style='text-align: left; font-size:1em; color:#666; clear: both; padding: 0px 10px;'>사업자번호 : "+ jsonObject4.get("shopBizNo")                 	+"</span></div>";
				message += "<div class='rt_txt01' style='text-align: left; font-size:1em; color:#666; clear: both; padding: 0px 10px;'>주소 : "    + jsonObject4.get("shopAddr")                  	+"</span></div>";
				
				
				message	+= "<div class=\"rt_table\" style='border-top: 1px solid #ddd; margin:20px 5px 0px 5px;border-bottom: 1px solid #ddd;'> ";
				message	+= "	<ul class=\"rt_tabletitle\" style='border-bottom: 1px solid #ddd; background:#fff; list-style:none;padding-left:0px;margin: 3px 0px;'>  ";
				message	+= "		<li style='width:45%; float:left; padding:5px 0; text-align:center; color:#333; list-style:none; margin:0;'>상품명</li>";
				message	+= "		<li style='width:20%; float:left; padding:5px 0; text-align:center; color:#333; list-style:none; margin:0;'>단가</li>";
				message	+= "		<li style='width:10%; float:left; padding:5px 0; text-align:center; color:#333; list-style:none; margin:0;'>수량</li>";
				message	+= "		<li style='width:25%; text-aling:right; padding-right:5px; float:left; padding:5px 0; text-align:center; color:#333; list-style:none; margin:0;'>금액</li>";
				message	+= "		<hr style='clear:both; height: 0px; border: 0; margin:0; visibility: hidden'>";
				message	+= "	</ul>";
				
				
				JSONArray dtlGoodsArr = (JSONArray) jsonObject.get("dtlGoodsArr");
				for (int i = 0; i < dtlGoodsArr.size(); i++) {
					JSONObject dtlGoodsObj = (JSONObject) dtlGoodsArr.get(i);
					message	+= "<ul style=' background:#fff; list-style:none;padding-left:0px;margin: 3px 0px;'>";
					message	+= "<li class=\"rt_mlat01\" id=\"dtlGoods"+i+"\" style='width:45%; float:left; padding:5px 0; text-align:center; color:#333; list-style:none; margin:0;'>" +	dtlGoodsObj.get("dtlGoods")+"</li>";
					message	+= "<li class=\"rt_mlat02\" id=\"dtlPPrice"+i+"\" style='width:20%; float:left; padding:5px 0; text-align:center; color:#333; list-style:none; margin:0;'>"+	dtlGoodsObj.get("dtlPPrice")+"</li>";
					message	+= "<li class=\"rt_mlat03\" id=\"dtlQty"+i+"\" style='width:10%; float:left; padding:5px 0; text-align:center; color:#333; list-style:none; margin:0;'>"   +	dtlGoodsObj.get("dtlQty")+"</li>";
					message	+= "<li class=\"rt_mlat04\" id=\"dtlSlAmt"+i+"\" style='width:25%; text-aling:right; padding-right:5px; float:left; padding:5px 0; text-align:center; color:#333; list-style:none; margin:0;'>" +	dtlGoodsObj.get("dtlSlAmt")+"</li>";
					message	+= "<hr style='clear:both; height: 0px; border: 0; margin:0; visibility: hidden'>";
					message	+= "</ul>";
				}
				message	+= "</div>";
				
				message	+= "<div class='rt_etc' style='border-top:0px dashed #aaa;text-align:left; font-size:1.3em; font-weight:700; color:#333; clear:both; padding:10px; border-bottom:1px dashed #aaa'>과세물품가액<span style='text-align:right; float:right;'>"+ replaceComma((Integer.parseInt(((String)jsonObject4.get("sumPaidAmt")).replace(",", ""))-Integer.parseInt(((String) jsonObject4.get("sumTaxAmt")).replace(",", "")))) +"</span></div>";
				message	+= "<div class='rt_etc' style='text-align:left; font-size:1.3em; font-weight:700; color:#333; clear:both; padding:10px; border-bottom:1px dashed #aaa'>부 가 세<span style='text-align:right; float:right;'>"+ jsonObject4.get("sumTaxAmt") +"</span></div>";
				message	+= "<div class='rt_etc' style='text-align:left; font-size:1.3em; font-weight:700; color:#333; clear:both; padding:10px; border-bottom:1px dashed #aaa'>합계금액<span style='text-align:right; float:right;'>"+jsonObject4.get("sumPaidAmt") +"</span></div>";
				message	+= "<div class='rt_etc' style='text-align:left; font-size:1.3em; font-weight:700; color:#333; clear:both; padding:10px; border-bottom:1px dashed #aaa'>반환금액<span style='text-align:right; float:right;'>"+jsonObject4.get("sumChargeAmt")+"</span></div>";
				
				message	+= "<div class='rt_etc' style='text-align:left; font-size:1.3em; font-weight:700; color:#333; clear:both; padding:10px; border-bottom:1px dashed #aaa'>";
				message	+= "<div class='rt_copy' style='text-align:center; font-size:0.7em'>";
				message	+= "*** 신용승인정보(고객용) ***";
				

				if (jsonObject2.size() > 0) {
					System.out.println(jsonObject2.get("cardCashDtlDiv"));
					System.out.println(jsonObject2.get("cardCashRegDate"));
					if(jsonObject2.get("cardCashDtlDiv")!=null){
					message += "<div class='rt_txt01' style='text-align:left; font-size:1em; color: #666; clear:both'>거래종류 : " + jsonObject2.get("cardCashDtlDiv") + "</div>";
					}else{
						message += "<div class='rt_txt01' style='text-align:left; font-size:1em; color: #666; clear:both'>거래종류 : 신용결제 </div>";	
					}
					message	+= "<div class='rt_txt01' style='text-align:left; font-size:1em; color: #666; clear:both'>거래일시 : "+ date + "</div>";
				}
				//System.out.println(jsonObject3 = (JSONObject) jsonParser.parse(jsonObject.get("cardDivObj").toString()));
				jsonObject3 = (JSONObject) jsonParser.parse(jsonObject.get("cardDivObj").toString());
				System.out.println(jsonObject3.size());
				if (jsonObject3.size() > 0) {
					message += "<div class='rt_txt01' style='text-align:left; font-size:1em; color: #666; clear:both'>카드종류 : " + jsonObject3.get("cardICom") + "</div>";
					message += "<div class='rt_txt01' style='text-align:left; font-size:1em; color: #666; clear:both'>거래번호 : " + jsonObject3.get("cardNo") + "</div>";
					message += "<div class='rt_txt01' style='text-align:left; font-size:1em; color: #666; clear:both'>승인번호 : " + jsonObject3.get("cardAppNo") + "</div>";
				}
				
				message	+= "</div></div>";

				msg.setText(message, "UTF-8");
			} catch (org.json.simple.parser.ParseException e) {
				e.printStackTrace();
			}

			// 이메일 내용

			// 이메일 헤더
			msg.setHeader("content-Type", "text/html");

			// 메일보내기
			javax.mail.Transport.send(msg);*/

		} catch (Exception addr_e) {
			addr_e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}






class MyAuthentication extends Authenticator {

	PasswordAuthentication pa;

	public MyAuthentication() {

		String id = "syj9530@gmail.com"; // 구글 ID
		String pw = "ehtusiwvihlcltcn"; // 구글 비밀번호

		// ID와 비밀번호를 입력한다.
		pa = new PasswordAuthentication(id, pw);

	}

	// 시스템에서 사용하는 인증정보
	@Override
	public PasswordAuthentication getPasswordAuthentication() {
		return pa;
	}
}