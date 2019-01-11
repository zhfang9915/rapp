package org.rapp.service.impl;

import org.rapp.pojo.account.User;
import org.rapp.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * 基础服务
 * @author 张芳
 *
 */
@Service
public class BaseServiceImpl implements BaseService {

	@Override
	public String getCreateBy(User user) {
		String createBy = "";
		switch (user.getRole().getRoleId()) {
		case 1://超级管理员
			break;
		case 2://管理员
			createBy = user.getUsername();
			break;
		case 4://企业
			createBy = user.getUsername();
			break;
		default://普通用户
			createBy = user.getUsername();
			break;
		}
		return createBy;
	}
}
