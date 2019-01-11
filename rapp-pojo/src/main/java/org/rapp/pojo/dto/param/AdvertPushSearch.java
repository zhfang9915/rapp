package org.rapp.pojo.dto.param;

import org.rapp.pojo.statistical.StatisticalParam;

public class AdvertPushSearch extends BaseTableParam {

	private String id;

	private String startTime;

	private String endTime;

	private String pushTime;

	private String devNo;

	private String advertId;
	
	private String advertName;

	private String createBy;

	private String dateType;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdvertId() {
		return advertId;
	}

	public void setAdvertId(String advertId) {
		this.advertId = advertId;
	}

	public String getDateType() {
		return dateType;
	}

	public String getPushTime() {
		return pushTime;
	}

	public void setPushTime(String pushTime) {
		this.pushTime = pushTime;
	}

	public void setDateType(String dateType) {
		if (dateType == null) {
			this.dateType = StatisticalParam.DAY;
		} else {
			switch (dateType) {
			case "Y":
				this.dateType = StatisticalParam.YEAR;
				break;
			case "M":
				this.dateType = StatisticalParam.MONTH;
				break;
			case "D":
				this.dateType = StatisticalParam.DAY;
				break;
			case "H":
				this.dateType = StatisticalParam.HOUR;
				break;
			default:
				this.dateType = StatisticalParam.DAY;
				break;
			}
		}
	}

	@Override
	public String toString() {
		return "AdvertPushSearch [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", pushTime="
				+ pushTime + ", devNo=" + devNo + ", advertName=" + advertName + ", advertId=" + advertId
				+ ", createBy=" + createBy + ", dateType=" + dateType + "]";
	}

}
