package org.rapp.pojo.wifi;

import org.rapp.pojo.account.BaseEntity;

public class FrameData extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String dataId;

	private String spyId;
	/**
	 * 手机的MAC地址
	 */
	private String staMac;
	/**
	 * AP的MAC 权威0XFF表示不带要AP Mac字段，只有在不全为0XFF的时候才记录数据库
	 */
	private String apMac;
	/**
	 * AP的ssid或者历史的ssid,可根据AP mac来区分
	 */
	private String ssId;
	/**
	 * AP的功率
	 */
	private int pwr;
	/**
	 * AP的信道，只有的AP MAC不全为0xFF时有效
	 */
	private int channel;

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getSpyId() {
		return spyId;
	}

	public void setSpyId(String spyId) {
		this.spyId = spyId;
	}

	public String getStaMac() {
		return staMac;
	}

	public void setStaMac(String staMac) {
		this.staMac = staMac;
	}

	public String getApMac() {
		return apMac;
	}

	public void setApMac(String apMac) {
		this.apMac = apMac;
	}

	public String getSsId() {
		return ssId;
	}

	public void setSsId(String ssId) {
		this.ssId = ssId;
	}

	public int getPwr() {
		return pwr;
	}

	public void setPwr(int pwr) {
		this.pwr = pwr;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	@Override
	public String toString() {
		return "FrameData [dataId=" + dataId + ", spyId=" + spyId + ", staMac=" + staMac + ", apMac=" + apMac
				+ ", ssId=" + ssId + ", pwr=" + pwr + ", channel=" + channel + "]";
	}

}
