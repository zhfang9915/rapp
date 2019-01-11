package org.rapp.controller;

import java.util.HashMap;
import java.util.List;
//import java.util.*;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.rapp.comm.annotation.NotLogin;
import org.rapp.comm.annotation.Permission;
import org.rapp.comm.util.AESUtil;
import org.rapp.comm.util.MD5Utils;
import org.rapp.comm.util.MatcherUtil;
import org.rapp.controller.base.BaseController;
import org.rapp.pojo.account.Role;
import org.rapp.pojo.account.User;
import org.rapp.pojo.dto.param.UserRegister;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.service.Keys;
import org.rapp.service.RoleAuthService;
import org.rapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 用户管理
 * @author 张芳
 */
@RequestMapping("/user")
@Controller
public class UserController extends BaseController {
	@Resource
	UserService userService;
	
	@Autowired
	RoleAuthService roleAuthService;
	
	private final String USER = "user/";
	
	/**
	 * 用户管理首页
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	@Permission("/user/index")
	public String index() {
		List<Role> roles = roleAuthService.queryRoles(super.transformMapParam(getRequest())).getRows();
		if (roles==null) {
			getRequest().setAttribute("result", new BaseResult<List<Role>>(false, "没有可选角色"));
		}else {
			getRequest().setAttribute("result", new BaseResult<List<Role>>(roles));
		}
		return USER + "index";
	}
	
	/**
	 * 查询账户列表
	 * @return
	 */
	@RequestMapping(value = "/accountList", method = RequestMethod.GET,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@Permission("/user/accountList")
	public TableResult<User> accountList (HttpServletRequest request) { 
		List<User> users = userService.queryAllUsers(request);
		int total = userService.queryAllUsersCount(request);
		return new TableResult<>(total, users);
	}
	
	/**
	 * 新增账户
	 * @return
	 */
	@RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	@Permission("/user/addOrUpdate")
	public BaseResult<String> addOrUpdate(User user, HttpServletRequest request) {
		// 判断是增加还是修改
		String method = request.getParameter("method");
		if (method == "add") {
			// 增加
			// 先判断此用户是否存在
			User tmpUser = userService.queryUserByName(user.getUsername());
			if (tmpUser != null) {
				return new BaseResult<String>(false, "该用户名已存在");
			}
		}
		
		boolean flag = userService.insertOrUpdateUser(user);
		if (flag == true) {
			System.out.println("[addOrUpdate] success");
			return new BaseResult<String>(true, "操作成功");
		} else {
			System.out.println("[addOrUpdate] failure");
			return new BaseResult<String>(false, "操作失败");
		}
	}
	
	/**
	 * 删除账户
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	@Permission("/user/delete")
	public BaseResult<Boolean> delete(@RequestBody List<User> users) {
		return userService.deleteUser(users);
	}
	
	/**
	 * 验证账户
	 * @return
	 */
	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	@Permission("/user/validate")
	@ResponseBody
	public String validate(String username) {
		User user = userService.queryUserByName(username);
		if (user == null) {
			System.out.println("用户不存在，可以创建");
			return "该用户名已经被占用啦";
		} else {
			System.out.println("该用户名已经被占用啦");
			return "该用户名已经被占用";
		}
	}
	
	/**
	 * 注册校验用户名
	 * @return
	 */
	@RequestMapping(value = "/validate/user", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@NotLogin
	public String validataUser() {
		String username = getRequest().getParameter("username");
		User user = userService.queryUserByName(username);
		boolean flag = false;
		if (user == null) {
			flag = true;
		}
		Map<String, Boolean> map = new HashMap<>();
        map.put("valid", flag);
        
        return super.toJson(map);
	}
	
	/**
	 * 注册校验用户名
	 * @return
	 */
	@RequestMapping(value = "/validate/email", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@NotLogin
	public String validataEmail() {
		String email = getRequest().getParameter("email");
		User user = userService.queryUserByEmail(email);
		boolean flag = false;
		if (user == null) {
			flag = true;
		}
		Map<String, Boolean> map = new HashMap<>();
        map.put("valid", flag);
        
        return super.toJson(map);
	}
	
	/**
	 * 注册页面
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	@NotLogin
	public String register() {
		return USER + "register";
	}
	
	/**
	 * 提交注册信息
	 * @param redirectAttributes
	 * @param ur
	 * @return
	 */
	@RequestMapping(value = "/register/submit", method = RequestMethod.POST)
	@NotLogin
	public String registerSubmit(RedirectAttributes redirectAttributes, UserRegister ur) {
		redirectAttributes.addFlashAttribute("ur", ur);
		//验证用户名是否被占用
		User user = userService.queryUserByName(ur.getUsername());
		if (user != null) {
			redirectAttributes.addFlashAttribute("errun", "该用户名已经被占用");
			return "redirect:/user/register";
		}
		//验证两次密码是否一致
		if (!ur.getPassword().equals(ur.getPassword_repeat())) {
			redirectAttributes.addFlashAttribute("errpr", "两次密码不一致");
			return "redirect:/user/register";
		}
		//验证邮箱格式
		if (!MatcherUtil.isEmail(ur.getEmail())) {
			redirectAttributes.addFlashAttribute("erremail", "邮箱格式不正确");
			return "redirect:/user/register";
		}
		
		BaseResult<UserRegister> result = null;
		//注册
		boolean flag = userService.register(ur);
		if (flag) {
			result = new BaseResult<>(ur);
		}else{
			result = new BaseResult<>(false, "注册失败，请重试！");
		}
		redirectAttributes.addFlashAttribute("result", result);
		return "redirect:/user/register/result";
	}
	
	/**
	 * 注册邮件重发
	 * @param username
	 * @param email
	 */
	@RequestMapping(value = "/register/sendAgain", method = RequestMethod.POST, 
			produces = "application/json;charset=UTF-8")
	@NotLogin
	public void sendRegisterEmailAgain(String username, String email) {
		userService.sendRegisterEmailAgain(username, email);
	}
	
	/**
	 * 结果页
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/register/result", method = RequestMethod.GET)
	@NotLogin
	public String registerResult() {
		return USER + "register-result";
	}
	
	/**
	 * 用户激活
	 * @return
	 */
	@RequestMapping(value = "/activate/index", method = RequestMethod.GET)
	@NotLogin
	public String activateIndex() {
		String username = getRequest().getParameter("username");
		User account = userService.queryUserByName(username);
		if (account == null) {
			getRequest().setAttribute("flag", false);
		}else {
			getRequest().setAttribute("flag", true);
			getRequest().setAttribute("account", account);
		}
		return USER + "activate";
	}
	
	/**
	 * 用户激活
	 * @param usernameMd5 username加密的AES字符串
	 * @param token username加密的AES字符串再md5加密的字符串
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/activate/{usernameEncrypt}/{token}", method = RequestMethod.GET)
	@NotLogin
	public String activate(RedirectAttributes redirectAttributes, @PathVariable("usernameEncrypt")String username,
			@PathVariable("token")String token) {
		BaseResult<String> result = null;
		//验证token
		String md5 = MD5Utils.getMD5(username);
		if (md5.equals(token)) {//信息未被篡改，解密username
			username = AESUtil.decrypt(username, MD5Utils.salt);
			//通过解密的username验证是否存在改用户
			User user = userService.queryUserByName(username);
			if (user != null) {
				//存在该用户  则激活
				boolean flag = userService.activate(username);
				if (flag) {
					//激活成功
					result = new BaseResult<String>(username);
					redirectAttributes.addFlashAttribute("result", result);
					return "redirect:/user/activate/result";
				}
			}
			
		}
		result = new BaseResult<String>(false, "激活失败，请联系管理员");
		redirectAttributes.addFlashAttribute("result", result);
		return "redirect:/user/activate/result";
	}
	
	/**
	 * 结果页
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/activate/result", method = RequestMethod.GET)
	@NotLogin
	public String activateResult() {
		return USER + "activate-result";
	}
	
	
	/**
	 * 找回密码
	 * @return
	 */
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	@NotLogin
	public String findPassword() {
		return USER + "find";
	}
	
	@RequestMapping(value = "/find/submit", method = RequestMethod.POST)
	@NotLogin
	public String findPasswordSubmit(RedirectAttributes redirectAttributes, UserRegister ur) {
		redirectAttributes.addFlashAttribute("ur", ur);
		String randCode = getRequest().getParameter("rand");
		if (validateRandCode(randCode)) {
			if (StringUtils.isBlank(ur.getUsername())) {
				redirectAttributes.addFlashAttribute("errun", "请填写用户名");
				return "redirect:/user/find";
			}
			//验证邮箱格式
			if (!MatcherUtil.isEmail(ur.getEmail())) {
				redirectAttributes.addFlashAttribute("erremail", "邮箱格式不正确");
				return "redirect:/user/find";
			}
			//验证用户名
			User user = userService.queryUserByName(ur.getUsername());
			if (user == null) {
				redirectAttributes.addFlashAttribute("errun", "请确认您的用户名填写是否正确");
				return "redirect:/user/find";
			}
			if (!ur.getEmail().equals(user.getEmail())) {
				redirectAttributes.addFlashAttribute("erremail", "填写的邮箱与注册邮箱不一致");
				return "redirect:/user/find";
			}
			BaseResult<UserRegister> result = null;
			//发送密码重置邮件
			boolean flag = userService.findPassword(ur);
			if (flag) {
				result = new BaseResult<UserRegister>(ur);
				getSession().setAttribute("FINDPASSOWRD-RESULT", result);
				return "redirect:/user/find/result";
			}
			result = new BaseResult<UserRegister>(false, "密码找回失败，请重试，若依然失败请联系管理员");
			redirectAttributes.addFlashAttribute("result", result);
			return "redirect:/user/find/result";
		}else {
			redirectAttributes.addFlashAttribute("errcode", "验证码错误");
			return "redirect:/user/find";
		}
	}
	
	/**
	 * 结果页
	 * @param result
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/find/result", method = RequestMethod.GET)
	@NotLogin
	public String findPasswordResult() {
		BaseResult<UserRegister> result = (BaseResult<UserRegister>) getSession().getAttribute("FINDPASSOWRD-RESULT");
		getRequest().setAttribute("result", result);
		return USER + "find-result";
	}
	
	/**
	 * 个人信息中心
	 * @return
	 */
	@RequestMapping(value = "/center", method = RequestMethod.GET)
	public String toMyCenter() {
		return USER + "user-center";
	}
	
	@RequestMapping(value = "/changePwd", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<String> changePassword(@SessionAttribute(Keys.LOGIN_USER)User user) {
		String newPwd = getRequest().getParameter("newPwd");
		String newPwdr = getRequest().getParameter("newPwdr");
		if (StringUtils.isBlank(newPwd) || !newPwd.equals(newPwdr)) {//密码格式不对
			return new BaseResult<>(false, "修改失败，密码格式不正确");
		}
		//修改密码
		return userService.changePassword(user.getUsername(), newPwd);
	}
	
}
