package org.rapp.pojo.wechat.api;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

/**
 * 微信AccessToken
 * 
 * @author 张芳
 *
 */
public class SecretKeyResponse implements Serializable {
	/**
	 * <p>
	 * { 
	 * 	"errcode": 0, 
	 * 		"data": { 
	 * 			"secretkey": "1af08ec5cdb70a4d7365bcd64d3120f6"
	 * 		} 
	 * }
	 * </p>
	 */
	private static final long serialVersionUID = -5282419986021634883L;

	/* Access Token */
	private SecretKeyData data;

	private int errcode;

	public SecretKeyData getData() {
		return data;
	}

	public void setData(SecretKeyData data) {
		this.data = data;
	}

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
