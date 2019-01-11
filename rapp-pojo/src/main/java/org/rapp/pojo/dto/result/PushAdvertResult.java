package org.rapp.pojo.dto.result;

public class PushAdvertResult {

	private String logid;

	private String data;
	private String[] js;

	public PushAdvertResult(String logid, String data, String[] js) {
		super();
		this.logid = logid;
		this.data = data;
		this.js = js;
	}

	public PushAdvertResult() {
		super();
	}

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String[] getJs() {
		return js;
	}

	public void setJs(String[] js) {
		this.js = js;
	}

}
