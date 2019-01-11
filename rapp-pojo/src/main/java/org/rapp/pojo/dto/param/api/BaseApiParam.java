package org.rapp.pojo.dto.param.api;

/**
 * API授权认证
 * 
 * @author 张芳
 *
 */
public class BaseApiParam {

	/**
	 * 设备ID
	 */
	private String devNo;
	/**
	 * 请求令牌 由devNo+路由器MAC+盐值生成MD5
	 */
	private String token;

	public String getDevNo() {
		return devNo;
	}

	public void setDevNo(String devNo) {
		this.devNo = devNo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	

}
