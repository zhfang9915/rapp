package org.rapp.pojo.entry;

import java.io.Serializable;

import org.rapp.pojo.account.BaseEntity;

/**
 * 固件实体
 * 
 * @author 张芳
 *
 */
public class FirmWare extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 398078662205251464L;

	/**
	 * 固件ID
	 */
	private String fwId;
	/**
	 * 固件名称
	 */
	private String fwName;
	/**
	 * 固件存放的系统路径
	 */
	private String fwPath;
	/**
	 * 下载链接
	 */
	private String downloadUrl;
	/**
	 * 固件版本
	 */
	private String version;
	/**
	 * openwrt版本
	 */
	private String openwrt;
	/**
	 * 交叉编译工具版本
	 */
	private String crossstool;
	/**
	 * 刷机教程说明
	 */
	private String flashCourse;
	/**
	 * 备注
	 */
	private String remark;

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

	public String getFwPath() {
		return fwPath;
	}

	public void setFwPath(String fwPath) {
		this.fwPath = fwPath;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getOpenwrt() {
		return openwrt;
	}

	public void setOpenwrt(String openwrt) {
		this.openwrt = openwrt;
	}

	public String getCrossstool() {
		return crossstool;
	}

	public void setCrossstool(String crossstool) {
		this.crossstool = crossstool;
	}

	public String getFlashCourse() {
		return flashCourse;
	}

	public void setFlashCourse(String flashCourse) {
		this.flashCourse = flashCourse;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

}
