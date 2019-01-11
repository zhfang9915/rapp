package org.rapp.pojo.dto.param.api;

/**
 * 广告推送入参
 * 
 * @author 张芳
 *
 */
public class AdvertPushParam extends BaseApiParam {

	/**
	 * 当前页面的URL
	 */
	private String url;
	/**
	 * 手机Mac
	 */
	private String phoneMac;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPhoneMac() {
		return phoneMac;
	}

	public void setPhoneMac(String phoneMac) {
		this.phoneMac = phoneMac;
	}

}
