package org.rapp.pojo.entry;

import java.io.Serializable;

import org.rapp.pojo.account.BaseEntity;

/**
 * 渠道实体
 * 
 * @author 张芳
 *
 */
public class Channel extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5239998353784164820L;

	/**
	 * 渠道ID
	 */
	private String channelId;
	/**
	 * 渠道名称简写(字母+数字)
	 */
	private String channelNameEn;
	/**
	 * 渠道名称
	 */
	private String channelNameCh;
	/**
	 * 状态：0 禁用 1启用
	 */
	private int state;
	/**
	 * 渠道下的JS源码
	 */
	private JSCode code;
	/**
	 * 模版ID
	 */
	private String codeId;
	/**
	 * 备注
	 */
	private String remark;

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannelNameEn() {
		return channelNameEn;
	}

	public void setChannelNameEn(String channelNameEn) {
		this.channelNameEn = channelNameEn;
	}

	public String getChannelNameCh() {
		return channelNameCh;
	}

	public void setChannelNameCh(String channelNameCh) {
		this.channelNameCh = channelNameCh;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public JSCode getCode() {
		return code;
	}

	public void setCode(JSCode code) {
		this.code = code;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	@Override
	public String toString() {
		return "Channel [channelId=" + channelId + ", channelNameEn=" + channelNameEn + ", channelNameCh="
				+ channelNameCh + ", state=" + state + ", code=" + code + ", codeId=" + codeId + ", remark=" + remark
				+ "]";
	}

}
