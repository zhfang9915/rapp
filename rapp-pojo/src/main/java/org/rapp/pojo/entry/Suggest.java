package org.rapp.pojo.entry;

import java.util.Date;

/**
 * 建议实体
 * 
 * @author 张芳
 *
 */
public class Suggest {

	private int id;
	/**
	 * 建议人
	 */
	private String createBy;
	/**
	 * 主题
	 */
	private String title;
	/**
	 * 建议内容
	 */
	private String context;
	/**
	 * 建议时间
	 */
	private Date createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Suggest [id=" + id + ", createBy=" + createBy + ", title=" + title + ", context=" + context
				+ ", createTime=" + createTime + "]";
	}

}
