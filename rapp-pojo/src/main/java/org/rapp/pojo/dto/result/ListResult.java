package org.rapp.pojo.dto.result;

import java.util.List;

/**
 * @ClassName: TableResult
 * @author zhfang
 * 
 */
public class ListResult<T> {
	/**
	 * 总数
	 */
	private int pageCount;
	/**
	 * 行数据
	 */
	private List<T> data;

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public ListResult(int pageCount, List<T> data) {
		super();
		this.pageCount = pageCount;
		this.data = data;
	}

	public ListResult() {
		super();
	}

}
