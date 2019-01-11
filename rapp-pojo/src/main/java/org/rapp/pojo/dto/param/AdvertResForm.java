package org.rapp.pojo.dto.param;

import java.util.List;

/**
 * 前台form提交的资源信息
 * 
 * @author 张芳
 *
 */
public class AdvertResForm {

	private List<String> advertUrl;

	private List<String> toUrl;

	public List<String> getAdvertUrl() {
		return advertUrl;
	}

	public void setAdvertUrl(List<String> advertUrl) {
		this.advertUrl = advertUrl;
	}

	public List<String> getToUrl() {
		return toUrl;
	}

	public void setToUrl(List<String> toUrl) {
		this.toUrl = toUrl;
	}

}
