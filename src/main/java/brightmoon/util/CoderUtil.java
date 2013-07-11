package brightmoon.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class CoderUtil extends Coder {
	private final static String CODING = "UTF-8";

	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decryptBase64(String key) throws Exception {
		return new String(decryptBASE64(key));
	}

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBase64(String str) throws Exception {
		return encryptBASE64(str.getBytes(CODING));
	}

	/**
	 * MD5加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encryptMd5(String str)   {
		try {
			return byte2String(encryptMD5(str.getBytes(CODING)));
		} catch (UnsupportedEncodingException e) { 
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 使用C#返回的MD5的值.
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String encryptMd5WithC(String str) throws Exception {
		return byte2String2(encryptMD5(str.getBytes(CODING)));
	}

	/**
	 * 下面的是为了防止C#里面和java里面返回的结果不一致进行的处理. 原因是因为C#的byte的范围和java的不一样。。
	 * 
	 * @param byts
	 * @return
	 */
	private static String byte2String2(byte[] byts) { 
		StringBuffer sb = new StringBuffer(32);
		for (int i = 0; i < byts.length; i++) {
			int val = byts[i] & 0xff;
			if (val < 0xf) {
				sb.append("0");
			}
			sb.append(Integer.toHexString(val));
		}
		return sb.toString().toUpperCase();
	}
	
	private static String byte2String(byte[] byts) {
		 BigInteger md5 = new BigInteger(byts);
		 return md5.toString(16); 
	}

	/**
	 * SHA加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encryptSha(String str) throws Exception {
		return byte2String(encryptSHA(str.getBytes(CODING)));
	}

	public static String initMackey() throws Exception {
		return initMacKey();
	}

	/**
	 * HMAC加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptHmac(String data, String key) throws Exception {
		return byte2String(encryptHMAC(data.getBytes(CODING), key));

	}
}