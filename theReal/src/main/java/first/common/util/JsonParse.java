package first.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.InvalidKeyException;
import org.json.simple.parser.ParseException;

public class JsonParse {
	// public static void Test(){

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException, InvalidKeyException, ParseException {

		// return lpointResData ;

		int timeout = 10;
		// 연결
		// URL url = new
		// URL("http://117.52.97.40/theReal/receipt/reqTheRealToLpoint.do");
		// URL url = new
		// URL("http://localhost:8080/theReal/receipt/reqTheRealToLpoint.do");
		// URL url = new
		// URL("http://117.52.97.40/theReal/receipt/insertReceiptData.do");
		// URL url = new
		// URL("http://localhost:8080/theReal/receipt/insertReceiptData.do");
		// URL url = new URL("http://192.168.100.111/app/cmu/api/requestptom");
		// URL url = new URL("https://op_dev.lpoint.com:8903/op");
		URL url = new URL("http://182.162.84.177/theReal/receipt/reqTheRealToLpointPlainText.do");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST"); // 보내는 타입
		conn.setRequestProperty("Accept-Language", "ko-kr,ko;q=0.8,en-us;q=0.5,en;q=0.3");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("Accept-Charset", "UTF-8");
		conn.setConnectTimeout(timeout);

		// 데이터
		String str = "";
		/*
		 * str += "{"; str += "	\"cashInfo\": {"; str +=
		 * "		\"cashDate\": \"20170105164146\","; str +=
		 * "		\"cashAppNo\": \"\","; str += "		\"cashType\": \"CH02\","
		 * ; str += "		\"cashNo\": \"\","; str +=
		 * "		\"cashAmt\": \"7000\""; str += "	},"; str +=
		 * "	\"cardInfo\": {"; str += "		\"cardNo\": \"\","; str +=
		 * "		\"cardAmt\": \"\","; str += "		\"cardDate\": \"\",";
		 * str += "		\"cardAppNo\": \"\","; str += "		\"cardICom\": \"\","
		 * ; str += "		\"cardInstallment\": \"\","; str +=
		 * "		\"cardPCom\": \"\""; str += "	},"; str +=
		 * "	\"userKey\": \"01077003578\","; str += "	\"shopInfo\": {";
		 * str += "		\"ceo\": \"손현준\","; str +=
		 * "		\"phone\": \"032-887-3338\","; str +=
		 * "		\"address\": \"인천 남구 용현동 664 1층\","; str +=
		 * "		\"name\": \"33떡볶이 용현점\","; str +=
		 * "		\"bizNo\": \"123-45-67890\","; str +=
		 * "		\"cashier\": \"손현준\""; str += "	},"; str +=
		 * "	\"customerInfo\": {"; str += "		\"customerCode\": \"\",";
		 * str += "		\"pointAmt\": \"\","; str +=
		 * "		\"customerPoint\": \"\","; str += "		\"getPoint\": \"\"";
		 * str += "	},"; str += "	\"salesList\": [{"; str +=
		 * "		\"fpAmt\": \"4545\","; str += "		\"oPrice\": \"5000\",";
		 * str += "		\"taxAmt\": \"455\","; str += "		\"dfAmt\": \"0\",";
		 * str += "		\"pName\": \"인천쫄면\","; str +=
		 * "		\"opAmt\": \"5000\","; str += "		\"seqNo\": \"1\","; str
		 * += "		\"slAmt\": \"5000\","; str += "		\"qty\": \"1\","; str +=
		 * "		\"pPrice\": \"5000\""; str += "	},"; str += "	{"; str +=
		 * "		\"fpAmt\": \"1818\","; str += "		\"oPrice\": \"2000\",";
		 * str += "		\"taxAmt\": \"182\","; str += "		\"dfAmt\": \"0\",";
		 * str += "		\"pName\": \"오뎅3개\","; str +=
		 * "		\"opAmt\": \"2000\","; str += "		\"seqNo\": \"2\","; str
		 * += "		\"slAmt\": \"2000\","; str += "		\"qty\": \"1\","; str +=
		 * "		\"pPrice\": \"2000\""; str += "	}],";
		 */
		/*
		 * str += "	\"salesList\": [{"; str += "		\"fpAmt\": \"4545\",";
		 * str += "		\"oPrice\": \"5000\","; str +=
		 * "		\"taxAmt\": \"455\","; str += "		\"dfAmt\": \"0\","; str
		 * += "		\"pName\": \"인천쫄면\","; str += "		\"opAmt\": \"5000\",";
		 * str += "		\"seqNo\": \"1\","; str +=
		 * "		\"slAmt\": \"5000\","; str += "		\"qty\": \"1\","; str +=
		 * "		\"pPrice\": \"5000\""; str += "	}],";
		 */
		/*
		 * str += "	\"salesList\": [{"; str += "		\"fpAmt\": \"2727\",";
		 * str += "		\"oPrice\": \"3000\","; str +=
		 * "		\"taxAmt\": \"273\","; str += "		\"dfAmt\": \"0\","; str
		 * += "		\"pName\": \"매운국물떡볶이\","; str +=
		 * "		\"opAmt\": \"3000\","; str += "		\"seqNo\": \"1\","; str
		 * += "		\"slAmt\": \"3000\","; str += "		\"qty\": \"1\","; str +=
		 * "		\"pPrice\": \"3000\""; str += "	}],";
		 */
		/*
		 * str += "	\"salesList\": [{"; str += "		\"fpAmt\": \"2727\",";
		 * str += "		\"oPrice\": \"3000\","; str +=
		 * "		\"taxAmt\": \"273\","; str += "		\"dfAmt\": \"0\","; str
		 * += "		\"pName\": \"매운국물떡볶이\","; str +=
		 * "		\"opAmt\": \"3000\","; str += "		\"seqNo\": \"1\","; str
		 * += "		\"slAmt\": \"3000\","; str += "		\"qty\": \"1\","; str +=
		 * "		\"pPrice\": \"3000\""; str += "	},"; str += "	{"; str +=
		 * "		\"fpAmt\": \"3181\","; str += "		\"oPrice\": \"5000\",";
		 * str += "		\"taxAmt\": \"319\","; str += "		\"dfAmt\": \"0\",";
		 * str += "		\"pName\": \"인천쫄면\","; str +=
		 * "		\"opAmt\": \"5000\","; str += "		\"seqNo\": \"2\","; str
		 * += "		\"slAmt\": \"5000\","; str += "		\"qty\": \"1\","; str +=
		 * "		\"pPrice\": \"5000\""; str += "	}],";
		 */
		/*
		 * str += "	\"salesInfo\": {"; str += "		\"salesType\": \"RCP01\",";
		 * str += "		\"sumSlAmt\": \"7000\","; str +=
		 * "		\"sumTaxAmt\": \"637\","; str += "		\"rePrint\": \"0\","
		 * ; str += "		\"salesDate\": \"20170105164146\","; str +=
		 * "		\"dtCnt\": \"2\","; str += "		\"paidAmt\": \"7000\",";
		 * str += "		\"chgAmt\": \"7000\","; str +=
		 * "		\"sumDfAmt\": \"0\","; str +=
		 * "		\"printDate\": \"20170105164146\","; str +=
		 * "		\"salesBarCode\": \"2017010511118\","; str +=
		 * "		\"sumOpAmt\": \"7000\","; str +=
		 * "		\"sumFpAmt\": \"6363\""; str += "	},"; str +=
		 * "	\"etcInfo\": {"; str += "		\"memo\": \"memo\","; str +=
		 * "		\"event\": \"event\""; str += "	}"; str += "}";
		 */
		/*
		 * String param =
		 * "mbrNo=100011&amount=1000&goodsName=%EC%B6%95%EA%B5%AC%EA%B3%B5&customerName="
		 * +
		 * "%EC%A1%B0%EC%8A%B9%EA%B7%9C&customerTelNo=01023183701 &timestamp=20160722000009&mbrRefNo=10102201010010&"
		 * +
		 * "signature=8c697d9f69e129ba129e9097342fdea1e848aa4a72e0e531c4b4c230b557401d";
		 * 
		 * String jsonData2 = "{"; jsonData2 += "          \"control\" : ";
		 * jsonData2 += "          { "; jsonData2 +=
		 * "                    flwNo : \"O110B20120160126000001\""; jsonData2
		 * += "          }"; jsonData2 +=
		 * "          , mblNo : \"010-0000-0000\""; jsonData2 +=
		 * "          , copMcno : \"0000000000\""; jsonData2 += "}";
		 * 
		 * String jsonData = "{\r\n"; jsonData +=
		 * "  \"userKey\": \"01099150940\",\r\n"; jsonData +=
		 * "  \"etcInfo\": {\r\n"; jsonData += "    \"memo\": \"memo\",\r\n";
		 * jsonData += "    \"event\": \"event\"\r\n"; jsonData += "  },\r\n";
		 * jsonData += "  \"shopInfo\": {\r\n"; jsonData +=
		 * "    \"name\": \"아임유\",\r\n"; jsonData +=
		 * "    \"bizNo\": \"012-34-56789\",\r\n"; jsonData +=
		 * "    \"address\": \"경기 수원시 팔달구 화서문로 19 354 호\",\r\n"; jsonData +=
		 * "    \"ceo\": \"김기봉\",\r\n"; jsonData +=
		 * "    \"phone\": \"0245645678\",\r\n"; jsonData +=
		 * "    \"cashier\": \"김기봉\"\r\n"; jsonData += "  },\r\n"; jsonData +=
		 * "  \"salesInfo\": {\r\n"; jsonData +=
		 * "    \"salesBarCode\": \"2016082410005\",\r\n"; jsonData +=
		 * "    \"salesDate\": \"20160824132657\",\r\n"; jsonData +=
		 * "    \"printDate\": \"20160824132657\",\r\n"; jsonData +=
		 * "    \"salesType\": \"01099150940\",\r\n"; jsonData +=
		 * "    \"sumDfAmt\": \"10500\",\r\n"; jsonData +=
		 * "    \"sumFpAmt\": \"8727\",\r\n"; jsonData +=
		 * "    \"sumTaxAmt\": \"873\",\r\n"; jsonData +=
		 * "    \"sumSlAmt\": \"20100\",\r\n"; jsonData +=
		 * "    \"sumOpAmt\": \"20100\",\r\n"; jsonData +=
		 * "    \"chgAmt\": \"20100\",\r\n"; jsonData +=
		 * "    \"paidAmt\": \"20100\",\r\n"; jsonData +=
		 * "    \"rePrint\": \"0\",\r\n"; jsonData += "    \"dtCnt\": \"3\"\r\n"
		 * ; jsonData += "  },\r\n"; jsonData += "  \"salesList\": [\r\n";
		 * jsonData += "    {\r\n"; jsonData += "      \"seqNo\": \"1\",\r\n";
		 * jsonData += "      \"pName\": \"메뉴_2000\",\r\n"; jsonData +=
		 * "      \"pPrice\": \"\",\r\n"; jsonData +=
		 * "      \"oPrice\": \"2000\",\r\n"; jsonData +=
		 * "      \"qty\": \"4\",\r\n"; jsonData +=
		 * "      \"dfAmt\": \"0\",\r\n"; jsonData +=
		 * "      \"fpAmt\": \"7273\",\r\n"; jsonData +=
		 * "      \"taxAmt\": \"727\",\r\n"; jsonData +=
		 * "      \"slAmt\": \"8000\",\r\n"; jsonData +=
		 * "      \"opAmt\": \"8000\"\r\n"; jsonData += "    },\r\n"; jsonData
		 * += "    {\r\n"; jsonData += "      \"seqNo\": \"2\",\r\n"; jsonData
		 * += "      \"pName\": \"아이템1\",\r\n"; jsonData +=
		 * "      \"pPrice\": \"\",\r\n"; jsonData +=
		 * "      \"oPrice\": \"800\",\r\n"; jsonData +=
		 * "      \"qty\": \"2\",\r\n"; jsonData +=
		 * "      \"dfAmt\": \"0\",\r\n"; jsonData +=
		 * "      \"fpAmt\": \"1454\",\r\n"; jsonData +=
		 * "      \"taxAmt\": \"146\",\r\n"; jsonData +=
		 * "      \"slAmt\": \"1600\",\r\n"; jsonData +=
		 * "      \"opAmt\": \"1600\"\r\n"; jsonData += "    },\r\n"; jsonData
		 * += "    {\r\n"; jsonData += "      \"seqNo\": \"3\",\r\n"; jsonData
		 * += "      \"pName\": \"메뉴_부가세없음\",\r\n"; jsonData +=
		 * "      \"pPrice\": \"\",\r\n"; jsonData +=
		 * "      \"oPrice\": \"3500\",\r\n"; jsonData +=
		 * "      \"qty\": \"3\",\r\n"; jsonData +=
		 * "      \"dfAmt\": \"10500\",\r\n"; jsonData +=
		 * "      \"fpAmt\": \"0\",\r\n"; jsonData +=
		 * "      \"taxAmt\": \"0\",\r\n"; jsonData +=
		 * "      \"slAmt\": \"10500\",\r\n"; jsonData +=
		 * "      \"opAmt\": \"10500\"\r\n"; jsonData += "    }\r\n"; jsonData
		 * += "  ],\r\n"; jsonData += "  \"cashInfo\": {\r\n"; jsonData +=
		 * "    \"cashAmt\": \"20100\",\r\n"; jsonData +=
		 * "    \"cashType\": \"CH02\",\r\n"; jsonData +=
		 * "    \"cashNo\": \"\",\r\n"; jsonData +=
		 * "    \"cashAppNo\": \"\",\r\n"; jsonData +=
		 * "    \"cashDate\": \"20160824132658\"\r\n"; jsonData += "  },\r\n";
		 * jsonData += "  \"cardInfo\": {\r\n"; jsonData +=
		 * "    \"cardAmt\": \"\",\r\n"; jsonData +=
		 * "    \"cardInstallment\": \"\",\r\n"; jsonData +=
		 * "    \"cardAppNo\": \"\",\r\n"; jsonData +=
		 * "    \"cardDate\": \"\",\r\n"; jsonData +=
		 * "    \"cardICom\": \"\",\r\n"; jsonData +=
		 * "    \"cardPCom\": \"\",\r\n"; jsonData += "    \"cardNo\": \"\"\r\n"
		 * ; jsonData += "  },\r\n"; jsonData += "  \"customerInfo\": {\r\n";
		 * jsonData += "    \"pointAmt\": \"\",\r\n"; jsonData +=
		 * "    \"getPoint\": 1005.0,\r\n"; jsonData +=
		 * "    \"customerCode\": \"10000024\",\r\n"; jsonData +=
		 * "    \"customerPoint\": 46608.0\r\n"; jsonData += "  }\r\n"; jsonData
		 * += "}\r\n";
		 */

		/*
		 * str += "{"; str += "	\"control\": {"; str +=
		 * "		\"flwNo\": \"O100\""; str += "	},"; str +=
		 * "	\"wcc\": \"3\","; str += "	\"aprAkMdDc\": \"1\","; str +=
		 * "	\"cstDrmV\": \"\","; str += "	\"cstDrmDc\": \"1\","; str +=
		 * "	\"cdno\": \"8710350063911021\","; str +=
		 * "	\"copMcno\": \"P001400002\","; str += "	\"posNo\": \"\","; str
		 * += "	\"filler\": \"\""; str += "}";
		 */

		/*
		 * str += "{											"; str +=
		 * "  \"control\": {                            "; str +=
		 * "    \"flwNo\": \"O250\"                     "; str +=
		 * "  },                                        "; str +=
		 * "  \"wcc\": \"3\",                           "; str +=
		 * "  \"aprAkMdDc\": \"1\",                     "; str +=
		 * "  \"cstDrmDc\": \"1\",                      "; str +=
		 * "  \"cdno\": \"8710350063911021\",           "; str +=
		 * "  \"copMcno\": \"P001400002\",              "; str +=
		 * "  \"posNo\": \"\",                          "; str +=
		 * "  \"ccoAprno\": \"20170224100311100\",      "; str +=
		 * "  \"deDt\": \"20170224\",                   "; str +=
		 * "  \"deHr\": \"180857\",                     "; str +=
		 * "  \"deDc\": \"20\",                         "; str +=
		 * "  \"deRsc\": \"200\",                       "; str +=
		 * "  \"uDc\": \"2\",                           "; str +=
		 * "  \"deAkMdDc\": \"0\",                      "; str +=
		 * "  \"ptUDc\": \"1\",                         "; str +=
		 * "  \"ttnUPt\": \"5600\",                     "; str +=
		 * "  \"cponNo\": \"\",                         "; str +=
		 * "  \"rtgDc\": \"1\",                         "; str +=
		 * "  \"otInfYnDc\": \"1\",                     "; str +=
		 * "  \"otInfDc\": \"1\",                       "; str +=
		 * "  \"otAprno\": \"030605662\",               "; str +=
		 * "  \"otDt\": \"20170224180857\",             "; str +=
		 * "  \"filler\": \"\"                          "; str +=
		 * "}                                           ";
		 */

		str += " {													";
		str += "     \"control\": {                                 ";
		str += "         \"flwNo\": \"O250\"                        ";
		str += "     },                                             ";
		str += "     \"wcc\": \"3\",                                ";
		str += "     \"aprAkMdDc\": \"1\",                          ";
		str += "     \"cstDrmDc\": \"1\",                           ";
		str += "     \"cdno\": \"8710350063911021\",                ";
		str += "     \"copMcno\": \"P001400002\",                   ";
		str += "     \"posNo\": \"\",                               ";
		str += "     \"ccoAprno\": \"2017030916312242\",            ";
		str += "     \"deDt\": \"20170309\",                        ";
		str += "     \"deHr\": \"163122\",                          ";
		str += "     \"deDc\": \"20\",                              ";
		str += "     \"deRsc\": \"200\",                            ";
		str += "     \"uDc\": \"2\",                                ";
		str += "     \"deAkMdDc\": \"0\",                           ";
		str += "     \"ptUDc\": \"1\",                              ";
		str += "     \"ttnUPt\": \"200\",                          ";
		str += "     \"cponNo\": \"\",                              ";
		str += "     \"rtgDc\": \"1\",                              ";
		str += "     \"otInfYnDc\": \"1\",                          ";
		str += "     \"otInfDc\": \"1\",                            ";
		str += "     \"otAprno\": \"030608247\",                    ";
		str += "     \"otDt\": \"20170309163126\",                  ";
		str += "     \"filler\": \"\"                               ";
		str += " }                                                  ";

		// 전송
		OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
		System.out.println("jsonData" + str.getBytes().length);
		try {
			osw.write(str);
			osw.flush();

			// 응답
			BufferedReader br = null;
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

			String line = null;
			System.out.println("br.readLine():" + br.readLine());
			System.out.println("br:" + br);

			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}

			// 닫기
			osw.close();
			br.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
