package org.rapp.pojo.echart;

import java.util.List;

public class BarSeries<T> {
	/**
	 * X轴名称
	 */
	private String name;
	/**
	 * 类型
	 */
	private String type = "bar";
	/**
	 * 最大宽带
	 */
	private String barMaxWidth = "15px";
	/**
	 * 这里要用int 不能用String 不然前台显示不正常（特别是在做数学运算的时候）
	 */
	private List<T> data;

	public BarSeries(String name, List<T> data) {
		super();
		this.name = name;
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBarMaxWidth() {
		return barMaxWidth;
	}

	public void setBarMaxWidth(String barMaxWidth) {
		this.barMaxWidth = barMaxWidth;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
