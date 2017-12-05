package first.common.util;

public class TestUplusSha256 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
String mbrNo = "551553";
String mbrRefNo = "10102201010054";
String amount = "100";
String apikey = "k/CKiKECVPWQ+AcemOaIbBk/gJk/gZc0gBozGRa8gJoYGi==";
String timestamp = "20160614210832";
		
				String signature = Sha256.encryptString(mbrNo+"|"+mbrRefNo+"|"+amount+"|"+apikey+"|"+timestamp);
		System.out.println(signature);
	}

}
