package org.rapp.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.rapp.controller.base.BaseController;
import org.rapp.pojo.account.Role;
import org.rapp.pojo.account.User;
import org.rapp.pojo.web.Rapp;
import org.rapp.service.AdvertCheckService;
import org.rapp.service.AdvertService;
import org.rapp.service.RoleAuthService;
import org.rapp.service.UserService;
import org.rapp.service.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * Controller
 * @author 张芳
 */
@Controller
public class MainController extends BaseController {
	
	@Resource
	RoleAuthService roleAuthService;
	
	@Autowired
	AdvertService advertService;
	
	@Autowired
	AdvertCheckService advertCheckService;
	
	@Autowired UserService userService;
	
	@Autowired
	Rapp rapp;
	
	@RequestMapping("/index")
	public String index() {
		//验证是否登录，未登录则重定向登录页
		User user = (User) getSession().getAttribute(Keys.LOGIN_USER);
		if (user==null) {
			return "redirect:/login/index";
		}
		//加载目录
		Role role = user.getRole();
		getRequest().setAttribute("catalog", role.getMenu());
		if(user.getRole().getRoleId()==1){//超级管理员
			return "main";
		}
		
		//获取用户账户的信息
		Map<String, Object> map = userService.queryUserInfo(user);
		getRequest().setAttribute("map", map);
		return "main_user";
//		return "redirect:/apvert/list";
	}
	
	/**
	 * 个人中心
	 * @return
	 */
	@RequestMapping("/center")
	public String center(@SessionAttribute(Keys.LOGIN_USER)User user) {
		int unreadMsg4Advert = 0;//未读的广告审核消息/管理员未审核的消息
		if (user.getRole().getRoleId()<3) {//管理员
			unreadMsg4Advert = advertCheckService.tableUncheckAdverts();
			getRequest().setAttribute("unreadMsg4Advert_URL", rapp.getRootPath() + "apvert/uncheck");
		}else{
			unreadMsg4Advert = advertCheckService.totalUnreadAdvertMsg(user.getUsername());
			getRequest().setAttribute("unreadMsg4Advert_URL", rapp.getRootPath() + "apvert/unread");
		}
		getRequest().setAttribute("unreadMsg4Advert", unreadMsg4Advert);
		
		return "personal_center";
	}
	
	
	
	
	@RequestMapping(value = "/401", method = RequestMethod.GET)
	public String noPermiss() {
		//跳转无权限访问提示页
		return "error/401";
	}
}
