package first.common.filter;

import java.util.HashMap;

import javax.annotation.Resource;

import org.jfree.util.Log;
import org.omg.Messaging.SyncScopeHelper;

import first.common.util.CommonUtils;
import first.sample.controller.ReceiptController;
import first.sample.service.ReceiptService;

public class SmsParse {
	
	@Resource(name = "receiptService")
	static private ReceiptService receiptService;
	
	static String samsungCardTelNo  = "15888900";
	static String wooriCardTelNo 	= "15889955";
	static String nhCardTelNo 		= "15881600";
	static String bcCardTelNo 		= "15884000";
	static String shinhanTelNo 		= "15447200";
	static String lotteTelNo 		= "15888100";
	static String kbCardTelNo 		= "15881688";
	static String kbChkCardTelNo 	= "16449999";
	static String hanaCardTelNo 	= "18001111";
	static String testCardTelNo 	= "01074505585";
	static String testCardTelNo2 	= "01074960125";
	
	static String app_dt = "";
	static String app_co = "";
	
	public static boolean isAmount(String st){
		boolean flag = false;
		if(st.contains("원")){
			if(!(st.contains("누적") || st.contains("잔액"))){
				try {
					st = st.replace("원", "").replace(",", "").replace("(금액)", "");
					int integ = Integer.parseInt(st);
					flag =  true;
				} catch (Exception e) {
					System.out.println("sample.SmsParse.java : isAmount - " + e);
				}
			}
		}
		return flag;
	}
	
	public static HashMap<String,Object> smsCardParse(String sendTelNo, String recTelNo, String sms ){
		HashMap<String, Object> insertMap = new HashMap<String, Object>();
		app_dt = "";
		app_co = "";
			try {
				insertMap.put("REC_TEN_NO", recTelNo);
				insertMap.put("SEND_TEN_NO", sendTelNo);
				sms = sms.replace("  ", " ").replace("   ", " ").replace("    ", " ").replace("[Web발신]", "");
				if(sms.contains("한도") || sms.contains("초과") || sms.contains("금지") || sms.contains("도용") || sms.contains("신고") || sms.contains("도난") || 
				   sms.contains("부족") || sms.contains("정지") || sms.contains("대출") || sms.contains("금리") || sms.contains("이자") || sms.contains("이율") ||
				   sms.contains("포인트사용") || sms.contains("결제대금") || sms.contains("결제예정") || sms.contains("거절") || sms.contains("유가보조금")){
					return null;
				}
				
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■   삼성카드
				if (samsungCardTelNo.equals(sendTelNo)) {
					System.out.println("Start Insert SMS Data :: 삼성카드");
					sms = sms.replace("\n", " ");
					String[] smsStr = sms.split(" ");
					
					for (int i = 0; i < smsStr.length; i++) {
						System.out.println("문자 분해 :: "+smsStr[i]);
					
						if(i==0){
							insertMap.put("CARD_APP_CO", "삼성카드");
							insertMap.put("INST_DIV", "체크");
						}
						
						//카드 승인&취소 건
						if(smsStr[i].contains("승인") && !(smsStr[i].contains("취소"))){
							insertMap.put("CARD_APP_DIV", "승인");
						}
						else if(smsStr[i].contains("취소")){
							insertMap.put("CARD_APP_DIV", "취소");
						}
						
						//자동결제 
						else if(smsStr[i].contains("자동결제")){
							insertMap.put("CARD_APP_DIV", "자동결제");
						}
						
						//이름 (DB저장 X)
						else if(smsStr[i].matches(".*[*].*") &&  !(smsStr[i].contains("삼성"))){
							 insertMap.put("USER_NM", smsStr[i]);
						}
						
						//결제 날짜
						else if(smsStr[i].matches("^[0-9][0-9][/][0-9][0-9]$")){
							app_dt +=  smsStr[i];
							insertMap.put("APP_DT", app_dt);
						}else if(smsStr[i].matches("^[0-9][0-9][:][0-9][0-9]$")){
							app_dt += " "+ smsStr[i];
							insertMap.put("APP_DT", app_dt);
						}
						
						//누적 금액 구분
						else if(smsStr[i].contains("누적") && smsStr[i].contains("원")){
							String acc_sum_amount = smsStr[i];
							acc_sum_amount = acc_sum_amount.replace("," , "");
							acc_sum_amount = acc_sum_amount.replace("원", "");
							acc_sum_amount = acc_sum_amount.replace("누적", "");
							insertMap.put("ACC_SUM_AMOUNT", acc_sum_amount);
						}
						
						//결제 금액 구분
						else if(isAmount(smsStr[i])){
							String app_amount = smsStr[i];
							app_amount = app_amount.replace(",", "");
							app_amount = app_amount.replace("원", "");
							insertMap.put("APP_AMOUNT", app_amount);
						}
						
						//일시불 & 할부 개월
						else if(smsStr[i].contains("일시불") || smsStr[i].contains("개월")){
							insertMap.put("INST_DIV", smsStr[i]);
						}
						
						//상호명
						else{
							if(i!=0){
								app_co += smsStr[i]+" ";
								insertMap.put("APP_CO", app_co);
								}
						}
					}
					
					//상호명 Null
					if(insertMap.get("APP_CO").equals("") || insertMap.get("APP_CO")==null){
						insertMap.put("APP_CO", "미확인 가맹점");
					}
					
					if(insertMap.get("APP_AMOUNT").equals("") || insertMap.get("APP_AMOUNT")==null){
						insertMap.put("APP_AMOUNT", "0");
					}
					
					insertMap.put("APP_CO_DIV", CommonUtils.smsAppCoDicParse(app_co));
					
					
					System.out.println("Parsing End Insert SMS Data :: 삼성카드");
					System.out.println("Map Data :: " + insertMap);
					
					
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■   우리카드					
				}else if(wooriCardTelNo.equals(sendTelNo)){
					System.out.println("Start Insert SMS Data :: 우리카드");
					sms = sms.replace("\n", " ");
					String[] smsStr = sms.split(" ");
					
					for (int i = 0; i < smsStr.length; i++) {
						System.out.println("문자 분해 :: "+smsStr[i]);
					
						if(i==0){ 
							insertMap.put("CARD_APP_CO", "우리카드");
							insertMap.put("INST_DIV", "체크");
						}
						if(smsStr[i].matches(".*[(][0-9][*].*") && !((smsStr[i].contains("승인")) || (smsStr[i].contains("취소")))){
							
						}
						//버려야할 데이터
						else if(smsStr[i].contains("적립예정")){
						
						}
						else if(smsStr[i].contains("지급가능액")){ }//우리카드 체크카드 "지급가능액" 남은금액이므로 분리할필요 x 
						//카드 승인&취소 건
						else if(smsStr[i].contains("승인") && !(smsStr[i].contains("취소"))){
							insertMap.put("CARD_APP_DIV", "승인");
						}
						else if(smsStr[i].contains("취소")){
							insertMap.put("CARD_APP_DIV", "취소");
						}
						
						//자동결제 
						else if(smsStr[i].contains("자동결제")){
							insertMap.put("CARD_APP_DIV", "자동결제");
						}
						
						//이름 (DB저장 X)
						else if(smsStr[i].matches(".*[*].*") &&  !(smsStr[i].contains("우리"))){
							 insertMap.put("USER_NM", smsStr[i]);
						}
						
						//결제 날짜
						else if(smsStr[i].matches("^[0-9][0-9][/][0-9][0-9]$")){
							app_dt +=  smsStr[i];
							insertMap.put("APP_DT", app_dt);
						}else if(smsStr[i].matches("^[0-9][0-9][:][0-9][0-9]$")){
							app_dt += " "+ smsStr[i];
							insertMap.put("APP_DT", app_dt);
						}
						
						//누적 금액 구분
						else if(smsStr[i].contains("누적") && smsStr[i].contains("원")){
							String acc_sum_amount = smsStr[i];
							acc_sum_amount = acc_sum_amount.replace("," , "");
							acc_sum_amount = acc_sum_amount.replace("원", "");
							acc_sum_amount = acc_sum_amount.replace("누적", "");
							insertMap.put("ACC_SUM_AMOUNT", acc_sum_amount);
						}
						
						//결제 금액 구분
						else if(isAmount(smsStr[i])){
							String app_amount = smsStr[i];
							app_amount = app_amount.replace(",", "");
							app_amount = app_amount.replace("원", "");
							insertMap.put("APP_AMOUNT", app_amount);
						}
						
						//일시불 & 할부 개월
						else if(smsStr[i].contains("일시불") || smsStr[i].contains("개월")){
							insertMap.put("INST_DIV", smsStr[i]);
						}
						
						//상호명
						else{
							if(i!=0){
								app_co += smsStr[i]+" ";
								insertMap.put("APP_CO", app_co);
								}
						}
						
						
					}
					//상호명 Null
					if(insertMap.get("APP_CO").equals("") || insertMap.get("APP_CO")==null){
						insertMap.put("APP_CO", "미확인 가맹점");
					}
					
					if(insertMap.get("APP_AMOUNT").equals("") || insertMap.get("APP_AMOUNT")==null){
						insertMap.put("APP_AMOUNT", "0");
					}
					
					insertMap.put("APP_CO_DIV", CommonUtils.smsAppCoDicParse(app_co));
					
					
					System.out.println("Parsing End Insert SMS Data :: 우리카드");
					System.out.println("Map Data :: " + insertMap);
				
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■   농협카드					
				}else if(nhCardTelNo.equals(sendTelNo)){
					System.out.println("Start Insert SMS Data :: NH농협카드");
				sms = sms.replace("\n", " ");
				
				String[] smsStr = sms.split(" ");
				
				for (int i = 0; i < smsStr.length; i++) {
					System.out.println("문자 분해 :: "+smsStr[i]);
				
					if(i==0){
						insertMap.put("CARD_APP_CO", "NH농협카드");
						insertMap.put("INST_DIV", "체크");
					}
					
					if(smsStr[i].matches(".*[(][0-9][*].*") && !((smsStr[i].contains("승인")) || (smsStr[i].contains("취소"))))
					{   }
					
					//버려야할 데이터
					else if(smsStr[i].contains("적립예정")){
						
					}
					
					
					//카드 승인&취소 건
					else if(smsStr[i].contains("승인") && !(smsStr[i].contains("취소"))){
						insertMap.put("CARD_APP_DIV", "승인");
					}
					else if(smsStr[i].contains("취소")){
						insertMap.put("CARD_APP_DIV", "취소");
					}
					
					//자동결제 
					else if(smsStr[i].contains("자동결제")){
						insertMap.put("CARD_APP_DIV", "자동결제");
					}
					
					//이름 (DB저장 X)
					else if((smsStr[i].matches(".*[*].*") &&  !(smsStr[i].contains("농협"))) || (smsStr[i].contains("법인") && smsStr[i].contains("님"))){
						 insertMap.put("USER_NM", smsStr[i]);
					}
					
					//결제 날짜
					else if(smsStr[i].matches("^[0-9][0-9][/][0-9][0-9]$")){
						app_dt +=  smsStr[i];
						insertMap.put("APP_DT", app_dt);
					}else if(smsStr[i].matches("^[0-9][0-9][:][0-9][0-9]$")){
						app_dt += " "+ smsStr[i];
						insertMap.put("APP_DT", app_dt);
					}
					
					//누적 금액 구분
					else if(smsStr[i].contains("누적") && smsStr[i].contains("원")){
						String acc_sum_amount = smsStr[i];
						acc_sum_amount = acc_sum_amount.replace("," , "");
						acc_sum_amount = acc_sum_amount.replace("원", "");
						acc_sum_amount = acc_sum_amount.replace("누적", "");
						insertMap.put("ACC_SUM_AMOUNT", acc_sum_amount);
					}
					
					//결제 금액 구분
					else if(isAmount(smsStr[i])){
						String app_amount = smsStr[i];
						app_amount = app_amount.replace(",", "");
						app_amount = app_amount.replace("원", "");
						insertMap.put("APP_AMOUNT", app_amount);
					}
					
					//일시불 & 할부 개월
					else if(smsStr[i].contains("일시불") || smsStr[i].contains("개월")){
						insertMap.put("INST_DIV", smsStr[i]);
					}
					
					//상호명
					else{
						if(i!=0 && !smsStr[i].equals("체크")){
							app_co += smsStr[i]+" ";
							insertMap.put("APP_CO", app_co);
							}
					}
				}
				
				//상호명 Null
				if(insertMap.get("APP_CO").equals("") || insertMap.get("APP_CO")==null){
					insertMap.put("APP_CO", "미확인 가맹점");
				}
				
				if(insertMap.get("APP_AMOUNT").equals("") || insertMap.get("APP_AMOUNT")==null){
					insertMap.put("APP_AMOUNT", "0");
				}
				
				insertMap.put("APP_CO_DIV", CommonUtils.smsAppCoDicParse(app_co));
				
				
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■   BC카드						
				
			}else if(bcCardTelNo.equals(sendTelNo)){
				System.out.println("Start Insert SMS Data :: BC카드");
				sms = sms.replace("\n", " ");
				
				String[] smsStr = sms.split(" ");
				
				for (int i = 0; i < smsStr.length; i++) {
					System.out.println("문자 분해 :: "+smsStr[i]);
					
					
					if(i==0){
						insertMap.put("INST_DIV", "체크");
					}
					if(smsStr[i].contains("SC") && smsStr[i].contains("BC")){
						insertMap.put("CARD_APP_CO", "SC은행BC카드");
					}else if(smsStr[i].contains("농협") && smsStr[i].contains("BC")){
						insertMap.put("CARD_APP_CO", "농협은행BC카드");
					}else if(smsStr[i].contains("기업") && smsStr[i].contains("BC")){
						insertMap.put("CARD_APP_CO", "기업은행BC카드");
					}
					
					//카드 승인&취소 건
					if(smsStr[i].contains("승인") && !(smsStr[i].contains("취소"))){
						insertMap.put("CARD_APP_DIV", "승인");
					}
					else if(smsStr[i].contains("취소")){
						insertMap.put("CARD_APP_DIV", "취소");
					}
					
					//자동결제 
					else if(smsStr[i].contains("자동결제")){
						insertMap.put("CARD_APP_DIV", "자동결제");
					}
					
					//이름 (DB저장 X)
					else if((smsStr[i].matches(".*[*].*") &&  !(smsStr[i].contains("BC"))) || (smsStr[i].contains("법인") && smsStr[i].contains("님"))){
						 insertMap.put("USER_NM", smsStr[i]);
					}
					
					//결제 날짜
					else if(smsStr[i].matches("^[0-9][0-9][/][0-9][0-9]$")){
						app_dt +=  smsStr[i];
						insertMap.put("APP_DT", app_dt);
					}else if(smsStr[i].matches("^[0-9][0-9][:][0-9][0-9]$")){
						app_dt += " "+ smsStr[i];
						insertMap.put("APP_DT", app_dt);
					}
					
					//누적 금액 구분
					else if(smsStr[i].contains("누적") && smsStr[i].contains("원")){
						String acc_sum_amount = smsStr[i];
						acc_sum_amount = acc_sum_amount.replace("," , "");
						acc_sum_amount = acc_sum_amount.replace("원", "");
						acc_sum_amount = acc_sum_amount.replace("누적", "");
						insertMap.put("ACC_SUM_AMOUNT", acc_sum_amount);
					}
					
					//결제 금액 구분
					else if(isAmount(smsStr[i])){
						String app_amount = smsStr[i];
						app_amount = app_amount.replace(",", "");
						app_amount = app_amount.replace("원", "");
						insertMap.put("APP_AMOUNT", app_amount);
					}
					
					//일시불 & 할부 개월
					else if(smsStr[i].contains("일시불") || smsStr[i].contains("개월")){
						insertMap.put("INST_DIV", smsStr[i]);
					}
					
					//상호명
					else{
						if(i!=0 && !smsStr[i].contains("법인")){
							app_co += smsStr[i]+" ";
							insertMap.put("APP_CO", app_co);
							}
					}
					
					
					
				}
				
				//상호명 Null
				if(insertMap.get("APP_CO").equals("") || insertMap.get("APP_CO")==null){
					insertMap.put("APP_CO", "미확인 가맹점");
				}
				
				if(insertMap.get("APP_AMOUNT").equals("") || insertMap.get("APP_AMOUNT")==null){
					insertMap.put("APP_AMOUNT", "0");
				}
				
				insertMap.put("APP_CO_DIV", CommonUtils.smsAppCoDicParse(app_co));
				
				
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■   신한카드	
			}else if(shinhanTelNo.equals(sendTelNo) || testCardTelNo.equals(sendTelNo) || testCardTelNo2.equals(sendTelNo)){
				System.out.println("Start Insert SMS Data :: 신한카드");
				sms = sms.replace("(금액)","").replace("(일시불)"," (일시불) ").replace("원(", "원 (").replace("월)", "월) ");
				sms = sms.replace("\n", " ");
				
				String[] smsStr = sms.split(" ");
				
				for (int i = 0; i < smsStr.length; i++) {
					System.out.println("문자 분해 :: "+smsStr[i]);
					
					if(i==0){
						insertMap.put("CARD_APP_CO", "신한카드");
						insertMap.put("INST_DIV", "체크");
					}
					
					
					if(smsStr[i].matches(".*[(][0-9][*].*") && !((smsStr[i].contains("승인")) || (smsStr[i].contains("취소")))){
						
					}
					//카드 승인&취소 건
					else if(smsStr[i].contains("승인") && !(smsStr[i].contains("취소"))){
						insertMap.put("CARD_APP_DIV", "승인");
					}
					else if(smsStr[i].contains("취소")){
						insertMap.put("CARD_APP_DIV", "취소");
					}
					
					//자동결제 
					else if(smsStr[i].contains("자동결제")){
						insertMap.put("CARD_APP_DIV", "자동결제");
					}
					
					//이름 (DB저장 X)
					else if((smsStr[i].matches(".*[*].*") &&  !(smsStr[i].contains("신한"))) || (smsStr[i].contains("법인") && smsStr[i].contains("님"))){
						 insertMap.put("USER_NM", smsStr[i]);
					}
					
					//결제 날짜
					else if(smsStr[i].matches("^[0-9][0-9][/][0-9][0-9]$")){
						app_dt +=  smsStr[i];
						insertMap.put("APP_DT", app_dt);
					}else if(smsStr[i].matches("^[0-9][0-9][:][0-9][0-9]$")){
						app_dt += " "+ smsStr[i];
						insertMap.put("APP_DT", app_dt);
					}
					
					//누적 금액 구분
					else if((smsStr[i].contains("누적")||(smsStr[i].contains("잔액"))) && smsStr[i].contains("원")){
						String acc_sum_amount = smsStr[i];
						acc_sum_amount = acc_sum_amount.replace("," , "");
						acc_sum_amount = acc_sum_amount.replace("원", "");
						acc_sum_amount = acc_sum_amount.replace("누적", "");
						insertMap.put("ACC_SUM_AMOUNT", acc_sum_amount);
					}
					
					//결제 금액 구분
					else if(isAmount(smsStr[i])){
						String app_amount = smsStr[i];
						app_amount = app_amount.replace(",", "");
						app_amount = app_amount.replace("원", "");
						insertMap.put("APP_AMOUNT", app_amount);
					}
					
					//일시불 & 할부 개월
					else if(smsStr[i].contains("일시불") || smsStr[i].contains("개월")){
						insertMap.put("INST_DIV", smsStr[i].replace("(", "").replace(")", ""));
					}
					
					//상호명
					else{
						if(i!=0){
							app_co += smsStr[i]+" ";
							insertMap.put("APP_CO", app_co);
							}
					}
					
					
					
				}
				
				//상호명 Null
				if(insertMap.get("APP_CO").equals("") || insertMap.get("APP_CO")==null){
					insertMap.put("APP_CO", "미확인 가맹점");
				}
				
				if(insertMap.get("APP_AMOUNT").equals("") || insertMap.get("APP_AMOUNT")==null){
					insertMap.put("APP_AMOUNT", "0");
				}
				
				insertMap.put("APP_CO_DIV", CommonUtils.smsAppCoDicParse(app_co));
				
				
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■   국민카드					
			}else if(kbCardTelNo.equals(sendTelNo) || kbChkCardTelNo.equals(sendTelNo)){
				System.out.println("Start Insert SMS Data :: 국민카드");
				sms = sms.replace("\n", " ").replace("사용", "");
				
				
				String[] smsStr = sms.split(" ");
				
				for (int i = 0; i < smsStr.length; i++) {
					System.out.println("문자 분해 :: "+smsStr[i]);
					
					if(i==0){
						insertMap.put("CARD_APP_CO", "국민카드");
						insertMap.put("INST_DIV", "체크");
					}
					if(smsStr[i].matches(".*[(][0-9][*].*") && (!(smsStr[i].contains("승인")) || !(smsStr[i].contains("취소")))){
						
					}
					//카드 승인&취소 건
					else if(smsStr[i].contains("승인") && !(smsStr[i].contains("취소"))){
						insertMap.put("CARD_APP_DIV", "승인");
					}
					else if(smsStr[i].contains("취소")){
						insertMap.put("CARD_APP_DIV", "취소");
					}
					
					else if(smsStr[i].matches(".*[*][0-9][*].*")){
						
					}
					
					//자동결제 
					else if(smsStr[i].contains("자동결제")){
						insertMap.put("CARD_APP_DIV", "자동결제");
					}
					
					//이름 (DB저장 X)
					else if(smsStr[i].matches(".*[*].*") &&  !(smsStr[i].contains("국민"))){
						 insertMap.put("USER_NM", smsStr[i]);
					}
					
					//결제 날짜
					else if(smsStr[i].matches("^[0-9][0-9][/][0-9][0-9]$")){
						app_dt +=  smsStr[i];
						insertMap.put("APP_DT", app_dt);
					}else if(smsStr[i].matches("^[0-9][0-9][:][0-9][0-9]$")){
						app_dt += " "+ smsStr[i];
						insertMap.put("APP_DT", app_dt);
					}
					
					//누적 금액 구분
					else if(smsStr[i].contains("누적") && smsStr[i].contains("원")){
						String acc_sum_amount = smsStr[i];
						acc_sum_amount = acc_sum_amount.replace("," , "");
						acc_sum_amount = acc_sum_amount.replace("원", "");
						acc_sum_amount = acc_sum_amount.replace("누적", "");
						insertMap.put("ACC_SUM_AMOUNT", acc_sum_amount);
					}
					
					//결제 금액 구분
					else if(isAmount(smsStr[i])){
						String app_amount = smsStr[i];
						app_amount = app_amount.replace(",", "");
						app_amount = app_amount.replace("원", "");
						insertMap.put("APP_AMOUNT", app_amount);
					}
					
					//일시불 & 할부 개월
					else if(smsStr[i].contains("일시불") || smsStr[i].contains("개월")){
						insertMap.put("INST_DIV", smsStr[i]);
					}
					
					//상호명
					else{
						if(i!=0){
							app_co += smsStr[i]+" ";
							insertMap.put("APP_CO", app_co);
							}
					}

				}
				
				//상호명 Null
				if(insertMap.get("APP_CO").equals("") || insertMap.get("APP_CO")==null){
					insertMap.put("APP_CO", "미확인 가맹점");
				}
				
				if(insertMap.get("APP_AMOUNT").equals("") || insertMap.get("APP_AMOUNT")==null){
					insertMap.put("APP_AMOUNT", "0");
				}
				
				insertMap.put("APP_CO_DIV", CommonUtils.smsAppCoDicParse(app_co));
				
			}
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■   하나카드			
			else if(hanaCardTelNo.equals(sendTelNo)){
				System.out.println("Start Insert SMS Data :: 하나카드");
				sms = sms.replace("\n", " ");
				
				
				String[] smsStr = sms.split(" ");
				
				for (int i = 0; i < smsStr.length; i++) {
					System.out.println("문자 분해 :: "+smsStr[i]);
					
					if(i==0){
						insertMap.put("CARD_APP_CO", "하나카드");
						insertMap.put("INST_DIV", "체크");
					}
					
					if(smsStr[i].matches(".*[(][0-9][*].*") && (!(smsStr[i].contains("승인")) || !(smsStr[i].contains("취소")))){
						
					}
					//카드 승인&취소 건
					else if(smsStr[i].contains("승인") && !(smsStr[i].contains("취소"))){
						insertMap.put("CARD_APP_DIV", "승인");
					}
					else if(smsStr[i].contains("취소")){
						insertMap.put("CARD_APP_DIV", "취소");
					}
					
					//자동결제 
					else if(smsStr[i].contains("자동결제")){
						insertMap.put("CARD_APP_DIV", "자동결제");
					}
					
					//이름 (DB저장 X)
					else if(smsStr[i].matches(".*[*].*") &&  !(smsStr[i].contains("하나"))){
						 insertMap.put("USER_NM", smsStr[i]);
					}
					
					//결제 날짜
					else if(smsStr[i].matches("^[0-9][0-9][/][0-9][0-9]$")){
						app_dt +=  smsStr[i];
						insertMap.put("APP_DT", app_dt);
					}else if(smsStr[i].matches("^[0-9][0-9][:][0-9][0-9]$")){
						app_dt += " "+ smsStr[i];
						insertMap.put("APP_DT", app_dt);
					}
					
					//누적 금액 구분
					else if(smsStr[i].contains("누적") && smsStr[i].contains("원")){
						String acc_sum_amount = smsStr[i];
						acc_sum_amount = acc_sum_amount.replace("," , "");
						acc_sum_amount = acc_sum_amount.replace("원", "");
						acc_sum_amount = acc_sum_amount.replace("누적", "");
						insertMap.put("ACC_SUM_AMOUNT", acc_sum_amount);
					}
					
					//결제 금액 구분
					else if(isAmount(smsStr[i])){
						String app_amount = smsStr[i];
						app_amount = app_amount.replace(",", "");
						app_amount = app_amount.replace("원", "");
						insertMap.put("APP_AMOUNT", app_amount);
					}
					
					//일시불 & 할부 개월
					else if(smsStr[i].contains("일시불") || smsStr[i].contains("개월")){
						insertMap.put("INST_DIV", smsStr[i]);
					}
					
					//상호명
					else{
						if(i!=0 && !smsStr[i].equals("누적")){
							app_co += smsStr[i]+" ";
							insertMap.put("APP_CO", app_co);
							}
					}
					
				}
				
				//상호명 Null
				if(insertMap.get("APP_CO").equals("") || insertMap.get("APP_CO")==null){
					insertMap.put("APP_CO", "미확인 가맹점");
				}
				
				if(insertMap.get("APP_AMOUNT").equals("") || insertMap.get("APP_AMOUNT")==null){
					insertMap.put("APP_AMOUNT", "0");
				}
				
				insertMap.put("APP_CO_DIV", CommonUtils.smsAppCoDicParse(app_co));
				
				
				
				
				
			}	
				
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■  롯데카드			
			else if(lotteTelNo.equals(sendTelNo)){
				System.out.println("Start Insert SMS Data :: 롯데카드");
				sms = sms.replace("\n", " ");
				
				String[] smsStr = sms.split(" ");
				
				for (int i = 0; i < smsStr.length; i++) {
					System.out.println("문자 분해 :: "+smsStr[i]);
					
					if(i==0){
						insertMap.put("CARD_APP_CO", "롯데카드");
						insertMap.put("INST_DIV", "체크");
					}
					
					if(smsStr[i].matches("^.[롯데][0-9][*][0-9][*]$")){ }
					//카드 승인&취소 건
					else if(smsStr[i].contains("승인") && !(smsStr[i].contains("취소"))){
						insertMap.put("CARD_APP_DIV", "승인");
					}
					else if(smsStr[i].contains("취소")){
						insertMap.put("CARD_APP_DIV", "취소");
					}
					
					//자동결제 
					else if(smsStr[i].contains("자동결제")){
						insertMap.put("CARD_APP_DIV", "자동결제");
					}
					
					//이름 (DB저장 X)
					else if(smsStr[i].matches(".*[*].*") &&  !(smsStr[i].contains("롯데"))){
						 insertMap.put("USER_NM", smsStr[i]);
					}
					
					//결제 날짜
					else if(smsStr[i].matches("^[0-9][0-9][/][0-9][0-9]$")){
						app_dt +=  smsStr[i];
						insertMap.put("APP_DT", app_dt);
					}else if(smsStr[i].matches("^[0-9][0-9][:][0-9][0-9]$")){
						app_dt += " "+ smsStr[i];
						insertMap.put("APP_DT", app_dt);
					}
					
					//누적 금액 구분
					else if(smsStr[i].contains("누적") && smsStr[i].contains("원")){
						String acc_sum_amount = smsStr[i];
						acc_sum_amount = acc_sum_amount.replace("," , "");
						acc_sum_amount = acc_sum_amount.replace("원", "");
						acc_sum_amount = acc_sum_amount.replace("누적", "");
						insertMap.put("ACC_SUM_AMOUNT", acc_sum_amount);
					}
					
					//결제 금액 구분
					else if(isAmount(smsStr[i])){
						String app_amount = smsStr[i];
						app_amount = app_amount.replace(",", "");
						app_amount = app_amount.replace("원", "");
						insertMap.put("APP_AMOUNT", app_amount);
					}
					
					//일시불 & 할부 개월
					else if(smsStr[i].contains("일시불") || smsStr[i].contains("개월")){
						insertMap.put("INST_DIV", smsStr[i]);
					}
					
					//상호명
					else{
						if(i!=0){
						app_co += smsStr[i]+" ";
						insertMap.put("APP_CO", app_co);
						}
					}
				}
				//상호명 Null
				if(insertMap.get("APP_CO").equals("") || insertMap.get("APP_CO")==null){
					insertMap.put("APP_CO", "미확인 가맹점");
				}
				if(insertMap.get("APP_AMOUNT").equals("") || insertMap.get("APP_AMOUNT")==null){
					insertMap.put("APP_AMOUNT", "0");
				}
				insertMap.put("APP_CO_DIV", CommonUtils.smsAppCoDicParse(app_co));
			}	
			} catch (Exception e) {
				System.out.println("Error Insert SMS Data :: " + e);
				return null;
			}
		
			
		return insertMap;
		
	}

}
