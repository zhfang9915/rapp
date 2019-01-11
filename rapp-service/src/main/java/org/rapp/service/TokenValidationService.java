package org.rapp.service;

/**
 * token校验公共接口
 * @author 张芳
 *
 */
public interface TokenValidationService {

	/**
	 * 校验设备编码和Mac地址
	 * @param devNo
	 * @param mac
	 * @param token
	 * @return
	 */
	boolean validationDveNoAndMac(String arg0, String arg1, String token);
	
	
}
