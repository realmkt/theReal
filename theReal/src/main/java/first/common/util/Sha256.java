package first.common.util;

public class Sha256 {

	public static String encryptString(String src) {
		java.security.MessageDigest sha256 = null;
		try {
			sha256 = java.security.MessageDigest.getInstance("sha-256");
		} catch (Exception e) {
			return "";
		}

		String eip;
		byte[] bip;
		String temp = "";
		String tst = src;

		bip = sha256.digest(tst.getBytes());
		for (int i = 0; i < bip.length; i++) {
			eip = "" + Integer.toHexString(bip[i] & 0x000000ff);
			if (eip.length() < 2)
				eip = "0" + eip;
			temp = temp + eip;
		}
		return temp;
	}

	public static void main(String[] args) {

		String a = "real1201!";

		a = encryptString(a);

		System.out.println("결과  ㅋ  " + a);
		/**
		 * 결과1 ㅋ d8212f150cdc4ac49ded83df368cc653
		 * 032c17638e8d731268d2c4022636b7fbba09e702e4c21b03b21901f1c14a1886
		 * 
		 * 결과2 ㅋ D8212F150CDC4AC49DED83DF368CC653
		 */
		// TODO Auto-generated method stub

	}

}
