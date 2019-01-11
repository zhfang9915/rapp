/**
 * 创建于 2016年6月28日 下午1:22:02
 *
 */

package org.rapp.comm.util;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密工具类
 * @author zhfang
 *
 */
public class AESUtil {

	public static final String DEFAULT_CODING = "UTF-8";

	public static String decrypt(String encrypted, String seed){
		try {
			byte[] keyb = seed.getBytes(DEFAULT_CODING);
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(keyb);
			SecretKeySpec skey = new SecretKeySpec(thedigest, "AES");
			Cipher dcipher = Cipher.getInstance("AES");
			dcipher.init(Cipher.DECRYPT_MODE, skey);

			byte[] clearbyte = dcipher.doFinal(toByte(encrypted));
			return new String(clearbyte);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

	public static String encrypt(String content, String key) {
		try {
			byte[] input = content.getBytes(DEFAULT_CODING);
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(key.getBytes(DEFAULT_CODING));
			SecretKeySpec skc = new SecretKeySpec(thedigest, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skc);
			
			byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
			int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
			ctLength += cipher.doFinal(cipherText, ctLength);
				
			return parseByte2HexStr(cipherText);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	private static byte[] toByte(String hexString) {
		int len = hexString.length() / 2;
		byte[] result = new byte[len];
		for (int i = 0; i < len; i++) {
			result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
		}
		return result;
	}
	
	private static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) throws Exception {
//		String time = CalendarUtil.parseLongTime(new Date());
		String key = "vistamall189";
		
//		System.out.println(AESUtil.encrypt(time, key)); 
		System.out.println(AESUtil.encrypt("201943085792", key));
	}
}
