package first.common.util;

import org.apache.log4j.Logger;

import first.sample.theRealShop.TheRealShopToLpoint;

public class logTest {
	private static final Logger log = Logger.getLogger(TheRealShopToLpoint.class.getName());
	public static void main(String[] args) {
		
		
		String userBirth = "198702251";
		  String sexDivCd = userBirth.substring(8);
		  userBirth = userBirth.substring(0, 8);		
		System.out.println(sexDivCd);
		System.out.println(userBirth);
	}

}
