package org.rapp.pojo.email;

public class EmailFindPwd {

	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 新密码
	 */
	private String newPwd;
	/**
	 * 登录地址
	 */
	private String loginUrl;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public EmailFindPwd() {
		super();
	}

	public EmailFindPwd(String username, String newPwd, String loginUrl) {
		super();
		this.username = username;
		this.newPwd = newPwd;
		this.loginUrl = loginUrl;
	}

}
