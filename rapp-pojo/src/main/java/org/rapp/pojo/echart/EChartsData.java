package org.rapp.pojo.echart;

import java.util.ArrayList;
import java.util.List;

/**
 * Echarts 数据封装
 * @author 张芳
 *
 */
public class EChartsData<T> {

	/**
	 * 数据分组
	 */
	public List<String> legend = new ArrayList<String>();
	/**
	 * 横坐标
	 */
	public List<String> category = new ArrayList<String>();
	/**
	 * 纵坐标
	 */
	public List<T> series = new ArrayList<T>();

	public EChartsData(List<String> legendList, List<String> categoryList, List<T> seriesList) {
		super();
		this.legend = legendList;
		this.category = categoryList;
		this.series = seriesList;
	}
}
