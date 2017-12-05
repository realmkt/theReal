package first.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import org.json.simple.JSONValue;

public class HttpJSonParamTest {

	private Object reqJsonObj;

	public HttpJSonParamTest(HttpServletRequest request) throws IOException, ParseException {
		String str;
		StringBuffer paramData = new StringBuffer();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

		try {
			while ((str = br.readLine()) != null) {
				paramData.append(str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

		try {
			setJSonArrayData(paramData.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	private void setJSonArrayData(String requestData) throws ParseException {
		reqJsonObj = JSONValue.parse(requestData);
	}

	public void printJsonArrayData() {
		JSONObject main = (JSONObject) reqJsonObj;
		JSONArray testList = (JSONArray) main.get("test");
		for (int i = 0; i < testList.size(); i++) {
			System.out.println("===============row : " + i + "======================");
			System.out.println("khs post KEY1 : " + ((JSONObject) testList.get(i)).get("KEY1") + " KEY2: "
					+ ((JSONObject) testList.get(i)).get("KEY2").toString() + " NAME: "
					+ ((JSONObject) testList.get(i)).get("NAME").toString());

		}
	}
}