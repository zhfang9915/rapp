package org.rapp.pojo.dto.param;

/**
 * 渠道搜索参数
 * 
 * @author 张芳
 *
 */
public class ChannelParam extends BaseTableParam {

	private String channelId;

	private String channelNameEn;

	private String channelNameCh;

	private String state;

	private String createBy;

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

}
