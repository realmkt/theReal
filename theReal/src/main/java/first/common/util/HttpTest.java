package first.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HttpTest {
	public static void main(String[] args) throws IOException {
		Date dt = new Date();
		SimpleDateFormat dtFmt = new SimpleDateFormat("yyyyMMdd");

		String makeFile = "./temp/" + dtFmt.format(dt) + ".txt";
		String s = "";
		String resultFlwNo;
		File file = new File(makeFile);

		if (!file.exists()) {
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(makeFile));
				s = "1";
				out.write(s);
				out.newLine();
				out.close();
			} catch (IOException e) {
				System.err.println(e); // 에러가 있다면 메시지 출력
				System.exit(1);
			}
		} else {
			try {
				BufferedReader in = new BufferedReader(new FileReader(makeFile));
				Integer s1 = Integer.parseInt(in.readLine());
				resultFlwNo = String.format("%1$06d", s1 + 1);
				System.out.println("resultFlwNo:" + resultFlwNo);
				in.close();
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter(makeFile));
					s = Integer.toString(s1 + 1);
					out.write(s);
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
