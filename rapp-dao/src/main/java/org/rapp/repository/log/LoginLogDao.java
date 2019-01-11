package org.rapp.repository.log;

import org.rapp.pojo.log.LoginLog;

public interface LoginLogDao {

	/**
	 * 保存登录日志
	 */
	void insertLoginLog(LoginLog log);
}
