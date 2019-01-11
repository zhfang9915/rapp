package org.rapp.pojo.dto.param;

public class PluginUnitParam extends BaseTableParam {

	/**
	 * 插件编码
	 */
	private String pluginId;
	/**
	 * 插件名称
	 */
	private String pluginName;

	public String getPluginId() {
		return pluginId;
	}

	public void setPluginId(String pluginId) {
		this.pluginId = pluginId;
	}

	public String getPluginName() {
		return pluginName;
	}

	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}

}
