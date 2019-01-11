package org.rapp.service;

import java.util.List;
import java.util.Map;

import org.rapp.pojo.account.Auth;
import org.rapp.pojo.account.Role;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.dto.result.TableResult;

public interface RoleAuthService {

	/**
	 * 角色授权
	 * @param roleId
	 * @return
	 */
	Role accreditRole(int roleId);
	
	/**
	 * 查询权限列表
	 * @param param
	 * @return
	 */
	TableResult<Auth> queryAuths(Map<String, Object> param);
	
	/**
	 * 新增权限
	 * @param auth
	 * @return
	 */
	BaseResult<String> insertAuth(Auth auth);
	
	/**
	 * 更新权限
	 * @param auth
	 * @return
	 */
	BaseResult<String> updateAuth(Auth auth);
	
	/**
	 * 删除权限
	 */
	BaseResult<String> deleteAuth(List<Auth> auths);
	
	
	
	/**
	 * 查询权限列表
	 * @param param
	 * @return
	 */
	TableResult<Role> queryRoles(Map<String, Object> param);
	
	/**
	 * 新增角色
	 * @param role
	 * @return
	 */
	BaseResult<String> insertRole(Role role);
	
	/**
	 * 更具ID查角色
	 * @param roleId
	 * @return
	 */
	Role queryRoleById(String roleId);
	
	/**
	 * 更新角色
	 * @param role
	 * @return
	 */
	BaseResult<String> updateRole(Role role);
	
	/**
	 * 删除角色
	 */
	BaseResult<String> deleteRole(List<Role> roles);
	
	/**
	 * 查询已有/未有权限
	 * @param methed
	 * @param roleId
	 * @return
	 */
	TableResult<Auth> existOrOptionalAuths(String methed, String roleId, Map<String, Object> params);
	
	/**
	 * 为角色配置权限
	 * @param roleId
	 * @param auths
	 * @return
	 */
	BaseResult<String> authConfiguration(String method, String roleId, List<Auth> auths);

}
