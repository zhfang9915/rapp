package org.rapp.pojo.echart;

import java.util.List;

public class PieSeries<T> {
	/**
	 * X轴名称
	 */
	private String name;
	/**
	 * 类型
	 */
	private String type = "pie";
	/**
	 * 饼图的半径
	 */
	private String radius = "55%";
	/**
	 * 饼图的中心（圆心）坐标，数组的第一项是横坐标，第二项是纵坐标
	 */
	private String[] center = { "50%", "60%" };
	/**
	 * 这里要用int 不能用String 不然前台显示不正常（特别是在做数学运算的时候）
	 */
	private List<T> data;

	public PieSeries(String name, List<T> data) {
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

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	public String[] getCenter() {
		return center;
	}

	public void setCenter(String[] center) {
		this.center = center;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
