package first.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class JusoTest {

	public static void main(String[] args) {
		System.out.println("1212");
		// TODO Auto-generated method stub
		URL url = null;
		URLConnection urlConnection = null;

		// URL 주소
		String sUrl = " http://api.openapi.io";

		// 파라미터 이름
		String paramName = "sidocd";

		// 파라미터 이름에 대한 값
		String paramValue = "0000000000";

		try {
			// Get방식으로 전송 하기
			url = new URL(sUrl + "?" + paramName + "=" + paramValue);
			urlConnection = url.openConnection();
			urlConnection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			urlConnection.addRequestProperty("x-waple-authorization",
					"MS0xMzY1NjY2MTAyNDk0LTA2MWE4ZDgyLTZhZmMtNGU5OS05YThkLTgyNmFmYzVlOTkzZQ==");
			printByInputStream(urlConnection.getInputStream());

			System.out.println("printByInputStream:" + urlConnection);

			// Post방식으로 전송 하기
			url = new URL(sUrl);
			urlConnection = url.openConnection();
			urlConnection.setDoOutput(true);

			printByOutputStream(urlConnection.getOutputStream(), paramName + "=" + paramValue);
			printByInputStream(urlConnection.getInputStream());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 웹 서버로 부터 받은 웹 페이지 결과를 콘솔에 출력하는 메소드
	public static void printByInputStream(InputStream is) {
		byte[] buf = new byte[1024];
		int len = -1;

		try {
			while ((len = is.read(buf, 0, buf.length)) != -1) {
				System.out.write(buf, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 웹 서버로 파라미터명과 값의 쌍을 전송하는 메소드
	public static void printByOutputStream(OutputStream os, String msg) {
		try {
			byte[] msgBuf = msg.getBytes("UTF-8");
			os.write(msgBuf, 0, msgBuf.length);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
