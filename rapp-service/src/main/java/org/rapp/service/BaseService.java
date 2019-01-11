package org.rapp.service;

import org.rapp.pojo.account.User;

public interface BaseService {

	/**
	 * 根据权限获取创建人信息
	 * @param user
	 * @return
	 */
	String getCreateBy(User user);

}
