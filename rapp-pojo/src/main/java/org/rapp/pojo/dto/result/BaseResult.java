package org.rapp.pojo.dto.result;

import java.io.Serializable;

/**
 * 所有的ajax请求返回类型，封装json结果
 * 
 * @ClassName: SeckillResult
 * @Description: TODO
 * @author zhfang
 * 
 * @param <T>
 */
public class BaseResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -244265065717082501L;
	private boolean success;
	private T data;
	private String msg;

	public BaseResult() {
		super();
	}

	public BaseResult(T data) {
		this.success = true;
		this.data = data;
	}

	public BaseResult(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
