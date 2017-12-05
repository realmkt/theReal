package first.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class TestTheRealToUplus {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));
		
        System.out.println(today);
		
		
		Random random = new Random();
        //0~999999난수발생 시키고 0~99999을 위해 100000을 더해준다. 
		int result = random.nextInt(1000000)+100000;
		//900000 ~ 999999은 100000더하면 7자리가 되므로 -100000을 해준다.
		if(result>1000000){
			System.out.println("test");
		    result = result - 100000;
		}
		
		System.out.println(Integer.toString(result));
		 		
		System.out.println("U"+today+Integer.toString(result));
	}

}
