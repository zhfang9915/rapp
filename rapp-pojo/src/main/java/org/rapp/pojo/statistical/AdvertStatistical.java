package org.rapp.pojo.statistical;

import java.io.Serializable;

/**
 * 广告推送次数数据统计
 * 
 * @author 张芳
 *
 */
public class AdvertStatistical implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4811291215267909299L;
	/**
	 * 推送量
	 */
	private long pushCount;
	/**
	 * 点击量
	 */
	private long clickCount;
	/**
	 * 广告名称
	 */
	private String name;

	public AdvertStatistical(long pushCount, long clickCount, String name) {
		super();
		this.pushCount = pushCount;
		this.clickCount = clickCount;
		this.name = name;
	}

	public AdvertStatistical() {
		super();
	}

	public long getPushCount() {
		return pushCount;
	}

	public void setPushCount(long pushCount) {
		this.pushCount = pushCount;
	}

	public long getClickCount() {
		return clickCount;
	}

	public void setClickCount(long clickCount) {
		this.clickCount = clickCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
