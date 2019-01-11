package org.rapp.pojo.dto.result;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ChannelReuslt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6897282319064080183L;
	/**
	 * 区域名称
	 */
	private String channelId;
	/**
	 * 区域名称
	 */
	private String channelName;
	/**
	 * 区域创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	/**
	 * 总设备数
	 */
	private Long countRouter;
	/**
	 * 在线设备
	 */
	private Long onlineRouter;
	/**
	 * 当前链接用户
	 */
	private Long connectPhone;
	/**
	 * 累积推送量
	 */
	private Long countPush;

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCountRouter() {
		return countRouter;
	}

	public void setCountRouter(Long countRouter) {
		this.countRouter = countRouter;
	}

	public Long getOnlineRouter() {
		return onlineRouter;
	}

	public void setOnlineRouter(Long onlineRouter) {
		this.onlineRouter = onlineRouter;
	}

	public Long getConnectPhone() {
		return connectPhone;
	}

	public void setConnectPhone(Long connectPhone) {
		this.connectPhone = connectPhone;
	}

	public Long getCountPush() {
		return countPush;
	}

	public void setCountPush(Long countPush) {
		this.countPush = countPush;
	}

	@Override
	public String toString() {
		return "ChannelReuslt [channelId=" + channelId + ", channelName=" + channelName + ", createTime=" + createTime
				+ ", countRouter=" + countRouter + ", onlineRouter=" + onlineRouter + ", connectPhone=" + connectPhone
				+ ", countPush=" + countPush + "]";
	}

}
