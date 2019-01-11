package org.rapp.pojo.dto.param;

/**
 * 条件查询设备
 * 
 * @author 张芳
 *
 */
public class RouterParam extends BaseTableParam {

	/**
	 * 设备编码
	 */
	private String devNo;
	/**
	 * 设备名称
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
	 * 固件ID
	 */
	private String fwId;
	/**
	 * 状态
	 */
	private String state;
	/**
	 * 所属人
	 */
	private String createBy;

	public String getDevNo() {
		return devNo;
	}

	public void setDevNo(String devNo) {
		this.devNo = devNo;
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

	public String getFwId() {
		return fwId;
	}

	public void setFwId(String fwId) {
		this.fwId = fwId;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

}
