package org.rapp.pojo.entry;

import org.rapp.pojo.account.BaseEntity;

public class JSCode extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1684296971795701699L;
	/**
	 * JS代码编码
	 */
	private String codeId;
	/**
	 * 代码名称
	 */
	private String codeName;
	/**
	 * 是否默认Js  1默认  0自定义
	 */
	private int isDefault;
	/**
	 * js源码
	 */
	private String code;
	/**
	 * 广告服务器域名/IP
	 */
	private String serverIp;
	/**
	 * 广告服务器端口
	 */
	private String serverPort;
	/**
	 * 备注
	 */
	private String remark;

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getServerPort() {
		return serverPort;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public String toString() {
		return "JSCode [codeId=" + codeId + ", codeName=" + codeName + ", code=" + code + ", serverIp=" + serverIp
				+ ", serverPort=" + serverPort + ", remark=" + remark + "]";
	}

}
