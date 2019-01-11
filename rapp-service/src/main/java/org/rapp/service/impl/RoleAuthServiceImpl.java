package org.rapp.service.impl;

import java.util.List;
import java.util.Map;

import org.rapp.comm.exception.BaseServiceException;
import org.rapp.pojo.account.Auth;
import org.rapp.pojo.account.Role;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.repository.RoleAuthDao;
import org.rapp.service.RoleAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleAuthServiceImpl implements RoleAuthService {

	@Autowired
	RoleAuthDao roleAuthDao;

	/**
	 * 角色授权
	 */
	@Override
	public Role accreditRole(int roleId) {
		Role role = roleAuthDao.queryRoleById(roleId);
		List<Auth> auths = roleAuthDao.queryAuthsByRoleId(roleId);
		role.setAuths(auths);
		return role;
	}

	@Override
	public TableResult<Auth> queryAuths(Map<String, Object> param) {
		TableResult<Auth> result = new TableResult<>();
		List<Auth> auths = roleAuthDao.queryAuths(
				(String) param.get("offset"), 
				(String) param.get("limit"),
				(String) param.get("search")
			);
		int count = roleAuthDao.queryAuthsCount((String) param.get("search"));
		result.setRows(auths);
		result.setTotal(count);
		return result;
	}

	@Override
	public BaseResult<String> insertAuth(Auth auth) {
		int insertCount = roleAuthDao.insertAuth(auth);
		if (insertCount == 1) {
			return new BaseResult<String>(true, "新增权限成功");
		}
		return new BaseResult<String>(false, "新增权限失败");
	}

	@Override
	public BaseResult<String> updateAuth(Auth auth) {
		int updateCount = roleAuthDao.updateAuth(auth);
		if (updateCount == 1) {
			return new BaseResult<String>(true, "更新权限成功");
		}
		return new BaseResult<String>(false, "更新权限失败");
	}

	@Override
	public BaseResult<String> deleteAuth(List<Auth> auths) {
		try {
			int deleteCount = roleAuthDao.deleteAuth(auths);
			if (deleteCount > 0) {
				return new BaseResult<String>(true, "删除权限成功");
			}
			return new BaseResult<String>(false, "删除权限失败");
		} catch (Exception e) {
			throw new BaseServiceException("删除失败，该权限正在被使用！");
		}
	}
	
	
	
	@Override
	public TableResult<Role> queryRoles(Map<String, Object> param) {
		TableResult<Role> result = new TableResult<>();
		List<Role> roles = roleAuthDao.queryRoles(
				(String) param.get("offset"), 
				(String) param.get("limit"),
				(String) param.get("search")
			);
		int count = roleAuthDao.queryRolesCount((String) param.get("search"));
		result.setRows(roles);
		result.setTotal(count);
		return result;
	}
	
	@Override
	public Role queryRoleById(String roleId) {
		return roleAuthDao.queryRoleById(roleId);
	}

	@Override
	public BaseResult<String> insertRole(Role role) {
		int insertCount = roleAuthDao.insertRole(role);
		if (insertCount == 1) {
			return new BaseResult<String>(true, "新增角色成功");
		}
		return new BaseResult<String>(false, "新增角色失败");
	}

	@Override
	public BaseResult<String> updateRole(Role role) {
		int updateCount = roleAuthDao.updateRole(role);
		if (updateCount == 1) {
			return new BaseResult<String>(true, "更新角色成功");
		}
		return new BaseResult<String>(false, "更新角色失败");
	}

	@Override
	public BaseResult<String> deleteRole(List<Role> roles) {
		try {
			int deleteCount = roleAuthDao.deleteRole(roles);
			
			if (deleteCount > 0) {
				return new BaseResult<String>(true, "删除角色成功");
			}
			return new BaseResult<String>(false, "删除角色失败");
		} catch (Exception e) {
			throw new BaseServiceException("删除失败，该角色正在被使用！");
		}
	}

	@Override
	public TableResult<Auth> existOrOptionalAuths(String methed, String roleId, Map<String, Object> params) {
		TableResult<Auth> result = new TableResult<>();
		List<Auth> auths = roleAuthDao.existOrOptionalAuths(roleId, methed, (String)params.get("search"));
		result.setRows(auths);
		result.setTotal(0);
		return result;
	}

	@Override
	public BaseResult<String> authConfiguration(String method, String roleId, List<Auth> auths) {
		if ("add".equals(method)) {
			int add = roleAuthDao.addAuthToRole(roleId, auths);
			if (add > 0) {
				return new BaseResult<>("授权成功");
			}else {
				return new BaseResult<>(false, "授权失败");
			}
		}else if ("remove".equals(method)) {
			int remove = roleAuthDao.removeAuthFromRole(roleId, auths);
			if (remove > 0) {
				return new BaseResult<>("销权成功");
			}else {
				return new BaseResult<>(false, "销权失败");
			}
		}else {
			return new BaseResult<>(false, "您的请求未被定义");
		}
	}



}
