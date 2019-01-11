/**
 * 创建于 2016年8月29日 下午1:34:43
 *
 */

package org.rapp.comm.util;

import org.springframework.util.DigestUtils;

/** 
 * @ClassName: MD5Utils 
 * @Description: TODO
 * @author zhfang
 *  
 */
public class MD5Utils {

	/** MD5盐值 */
	public static final String salt = "ltd.qcwifi.www";
	
	/**
	 * 生成MD5加密串
	 * @Title: getMD5 
	 * @Description: TODO
	 * @param @param value
	 * @param @return    
	 * @return String    
	 * @throws
	 */
	public static String getMD5(String value) {
		String base = value + "#" + salt;
		return DigestUtils.md5DigestAsHex(base.getBytes());
	}
	
	public static void main(String[] args) {
		System.out.println(getMD5("b67ea98f57d5cbb7d175d939c5b4904cd8:c8:e9:2d:ff:99"));
	}
	
}
