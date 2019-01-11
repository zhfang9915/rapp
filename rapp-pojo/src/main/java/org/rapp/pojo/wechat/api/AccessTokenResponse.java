package org.rapp.pojo.wechat.api;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

/**
 * 微信AccessToken
 * 
 * @author 张芳
 *
 */
public class AccessTokenResponse implements Serializable {
	/**
	 * <p>
	 * {"access_token":"m11-EmKkgwh7U5WVSxeKFkzNA2rKAXb-VFk7UUSWJGayll4s_-yhbFdQGblnl8nQAiiUwRHCorIJGa7z3S4aEJW27ZoJfChFaPxgUuHRl62io_TKhDGPFmy6r5UhOrdsRMRiAJALGL",
	 * "expires_in":7200}
	 * </p>
	 */
	private static final long serialVersionUID = -5282419986021634883L;

	/* Access Token */
	private String access_token;

	private int expires_in;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
