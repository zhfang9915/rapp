package org.rapp.pojo.entry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.rapp.pojo.dto.param.api.BaseApiParam;

/**
 * 广告流水
 * 
 * @author 张芳
 *
 */
public class AdvertLog extends BaseApiParam {

	/**
	 * 流水序列号
	 */
	private String id;
	/**
	 * 手机MAC地址
	 */
	private String phoneMac;
	/**
	 * 渠道信息
	 */
	private String channelId;
	/**
	 * 路由器详细地址
	 */
	private String routerDetail;
	/**
	 * 网页URL
	 */
	private String sourceUrl;
	/**
	 * 网页标题
	 */
	private String sourceTitle;
	/**
	 * 网页关键字
	 */
	private String sourceKeyWord;
	/**
	 * 关闭时间
	 */
	private Date closeTime;
	/**
	 * 点击时间
	 */
	private Date clickTime;
	/**
	 * 显示时间
	 */
	private Date showTime;
	/**
	 * 广告ID
	 */
	private String logid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhoneMac() {
		return phoneMac;
	}

	public void setPhoneMac(String phoneMac) {
		this.phoneMac = phoneMac;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getRouterDetail() {
		return routerDetail;
	}

	public void setRouterDetail(String routerDetail) {
		this.routerDetail = routerDetail;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getSourceTitle() {
		return sourceTitle;
	}

	public void setSourceTitle(String sourceTitle) {
		this.sourceTitle = sourceTitle;
	}

	public String getSourceKeyWord() {
		return sourceKeyWord;
	}

	public void setSourceKeyWord(String sourceKeyWord) {
		this.sourceKeyWord = sourceKeyWord;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = parseDate(closeTime);
	}

	public Date getClickTime() {
		return clickTime;
	}

	public void setClickTime(String clickTime) {
		this.clickTime = parseDate(clickTime);
	}

	public Date getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = parseDate(showTime);
	}

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	/**
	 * 字符串转日期
	 * 
	 * @param dateString
	 * @return
	 */
	private Date parseDate(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");// 小写的mm表示的是分钟
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
		return date;
	}

	@Override
	public String toString() {
		return "AdvertLog [id=" + id + ", phoneMac=" + phoneMac + ", channelId=" + channelId + ", routerDetail="
				+ routerDetail + ", sourceUrl=" + sourceUrl + ", sourceTitle=" + sourceTitle + ", sourceKeyWord="
				+ sourceKeyWord + ", closeTime=" + closeTime + ", clickTime=" + clickTime + ", showTime=" + showTime
				+ ", logid=" + logid + "]";
	}
	
	

}
