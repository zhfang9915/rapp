/**
 * 创建于 2016年8月29日 上午11:31:38
 *
 */

package org.rapp.pojo.account;

import java.io.Serializable;

/**
 * @ClassName: Account
 * @Description: TODO
 * @author zhfang
 * 
 */
public class User extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4774114682920479283L;

	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 邮箱地址
	 */
	private String email;
	/**
	 * 状态：1可用 2不可用
	 */
	private int state;
	/**
	 * 权限ID
	 */
	private int roleId;
	/**
	 * 角色
	 */
	private Role role;

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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", state=" + state + ", roleId=" + roleId
				+ ", role=" + role + "]";
	}

}
