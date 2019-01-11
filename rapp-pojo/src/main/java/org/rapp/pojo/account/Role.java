package org.rapp.pojo.account;

import java.io.Serializable;
import java.util.List;

/**
 * 角色
 * 
 * @author 张芳
 *
 */
public class Role extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7636481141781078863L;

	/**
	 * 角色ID
	 */
	private int roleId;
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 目录菜单
	 */
	private String menu;

	/**
	 * 最大广告配置数量
	 */
	private int advertMax;

	private List<Auth> auths;

	public Role() {
		super();
	}

	public Role(int roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Auth> getAuths() {
		return auths;
	}

	public void setAuths(List<Auth> auths) {
		this.auths = auths;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public int getAdvertMax() {
		return advertMax;
	}

	public void setAdvertMax(int advertMax) {
		this.advertMax = advertMax;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", auths=" + auths + "]";
	}

}
