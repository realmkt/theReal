package first.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date dt = new Date();
		SimpleDateFormat dtFmt = new SimpleDateFormat("yyyyMMdd");
		System.out.println(dtFmt.format(dt));
		String e1 = "1";
		String f1;
		Integer.parseInt(e1);
		Integer s1 = 0;
		for (int i = 0; i < 10; i++) {
			s1++;
			System.out.println(s1);
		}

		f1 = Integer.toString(s1) + e1;
		System.out.println(f1);
	}

}
