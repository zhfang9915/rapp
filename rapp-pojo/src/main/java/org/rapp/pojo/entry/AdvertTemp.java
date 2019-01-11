package org.rapp.pojo.entry;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 广告模版
 * 
 * @author 张芳
 *
 */
public class AdvertTemp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2326503666305857001L;

	/**
	 * 模版Id
	 */
	private int tempId;
	/**
	 * 模版名称
	 */
	private String tempName;
	/**
	 * 状态 Y启用 N禁用
	 */
	private String state;
	/**
	 * 模版
	 */
	private String temp;
	/**
	 * 循环部分
	 */
	private String tempFor;
	/**
	 * 模版JS
	 */
	private String tempJs;
	/**
	 * 是否多资源 N单个 Y多个
	 */
	private String isMore = "N";
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date createTime;

	public int getTempId() {
		return tempId;
	}

	public void setTempId(int tempId) {
		this.tempId = tempId;
	}

	public String getTempName() {
		return tempName;
	}

	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getTempFor() {
		return tempFor;
	}

	public void setTempFor(String tempFor) {
		this.tempFor = tempFor;
	}

	public String getTempJs() {
		return tempJs;
	}

	public void setTempJs(String tempJs) {
		this.tempJs = tempJs;
	}

	public String getIsMore() {
		return isMore;
	}

	public void setIsMore(String isMore) {
		this.isMore = isMore;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "AdvertTemp [tempId=" + tempId + ", tempName=" + tempName + ", state=" + state + ", temp=" + temp
				+ ", tempFor=" + tempFor + ", tempJs=" + tempJs + ", isMore=" + isMore + ", createTime=" + createTime
				+ "]";
	}

}
