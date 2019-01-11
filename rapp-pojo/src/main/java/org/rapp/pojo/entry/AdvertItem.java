package org.rapp.pojo.entry;

import java.io.Serializable;

/**
 * 广告资源
 * 
 * @author 张芳
 *
 */
public class AdvertItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2326503666305857001L;

	/**
	 * 广告主键
	 */
	private String advertId;

	/**
	 * 广告内容URL
	 */
	private String advertUrl;
	/**
	 * 本地存放的目录
	 */
	private String localUrl;
	/**
	 * 跳转链接
	 */
	private String toUrl;

	public String getAdvertId() {
		return advertId;
	}

	public void setAdvertId(String advertId) {
		this.advertId = advertId;
	}

	public String getAdvertUrl() {
		return advertUrl;
	}

	public void setAdvertUrl(String advertUrl) {
		this.advertUrl = advertUrl;
	}

	public String getLocalUrl() {
		return localUrl;
	}

	public void setLocalUrl(String localUrl) {
		this.localUrl = localUrl;
	}

	public String getToUrl() {
		return toUrl;
	}

	public void setToUrl(String toUrl) {
		this.toUrl = toUrl;
	}

	@Override
	public String toString() {
		return "AdvertItem [advertId=" + advertId + ", advertUrl=" + advertUrl + ", localUrl=" + localUrl + ", toUrl="
				+ toUrl + "]";
	}

}
