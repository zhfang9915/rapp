package org.rapp.pojo.entry;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AdvertUploadLog {

	private String logid;

	private int state;

	private String timestamp;

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		Timestamp ts = new Timestamp(Long.valueOf(timestamp));
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.timestamp = sdf.format(ts);
	}

	@Override
	public String toString() {
		return "AdvertUploadLog [logid=" + logid + ", state=" + state + ", timestamp=" + timestamp + "]";
	}

	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
	}
}
