/**
 *
 */

package org.rapp.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.rapp.pojo.account.User;
import org.rapp.pojo.dto.param.UserRegister;
import org.rapp.pojo.dto.result.BaseResult;

/** 
 * @ClassName: UserService 
 * @Description: TODO
 * @author zhfang
 *  
 */
public interface UserService {
	
	/**
	 * 根据偏移量查询账户
	 */
	List<User> queryAllUsers(HttpServletRequest request);
	int queryAllUsersCount(HttpServletRequest request);
	
	/**
	 * 根据用户名来查询用户登录信息
	 */
	User queryUserByName(String username);
	
	/**
	 * 根据邮箱查用户
	 * @param email
	 * @return
	 */
	User queryUserByEmail(String email);
	
	/**
	 * 验证密码
	 */
	boolean validatePassword(String password, String dbpassword);
	
	/**
	 * 更新或新增账户
	 */
	boolean insertOrUpdateUser(User user);
	
	/**
	 * 删除账户
	 */
	BaseResult<Boolean> deleteUser(List<User> users);
	
	/**
	 * 用户注册并发送激活邮件
	 * @param ur
	 * @return
	 */
	boolean register(UserRegister ur);
	
	/**
	 * 注册邮件重发
	 * @param username
	 * @param email
	 */
	void sendRegisterEmailAgain(String username, String email);
	
	/**
	 * 激活账户
	 * @param username
	 * @return
	 */
	boolean activate(String username);
	
	/**
	 * 找回密码
	 * @param ur
	 * @return
	 */
	boolean findPassword(UserRegister ur);
	
	/**
	 * 更新密码
	 * @param username
	 * @param password
	 * @return
	 */
	BaseResult<String> changePassword(String username, String password);
	
	/**
	 * 查询用户的账户信息
	 * @param user
	 * @return
	 */
	Map<String, Object> queryUserInfo(User user);

}
