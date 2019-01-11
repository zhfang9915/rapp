package org.rapp.pojo.entry;

import java.io.Serializable;

import org.rapp.pojo.account.BaseEntity;

public class Router extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 630280736089969423L;
	/**
	 * 设备ID
	 */
	private String devNo;
	/**
	 * 设备别名
	 */
	private String devName;
	/**
	 * 设备MAC
	 */
	private String mac;
	/**
	 * 所属渠道
	 */
	private String channelId;
	/**
	 * 渠道
	 */
	private Channel channel;
	/**
	 * 固件ID
	 */
	private String fwId;
	/**
	 * 固件
	 */
	private FirmWare fw;
	/**
	 * 固件版本
	 */
	private String fwv;
	/**
	 * 状态 1未激活 2激活 3欠费停机
	 */
	private int state;
	/**
	 * 所在城市
	 */
	private String selectCity;
	/**
	 * 地址ID
	 */
	private Integer address;
	/**
	 * 经度
	 */
	private Double lng;
	/**
	 * 纬度
	 */
	private Double lat;
	/**
	 * 安装地址
	 */
	private String installAddress;
	/**
	 * 口令
	 */
	private String token;
	/**
	 * 回调地址
	 */
	private String backUrl;
	/**
	 * 备注
	 */
	private String remark;

	public String getDevNo() {
		return devNo;
	}

	public void setDevNo(String devNo) {
		this.devNo = devNo;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getFwId() {
		return fwId;
	}

	public void setFwId(String fwId) {
		this.fwId = fwId;
	}

	public FirmWare getFw() {
		return fw;
	}

	public void setFw(FirmWare fw) {
		this.fw = fw;
	}

	public String getFwv() {
		return fwv;
	}

	public void setFwv(String fwv) {
		this.fwv = fwv;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getSelectCity() {
		return selectCity;
	}

	public void setSelectCity(String selectCity) {
		this.selectCity = selectCity;
	}

	public Integer getAddress() {
		return address;
	}

	public void setAddress(Integer address) {
		this.address = address;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public String getInstallAddress() {
		return installAddress;
	}

	public void setInstallAddress(String installAddress) {
		this.installAddress = installAddress;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Router [devNo=" + devNo + ", devName=" + devName + ", mac=" + mac + ", channelId=" + channelId
				+ ", channel=" + channel + ", fwId=" + fwId + ", fw=" + fw + ", fwv=" + fwv + ", state=" + state
				+ ", selectCity=" + selectCity + ", address=" + address + ", lng=" + lng + ", lat=" + lat
				+ ", installAddress=" + installAddress + ", token=" + token + ", backUrl=" + backUrl + ", remark="
				+ remark + "]";
	}

}
