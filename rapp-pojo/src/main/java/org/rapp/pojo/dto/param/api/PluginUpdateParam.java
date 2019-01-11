package org.rapp.pojo.dto.param.api;

public class PluginUpdateParam extends BaseApiParam {

	/**
	 * 固件版本
	 */
	private String fwv;
	/**
	 * 交叉编译版本
	 */
	private String gccv;
	/**
	 * 当前插件版本
	 */
	private String pv;

	public String getFwv() {
		return fwv;
	}

	public void setFwv(String fwv) {
		this.fwv = fwv;
	}

	public String getGccv() {
		return gccv;
	}

	public void setGccv(String gccv) {
		this.gccv = gccv;
	}

	public String getPv() {
		return pv;
	}

	public void setPv(String pv) {
		this.pv = pv;
	}

	@Override
	public String toString() {
		return "PluginUpdateParam [fwv=" + fwv + ", gccv=" + gccv + ", pv=" + pv + ", toString()=" + super.toString()
				+ "]";
	}

	
}
