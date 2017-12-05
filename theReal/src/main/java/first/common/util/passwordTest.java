package first.common.util;

public class passwordTest {

	public static String encryptString(String src) {
		java.security.MessageDigest sha256 = null;
		try {
			sha256 = java.security.MessageDigest.getInstance("md5");
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

		String a = "tjfdbwls12!";

		a = encryptString(a);
		System.out.println("결과1  ㅋ  " + a);
		System.out.println("결과2  ㅋ  " + a.toUpperCase());

		// TODO Auto-generated method stub

	}

}
