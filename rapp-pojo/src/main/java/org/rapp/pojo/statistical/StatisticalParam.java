package org.rapp.pojo.statistical;

public class StatisticalParam {

	/**
	 * 年
	 */
	public static final String YEAR = "%Y";
	/**
	 * 月
	 */
	public static final String MONTH = "%Y-%m";
	/**
	 * 日
	 */
	public static final String DAY = "%Y-%m-%d";
	/**
	 * 时
	 */
	public static final String HOUR = "%Y-%m-%d %H";

	private String createBy;

	private String advertId;

	private String advertName;

	private String startTime;

	private String endTime;

	/**
	 * 设备编码
	 */
	private String devNo;
	/**
	 * 推送时间
	 */
	private String pushTime;
	/**
	 * 统计类型
	 */
	private String statisticalType = DAY;

	private String whereType = MONTH;

	public StatisticalParam() {
		super();
	}

	public StatisticalParam(String createBy) {
		super();
		this.createBy = createBy;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

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

	public String getPushTime() {
		return pushTime;
	}

	public void setPushTime(String pushTime) {
		this.pushTime = pushTime;
	}

	public String getStatisticalType() {
		return statisticalType;
	}

	public void setStatisticalType(String type) {
		switch (type) {
		case "Y":
			this.statisticalType = YEAR;
			this.whereType = YEAR;
			break;
		case "M":
			this.statisticalType = MONTH;
			this.whereType = MONTH;
			break;
		case "D":
			this.statisticalType = DAY;
			this.whereType = DAY;
			break;
		case "H":
			this.statisticalType = HOUR;
			this.whereType = DAY;
			break;
		default:
			this.statisticalType = DAY;
			this.whereType = DAY;
			break;
		}
	}

	public String getWhereType() {
		return whereType;
	}

}
