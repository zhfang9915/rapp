package org.rapp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.rapp.comm.util.AESUtil;
import org.rapp.comm.util.CalendarUtil;
import org.rapp.comm.util.EmailType;
import org.rapp.comm.util.MD5Utils;
import org.rapp.comm.velocity.VelocityUtil;
import org.rapp.pojo.account.User;
import org.rapp.pojo.dto.param.UserRegister;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.email.EmailFindPwd;
import org.rapp.pojo.email.EmailRegister;
import org.rapp.pojo.web.Rapp;
import org.rapp.repository.AdvertDao;
import org.rapp.repository.AdvertLogDao;
import org.rapp.repository.ApiDao;
import org.rapp.repository.ChannelDao;
import org.rapp.repository.RouterDao;
import org.rapp.repository.UserDao;
import org.rapp.service.MailService;
import org.rapp.service.UserService;
import org.rapp.service.statistical.DataStatisticalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** 
 * @ClassName: UserServiceImpl 
 * @author zhfang
 *  
 */
@Service
public class UserServiceImpl implements UserService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	Rapp rapp;
	
	@Autowired
	VelocityUtil velocityUtil;
	
	@Autowired RouterDao routerDao;
	
	@Autowired AdvertDao advertDao;
	
	@Autowired AdvertLogDao advertLogDao;
	
	@Autowired DataStatisticalService dsService;
	
	
	
	@Override
	public Map<String, Object> queryUserInfo(User user) {
		Map<String, Object> map = new HashMap<>();
		// 查询名下的设备总数
		int countRouter = routerDao.queryUserRouterCount(user.getUsername());
		//在线设备
		int online = dsService.statisticalRouterOnlineOrActive(CalendarUtil.getAssignDate(0, "yyyy-MM-dd"), ApiDao.ONLINE, user.getUsername());
		map.put("onlineRouter", online);
		map.put("offlineRouter", countRouter - online);
		//激活的设备
		int active = routerDao.queryActiceRouterCount(user.getUsername());
		map.put("activeRouter", active);
		map.put("unActiveRouter", countRouter - active);
		
		Map<String, Long> totalMap = advertLogDao.totalPushAtDay(CalendarUtil.getAssignDate(-1, "yyyy-MM-dd"), user.getUsername());
		if(totalMap == null){
			map.put("pushCountYestoday", 0);
			map.put("onlineUser", 0);
		}else{
			map.put("pushCountYestoday", totalMap.get("pushAdvertCount") == null ? 0 : totalMap.get("pushAdvertCount"));
			map.put("onlineUser", totalMap.get("onlineUser") == null ? 0 : totalMap.get("onlineUser"));
		}
		
		
		return map;
	}
	
	@Override
	public User queryUserByName(String username) {
		logger.debug("queryUserByName username:" + username);
		return userDao.queryUserByName(username);
	}
	
	@Override
	public User queryUserByEmail(String email) {
		logger.debug("queryUserByEmail email:" + email);
		return userDao.queryUserByEmail(email);
	}

	@Override
	public List<User> queryAllUsers(HttpServletRequest request){
		String limit = request.getParameter("limit");
		String offset = request.getParameter("offset");
		String username = request.getParameter("username");
		String state = request.getParameter("state");
		String roleId = request.getParameter("roleId");
		return userDao.queryAllUsers(offset, limit, username, state, roleId);
	}
	
	@Override
	public int queryAllUsersCount(HttpServletRequest request){
		String username = request.getParameter("username");
		String state = request.getParameter("state");
		String roleId = request.getParameter("roleId");
		return userDao.queryAllUsersCount(username, state, roleId);
	}
	
	@Override
	public boolean validatePassword(String password, String dbpassword) {
		if (StringUtils.isBlank(password) || StringUtils.isBlank(dbpassword)) {
			//参数为空
			return false;
		}
		//将传入的密码 md5
		String md5password = MD5Utils.getMD5(password);
		if (md5password.equals(dbpassword)) {
			//密码匹配
			return true;
		}
		//密码不正确
		return false;
	}

	@Override
	public boolean insertOrUpdateUser(User user) {
		//存在state不为0则为更新
		int state = user.getState();
		if (state==0) {//新增
			user.setPassword(MD5Utils.getMD5(user.getPassword()));
			int insertCount = userDao.insertUser(user);
			if (insertCount == 1) {
				return true;	//新增成功
			}
		} else {//更新
			int updateCount = userDao.updateUser(user);
			if (updateCount == 1) {
				return true;	//更新成功
			}
		}
		return false;
	}

	@Override
	public BaseResult<Boolean> deleteUser(List<User> users) {
		int deleteCount = userDao.deleteUser(users);
		if (deleteCount > 0) {
			return new BaseResult<Boolean>(true, "删除用户成功");	//删除成功
		}
		return new BaseResult<Boolean>(false, "删除用户失败");
	}
	
	@Override
	public boolean register(UserRegister ur) {
		User user = new User();
		user.setEmail(ur.getEmail());
		user.setUsername(ur.getUsername());
		user.setPassword(MD5Utils.getMD5(ur.getPassword()));
		user.setRoleId(3);//普通用户
		//保存用户信息
		int count = userDao.insertUser(user);
		if (count == 1) {
			//保存成功后发邮件给用户激活链接
			String usernameAES = AESUtil.encrypt(ur.getUsername(), MD5Utils.salt);
			String token = MD5Utils.getMD5(usernameAES);
			String activateUrl = rapp.getRootPath() + "user/activate/" + usernameAES + "/" + token;
			EmailRegister er = new EmailRegister(ur.getUsername(), activateUrl);
			String emailBody = velocityUtil.createEmailTemplate(EmailType.用户注册.getValue(), er);
			mailService.sendHtml(ur.getEmail(), "前辰无线平台账户：" + ur.getUsername() + "激活", emailBody);
			return true;	//新增成功
		}
		return false;
	}
	
	@Override
	public void sendRegisterEmailAgain(String username, String email) {
		User user = new User();
		user.setEmail(email);
		user.setUsername(username);
		//保存成功后发邮件给用户激活链接
		String usernameAES = AESUtil.encrypt(user.getUsername(), MD5Utils.salt);
		String token = MD5Utils.getMD5(usernameAES);
		String activateUrl = rapp.getRootPath() + "user/activate/" + usernameAES + "/" + token;
		EmailRegister er = new EmailRegister(user.getUsername(), activateUrl);
		String emailBody = velocityUtil.createEmailTemplate(EmailType.用户注册.getValue(), er);
		mailService.sendHtml(user.getEmail(), "前辰无线平台账户：" + user.getUsername() + "激活", emailBody);
	}

	@Override
	public boolean activate(String username) {
		User user = new User();
		user.setUsername(username);
		user.setState(1);
		int count = userDao.updateUser(user);
		if (count == 1) {
			return true;	//激活成功
		}
		return false;
	}
	
	@Override
	public boolean findPassword(UserRegister ur) {
		//重置密码为随机6未数字
		int randPassword = (int)((Math.random()*9+1)*100000);
		User user = new User();
		user.setUsername(ur.getUsername());
		user.setPassword(MD5Utils.getMD5(randPassword+""));
		int count = userDao.updatePassword(user);
		if (count == 1) {
			//保存成功后发邮件给用户激活链接
			String loginUrl = rapp.getRootPath() + "login/index";
			EmailFindPwd ef = new EmailFindPwd(ur.getUsername(), randPassword+"", loginUrl);
			String emailBody = velocityUtil.createEmailTemplate(EmailType.找回密码.getValue(), ef);
			mailService.sendHtml(ur.getEmail(), "前辰无线平台账户：" + ur.getUsername() + "找回密码", emailBody);
			return true;
		}
		return false;
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	@Override
	public BaseResult<String> changePassword(String username, String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(MD5Utils.getMD5(password));
		int count = userDao.updatePassword(user);
		if (count > 0) {
			return new BaseResult<>(true, "密码修改成功，请重新登录");	//删除成功
		}
		return new BaseResult<>(false, "密码修改失败");
	}

}
