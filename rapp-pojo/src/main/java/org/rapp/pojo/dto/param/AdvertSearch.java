package org.rapp.pojo.dto.param;

public class AdvertSearch extends BaseTableParam {

	private String logid;

	private String startTime;

	private String endTime;

	private String devNo;

	private String advertName;

	private String advertId;

	private String createBy;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDevNo() {
		return devNo;
	}

	public void setDevNo(String devNo) {
		this.devNo = devNo;
	}

	public String getAdvertName() {
		return advertName;
	}

	public void setAdvertName(String advertName) {
		this.advertName = advertName;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public String getAdvertId() {
		return advertId;
	}

	public void setAdvertId(String advertId) {
		this.advertId = advertId;
	}

	@Override
	public String toString() {
		return "AdvertSearch [logid=" + logid + ", startTime=" + startTime + ", endTime=" + endTime + ", devNo=" + devNo
				+ ", advertName=" + advertName + ", advertId=" + advertId + ", createBy=" + createBy + "]";
	}

}
