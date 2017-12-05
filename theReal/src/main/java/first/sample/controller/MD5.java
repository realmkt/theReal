package first.sample.controller;

/**
 * EncryptUtil.
 */
public class MD5 {
	/**
	 *
	 */
	public static String encryptString(String src) {
		java.security.MessageDigest md5 = null;
		try {
			md5 = java.security.MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			return "";
		}

		String eip;
		byte[] bip;
		String temp = "";
		String tst = src;

		bip = md5.digest(tst.getBytes());
		for (int i = 0; i < bip.length; i++) {
			eip = "" + Integer.toHexString(bip[i] & 0x000000ff);
			if (eip.length() < 2)
				eip = "0" + eip;
			temp = temp + eip;
		}
		return temp;
	}
}
