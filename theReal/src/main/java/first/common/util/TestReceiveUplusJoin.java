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

public class TestReceiveUplusJoin {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// return lpointResData ;

		int timeout = 10;
		// 연결
		// URL url = new
		// URL("http://117.52.97.40/theReal/receipt/reqTheRealToLpoint.do");
		// URL url = new
		// URL("http://localhost:8080/theReal/receipt/reqTheRealToLpoint.do");
		URL url = new URL("http://117.52.97.40/theReal/receipt/receiveUplusJoin.do");
		// URL url = new
		// URL("http://localhost:8080/theReal/receipt/insertReceiptData.do");
		// URL url = new URL("http://192.168.100.111/app/cmu/api/requestptom");
		// URL url = new URL("https://op_dev.lpoint.com:8903/op");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST"); // 보내는 타입
		conn.setRequestProperty("Accept-Language", "ko-kr,ko;q=0.8,en-us;q=0.5,en;q=0.3");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("Accept-Charset", "UTF-8");
		conn.setConnectTimeout(timeout);

		// 데이터
		String str = "";
		str += "{								";
		str += "	\"telNo\": \"01077003578\",";
		str += "	\"userNm\": \"설유진\",";
		str += "	\"userBirth\": \"198702251\",";
		str += "	\"localDivCd\": \"1\"";
		str += "}";

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
