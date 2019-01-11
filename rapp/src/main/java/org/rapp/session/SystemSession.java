package org.rapp.session;

import org.rapp.pojo.account.User;

/**
 * 通过ThreadLocal管理用户session信息
 * @author 张芳
 *
 */
public class SystemSession {

	private static ThreadLocal<User> local = new ThreadLocal<User>();

	public static void setUserSession(User user) {
		local.set(user);
	}

	public static User getUserSession() {
		return local.get();
	}
}
