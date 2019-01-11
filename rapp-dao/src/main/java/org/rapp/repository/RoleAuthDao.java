package org.rapp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.rapp.pojo.account.Auth;
import org.rapp.pojo.account.Role;

/**
 * 角色权限
 * @author 张芳
 */
public interface RoleAuthDao {
	
	/**
	 * 根据分页查询权限列表
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Auth> queryAuths(@Param("offset")String offset, @Param("limit")String limit, @Param("authName")String search);
	/**
	 * 查询权限列表总数
	 * @param authName
	 * @return
	 */
	int queryAuthsCount(@Param("authName")String authName);
	/**
	 * 新增权限
	 */
	int insertAuth(Auth auth);
	
	/**
	 * 更新权限
	 */
	int updateAuth(Auth auth);
	
	/**
	 * 删除权限
	 */
	int deleteAuth(List<Auth> auths);
	
	
	/*----------------------------------------------------------------------------*/
	
	/**
	 * 根据分页查询权限列表
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Role> queryRoles(@Param("offset")String offset, @Param("limit")String limit, @Param("roleName")String search);
	/**
	 * 查询权限列表总数
	 * @param roleName
	 * @return
	 */
	int queryRolesCount(@Param("roleName")String roleName);
	/**
	 * 新增权限
	 */
	int insertRole(Role role);
	
	/**
	 * 更新权限
	 */
	int updateRole(Role role);
	
	/**
	 * 删除权限
	 */
	int deleteRole(List<Role> roles);
	
	/**
	 * 根据ID查询角色
	 * @param roleId
	 * @return
	 */
	Role queryRoleById(@Param("roleId")String roleId);
	
	/**
	 * 查询当前角色已有/未有的权限
	 * @param roleId
	 * @return
	 */
	List<Auth> existOrOptionalAuths(@Param("roleId")String roleId, @Param("selectType")String selectType,
			@Param("authName")String authName);
	
	/**
	 * 删除角色的权限
	 * @param roleId
	 * @param auths
	 * @return
	 */
	int removeAuthFromRole(@Param("roleId")String roleId, @Param("list")List<Auth> auths);
	
	/**
	 * 为角色增加权限
	 * @param roleId
	 * @param auths
	 * @return
	 */
	int addAuthToRole(@Param("roleId")String roleId, @Param("list")List<Auth> auths);
	
	

	/**
	 * 根据角色ID查询该角色所有的权限
	 * @param roleId
	 * @return
	 */
	List<Auth> queryAuthsByRoleId(int roleId);
	
	/**
	 * 根据角色ID获取角色对象
	 * @param roleId
	 * @return
	 */
	Role queryRoleById(int roleId);
	
}
