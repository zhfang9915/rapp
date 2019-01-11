package org.rapp.pojo.entry;

import java.io.Serializable;
import java.util.List;

/**
 * 广告实体
 * 
 * @author 张芳
 *
 */
public class AdvertSubmit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8152671825041749445L;

	/**
	 * 广告主键
	 */
	private String advertId;
	/**
	 * 广告名称
	 */
	private String advertName;
	/**
	 * 状态：0禁用 1待审核(默认) 2审核通过 3审核未通过 4启用
	 */
	private String state;
	/**
	 * 广告类型：1图文广告 2视频广告 3插屏广告
	 */
	private String advertType;
	/**
	 * 推送权重 默认为1
	 */
	private String weight = "1";

	/**
	 * 广告内容URL
	 */
	private String advertUrl;
	/**
	 * 本地存放的目录
	 */
	private String localUrl;
	/**
	 * 跳转链接
	 */
	private String toUrl;
	/**
	 * 广告显示的位置：1顶部 2底部 3屏幕中间
	 */
	// private int location;
	/**
	 * 备注
	 */
	private String remark;

	private String channelsId;

	private List<Channel> channels;

	/**
	 * 创建人
	 */
	private String createBy;

	public String getAdvertId() {
		return advertId;
	}

	public void setAdvertId(String advertId) {
		this.advertId = advertId;
	}

	public String getAdvertName() {
		return advertName;
	}

	public void setAdvertName(String advertName) {
		this.advertName = advertName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAdvertType() {
		return advertType;
	}

	public void setAdvertType(String advertType) {
		this.advertType = advertType;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getAdvertUrl() {
		return advertUrl;
	}

	public void setAdvertUrl(String advertUrl) {
		this.advertUrl = advertUrl;
	}

	public String getToUrl() {
		return toUrl;
	}

	public void setToUrl(String toUrl) {
		this.toUrl = toUrl;
	}

	// public int getLocation() {
	// return location;
	// }
	//
	// public void setLocation(int location) {
	// this.location = location;
	// }

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getChannelsId() {
		return channelsId;
	}

	public void setChannelsId(String channelsId) {
		this.channelsId = channelsId;
	}

	public String getLocalUrl() {
		return localUrl;
	}

	public void setLocalUrl(String localUrl) {
		this.localUrl = localUrl;
	}

	public List<Channel> getChannels() {
		return channels;
	}

	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return "Advert [advertId=" + advertId + ", advertName=" + advertName + ", state=" + state + ", advertType="
				+ advertType + ", weight=" + weight + ", advertUrl=" + advertUrl + ", localUrl=" + localUrl + ", toUrl="
				+ toUrl + ", remark=" + remark + ", channelsId=" + channelsId + ", channels=" + channels + "]";
	}

}
