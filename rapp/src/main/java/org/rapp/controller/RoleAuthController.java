package org.rapp.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rapp.comm.annotation.Permission;
import org.rapp.controller.base.BaseController;
import org.rapp.pojo.account.Auth;
import org.rapp.pojo.account.Role;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.service.RoleAuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户管理
 * @author 张芳
 */
@RequestMapping("/ra")
@Controller
public class RoleAuthController extends BaseController {
	
	@Resource
	RoleAuthService roleAuthService;

	private final static String ROLEAUTH = "roleauth/";
	
	/**
	 * 用户管理首页
	 * @return
	 */
	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	@Permission("/ra/auth")
	public String auth() {
		//只有管理员角色并且具有该权限才能访问
		return ROLEAUTH + "auth";
	}
	
	/**
	 * 查询权限列表
	 * @return
	 */
	@RequestMapping(value = "/auth/list", method = RequestMethod.GET,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@Permission("/ra/auth/list")
	public TableResult<Auth> queryAuths(){
		return roleAuthService.queryAuths(super.transformMapParam(getRequest()));
	}
	
	/**
	 * 新增或更新权限
	 * @param auth
	 * @return
	 */
	@RequestMapping(value = "/auth/addOrUpdate", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@Permission("/ra/auth/addOrUpdate")
	public BaseResult<String> addOrUpdateAuth(Auth auth) {
		String method = getRequest().getParameter("method");
		if ("add".equals(method)) {//新增
			return roleAuthService.insertAuth(auth);
		}else if ("edit".equals(method)) {//编辑
			return roleAuthService.updateAuth(auth);
		}
		return new BaseResult<String>(false, "您的操作未被定义");
	}
	
	/**
	 * 删除权限
	 * @param auth
	 * @return
	 */
	@RequestMapping(value = "/auth/delete", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@Permission("/ra/auth/delete")
	public BaseResult<String> deleteAuth(@RequestBody List<Auth> auths) {
		return roleAuthService.deleteAuth(auths);
	}
	
	
	/**
	 * 角色管理首页
	 * @return
	 */
	@RequestMapping(value = "/role", method = RequestMethod.GET)
	@Permission("/ra/role")
	public String role() {
		
		return ROLEAUTH + "role";
	}
	
	/**
	 * 查询角色列表
	 * @return
	 */
	@RequestMapping(value = "/role/list", method = RequestMethod.GET,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@Permission("/ra/role/list")
	public TableResult<Role> queryRoles(){
		return roleAuthService.queryRoles(super.transformMapParam(getRequest()));
	}
	
	/**
	 * 新增或更新角色
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/role/addOrUpdate", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@Permission("/ra/role/addOrUpdate")
	public BaseResult<String> addOrUpdateRole(Role role) {
		String method = getRequest().getParameter("method");
		if ("add".equals(method)) {//新增
			return roleAuthService.insertRole(role);
		}else if ("edit".equals(method)) {//编辑
			return roleAuthService.updateRole(role);
		}
		return new BaseResult<String>(false, "您的操作未被定义");
	}
	
	/**
	 * 删除角色
	 * @param auth
	 * @return
	 */
	@RequestMapping(value = "/role/delete", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@Permission("/ra/role/delete")
	public BaseResult<String> deleteRole(@RequestBody List<Role> roles) {
		return roleAuthService.deleteRole(roles);
	}
	
	/**
	 * 角色详情管理首页
	 * @return
	 */
	@RequestMapping(value = "/role/detail/{roleId}", method = RequestMethod.GET)
	@Permission("/ra/role/detail")
	public String roleDetail(@PathVariable("roleId") String roleId) {
		getRequest().setAttribute("roleId", roleId);//反传角色ID
		getRequest().setAttribute("role", roleAuthService.queryRoleById(roleId));
		return ROLEAUTH + "role_detail";
	}
	
	/**
	 * 查询该角色已有的权限列表
	 * @param methed
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/role/exist/{roleId}", method = RequestMethod.GET,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@Permission("/ra/role/existAuths")
	public TableResult<Auth> existOrOptionalAuths(@PathVariable("roleId") String roleId) {
		Map<String, Object> params = transformMapParam(getRequest());
		//返回结果集
		return roleAuthService.existOrOptionalAuths("exist", roleId, params);
	}
	
	/**
	 * 查询该角色未有的权限列表
	 * @param methed
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/role/optional/{roleId}", method = RequestMethod.GET,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@Permission("/ra/role/optionalAuths")
	public TableResult<Auth> optionalAuths(@PathVariable("roleId") String roleId) {
		Map<String, Object> params = transformMapParam(getRequest());
		//返回结果集
		return roleAuthService.existOrOptionalAuths("optional", roleId, params);
	}
	
	/**
	 * 删除角色
	 * @param auth
	 * @return
	 */
	@RequestMapping(value = "/role/{roleId}/auth/{method}", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@Permission("/ra/role/authConfiguration")
	public BaseResult<String> authConfiguration(@PathVariable("roleId")String roleId,
			@PathVariable("method")String method, @RequestBody List<Auth> auths) {
		
		return roleAuthService.authConfiguration(method, roleId, auths);
	}
	
}
