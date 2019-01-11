package org.rapp.pojo.dto.param;

public class FirmWareParam extends BaseTableParam {

	/**
	 * 固件ID
	 */
	private String fwId;
	/**
	 * 固件名称
	 */
	private String fwName;
	/**
	 * 版本
	 */
	private String version;

	public String getFwId() {
		return fwId;
	}

	public void setFwId(String fwId) {
		this.fwId = fwId;
	}

	public String getFwName() {
		return fwName;
	}

	public void setFwName(String fwName) {
		this.fwName = fwName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
