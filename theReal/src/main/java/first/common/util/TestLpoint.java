package first.common.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;

import openpoint.aria.lib.ARIACipher;

public class TestLpoint {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		try {

			byte[] keys = ARIACipher.readKeyFile("C:/O180.dat");

			ARIACipher cipher = new ARIACipher(keys);
			/*
			 * String jsonData = "{"; jsonData += "          \"control\" : ";
			 * //컨트롤영역 jsonData += "          { "; jsonData +=
			 * "             \"flwNo\" : \"O110B20120160126000001\""; //추적번호
			 * jsonData += "          }"; jsonData +=
			 * "          ,\"wcc\" : \"3\""; //WCC(인증방식) / 1:MSR/2:IC/3:KEY IN
			 * jsonData += "          ,\"aprAkMdDc\":\"1\""; //승인요청방식구분코드/ 1:POS
			 * jsonData += "          ,\"cstDrmV\" : \"2\""; // 고객식별 구분코드/
			 * 1:카드번호 /2: 고객번호 jsonData += "          , \"cstDrmDc\" : \"\"";
			 * //고객식별값/ 20자 jsonData +=
			 * "          , \"copMcno \": \" P001400002 \""; //개방형
			 * 제휴가맹점번호/10자(가맹점 번호 지원받고 데이터전송예정) jsonData +=
			 * "          , \"posNo\" : \"\""; //포스번호 8자(포스번호+점포코드) jsonData +=
			 * "          , \"filler\" : \"2\""; //filler jsonData += "}";
			 */
			/* string 단위 암복호화 : c# (-) java */
			String jsonData = "";
			jsonData += "{";
			jsonData += "	\"control\": {";
			jsonData += "		\"flwNo\": \"O110B20120160126000001\"";
			jsonData += "	},";
			jsonData += "	\"wcc\": \"3\",";
			jsonData += "	\"aprAkMdDc\": \"1\",";
			jsonData += "	\"cstDrmV\": \"2\",";
			jsonData += "	\"cstDrmDc\": \" \",";
			jsonData += "	\"copMcno\": \"P001400002\",";
			jsonData += "	\"posNo\": \"\",";
			jsonData += "	\"filler\": \"2\"";
			jsonData += "}";

			String jsonData1 = "test";
			// jsonData1 = "";
			System.out.println("평문 : " + jsonData);
			String encrypted = cipher.encryptString(jsonData, "UTF-8");
			System.out.println("암호화 : " + encrypted);
			String test = "s3iTghaSVbhdyDZMHAYUsWB51iLCjaGM7XzzbehVLLNHxoIMR2LnFZuDxBLd4NzkjsFE7ynr2E9YU/zBS98qLyvWU1/uG4fYtGCFCXwr+qvQhNP0f69hf7/KblbQwkFmXC5yZ0YAMc3Y39c1lDBHmdgfD/vnfsVJGfpmG/zht2VuqsJTW277ZhmFSR7uj4AzRg16lG284oAS/B0gBlQxHjBcwGVYGojqHBzG4YjeOUWB+LlzHxzDs/57B2BSZZPmmKlvxSn54fCN4vsDsX9fsFPMJEsSoC88nMsYKkPUPaI=";
			String decrypted = cipher.decryptString(test, "UTF-8");
			System.out.println("복호화 : " + decrypted);
			System.out.println("check : " + (jsonData.equals(decrypted)));

		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
