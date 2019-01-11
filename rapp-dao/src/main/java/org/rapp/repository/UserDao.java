package org.rapp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.rapp.pojo.account.User;

/** 
 * @ClassName: userDao 
 * @author zhfang
 *  
 */
public interface UserDao {
	
	/**
	 * 根据偏移量查询账户
	 */
	List<User> queryAllUsers(@Param("offset")String offset, @Param("limit")String limit,
			@Param("username")String username, @Param("state")String state, @Param("roleId")String roleId);
	
	/**
	 * 所有账户数量
	 */
	int queryAllUsersCount(@Param("username")String username, 
			@Param("state")String state, @Param("roleId")String roleId);
	
	/**
	 * 根据用户名查询账号信息
	 */
	User queryUserByName(@Param("username")String username);
	
	/**
	 * 根据用户名查询账号信息
	 */
	User queryUserByEmail(@Param("email")String email);
	
	/**
	 * 新建账号
	 */
	int insertUser(User user);
	
	/**
	 * 更新账号
	 */
	int updateUser(User user);
	
	/**
	 * 删除账户
	 */
	int deleteUser(List<User> users);
	
	/**
	 * 更新密码
	 * @param user
	 * @return
	 */
	int updatePassword(User user);
	
}
