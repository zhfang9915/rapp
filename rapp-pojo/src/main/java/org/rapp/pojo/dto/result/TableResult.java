package org.rapp.pojo.dto.result;

import java.util.List;

/**
 * @ClassName: TableResult
 * @author zhfang
 * 
 */
public class TableResult<T> {
	/**
	 * 总数
	 */
	private int total;
	/**
	 * 行数据
	 */
	private List<T> rows;

	public TableResult(int total, List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	public TableResult() {
		super();
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
