package first.common.util;

import java.util.HashMap;

import org.json.simple.JSONObject;

public class TestParameter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String test = "mbrNo=551553&amount=100&mbrRefNo=10102201010054&orgTranDate=160929&orgRefNo=0928P3401997&charset=UTF-8&signature=2123ed45576303344e30fc1304156e6676416430eb444320882050389da4e713&clientType=pos&timestamp=20160929160315";

		String[] array;
		String[] array2;
		array = test.split("&");
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < array.length; i++) {
			array2 = array[i].split("=");
			map.put(array2[0], array2[1]);
		}
		System.out.println(map);

		System.out.println(map.get("mbrNo"));

		JSONObject json = new JSONObject();
		json.putAll(map);
		System.out.println(json);

	}

}
