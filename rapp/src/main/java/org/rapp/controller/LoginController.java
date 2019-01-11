package org.rapp.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.rapp.controller.base.BaseController;
import org.rapp.pojo.account.Role;
import org.rapp.pojo.account.User;
import org.rapp.service.Keys;
import org.rapp.service.RoleAuthService;
import org.rapp.service.UserService;
import org.rapp.service.log.LoginLogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 登录认证处理器
 * @author 张芳
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

	@Resource
	UserService userService;
	
	@Resource
	RoleAuthService roleAuthService;
	
	@Resource
	LoginLogService logService;
	
	/**
	 * 登录页
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() throws UnsupportedEncodingException {
		String toUrl = getRequest().getParameter("to");
		if (toUrl == null) {
			toUrl = "index";
		}
		getRequest().setAttribute("resouesUrl", URLEncoder.encode(toUrl, "UTF-8"));
		return "login";
	}
	
	@RequestMapping(value = "/validataRand", produces = "application/json;charset=UTF-8")
    public @ResponseBody String validataRand() {
		String randCode = getRequest().getParameter("rand");
		boolean flag = validateRandCode(randCode);
		
		Map<String, Boolean> map = new HashMap<>();
        map.put("valid", flag);
        
        return super.toJson(map);
	}
	
	/**
	 * @throws IOException 
	 * 登录校验
	 * @Title: login 
	 * @Description: TODO
	 * @param @param redirectAttributes
	 * @param @return    
	 * @return String    
	 * @throws
	 */
	@RequestMapping(value = "/authConfim", method = RequestMethod.POST)
	public String authConfim(RedirectAttributes redirectAttributes, HttpServletResponse response) throws IOException {
		//受限地址
		String url = getRequest().getParameter("url");
		
		String randCode = getRequest().getParameter("rand");
		String username = getRequest().getParameter("username");
		String password = getRequest().getParameter("pwd");
		
		redirectAttributes.addFlashAttribute("username", username);
		redirectAttributes.addFlashAttribute("pwd", password);
		redirectAttributes.addFlashAttribute("rand", randCode);
		
		//验证码校验
		if (validateRandCode(randCode)) {
			//验证码正确
			User account = userService.queryUserByName(username);
			if (account == null) {
				//未查询到记录 （用户名不对） 
				redirectAttributes.addFlashAttribute("errorMsg", "用户名不存在");
				return "redirect:/login/index?to=" + url;
			}else if (account.getState()!=1) {
				return "redirect:/user/activate/index?username=" + account.getUsername();
			}else {
				if (userService.validatePassword(password, account.getPassword())) {
					//密码正确则进行权限查询
					int roleId = account.getRoleId();
					Role role = roleAuthService.accreditRole(roleId);
					account.setRole(role);
//					account.setUsername("15036112810");
					getSession().setAttribute(Keys.LOGIN_USER, account);
					
					logService.saveLoginLog(getRequest());
					if (!"index".equals(url)) {
						response.sendRedirect(url);
						return null;
					}
					return "redirect:/" + url;
				}else {
					//密码错误
					redirectAttributes.addFlashAttribute("errorMsg", "密码错误");
					return "redirect:/login/index?to=" + url;
				}
			}
		}else {
			redirectAttributes.addFlashAttribute("errorMsg", "验证码错误");
			return "redirect:/login/index?to=" + url;
		}
	}
	
	/**
	 * 退出登录
	 * @Title: logout 
	 * @Description: TODO
	 * @param @return    
	 * @return String    
	 * @throws
	 */
	@RequestMapping(value="/exit", method=RequestMethod.GET)
	public String logout(){
		//清除redis 中的 session
		getSession().invalidate();
		return "redirect:/login/index";
	}
	
}
