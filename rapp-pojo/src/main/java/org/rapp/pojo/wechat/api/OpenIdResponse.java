package org.rapp.pojo.wechat.api;

import com.alibaba.fastjson.JSONObject;

public class OpenIdResponse extends BaseWeChatResponse {
	/**
	 * <p>
	 * Discription:[字段功能描述]
	 * </p>
	 */
	private static final long serialVersionUID = -4302144585025327016L;

	/* OpenId */
	private String openid;

	/*
	 * platform=WX_WT openid表示中国电信上海网厅公众号用户唯一标识 platform=WX_KF
	 * openid表示中国电信上海客服公众号用户唯一标识
	 */
	private String platform;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
