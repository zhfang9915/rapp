package org.rapp.pojo.dto.param;

import java.io.Serializable;

/**
 * 用户注册信息
 * 
 * @author 张芳
 *
 */
public class UserRegister implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3885799447335364607L;

	private String username;

	private String password;

	private String password_repeat;

	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword_repeat() {
		return password_repeat;
	}

	public void setPassword_repeat(String password_repeat) {
		this.password_repeat = password_repeat;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
