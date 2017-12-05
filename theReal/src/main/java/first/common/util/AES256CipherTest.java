package first.common.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;

public class AES256CipherTest {

	public static void main(String[] args) throws InvalidKeyException, UnsupportedEncodingException,
			NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException, EncoderException, DecoderException {
		// TODO Auto-generated method stub

		String key = "aes256-test-key!!"; // key는 16자 이상
		AES256Util aes256 = new AES256Util(key);
		URLCodec codec = new URLCodec();

		String loginidx = "설유진";

		String id = "설유진";
		String custrnmNo = "01071064573";
		String custNm = "8702251163321";

		/*
		 * String encLoginidx = codec.encode(aes256.aesEncode(loginidx)); String
		 * decLoginidx = aes256.aesDecode(codec.decode(encLoginidx));
		 */

		System.out.println("### loginidx:" + aes256.aesEncode(id));
		System.out.println("### encLoginidx:" + aes256.aesEncode(custrnmNo));
		System.out.println("### decLoginidx:" + aes256.aesEncode(custNm));
		System.out.println("### loginidx:" + aes256.aesDecode(aes256.aesEncode(id)));
		System.out.println("### encLoginidx:" + aes256.aesDecode(aes256.aesEncode(custrnmNo)));
		System.out.println("### decLoginidx:" + aes256.aesDecode(aes256.aesEncode(custNm)));

		/*
		 * String encLat = codec.encode(aes256.aesEncode(""+Lat)); String decLat
		 * = aes256.aesDecode(codec.decode(encLat));
		 * 
		 * System.out.println("### Lat:"+Lat); //37.71248872643193
		 * System.out.println("### encLat:"+encLat); //encoding 전
		 * 0FTRN5NS7FrhNsJ4GEI4Hc62CLzuTx89JUX+2Z4PvqE= / 후
		 * 0FTRN5NS7FrhNsJ4GEI4Hc62CLzuTx89JUX%2B2Z4PvqE%3D System.out.println(
		 * "### decLat:"+decLat); //37.71248872643193
		 * 
		 * String encLng = codec.encode(aes256.aesEncode(""+Lng)); String decLng
		 * = aes256.aesDecode(codec.decode(encLng));
		 * 
		 * System.out.println("### Lng:"+Lng); //127.26688771630859
		 * System.out.println("### encLng:"+encLng); //encoding 전
		 * OVDlF0whDEW7MxGbJvk84Jajx4ve0tikK4YXj+Z8y6Q= / 후
		 * OVDlF0whDEW7MxGbJvk84Jajx4ve0tikK4YXj%2BZ8y6Q%3D System.out.println(
		 * "### decLng:"+decLng); //127.26688771630859
		 */
		/*
		 * enId:QBeiJoMS8I23ijex211yOw== enYyyymmdd:5fHh+Em1Y/XlvjkkzRRLbA==
		 * enCustNm:ELWvIIL0q3Zf54pmVZl4wA== desId:설유진 desYyyymmdd:01071064573
		 * desCustNm:8702251163321
		 * 
		 * String id = "설유진"; String custrnmNo = "01071064573"; String custNm =
		 * "8702251163321";
		 * 
		 * AES256Cipher a256 = AES256Cipher.getInstance();
		 * 
		 * String enId = a256.AES_Encode(id); String enYyyymmdd =
		 * a256.AES_Encode(custrnmNo); String enCustNm =
		 * a256.AES_Encode(custNm);
		 * 
		 * String desId = a256.AES_Decode(enId); String desYyyymmdd =
		 * a256.AES_Decode(enYyyymmdd); String desCustNm =
		 * a256.AES_Decode(enCustNm);
		 * 
		 * System.out.println("enId:"+enId);
		 * System.out.println("enYyyymmdd:"+enYyyymmdd);
		 * System.out.println("enCustNm:"+enCustNm);
		 * System.out.println("desId:"+desId);
		 * System.out.println("desYyyymmdd:"+desYyyymmdd);
		 * System.out.println("desCustNm:"+desCustNm);
		 */

	}

}
