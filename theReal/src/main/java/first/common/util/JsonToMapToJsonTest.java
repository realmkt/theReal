package first.common.util;

import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonToMapToJsonTest {

	public static void main(String[] args) throws IOException {

		// JSON 데이터
		String jsonData = "{												";
		jsonData += "	\"emailInfo\": \"god870225@nate.com\",      ";
		jsonData += "	\"cashDivObj\": {                           ";
		jsonData += "		\"cardCashDtlDiv\": \"현금\",             ";
		jsonData += "		\"cardCashRegDate\": \"2016-10-20\"     ";
		jsonData += "	},                                          ";
		jsonData += "	\"cardDivObj\": {},                         ";
		jsonData += "	\"dtlInfoObj\": {                           ";
		jsonData += "		\"sumTaxAmt\": \"1,727\",               ";
		jsonData += "		\"sumAlAmt\": \"19,000\",               ";
		jsonData += "		\"sumPaidAmt\": \"19,000\",             ";
		jsonData += "		\"sumChargeAmt\": 0                     ";
		jsonData += "	},                                          ";
		jsonData += "	\"dtlGoodsArr\": [{                         ";
		jsonData += "		\"dtlGoods\": \"아메리카노\",               ";
		jsonData += "		\"dtlPPrice\": \"2,800\",               ";
		jsonData += "		\"dtlQty\": \"2\",                      ";
		jsonData += "		\"dtlSlAmt\": \"5,600\"                 ";
		jsonData += "	}, {                                        ";
		jsonData += "		\"dtlGoods\": \"카페라테\",                 ";
		jsonData += "		\"dtlPPrice\": \"3,200\",               ";
		jsonData += "		\"dtlQty\": \"2\",                      ";
		jsonData += "		\"dtlSlAmt\": \"6,400\"                 ";
		jsonData += "	}, {                                        ";
		jsonData += "		\"dtlGoods\": \"카페모카\",                 ";
		jsonData += "		\"dtlPPrice\": \"3,500\",               ";
		jsonData += "		\"dtlQty\": \"2\",                      ";
		jsonData += "		\"dtlSlAmt\": \"7,000\"                 ";
		jsonData += "	}]                                          ";
		jsonData += "}                                              ";

		JSONParser jsonParser = new JSONParser();
		// JSON데이터를 넣어 JSON Object 로 만들어 준다.
		JSONObject jsonObject = null;
		JSONObject jsonObject2 = null;
		JSONObject jsonObject3 = null;
		JSONObject jsonObject4 = null;
		try {
			jsonObject = (JSONObject) jsonParser.parse(jsonData);

			System.out.println(jsonObject);
			System.out.println(jsonObject.get("emailInfo"));
			System.out.println(jsonObject.get("cashDivObj"));
			System.out.println(jsonObject2 = (JSONObject) jsonParser.parse(jsonObject.get("cashDivObj").toString()));
			System.out.println(jsonObject2.size());
			if (jsonObject2.size() > 0) {
				System.out.println(jsonObject2.get("cardCashDtlDiv"));
				System.out.println(jsonObject2.get("cardCashRegDate"));
			}
			System.out.println(jsonObject3 = (JSONObject) jsonParser.parse(jsonObject.get("cardDivObj").toString()));
			System.out.println(jsonObject3.size());
			if (jsonObject3.size() > 0) {
				System.out.println(jsonObject3.get("cardICom"));
				System.out.println(jsonObject3.get("cardNo"));
				System.out.println(jsonObject3.get("cardCashDtlDiv"));
				System.out.println(jsonObject3.get("cardAppNo"));
				System.out.println(jsonObject3.get("cardInstall"));
			}
			System.out.println(jsonObject4 = (JSONObject) jsonParser.parse(jsonObject.get("dtlInfoObj").toString()));
			System.out.println(jsonObject4.get("sumTaxAmt"));
			System.out.println(jsonObject4.get("sumAlAmt"));
			System.out.println(jsonObject4.get("sumPaidAmt"));
			System.out.println(jsonObject4.get("sumChargeAmt"));

			JSONArray dtlGoodsArr = (JSONArray) jsonObject.get("dtlGoodsArr");
			for (int i = 0; i < dtlGoodsArr.size(); i++) {
				JSONObject dtlGoodsObj = (JSONObject) dtlGoodsArr.get(i);
				System.out.println("dtlGoods==>" + dtlGoodsObj.get("dtlGoods"));
				System.out.println("dtlQty==>" + dtlGoodsObj.get("dtlPPrice"));
				System.out.println("dtlQty==>" + dtlGoodsObj.get("dtlQty"));
				System.out.println("dtlSlAmt==>" + dtlGoodsObj.get("dtlSlAmt"));
			}

		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}

	}
}
