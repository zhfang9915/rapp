package org.rapp.pojo.entry;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.rapp.pojo.account.BaseEntity;

/**
 * 广告实体
 * 
 * @author 张芳
 *
 */
public class Advert extends BaseEntity {

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
	 * 下架时间
	 */
	private String offTime;
	/**
	 * 状态：0禁用 1待审核(默认) 2审核通过 3审核未通过 4启用
	 */
	private int state;
	/**
	 * 广告模版Id
	 */
	private int tempId;
	/**
	 * 广告模版
	 */
	private AdvertTemp temp;
	/**
	 * 推送权重 默认为1
	 */
	private int weight = 1;
	/**
	 * 备注
	 */
	private String remark;

	private String channelsId;

	/**
	 * 所属渠道
	 */
	private List<Channel> channels;
	/**
	 * 广告资源
	 */
	private List<AdvertItem> items;

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

	public String getChannelsId() {
		return channelsId;
	}

	public void setChannelsId(String channelsId) {
		this.channelsId = channelsId;
	}

	public List<Channel> getChannels() {
		return channels;
	}

	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getOffTime() {
		return offTime;
	}

	public void setOffTime(String offTime) {
		this.offTime = offTime;
	}

	public int getTempId() {
		return tempId;
	}

	public void setTempId(int tempId) {
		this.tempId = tempId;
	}

	public AdvertTemp getTemp() {
		return temp;
	}

	public void setTemp(AdvertTemp temp) {
		this.temp = temp;
	}

	public List<AdvertItem> getItems() {
		return items;
	}

	public void setItems(List<AdvertItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Advert [advertId=" + advertId + ", advertName=" + advertName + ", offTime=" + offTime + ", state="
				+ state + ", tempId=" + tempId + ", temp=" + temp + ", weight=" + weight + ", remark=" + remark
				+ ", channelsId=" + channelsId + ", channels=" + channels + ", items=" + items + "]";
	}

}
