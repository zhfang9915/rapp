package org.rapp.comm.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则验证工具类
 * @author 张芳
 *
 */
public class MatcherUtil {

	/**
	 * 邮箱格式校验
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		Pattern regex = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
		Matcher matcher = regex.matcher(email);
		return matcher.matches();
	}

}
