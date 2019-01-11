package org.rapp.pojo.account;

import java.io.Serializable;

/**
 * 权限
 * 
 * @author 张芳
 */
public class Auth extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9009801105946536358L;
	/**
	 * 权限ID
	 */
	private int authId;
	/**
	 * 权限名称
	 */
	private String authName;
	/**
	 * 可访问的URL
	 */
	private String requestUrl;

	public Auth() {
		super();
	}

	public Auth(int authId, String authName, String requestUrl) {
		super();
		this.authId = authId;
		this.authName = authName;
		this.requestUrl = requestUrl;
	}

	public int getAuthId() {
		return authId;
	}

	public void setAuthId(int authId) {
		this.authId = authId;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	@Override
	public String toString() {
		return "Auth [authId=" + authId + ", authName=" + authName + ", requestUrl=" + requestUrl + "]";
	}

}
