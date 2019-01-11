package org.rapp.pojo.entry.authentication.wechat;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信渠道认证信息
 * 
 * @author 张芳
 *
 */
public class WechatAuth implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4615315397121376670L;

	/**
	 * 主键
	 */
	private long id;
	/**
	 * 渠道
	 */
	private String channelId;
	/**
	 * 商家微信公众平台账号
	 */
	private String appId;
	/**
	 * AP设备所在门店的ID
	 */
	private String shopId;
	/**
	 * 门店的开发者密钥
	 */
	private String secretKey;
	/**
	 * 绑定日期
	 */
	private Date createDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
