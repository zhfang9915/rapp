package org.rapp.comm.util;

public enum EmailType {

	用户注册("register"),
	找回密码("findPassword");

	private String value;

	public String getValue() {
		return value;
	}

	private EmailType(String value) {
		this.value = value;
	}
}
