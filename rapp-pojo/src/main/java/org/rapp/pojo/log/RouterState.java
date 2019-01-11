package org.rapp.pojo.log;

import java.util.Date;

/**
 * 设备状态
 * 
 * @author 张芳
 *
 */
public class RouterState {

	/**
	 * 设备编码
	 */
	private String devNo;
	/**
	 * 请求时间
	 */
	private Date requestDate;
	/**
	 * 活跃类型
	 */
	private int type;

	public RouterState(String devNo, int type) {
		super();
		this.devNo = devNo;
		this.type = type;
		this.requestDate = new Date();
	}

	public String getDevNo() {
		return devNo;
	}

	public void setDevNo(String devNo) {
		this.devNo = devNo;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
