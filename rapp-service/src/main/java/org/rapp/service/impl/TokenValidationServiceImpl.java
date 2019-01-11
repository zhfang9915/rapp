package org.rapp.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.rapp.comm.util.MD5Utils;
import org.rapp.service.TokenValidationService;
import org.springframework.stereotype.Service;

@Service
public class TokenValidationServiceImpl implements TokenValidationService {

	/**
	 * 校验token
	 * @return
	 */
	@Override
	public boolean validationDveNoAndMac(String arg0, String arg1, String token) {
		//参数不对
		if (StringUtils.isBlank(arg0) || StringUtils.isBlank(arg1) || StringUtils.isBlank(token)) {
			return false;
		}
		String md5 = MD5Utils.getMD5(arg0 + arg1);
		if (!md5.equals(token)) {
			return false;
		}
		
		return true;
	}
	
}
