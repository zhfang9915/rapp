package org.rapp.service.log;

import javax.servlet.http.HttpServletRequest;

public interface LoginLogService {

	/**
	 * 保存登录日志
	 */
	public void saveLoginLog(HttpServletRequest request);
}
