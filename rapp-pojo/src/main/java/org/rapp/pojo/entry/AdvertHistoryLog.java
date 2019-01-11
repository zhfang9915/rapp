package org.rapp.pojo.entry;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 广告历史推送详情
 * 
 * @author 张芳
 *
 */
public class AdvertHistoryLog {

	/**
	 * 广告名称
	 */
	private String advertName;
	/**
	 * 设备名称
	 */
	private String devName;
	/**
	 * 手机MAC
	 */
	private String phoneMac;
	/**
	 * 推送时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private String pushTime;
	/**
	 * 目标URL
	 */
	private String url;
	/**
	 * 广告展示时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private String showTime;
	/**
	 * 广告关闭时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private String closeTime;
	/**
	 * 广告点击时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private String clickTime;
	/**
	 * 网页关键字
	 */
	private String sourceKeyWord;
	/**
	 * 网页标题
	 */
	private String sourceTitle;
	/**
	 * 网页URL
	 */
	private String sourceUrl;

	public String getAdvertName() {
		return advertName;
	}

	public void setAdvertName(String advertName) {
		this.advertName = advertName;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getPhoneMac() {
		return phoneMac;
	}

	public void setPhoneMac(String phoneMac) {
		this.phoneMac = phoneMac;
	}

	public String getPushTime() {
		return pushTime;
	}

	public void setPushTime(String pushTime) {
		this.pushTime = pushTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	public String getClickTime() {
		return clickTime;
	}

	public void setClickTime(String clickTime) {
		this.clickTime = clickTime;
	}

	public String getSourceKeyWord() {
		return sourceKeyWord;
	}

	public void setSourceKeyWord(String sourceKeyWord) {
		this.sourceKeyWord = sourceKeyWord;
	}

	public String getSourceTitle() {
		return sourceTitle;
	}

	public void setSourceTitle(String sourceTitle) {
		this.sourceTitle = sourceTitle;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

}
