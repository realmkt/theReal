package first.common.util;

import java.util.HashMap;

import first.sample.service.ReceiptService;

public class TestSamsungCard {
	private static ReceiptService receiptService;
	public static void main(String[] args) throws Exception {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		String test = "[Web발신]\n삼성카드승인7961조*호\r\n01/23 12:04 (주)티켓몬\r\n30,000원 일시불\r\n누적2,514,681원";
		
		//String test = "[Web발신]\n삼성카드승인9360설*진\r\n12/18 16:14 티머니택시\r\n4,000원 일시불\r\n누적2,289,720원";
		//String test = "[Web발신]\n삼성카드승인7624나*준\n01/20 15:17 신세계페이\n75,740원 일시불\n누적3,545,654원";
		//String[] test1 = var.find("message").toString().split("\r");
		String[] test1 = test.replace("\r","").split("\n");
		map.put("REC_TEN_NO", "01022086378");
		map.put("SEND_TEN_NO", "15888900");
		for(int i = 0; i<test1.length;i++){
			
			if(test1[i].contains(":") &&test1[i].contains("/")){
				
/*				System.out.println(test1[i]);
				System.out.println(test1[i].substring(0, (test1[i].indexOf(":")+3)));
				System.out.println(test1[i].substring((test1[i].indexOf(":")+4)));		*/
				map.put("APP_DT",test1[i].substring(0, (test1[i].indexOf(":")+3)).replace("\n", ""));
				map.put("APP_CO",test1[i].substring((test1[i].indexOf(":")+4)));
			}
			
			//사용금액
			if(test1[i].contains("카드승인")){
				map.put("CARD_APP_DIV","승인");
				map.put("CARD_APP_CO",test1[i].substring(0,(test1[i].indexOf("승인"))).replace("\n", "").replace("[Web발신]", ""));
			}
			if(test1[i].startsWith("\n누적")){
				map.put("ACC_SUM_AMMOUNT",test1[i].substring((test1[i].indexOf("적")+1)).replace("\n", ""));
			}
			if(test1[i].endsWith("일시불")){
				System.out.println("일시불");
				map.put("APP_AMOUNT",test1[i].substring(0,(test1[i].indexOf("원")+1)).replace("\n", "").replace("원", "").replace(",", ""));
				map.put("INST_DIV",test1[i].substring((test1[i].indexOf("원")+2)));
			}else if(test1[i].endsWith("개월")){
				System.out.println("개월");
				map.put("APP_AMOUNT",test1[i].substring(0,(test1[i].indexOf("원")+1)).replace("\n", "").replace("원", "").replace(",", ""));
				map.put("INST_DIV",test1[i].substring((test1[i].indexOf("원")+2)));
			};
		/*	if(Pattern.compile("\\[(.*?)\\]").matcher(test1[i]){
				
			}*/
		}		
		
/*		if(!"".equals(map.get("APP_AMOUNT")) && map.get("APP_AMOUNT") != null){
			receiptService.insertSmsData(map);
		}*/
		System.out.println("map:"+map);		
	}

}
