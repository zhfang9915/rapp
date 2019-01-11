package org.rapp.pojo.log;

import org.rapp.pojo.account.BaseEntity;

public class AdvertCheckLog extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4239956116028237823L;

	/**
	 * 消息ID
	 */
	private int id;
	/**
	 * 广告ID
	 */
	private String advertId;
	/**
	 * 是否已读
	 */
	private int haveRead;
	/**
	 * 消息标题
	 */
	private String title;
	/**
	 * 消息内容
	 */
	private String msg;
	/**
	 * 接收人
	 */
	private String reviewer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getAdvertId() {
		return advertId;
	}

	public void setAdvertId(String advertId) {
		this.advertId = advertId;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public int getHaveRead() {
		return haveRead;
	}

	public void setHaveRead(int haveRead) {
		this.haveRead = haveRead;
	}

}
