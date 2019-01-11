package org.rapp.pojo.log;

import org.rapp.pojo.account.BaseEntity;

/**
 * 系统登录日志
 * 
 * @author 张芳
 *
 */
public class LoginLog extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4438762310535285953L;

	private String id;

	private String userName;

	private String ip;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "LoginLog [id=" + id + ", userName=" + userName + ", ip=" + ip + "]";
	}

}
