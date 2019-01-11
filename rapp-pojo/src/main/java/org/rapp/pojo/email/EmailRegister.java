package org.rapp.pojo.email;

public class EmailRegister {

	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 激活URL
	 */
	private String activeUrl;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getActiveUrl() {
		return activeUrl;
	}

	public void setActiveUrl(String activeUrl) {
		this.activeUrl = activeUrl;
	}

	public EmailRegister(String username, String activeUrl) {
		super();
		this.username = username;
		this.activeUrl = activeUrl;
	}

	public EmailRegister() {
		super();
	}

}
