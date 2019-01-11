package org.rapp.pojo.entry;

import org.rapp.pojo.account.BaseEntity;

/**
 * 插件实体POJO
 * 
 * @author 张芳
 *
 */
public class PluginUnit extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5338725035771295875L;
	/**
	 * 插件编码
	 */
	private String pluginId;
	/**
	 * 插件名称
	 */
	private String pluginName;
	/**
	 * 插件版本
	 */
	private String version;
	/**
	 * 平台固件版本信息
	 */
//	private FirmWare fw;
	/**
	 * 交叉编译版本
	 */
	private String crossstool;
	/**
	 * 插件源文件存放路径
	 */
	private String pluginPath;
	/**
	 * 插件的下载链接
	 */
	private String downloadUrl;
	/**
	 * 插件的MD5值
	 */
	private String md5;
	/**
	 * 状态 0禁用 1启用
	 */
	private String state;
	/**
	 * 备注
	 */
	private String remark;

	public String getPluginId() {
		return pluginId;
	}

	public void setPluginId(String pluginId) {
		this.pluginId = pluginId;
	}

	public String getPluginName() {
		return pluginName;
	}

	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
//
//	public FirmWare getFw() {
//		return fw;
//	}
//
//	public void setFw(FirmWare fw) {
//		this.fw = fw;
//	}

	public String getPluginPath() {
		return pluginPath;
	}

	public void setPluginPath(String pluginPath) {
		this.pluginPath = pluginPath;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCrossstool() {
		return crossstool;
	}

	public void setCrossstool(String crossstool) {
		this.crossstool = crossstool;
	}

}
