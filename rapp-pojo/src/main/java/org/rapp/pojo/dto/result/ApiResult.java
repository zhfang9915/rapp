package org.rapp.pojo.dto.result;

/**
 * 接口公共结果集
 * 
 * @author 张芳
 *
 * @param <T>
 */
public class ApiResult<T> {

	private String code;

	private String msg;

	private T data;
	/**
	 * 数据的MD5值
	 */
	private String token;

	public ApiResult() {
		super();
	}

	public ApiResult(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public ApiResult(T data) {
		super();
		this.code = "000";
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
