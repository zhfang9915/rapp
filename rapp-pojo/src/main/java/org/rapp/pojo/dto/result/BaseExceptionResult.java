package org.rapp.pojo.dto.result;

public class BaseExceptionResult {

	private boolean exception;

	private String exceptionMsg;

	public BaseExceptionResult(boolean exception, String exceptionMsg) {
		super();
		this.exception = exception;
		this.exceptionMsg = exceptionMsg;
	}

	public boolean isException() {
		return exception;
	}

	public void setException(boolean exception) {
		this.exception = exception;
	}

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

}
