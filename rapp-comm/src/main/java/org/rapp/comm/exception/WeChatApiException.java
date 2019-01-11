package org.rapp.comm.exception;

public class WeChatApiException extends RuntimeException {
	
	private static final long serialVersionUID = 4829517229959880736L;
	
	private String errCode;

	public WeChatApiException() {
		super();
	}

	public WeChatApiException(String message) {
		super(message);
	}
	
	public WeChatApiException(String errCode, String message) {
		super(message);
		this.errCode = errCode;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	} 
	
}
