package org.rapp.pojo.log;

import java.util.Date;

import org.rapp.pojo.entry.Advert;
import org.rapp.pojo.entry.Router;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AdvertPushLog {

	/**
	 * 主键
	 */
	private String id;
	/**
	 * 路由器设备号
	 */
	private String devNo;
	/**
	 * 设备信息
	 */
	private Router router;
	/**
	 * 被推送的广告ID
	 */
	private String advertId;
	/**
	 * 广告信息
	 */
	private Advert advert;
	/**
	 * 当前页面的URL
	 */
	private String url;
	/**
	 * 被推送的时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date pushTime;
	/**
	 * 手机MAC地址
	 */
	private String phoneMac;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDevNo() {
		return devNo;
	}

	public void setDevNo(String devNo) {
		this.devNo = devNo;
	}

	public String getAdvertId() {
		return advertId;
	}

	public void setAdvertId(String advertId) {
		this.advertId = advertId;
	}

	public Date getPushTime() {
		return pushTime;
	}

	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Router getRouter() {
		return router;
	}

	public void setRouter(Router router) {
		this.router = router;
	}

	public Advert getAdvert() {
		return advert;
	}

	public void setAdvert(Advert advert) {
		this.advert = advert;
	}

	public String getPhoneMac() {
		return phoneMac;
	}

	public void setPhoneMac(String phoneMac) {
		this.phoneMac = phoneMac;
	}

}
